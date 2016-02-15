package com.github.tobato.fastdfs.proto.storage;

import org.junit.Test;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.StorageCommandTestBase;

/**
 * 文件下载
 * 
 * @author tobato
 *
 */
public class StorageDownloadCommandTest extends StorageCommandTestBase {

    @Test
    public void testStorageDownloadCommand() {
        // 上传文件
        StorePath path = uploadDefaultFile();
        DownloadFileWriter callback = new DownloadFileWriter("Test.jpg");
        // 删除文件
        StorageDownloadCommand<String> command = new StorageDownloadCommand<String>(path.getGroup(), path.getPath(),
                callback);
        String fileName = executeStoreCmd(command);
        LOGGER.debug("----文件下载成功-----{}", fileName);
    }

}
