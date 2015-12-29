package com.github.tobato.fastdfs.cmd.tracker;

import java.util.List;

import com.github.tobato.fastdfs.GroupState;
import com.github.tobato.fastdfs.cmd.AbstractFdfsCommand;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerListGroupsRequest;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerListGroupsResponse;

/**
 * 列出组命令
 * 
 * @author wuyf
 *
 */
public class TrackerListGroupsCommand extends AbstractFdfsCommand<List<GroupState>> {

    public TrackerListGroupsCommand() {
        super.request = new TrackerListGroupsRequest();
        super.response = new TrackerListGroupsResponse();
    }

}
