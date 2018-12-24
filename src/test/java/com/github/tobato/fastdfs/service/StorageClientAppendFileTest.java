package com.github.tobato.fastdfs.service;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.RandomTextFile;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 支持断点续传的文件操作演示
 *
 * @author tobato
 */
public class StorageClientAppendFileTest extends StorageClientTestBase {

    /**
     * 支持断点续传的文件操作测试
     *
     * @throws IOException
     */
    @Test
    public void testAppendFileStorageClient() throws IOException {
        String firstString = "This is a string of append file Test.";
        String secendString = " This is secend content.";

        LOGGER.debug("##上传第一段..##");
        RandomTextFile file = new RandomTextFile(firstString);
        StorePath path = storageClient.uploadAppenderFile(TestConstants.DEFAULT_GROUP, file.getInputStream(),
                file.getFileSize(), file.getFileExtName());
        assertNotNull(path);
        LOGGER.debug("上传文件 result={}", path);

        LOGGER.debug("##添加第二段..##");
        RandomTextFile secendFile = new RandomTextFile(secendString);
        storageClient.appendFile(path.getGroup(), path.getPath(), secendFile.getInputStream(),
                secendFile.getFileSize());

        LOGGER.debug("##下载文件..##");
        DownloadByteArray callback = new DownloadByteArray();
        byte[] content = storageClient.downloadFile(path.getGroup(), path.getPath(), callback);
        byte[] fullContent = getContent(file, secendFile);
        // 验证文件相同
        assertArrayEquals(content, fullContent);

        LOGGER.debug("##截取保留第一段文件..##");
        long truncatedFileSize = 0;
        // TODO truncatedFileSize 是任何其他值都会报错?
        storageClient.truncateFile(path.getGroup(), path.getPath(), truncatedFileSize);

        LOGGER.debug("##使用modify重新上传第一段文件..##");
        storageClient.modifyFile(path.getGroup(), path.getPath(), file.getInputStream(), file.getFileSize(), 0);
        LOGGER.debug("##使用modify重新上传第二段文件..##");
        storageClient.modifyFile(path.getGroup(), path.getPath(), secendFile.getInputStream(), secendFile.getFileSize(),
                file.getFileSize());
        LOGGER.debug("##下载文件..##");
        content = storageClient.downloadFile(path.getGroup(), path.getPath(), callback);
        // 验证文件相同
        assertArrayEquals(content, fullContent);

        LOGGER.debug("##删除文件..##");
        storageClient.deleteFile(path.getGroup(), path.getPath());

    }

    /**
     * 合并文件内容
     *
     * @param file
     * @param secendFile
     * @return
     */
    private byte[] getContent(RandomTextFile file, RandomTextFile secendFile) {
        int fileSize = (int) file.getFileSize();
        int secendFileSize = (int) secendFile.getFileSize();
        byte[] fullContent = new byte[fileSize + secendFileSize];
        System.arraycopy(file.toByte(), 0, fullContent, 0, fileSize);
        System.arraycopy(secendFile.toByte(), 0, fullContent, fileSize, secendFileSize);
        return fullContent;
    }

}
