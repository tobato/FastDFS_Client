package com.github.tobato.fastdfs.exception;

/**
 * 从Url解析StorePath文件路径对象错误
 * 
 * @author wuyf
 *
 */
public class FdfsUnsupportStorePathException extends FdfsException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8116336411011152869L;

    public FdfsUnsupportStorePathException(String message) {
        super(message);
    }

}
