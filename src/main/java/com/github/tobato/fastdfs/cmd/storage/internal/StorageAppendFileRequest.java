package com.github.tobato.fastdfs.cmd.storage.internal;

import java.io.InputStream;

import com.github.tobato.fastdfs.cmd.FdfsRequest;
import com.github.tobato.fastdfs.cmd.ProtoHead;
import com.github.tobato.fastdfs.cmd.mark.DynamicFieldType;
import com.github.tobato.fastdfs.cmd.mark.FdfsColumn;
import com.github.tobato.fastdfs.proto.CmdConstants;

/**
 * 文件上传命令
 * 
 * @author wuyf
 *
 */
public class StorageAppendFileRequest extends FdfsRequest {

    /** 文件路径长度 */
    @FdfsColumn(index = 0)
    private long pathSize;
    /** 发送文件长度 */
    @FdfsColumn(index = 1)
    private long fileSize;
    /** 文件路径 */
    @FdfsColumn(index = 2, dynamicField = DynamicFieldType.allRestByte)
    private String path;

    /**
     * 构造函数
     * 
     * @param inputStream
     * @param fileExtName
     * @param fileSize
     * @param storeIndex
     * @param isAppenderFile
     */
    public StorageAppendFileRequest(InputStream inputStream, long fileSize, String path) {
        super();
        this.inputFile = inputStream;
        this.fileSize = fileSize;
        this.path = path;
        this.pathSize = path.length();
        head = new ProtoHead(CmdConstants.STORAGE_PROTO_CMD_APPEND_FILE);
    }

    public long getPathSize() {
        return pathSize;
    }

    public void setPathSize(long pathSize) {
        this.pathSize = pathSize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "StorageAppendFileRequest [pathSize=" + pathSize + ", fileSize=" + fileSize + ", path=" + path + "]";
    }

}
