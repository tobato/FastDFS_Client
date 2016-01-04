package com.github.tobato.fastdfs.tobato;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import com.github.tobato.fastdfs.RemoteServiceDefine;
import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.RandomTextFile;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;

/**
 * 存储节点服务测试
 * 
 * @author wuyf
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FastdfsTestApplication.class)
public class AppendFileStorageClientTest {

    @Autowired
    private AppendFileStorageClient storageClient;

    /** 日志 */
    private static Logger LOGGER = LoggerFactory.getLogger(AppendFileStorageClientTest.class);

    /**
     * 文件上传测试
     * 
     * @throws IOException
     */
    @Test
    public void testGenerateStorageClient() throws IOException {
        LOGGER.debug("##上传文件..##");
        RandomTextFile file = new RandomTextFile();
        StorePath path = storageClient.uploadFile(RemoteServiceDefine.DEFAULT_GROUP, file.getInputStream(),
                file.getFileSize(), file.getFileExtName());
        assertNotNull(path);
        LOGGER.debug("上传文件 result={}", path);

        LOGGER.debug("##查询文件信息..##");
        FileInfo fileInfo = storageClient.queryFileInfo(path.getGroup(), path.getPath());
        LOGGER.debug("查询文件信息 result={}", fileInfo);

        LOGGER.debug("##下载文件..##");
        DownloadByteArray callback = new DownloadByteArray();
        byte[] content = storageClient.downloadFile(path.getGroup(), path.getPath(), callback);
        assertArrayEquals(content, file.toByte());

        LOGGER.debug("##上传从文件..##");
        RandomTextFile slaveFile = new RandomTextFile();
        // TODO 120*120会报错误，看是否可以从客户端截获此错误
        StorePath slavePath = storageClient.uploadSlaveFile(path.getGroup(), path.getPath(), slaveFile.getInputStream(),
                slaveFile.getFileSize(), "120_120", slaveFile.getFileExtName());
        LOGGER.debug("上传从文件 result={}", slavePath);

        LOGGER.debug("##删除主文件..##");
        storageClient.deleteFile(path.getGroup(), path.getPath());
        LOGGER.debug("##删除从文件..##");
        storageClient.deleteFile(slavePath.getGroup(), slavePath.getPath());

    }

    @Test
    public void testTruncateFile() {
        fail("Not yet implemented");
    }

    @Test
    public void testAppenderFile() {
        LOGGER.debug("testUploadAppenderFile..");
        RandomTextFile file = new RandomTextFile();
        StorePath path = storageClient.uploadAppenderFile(RemoteServiceDefine.DEFAULT_GROUP, file.getInputStream(),
                file.getFileSize(), file.getFileExtName());
        assertNotNull(path);
        LOGGER.debug("result={}", path);
    }

    @Test
    public void testAppendFile() {
        fail("Not yet implemented");
    }

    @Test
    public void testModifyFile() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMetadata() {
        fail("Not yet implemented");
    }

    @Test
    public void testOverwriteMetadata() {
        fail("Not yet implemented");
    }

    @Test
    public void testMergeMetadata() {
        fail("Not yet implemented");
    }

}
