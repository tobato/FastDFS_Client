package com.github.tobato.fastdfs.domain.proto.storage;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.proto.AbstractFdfsCommand;
import com.github.tobato.fastdfs.domain.proto.FdfsResponse;
import com.github.tobato.fastdfs.domain.proto.storage.internal.StorageQueryFileInfoRequest;

/**
 * 文件查询命令
 *
 * @author tobato
 */
public class StorageQueryFileInfoCommand extends AbstractFdfsCommand<FileInfo> {

    /**
     * 文件查询命令
     *
     * @param groupName
     * @param path
     */
    public StorageQueryFileInfoCommand(String groupName, String path) {
        super();
        this.request = new StorageQueryFileInfoRequest(groupName, path);
        // 输出响应
        this.response = new FdfsResponse<FileInfo>() {
            // default response
        };
    }

}
