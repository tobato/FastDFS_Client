package com.github.tobato.fastdfs.domain.fdfs;

import java.util.Date;

import com.github.tobato.fastdfs.domain.proto.OtherConstants;
import com.github.tobato.fastdfs.domain.proto.mapper.FdfsColumn;

/**
 * fastdfs中storage节点的状态信息
 *
 * @author yuqih
 */
public class StorageState {

    /**
     * 状态代码
     */
    @FdfsColumn(index = 0)
    private byte status;
    /**
     * id
     */
    @FdfsColumn(index = 1, max = OtherConstants.FDFS_STORAGE_ID_MAX_SIZE)
    private String id;
    /**
     * ip地址
     */
    @FdfsColumn(index = 2, max = OtherConstants.FDFS_IPADDR_SIZE)
    private String ipAddr;
    /**
     * domain
     */
    @FdfsColumn(index = 3, max = OtherConstants.FDFS_DOMAIN_NAME_MAX_SIZE)
    private String domainName; // http domain name
    /**
     * 源ip地址
     */
    @FdfsColumn(index = 4, max = OtherConstants.FDFS_IPADDR_SIZE)
    private String srcIpAddr;
    /**
     * version
     */
    @FdfsColumn(index = 5, max = OtherConstants.FDFS_VERSION_SIZE)
    private String version;
    /**
     * 存储加入时间
     */
    @FdfsColumn(index = 6)
    private Date joinTime; // storage join timestamp (create timestamp)
    /**
     * 存储更新时间
     */
    @FdfsColumn(index = 7)
    private Date upTime; // storage service started timestamp
    /**
     * 存储总容量
     */
    @FdfsColumn(index = 8)
    private long totalMB; // total disk storage in MB
    /**
     * 空闲存储
     */
    @FdfsColumn(index = 9)
    private long freeMB; // free disk storage in MB
    /**
     * 文件上传权重
     */
    @FdfsColumn(index = 10)
    private int uploadPriority; // upload priority
    /**
     * 存储路径数
     */
    @FdfsColumn(index = 11)
    private int storePathCount; // store base path count of each storage
    // server
    /**
     * 存储路径子目录数
     */
    @FdfsColumn(index = 12)
    private int subdirCountPerPath;
    /**
     * 当前写路径
     */
    @FdfsColumn(index = 13)
    private int currentWritePath; // current write path index
    /**
     * 存储端口
     */
    @FdfsColumn(index = 14)
    private int storagePort;
    /**
     * 存储http端口
     */
    @FdfsColumn(index = 15)
    private int storageHttpPort; // storage http server port
    @FdfsColumn(index = 16, max = OtherConstants.FDFS_PROTO_CONNECTION_LEN)
    private int connectionAllocCount;
    @FdfsColumn(index = 17, max = OtherConstants.FDFS_PROTO_CONNECTION_LEN)
    private int connectionCurrentCount;
    @FdfsColumn(index = 18, max = OtherConstants.FDFS_PROTO_CONNECTION_LEN)
    private int connectionMaxCount;
    /**
     * 总上传文件数
     */
    @FdfsColumn(index = 19)
    private long totalUploadCount;
    /**
     * 成功上传文件数
     */
    @FdfsColumn(index = 20)
    private long successUploadCount;
    /**
     * 合并存储文件数
     */
    @FdfsColumn(index = 21)
    private long totalAppendCount;
    /**
     * 成功合并文件数
     */
    @FdfsColumn(index = 22)
    private long successAppendCount;
    /**
     * 文件修改数
     */
    @FdfsColumn(index = 23)
    private long totalModifyCount;
    /**
     * 文件成功修改数
     */
    @FdfsColumn(index = 24)
    private long successModifyCount;
    /**
     * 总清除数
     */
    @FdfsColumn(index = 25)
    private long totalTruncateCount;
    /**
     * 成功清除数
     */
    @FdfsColumn(index = 26)
    private long successTruncateCount;
    /**
     * 总设置标签数
     */
    @FdfsColumn(index = 27)
    private long totalSetMetaCount;
    /**
     * 成功设置标签数
     */
    @FdfsColumn(index = 28)
    private long successSetMetaCount;
    /**
     * 总删除文件数
     */
    @FdfsColumn(index = 29)
    private long totalDeleteCount;
    /**
     * 成功删除文件数
     */
    @FdfsColumn(index = 30)
    private long successDeleteCount;
    /**
     * 总下载量
     */
    @FdfsColumn(index = 31)
    private long totalDownloadCount;
    /**
     * 成功下载量
     */
    @FdfsColumn(index = 32)
    private long successDownloadCount;
    /**
     * 总获取标签数
     */
    @FdfsColumn(index = 33)
    private long totalGetMetaCount;
    /**
     * 成功获取标签数
     */
    @FdfsColumn(index = 34)
    private long successGetMetaCount;
    /**
     * 总创建链接数
     */
    @FdfsColumn(index = 35)
    private long totalCreateLinkCount;
    /**
     * 成功创建链接数
     */
    @FdfsColumn(index = 36)
    private long successCreateLinkCount;
    /**
     * 总删除链接数
     */
    @FdfsColumn(index = 37)
    private long totalDeleteLinkCount;
    /**
     * 成功删除链接数
     */
    @FdfsColumn(index = 38)
    private long successDeleteLinkCount;
    /**
     * 总上传数据量
     */
    @FdfsColumn(index = 39)
    private long totalUploadBytes;
    /**
     * 成功上传数据量
     */
    @FdfsColumn(index = 40)
    private long successUploadBytes;
    /**
     * 合并数据量
     */
    @FdfsColumn(index = 41)
    private long totalAppendBytes;
    /**
     * 成功合并数据量
     */
    @FdfsColumn(index = 42)
    private long successAppendBytes;
    /**
     * 修改数据量
     */
    @FdfsColumn(index = 43)
    private long totalModifyBytes;
    /**
     * 成功修改数据量
     */
    @FdfsColumn(index = 44)
    private long successModifyBytes;
    /**
     * 下载数据量
     */
    @FdfsColumn(index = 45)
    private long totalDownloadloadBytes;
    /**
     * 成功下载数据量
     */
    @FdfsColumn(index = 46)
    private long successDownloadloadBytes;
    /**
     * 同步数据量
     */
    @FdfsColumn(index = 47)
    private long totalSyncInBytes;
    /**
     * 成功同步数据量
     */
    @FdfsColumn(index = 48)
    private long successSyncInBytes;
    /**
     * 同步输出数据量
     */
    @FdfsColumn(index = 49)
    private long totalSyncOutBytes;
    /**
     * 成功同步输出数据量
     */
    @FdfsColumn(index = 50)
    private long successSyncOutBytes;
    /**
     * 打开文件数量
     */
    @FdfsColumn(index = 51)
    private long totalFileOpenCount;
    /**
     * 成功打开文件数量
     */
    @FdfsColumn(index = 52)
    private long successFileOpenCount;
    /**
     * 文件读取数量
     */
    @FdfsColumn(index = 53)
    private long totalFileReadCount;
    /**
     * 文件成功读取数量
     */
    @FdfsColumn(index = 54)
    private long successFileReadCount;
    /**
     * 文件写数量
     */
    @FdfsColumn(index = 56)
    private long totalFileWriteCount;
    /**
     * 文件成功写数量
     */
    @FdfsColumn(index = 57)
    private long successFileWriteCount;
    /**
     * 最后上传时间
     */
    @FdfsColumn(index = 58)
    private Date lastSourceUpdate;
    /**
     * 最后同步时间
     */
    @FdfsColumn(index = 59)
    private Date lastSyncUpdate;
    /**
     * 最后同步时间戳
     */
    @FdfsColumn(index = 60)
    private Date lastSyncedTimestamp;
    /**
     * 最后心跳时间
     */
    @FdfsColumn(index = 61)
    private Date lastHeartBeatTime;
    /**
     * 是否trunk服务器
     */
    @FdfsColumn(index = 62)
    private boolean isTrunkServer;

