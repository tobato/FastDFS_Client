package com.github.tobato.fastdfs.cmd.tracker;

import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.FdfsResponse;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerDeleteStorageRequest;

/**
 * 移除存储服务器命令
 * 
 * @author wuyf
 *
 */
public class TrackerDeleteStorageCommand extends AbstractFdfsCommand<Void> {

    public TrackerDeleteStorageCommand(String groupName, String storageIpAddr) {
        super.request = new TrackerDeleteStorageRequest(groupName, storageIpAddr);
        super.response = new FdfsResponse<Void>() {
            // default response
        };
    }

}
