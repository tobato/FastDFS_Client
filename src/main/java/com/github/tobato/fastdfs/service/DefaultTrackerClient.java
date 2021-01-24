package com.github.tobato.fastdfs.service;

import com.github.tobato.fastdfs.domain.conn.FdfsConnectionManager;
import com.github.tobato.fastdfs.domain.conn.TrackerConnectionManager;
import com.github.tobato.fastdfs.domain.fdfs.*;
import com.github.tobato.fastdfs.domain.proto.tracker.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 目录服务客户端默认实现
 *
 * @author tobato
 */
@Service
public class DefaultTrackerClient implements TrackerClient {

    /**
     * 日志
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(DefaultTrackerClient.class);

    @Autowired
    private TrackerConnectionManager trackerConnectionManager;

    @Autowired
    private StorageNodeConfig storageNodeConfig;

    /**
     * 获取存储节点
     */
    @Override
    public StorageNode getStoreStorage() {
        TrackerGetStoreStorageCommand command = new TrackerGetStoreStorageCommand();
        return getStorageNode(trackerConnectionManager.executeFdfsTrackerCmd(command));
    }

    /**
     * 按组获取存储节点
     */
    @Override
    public StorageNode getStoreStorage(String groupName) {
        TrackerGetStoreStorageCommand command;
        if (StringUtils.isBlank(groupName)) {
            command = new TrackerGetStoreStorageCommand();
        } else {
            command = new TrackerGetStoreStorageCommand(groupName);
        }

        return getStorageNode(trackerConnectionManager.executeFdfsTrackerCmd(command));
    }

    /**
     * 获取源服务器
     */
    @Override
    public StorageNodeInfo getFetchStorage(String groupName, String filename) {
        TrackerGetFetchStorageCommand command = new TrackerGetFetchStorageCommand(groupName, filename, false);
        return getStorageNode(trackerConnectionManager.executeFdfsTrackerCmd(command));
    }

    /**
     * 获取更新服务器
     */
    @Override
    public StorageNodeInfo getUpdateStorage(String groupName, String filename) {
        TrackerGetFetchStorageCommand command = new TrackerGetFetchStorageCommand(groupName, filename, true);
        return getStorageNode(trackerConnectionManager.executeFdfsTrackerCmd(command));
    }

    /**
     * 列出组
     */
    @Override
    public List<GroupState> listGroups() {
        TrackerListGroupsCommand command = new TrackerListGroupsCommand();
        return trackerConnectionManager.executeFdfsTrackerCmd(command);
    }

    /**
     * 按组列出存储状态
     */
    @Override
    public List<StorageState> listStorages(String groupName) {
        TrackerListStoragesCommand command = new TrackerListStoragesCommand(groupName);
        return trackerConnectionManager.executeFdfsTrackerCmd(command);
    }

    /**
     * 按ip列出存储状态
     */
    @Override
    public List<StorageState> listStorages(String groupName, String storageIpAddr) {
        TrackerListStoragesCommand command = new TrackerListStoragesCommand(groupName, storageIpAddr);
        return trackerConnectionManager.executeFdfsTrackerCmd(command);
    }

    /**
     * 删除存储节点
     */
    @Override
    public void deleteStorage(String groupName, String storageIpAddr) {
        TrackerDeleteStorageCommand command = new TrackerDeleteStorageCommand(groupName, storageIpAddr);
        trackerConnectionManager.executeFdfsTrackerCmd(command);
    }

    /**
     * 获取 存储节点
     * @param storageNode 存储节点
     * @return 存储节点信息
     */
    private <T> T getStorageNode(T storageNode) {

        Object groupNameObj = getFieldValue(storageNode, "groupName");
        Object ipObj = getFieldValue(storageNode, "ip");
        Object portObj = getFieldValue(storageNode, "port");

        if (!ObjectUtils.isEmpty(groupNameObj) && !ObjectUtils.isEmpty(ipObj)
                && !ObjectUtils.isEmpty(portObj)) {
            String groupName = String.valueOf(groupNameObj);
            String ip = String.valueOf(ipObj);
            Integer port = Integer.parseInt(String.valueOf(portObj));

            StorageNodeReal storageNodeReal = getStorageNodeReal(groupName, ip, port);
            BeanUtils.copyProperties(storageNodeReal, storageNode);
        }

        return storageNode;
    }

    /**
     * 获取节点真实的IP，端口信息
     * @param groupName 组名
     * @param ip IP
     * @param port 端口
     * @return 真实的IP, 端口信息
     */
    private StorageNodeReal getStorageNodeReal(String groupName, String ip, Integer port) {

        Map<String, Map<String, String>> ipMapping = storageNodeConfig.getIpMapping();

        StorageNodeReal storageNodeReal = new StorageNodeReal();
        storageNodeReal.setGroupName(groupName);
        storageNodeReal.setIp(ip);
        storageNodeReal.setPort(port);

        if (MapUtils.isNotEmpty(ipMapping)) {
            if (ipMapping.containsKey(groupName)) {
                String host = ip + "-" + port;
                Map<String, String> realNode = ipMapping.get(groupName);
                if (MapUtils.isNotEmpty(realNode) && realNode.containsKey(host)) {
                    String[] node = StringUtils.split(realNode.get(host), "-");
                    storageNodeReal.setIp(node[0]);
                    storageNodeReal.setPort(Integer.parseInt(node[1]));
                }
            }
        }

        return storageNodeReal;
    }

    private List<Field> getFields(Object obj) {
        return Arrays.asList(obj.getClass().getDeclaredFields());
    }

    private Object getFieldValue(Object obj, String fieldName) {
        List<Field> fieldList = getFields(obj);
        for (Field field : fieldList) {
            if (StringUtils.equalsIgnoreCase(field.getName(), fieldName)) {

                boolean accessFlag = field.isAccessible();

                try {
                    field.setAccessible(Boolean.TRUE);
                    return field.get(obj);
                } catch (IllegalAccessException e) {
                    LOGGER.error("getFieldValue {}", e.getMessage());
                }finally {
                    field.setAccessible(accessFlag);
                }
            }
        }
        return null;
    }



}
