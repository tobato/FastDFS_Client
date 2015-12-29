package com.github.tobato.fastdfs;

import java.nio.charset.Charset;

import com.github.tobato.fastdfs.proto.OtherConstants;

/**
 * 存储状态报文解析
 * 
 * @author tobato
 *
 */
public class StorageStateBuilder {

    private static final int FIELD_INDEX_STATUS = 0;
    private static final int FIELD_INDEX_ID = 1;
    private static final int FIELD_INDEX_IP_ADDR = 2;
    private static final int FIELD_INDEX_DOMAIN_NAME = 3;
    private static final int FIELD_INDEX_SRC_IP_ADDR = 4;
    private static final int FIELD_INDEX_VERSION = 5;
    private static final int FIELD_INDEX_JOIN_TIME = 6;
    private static final int FIELD_INDEX_UP_TIME = 7;
    private static final int FIELD_INDEX_TOTAL_MB = 8;
    private static final int FIELD_INDEX_FREE_MB = 9;
    private static final int FIELD_INDEX_UPLOAD_PRIORITY = 10;
    private static final int FIELD_INDEX_STORE_PATH_COUNT = 11;
    private static final int FIELD_INDEX_SUBDIR_COUNT_PER_PATH = 12;
    private static final int FIELD_INDEX_CURRENT_WRITE_PATH = 13;
    private static final int FIELD_INDEX_STORAGE_PORT = 14;
    private static final int FIELD_INDEX_STORAGE_HTTP_PORT = 15;
    private static final int FIELD_INDEX_TOTAL_UPLOAD_COUNT = 16;
    private static final int FIELD_INDEX_SUCCESS_UPLOAD_COUNT = 17;
    private static final int FIELD_INDEX_TOTAL_APPEND_COUNT = 18;
    private static final int FIELD_INDEX_SUCCESS_APPEND_COUNT = 19;
    private static final int FIELD_INDEX_TOTAL_MODIFY_COUNT = 20;
    private static final int FIELD_INDEX_SUCCESS_MODIFY_COUNT = 21;
    private static final int FIELD_INDEX_TOTAL_TRUNCATE_COUNT = 22;
    private static final int FIELD_INDEX_SUCCESS_TRUNCATE_COUNT = 23;
    private static final int FIELD_INDEX_TOTAL_SET_META_COUNT = 24;
    private static final int FIELD_INDEX_SUCCESS_SET_META_COUNT = 25;
    private static final int FIELD_INDEX_TOTAL_DELETE_COUNT = 26;
    private static final int FIELD_INDEX_SUCCESS_DELETE_COUNT = 27;
    private static final int FIELD_INDEX_TOTAL_DOWNLOAD_COUNT = 28;
    private static final int FIELD_INDEX_SUCCESS_DOWNLOAD_COUNT = 29;
    private static final int FIELD_INDEX_TOTAL_GET_META_COUNT = 30;
    private static final int FIELD_INDEX_SUCCESS_GET_META_COUNT = 31;
    private static final int FIELD_INDEX_TOTAL_CREATE_LINK_COUNT = 32;
    private static final int FIELD_INDEX_SUCCESS_CREATE_LINK_COUNT = 33;
    private static final int FIELD_INDEX_TOTAL_DELETE_LINK_COUNT = 34;
    private static final int FIELD_INDEX_SUCCESS_DELETE_LINK_COUNT = 35;
    private static final int FIELD_INDEX_TOTAL_UPLOAD_BYTES = 36;
    private static final int FIELD_INDEX_SUCCESS_UPLOAD_BYTES = 37;
    private static final int FIELD_INDEX_TOTAL_APPEND_BYTES = 38;
    private static final int FIELD_INDEX_SUCCESS_APPEND_BYTES = 39;
    private static final int FIELD_INDEX_TOTAL_MODIFY_BYTES = 40;
    private static final int FIELD_INDEX_SUCCESS_MODIFY_BYTES = 41;
    private static final int FIELD_INDEX_TOTAL_DOWNLOAD_BYTES = 42;
    private static final int FIELD_INDEX_SUCCESS_DOWNLOAD_BYTES = 43;
    private static final int FIELD_INDEX_TOTAL_SYNC_IN_BYTES = 44;
    private static final int FIELD_INDEX_SUCCESS_SYNC_IN_BYTES = 45;
    private static final int FIELD_INDEX_TOTAL_SYNC_OUT_BYTES = 46;
    private static final int FIELD_INDEX_SUCCESS_SYNC_OUT_BYTES = 47;
    private static final int FIELD_INDEX_TOTAL_FILE_OPEN_COUNT = 48;
    private static final int FIELD_INDEX_SUCCESS_FILE_OPEN_COUNT = 49;
    private static final int FIELD_INDEX_TOTAL_FILE_READ_COUNT = 50;
    private static final int FIELD_INDEX_SUCCESS_FILE_READ_COUNT = 51;
    private static final int FIELD_INDEX_TOTAL_FILE_WRITE_COUNT = 52;
    private static final int FIELD_INDEX_SUCCESS_FILE_WRITE_COUNT = 53;
    private static final int FIELD_INDEX_LAST_SOURCE_UPDATE = 54;
    private static final int FIELD_INDEX_LAST_SYNC_UPDATE = 55;
    private static final int FIELD_INDEX_LAST_SYNCED_TIMESTAMP = 56;
    private static final int FIELD_INDEX_LAST_HEART_BEAT_TIME = 57;
    private static final int FIELD_INDEX_IF_TRUNK_FILE = 58;

