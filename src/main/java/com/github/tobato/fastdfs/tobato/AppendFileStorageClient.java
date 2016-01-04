package com.github.tobato.fastdfs.tobato;

import java.io.InputStream;

import com.github.tobato.fastdfs.domain.StorePath;

/**
 * 支持断点续传的文件服务接口
 * 
 * @author wuyf
 *
 */
public interface AppendFileStorageClient extends GenerateStorageClient {

    /**
     * 上传支持断点续传的文件
     * 
     * @param ins
     * @param size
     * @param ext
     * @return
     */
    StorePath uploadAppenderFile(String groupName, InputStream inputStream, long fileSize, String fileExtName);

    /**
     * 断点续传文件
     * 
     * @param groupName
     * @param path
     * @param inputStream
     * @param fileSize
     */
    void appendFile(String groupName, String path, InputStream inputStream, long fileSize);

    /**
     * 修改续传文件的内容
     * 
     * @param path
     * @param inputStream
     * @param fileSize
     * @param fileOffset
     */
    void modifyFile(String groupName, String path, InputStream inputStream, long fileSize, long fileOffset);

    /**
     * 清除续传类型文件的内容
     * 
     * @param path
     * @param truncatedFileSize
     */
    void truncateFile(String groupName, String path, long truncatedFileSize);

}
