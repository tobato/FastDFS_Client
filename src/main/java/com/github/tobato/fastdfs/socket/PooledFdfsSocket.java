package com.github.tobato.fastdfs.socket;

import java.io.IOException;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pooled FdfsSocket
 * 
 * <pre>
 * 定义了被池化对象的一些附加信息(创建时间，池中状态)
 * </pre>
 * 
 * @author tobato
 *
 */
public class PooledFdfsSocket extends FdfsConnection {

    private final Logger LOGGER = LoggerFactory.getLogger(PooledFdfsSocket.class);

    private GenericObjectPool<PooledFdfsSocket> pool;
    private boolean needDestroy;

    /**
     * @param pool
     */
    public PooledFdfsSocket(GenericObjectPool<PooledFdfsSocket> pool) {
        super();
        this.pool = pool;
    }

    /**
     * 当客户端关闭连接的时候状态设置为true(空闲）
     */
    @Override
    public synchronized void close() throws IOException {
        if (needDestroy) {
            try {
                pool.invalidateObject(this);
            } catch (Exception ignore) {
                LOGGER.warn("error occurs when invalidate socket in pool", ignore);
            }
        } else {
            try {
                pool.returnObject(this);
            } catch (Exception ignore) {
                LOGGER.warn("error occurs when return socket to pool", ignore);
            }
        }
    }

    protected synchronized void destroy() throws IOException {
        super.close();
    }

    /**
     * @return
     */
    protected boolean isNeedDestroy() {
        return needDestroy;
    }

    /**
     * @param needDestroy
     */
    public void setNeedDestroy(boolean needDestroy) {
        this.needDestroy = needDestroy;
    }

}
