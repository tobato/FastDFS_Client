package com.github.tobato.fastdfs.cmd.tracker.internal;

import com.github.tobato.fastdfs.cmd.FdfsRequest;
import com.github.tobato.fastdfs.cmd.ProtoHead;
import com.github.tobato.fastdfs.proto.CmdConstants;

/**
 * 获取存储节点请求
 * 
 * @author wuyf
 *
 */
public class TrackerGetStoreStorageRequest extends FdfsRequest {

    private static final byte withoutGroupCmd = CmdConstants.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITHOUT_GROUP_ONE;

    /**
     * 获取存储节点
     * 
     * @param groupName
     */
    public TrackerGetStoreStorageRequest() {
        super();
        this.head = new ProtoHead(withoutGroupCmd);
    }

}
