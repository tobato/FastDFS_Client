package com.github.tobato.fastdfs.socket;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fdfs Socket manager
 * 
 * @author tobato
 *
 */
public class FdfsSocketService {

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(FdfsSocketService.class);

    /** 默认连接超时时间 */
    protected static final int DEFAULT_CONNECT_TIMEOUT = 5 * 1000;
    /** 默认read超时时间 */
    protected static final int DEFAULT_NETWORK_TIMEOUT = 30 * 1000;

    /** Socket获取策略 */
    private static final IBorrowSockectErrorPolicy Default_BorrowSockectErrorPolicy = new BorrowSockectErrorThrowPolicy();

    /**
     * 连接超时时间
     * 
     * <pre>
     * 单位：毫秒,应用与socket connect方法的参数
     * </pre>
     */
    private int connectTimeout;
    /**
     * read超时时间
     * 单位：毫秒，对应so_timeout参数
     */
    private int soTimeout;

    /** 连接池配置 */
    private FdfsPoolConfig poolConfig;
    /** 获取连接失败处理 */
    private IBorrowSockectErrorPolicy borrowSockectErrorPolicy;

    /**
     * 连接池
     * 
     * <pre>
     * 一个IP地址一个连接池
     * </pre>
     */
    private final Map<InetSocketAddress, FdfsConnectionPool> poolMapping = new ConcurrentHashMap<InetSocketAddress, FdfsConnectionPool>();

    /**
     * 初始化
     */
    public void init() {
        if (connectTimeout <= 0) {
            connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        }

        if (soTimeout <= 0) {
            soTimeout = DEFAULT_NETWORK_TIMEOUT;
        }

        if (borrowSockectErrorPolicy == null) {
            borrowSockectErrorPolicy = Default_BorrowSockectErrorPolicy;
        }

    }

    /**
     * 获取连接
     * 
     * @param address
     * @return
     */
    public FdfsConnection getSocket(InetSocketAddress address) {
        if (poolConfig == null) {
            return FdfsConnection.create(address, soTimeout, connectTimeout);
        }

        FdfsConnectionPool pool;
        synchronized (this) {
            pool = poolMapping.get(address);
            if (pool == null) {
                FdfsSocketFactory factory = new FdfsSocketFactory(address, soTimeout, connectTimeout);
                pool = new FdfsConnectionPool(factory, poolConfig);
                poolMapping.put(address, pool);
            }
        }
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            return borrowSockectErrorPolicy.handleWhenErrorOccur(pool, address, e);
        }
    }

    /**
     * 销毁方法，释放连接池
     */
    public void destroy() {

        synchronized (this) {
            for (FdfsConnectionPool pool : poolMapping.values()) {
                try {
                    pool.close();
                    LOGGER.debug("pool current size :" + pool.getNumActive() + "-" + pool.getNumIdle());
                } catch (Exception e) {
                    LOGGER.warn("destory pool error", e);
                }
            }
            poolMapping.clear();
        }
    }

    /**
     * @param connectTimeout
     *            the connectTimeout to set
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout * 1000;
    }

    /**
     * @param soTimeout
     *            the soTimeout to set
     */
    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout * 1000;
    }

    /**
     * @param poolConfig
     *            the poolConfig to set
     */
    public void setPoolConfig(FdfsPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    /**
     * @param borrowSockectErrorPolicy the borrowSockectErrorPolicy to set
     */
    public void setBorrowSockectErrorPolicy(IBorrowSockectErrorPolicy borrowSockectErrorPolicy) {
        this.borrowSockectErrorPolicy = borrowSockectErrorPolicy;
    }

}
