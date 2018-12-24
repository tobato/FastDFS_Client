package com.github.tobato.fastdfs.domain.upload;


import com.github.tobato.fastdfs.domain.fdfs.MetaData;

import java.io.InputStream;
import java.util.Set;

/**
 * 上传图片文件
 *
 * @author tobato
 * @create 2018-12-23 3:06 PM
 */
public class FastImageFile extends FastFile {

    /**
     * 图片配置
     */
    private ThumbImage thumbImage;

    /**
     * 上传图片文件
     *
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @param metaDataSet
     */
    public FastImageFile(InputStream inputStream, long fileSize, String fileExtName, Set<MetaData> metaDataSet) {
        super(inputStream, fileSize, fileExtName, metaDataSet);
    }

    /**
     * 上传图片文件
     *
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @param metaDataSet
     * @param thumbImage
     */
    public FastImageFile(InputStream inputStream, long fileSize, String fileExtName, Set<MetaData> metaDataSet, ThumbImage thumbImage) {
        super(inputStream, fileSize, fileExtName, metaDataSet);
        this.thumbImage = thumbImage;
    }

    private FastImageFile() {
        //for build
    }

    public ThumbImage getThumbImage() {
        return thumbImage;
    }

    /**
     * 获取缩略图路径
     *
     * @param masterFilename
     * @return
     */
    public String getThumbImagePath(String masterFilename) {
        return thumbImage.getThumbImagePath(masterFilename);
    }

    /**
     * 构造模式
     */
    public static class Builder extends AbstractFastFileBuilder<FastImageFile> {

        private ThumbImage thumbImage;

        @Override
        public Builder toGroup(String groupName) {
            super.toGroup(groupName);
            return this;
        }

        @Override
        public Builder withFile(InputStream inputStream, long fileSize, String fileExtName) {
            super.withFile(inputStream, fileSize, fileExtName);
            return this;

        }

        @Override
        public Builder withMetaData(String name, String value) {
            super.withMetaData(name, value);
            return this;
        }

        @Override
        public Builder withMetaData(Set<MetaData> metaDataSet) {
            super.withMetaData(metaDataSet);
            return this;
        }

        /**
         * 按默认方式生成缩略图
         *
         * @return
         */
        public Builder withThumbImage() {
            this.thumbImage = new ThumbImage();
            return this;
        }

        /**
         * 缩略图配置
         *
         * @param width
         * @param height
         * @return
         */
        public Builder withThumbImage(int width, int height) {
            this.thumbImage = new ThumbImage(width, height);
            return this;
        }


        /**
         * 缩放比例配置
         *
         * @param percent
         * @return
         */
        public Builder withThumbImage(double percent) {
            this.thumbImage = new ThumbImage(percent);
            return this;
        }

        /**
         * 构造上传文件对象
         *
         * @return
         */
        @Override
        public FastImageFile build() {
            FastImageFile file = new FastImageFile();
            file.inputStream = this.inputStream;
            file.fileExtName = this.fileExtName;
            file.fileSize = this.fileSize;
            file.metaDataSet = this.metaDataSet;
            file.thumbImage = this.thumbImage;
            file.groupName = this.groupName;
            return file;
        }

    }
}
