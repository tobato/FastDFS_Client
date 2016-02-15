package com.github.tobato.fastdfs.domain;

import static org.junit.Assert.*;

import java.net.InetSocketAddress;

import org.junit.Test;

/**
 * TrackerAddressHolder测试
 * 
 * @author tobato
 *
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
