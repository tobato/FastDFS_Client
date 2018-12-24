package com.github.tobato.fastdfs.service;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.fdfs.GroupState;
import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.fdfs.StorageState;
import com.github.tobato.fastdfs.domain.proto.ErrorCodeConstants;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * unit test for TrackerClientService
 *
 * @author tobato
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FastdfsTestApplication.class)
public class TrackerClientTest {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(TrackerClientTest.class);

    @Autowired
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
        StorageNode client = trackerClient.getStoreStorage(TestConstants.DEFAULT_GROUP);
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
        List<StorageState> list = trackerClient.listStorages(TestConstants.DEFAULT_GROUP);
        assertNotNull(list);
        LOGGER.debug("result={}", list);
    }

    @Test
    public void testListStoragesByGroupAndIp() {
        LOGGER.debug("testListStoragesByGroupAndIp..");
        List<StorageState> list = trackerClient.listStorages(TestConstants.DEFAULT_GROUP,
                TestConstants.DEFAULT_STORAGE_IP);
        assertNotNull(list);
        LOGGER.debug("result={}", list);
    }

    @Test
    public void testDeleteStorage() {
        LOGGER.debug("testDeleteStorage..");
        try {
            trackerClient.deleteStorage(TestConstants.DEFAULT_GROUP,
                    TestConstants.DEFAULT_STORAGE_IP);
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsServerException);
            assertTrue(((FdfsServerException) e).getErrorCode() == ErrorCodeConstants.ERR_NO_EBUSY);
        }
        LOGGER.debug("testDeleteStorage..done");
    }

}
