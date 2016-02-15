package com.github.tobato.fastdfs.proto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.core.GenericTypeResolver;

import com.github.tobato.fastdfs.proto.mapper.FdfsParamMapper;

/**
 * Fdfs交易应答基类
 * 
 * @author tobato
 *
 */
public abstract class FdfsResponse<T> {
    /** 报文头 */
    protected ProtoHead head;

    /** 返回值类型 */
    protected final Class<T> genericType;

    /** 获取报文长度 */
    protected long getContentLength() {
        return head.getContentLength();
    }

    /**
     * 构造函数
     * 
     * @param genericType
     */
    @SuppressWarnings("unchecked")
    public FdfsResponse() {
        super();
        this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), FdfsResponse.class);
        // Type theclass = this.getClass().getGenericSuperclass();
        // this.genericType = ((ParameterizedType)
        // theclass).getActualTypeArguments()[0];
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
    public T decodeContent(InputStream in, Charset charset) throws IOException {
        // 如果有内容
        if (getContentLength() > 0) {
            byte[] bytes = new byte[(int) getContentLength()];
            int contentSize = in.read(bytes);
            // 获取数据
            if (contentSize != getContentLength()) {
                throw new IOException("读取到的数据长度与协议长度不符");
            }
            return FdfsParamMapper.map(bytes, genericType, charset);
        }
        return null;
    }

}
