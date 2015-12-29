package com.github.tobato.fastdfs;

/**
 * 文件的基础信息
 * 
 * @author yuqih
 * 
 */
public class FileInfo {

    private String sourceIpAddr;
    private long size;
    private int createTime;
    private int crc32;

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

}
