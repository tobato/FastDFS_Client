package com.github.tobato.fastdfs.domain.upload;


import com.github.tobato.fastdfs.domain.fdfs.MetaData;

import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

/**
 * 上传普通文件
 *
 * @author tobato
 * @create 2018-12-23 3:06 PM
 */
public class FastFile {

    /**
     * 输入流
     */
    protected InputStream inputStream;
    /**
     * 文件大小
     */
    protected long fileSize;
    /**
     * 文件扩展名
     */
    protected String fileExtName;
    /**
     * 文件元数据
     */
    protected Set<MetaData> metaDataSet;

    /**
     * 上传文件分组
     */
    protected String groupName;

    /**
     * 构造模式
     */
    public static class Builder extends AbstractFastFileBuilder<FastFile> {

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

        @Override
        public Builder toGroup(String groupName) {
            super.toGroup(groupName);
            return this;
        }

        /**
         * 构造上传文件对象
         *
         * @return
         */
        public FastFile build() {
            FastFile file = new FastFile();
            file.inputStream = this.inputStream;
            file.fileExtName = this.fileExtName;
            file.fileSize = this.fileSize;
            file.metaDataSet = this.metaDataSet;
            file.groupName = this.groupName;
            return file;
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFileExtName() {
        return fileExtName;
    }

    public Set<MetaData> getMetaDataSet() {
        return metaDataSet != null ? Collections.unmodifiableSet(metaDataSet) : Collections.EMPTY_SET;
    }

    public String getGroupName() {
        return groupName;
    }

    /**
     * 上传文件对象
     *
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @param metaDataSet
     */
    public FastFile(InputStream inputStream, long fileSize,
                    String fileExtName, Set<MetaData> metaDataSet) {
        this.inputStream = inputStream;
        this.fileSize = fileSize;
        this.fileExtName = fileExtName;
        this.metaDataSet = metaDataSet;
    }

    protected FastFile() {
        //for build
    }
}
