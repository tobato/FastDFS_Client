package com.github.tobato.fastdfs.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.tobato.fastdfs.FastdfsTestApplication;

/**
 * 缩略图配置测试
 * 
 * @author tobato
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FastdfsTestApplication.class)
public class DefaultThumbImageConfigTest {

    /** 日志 */
    protected static Logger LOGGER = LoggerFactory.getLogger(DefaultThumbImageConfigTest.class);

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void testGetThumbImagePrefixName() {
        assertNotNull(thumbImageConfig.getPrefixName());
    }

    @Test
    public void testGetThumbImagePath() {

        String path = "wKgBaVaNODiAPpVCAAGtJ7UVNRA438.jpg";
        String thumbPath = "wKgBaVaNODiAPpVCAAGtJ7UVNRA438" + thumbImageConfig.getPrefixName() + ".jpg";

        String result = thumbImageConfig.getThumbImagePath(path);
        LOGGER.debug(result);
        assertEquals(thumbPath, result);

    }

}
