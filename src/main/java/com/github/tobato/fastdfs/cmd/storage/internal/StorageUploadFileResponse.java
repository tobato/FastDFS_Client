package com.github.tobato.fastdfs.cmd.storage.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.StorePath;
import com.github.tobato.fastdfs.cmd.FdfsResponse;
import com.github.tobato.fastdfs.cmd.mark.FdfsParamMapper;

/**
 * 文件上传保存反馈结果
 * 
 * @author wuyf
 *
 */
public class StorageUploadFileResponse extends FdfsResponse<StorePath> {

    @Override
    public StorePath decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        byte[] bytes = new byte[(int) getContentLength()];
        int contentSize = in.read(bytes);
        // 获取数据
        if (contentSize != getContentLength()) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }
        return FdfsParamMapper.map(bytes, StorePath.class, charset);
    }

}