    /**
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(byte status) {
        this.status = status;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ipAddr
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * @param ipAddr the ipAddr to set
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * @return the srcIpAddr
     */
    public String getSrcIpAddr() {
        return srcIpAddr;
    }

    /**
     * @param srcIpAddr the srcIpAddr to set
     */
    public void setSrcIpAddr(String srcIpAddr) {
        this.srcIpAddr = srcIpAddr;
    }

    /**
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName the domainName to set
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the totalMB
     */
    public long getTotalMB() {
        return totalMB;
    }

    /**
     * @param totalMB the totalMB to set
     */
    public void setTotalMB(long totalMB) {
        this.totalMB = totalMB;
    }

    /**
     * @return the freeMB
     */
    public long getFreeMB() {
        return freeMB;
    }

    /**
     * @param freeMB the freeMB to set
     */
    public void setFreeMB(long freeMB) {
        this.freeMB = freeMB;
    }

    /**
     * @return the uploadPriority
     */
    public int getUploadPriority() {
        return uploadPriority;
    }

    /**
     * @param uploadPriority the uploadPriority to set
     */
    public void setUploadPriority(int uploadPriority) {
        this.uploadPriority = uploadPriority;
    }

    /**
     * @return the joinTime
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * @param joinTime the joinTime to set
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * @return the upTime
     */
    public Date getUpTime() {
        return upTime;
    }

