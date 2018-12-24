package com.github.tobato.fastdfs.domain.proto;

import java.io.InputStream;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.domain.proto.mapper.FdfsParamMapper;
import com.github.tobato.fastdfs.domain.proto.mapper.ObjectMetaData;

/**
 * Fdfs交易请求基类
 *
 * @author tobato
 */
public abstract class FdfsRequest {

    /**
     * 报文头
     */
    protected ProtoHead head;
    /**
     * 发送文件
     */
    protected InputStream inputFile;

    /**
     * 获取报文头(包内可见)
     *
     * @return
     */
    ProtoHead getHead() {
        return head;
    }

    /**
     * 获取报文头
     *
     * @param charset
     * @return
     */
    public byte[] getHeadByte(Charset charset) {
        // 设置报文长度
        head.setContentLength(getBodyLength(charset));
        // 返回报文byte
        return head.toByte();
    }

    /**
     * 打包参数
     *
     * @param charset
     * @return
     */
    public byte[] encodeParam(Charset charset) {
        return FdfsParamMapper.toByte(this, charset);
    }

    /**
     * 获取参数域长度
     *
     * @return
     */
    protected long getBodyLength(Charset charset) {
        ObjectMetaData objectMetaData = FdfsParamMapper.getObjectMap(this.getClass());
        return objectMetaData.getFieldsSendTotalByteSize(this, charset) + getFileSize();
    }

    public InputStream getInputFile() {
        return inputFile;
    }

    public long getFileSize() {
        return 0;
    }

}
