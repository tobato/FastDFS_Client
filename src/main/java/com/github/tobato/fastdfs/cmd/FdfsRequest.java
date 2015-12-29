package com.github.tobato.fastdfs.cmd;

import java.io.InputStream;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.cmd.mark.FdfsParamMapper;
import com.github.tobato.fastdfs.cmd.mark.ObjectMateData;

/**
 * Fdfs交易请求基类
 * 
 * @author wuyf
 *
 */
public abstract class FdfsRequest {

    /** 报文头 */
    protected ProtoHead head;
    /** 发送文件 */
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

    public byte[] encodeParam(Charset charset) {
        return FdfsParamMapper.toByte(this, charset);
    }

    /**
     * 获取参数域长度
     * 
     * @return
     */
    protected long getBodyLength(Charset charset) {
        ObjectMateData objectMateData = FdfsParamMapper.getObjectMap(this.getClass());
        return objectMateData.getFieldsSendTotalByteSize(this, charset) + getFileSize();
    }

    public InputStream getInputFile() {
        return inputFile;
    }

    public long getFileSize() {
        return 0;
    }

}
