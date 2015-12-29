package com.github.tobato.fastdfs.cmd.tracker.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.StorageClient;
import com.github.tobato.fastdfs.cmd.FdfsResponse;
import com.github.tobato.fastdfs.cmd.mark.FdfsParamMapper;

/**
 * 列出分组信息执行结果
 * 
 * @author wuyf
 *
 */
public class TrackerGetStoreStorageResponse extends FdfsResponse<StorageClient> {

    /**
     * 解析反馈内容
     */
    @Override
    public StorageClient decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        byte[] bytes = new byte[(int) getContentLength()];
        int contentSize = in.read(bytes);
        // 此处fastdfs的服务端有bug
        if (contentSize != getContentLength()) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }
        return FdfsParamMapper.map(bytes, StorageClient.class, charset);
    }

}
