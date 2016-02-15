package com.github.tobato.fastdfs.proto.storage;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.proto.storage.StorageQueryFileInfoCommand;

/**
 * 文件查询处理
 * 
 * @author tobato
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
