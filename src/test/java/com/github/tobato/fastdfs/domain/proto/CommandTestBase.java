package com.github.tobato.fastdfs.domain.proto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.conn.FdfsConnectionManager;
import com.github.tobato.fastdfs.domain.conn.FdfsConnectionPool;
import com.github.tobato.fastdfs.domain.conn.PooledConnectionFactory;

/**
 * command测试基类
 *
 * @author tobato
 */
public class CommandTestBase {
    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(CommandTestBase.class);

    /**
     * 连接池
     */
    protected FdfsConnectionManager manager = createConnectionManager();

    /**
     * 执行Tracker交易命令
     *
     * @param command
     * @return
     */
    protected <T> T executeTrackerCmd(FdfsCommand<T> command) {
        return manager.executeFdfsCmd(TestConstants.address, command);
    }

    /**
     * 执行存储交易命令
     *
     * @param command
     * @return
     */
    protected <T> T executeStoreCmd(FdfsCommand<T> command) {
        return manager.executeFdfsCmd(TestConstants.store_address, command);
    }

    private FdfsConnectionManager createConnectionManager() {
        return new FdfsConnectionManager(createPool());
    }

    private FdfsConnectionPool createPool() {
        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setConnectTimeout(TestConstants.connectTimeout);
        factory.setSoTimeout(TestConstants.soTimeout);
        return new FdfsConnectionPool(new PooledConnectionFactory());
    }

    /**
     * 导出pool信息
     */
    protected void dumpPool() {
        manager.dumpPoolInfo(TestConstants.store_address);
    }

}
