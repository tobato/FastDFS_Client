package com.github.tobato.fastdfs.domain;

import java.io.InputStream;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.tobato.fastdfs.RemoteServiceDefine;
import com.github.tobato.fastdfs.TestUtils;

/**
 * 测试用随机字符文件
 * 
 * @author wuyf
 *
 */
public class RandomTextFile {

    private String text;

    private InputStream inputStream;

    private long fileSize;

    private String fileExtName = "text";

    public RandomTextFile() {
        this.text = RandomStringUtils.random(30, "762830abdcefghijklmnopqrstuvwxyz0991822-");
        this.inputStream = TestUtils.getTextInputStream(text);
        this.fileSize = TestUtils.getTextLength(text);
    }

    public String getText() {
        return text;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileExtName() {
        return fileExtName;
    }

    public byte[] toByte() {
        return this.text.getBytes(RemoteServiceDefine.DEFAULT_CHARSET);
    }

}
