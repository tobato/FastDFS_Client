package com.github.tobato.fastdfs.proto.storage.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.proto.FdfsResponse;
import com.github.tobato.fastdfs.socket.FdfsInputStream;

/**
 * 文件下载结果
 * 
 * @author wuyf
 *
 */
public class StorageDownloadResponse extends FdfsResponse<FdfsInputStream> {

    /**
     * 解析反馈内容
     */
    @Override
    public FdfsInputStream decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        return new FdfsInputStream(in, getContentLength());
    }

}
