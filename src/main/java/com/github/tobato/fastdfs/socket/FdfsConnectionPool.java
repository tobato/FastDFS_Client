package com.github.tobato.fastdfs.socket;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 定义Fdfs连接池对象
 * 
 * <pre>
 * 定义了对象池要实现的功能
 * </pre>
 * 
 * @author tobato
 *
 */
class FdfsConnectionPool extends GenericObjectPool<PooledFdfsSocket> {

    /**
     * 默认构造函数
     * 
     * @param factory
     * @param config
     */
    public FdfsConnectionPool(FdfsSocketFactory factory, GenericObjectPoolConfig config) {
        super(factory, config);
        factory.setPool(this);
    }

}
