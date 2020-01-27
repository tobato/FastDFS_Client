package com.github.tobato.fastdfs.domain.proto.storage;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Web环境下文件下载回调方法,默认按4K循环读取，防止下载时内存溢出
 * <pre>
 *
 * refactor:
 * 将HttpServletResponse调整为OutputStream对象，
 * 注意：使用时候在外层做response.getOutputStream()，使用完毕后，在外层做 os.close()
 * 如：
 *  os = response.getOutputStream();
 *  DownloadFileStream stream = new DownloadFileStream(os);
 *  ...
 *  os.close();
 *
 * </pre>
 *
 * @author xulb
 */
public class DownloadFileStream implements DownloadCallback<BufferedInputStream> {

    /**
     * 默认缓存长度
     */
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    /**
     * 输出流
     * HttpServletResponse对象response.getOutputStream()
     */
    private OutputStream outputStream;

    /**
     * 默认缓存长度
     */
    private int bufferLength = DEFAULT_BUFFER_SIZE;


    /**
     * 从HttpServletResponse对象response.getOutputStream()构造
     *
     * @param responseOutputStream 输出流
     */
    public DownloadFileStream(OutputStream responseOutputStream) {
        this.outputStream = responseOutputStream;
    }

    /**
     * 从HttpServletResponse对象response.getOutputStream()构造
     *
     * @param responseOutputStream 输出流
     * @param bufferLength         缓存长度
     */
    public DownloadFileStream(OutputStream responseOutputStream, int bufferLength) {
        this.outputStream = responseOutputStream;
        this.bufferLength = bufferLength;
    }

    /**
     * 文件接收处理
     *
     * @return
     */
    @Override
    public BufferedInputStream recv(InputStream ins) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(ins);
        // 实现文件下载
        byte[] buffer = new byte[bufferLength];
        try {
            IOUtils.copyLarge(ins, outputStream, buffer);
        } catch (IOException e) {
            throw new IOException("文件下载失败!", e);
        } finally {
            IOUtils.closeQuietly(bufferedInputStream);
        }
        return null;
    }

}
