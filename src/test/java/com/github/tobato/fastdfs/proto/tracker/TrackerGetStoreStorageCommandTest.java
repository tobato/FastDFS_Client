package com.github.tobato.fastdfs.proto.tracker;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.tobato.fastdfs.domain.StorageNode;
import com.github.tobato.fastdfs.proto.CommandTestBase;
import com.github.tobato.fastdfs.proto.tracker.TrackerGetStoreStorageCommand;

/**
 * 获取存储节点交易
 * 
 * @author tobato
 *
 */
public class TrackerGetStoreStorageCommandTest extends CommandTestBase {

    @Test
    public void testTrackerGetStoreStorageCommand() {
        StorageNode client = executeTrackerCmd(new TrackerGetStoreStorageCommand());
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
        StorageNode client = executeTrackerCmd(new TrackerGetStoreStorageCommand("group1"));
        assertNotNull(client.getInetSocketAddress());
        LOGGER.debug("-----按组获取存储节点交易处理结果-----");
        LOGGER.debug(client.toString());

        // Connection conn = new
        // DefaultConnection(client.getInetSocketAddress(), 500, 300, null);
        // LOGGER.debug("连接状态{}", conn.isValid());
        // conn.close();
    }

}
