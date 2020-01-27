package com.github.tobato.fastdfs.domain.proto.storage;

import com.github.tobato.fastdfs.TestConstants;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * 文件缓存方式下载机制测试
 *
 * @author tobato
 */
public class DownloadFileStreamTest {

    @Test
    public void recv() throws IOException {

        String firstText = "加入文件缓存方式下载机制\r\n";
        InputStream input = getTextInputStream(firstText);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        //模拟输出，输出
        DownloadFileStream downloadFileStream = new DownloadFileStream(output);
        downloadFileStream.recv(input);
        String result = output.toString("UTF-8");
        //比对结果
        assertEquals(firstText, result);
        //关闭输入，输出
        IOUtils.closeQuietly(input);
        IOUtils.closeQuietly(output);

    }

    private InputStream getTextInputStream(String text) throws IOException {
        // 将String转换为InputStream
        return new ByteArrayInputStream(text.getBytes(TestConstants.DEFAULT_CHARSET));
    }
}