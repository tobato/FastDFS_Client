package com.github.tobato.fastdfs.domain.proto;

/**
 * fastdfs协议暂时未分类的常量
 *
 * @author yuqih
 */
public final class OtherConstants {
    /**
     * for overwrite all old metadata
     */
    public static final byte STORAGE_SET_METADATA_FLAG_OVERWRITE = 'O';

    /**
     * for replace, insert when the meta item not exist, otherwise update it
     */
    public static final byte STORAGE_SET_METADATA_FLAG_MERGE = 'M';

    public static final int FDFS_PROTO_PKG_LEN_SIZE = 8;
    public static final int FDFS_PROTO_CMD_SIZE = 1;
    public static final int FDFS_PROTO_CONNECTION_LEN = 4;
    public static final int FDFS_GROUP_NAME_MAX_LEN = 16;
    public static final int FDFS_IPADDR_SIZE = 16;
    public static final int FDFS_DOMAIN_NAME_MAX_SIZE = 128;
    public static final int FDFS_VERSION_SIZE = 6;
    public static final int FDFS_STORAGE_ID_MAX_SIZE = 16;

    public static final String FDFS_RECORD_SEPERATOR = "\u0001";
    public static final String FDFS_FIELD_SEPERATOR = "\u0002";

    public static final int TRACKER_QUERY_STORAGE_FETCH_BODY_LEN = FDFS_GROUP_NAME_MAX_LEN + FDFS_IPADDR_SIZE - 1
            + FDFS_PROTO_PKG_LEN_SIZE;
    public static final int TRACKER_QUERY_STORAGE_STORE_BODY_LEN = FDFS_GROUP_NAME_MAX_LEN + FDFS_IPADDR_SIZE
            + FDFS_PROTO_PKG_LEN_SIZE;

    /**
     * 报文头中命令位置
     */
    public static final int PROTO_HEADER_CMD_INDEX = FDFS_PROTO_PKG_LEN_SIZE;
    /**
     * 报文头中状态码位置
     */
    public static final int PROTO_HEADER_STATUS_INDEX = FDFS_PROTO_PKG_LEN_SIZE + 1;

    public static final byte FDFS_FILE_EXT_NAME_MAX_LEN = 6;
    public static final byte FDFS_FILE_PREFIX_MAX_LEN = 16;
    public static final byte FDFS_FILE_PATH_LEN = 10;
    public static final byte FDFS_FILENAME_BASE64_LENGTH = 27;
    public static final byte FDFS_TRUNK_FILE_INFO_LEN = 16;

    public static final long INFINITE_FILE_SIZE = 256 * 1024L * 1024 * 1024 * 1024 * 1024L;
    public static final long APPENDER_FILE_SIZE = INFINITE_FILE_SIZE;
    public static final long TRUNK_FILE_MARK_SIZE = 512 * 1024L * 1024 * 1024 * 1024 * 1024L;
    public static final long NORMAL_LOGIC_FILENAME_LENGTH = FDFS_FILE_PATH_LEN + FDFS_FILENAME_BASE64_LENGTH
            + FDFS_FILE_EXT_NAME_MAX_LEN + 1;
    public static final long TRUNK_LOGIC_FILENAME_LENGTH = NORMAL_LOGIC_FILENAME_LENGTH + FDFS_TRUNK_FILE_INFO_LEN;
}
