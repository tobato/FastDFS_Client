package com.github.tobato.fastdfs.cmd.storage.internal;

import java.util.List;

import com.github.tobato.fastdfs.NameValuePair;
import com.github.tobato.fastdfs.cmd.FdfsRequest;
import com.github.tobato.fastdfs.cmd.ProtoHead;
import com.github.tobato.fastdfs.cmd.mark.DynamicFieldType;
import com.github.tobato.fastdfs.cmd.mark.FdfsColumn;
import com.github.tobato.fastdfs.proto.CmdConstants;

/**
 * 设置文件标签
 * 
 * @author wuyf
 *
 */
public class StorageSetMetadataRequest extends FdfsRequest {

    /** 组名 */
    @FdfsColumn(index = 0)
    private String groupName;
    /** 元数据 */
    @FdfsColumn
    private List<NameValuePair> metaList;
    /** 操作标记 */
    @FdfsColumn(index = 1)
    private byte opFlag;
    /** 文件路径 */
    @FdfsColumn(index = 2, dynamicField = DynamicFieldType.allRestByte)
    private String path;

    /**
     * 设置文件元数据
     * 
     * @param groupName
     * @param path
     * @param metaList
     * @param opFlag
     */
    public StorageSetMetadataRequest(String groupName, String path, List<NameValuePair> metaList, byte opFlag) {
        super();
        this.groupName = groupName;
        this.path = path;
        this.metaList = metaList;
        this.opFlag = opFlag;
        head = new ProtoHead(CmdConstants.STORAGE_PROTO_CMD_SET_METADATA);
    }

    public String getGroupName() {
        return groupName;
    }

    public List<NameValuePair> getMetaList() {
        return metaList;
    }

    public byte getOpFlag() {
        return opFlag;
    }

    public String getPath() {
        return path;
    }

}
