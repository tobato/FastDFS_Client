package com.github.tobato.fastdfs.domain.fdfs;

import org.junit.Test;

import java.net.InetSocketAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TrackerAddressHolder测试
 *
 * @author tobato
 */
public class TrackerAddressHolderTest {

    @Test
    public void testCanTryToConnect() throws InterruptedException {
        TrackerAddressHolder holder = new TrackerAddressHolder(new InetSocketAddress(9988));
        assertTrue(holder.isAvailable());
        holder.setInActive();
        assertFalse(holder.isAvailable());
        // 验证超时
        boolean canRetry = false;
        int i = 0;
        while (!canRetry) {
            Thread.sleep(2000);
            canRetry = holder.canTryToConnect(10);
            System.out.println("第" + (++i) + "次尝试重新连接..可尝试状态=" + canRetry);
        }

    }

}
