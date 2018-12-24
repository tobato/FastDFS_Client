package com.github.tobato.fastdfs.domain.proto;

/**
 * fastdfs协议命令的常量
 *
 * @author yuqih
 */
public final class CmdConstants {
    /**
     * 客户端关闭连接命令
     */
    public static final byte FDFS_PROTO_CMD_QUIT = 82;
    /**
     * 连接状态检查命令
     */
    public static final byte FDFS_PROTO_CMD_ACTIVE_TEST = 111;
    /**
     * 服务端正确返回报文状态
     */
    public static final byte FDFS_PROTO_CMD_RESP = 100;

    public static final byte TRACKER_PROTO_CMD_SERVER_LIST_GROUP = 91;
    public static final byte TRACKER_PROTO_CMD_SERVER_LIST_STORAGE = 92;
    public static final byte TRACKER_PROTO_CMD_SERVER_DELETE_STORAGE = 93;
    public static final byte TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITHOUT_GROUP_ONE = 101;
    public static final byte TRACKER_PROTO_CMD_SERVICE_QUERY_FETCH_ONE = 102;
    public static final byte TRACKER_PROTO_CMD_SERVICE_QUERY_UPDATE = 103;
    public static final byte TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITH_GROUP_ONE = 104;
    public static final byte TRACKER_PROTO_CMD_SERVICE_QUERY_FETCH_ALL = 105;
    public static final byte TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITHOUT_GROUP_ALL = 106;
    public static final byte TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITH_GROUP_ALL = 107;

    /**
     * 上传文件
     */
    public static final byte STORAGE_PROTO_CMD_UPLOAD_FILE = 11;
    public static final byte STORAGE_PROTO_CMD_DELETE_FILE = 12;
    public static final byte STORAGE_PROTO_CMD_SET_METADATA = 13;
    public static final byte STORAGE_PROTO_CMD_DOWNLOAD_FILE = 14;
    public static final byte STORAGE_PROTO_CMD_GET_METADATA = 15;
    public static final byte STORAGE_PROTO_CMD_UPLOAD_SLAVE_FILE = 21;
    public static final byte STORAGE_PROTO_CMD_QUERY_FILE_INFO = 22;
    /**
     * 创建一个支持断点续传的文件
     */
    public static final byte STORAGE_PROTO_CMD_UPLOAD_APPENDER_FILE = 23; // create
    // appender
    // file
    /**
     * 对文件断点续传(附加在原来为上传完全的文件后面)
     */
    public static final byte STORAGE_PROTO_CMD_APPEND_FILE = 24; // append file
    /**
     * 修改支持断点续传的文件
     */
    public static final byte STORAGE_PROTO_CMD_MODIFY_FILE = 34; // modify
    // appender
    // file
    /**
     * 清空支持断点的文件
     */
    public static final byte STORAGE_PROTO_CMD_TRUNCATE_FILE = 36; // truncate
    // appender
    // file
}
