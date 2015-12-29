package com.github.tobato.fastdfs.exception;

/**
 * 封装fastdfs的异常，使用运行时异常
 * 
 * @author yuqih
 * @author tobato
 * 
 */
public abstract class FdfsException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    protected FdfsException(String message) {
        super(message);
    }

    /**
     * @param message
     * @param cause
     */
    protected FdfsException(String message, Throwable cause) {
        super(message, cause);
    }

}
