package com.github.tobato.fastdfs.cmd;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Fdfs交易应答基类
 * 
 * @author wuyf
 *
 */
public abstract class FdfsResponse<T> {
    /** 报文头 */
    protected ProtoHead head;

    /** 获取报文长度 */
    protected long getContentLength() {
        return head.getContentLength();
    }

    /**
     * 解析反馈结果,head已经被解析过
     * 
     * @param head
     * @param in
     * @param charset
     * @return
     * @throws IOException
     */
    public T decode(ProtoHead head, InputStream in, Charset charset) throws IOException {
        this.head = head;
        return decodeContent(in, charset);
    }

    /**
     * 解析反馈内容
     * 
     * @param in
     * @param charset
     * @return
     * @throws IOException
     */
    public abstract T decodeContent(InputStream in, Charset charset) throws IOException;

}
