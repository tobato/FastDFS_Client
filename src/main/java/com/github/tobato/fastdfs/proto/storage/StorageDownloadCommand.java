package com.github.tobato.fastdfs.proto.storage;

import com.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import com.github.tobato.fastdfs.proto.storage.internal.StorageDownloadRequest;
import com.github.tobato.fastdfs.proto.storage.internal.StorageDownloadResponse;

/**
 * 文件下载命令
 * 
 * @author tobato
 * @param <T>
 *
 */
public class StorageDownloadCommand<T> extends AbstractFdfsCommand<T> {

    /**
     * 下载文件
     * 
     * @param groupName
     * @param path
     * @param fileOffset
     * @param fileSize
     */
    public StorageDownloadCommand(String groupName, String path, long fileOffset, long fileSize,
            DownloadCallback<T> callback) {
        super();
        this.request = new StorageDownloadRequest(groupName, path, fileOffset, fileSize);
        // 输出响应
        this.response = new StorageDownloadResponse<T>(callback);
    }

    /**
     * 下载文件
     * 
     * @param groupName
     * @param path
     */
    public StorageDownloadCommand(String groupName, String path, DownloadCallback<T> callback) {
        super();
        this.request = new StorageDownloadRequest(groupName, path, 0, 0);
        // 输出响应
        this.response = new StorageDownloadResponse<T>(callback);
    }
}