    /**
     * @param upTime the upTime to set
     */
    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    /**
     * @return the storePathCount
     */
    public int getStorePathCount() {
        return storePathCount;
    }

    /**
     * @param storePathCount the storePathCount to set
     */
    public void setStorePathCount(int storePathCount) {
        this.storePathCount = storePathCount;
    }

    /**
     * @return the subdirCountPerPath
     */
    public int getSubdirCountPerPath() {
        return subdirCountPerPath;
    }

    /**
     * @param subdirCountPerPath the subdirCountPerPath to set
     */
    public void setSubdirCountPerPath(int subdirCountPerPath) {
        this.subdirCountPerPath = subdirCountPerPath;
    }

    /**
     * @return the storagePort
     */
    public int getStoragePort() {
        return storagePort;
    }

    /**
     * @param storagePort the storagePort to set
     */
    public void setStoragePort(int storagePort) {
        this.storagePort = storagePort;
    }

    /**
     * @return the storageHttpPort
     */
    public int getStorageHttpPort() {
        return storageHttpPort;
    }

    /**
     * @param storageHttpPort the storageHttpPort to set
     */
    public void setStorageHttpPort(int storageHttpPort) {
        this.storageHttpPort = storageHttpPort;
    }

    /**
     * @return the currentWritePath
     */
    public int getCurrentWritePath() {
        return currentWritePath;
    }

    /**
     * @param currentWritePath the currentWritePath to set
     */
    public void setCurrentWritePath(int currentWritePath) {
        this.currentWritePath = currentWritePath;
    }

    /**
     * @return the totalUploadCount
     */
    public long getTotalUploadCount() {
        return totalUploadCount;
    }

    /**
     * @param totalUploadCount the totalUploadCount to set
     */
    public void setTotalUploadCount(long totalUploadCount) {
        this.totalUploadCount = totalUploadCount;
    }

    /**
     * @return the successUploadCount
     */
    public long getSuccessUploadCount() {
        return successUploadCount;
    }

    /**
     * @param successUploadCount the successUploadCount to set
     */
    public void setSuccessUploadCount(long successUploadCount) {
        this.successUploadCount = successUploadCount;
    }

    /**
     * @return the totalAppendCount
     */
    public long getTotalAppendCount() {
        return totalAppendCount;
    }

    /**
     * @param totalAppendCount the totalAppendCount to set
     */
    public void setTotalAppendCount(long totalAppendCount) {
        this.totalAppendCount = totalAppendCount;
    }

    /**
     * @return the successAppendCount
     */
    public long getSuccessAppendCount() {
        return successAppendCount;
    }

    /**
     * @param successAppendCount the successAppendCount to set
     */
    public void setSuccessAppendCount(long successAppendCount) {
        this.successAppendCount = successAppendCount;
    }

    /**
     * @return the totalModifyCount
     */
    public long getTotalModifyCount() {
        return totalModifyCount;
    }

    /**
     * @param totalModifyCount the totalModifyCount to set
     */
    public void setTotalModifyCount(long totalModifyCount) {
        this.totalModifyCount = totalModifyCount;
    }

    /**
     * @return the successModifyCount
     */
    public long getSuccessModifyCount() {
        return successModifyCount;
    }

    /**
     * @param successModifyCount the successModifyCount to set
     */
    public void setSuccessModifyCount(long successModifyCount) {
        this.successModifyCount = successModifyCount;
    }

