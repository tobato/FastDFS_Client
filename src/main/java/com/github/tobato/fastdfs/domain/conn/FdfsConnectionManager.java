package com.github.tobato.fastdfs.domain.conn;

import com.github.tobato.fastdfs.domain.proto.FdfsCommand;
import com.github.tobato.fastdfs.exception.FdfsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * 连接池管理
 * <pre>
 * 负责借出连接，在连接上执行业务逻辑，然后归还连接
 *
 * FdfsConnectionManager类主要负责StorageConnect连接管理
 * FdfsConnectionManager类扩展的子类{@link TrackerConnectionManager}主要负责TrackerConnection连接管理
 * </pre>
 *
 * @author tobato
 */
@Component
public class FdfsConnectionManager {

    /**
     * 连接池
     */
    @Autowired
    private FdfsConnectionPool pool;
    /**
     * 日志
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(FdfsConnectionManager.class);

    /**
     * 构造函数
     */
    public FdfsConnectionManager() {
        super();
    }

    /**
     * 构造函数
     *
     * @param pool
     */
    public FdfsConnectionManager(FdfsConnectionPool pool) {
        super();
        this.pool = pool;
    }

    /**
     * 获取连接并执行交易
     *
     * @param address
     * @param command
     * @return
     */
    public <T> T executeFdfsCmd(InetSocketAddress address, FdfsCommand<T> command) {

        // 获取连接
        Connection conn = getConnection(address);
        // 执行交易
        return execute(address, conn, command);

    }

    /**
     * 执行交易
     *
     * @param conn
     * @param command
     * @return
     */
    protected <T> T execute(InetSocketAddress address, Connection conn, FdfsCommand<T> command) {
        boolean isException = false;
        try {
            // 执行交易
            LOGGER.debug("对地址{}发出交易请求{}", address, command.getClass().getSimpleName());
            return command.execute(conn);
        } catch (FdfsException e) {
            LOGGER.error("execute fdfs command error", e);
            isException = true;
            throw e;
        } catch (Exception e) {
            LOGGER.error("execute fdfs command exception", e);
            isException = true;
            throw new RuntimeException("execute fdfs command error", e);
        } finally {
            if (isException) {
                //移除连接
                LOGGER.debug("remove connect {}", conn);
                removeConnect(address, conn);
            } else {
                //归还连接
                LOGGER.debug("return connect {}", conn);
                returnConnect(address, conn);
            }

        }
    }

    /**
     * 出现例外时从连接池移除连接
     *
     * @param address
     * @param conn
     */
    private void removeConnect(InetSocketAddress address, Connection conn) {
        try {
            if (null != conn) {
                //移除pool
                pool.invalidateObject(address, conn);
            }
        } catch (Exception e) {
            LOGGER.error("remove pooled connection error", e);
        }
    }

    /**
     * 归还连接
     *
     * @param address
     * @param conn
     */
    private void returnConnect(InetSocketAddress address, Connection conn) {
        try {
            if (null != conn) {
                //归还连接
                pool.returnObject(address, conn);
            }
        } catch (Exception e) {
            LOGGER.error("return pooled connection error", e);
        }
    }

    /**
     * 获取连接
     *
     * @param address
     * @return
     */
    protected Connection getConnection(InetSocketAddress address) {
        Connection conn = null;
        try {
            // 获取连接
            conn = pool.borrowObject(address);
            //dumpPoolInfo(address);
        } catch (FdfsException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unable to borrow buffer from pool", e);
            throw new RuntimeException("Unable to borrow buffer from pool", e);
        }
        return conn;
    }

    public FdfsConnectionPool getPool() {
        return pool;
    }

    public void setPool(FdfsConnectionPool pool) {
        this.pool = pool;
    }

    /**
     * 打印连接池情况
     *
     * @param address
     */
    public void dumpPoolInfo(InetSocketAddress address) {

        LOGGER.debug("==============Begin Dump Pool Info==========");
        LOGGER.debug("Address={}", address);
        LOGGER.debug("连接池最大连接数配置{}", pool.getMaxTotal());
        LOGGER.debug("每个Key最大连接数配置{}", pool.getMaxTotalPerKey());
        LOGGER.debug("每个key对应连接池最大空闲连接数{}", pool.getMaxIdlePerKey());
        LOGGER.debug("每个key对应连接池最小空闲连接数{}", pool.getMinIdlePerKey());
        LOGGER.debug("活动连接{}", pool.getNumActive(address));
        LOGGER.debug("空闲连接{}", pool.getNumIdle(address));
        LOGGER.debug("获取前测试连接状态{}", pool.getTestOnBorrow());
        LOGGER.debug("归还前测试连接状态{}", pool.getTestOnReturn());
        LOGGER.debug("空闲时测试连接状态{}", pool.getTestWhileIdle());
        LOGGER.debug("连接获取总数统计{}", pool.getBorrowedCount());
        LOGGER.debug("连接返回总数统计{}", pool.getReturnedCount());
        LOGGER.debug("连接销毁总数统计{}", pool.getDestroyedCount());
        LOGGER.debug("JmxName={}", pool.getJmxName());
        LOGGER.debug("==============END Dump Pool ================");
    }

    public void dumpFullPoolInfo() {
        //pool.get
    }

}
