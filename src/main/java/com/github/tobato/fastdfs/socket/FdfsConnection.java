package com.github.tobato.fastdfs.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tobato.fastdfs.exception.FdfsConnectException;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;
import com.github.tobato.fastdfs.proto.mapper.BytesUtil;

/**
 * represent a Fdfs connection
 * 
 * @author tobato
 *
 */
public class FdfsConnection extends Socket {

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(FdfsConnection.class);

    /**
     * 创建连接
     * 
     * @param address 地址
     * @param soTimeout read超时时间
     * @param connectTimeout 连接超时时间
     * @return
     */
    public static FdfsConnection create(InetSocketAddress address, int soTimeout, int connectTimeout) {
        try {
            FdfsConnection socket = new FdfsConnection();
            socket.setSoTimeout(soTimeout);
            LOGGER.debug("connect to ip={} , port={}", address.getHostString(), address.getPort());
            socket.connect(address, connectTimeout);
            return socket;
        } catch (IOException e) {
            throw new FdfsConnectException("can't create connection", e);
        }
    }

    /**
     * 关闭连接
     */
    @Override
    public synchronized void close() throws IOException {

        byte[] header = new byte[OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 2];
        Arrays.fill(header, (byte) 0);

        byte[] hex_len = BytesUtil.long2buff(0);
        System.arraycopy(hex_len, 0, header, 0, hex_len.length);
        header[OtherConstants.PROTO_HEADER_CMD_INDEX] = CmdConstants.FDFS_PROTO_CMD_QUIT;
        header[OtherConstants.PROTO_HEADER_STATUS_INDEX] = (byte) 0;
        // LOGGER.debug("say good bye {}", header);
        this.getOutputStream().write(header);

        super.close();
        LOGGER.debug("disconnect from {}", this);
    }

    /**
     * 检查连接状态
     * 
     * @return
     */
    protected boolean check() {
        try {
            byte[] header = new byte[OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 2];
            Arrays.fill(header, (byte) 0);

            byte[] hex_len = BytesUtil.long2buff(0);
            System.arraycopy(hex_len, 0, header, 0, hex_len.length);
            header[OtherConstants.PROTO_HEADER_CMD_INDEX] = CmdConstants.FDFS_PROTO_CMD_ACTIVE_TEST;
            header[OtherConstants.PROTO_HEADER_STATUS_INDEX] = (byte) 0;
            LOGGER.debug("check connection status of {} ", this);
            this.getOutputStream().write(header);

            if (this.getInputStream().read(header) != header.length) {
                return false;
            }

            return header[OtherConstants.PROTO_HEADER_STATUS_INDEX] == 0 ? true : false;
        } catch (IOException e) {
            return false;
        }

    }

}
