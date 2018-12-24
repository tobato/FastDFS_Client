package com.github.tobato.fastdfs;

/**
 * FDFSClient常量配置
 *
 * @author tobato
 */
public class FdfsClientConstants {

    private FdfsClientConstants() {
        // hide for constants
    }

    /**
     * 支持图片类型
     */
    public static final String[] SUPPORT_IMAGE_TYPE = {"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"};

    /**
     * 配置文件前缀
     */
    public static final String ROOT_CONFIG_PREFIX = "fdfs";

    /**
     * 缩略图配置
     */
    public static final String THUMB_IMAGE_CONFIG_PREFIX = "fdfs.thumb-image";

    /**
     * 连接池配置
     */
    public static final String POOL_CONFIG_PREFIX = "fdfs.pool";

}