    private static int fieldsTotalSize;
    private static FieldDefinition[] fieldsArray = new FieldDefinition[59];

    static {
        int offset = 0;

        fieldsArray[FIELD_INDEX_STATUS] = new FieldDefinition(offset, 1);
        offset += 1;

        fieldsArray[FIELD_INDEX_ID] = new FieldDefinition(offset, OtherConstants.FDFS_STORAGE_ID_MAX_SIZE);
        offset += OtherConstants.FDFS_STORAGE_ID_MAX_SIZE;

        fieldsArray[FIELD_INDEX_IP_ADDR] = new FieldDefinition(offset, OtherConstants.FDFS_IPADDR_SIZE);
        offset += OtherConstants.FDFS_IPADDR_SIZE;

        fieldsArray[FIELD_INDEX_DOMAIN_NAME] = new FieldDefinition(offset, OtherConstants.FDFS_DOMAIN_NAME_MAX_SIZE);
        offset += OtherConstants.FDFS_DOMAIN_NAME_MAX_SIZE;

        fieldsArray[FIELD_INDEX_SRC_IP_ADDR] = new FieldDefinition(offset, OtherConstants.FDFS_IPADDR_SIZE);
        offset += OtherConstants.FDFS_IPADDR_SIZE;

        fieldsArray[FIELD_INDEX_VERSION] = new FieldDefinition(offset, OtherConstants.FDFS_VERSION_SIZE);
        offset += OtherConstants.FDFS_VERSION_SIZE;

        fieldsArray[FIELD_INDEX_JOIN_TIME] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_UP_TIME] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_MB] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_FREE_MB] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_UPLOAD_PRIORITY] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_STORE_PATH_COUNT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUBDIR_COUNT_PER_PATH] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_CURRENT_WRITE_PATH] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_STORAGE_PORT] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_STORAGE_HTTP_PORT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_APPEND_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_APPEND_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_MODIFY_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_TRUNCATE_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_TRUNCATE_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_SET_META_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_SET_META_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_DELETE_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_DELETE_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_GET_META_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_GET_META_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_CREATE_LINK_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_CREATE_LINK_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_DELETE_LINK_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_DELETE_LINK_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_APPEND_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_APPEND_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_MODIFY_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_SYNC_IN_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_SYNC_IN_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_SYNC_OUT_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_SYNC_OUT_BYTES] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_FILE_OPEN_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_FILE_OPEN_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_FILE_READ_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_FILE_READ_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_TOTAL_FILE_WRITE_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_SUCCESS_FILE_WRITE_COUNT] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_LAST_SOURCE_UPDATE] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_LAST_SYNC_UPDATE] = new FieldDefinition(offset, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_LAST_SYNCED_TIMESTAMP] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_LAST_HEART_BEAT_TIME] = new FieldDefinition(offset,
                OtherConstants.FDFS_PROTO_PKG_LEN_SIZE);
        offset += OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;

        fieldsArray[FIELD_INDEX_IF_TRUNK_FILE] = new FieldDefinition(offset, 1);
        offset += 1;

        fieldsTotalSize = offset;
    }

    public static StorageState convert(byte[] bs, int offset, Charset charset) {

        StorageState state = new StorageState();
        state.status = StateBuilderHelper.byteValue(bs, offset, fieldsArray[FIELD_INDEX_STATUS]);
        state.id = StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_ID], charset);
        state.ipAddr = StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_IP_ADDR], charset);
        state.srcIpAddr = StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_SRC_IP_ADDR], charset);
        state.domainName = StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_DOMAIN_NAME], charset);
        state.version = StateBuilderHelper.stringValue(bs, offset, fieldsArray[FIELD_INDEX_VERSION], charset);
        state.totalMB = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_MB]);
        state.freeMB = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_FREE_MB]);
        state.uploadPriority = StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_UPLOAD_PRIORITY]);
        state.joinTime = StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_JOIN_TIME]);
        state.upTime = StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_UP_TIME]);
        state.storePathCount = StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORE_PATH_COUNT]);
        state.subdirCountPerPath = StateBuilderHelper.intValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUBDIR_COUNT_PER_PATH]);
        state.storagePort = StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORAGE_PORT]);
        state.storageHttpPort = StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_STORAGE_HTTP_PORT]);
        state.currentWritePath = StateBuilderHelper.intValue(bs, offset, fieldsArray[FIELD_INDEX_CURRENT_WRITE_PATH]);
        state.totalUploadCount = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_COUNT]);
        state.successUploadCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_COUNT]);
        state.totalAppendCount = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_APPEND_COUNT]);
        state.successAppendCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_APPEND_COUNT]);
        state.totalModifyCount = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_MODIFY_COUNT]);
        state.successModifyCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_COUNT]);
        state.totalTruncateCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_TRUNCATE_COUNT]);
        state.successTruncateCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_TRUNCATE_COUNT]);
        state.totalSetMetaCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_SET_META_COUNT]);
        state.successSetMetaCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_SET_META_COUNT]);
        state.totalDeleteCount = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_DELETE_COUNT]);
        state.successDeleteCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_DELETE_COUNT]);
        state.totalDownloadCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_COUNT]);
        state.successDownloadCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_COUNT]);
        state.totalGetMetaCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_GET_META_COUNT]);
        state.successGetMetaCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_GET_META_COUNT]);
        state.totalCreateLinkCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_CREATE_LINK_COUNT]);
        state.successCreateLinkCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_CREATE_LINK_COUNT]);
        state.totalDeleteLinkCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_DELETE_LINK_COUNT]);
        state.successDeleteLinkCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_DELETE_LINK_COUNT]);
        state.totalUploadBytes = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_UPLOAD_BYTES]);
        state.successUploadBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_UPLOAD_BYTES]);
        state.totalAppendBytes = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_APPEND_BYTES]);
        state.successAppendBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_APPEND_BYTES]);
        state.totalModifyBytes = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_MODIFY_BYTES]);
        state.successModifyBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_MODIFY_BYTES]);
        state.totalDownloadloadBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_DOWNLOAD_BYTES]);
        state.successDownloadloadBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_DOWNLOAD_BYTES]);
        state.totalSyncInBytes = StateBuilderHelper.longValue(bs, offset, fieldsArray[FIELD_INDEX_TOTAL_SYNC_IN_BYTES]);
        state.successSyncInBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_SYNC_IN_BYTES]);
        state.totalSyncOutBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_SYNC_OUT_BYTES]);
        state.successSyncOutBytes = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_SYNC_OUT_BYTES]);
        state.totalFileOpenCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_FILE_OPEN_COUNT]);
        state.successFileOpenCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_FILE_OPEN_COUNT]);
        state.totalFileReadCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_FILE_READ_COUNT]);
        state.successFileReadCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_FILE_READ_COUNT]);
        state.totalFileWriteCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_TOTAL_FILE_WRITE_COUNT]);
        state.successFileWriteCount = StateBuilderHelper.longValue(bs, offset,
                fieldsArray[FIELD_INDEX_SUCCESS_FILE_WRITE_COUNT]);
        state.lastSourceUpdate = StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_LAST_SOURCE_UPDATE]);
        state.lastSyncUpdate = StateBuilderHelper.dateValue(bs, offset, fieldsArray[FIELD_INDEX_LAST_SYNC_UPDATE]);
        state.lastSyncedTimestamp = StateBuilderHelper.dateValue(bs, offset,
                fieldsArray[FIELD_INDEX_LAST_SYNCED_TIMESTAMP]);
        state.lastHeartBeatTime = StateBuilderHelper.dateValue(bs, offset,
                fieldsArray[FIELD_INDEX_LAST_HEART_BEAT_TIME]);
        state.isTrunkServer = StateBuilderHelper.booleanValue(bs, offset, fieldsArray[FIELD_INDEX_IF_TRUNK_FILE]);

        return state;
    }

    public static int getFieldsTotalSize() {
        return fieldsTotalSize;
    }

}
