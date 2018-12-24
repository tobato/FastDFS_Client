package com.github.tobato.fastdfs.domain.proto.storage;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.StorageCommandTestBase;
import org.junit.Test;

/**
 * 文件下载
 *
 * @author tobato
 */
public class StorageDownloadCommandTest extends StorageCommandTestBase {

    @Test
    public void testStorageDownloadCommand() {
        // 上传文件
        StorePath path = uploadDefaultFile();
        DownloadFileWriter callback = new DownloadFileWriter("Test.jpg");
        // 下载文件
        StorageDownloadCommand<String> command = new StorageDownloadCommand<String>(path.getGroup(), path.getPath(),
                callback);
        String fileName = executeStoreCmd(command);
        LOGGER.debug("----文件下载成功-----{}", fileName);
    }

    @Test
    public void testStorageSubDownloadCommand() {
        // 上传文件
        StorePath path = uploadDefaultFile();
        DownloadFileWriter callback = new DownloadFileWriter("Test.jpg");
        // 下载文件
        StorageDownloadCommand<String> command = new StorageDownloadCommand<String>(
                path.getGroup(),
                path.getPath(),
                2, 2,
                callback);
        String fileName = executeStoreCmd(command);
        LOGGER.debug("----文件下载成功-----{}", fileName);
    }


}
