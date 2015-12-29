package com.github.tobato.fastdfs.exception;

/**
 * 非fastdfs本身的错误码抛出的异常，而是java客户端向服务端发送命令、文件或从服务端读取结果、下载文件时发生io异常
 * 
 * @author yuqihuang
 * @author tobato
 * 
 */
public class FdfsIOException extends FdfsException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param cause
     */
    public FdfsIOException(Throwable cause) {
        super("客户端连接服务端出现了io异常", cause);
    }

    /**
     * @param message
     * @param cause
     */
    public FdfsIOException(String messge, Throwable cause) {
        super("客户端连接服务端出现了io异常:" + messge, cause);
    }

    public FdfsIOException(String message) {
        super(message);
    }

}
