package com.github.tobato.fastdfs.domain.fdfs;

/**
 * 缩略图生成配置支持
 *
 * @author tobato
 */
public interface ThumbImageConfig {

    /**
     * 获得缩略图宽
     *
     * @return
     */
    int getWidth();

    /**
     * 获得缩略图高
     *
     * @return
     */
    int getHeight();

    /**
     * 获得缩略图前缀
     *
     * @return
     */
    String getPrefixName();

    /**
     * 获得缩略图路径
     *
     * @param masterFilename
     * @return
     */
    String getThumbImagePath(String masterFilename);

}
