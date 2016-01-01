package com.github.tobato.fastdfs.cmd.tracker;

import java.util.List;

import com.github.tobato.fastdfs.StorageState;
import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerListStoragesRequest;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerListStoragesResponse;

/**
 * 列出组命令
 * 
 * @author wuyf
 *
 */
public class TrackerListStoragesCommand extends AbstractFdfsCommand<List<StorageState>> {

    public TrackerListStoragesCommand(String groupName, String storageIpAddr) {
        super.request = new TrackerListStoragesRequest(groupName, storageIpAddr);
        super.response = new TrackerListStoragesResponse();
    }

    public TrackerListStoragesCommand(String groupName) {
        super.request = new TrackerListStoragesRequest(groupName);
        super.response = new TrackerListStoragesResponse();
    }

}
