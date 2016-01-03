package com.github.tobato.fastdfs.domain;

import com.github.tobato.fastdfs.proto.OtherConstants;
import com.github.tobato.fastdfs.proto.mapper.FdfsColumn;

/**
 * 文件的基础信息
 * 
 * @author yuqih
 * 
 */
public class FileInfo {
    /** 长度 */
    @FdfsColumn(index = 0)
    private long size;
    /** 创建时间 */
    @FdfsColumn(index = 1)
    private int createTime;
    /** 校验码 */
    @FdfsColumn(index = 2)
    private int crc32;
    /** ip地址 */
    @FdfsColumn(index = 3, max = OtherConstants.FDFS_IPADDR_SIZE)
    private String sourceIpAddr;

    /**
     * 
     */
    public FileInfo() {
        super();
    }

    /**
     * @param sourceIpAddr
     * @param size
     * @param createTime
     * @param crc32
     */
    public FileInfo(String sourceIpAddr, long size, int createTime, int crc32) {
        super();
        this.sourceIpAddr = sourceIpAddr;
        this.size = size;
        this.createTime = createTime;
        this.crc32 = crc32;
    }

    /**
     * @return the sourceIpAddr
     */
    public String getSourceIpAddr() {
        return sourceIpAddr;
    }

    /**
     * @param sourceIpAddr
     *            the sourceIpAddr to set
     */
    public void setSourceIpAddr(String sourceIpAddr) {
        this.sourceIpAddr = sourceIpAddr;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * @return the createTime
     */
    public int getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the crc32
     */
    public int getCrc32() {
        return crc32;
    }

    /**
     * @param crc32
     *            the crc32 to set
     */
    public void setCrc32(int crc32) {
        this.crc32 = crc32;
    }

    @Override
    public String toString() {
        return "FileInfo [size=" + size + ", createTime=" + createTime + ", crc32=" + crc32 + ", sourceIpAddr="
                + sourceIpAddr + "]";
    }

}
