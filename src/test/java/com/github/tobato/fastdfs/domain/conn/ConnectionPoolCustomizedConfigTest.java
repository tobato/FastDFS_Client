package com.github.tobato.fastdfs.domain.conn;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * 测试参数化连接池配置
 *
 * @author Wuyf
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FastdfsTestApplication.class)
@ActiveProfiles(value = "customized_pool")
public class ConnectionPoolCustomizedConfigTest {
    @Autowired
    private ConnectionPoolConfig connectionPoolConfig;

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(ConnectionPoolCustomizedConfigTest.class);

    /**
     * 测试环境配置
     * <p>
     * <pre>
     * spring.profiles: customized_pool
     * fdfs:
     *   pool:
     *     maxTotal: 153
     *     maxWaitMillis: 102
     * </pre>
     */
    @Test
    public void testCustomizedConfigPool() {
        // 从池中借出的对象的最大数目
        LOGGER.debug("从池中借出的对象的最大数目={}", connectionPoolConfig.getMaxTotal());
        assertEquals(153, connectionPoolConfig.getMaxTotal());

        LOGGER.debug("获取连接时的最大等待毫秒数={}", connectionPoolConfig.getMaxWaitMillis());
        assertEquals(102, connectionPoolConfig.getMaxWaitMillis());
    }

}
