package com.github.tobato.fastdfs.domain.conn;

import com.github.tobato.fastdfs.FdfsClientConstants;
import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * pooled FdfsSocket factory
 * <p>
 * <pre>
 * 定义了被池化的对象的创建，初始化，激活，钝化以及销毁功能
 * </per>
 *
 * @author tobato
 */
@Component
@ConfigurationProperties(prefix = FdfsClientConstants.ROOT_CONFIG_PREFIX)
public class PooledConnectionFactory extends BaseKeyedPooledObjectFactory<InetSocketAddress, Connection> {

    /**
     * 读取时间
     */
    private int soTimeout;
    /**
     * 连接超时时间
     */
    private int connectTimeout;
    /**
     * 字符集
     */
    private Charset charset;
    /**
     * 默认字符集
     */
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    /**
     * 设置默认字符集
     */
    private String charsetName = DEFAULT_CHARSET_NAME;

    /**
     * 创建连接
     */
    @Override
    public Connection create(InetSocketAddress address) throws Exception {
        // 初始化字符集
        if (null == charset) {
            charset = Charset.forName(charsetName);
        }
        return new DefaultConnection(address, soTimeout, connectTimeout, charset);
    }

    /**
     * 将对象池化pooledObject
     */
    @Override
    public PooledObject<Connection> wrap(Connection conn) {
        return new DefaultPooledObject<Connection>(conn);
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Charset getCharset() {
        return charset;
    }

    /**
     * 从池中移出
     *
     * @param key
     * @param p
     * @throws Exception
     */
    @Override
    public void destroyObject(InetSocketAddress key, PooledObject<Connection> p) throws Exception {
        p.getObject().close();
    }

    /***
     * 验证池中对象是否可用
     * @param key
     * @param p
     * @return
     */
    @Override
    public boolean validateObject(InetSocketAddress key, PooledObject<Connection> p) {
        return p.getObject().isValid();
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

}
