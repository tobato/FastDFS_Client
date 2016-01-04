package com.github.tobato.fastdfs.tobato;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import com.github.tobato.fastdfs.RemoteServiceDefine;
import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.domain.StorageNode;
import com.github.tobato.fastdfs.domain.StorageState;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.proto.ErrorCodeConstants;

/**
 * unit test for TrackerClientService
 * 
 * @author wuyf
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FastdfsTestApplication.class)
public class TrackerClientTest {

    /** 日志 */
    private static Logger LOGGER = LoggerFactory.getLogger(TrackerClientTest.class);

    @Resource
    private TrackerClient trackerClient;

    @Test
    public void testGetStoreStorage() {
        LOGGER.debug("testGetStoreStorage..");
        StorageNode client = trackerClient.getStoreStorage();
        assertNotNull(client.getInetSocketAddress());
        LOGGER.debug("result={}", client);

    }

    @Test
    public void testGetStoreStorageByGroup() {
        LOGGER.debug("testGetStoreStorageByGroup..");
        StorageNode client = trackerClient.getStoreStorage(RemoteServiceDefine.DEFAULT_GROUP);
        assertNotNull(client.getInetSocketAddress());
        LOGGER.debug("result={}", client);
    }

    @Test
    public void testListGroups() {
        LOGGER.debug("testListGroups..");
        List<GroupState> list = trackerClient.listGroups();
        assertNotNull(list);
        LOGGER.debug("result={}", list);
    }

    @Test
    public void testListStoragesByGroup() {
        LOGGER.debug("testListStoragesByGroup..");
        List<StorageState> list = trackerClient.listStorages(RemoteServiceDefine.DEFAULT_GROUP);
        assertNotNull(list);
        LOGGER.debug("result={}", list);
    }

    @Test
    public void testListStoragesByGroupAndIp() {
        LOGGER.debug("testListStoragesByGroupAndIp..");
        List<StorageState> list = trackerClient.listStorages(RemoteServiceDefine.DEFAULT_GROUP,
                RemoteServiceDefine.DEFAULT_STORAGE_IP);
        assertNotNull(list);
        LOGGER.debug("result={}", list);
    }

    @Test
    public void testDeleteStorage() {
        LOGGER.debug("testDeleteStorage..");
        try {
            trackerClient.deleteStorage(RemoteServiceDefine.DEFAULT_GROUP,
                    RemoteServiceDefine.DEFAULT_STORAGE_IP);
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsServerException);
            assertTrue(((FdfsServerException) e).getErrorCode() == ErrorCodeConstants.ERR_NO_EBUSY);
        }
        LOGGER.debug("testDeleteStorage..done");
    }

}
