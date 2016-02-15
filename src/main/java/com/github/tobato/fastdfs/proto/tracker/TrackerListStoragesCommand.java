package com.github.tobato.fastdfs.proto.tracker;

import java.util.List;

import com.github.tobato.fastdfs.domain.StorageState;
import com.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import com.github.tobato.fastdfs.proto.tracker.internal.TrackerListStoragesRequest;
import com.github.tobato.fastdfs.proto.tracker.internal.TrackerListStoragesResponse;

/**
 * 列出组命令
 * 
 * @author tobato
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
