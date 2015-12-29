package com.github.tobato.fastdfs.cmd.storage.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.cmd.FdfsResponse;

/**
 * 文件上传保存反馈结果
 * 
 * @author wuyf
 *
 */
public class StorageDeleteFileResponse extends FdfsResponse<Void> {

    @Override
    public Void decodeContent(InputStream in, Charset charset) throws IOException {
        return null;
    }

}
