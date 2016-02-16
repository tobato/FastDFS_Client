package com.github.tobato.fastdfs.service;

import java.io.InputStream;
import java.util.Set;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;

/**
 * 面向普通应用的文件操作接口封装
 * 
 * @author tobato
 *
 */
public interface FastFileStorageClient extends GenerateStorageClient {

    /**
     * 上传一般文件
     * 
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @param metaDataSet
     * @return
     */
    StorePath uploadFile(InputStream inputStream, long fileSize, String fileExtName, Set<MateData> metaDataSet);

    /**
     * 上传图片并且生成缩略图
     * 
     * <pre>
     * 支持的图片格式包括"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     * </pre>
     * 
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @param metaDataSet
     * @return
     */
    StorePath uploadImageAndCrtThumbImage(InputStream inputStream, long fileSize, String fileExtName,
            Set<MateData> metaDataSet);

    /**
     * 删除文件
     * 
     * @param filePath 文件路径(groupName/path)
     */
    void deleteFile(String filePath);

}
