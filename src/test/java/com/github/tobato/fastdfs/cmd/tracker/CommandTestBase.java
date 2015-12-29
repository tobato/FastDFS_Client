package com.github.tobato.fastdfs.cmd.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tobato.fastdfs.cmd.FdfsCommand;
import com.github.tobato.fastdfs.cmd.RemoteServiceDefine;
import com.github.tobato.fastdfs.conn.ConnectionManager;
import com.github.tobato.fastdfs.conn.ConnectionPool;
import com.github.tobato.fastdfs.conn.PooledConnectionFactory;

/**
 * command测试基类
 * 
 * @author wuyf
 *
 */
public class CommandTestBase<T> {
    /** 日志 */
    protected static Logger LOGGER = LoggerFactory.getLogger(CommandTestBase.class);

    /**
     * 连接池
     */
    protected ConnectionManager manager = createConnectionManager();

    /**
     * 执行Tracker交易命令
     * 
     * @param command
     * @return
     */
    protected T executeTrackerCmd(FdfsCommand<T> command) {
        return manager.executeFdfsCmd(RemoteServiceDefine.address, command);
    }

    /**
     * 执行存储交易命令
     * 
     * @param command
     * @return
     */
    protected T executeStoreCmd(FdfsCommand<T> command) {
        return manager.executeFdfsCmd(RemoteServiceDefine.store_address, command);
    }

    private ConnectionManager createConnectionManager() {
        return new ConnectionManager(createPool());
    }

    private ConnectionPool createPool() {
        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setConnectTimeout(RemoteServiceDefine.connectTimeout);
        factory.setSoTimeout(RemoteServiceDefine.soTimeout);
        return new ConnectionPool(new PooledConnectionFactory());
    }

}
