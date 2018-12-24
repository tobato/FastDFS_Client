package com.github.tobato.fastdfs.domain.proto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tobato.fastdfs.domain.conn.Connection;
import com.github.tobato.fastdfs.exception.FdfsIOException;

/**
 * 交易命令抽象类
 *
 * @param <T>
 * @author tobato
 */
public abstract class AbstractFdfsCommand<T> implements FdfsCommand<T> {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFdfsCommand.class);

    /**
     * 表示请求消息
     */
    protected FdfsRequest request;

    /**
     * 解析反馈消息对象
     */
    protected FdfsResponse<T> response;

    /**
     * 对服务端发出请求然后接收反馈
     */
    public T execute(Connection conn) {
        // 封装socket交易 send
        try {
            send(conn.getOutputStream(), conn.getCharset());
        } catch (IOException e) {
            LOGGER.error("send conent error", e);
            throw new FdfsIOException("socket io exception occured while sending cmd", e);
        }

        try {
            return receive(conn.getInputStream(), conn.getCharset());
        } catch (IOException e) {
            LOGGER.error("receive conent error", e);
            throw new FdfsIOException("socket io exception occured while receive content", e);
        }

    }

    /**
     * 将报文输出规范为模板方法
     * <p>
     * <pre>
     * 1.输出报文头
     * 2.输出报文参数
     * 3.输出文件内容
     * </pre>
     *
     * @param out
     * @throws IOException
     */
    protected void send(OutputStream out, Charset charset) throws IOException {
        // 报文分为三个部分
        // 报文头
        byte[] head = request.getHeadByte(charset);
        // 交易参数
        byte[] param = request.encodeParam(charset);
        // 交易文件流
        InputStream inputFile = request.getInputFile();
        long fileSize = request.getFileSize();
        LOGGER.debug("发出交易请求..报文头为{}", request.getHead());
        LOGGER.debug("交易参数为{}", param);
        // 输出报文头
        out.write(head);
        // 输出交易参数
        if (null != param) {
            out.write(param);
        }
        // 输出文件流
        if (null != inputFile) {
            sendFileContent(inputFile, fileSize, out);
        }
    }

    /**
     * 接收这里只能确切知道报文头，报文内容(参数+文件)只能靠接收对象分析
     *
     * @param in
     * @return
     * @throws IOException
     */
    protected T receive(InputStream in, Charset charset) throws IOException {

        // 解析报文头
        ProtoHead head = ProtoHead.createFromInputStream(in);
        LOGGER.debug("服务端返回报文头{}", head);
        // 校验报文头
        head.validateResponseHead();

        // 解析报文体
        return response.decode(head, in, charset);

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
        LOGGER.debug("开始上传文件流大小为{}", size);
        long remainBytes = size;
        byte[] buff = new byte[256 * 1024];
        int bytes;
        while (remainBytes > 0) {
            if ((bytes = ins.read(buff, 0, remainBytes > buff.length ? buff.length : (int) remainBytes)) < 0) {
                throw new IOException("the end of the stream has been reached. not match the expected size ");
            }

            ous.write(buff, 0, bytes);
            remainBytes -= bytes;
            LOGGER.debug("剩余数据量{}", remainBytes);
        }
    }

}
