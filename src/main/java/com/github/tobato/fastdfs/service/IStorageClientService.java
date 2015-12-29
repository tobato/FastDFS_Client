package com.github.tobato.fastdfs.service;

import java.io.InputStream;

import com.github.tobato.fastdfs.FileInfo;
import com.github.tobato.fastdfs.NameValuePair;
import com.github.tobato.fastdfs.StorePath;

public interface IStorageClientService {

    /**
     * 上传不可修改的文件
     * 
     * 
     * @param ins
     * @param size
     * @param ext
     * @return
     */
    StorePath uploadFile(String groupName, InputStream ins, long size, String ext);

    /**
     * 上传可修改的文件
     * 
     * 
     * @param ins
     * @param size
     * @param ext
     * @return
     */
    StorePath uploadAppenderFile(String groupName, InputStream ins, long size, String ext);

    StorePath uploadSlaveFile(String groupName, String masterFilename, InputStream ins, long size, String prefixName,
            String ext);

    /**
     * 可修改文件添加内容
     * 
     * 
     * @param path
     * @param ins
     * @param size
     */
    void appendFile(String groupName, String path, InputStream ins, long size);

    /**
     * 修改可修改文件的内容
     * 
     * 
     * @param groupName
     * @param path
     * @param offset
     * @param ins
     * @param size
     */
    void modifyFile(String groupName, String path, long offset, InputStream ins, long size);

    /**
     * 删除文件
     * 
     * 
     * @param groupName
     * @param path
     */
    void deleteFile(String groupName, String path);

    /**
     * 清除appender类型文件的内容
     * 
     * 
     * @param groupName
     * @param path
     */
    void truncateFile(String groupName, String path, long truncatedFileSize);

    /**
     * 下载整个文件
     * 
     * 
     * @param groupName
     * @param path
     * @param handling
     * @return
     */
    <T> T downloadFile(String groupName, String path, IFdfsFileInputStreamHandler<T> handling);

    /**
     * 下载文件片段
     * 
     * 
     * @param groupName
     * @param path
     * @param offset
     * @param size
     * @param handling
     * @return
     */
    <T> T downloadFile(String groupName, String path, long offset, long size, IFdfsFileInputStreamHandler<T> handling);

    /**
     * 获取文件元信息
     * 
     * 
     * @param groupName
     * @param path
     * @return
     */
    NameValuePair[] getMetadata(String groupName, String path);

    /**
     * 修改文件元信息（覆盖）
     * 
     * 
     * @param groupName
     * @param path
     * @param metaList
     */
    void overwriteMetadata(String groupName, String path, NameValuePair[] metaList);

    /**
     * 修改文件元信息（合并）
     * 
     * 
     * @param groupName
     * @param path
     * @param metaList
     */
    void mergeMetadata(String groupName, String path, NameValuePair[] metaList);

    /**
     * 查看文件的信息
     * 
     * 
     * @param groupName
     * @param path
     * @return
     */
    FileInfo queryFileInfo(String groupName, String path);
}