    /**
     * @return the totalTruncateCount
     */
    public long getTotalTruncateCount() {
        return totalTruncateCount;
    }

    /**
     * @param totalTruncateCount the totalTruncateCount to set
     */
    public void setTotalTruncateCount(long totalTruncateCount) {
        this.totalTruncateCount = totalTruncateCount;
    }

    /**
     * @return the successTruncateCount
     */
    public long getSuccessTruncateCount() {
        return successTruncateCount;
    }

    /**
     * @param successTruncateCount the successTruncateCount to set
     */
    public void setSuccessTruncateCount(long successTruncateCount) {
        this.successTruncateCount = successTruncateCount;
    }

    /**
     * @return the totalSetMetaCount
     */
    public long getTotalSetMetaCount() {
        return totalSetMetaCount;
    }

    /**
     * @param totalSetMetaCount the totalSetMetaCount to set
     */
    public void setTotalSetMetaCount(long totalSetMetaCount) {
        this.totalSetMetaCount = totalSetMetaCount;
    }

    /**
     * @return the successSetMetaCount
     */
    public long getSuccessSetMetaCount() {
        return successSetMetaCount;
    }

    /**
     * @param successSetMetaCount the successSetMetaCount to set
     */
    public void setSuccessSetMetaCount(long successSetMetaCount) {
        this.successSetMetaCount = successSetMetaCount;
    }

    /**
     * @return the totalDeleteCount
     */
    public long getTotalDeleteCount() {
        return totalDeleteCount;
    }

    /**
     * @param totalDeleteCount the totalDeleteCount to set
     */
    public void setTotalDeleteCount(long totalDeleteCount) {
        this.totalDeleteCount = totalDeleteCount;
    }

    /**
     * @return the successDeleteCount
     */
    public long getSuccessDeleteCount() {
        return successDeleteCount;
    }

    /**
     * @param successDeleteCount the successDeleteCount to set
     */
    public void setSuccessDeleteCount(long successDeleteCount) {
        this.successDeleteCount = successDeleteCount;
    }

    /**
     * @return the totalDownloadCount
     */
    public long getTotalDownloadCount() {
        return totalDownloadCount;
    }

    /**
     * @param totalDownloadCount the totalDownloadCount to set
     */
    public void setTotalDownloadCount(long totalDownloadCount) {
        this.totalDownloadCount = totalDownloadCount;
    }

    /**
     * @return the successDownloadCount
     */
    public long getSuccessDownloadCount() {
        return successDownloadCount;
    }

    /**
     * @param successDownloadCount the successDownloadCount to set
     */
    public void setSuccessDownloadCount(long successDownloadCount) {
        this.successDownloadCount = successDownloadCount;
    }

    /**
     * @return the totalGetMetaCount
     */
    public long getTotalGetMetaCount() {
        return totalGetMetaCount;
    }

    /**
     * @param totalGetMetaCount the totalGetMetaCount to set
     */
    public void setTotalGetMetaCount(long totalGetMetaCount) {
        this.totalGetMetaCount = totalGetMetaCount;
    }

    /**
     * @return the successGetMetaCount
     */
    public long getSuccessGetMetaCount() {
        return successGetMetaCount;
    }

    /**
     * @param successGetMetaCount the successGetMetaCount to set
     */
    public void setSuccessGetMetaCount(long successGetMetaCount) {
        this.successGetMetaCount = successGetMetaCount;
    }

    /**
     * @return the totalCreateLinkCount
     */
    public long getTotalCreateLinkCount() {
        return totalCreateLinkCount;
    }

    /**
     * @param totalCreateLinkCount the totalCreateLinkCount to set
     */
    public void setTotalCreateLinkCount(long totalCreateLinkCount) {
        this.totalCreateLinkCount = totalCreateLinkCount;
    }

    /**
     * @return the successCreateLinkCount
     */
    public long getSuccessCreateLinkCount() {
        return successCreateLinkCount;
    }

    /**
     * @param successCreateLinkCount the successCreateLinkCount to set
     */
    public void setSuccessCreateLinkCount(long successCreateLinkCount) {
        this.successCreateLinkCount = successCreateLinkCount;
    }

    /**
     * @return the totalDeleteLinkCount
     */
    public long getTotalDeleteLinkCount() {
        return totalDeleteLinkCount;
    }

