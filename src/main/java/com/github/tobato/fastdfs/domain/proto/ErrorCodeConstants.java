package com.github.tobato.fastdfs.domain.proto;

/**
 * fastdfs协议错误码的常量
 *
 * @author yuqih
 */
public final class ErrorCodeConstants {

    public static final byte SUCCESS = 0;
    public static final byte ERR_NO_ENOENT = 2;
    public static final byte ERR_NO_EIO = 5;
    public static final byte ERR_NO_EBUSY = 16;
    public static final byte ERR_NO_EINVAL = 22;
    public static final byte ERR_NO_ENOSPC = 28;
    public static final byte ERR_NO_CONNREFUSED = 61;
    public static final byte ERR_NO_EALREADY = 114;

}
