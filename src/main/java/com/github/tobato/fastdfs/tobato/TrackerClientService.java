package com.github.tobato.fastdfs.tobato;

import com.github.tobato.fastdfs.GroupState;
import com.github.tobato.fastdfs.StorageClient;
import com.github.tobato.fastdfs.StorageState;

/**
 * 目录服务接口
 * 
 * @author tobato
 */
public interface TrackerClientService {

    /**
     * 获取存储节点 get the StoreStorage Client
     * 
     * @return
     */
    StorageClient getStoreStorage();

    /**
     * 按组获取存储节点 get the StoreStorage Client by group
     * 
     * @param groupName
     * @return
     */
    StorageClient getStoreStorage(String groupName);

    /**
     * 获取读取存储节点 get the fetchStorage Client by group and filename
     * 
     * @param groupName
     * @param filename
     * @return
     */
    StorageClient getFetchStorage(String groupName, String filename);

    /**
     * 获取更新节点 get the updateStorage Client by group and filename
     * 
     * @param groupName
     * @param filename
     * @return
     */
    StorageClient getUpdateStorage(String groupName, String filename);

    /**
     * 获取组状态list groups
     * 
     * @return
     */
    GroupState[] listGroups();

    /**
     * 按组名获取存储节点状态list storages by groupName
     * 
     * @param groupName
     * @return
     */
    StorageState[] listStorages(String groupName);

    /**
     * 获取存储状态 list storages by groupName and storageIpAddr
     * 
     * @param groupName
     * @param storageIpAddr
     * @return
     */
    StorageState[] listStorages(String groupName, String storageIpAddr);

    /**
     * 删除存储节点 delete storage from TrackerServer
     * 
     * @param groupName
     * @param storageIpAddr
     */
    void deleteStorage(String groupName, String storageIpAddr);

}
