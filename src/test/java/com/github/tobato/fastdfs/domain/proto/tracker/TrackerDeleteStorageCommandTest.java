package com.github.tobato.fastdfs.domain.proto.tracker;

import com.github.tobato.fastdfs.domain.fdfs.StorageNode;
import com.github.tobato.fastdfs.domain.proto.ErrorCodeConstants;
import com.github.tobato.fastdfs.domain.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * 删除存储服务器
 *
 * @author tobato
 */
public class TrackerDeleteStorageCommandTest extends StorageCommandTestBase {

    /**
     * 删除存储服务器
     */
    @Test
    public void testTrackerDeleteStorageCommand() {

        // 获取存储节点信息
        StorageNode client = getOneStorage();

        // 获取源服务器
        TrackerDeleteStorageCommand command = new TrackerDeleteStorageCommand(client.getGroupName(), client.getIp());
        try {
            executeTrackerCmd(command);
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsServerException);
            assertTrue(((FdfsServerException) e).getErrorCode() == ErrorCodeConstants.ERR_NO_EBUSY);
        }
        LOGGER.debug("----删除存储服务器-----");
    }

    public StorageNode getOneStorage() {
        StorageNode client = executeTrackerCmd(new TrackerGetStoreStorageCommand("group1"));
        LOGGER.debug("-----列举存储服务器状态处理结果-----");
        LOGGER.debug(client.toString());
        return client;
    }

}