    /**
     * @param totalDeleteLinkCount the totalDeleteLinkCount to set
     */
    public void setTotalDeleteLinkCount(long totalDeleteLinkCount) {
        this.totalDeleteLinkCount = totalDeleteLinkCount;
    }

    /**
     * @return the successDeleteLinkCount
     */
    public long getSuccessDeleteLinkCount() {
        return successDeleteLinkCount;
    }

    /**
     * @param successDeleteLinkCount the successDeleteLinkCount to set
     */
    public void setSuccessDeleteLinkCount(long successDeleteLinkCount) {
        this.successDeleteLinkCount = successDeleteLinkCount;
    }

    /**
     * @return the totalUploadBytes
     */
    public long getTotalUploadBytes() {
        return totalUploadBytes;
    }

    /**
     * @param totalUploadBytes the totalUploadBytes to set
     */
    public void setTotalUploadBytes(long totalUploadBytes) {
        this.totalUploadBytes = totalUploadBytes;
    }

    /**
     * @return the successUploadBytes
     */
    public long getSuccessUploadBytes() {
        return successUploadBytes;
    }

    /**
     * @param successUploadBytes the successUploadBytes to set
     */
    public void setSuccessUploadBytes(long successUploadBytes) {
        this.successUploadBytes = successUploadBytes;
    }

    /**
     * @return the totalAppendBytes
     */
    public long getTotalAppendBytes() {
        return totalAppendBytes;
    }

    /**
     * @param totalAppendBytes the totalAppendBytes to set
     */
    public void setTotalAppendBytes(long totalAppendBytes) {
        this.totalAppendBytes = totalAppendBytes;
    }

    /**
     * @return the successAppendBytes
     */
    public long getSuccessAppendBytes() {
        return successAppendBytes;
    }

    /**
     * @param successAppendBytes the successAppendBytes to set
     */
    public void setSuccessAppendBytes(long successAppendBytes) {
        this.successAppendBytes = successAppendBytes;
    }

    /**
     * @return the totalModifyBytes
     */
    public long getTotalModifyBytes() {
        return totalModifyBytes;
    }

    /**
     * @param totalModifyBytes the totalModifyBytes to set
     */
    public void setTotalModifyBytes(long totalModifyBytes) {
        this.totalModifyBytes = totalModifyBytes;
    }

    /**
     * @return the successModifyBytes
     */
    public long getSuccessModifyBytes() {
        return successModifyBytes;
    }

    /**
     * @param successModifyBytes the successModifyBytes to set
     */
    public void setSuccessModifyBytes(long successModifyBytes) {
        this.successModifyBytes = successModifyBytes;
    }

    /**
     * @return the totalDownloadloadBytes
     */
    public long getTotalDownloadloadBytes() {
        return totalDownloadloadBytes;
    }

    /**
     * @param totalDownloadloadBytes the totalDownloadloadBytes to set
     */
    public void setTotalDownloadloadBytes(long totalDownloadloadBytes) {
        this.totalDownloadloadBytes = totalDownloadloadBytes;
    }

    /**
     * @return the successDownloadloadBytes
     */
    public long getSuccessDownloadloadBytes() {
        return successDownloadloadBytes;
    }

    /**
     * @param successDownloadloadBytes the successDownloadloadBytes to set
     */
    public void setSuccessDownloadloadBytes(long successDownloadloadBytes) {
        this.successDownloadloadBytes = successDownloadloadBytes;
    }

    /**
     * @return the totalSyncInBytes
     */
    public long getTotalSyncInBytes() {
        return totalSyncInBytes;
    }

    /**
     * @param totalSyncInBytes the totalSyncInBytes to set
     */
    public void setTotalSyncInBytes(long totalSyncInBytes) {
        this.totalSyncInBytes = totalSyncInBytes;
    }

    /**
     * @return the successSyncInBytes
     */
    public long getSuccessSyncInBytes() {
        return successSyncInBytes;
    }

    /**
     * @param successSyncInBytes the successSyncInBytes to set
     */
    public void setSuccessSyncInBytes(long successSyncInBytes) {
        this.successSyncInBytes = successSyncInBytes;
    }

    /**
     * @return the totalSyncOutBytes
     */
    public long getTotalSyncOutBytes() {
        return totalSyncOutBytes;
    }

