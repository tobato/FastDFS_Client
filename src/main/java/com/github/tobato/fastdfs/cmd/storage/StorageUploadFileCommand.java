package com.github.tobato.fastdfs.cmd.storage;

import java.io.InputStream;

import com.github.tobato.fastdfs.StorePath;
import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.FdfsResponse;
import com.github.tobato.fastdfs.cmd.storage.internal.StorageUploadFileRequest;

/**
 * 文件上传命令
 * 
 * @author wuyf
 *
 */
public class StorageUploadFileCommand extends AbstractFdfsCommand<StorePath> {

    /**
     * 文件上传命令
     * 
     * @param storeIndex 存储节点
     * @param inputStream 输入流
     * @param fileExtName 文件扩展名
     * @param size 文件大小
     * @param isAppenderFile 是否添加模式
     */
    public StorageUploadFileCommand(byte storeIndex, InputStream inputStream, String fileExtName, long fileSize,
            boolean isAppenderFile) {
        super();
        this.request = new StorageUploadFileRequest(storeIndex, inputStream, fileExtName, fileSize, isAppenderFile);
        // 输出响应
        this.response = new FdfsResponse<StorePath>() {
            // default response
        };
    }

}
