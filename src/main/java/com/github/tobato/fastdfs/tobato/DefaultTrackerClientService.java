package com.github.tobato.fastdfs.tobato;

import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.domain.StorageClient;
import com.github.tobato.fastdfs.domain.StorageState;

/**
 * 目录服务客户端默认实现
 * 
 * @author wuyf
 *
 */
public class DefaultTrackerClientService implements TrackerClientService {

    @Override
    public StorageClient getStoreStorage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StorageClient getStoreStorage(String groupName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StorageClient getFetchStorage(String groupName, String filename) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StorageClient getUpdateStorage(String groupName, String filename) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GroupState[] listGroups() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StorageState[] listStorages(String groupName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StorageState[] listStorages(String groupName, String storageIpAddr) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteStorage(String groupName, String storageIpAddr) {
        // TODO Auto-generated method stub

    }

}
