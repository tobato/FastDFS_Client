package com.github.tobato.fastdfs.domain.fdfs;

import com.github.tobato.fastdfs.exception.FdfsUnavailableException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * TrackerLocator创建
 *
 * @author tobato
 */
public class TrackerLocatorTest {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(TrackerLocatorTest.class);

    private String[] ips = {"192.168.174.47:22122", "192.168.1.105:22122"};
    private List<String> trackerIpList = Arrays.asList(ips);

    @Test
    public void testTrackerLocator() {
        // 创建Locator
        TrackerLocator locator = new TrackerLocator(trackerIpList);
        assertFalse(locator.getTrackerList().isEmpty());
        // 获取一个连接地址
        InetSocketAddress addressA = getAddress(locator, "获取地址A");
        InetSocketAddress addressB = getAddress(locator, "获取地址B");
        // 地址轮询
        assertNotEquals(addressA, addressB);
        // IF 连接断开
        locator.setInActive(addressA);
        InetSocketAddress addressC = getAddress(locator, "获取地址C");
        assertNotEquals(addressA, addressC);
        // 只剩一个地址
        assertEquals(addressB, addressC);
        // 连接恢复
        locator.setActive(addressA);

        // 获取连接
        InetSocketAddress addressD = getAddress(locator, "获取地址D");
        assertEquals(addressA, addressD);
        locator.setInActive(addressA);
        locator.setInActive(addressB);

        try {
            // 无连接可以获取
            getAddress(locator, "获取地址E");
            fail("No exception thrown.");
        } catch (Exception e) {
            assertTrue(e instanceof FdfsUnavailableException);
        }
    }

    /**
     * 获取一个连接地址
     *
     * @param msg
     * @param locator
     * @return
     */
    private InetSocketAddress getAddress(TrackerLocator locator, String msg) {
        InetSocketAddress address = locator.getTrackerAddress();
        assertNotNull(address);
        LOGGER.debug(msg + ":{}", address);
        return address;
    }

}
