package com.github.tobato.fastdfs.domain.conn;

import org.junit.Test;

import java.net.InetSocketAddress;

import static org.junit.Assert.assertFalse;

/**
 * 连接池创建测试
 *
 * @author tobato
 */
public class ConnectionPoolTest extends MockServerTestBase {

    /**
     * 验证如何使用连接池
     */
    @Test
    public void testPoolUsage() {
        FdfsConnectionPool pool = createPool();
        try {
            // 获取连接
            Connection connA = pool.borrowObject(address);
            // 验证获取到连接
            assertFalse(connA.isClosed());
            // 连接使用完毕归还池
            pool.returnObject(address, connA);
            // 清理连接
            pool.clear(address);
            LOGGER.debug("连接测试情况={}", pool.getTestOnBorrow());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPoolStatus() {
        FdfsConnectionPool pool = createPool();
        try {
            // 获取连接
            printPoolStates("未获取前", address, pool);
            //pool.setTestOnBorrow(true);
            Connection connA = pool.borrowObject(address);
            printPoolStates("获取一个连接", address, pool);
            // 获取第二个连接
            Connection connB = pool.borrowObject(address);
            printPoolStates("获取二个连接", address, pool);
            // 返回第一个连接
            pool.returnObject(address, connA);
            printPoolStates("归还第一个连接", address, pool);
            pool.returnObject(address, connB);
            printPoolStates("归还第二个连接", address, pool);

            // 再获取一个连接
            Connection connC = pool.borrowObject(address);
            printPoolStates("获取第三个连接", address, pool);

            // 第三个连接在使用当中发生错误，需要销毁
            pool.invalidateObject(address, connC);
            printPoolStates("第三个连接在使用当中发生错误，需要销毁", address, pool);

            // 清理连接池
            pool.clear(address);
            // printPoolStates("清理连接池", address, pool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FdfsConnectionPool createPool() {
        return new FdfsConnectionPool(new PooledConnectionFactory());
    }

    private void printPoolStates(String msg, InetSocketAddress address, FdfsConnectionPool pool) {
        LOGGER.debug("=============={}================", msg);
        LOGGER.debug("活动连接{}", pool.getNumActive(address));
        LOGGER.debug("空闲连接{}", pool.getNumIdle(address));
        LOGGER.debug("连接获取总数统计{}", pool.getBorrowedCount());
        LOGGER.debug("连接返回总数统计{}", pool.getReturnedCount());
        LOGGER.debug("连接销毁总数统计{}", pool.getDestroyedCount());
    }

}
