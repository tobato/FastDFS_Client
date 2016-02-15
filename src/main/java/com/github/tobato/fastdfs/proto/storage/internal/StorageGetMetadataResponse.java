package com.github.tobato.fastdfs.proto.storage.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Set;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.proto.FdfsResponse;
import com.github.tobato.fastdfs.proto.mapper.MetadataMapper;

/**
 * 列出分组信息执行结果
 * 
 * @author tobato
 *
 */
public class StorageGetMetadataResponse extends FdfsResponse<Set<MateData>> {

    /**
     * 解析反馈内容
     */
    @Override
    public Set<MateData> decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        byte[] bytes = new byte[(int) getContentLength()];
        int contentSize = in.read(bytes);
        if (contentSize != getContentLength()) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }
        return MetadataMapper.fromByte(bytes, charset);

    }

}
