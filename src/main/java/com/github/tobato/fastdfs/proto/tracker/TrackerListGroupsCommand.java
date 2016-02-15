package com.github.tobato.fastdfs.proto.tracker;

import java.util.List;

import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.proto.AbstractFdfsCommand;
import com.github.tobato.fastdfs.proto.tracker.internal.TrackerListGroupsRequest;
import com.github.tobato.fastdfs.proto.tracker.internal.TrackerListGroupsResponse;

/**
 * 列出组命令
 * 
 * @author tobato
 *
 */
public class TrackerListGroupsCommand extends AbstractFdfsCommand<List<GroupState>> {

    public TrackerListGroupsCommand() {
        super.request = new TrackerListGroupsRequest();
        super.response = new TrackerListGroupsResponse();
    }

}
