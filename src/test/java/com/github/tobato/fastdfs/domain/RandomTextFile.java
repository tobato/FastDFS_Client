package com.github.tobato.fastdfs.domain;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.TestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.InputStream;

/**
 * 测试用随机字符文件
 *
 * @author tobato
 */
public class RandomTextFile {

    private String text;

    private InputStream inputStream;

    private long fileSize;

    private String fileExtName = "txt";

    public RandomTextFile() {
        this.text = RandomStringUtils.random(30, "762830abdcefghijklmnopqrstuvwxyz0991822-");
        this.fileSize = TestUtils.getTextLength(text);
    }

    public RandomTextFile(String text) {
        this.text = text;
        this.fileSize = TestUtils.getTextLength(text);
    }

    public String getText() {
        return text;
    }

    public InputStream getInputStream() {
        this.inputStream = TestUtils.getTextInputStream(text);
        return inputStream;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileExtName() {
        return fileExtName;
    }

    public byte[] toByte() {
        return this.text.getBytes(TestConstants.DEFAULT_CHARSET);
    }

    public void setFileExtName(String fileExtName) {
        this.fileExtName = fileExtName;
    }
}
