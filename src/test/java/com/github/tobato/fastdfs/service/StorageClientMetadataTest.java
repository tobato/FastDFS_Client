package com.github.tobato.fastdfs.service;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.RandomTextFile;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.ErrorCodeConstants;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Metadata操作演示
 *
 * @author tobato
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
        Set<MetaData> firstMetaData = new HashSet<MetaData>();
        firstMetaData.add(new MetaData("Author", "wyf"));
        firstMetaData.add(new MetaData("CreateDate", "2016-01-05"));
        storageClient.overwriteMetadata(path.getGroup(), path.getPath(), firstMetaData);

        LOGGER.debug("##获取Metadata##");
        Set<MetaData> fetchMetaData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMetaData, firstMetaData);

        LOGGER.debug("##合并Metadata##");
        Set<MetaData> secendMetaData = new HashSet<MetaData>();
        secendMetaData.add(new MetaData("Author", "tobato"));
        secendMetaData.add(new MetaData("CreateDate", "2016-01-05"));
        storageClient.mergeMetadata(path.getGroup(), path.getPath(), secendMetaData);

        LOGGER.debug("##第二次获取Metadata##");
        fetchMetaData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMetaData, secendMetaData);

        LOGGER.debug("##删除主文件..##");
        storageClient.deleteFile(path.getGroup(), path.getPath());

        LOGGER.debug("##第三次获取Metadata##");
        try {
            fetchMetaData = storageClient.getMetadata(path.getGroup(), path.getPath());
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsServerException);
            assertTrue(((FdfsServerException) e).getErrorCode() == ErrorCodeConstants.ERR_NO_ENOENT);
        }
        LOGGER.debug("文件删除以后Metadata会自动删除，第三次就获取不到了");
    }

}
