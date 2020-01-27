package com.github.tobato.fastdfs.domain.conn;

import com.github.tobato.fastdfs.domain.fdfs.GroupState;
import com.github.tobato.fastdfs.domain.proto.tracker.TrackerListGroupsCommand;
import com.github.tobato.fastdfs.exception.FdfsConnectException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 验证连接池管理
 *
 * @author tobato
 */
public class TrackerConnectionManagerTest {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(TrackerConnectionManagerTest.class);

    /**
     * 会对tracker连接地址列表进行轮询
     */
    @Test
    public void testConnectionManager() {
        // 初始化
        TrackerConnectionManager manager = crtInvalidateIpListManager();
        List<GroupState> list = null;
        // 第一次执行
        try {
            // 连接失败
            list = manager.executeFdfsTrackerCmd(new TrackerListGroupsCommand());
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsConnectException);
        }

        // 第二次执行
        try {
            // 连接失败
            list = manager.executeFdfsTrackerCmd(new TrackerListGroupsCommand());
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsConnectException);
        }

        assertNull(list);
    }

    /**
     * 创建无效的IP地址列表连接管理
     *
     * @return
     */
    private TrackerConnectionManager crtInvalidateIpListManager() {
        String[] ips = {"192.168.1.1:22122", "192.168.1.115:212"};
        List<String> trackerIpList = Arrays.asList(ips);
        TrackerConnectionManager manager = new TrackerConnectionManager(createPool());
        manager.setTrackerList(trackerIpList);
        manager.initTracker();
        return manager;
    }

    /**
     * 创建连接池
     *
     * @return
     */
    private FdfsConnectionPool createPool() {
        PooledConnectionFactory factory = new PooledConnectionFactory();
        //缩短连接超时时间
        factory.setConnectTimeout(2);
        factory.setSoTimeout(2);
        return new FdfsConnectionPool(factory);
    }


}
