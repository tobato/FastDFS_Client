package com.github.tobato.fastdfs.domain.fdfs;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TrackerAddressHolder测试
 *
 * @author tobato
 */
public class TrackerAddressHolderTest {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(TrackerAddressHolderTest.class);

    @Test
    public void testCanTryToConnect() throws InterruptedException {
        TrackerAddressHolder holder = new TrackerAddressHolder(new InetSocketAddress(9988));
        assertTrue(holder.isAvailable());
        //连接设置为无效
        holder.setInActive();
        assertFalse(holder.isAvailable());
        // 验证超时10秒以后，可以重新尝试连接
        boolean canRetry = false;
        int i = 0;
        while (!canRetry) {
            i = i + 1;
            Thread.sleep(2000);
            canRetry = holder.canTryToConnect(10);
            LOGGER.debug("第{}次尝试重新连接..可尝试状态={}", i, canRetry);
        }

    }

}
