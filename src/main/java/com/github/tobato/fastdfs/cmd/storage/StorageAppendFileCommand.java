package com.github.tobato.fastdfs.cmd.storage;

import java.io.InputStream;

import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.FdfsResponse;
import com.github.tobato.fastdfs.cmd.storage.internal.StorageAppendFileRequest;

/**
 * 添加文件命令
 * 
 * @author wuyf
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
