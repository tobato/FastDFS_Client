package com.github.tobato.fastdfs.proto.storage;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.proto.storage.StorageDownloadCommand;
import com.github.tobato.fastdfs.socket.FdfsInputStream;

/**
 * 文件下载
 * 
 * @author wuyf
 *
 */
public class StorageDownloadCommandTest extends StorageCommandTestBase {

    @Test
    public void testStorageDownloadCommand() {
        // 上传文件
        StorePath path = uploadDefaultFile();

        // 删除文件
        StorageDownloadCommand command = new StorageDownloadCommand(path.getGroup(), path.getPath());
        FdfsInputStream input = executeStoreCmd(command);
        FileOutputStream out = null;
        InputStream in = null;
        try {
            out = new FileOutputStream("Test.jpg");
            in = new BufferedInputStream(input);
            // 通过ioutil 对接输入输出流，实现文件下载
            IOUtils.copy(in, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

        LOGGER.debug("----文件下载成功-----");
    }

}
