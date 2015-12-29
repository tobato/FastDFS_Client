package com.github.tobato.fastdfs.socket;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 连接对象测试类
 * 
 * @author tobato
 *
 */
public class FdfsConnectionTest {

    private static FdfsMockSocketServer socketServer = new FdfsMockSocketServer();
    private InetSocketAddress address = new InetSocketAddress(FdfsMockSocketServer.PORT);
    private int soTimeout = 50;
    private int connectTimeout = 50;

    @BeforeClass
    public static void startMockServer() {
        socketServer.start();
    }

    @AfterClass
    public static void stopMockServer() {
        socketServer.stopServer();
    }

    // @Test
    public void testClose() {
        fail("Not yet implemented");
    }

    @Test
    public void testCreate() throws IOException {
        // 创建连接测试
        FdfsConnection socketA = FdfsConnection.create(address, soTimeout, connectTimeout);

        assertTrue(socketA.isConnected());

        System.out.println("当前连接状态" + socketA.check());
        System.out.println("当前连接状态" + socketA.check());

        FdfsConnection socketB = FdfsConnection.create(address, soTimeout, connectTimeout);

        assertTrue(socketB.isConnected());

        socketA.close();
        socketB.close();

    }

    @Test
    public void testCheck() throws IOException {
        // 创建连接测试
        FdfsConnection socketA = FdfsConnection.create(address, soTimeout, connectTimeout);

        OutputStream out = socketA.getOutputStream();
        out.write("haha".getBytes());
        // 连接在使用的过程当中不能close
        // IOUtils.closeQuietly(out);
        // System.out.println("---------------open-----------");
        // OutputStream outb = socketA.getOutputStream();
        socketA.close();
    }

}
