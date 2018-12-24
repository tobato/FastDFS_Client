package com.github.tobato.fastdfs.domain.proto.mapper;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * java与C服务端数据交换时byte数组与int,long转换的工具类
 *
 * @author yuqih
 */
public class BytesUtil {
    /**
     * long convert to buff (big-endian)
     *
     * @param n long number
     * @return 8 bytes buff
     */
    public static byte[] long2buff(long n) {
        byte[] bs;

        bs = new byte[8];
        bs[0] = (byte) ((n >> 56) & 0xFF);
        bs[1] = (byte) ((n >> 48) & 0xFF);
        bs[2] = (byte) ((n >> 40) & 0xFF);
        bs[3] = (byte) ((n >> 32) & 0xFF);
        bs[4] = (byte) ((n >> 24) & 0xFF);
        bs[5] = (byte) ((n >> 16) & 0xFF);
        bs[6] = (byte) ((n >> 8) & 0xFF);
        bs[7] = (byte) (n & 0xFF);

        return bs;
    }

    /**
     * buff convert to long
     *
     * @param bs     the buffer (big-endian)
     * @param offset the start position based 0
     * @return long number
     */
    public static long buff2long(byte[] bs, int offset) {
        return (((long) (bs[offset] >= 0 ? bs[offset] : 256 + bs[offset])) << 56)
                | (((long) (bs[offset + 1] >= 0 ? bs[offset + 1] : 256 + bs[offset + 1])) << 48)
                | (((long) (bs[offset + 2] >= 0 ? bs[offset + 2] : 256 + bs[offset + 2])) << 40)
                | (((long) (bs[offset + 3] >= 0 ? bs[offset + 3] : 256 + bs[offset + 3])) << 32)
                | (((long) (bs[offset + 4] >= 0 ? bs[offset + 4] : 256 + bs[offset + 4])) << 24)
                | (((long) (bs[offset + 5] >= 0 ? bs[offset + 5] : 256 + bs[offset + 5])) << 16)
                | (((long) (bs[offset + 6] >= 0 ? bs[offset + 6] : 256 + bs[offset + 6])) << 8)
                | (bs[offset + 7] >= 0 ? bs[offset + 7] : 256 + bs[offset + 7]);
    }

    /**
     * buff convert to int
     *
     * @param bs     the buffer (big-endian)
     * @param offset the start position based 0
     * @return int number
     */
    public static int buff2int(byte[] bs, int offset) {
        return ((bs[offset] >= 0 ? bs[offset] : 256 + bs[offset]) << 24)
                | ((bs[offset + 1] >= 0 ? bs[offset + 1] : 256 + bs[offset + 1]) << 16)
                | ((bs[offset + 2] >= 0 ? bs[offset + 2] : 256 + bs[offset + 2]) << 8)
                | (bs[offset + 3] >= 0 ? bs[offset + 3] : 256 + bs[offset + 3]);
    }

    /**
     * 将String转换为byte
     *
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static byte[] objString2Byte(String value, int max, Charset charset) {
        byte[] fullContentBytes = new byte[max];
        // 填充默认值
        Arrays.fill(fullContentBytes, (byte) 0);
        if (null == value) {
            return fullContentBytes;
        }
        // 获取byte
        byte[] realContent = value.getBytes(charset);
        int length;
        if (realContent.length <= max) {
            length = realContent.length;
        } else {
            length = max;
        }
        // 复制数值
        System.arraycopy(realContent, 0, fullContentBytes, 0, length);
        return fullContentBytes;
    }

    /**
     * 将String转换为byte
     *
     * @param value
     * @param charset
     * @return
     */
    public static byte[] objString2Byte(String value, Charset charset) {
        if (null == value) {
            return null;
        }
        // 获取byte
        return value.getBytes(charset);

    }

}
