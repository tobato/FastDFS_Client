package com.github.tobato.fastdfs.service;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.domain.StorageNode;
import com.github.tobato.fastdfs.domain.StorageNodeInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.StorageAppendFileCommand;
import com.github.tobato.fastdfs.proto.storage.StorageModifyCommand;
import com.github.tobato.fastdfs.proto.storage.StorageTruncateCommand;
import com.github.tobato.fastdfs.proto.storage.StorageUploadFileCommand;

/**
 * 存储服务客户端接口实现
 * 
 * @author tobato
 *
 */
@Component
public class DefaultAppendFileStorageClient extends DefaultGenerateStorageClient implements AppendFileStorageClient {

    /**
     * 上传支持断点续传的文件
     */
    @Override
    public StorePath uploadAppenderFile(String groupName, InputStream inputStream, long fileSize, String fileExtName) {
        StorageNode client = trackerClient.getStoreStorage(groupName);
        StorageUploadFileCommand command = new StorageUploadFileCommand(client.getStoreIndex(), inputStream,
                fileExtName, fileSize, true);
        return connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 继续上载文件
     */
    @Override
    public void appendFile(String groupName, String path, InputStream inputStream, long fileSize) {
        StorageNodeInfo client = trackerClient.getUpdateStorage(groupName, path);
        StorageAppendFileCommand command = new StorageAppendFileCommand(inputStream, fileSize, path);
        connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 修改文件
     */
    @Override
    public void modifyFile(String groupName, String path, InputStream inputStream, long fileSize, long fileOffset) {
        StorageNodeInfo client = trackerClient.getUpdateStorage(groupName, path);
        StorageModifyCommand command = new StorageModifyCommand(path, inputStream, fileSize, fileOffset);
        connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);

    }

    /**
     * 清除文件
     */
    @Override
    public void truncateFile(String groupName, String path, long truncatedFileSize) {
        StorageNodeInfo client = trackerClient.getUpdateStorage(groupName, path);
        StorageTruncateCommand command = new StorageTruncateCommand(path, truncatedFileSize);
        connectionManager.executeFdfsCmd(client.getInetSocketAddress(), command);
    }

    /**
     * 清除文件
     */
    @Override
    public void truncateFile(String groupName, String path) {
        long truncatedFileSize = 0;
        truncateFile(groupName, path, truncatedFileSize);
    }

}
