package com.github.tobato.fastdfs.proto.storage;

import com.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import com.github.tobato.fastdfs.proto.storage.internal.StorageDownloadRequest;
import com.github.tobato.fastdfs.proto.storage.internal.StorageDownloadResponse;
import com.github.tobato.fastdfs.socket.FdfsInputStream;

/**
 * 文件下载命令
 * 
 * @author wuyf
 *
 */
public class StorageDownloadCommand extends AbstractFdfsCommand<FdfsInputStream> {

    /**
     * 下载文件
     * 
     * @param groupName
     * @param path
     * @param fileOffset
     * @param fileSize
     */
    public StorageDownloadCommand(String groupName, String path, long fileOffset, long fileSize) {
        super();
        this.request = new StorageDownloadRequest(groupName, path, fileOffset, fileSize);
        // 输出响应
        this.response = new StorageDownloadResponse();
    }

    /**
     * 下载文件
     * 
     * @param groupName
     * @param path
     */
    public StorageDownloadCommand(String groupName, String path) {
        super();
        this.request = new StorageDownloadRequest(groupName, path, 0, 0);
        // 输出响应
        this.response = new StorageDownloadResponse();
    }
}
