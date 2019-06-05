package com.github.tobato.fastdfs.domain.proto.storage.monkey;

import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.exception.FdfsIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 抛出例外的文件下载回调方法
 *
 * @author tobato
 */
public class MonkeyDownloadFileWriter implements DownloadCallback<String> {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(MonkeyDownloadFileWriter.class);

    /**
     * 文件名称
     */
    private String fileName;

    public MonkeyDownloadFileWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件接收处理
     */
    @Override
    public String recv(InputStream ins) throws IOException {
        byte[] buffer = new byte[2];
        ins.read(buffer);
        throw new FdfsIOException("模拟读取文件错误错误");
    }

}
