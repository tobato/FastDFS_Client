package com.github.tobato.fastdfs.domain.conn;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * 测试参数化连接池配置
 *
 * @author Wuyf
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FastdfsTestApplication.class)
public class ConnectionPoolConfigTest {
    @Autowired
    private ConnectionPoolConfig connectionPoolConfig;

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(ConnectionPoolConfigTest.class);

    /**
     * 默认配置
     */
    @Test
    public void testDefaultConfigPool() {
        // 从池中借出的对象的最大数目
        LOGGER.debug("从池中借出的对象的最大数目={}", connectionPoolConfig.getMaxTotal());
        assertEquals(ConnectionPoolConfig.FDFS_MAX_TOTAL, connectionPoolConfig.getMaxTotal());

        LOGGER.debug("获取连接时的最大等待毫秒数={}", connectionPoolConfig.getMaxWaitMillis());
        assertEquals(ConnectionPoolConfig.FDFS_MAX_WAIT_MILLIS, connectionPoolConfig.getMaxWaitMillis());
    }

}
