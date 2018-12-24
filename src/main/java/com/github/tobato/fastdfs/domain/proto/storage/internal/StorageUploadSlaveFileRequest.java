package com.github.tobato.fastdfs.domain.proto.storage.internal;

import java.io.InputStream;

import com.github.tobato.fastdfs.domain.proto.CmdConstants;
import com.github.tobato.fastdfs.domain.proto.FdfsRequest;
import com.github.tobato.fastdfs.domain.proto.OtherConstants;
import com.github.tobato.fastdfs.domain.proto.ProtoHead;
import com.github.tobato.fastdfs.domain.proto.mapper.DynamicFieldType;
import com.github.tobato.fastdfs.domain.proto.mapper.FdfsColumn;

/**
 * 从文件上传命令
 *
 * @author tobato
 */
public class StorageUploadSlaveFileRequest extends FdfsRequest {

    /**
     * 主文件名长度
     */
    @FdfsColumn(index = 0)
    private long masterFileNameSize;
    /**
     * 发送文件长度
     */
    @FdfsColumn(index = 1)
    private long fileSize;
    /**
     * 名称前缀
     */
    @FdfsColumn(index = 2, max = OtherConstants.FDFS_FILE_PREFIX_MAX_LEN)
    private final String prefixName;
    /**
     * 文件扩展名
     */
    @FdfsColumn(index = 3, max = OtherConstants.FDFS_FILE_EXT_NAME_MAX_LEN)
    private String fileExtName;
    /**
     * 主文件名
     */
    @FdfsColumn(index = 4, dynamicField = DynamicFieldType.allRestByte)
    private final String masterFilename;

    /**
     * 构造函数
     *
     * @param storeIndex
     * @param inputStream
     * @param masterFilename
     * @param fileExtName
     * @param prefixName
     * @param fileSize
     * @param isAppenderFile
     */
    public StorageUploadSlaveFileRequest(InputStream inputStream, long fileSize, String masterFilename,
                                         String prefixName, String fileExtName) {
        super();
        this.inputFile = inputStream;
        this.fileSize = fileSize;
        this.masterFileNameSize = masterFilename.length();
        this.masterFilename = masterFilename;
        this.fileExtName = fileExtName;
        this.prefixName = prefixName;
        head = new ProtoHead(CmdConstants.STORAGE_PROTO_CMD_UPLOAD_SLAVE_FILE);

    }

    public long getMasterFileNameSize() {
        return masterFileNameSize;
    }

    public void setMasterFileNameSize(long masterFileNameSize) {
        this.masterFileNameSize = masterFileNameSize;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public String getMasterFilename() {
        return masterFilename;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtName() {
        return fileExtName;
    }

    public void setFileExtName(String fileExtName) {
        this.fileExtName = fileExtName;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }

}
