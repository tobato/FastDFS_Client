package com.github.tobato.fastdfs.domain;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * 文件路径对象
 *
 * @author tobato
 */
public class StorePathTest {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(StorePathTest.class);

    /**
     * 可以从url解析文件路径
     */
    @Test
    public void testParseFromUrl() {
        String filePath = "group1/huex_sjuej/3hjshf.jpg";
        StorePath path = StorePath.parseFromUrl(filePath);
        assertNotNull(path);
        assertEquals(path.getGroup(), "group1");
        assertEquals(path.getPath(), "huex_sjuej/3hjshf.jpg");
    }


    /**
     * 可以从url解析文件路径(Bug)
     */
    @Test
    public void testParseFromUrl_FixBug() {
        String filePath = "testgroup1/M00/00/23/CgsCyFwXbRKACEiMAAAmhn9hIz402.xlsx";
        StorePath path = StorePath.parseFromUrl(filePath);
        assertNotNull(path);
        assertEquals(path.getGroup(), "testgroup1");
        assertEquals(path.getPath(), "M00/00/23/CgsCyFwXbRKACEiMAAAmhn9hIz402.xlsx");
    }


    /**
     * 不支持错误路径
     */
    @Test
    public void testParseFromUrlWithErr() {
        String filePath = "group1jshf.jpg";
        try {
            StorePath.parseFromUrl(filePath);
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsUnsupportStorePathException);
            LOGGER.debug(((FdfsUnsupportStorePathException) e).getMessage());
        }
    }

    /**
     * 路径地址必须包含group
     */
    @Test
    public void testParseFromUrlWithFullPathErr() {
        String filePath = "http://192.1.1.2/group1/jshf.jpg";
        StorePath path = StorePath.parseFromUrl(filePath);
        assertNotNull(path);
        assertEquals(path.getGroup(), "group1");
        assertEquals(path.getPath(), "jshf.jpg");
    }

}
