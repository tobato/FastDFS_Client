package com.github.tobato.fastdfs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试工具类
 * 
 * @author wuyf
 *
 */
public class TestUtils {

    private TestUtils() {
        // hide for utils
    }

    /**
     * 将String 转换为InputStream
     * 
     * @param text
     * @return
     * @throws IOException
     */
    public static InputStream getTextInputStream(String text) {
        // 将String转换为InputStream
        return new ByteArrayInputStream(text.getBytes(RemoteServiceDefine.DEFAULT_CHARSET));
    }

    /**
     * 获取String长度
     * 
     * @param text
     * @return
     * @throws IOException
     */
    public static long getTextLength(String text) {
        return text.getBytes(RemoteServiceDefine.DEFAULT_CHARSET).length;
    }

}
