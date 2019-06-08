package com.github.tobato.fastdfs.domain.proto.storage.monkey;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadFileWriter;
import com.github.tobato.fastdfs.domain.proto.storage.StorageDownloadCommand;
import org.junit.Test;

/**
 * 文件下载
 *
 * @author tobato
 */
public class StorageMonkeyDownloadTest extends StorageCommandTestBase {

    /**
     * 问题#121当发生异常时，怎么把链接中对应的流数据一次性清空
     */
    @Test
    public void testStorageDownloadCommand() {
        // 上传文件
        StorePath path = uploadDefaultFile();
        MonkeyDownloadFileWriter callback = new MonkeyDownloadFileWriter("Test.jpg");
        // 下载文件
        StorageDownloadCommand<String> commandErr = new StorageDownloadCommand<String>(path.getGroup(), path.getPath(),
                callback);

        //下载文件错误
        LOGGER.debug("======下载文件时候发生错误========");
        try {
            executeStoreCmd(commandErr);
        } catch (Exception e) {
            //ignore
        }
        //--导出pool
        dumpPool();
        LOGGER.debug("======再次下载文件========");

        DownloadFileWriter callbackB = new DownloadFileWriter("Test.jpg");
        //再次正常下载
        StorageDownloadCommand<String> command = new StorageDownloadCommand<String>(path.getGroup(), path.getPath(),
                callbackB);

        String file = executeStoreCmd(command);
        dumpPool();

        LOGGER.debug("----文件下载成功-----{}", file);
    }

}
