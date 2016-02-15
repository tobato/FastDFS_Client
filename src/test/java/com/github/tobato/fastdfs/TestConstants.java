package com.github.tobato.fastdfs;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.socket.FdfsMockSocketServer;

/**
 * 测试常量定义
 * 
 * @author tobato
 *
 */
@SuppressWarnings("unused")
public class TestConstants {
    private static String ip_home = "192.168.1.105";
    private static String ip_work = "192.168.174.47";
    private static String ip_work_store = "192.168.174.49";
    public static InetSocketAddress address = new InetSocketAddress(ip_work, FdfsMockSocketServer.PORT);
    public static InetSocketAddress store_address = new InetSocketAddress(ip_work_store,
            FdfsMockSocketServer.STORE_PORT);
    public static final int soTimeout = 550;
    public static final int connectTimeout = 500;
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public static final String DEFAULT_GROUP = "group1";
    public static final String DEFAULT_STORAGE_IP = ip_work_store;

    public static final String PERFORM_FILE_PATH = "/images/gs.jpg";
    public static final String CAT_IMAGE_FILE = "/images/cat.jpg";

}
