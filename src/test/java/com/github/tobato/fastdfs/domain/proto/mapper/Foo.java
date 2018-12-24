package com.github.tobato.fastdfs.domain.proto.mapper;

import com.github.tobato.fastdfs.domain.proto.OtherConstants;

/**
 * 测试类
 * 
 * @author tobato
 *
 */
public class Foo {

    @FdfsColumn(index = 0, max = OtherConstants.FDFS_GROUP_NAME_MAX_LEN)
    String groupName;
    @FdfsColumn(index = 1, max = OtherConstants.FDFS_IPADDR_SIZE - 1)
    String ip;
    @FdfsColumn(index = 2)
    long port;

    public long getPort() {
        return port;
    }

    public void setPort(long port) {
        this.port = port;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
