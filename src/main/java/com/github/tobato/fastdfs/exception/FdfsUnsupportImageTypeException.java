package com.github.tobato.fastdfs.exception;

/**
 * 不支持的图片格式
 * 
 * @author tobato
 *
 */
public class FdfsUnsupportImageTypeException extends FdfsException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8498179372343498770L;

    public FdfsUnsupportImageTypeException(String message) {
        super(message);
    }

}
