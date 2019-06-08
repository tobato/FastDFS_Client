package com.github.tobato.fastdfs.domain.fdfs;

import com.github.tobato.fastdfs.domain.proto.OtherConstants;
import com.github.tobato.fastdfs.domain.proto.mapper.FdfsColumn;

import java.text.SimpleDateFormat;

/**
 * 文件的基础信息
 *
 * @author yuqih
 */
public class FileInfo {
    /**
     * 长度
     */
    @FdfsColumn(index = 0)
    private long fileSize;
    /**
     * 创建时间
     */
    @FdfsColumn(index = 1)
    private long createTime;
    /**
     * 校验码
     */
    @FdfsColumn(index = 2)
    private int crc32;
    /**
     * ip地址
     */
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
     * @param fileSize
     * @param createTime
     * @param crc32
     */
    public FileInfo(String sourceIpAddr, long fileSize, long createTime, int crc32) {
        super();
        this.sourceIpAddr = sourceIpAddr;
        this.fileSize = fileSize;
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
     * @param sourceIpAddr the sourceIpAddr to set
     */
    public void setSourceIpAddr(String sourceIpAddr) {
        this.sourceIpAddr = sourceIpAddr;
    }

    /**
     * @return the size
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the size to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the createTime
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the crc32
     */
    public int getCrc32() {
        return crc32;
    }

    /**
     * @param crc32 the crc32 to set
     */
    public void setCrc32(int crc32) {
        this.crc32 = crc32;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "source_ip_addr = " + this.sourceIpAddr + ", " + "file_size = " + this.fileSize + ", "
                + "create_timestamp = " + df.format(this.createTime * 1000) + ", " + "crc32 = " + this.crc32;
    }

}
