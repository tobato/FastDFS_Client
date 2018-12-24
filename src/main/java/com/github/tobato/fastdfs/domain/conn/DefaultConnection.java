package com.github.tobato.fastdfs.domain.conn;

import com.github.tobato.fastdfs.domain.proto.CmdConstants;
import com.github.tobato.fastdfs.domain.proto.OtherConstants;
import com.github.tobato.fastdfs.domain.proto.mapper.BytesUtil;
import com.github.tobato.fastdfs.exception.FdfsConnectException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * 默认连接实现
 *
 * @author tobato
 */
public class DefaultConnection implements Connection {

    /**
     * 封装socket
     */
    private Socket socket;

    /**
     * 字符集
     */
    private Charset charset;

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultConnection.class);

    /**
     * 创建与服务端连接
     *
     * @param address
     * @param soTimeout
     * @param connectTimeout
     */
    public DefaultConnection(InetSocketAddress address, int soTimeout, int connectTimeout, Charset charset) {
        try {
            socket = new Socket();
            socket.setSoTimeout(soTimeout);
            LOGGER.debug("connect to {} soTimeout={} connectTimeout={}", address, soTimeout, connectTimeout);
            this.charset = charset;
            socket.connect(address, connectTimeout);
        } catch (IOException e) {
            throw new FdfsConnectException("can't create connection to" + address, e);
        }
    }

    /**
     * 正常关闭连接
     */
    public synchronized void close() {
        LOGGER.debug("disconnect from {}", socket);
        byte[] header = new byte[OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 2];
        Arrays.fill(header, (byte) 0);

        byte[] hex_len = BytesUtil.long2buff(0);
        System.arraycopy(hex_len, 0, header, 0, hex_len.length);
        header[OtherConstants.PROTO_HEADER_CMD_INDEX] = CmdConstants.FDFS_PROTO_CMD_QUIT;
        header[OtherConstants.PROTO_HEADER_STATUS_INDEX] = (byte) 0;
        try {
            socket.getOutputStream().write(header);
            socket.close();
        } catch (IOException e) {
            LOGGER.error("close connection error", e);
        } finally {
            IOUtils.closeQuietly(socket);
        }

    }

    /**
     * 连接是否关闭
     */
    @Override
    public boolean isClosed() {
        return socket.isClosed();
    }

    /**
     * 检查连接是否有效
     */
    @Override
    public boolean isValid() {
        LOGGER.debug("check connection status of {} ", this);
        try {
            byte[] header = new byte[OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 2];
            Arrays.fill(header, (byte) 0);

            byte[] hex_len = BytesUtil.long2buff(0);
            System.arraycopy(hex_len, 0, header, 0, hex_len.length);
            header[OtherConstants.PROTO_HEADER_CMD_INDEX] = CmdConstants.FDFS_PROTO_CMD_ACTIVE_TEST;
            header[OtherConstants.PROTO_HEADER_STATUS_INDEX] = (byte) 0;
            socket.getOutputStream().write(header);
            if (socket.getInputStream().read(header) != header.length) {
                return false;
            }

            return header[OtherConstants.PROTO_HEADER_STATUS_INDEX] == 0 ? true : false;
        } catch (IOException e) {
            LOGGER.error("valid connection error", e);
            return false;
        }
    }

    /**
     * 获取输出流
     *
     * @return
     * @throws IOException
     */
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    /**
     * 获取输入流
     *
     * @return
     * @throws IOException
     */
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    /**
     * 获取字符集
     *
     * @return
     */
    public Charset getCharset() {
        return charset;
    }

}
