package com.github.tobato.fastdfs.cmd.storage;

import java.io.InputStream;

import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.FdfsResponse;
import com.github.tobato.fastdfs.cmd.storage.internal.StorageAppendFileRequest;

/**
 * 设置文件标签
 * 
 * @author wuyf
 *
 */
public class StorageSetMetadataCommand extends AbstractFdfsCommand<Void> {

    public StorageSetMetadataCommand(InputStream inputStream, long fileSize, String path) {
        this.request = new StorageAppendFileRequest(inputStream, fileSize, path);
        // 输出响应
        this.response = new FdfsResponse<Void>() {
            // default response
        };
    }

}
