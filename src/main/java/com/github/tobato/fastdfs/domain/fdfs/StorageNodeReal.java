package com.github.tobato.fastdfs.domain.fdfs;

import java.io.Serializable;

/**
 *  节点的 真实IP，端口信息
 * @author Rong.Jia
 * @date 2021/01/24 12:56
 */
public class StorageNodeReal implements Serializable {

    private static final long serialVersionUID = 6856298300479910590L;

    /**
     * IP
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 组名
     */
    private String groupName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "StorageNodeReal{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
