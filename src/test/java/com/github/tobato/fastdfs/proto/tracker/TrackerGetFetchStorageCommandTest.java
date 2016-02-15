package com.github.tobato.fastdfs.proto.tracker;

import org.junit.Test;

import com.github.tobato.fastdfs.domain.StorageNodeInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.proto.tracker.TrackerGetFetchStorageCommand;

/**
 * 获取源服务器
 * 
 * @author tobato
 *
 */
public class TrackerGetFetchStorageCommandTest extends StorageCommandTestBase {

    /**
     * 获取源服务器
     */
    @Test
    public void testTrackerGetFetchStorageCommand() {

        // 上传文件
        StorePath path = uploadDefaultFile();

        // 获取源服务器
        TrackerGetFetchStorageCommand command = new TrackerGetFetchStorageCommand(path.getGroup(), path.getPath(),
                false);
        StorageNodeInfo client = executeTrackerCmd(command);
        LOGGER.debug("----获取源服务器-----");
        LOGGER.debug(client.toString());
    }
}
