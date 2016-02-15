package com.github.tobato.fastdfs.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.RandomTextFile;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;

/**
 * 文件基础操作测试演示
 * 
 * @author tobato
 *
 */
public class StorageClientBasicTest extends StorageClientTestBase {

    /**
     * 基本文件上传操作测试
     * 
     * @throws IOException
     */
    @Test
    public void testGenerateStorageClient() throws IOException {
        LOGGER.debug("##上传文件..##");
        RandomTextFile file = new RandomTextFile();
        StorePath path = storageClient.uploadFile(TestConstants.DEFAULT_GROUP, file.getInputStream(),
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

    /**
     * 演示上传文件的时候Group可以为空
     * 
     * @throws IOException
     */
    @Test
    public void testGenerateStorageClientWithGroupNull() throws IOException {

        LOGGER.debug("##上传文件..##");
        RandomTextFile file = new RandomTextFile();
        StorePath path = storageClient.uploadFile(null, file.getInputStream(), file.getFileSize(),
                file.getFileExtName());
        assertNotNull(path);
        LOGGER.debug("上传文件 result={}", path);

        LOGGER.debug("##删除文件..##");
        storageClient.deleteFile(path.getGroup(), path.getPath());
    }

}
