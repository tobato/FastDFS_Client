package com.github.tobato.fastdfs.domain.upload;


import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import org.apache.commons.lang3.Validate;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * 构造FastFile抽象对象
 *
 * @author wuyf
 * @create 2018-12-24 11:08 AM
 */
public abstract class AbstractFastFileBuilder<T> {

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
    protected Set<MetaData> metaDataSet = new HashSet<>();

    /**
     * 上传文件分组
     */
    protected String groupName;

    /**
     * 上传文件
     *
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @return
     */
    public AbstractFastFileBuilder<T> withFile(InputStream inputStream, long fileSize, String fileExtName) {
        this.inputStream = inputStream;
        this.fileSize = fileSize;
        this.fileExtName = fileExtName;
        return this;
    }

    /**
     * 元数据信息
     *
     * @param name
     * @param value
     * @return
     */
    public AbstractFastFileBuilder<T> withMetaData(String name, String value) {
        this.metaDataSet.add(new MetaData(name, value));
        return this;
    }

    /**
     * 元数据信息
     *
     * @param metaDataSet
     * @return
     */
    public AbstractFastFileBuilder<T> withMetaData(Set<MetaData> metaDataSet) {
        Validate.notNull(metaDataSet, "元数据不能为空");
        this.metaDataSet.addAll(metaDataSet);
        return this;
    }

    /**
     * 上传至文件组
     *
     * @param groupName
     * @return
     */
    public AbstractFastFileBuilder<T> toGroup(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * 构造上传文件对象
     *
     * @return
     */
    public abstract T build();

}
