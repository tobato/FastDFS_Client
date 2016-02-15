package com.github.tobato.fastdfs.exception;

/**
 * 上传图片例外
 * 
 * @author tobato
 *
 */
public class FdfsUploadImageException extends FdfsException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    protected FdfsUploadImageException(String message) {
        super(message);
    }

    public FdfsUploadImageException(String message, Throwable cause) {
        super(message, cause);
    }

}
