package com.github.tobato.fastdfs.domain.fdfs;

import com.github.tobato.fastdfs.domain.proto.OtherConstants;
import com.github.tobato.fastdfs.domain.proto.mapper.FdfsColumn;

import java.net.InetSocketAddress;

/**
 * 向tracker请求上传、下载文件或其他文件请求时，tracker返回的文件storage节点的信息
 *
 * @author yuqih
 */
public class StorageNodeInfo {

    @FdfsColumn(index = 0, max = OtherConstants.FDFS_GROUP_NAME_MAX_LEN)
    private String groupName;
    @FdfsColumn(index = 1, max = OtherConstants.FDFS_IPADDR_SIZE - 1)
    private String ip;
    @FdfsColumn(index = 2)
    private int port;

    /**
     * 存储节点
     *
     * @param ip
     * @param port
     */
    public StorageNodeInfo(String ip, int port) {
        super();
        this.ip = ip;
        this.port = port;
    }

    public StorageNodeInfo() {
        super();
    }

    /**
     * @return the inetSocketAddress
     */
    public InetSocketAddress getInetSocketAddress() {
        return new InetSocketAddress(ip, port);
    }

    public String getGroupName() {
        return groupName;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "StorageClientInfo [groupName=" + groupName + ", ip=" + ip + ", port=" + port + "]";
    }

}
