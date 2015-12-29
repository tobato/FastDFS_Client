package com.github.tobato.fastdfs;

import java.util.Date;

/**
 * fastdfs中storage节点的状态信息
 * 
 * @author yuqih
 * 
 */
public class StorageState {

    byte status;
    String id;
    String ipAddr;
    String srcIpAddr;
    String domainName; // http domain name
    String version;
    long totalMB; // total disk storage in MB
    long freeMB; // free disk storage in MB
    int uploadPriority; // upload priority
    Date joinTime; // storage join timestamp (create timestamp)
    Date upTime; // storage service started timestamp
    int storePathCount; // store base path count of each storage
                        // server
    int subdirCountPerPath;
    int storagePort;
    int storageHttpPort; // storage http server port
    int currentWritePath; // current write path index
    long totalUploadCount;
    long successUploadCount;
    long totalAppendCount;
    long successAppendCount;
    long totalModifyCount;
    long successModifyCount;
    long totalTruncateCount;
    long successTruncateCount;
    long totalSetMetaCount;
    long successSetMetaCount;
    long totalDeleteCount;
    long successDeleteCount;
    long totalDownloadCount;
    long successDownloadCount;
    long totalGetMetaCount;
    long successGetMetaCount;
    long totalCreateLinkCount;
    long successCreateLinkCount;
    long totalDeleteLinkCount;
    long successDeleteLinkCount;
    long totalUploadBytes;
    long successUploadBytes;
    long totalAppendBytes;
    long successAppendBytes;
    long totalModifyBytes;
    long successModifyBytes;
    long totalDownloadloadBytes;
    long successDownloadloadBytes;
    long totalSyncInBytes;
    long successSyncInBytes;
    long totalSyncOutBytes;
    long successSyncOutBytes;
    long totalFileOpenCount;
    long successFileOpenCount;
    long totalFileReadCount;
    long successFileReadCount;
    long totalFileWriteCount;
    long successFileWriteCount;
    Date lastSourceUpdate;
    Date lastSyncUpdate;
    Date lastSyncedTimestamp;
    Date lastHeartBeatTime;
    boolean isTrunkServer;

    /**
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
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
     * @param id
     *            the id to set
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
     * @param ipAddr
     *            the ipAddr to set
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
     * @param srcIpAddr
     *            the srcIpAddr to set
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
     * @param domainName
     *            the domainName to set
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
     * @param version
     *            the version to set
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
     * @param totalMB
     *            the totalMB to set
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
     * @param freeMB
     *            the freeMB to set
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
     * @param uploadPriority
     *            the uploadPriority to set
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
     * @param joinTime
     *            the joinTime to set
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
     * @param upTime
     *            the upTime to set
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
     * @param storePathCount
     *            the storePathCount to set
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
     * @param subdirCountPerPath
     *            the subdirCountPerPath to set
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
     * @param storagePort
     *            the storagePort to set
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
     * @param storageHttpPort
     *            the storageHttpPort to set
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
     * @param currentWritePath
     *            the currentWritePath to set
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
     * @param totalUploadCount
     *            the totalUploadCount to set
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
     * @param successUploadCount
     *            the successUploadCount to set
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
     * @param totalAppendCount
     *            the totalAppendCount to set
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
     * @param successAppendCount
     *            the successAppendCount to set
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
     * @param totalModifyCount
     *            the totalModifyCount to set
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
     * @param successModifyCount
     *            the successModifyCount to set
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
     * @param totalTruncateCount
     *            the totalTruncateCount to set
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
     * @param successTruncateCount
     *            the successTruncateCount to set
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
     * @param totalSetMetaCount
     *            the totalSetMetaCount to set
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
     * @param successSetMetaCount
     *            the successSetMetaCount to set
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
     * @param totalDeleteCount
     *            the totalDeleteCount to set
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
     * @param successDeleteCount
     *            the successDeleteCount to set
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
     * @param totalDownloadCount
     *            the totalDownloadCount to set
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
     * @param successDownloadCount
     *            the successDownloadCount to set
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
     * @param totalGetMetaCount
     *            the totalGetMetaCount to set
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
     * @param successGetMetaCount
     *            the successGetMetaCount to set
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
     * @param totalCreateLinkCount
     *            the totalCreateLinkCount to set
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
     * @param successCreateLinkCount
     *            the successCreateLinkCount to set
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
     * @param totalDeleteLinkCount
     *            the totalDeleteLinkCount to set
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
     * @param successDeleteLinkCount
     *            the successDeleteLinkCount to set
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
     * @param totalUploadBytes
     *            the totalUploadBytes to set
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
     * @param successUploadBytes
     *            the successUploadBytes to set
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
     * @param totalAppendBytes
     *            the totalAppendBytes to set
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
     * @param successAppendBytes
     *            the successAppendBytes to set
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
     * @param totalModifyBytes
     *            the totalModifyBytes to set
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
     * @param successModifyBytes
     *            the successModifyBytes to set
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
     * @param totalDownloadloadBytes
     *            the totalDownloadloadBytes to set
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
     * @param successDownloadloadBytes
     *            the successDownloadloadBytes to set
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
     * @param totalSyncInBytes
     *            the totalSyncInBytes to set
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
     * @param successSyncInBytes
     *            the successSyncInBytes to set
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
     * @param totalSyncOutBytes
     *            the totalSyncOutBytes to set
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
     * @param successSyncOutBytes
     *            the successSyncOutBytes to set
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
     * @param totalFileOpenCount
     *            the totalFileOpenCount to set
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
     * @param successFileOpenCount
     *            the successFileOpenCount to set
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
     * @param totalFileReadCount
     *            the totalFileReadCount to set
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
     * @param successFileReadCount
     *            the successFileReadCount to set
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
     * @param totalFileWriteCount
     *            the totalFileWriteCount to set
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
     * @param successFileWriteCount
     *            the successFileWriteCount to set
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
     * @param lastSourceUpdate
     *            the lastSourceUpdate to set
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
     * @param lastSyncUpdate
     *            the lastSyncUpdate to set
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
     * @param lastSyncedTimestamp
     *            the lastSyncedTimestamp to set
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
     * @param lastHeartBeatTime
     *            the lastHeartBeatTime to set
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
     * @param isTrunkServer
     *            the isTrunkServer to set
     */
    public void setTrunkServer(boolean isTrunkServer) {
        this.isTrunkServer = isTrunkServer;
    }

}
