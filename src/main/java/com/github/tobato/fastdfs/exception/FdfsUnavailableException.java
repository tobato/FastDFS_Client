package com.github.tobato.fastdfs.exception;

/**
 * 非fastdfs本身的错误码抛出的异常，取服务端连接取不到时抛出的异常
 * 
 * @author yuqihuang
 * 
 */
public class FdfsUnavailableException extends FdfsException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public FdfsUnavailableException(String message) {
        super("无法获取服务端连接资源：" + message);
    }

    /**
     * @param message
     */
    public FdfsUnavailableException(String message, Throwable t) {
        super("无法获取服务端连接资源：" + message, t);
    }

}
