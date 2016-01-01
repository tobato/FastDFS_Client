package com.github.tobato.fastdfs.cmd.storage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.tobato.fastdfs.FileInfo;
import com.github.tobato.fastdfs.StorePath;
import com.github.tobato.fastdfs.cmd.StorageCommandTestBase;

/**
 * 文件查询处理
 * 
 * @author wuyf
 *
 */
public class StorageQueryFileInfoCommandTest extends StorageCommandTestBase {

    @Test
    public void testStorageQueryFileInfoCommand() {
        // 上传文件
        StorePath path = uploadDefaultFile();

        // 查询文件
        StorageQueryFileInfoCommand command = new StorageQueryFileInfoCommand(path.getGroup(), path.getPath());
        FileInfo fileInfo = executeStoreCmd(command);
        assertNotNull(fileInfo);
        LOGGER.debug("----文件查询处理结果-----");
        LOGGER.debug(fileInfo.toString());
    }

}
