package com.github.tobato.fastdfs.service;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.TestUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 缩略图算法测试
 *
 * @author tobato
 */
public class ThumbImageTest {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * PNG生成缩略图背景为黑色问题
     *
     * @throws IOException
     */
    @Test
    public void testImage() throws IOException {
        InputStream in = TestUtils.getFileInputStream(TestConstants.FLY_IMAGE_FILE);
        File file = new File("test.png");
        OutputStream out = new FileOutputStream(file);
        Thumbnails
                .of(in)
                .size(200, 200)
                .imageType(BufferedImage.TYPE_INT_ARGB)  //不加入这句就是黑色
                .toOutputStream(out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }


}