    /**
     * @param totalSyncOutBytes the totalSyncOutBytes to set
     */
    public void setTotalSyncOutBytes(long totalSyncOutBytes) {
        this.totalSyncOutBytes = totalSyncOutBytes;
    }

    /**
     * @return the successSyncOutBytes
     */
    public long getSuccessSyncOutBytes() {
        return successSyncOutBytes;
    }

    /**
     * @param successSyncOutBytes the successSyncOutBytes to set
     */
    public void setSuccessSyncOutBytes(long successSyncOutBytes) {
        this.successSyncOutBytes = successSyncOutBytes;
    }

    /**
     * @return the totalFileOpenCount
     */
    public long getTotalFileOpenCount() {
        return totalFileOpenCount;
    }

    /**
     * @param totalFileOpenCount the totalFileOpenCount to set
     */
    public void setTotalFileOpenCount(long totalFileOpenCount) {
        this.totalFileOpenCount = totalFileOpenCount;
    }

    /**
     * @return the successFileOpenCount
     */
    public long getSuccessFileOpenCount() {
        return successFileOpenCount;
    }

    /**
     * @param successFileOpenCount the successFileOpenCount to set
     */
    public void setSuccessFileOpenCount(long successFileOpenCount) {
        this.successFileOpenCount = successFileOpenCount;
    }

    /**
     * @return the totalFileReadCount
     */
    public long getTotalFileReadCount() {
        return totalFileReadCount;
    }

    /**
     * @param totalFileReadCount the totalFileReadCount to set
     */
    public void setTotalFileReadCount(long totalFileReadCount) {
        this.totalFileReadCount = totalFileReadCount;
    }

    /**
     * @return the successFileReadCount
     */
    public long getSuccessFileReadCount() {
        return successFileReadCount;
    }

    /**
     * @param successFileReadCount the successFileReadCount to set
     */
    public void setSuccessFileReadCount(long successFileReadCount) {
        this.successFileReadCount = successFileReadCount;
    }

    /**
     * @return the totalFileWriteCount
     */
    public long getTotalFileWriteCount() {
        return totalFileWriteCount;
    }

    /**
     * @param totalFileWriteCount the totalFileWriteCount to set
     */
    public void setTotalFileWriteCount(long totalFileWriteCount) {
        this.totalFileWriteCount = totalFileWriteCount;
    }

    /**
     * @return the successFileWriteCount
     */
    public long getSuccessFileWriteCount() {
        return successFileWriteCount;
    }

    /**
     * @param successFileWriteCount the successFileWriteCount to set
     */
    public void setSuccessFileWriteCount(long successFileWriteCount) {
        this.successFileWriteCount = successFileWriteCount;
    }

    /**
     * @return the lastSourceUpdate
     */
    public Date getLastSourceUpdate() {
        return lastSourceUpdate;
    }

    /**
     * @param lastSourceUpdate the lastSourceUpdate to set
     */
    public void setLastSourceUpdate(Date lastSourceUpdate) {
        this.lastSourceUpdate = lastSourceUpdate;
    }

    /**
     * @return the lastSyncUpdate
     */
    public Date getLastSyncUpdate() {
        return lastSyncUpdate;
    }

    /**
     * @param lastSyncUpdate the lastSyncUpdate to set
     */
    public void setLastSyncUpdate(Date lastSyncUpdate) {
        this.lastSyncUpdate = lastSyncUpdate;
    }

    /**
     * @return the lastSyncedTimestamp
     */
    public Date getLastSyncedTimestamp() {
        return lastSyncedTimestamp;
    }

    /**
     * @param lastSyncedTimestamp the lastSyncedTimestamp to set
     */
    public void setLastSyncedTimestamp(Date lastSyncedTimestamp) {
        this.lastSyncedTimestamp = lastSyncedTimestamp;
    }

    /**
     * @return the lastHeartBeatTime
     */
    public Date getLastHeartBeatTime() {
        return lastHeartBeatTime;
    }

    /**
     * @param lastHeartBeatTime the lastHeartBeatTime to set
     */
    public void setLastHeartBeatTime(Date lastHeartBeatTime) {
        this.lastHeartBeatTime = lastHeartBeatTime;
    }

