package com.github.tobato.fastdfs.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.TestUtils;
import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.RandomTextFile;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;

/**
 * FastFileStorageClient客户端
 * 
 * @author tobato
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FastdfsTestApplication.class)
public class FastFileStorageClientTest {

    @Autowired
    protected FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    /** 日志 */
    protected static Logger LOGGER = LoggerFactory.getLogger(FastFileStorageClientTest.class);

    /**
     * 上传文件，并且设置MateData
     */
    @Test
    public void testUploadFileAndMateData() {

        LOGGER.debug("##上传文件..##");
        RandomTextFile file = new RandomTextFile();
        // Metadata
        Set<MateData> metaDataSet = createMateData();
        // 上传文件和Metadata
        StorePath path = storageClient.uploadFile(file.getInputStream(), file.getFileSize(), file.getFileExtName(),
                metaDataSet);
        assertNotNull(path);
        LOGGER.debug("上传文件路径{}", path);

        // 验证获取MataData
        LOGGER.debug("##获取Metadata##");
        Set<MateData> fetchMateData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMateData, metaDataSet);

        LOGGER.debug("##删除文件..##");
        storageClient.deleteFile(path.getGroup(), path.getPath());
    }

    /**
     * 不带MateData也应该能上传成功
     */
    @Test
    public void testUploadFileWithoutMateData() {

        LOGGER.debug("##上传文件..##");
        RandomTextFile file = new RandomTextFile();
        // 上传文件和Metadata
        StorePath path = storageClient.uploadFile(file.getInputStream(), file.getFileSize(), file.getFileExtName(),
                null);
        assertNotNull(path);

        LOGGER.debug("##删除文件..##");
        storageClient.deleteFile(path.getFullPath());
    }

    /**
     * 上传图片，并且生成缩略图
     */
    @Test
    public void testUploadImageAndCrtThumbImage() {
        LOGGER.debug("##上传文件..##");
        Set<MateData> metaDataSet = createMateData();
        StorePath path = uploadImageAndCrtThumbImage(TestConstants.PERFORM_FILE_PATH, metaDataSet);
        LOGGER.debug("上传文件路径{}", path);

        // 验证获取MataData
        LOGGER.debug("##获取Metadata##");
        Set<MateData> fetchMateData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMateData, metaDataSet);

        // 验证获取从文件
        LOGGER.debug("##获取Metadata##");
        // 这里需要一个获取从文件名的能力，所以从文件名配置以后就最好不要改了
        String slavePath = thumbImageConfig.getThumbImagePath(path.getPath());
        // 或者由客户端再记录一下从文件的前缀
        FileInfo slaveFile = storageClient.queryFileInfo(path.getGroup(), slavePath);
        assertNotNull(slaveFile);
        LOGGER.debug("##获取到从文件##{}", slaveFile);

    }

    /**
     * 上传文件
     * 
     * @param filePath
     * @return
     */
    private StorePath uploadImageAndCrtThumbImage(String filePath, Set<MateData> metaDataSet) {
        InputStream in = null;
        File file = TestUtils.getFile(filePath);
        String fileExtName = FilenameUtils.getExtension(file.getName());
        long fileSize = file.length();
        try {
            in = TestUtils.getFileInputStream(filePath);
            return storageClient.uploadImageAndCrtThumbImage(in, fileSize, fileExtName, metaDataSet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    private Set<MateData> createMateData() {
        Set<MateData> metaDataSet = new HashSet<MateData>();
        metaDataSet.add(new MateData("Author", "wyf"));
        metaDataSet.add(new MateData("CreateDate", "2016-01-05"));
        return metaDataSet;
    }

}
