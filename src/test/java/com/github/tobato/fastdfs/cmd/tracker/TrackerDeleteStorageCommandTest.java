package com.github.tobato.fastdfs.cmd.tracker;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.tobato.fastdfs.StorageClient;
import com.github.tobato.fastdfs.cmd.StorageCommandTestBase;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.proto.ErrorCodeConstants;

/**
 * 删除存储服务器
 * 
 * @author wuyf
 *
 */
public class TrackerDeleteStorageCommandTest extends StorageCommandTestBase {

    /**
     * 删除存储服务器
     */
    @Test
    public void testTrackerDeleteStorageCommand() {

        // 获取存储节点信息
        StorageClient client = getOneStorage();

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

    public StorageClient getOneStorage() {
        StorageClient client = executeTrackerCmd(new TrackerGetStoreStorageCommand("group1"));
        LOGGER.debug("-----列举存储服务器状态处理结果-----");
        LOGGER.debug(client.toString());
        return client;
    }

}
