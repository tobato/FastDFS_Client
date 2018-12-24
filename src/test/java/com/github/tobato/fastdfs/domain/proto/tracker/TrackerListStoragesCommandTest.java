package com.github.tobato.fastdfs.domain.proto.tracker;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.fdfs.StorageState;
import com.github.tobato.fastdfs.domain.proto.CommandTestBase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 列举存储目录服务器状态
 *
 * @author tobato
 */
public class TrackerListStoragesCommandTest extends CommandTestBase {

    @Test
    public void testGroupAndIp() {
        List<StorageState> list = executeTrackerCmd(
                new TrackerListStoragesCommand("group1", TestConstants.store_address.getHostString()));
        assertTrue(list.size() > 0);
        LOGGER.debug("-----根据IP列举存储服务器状态处理结果-----");
        LOGGER.debug(list.toString());
    }

    @Test
    public void testGroup() {
        List<StorageState> list = executeTrackerCmd(new TrackerListStoragesCommand("group1"));
        assertTrue(list.size() > 0);
        LOGGER.debug("-----列举存储服务器状态处理结果-----");
        LOGGER.debug(list.toString());
    }

}
