package com.github.tobato.fastdfs.service;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.RandomTextFile;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.proto.ErrorCodeConstants;

/**
 * Metadata操作演示
 * 
 * @author tobato
 *
 */
public class StorageClientMetadataTest extends StorageClientTestBase {

    @Test
    public void testMetadataOperator() {
        LOGGER.debug("##上传文件..##");
        RandomTextFile file = new RandomTextFile();
        StorePath path = storageClient.uploadFile(TestConstants.DEFAULT_GROUP, file.getInputStream(),
                file.getFileSize(), file.getFileExtName());
        assertNotNull(path);
        LOGGER.debug("上传文件 result={}", path);

        LOGGER.debug("##生成Metadata##");
        Set<MateData> firstMateData = new HashSet<MateData>();
        firstMateData.add(new MateData("Author", "wyf"));
        firstMateData.add(new MateData("CreateDate", "2016-01-05"));
        storageClient.overwriteMetadata(path.getGroup(), path.getPath(), firstMateData);

        LOGGER.debug("##获取Metadata##");
        Set<MateData> fetchMateData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMateData, firstMateData);

        LOGGER.debug("##合并Metadata##");
        Set<MateData> secendMateData = new HashSet<MateData>();
        secendMateData.add(new MateData("Author", "tobato"));
        secendMateData.add(new MateData("CreateDate", "2016-01-05"));
        storageClient.mergeMetadata(path.getGroup(), path.getPath(), secendMateData);

        LOGGER.debug("##第二次获取Metadata##");
        fetchMateData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMateData, secendMateData);

        LOGGER.debug("##删除主文件..##");
        storageClient.deleteFile(path.getGroup(), path.getPath());

        LOGGER.debug("##第三次获取Metadata##");
        try {
            fetchMateData = storageClient.getMetadata(path.getGroup(), path.getPath());
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsServerException);
            assertTrue(((FdfsServerException) e).getErrorCode() == ErrorCodeConstants.ERR_NO_ENOENT);
        }
        LOGGER.debug("文件删除以后Metadata会自动删除，第三次就获取不到了");
    }

}
