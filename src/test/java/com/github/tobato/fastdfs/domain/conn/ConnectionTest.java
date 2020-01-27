package com.github.tobato.fastdfs.domain.conn;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * socket连接测试
 *
 * @author tobato
 */
public class ConnectionTest extends MockServerTestBase {

    //@Test
    public void testClose() {
        // 创建连接
        Connection conn = createConnection();
        // 正常连接
        assertFalse(conn.isClosed());
        conn.close();
        assertTrue(conn.isClosed());
    }

    //@Test
    public void testCheck() {
        // 创建连接测试
        Connection conn = createConnection();
        LOGGER.debug("当前连接状态={}", conn.isValid());
        conn.close();
    }

    /**
     * 创建连接
     *
     * @return
     */
    private Connection createConnection() {
        return new DefaultConnection(address, soTimeout, connectTimeout, null);
    }

}
