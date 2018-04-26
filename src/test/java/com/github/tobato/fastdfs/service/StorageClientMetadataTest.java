package com.github.tobato.fastdfs.service;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import com.github.tobato.fastdfs.domain.MataData;
import org.junit.Test;

import com.github.tobato.fastdfs.TestConstants;
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
        Set<MataData> firstMataData = new HashSet<MataData>();
        firstMataData.add(new MataData("Author", "wyf"));
        firstMataData.add(new MataData("CreateDate", "2016-01-05"));
        storageClient.overwriteMetadata(path.getGroup(), path.getPath(), firstMataData);

        LOGGER.debug("##获取Metadata##");
        Set<MataData> fetchMataData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMataData, firstMataData);

        LOGGER.debug("##合并Metadata##");
        Set<MataData> secendMataData = new HashSet<MataData>();
        secendMataData.add(new MataData("Author", "tobato"));
        secendMataData.add(new MataData("CreateDate", "2016-01-05"));
        storageClient.mergeMetadata(path.getGroup(), path.getPath(), secendMataData);

        LOGGER.debug("##第二次获取Metadata##");
        fetchMataData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMataData, secendMataData);

        LOGGER.debug("##删除主文件..##");
        storageClient.deleteFile(path.getGroup(), path.getPath());

        LOGGER.debug("##第三次获取Metadata##");
        try {
            fetchMataData = storageClient.getMetadata(path.getGroup(), path.getPath());
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsServerException);
            assertTrue(((FdfsServerException) e).getErrorCode() == ErrorCodeConstants.ERR_NO_ENOENT);
        }
        LOGGER.debug("文件删除以后Metadata会自动删除，第三次就获取不到了");
    }

}
