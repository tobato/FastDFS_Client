package com.github.tobato.fastdfs.service;

import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.domain.StorageNode;
import com.github.tobato.fastdfs.domain.StorageState;

/**
 * TrackerClientService
 * 
 * @author tobato
 */
public interface TrackerClientService {

    /**
     * get the StoreStorage Client
     * 
     * @return
     */
    StorageNode getStoreStorage();

    /**
     * get the StoreStorage Client by group
     * 
     * @param groupName
     * @return
     */
    StorageNode getStoreStorage(String groupName);

    /**
     * get the fetchStorage Client by group and filename
     * 
     * @param groupName
     * @param filename
     * @return
     */
    StorageNode getFetchStorage(String groupName, String filename);

    /**
     * get the updateStorage Client by group and filename
     * 
     * @param groupName
     * @param filename
     * @return
     */
    StorageNode getUpdateStorage(String groupName, String filename);

    /**
     * list groups
     * 
     * @return
     */
    GroupState[] listGroups();

    /**
     * list storages by groupName
     * 
     * @param groupName
     * @return
     */
    StorageState[] listStorages(String groupName);

    /**
     * list storages by groupName and storageIpAddr
     * 
     * @param groupName
     * @param storageIpAddr
     * @return
     */
    StorageState[] listStorages(String groupName, String storageIpAddr);

    /**
     * delete storage from TrackerServer
     * 
     * @param groupName
     * @param storageIpAddr
     */
    void deleteStorage(String groupName, String storageIpAddr);

}
