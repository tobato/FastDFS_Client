package com.github.tobato.fastdfs.proto.storage.internal;

import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.FdfsRequest;
import com.github.tobato.fastdfs.proto.OtherConstants;
import com.github.tobato.fastdfs.proto.ProtoHead;
import com.github.tobato.fastdfs.proto.mapper.DynamicFieldType;
import com.github.tobato.fastdfs.proto.mapper.FdfsColumn;

/**
 * 文件下载请求
 * 
 * @author tobato
 *
 */
public class StorageDownloadRequest extends FdfsRequest {

    /** 开始位置 */
    @FdfsColumn(index = 0)
    private long fileOffset;
    /** 读取文件长度 */
    @FdfsColumn(index = 1)
    private long fileSize;
    /** 组名 */
    @FdfsColumn(index = 2, max = OtherConstants.FDFS_GROUP_NAME_MAX_LEN)
    private String groupName;
    /** 文件路径 */
    @FdfsColumn(index = 3, dynamicField = DynamicFieldType.allRestByte)
    private String path;

    /**
     * 文件下载请求
     * 
     * @param groupName
     * @param path
     * @param fileOffset
     * @param fileSize
     */
    public StorageDownloadRequest(String groupName, String path, long fileOffset, long fileSize) {
        super();
        this.groupName = groupName;
        this.fileSize = fileSize;
        this.path = path;
        this.fileOffset = fileOffset;
        head = new ProtoHead(CmdConstants.STORAGE_PROTO_CMD_DOWNLOAD_FILE);

    }

    public long getFileOffset() {
        return fileOffset;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getPath() {
        return path;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }

}
