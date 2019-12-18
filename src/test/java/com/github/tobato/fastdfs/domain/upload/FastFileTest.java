package com.github.tobato.fastdfs.domain.upload;

import com.github.tobato.fastdfs.domain.RandomTextFile;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;

import static junit.framework.TestCase.*;

/**
 * FastFile单元测试
 */
public class FastFileTest {


    /**
     * 元数据不能为空
     *
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testBuildMateDataNull() throws Exception {
        RandomTextFile file = new RandomTextFile();
        FastFile fastFile = new FastFile.Builder()
                .withFile(file.getInputStream(), file.getFileSize(), file.getFileExtName())
                .withMetaData(null)
                .build();
    }

    /**
     * 元数据不能为空
     *
     * @throws Exception
     */
    @Test
    public void testBuildMateDataNotSet() throws Exception {
        RandomTextFile file = new RandomTextFile();
        FastFile fastFile = new FastFile(file.getInputStream(),
                file.getFileSize(),
                file.getFileExtName(),
                null);
        assertNotNull(fastFile.getFileExtName());

        assertEquals(fastFile.getMetaDataSet(), Collections.EMPTY_SET);
    }

    /**
     * 元数据可以不存在
     *
     * @throws Exception
     */
    @Test
    public void testBuildMateDataEmpty() throws Exception {
        RandomTextFile file = new RandomTextFile();
        FastFile fastFile = new FastFile.Builder()
                .withFile(file.getInputStream(), file.getFileSize(), file.getFileExtName())
                .withMetaData(new HashSet<MetaData>())
                .build();
        assertTrue(fastFile.getMetaDataSet().isEmpty());
    }

    /**
     * 元数据可指定
     *
     * @throws Exception
     */
    @Test
    public void testBuildMateDataWithString() throws Exception {
        RandomTextFile file = new RandomTextFile();
        FastFile fastFile = new FastFile.Builder()
                .withFile(file.getInputStream(), file.getFileSize(), file.getFileExtName())
                .withMetaData("hello", "world")
                .build();
        assertTrue(fastFile.getMetaDataSet().contains(new MetaData("hello", "world")));
    }

}