package com.github.tobato.fastdfs.domain.fdfs;

import org.apache.commons.lang3.Validate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.FdfsClientConstants;

/**
 * 缩略图配置参数
 *
 * @author tobato
 */
@Component
@ConfigurationProperties(prefix = FdfsClientConstants.THUMB_IMAGE_CONFIG_PREFIX)
public class DefaultThumbImageConfig implements ThumbImageConfig {

    private int width;

    private int height;

    private static String cachedPrefixName;

    /**
     * 生成前缀如:_150x150
     */
    @Override
    public String getPrefixName() {
        if (cachedPrefixName == null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append("_").append(width).append("x").append(height);
            cachedPrefixName = new String(buffer);
        }
        return cachedPrefixName;
    }

    /**
     * 根据文件名获取缩略图路径
     */
    @Override
    public String getThumbImagePath(String masterFilename) {
        Validate.notBlank(masterFilename, "主文件不能为空");
        StringBuilder buff = new StringBuilder(masterFilename);
        int index = buff.lastIndexOf(".");
        buff.insert(index, getPrefixName());
        return buff.toString();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
