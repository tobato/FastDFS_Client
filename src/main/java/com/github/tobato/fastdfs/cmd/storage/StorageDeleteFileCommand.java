package com.github.tobato.fastdfs.cmd.storage;

import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.storage.internal.StorageDeleteFileRequest;
import com.github.tobato.fastdfs.cmd.storage.internal.StorageDeleteFileResponse;

/**
 * 文件删除命令
 * 
 * @author wuyf
 *
 */
public class StorageDeleteFileCommand extends AbstractFdfsCommand<Void> {

    /**
     * 文件上传命令
     * 
     * @param storeIndex 存储节点
     * @param inputStream 输入流
     * @param fileExtName 文件扩展名
     * @param size 文件大小
     * @param isAppenderFile 是否添加模式
     */
    public StorageDeleteFileCommand(String groupName, String path) {
        super();
        this.request = new StorageDeleteFileRequest(groupName, path);
        this.response = new StorageDeleteFileResponse();
    }

}
