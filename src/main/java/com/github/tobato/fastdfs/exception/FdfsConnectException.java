package com.github.tobato.fastdfs.exception;

/**
 * 非fastdfs本身的错误码抛出的异常，socket连不上时抛出的异常
 * 
 * @author yuqihuang
 * @author tobato
 * 
 */
public class FdfsConnectException extends FdfsUnavailableException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param message
     */
    public FdfsConnectException(String message, Throwable t) {
        super(message, t);
    }

}
