package com.github.tobato.fastdfs.service;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.TestUtils;
import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * FastFileStorageNewFileClientTest客户端
 *
 * @author tobato
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FastdfsTestApplication.class)
public class FastFileStorageNewImageClientTest {

    @Autowired
    protected FastFileStorageClient storageClient;

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(FastFileStorageNewImageClientTest.class);


    /**
     * 上传图片，不生成缩略图
     */
    @Test
    public void testUploadImageAndMetaData() throws Exception {
        LOGGER.debug("##上传文件..##");
        FastImageFile fastImageFile = crtFastImageFileOnly();
        StorePath path = uploadImageAndCrtThumbImage(fastImageFile);
        LOGGER.debug("上传文件路径{}", path);

        // 验证获取MetaData
        LOGGER.debug("##获取Metadata##");
        Set<MetaData> fetchMetaData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMetaData, fastImageFile.getMetaDataSet());
    }


    /**
     * 按缩略图默认配置上传
     *
     * @throws Exception
     */
    @Test
    public void testUploadImageAndCrtThumbImageByDefault() throws Exception {
        FastImageFile fastImageFile = crtFastImageAndCrtThumbImageByDefault();
        testUploadImageAndCrtThumbImage(fastImageFile);
    }

    /**
     * 按缩略图设定尺寸上传
     *
     * @throws Exception
     */
    @Test
    public void testUploadImageAndCrtThumbImageBySize() throws Exception {
        FastImageFile fastImageFile = crtFastImageAndCrtThumbImageBySize();
        testUploadImageAndCrtThumbImage(fastImageFile);
    }

    /**
     * 按缩略图比例上传
     *
     * @throws Exception
     */
    @Test
    public void testUploadImageAndCrtThumbImageByScale() throws Exception {
        FastImageFile fastImageFile = crtFastImageAndCrtThumbImageByScale();
        testUploadImageAndCrtThumbImage(fastImageFile);
    }


    /**
     * 上传图片，按默认配置生成缩略图
     */
    private void testUploadImageAndCrtThumbImage(FastImageFile fastImageFile) throws Exception {
        LOGGER.debug("##上传文件..##");
        StorePath path = uploadImageAndCrtThumbImage(fastImageFile);
        LOGGER.debug("测试上传主文件路径{}", path);

        // 验证获取MetaData
        LOGGER.debug("##能够获取Metadata##");
        Set<MetaData> fetchMetaData = storageClient.getMetadata(path.getGroup(), path.getPath());
        assertEquals(fetchMetaData, fastImageFile.getMetaDataSet());

        // 验证获取从文件
        LOGGER.debug("##能够获取从文件##");
        // 这里需要一个获取从文件名的能力
        String slavePath = fastImageFile.getThumbImagePath(path.getPath());
        LOGGER.debug("测试获取从文件路径{}", slavePath);
        // 或者由客户端再记录一下从文件的前缀
        FileInfo slaveFile = storageClient.queryFileInfo(path.getGroup(), slavePath);
        assertNotNull(slaveFile);
        LOGGER.debug("##测试获取到从文件##{}", slaveFile);

    }

    /**
     * 上传文件
     *
     * @param fastImageFile
     * @return
     */
    private StorePath uploadImageAndCrtThumbImage(FastImageFile fastImageFile) throws Exception {
        StorePath path = storageClient.uploadImage(fastImageFile);
        return path;
    }

    /**
     * 只上传文件
     *
     * @return
     * @throws Exception
     */
    private FastImageFile crtFastImageFileOnly() throws Exception {
        InputStream in = TestUtils.getFileInputStream(TestConstants.PERFORM_FILE_PATH);
        Set<MetaData> metaDataSet = createMetaData();
        File file = TestUtils.getFile(TestConstants.PERFORM_FILE_PATH);
        String fileExtName = FilenameUtils.getExtension(file.getName());

        return new FastImageFile.Builder()
                .withFile(in, file.length(), fileExtName)
                .withMetaData(metaDataSet)
                .build();
    }

    /**
     * 上传文件，按默认方式生成缩略图
     *
     * @return
     * @throws Exception
     */
    private FastImageFile crtFastImageAndCrtThumbImageByDefault() throws Exception {
        InputStream in = TestUtils.getFileInputStream(TestConstants.PERFORM_FILE_PATH);
        Set<MetaData> metaDataSet = createMetaData();
        File file = TestUtils.getFile(TestConstants.PERFORM_FILE_PATH);
        String fileExtName = FilenameUtils.getExtension(file.getName());

        return new FastImageFile.Builder()
                .withThumbImage()
                .withFile(in, file.length(), fileExtName)
                .withMetaData(metaDataSet)
                .build();
    }


    /**
     * 上传文件，按设定尺寸方式生成缩略图
     *
     * @return
     * @throws Exception
     */
    private FastImageFile crtFastImageAndCrtThumbImageBySize() throws Exception {
        InputStream in = TestUtils.getFileInputStream(TestConstants.PERFORM_FILE_PATH);
        Set<MetaData> metaDataSet = createMetaData();
        File file = TestUtils.getFile(TestConstants.PERFORM_FILE_PATH);
        String fileExtName = FilenameUtils.getExtension(file.getName());

        return new FastImageFile.Builder()
                .withThumbImage(200, 200)
                .withFile(in, file.length(), fileExtName)
                .withMetaData(metaDataSet)
                .build();
    }


    /**
     * 上传文件，按比例生成缩略图
     *
     * @return
     * @throws Exception
     */
    private FastImageFile crtFastImageAndCrtThumbImageByScale() throws Exception {
        InputStream in = TestUtils.getFileInputStream(TestConstants.PERFORM_FILE_PATH);
        Set<MetaData> metaDataSet = createMetaData();
        File file = TestUtils.getFile(TestConstants.PERFORM_FILE_PATH);
        String fileExtName = FilenameUtils.getExtension(file.getName());

        return new FastImageFile.Builder()
                .withThumbImage(0.5)  //50%比例
                .withFile(in, file.length(), fileExtName)
                .withMetaData(metaDataSet)
                .build();
    }


    /**
     * 元数据
     *
     * @return
     */
    private Set<MetaData> createMetaData() {
        Set<MetaData> metaDataSet = new HashSet<MetaData>();
        metaDataSet.add(new MetaData("Author", "tobato"));
        metaDataSet.add(new MetaData("CreateDate", "2016-01-05"));
        return metaDataSet;
    }

}