    /**
     * @return the isTrunkServer
     */
    public boolean isTrunkServer() {
        return isTrunkServer;
    }

    /**
     * @param isTrunkServer the isTrunkServer to set
     */
    public void setTrunkServer(boolean isTrunkServer) {
        this.isTrunkServer = isTrunkServer;
    }

    public int getConnectionAllocCount() {
        return connectionAllocCount;
    }

    public void setConnectionAllocCount(int connectionAllocCount) {
        this.connectionAllocCount = connectionAllocCount;
    }

    public int getConnectionCurrentCount() {
        return connectionCurrentCount;
    }

    public void setConnectionCurrentCount(int connectionCurrentCount) {
        this.connectionCurrentCount = connectionCurrentCount;
    }

    public int getConnectionMaxCount() {
        return connectionMaxCount;
    }

    public void setConnectionMaxCount(int connectionMaxCount) {
        this.connectionMaxCount = connectionMaxCount;
    }

    @Override
    public String toString() {
        return "StorageState [status=" + status + ", id=" + id + ", ipAddr=" + ipAddr + ", domainName=" + domainName
                + ", srcIpAddr=" + srcIpAddr + ", version=" + version + ", joinTime=" + joinTime + ", upTime=" + upTime
                + ", totalMB=" + totalMB + ", freeMB=" + freeMB + ", uploadPriority=" + uploadPriority
                + ", storePathCount=" + storePathCount + ", subdirCountPerPath=" + subdirCountPerPath
                + ", currentWritePath=" + currentWritePath + ", storagePort=" + storagePort + ", storageHttpPort="
                + storageHttpPort + ", connectionAllocCount=" + connectionAllocCount + ", connectionCurrentCount="
                + connectionCurrentCount + ", connectionMaxCount=" + connectionMaxCount + ", totalUploadCount="
                + totalUploadCount + ", successUploadCount=" + successUploadCount + ", totalAppendCount="
                + totalAppendCount + ", successAppendCount=" + successAppendCount + ", totalModifyCount="
                + totalModifyCount + ", successModifyCount=" + successModifyCount + ", totalTruncateCount="
                + totalTruncateCount + ", successTruncateCount=" + successTruncateCount + ", totalSetMetaCount="
                + totalSetMetaCount + ", successSetMetaCount=" + successSetMetaCount + ", totalDeleteCount="
                + totalDeleteCount + ", successDeleteCount=" + successDeleteCount + ", totalDownloadCount="
                + totalDownloadCount + ", successDownloadCount=" + successDownloadCount + ", totalGetMetaCount="
                + totalGetMetaCount + ", successGetMetaCount=" + successGetMetaCount + ", totalCreateLinkCount="
                + totalCreateLinkCount + ", successCreateLinkCount=" + successCreateLinkCount
                + ", totalDeleteLinkCount=" + totalDeleteLinkCount + ", successDeleteLinkCount="
                + successDeleteLinkCount + ", totalUploadBytes=" + totalUploadBytes + ", successUploadBytes="
                + successUploadBytes + ", totalAppendBytes=" + totalAppendBytes + ", successAppendBytes="
                + successAppendBytes + ", totalModifyBytes=" + totalModifyBytes + ", successModifyBytes="
                + successModifyBytes + ", totalDownloadloadBytes=" + totalDownloadloadBytes
                + ", successDownloadloadBytes=" + successDownloadloadBytes + ", totalSyncInBytes=" + totalSyncInBytes
                + ", successSyncInBytes=" + successSyncInBytes + ", totalSyncOutBytes=" + totalSyncOutBytes
                + ", successSyncOutBytes=" + successSyncOutBytes + ", totalFileOpenCount=" + totalFileOpenCount
                + ", successFileOpenCount=" + successFileOpenCount + ", totalFileReadCount=" + totalFileReadCount
                + ", successFileReadCount=" + successFileReadCount + ", totalFileWriteCount=" + totalFileWriteCount
                + ", successFileWriteCount=" + successFileWriteCount + ", lastSourceUpdate=" + lastSourceUpdate
                + ", lastSyncUpdate=" + lastSyncUpdate + ", lastSyncedTimestamp=" + lastSyncedTimestamp
                + ", lastHeartBeatTime=" + lastHeartBeatTime + ", isTrunkServer=" + isTrunkServer + "]";
    }

}
