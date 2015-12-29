package com.github.tobato.fastdfs.cmd.tracker;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.tobato.fastdfs.StorageClient;

/**
 * 获取存储节点交易
 * 
 * @author wuyf
 *
 */
public class TrackerGetStoreStorageCommandTest extends CommandTestBase<StorageClient> {

    @Test
    public void testTrackerGetStoreStorageCommand() {
        StorageClient client = executeTrackerCmd(new TrackerGetStoreStorageCommand());
        assertNotNull(client.getInetSocketAddress());
        LOGGER.debug("-----获取存储节点交易处理结果-----{}");
        LOGGER.debug(client.toString());

        // Connection conn = new
        // DefaultConnection(client.getInetSocketAddress(), 500, 300, null);
        // LOGGER.debug("连接状态{}", conn.isValid());
        // conn.close();
    }

    @Test
    public void testTrackerGetStoreStorageWithGroupCommand() {
        StorageClient client = executeTrackerCmd(new TrackerGetStoreStorageCommand("group1"));
        assertNotNull(client.getInetSocketAddress());
        LOGGER.debug("-----按组获取存储节点交易处理结果-----");
        LOGGER.debug(client.toString());

        // Connection conn = new
        // DefaultConnection(client.getInetSocketAddress(), 500, 300, null);
        // LOGGER.debug("连接状态{}", conn.isValid());
        // conn.close();
    }

}
