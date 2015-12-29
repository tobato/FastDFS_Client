package com.github.tobato.fastdfs.cmd.tracker;

import com.github.tobato.fastdfs.StorageClient;
import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerGetStoreStorageRequest;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerGetStoreStorageResponse;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerGetStoreStorageWithGroupRequest;

/**
 * 获取存储节点命令
 * 
 * @author wuyf
 *
 */
public class TrackerGetStoreStorageCommand extends AbstractFdfsCommand<StorageClient> {

    public TrackerGetStoreStorageCommand(String groupName) {
        super.request = new TrackerGetStoreStorageWithGroupRequest(groupName);
        super.response = new TrackerGetStoreStorageResponse();
    }

    public TrackerGetStoreStorageCommand() {
        super.request = new TrackerGetStoreStorageRequest();
        super.response = new TrackerGetStoreStorageResponse();
    }

}
