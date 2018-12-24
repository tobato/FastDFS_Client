package com.github.tobato.fastdfs.domain.proto.storage;

import java.io.IOException;
import java.io.InputStream;

/**
 * FdfsInputStream包装类
 *
 * @author tobato
 */
public class FdfsInputStream extends InputStream {

    private final InputStream ins;
    private final long size;
    private long remainByteSize;

    public FdfsInputStream(InputStream ins, long size) {
        this.ins = ins;
        this.size = size;
        remainByteSize = size;
    }

    @Override
    public int read() throws IOException {
        return ins.read();
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        if (remainByteSize == 0) {
            return -1;
        }
        int byteSize = ins.read(b, off, len);
        if (remainByteSize < byteSize) {
            throw new IOException("协议长度" + size + "与实际长度不符");
        }

        remainByteSize -= byteSize;
        return byteSize;
    }

    @Override
    public void close() throws IOException {
        // do nothing
    }

    /**
     * 是否已完成读取
     *
     * @return
     */
    public boolean isReadCompleted() {
        return remainByteSize == 0;
    }

}
