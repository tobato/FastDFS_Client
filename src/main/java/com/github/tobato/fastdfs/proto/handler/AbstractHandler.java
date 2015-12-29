package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import com.github.tobato.fastdfs.exception.FdfsIOException;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.ErrorCodeConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;
import com.github.tobato.fastdfs.socket.PooledFdfsSocket;

/**
 * 封装与服务器交易公用逻辑
 * 
 * @author tobato
 *
 * @param <T>
 */
abstract class AbstractHandler<T> implements ICmdProtoHandler<T> {

    protected final Socket socket;
    protected byte errorCode;
    protected long contentLength;
    protected T result;

    /**
     * @param socket
     */
    protected AbstractHandler(Socket socket) {
        super();
        this.socket = socket;
    }

    /**
     * 处理
     * 
     */
    @Override
    public T handle() {
        try {
            send(socket.getOutputStream());
        } catch (IOException e) {
            if (socket instanceof PooledFdfsSocket) {
                ((PooledFdfsSocket) socket).setNeedDestroy(true);
            }
            throw new FdfsIOException("socket io exception occured while sending cmd", e);
        }
        try {
            receive(socket.getInputStream());
        } catch (IOException e) {
            if (socket instanceof PooledFdfsSocket) {
                ((PooledFdfsSocket) socket).setNeedDestroy(true);
            }
            throw new FdfsIOException("socket io exception occured while receive content", e);
        }

        if (errorCode == ErrorCodeConstants.SUCCESS) {
            return result;
        }
        throw FdfsServerException.byCode(errorCode);

    }

    protected abstract void send(OutputStream ous) throws IOException;

    protected abstract void receive(InputStream ins) throws IOException;

    /**
     * 打包报文头
     * 
     * @param cmd
     * @param contentLength
     * @return
     */
    protected byte[] packHeader(byte cmd, long contentLength) {
        byte[] header;
        byte[] hex_len;

        header = new byte[OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 2];
        Arrays.fill(header, (byte) 0);

        hex_len = BytesUtil.long2buff(contentLength);
        System.arraycopy(hex_len, 0, header, 0, hex_len.length);
        header[OtherConstants.PROTO_HEADER_CMD_INDEX] = cmd;
        header[OtherConstants.PROTO_HEADER_STATUS_INDEX] = (byte) 0;
        return header;
    }

    protected void receiveHeader(InputStream ins) throws IOException {

        byte[] header = new byte[OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 2];
        int bytes;
        if ((bytes = ins.read(header)) != header.length) {
            throw new IOException("recv package size " + bytes + " != " + header.length);
        }

        if (header[OtherConstants.PROTO_HEADER_CMD_INDEX] != CmdConstants.FDFS_PROTO_CMD_RESP) {
            throw new IOException("recv cmd: " + header[OtherConstants.PROTO_HEADER_CMD_INDEX]
                    + " is not correct, expect cmd: " + CmdConstants.FDFS_PROTO_CMD_RESP);
        }

        if (header[OtherConstants.PROTO_HEADER_STATUS_INDEX] != 0) {
            errorCode = header[OtherConstants.PROTO_HEADER_STATUS_INDEX];
            return;
        }

        long contentLength = BytesUtil.buff2long(header, 0);
        if (contentLength < 0) {
            throw new IOException("recv body length: " + contentLength + " < 0!");
        }

        errorCode = (byte) 0;
        this.contentLength = contentLength;
    }

    /**
     * 发送文件
     * 
     * @param ins
     * @param size
     * @param ous
     * @throws IOException
     */
    protected void sendFileContent(InputStream ins, long size, OutputStream ous) throws IOException {
        long remainBytes = size;
        byte[] buff = new byte[256 * 1024];
        int bytes;
        while (remainBytes > 0) {
            if ((bytes = ins.read(buff, 0, remainBytes > buff.length ? buff.length : (int) remainBytes)) < 0) {
                throw new IOException("the end of the stream has been reached. not match the expected size ");
            }

            ous.write(buff, 0, bytes);
            remainBytes -= bytes;
        }
    }

}
