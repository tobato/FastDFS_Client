package com.github.tobato.fastdfs.proto.storage;

import java.io.InputStream;

import com.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import com.github.tobato.fastdfs.proto.FdfsResponse;
import com.github.tobato.fastdfs.proto.storage.internal.StorageAppendFileRequest;

/**
 * 添加文件命令
 * 
 * @author tobato
 *
 */
public class StorageAppendFileCommand extends AbstractFdfsCommand<Void> {

    public StorageAppendFileCommand(InputStream inputStream, long fileSize, String path) {
        this.request = new StorageAppendFileRequest(inputStream, fileSize, path);
        // 输出响应
        this.response = new FdfsResponse<Void>() {
            // default response
        };
    }

}
