package com.github.tobato.fastdfs.cmd;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.socket.FdfsMockSocketServer;

public class RemoteServiceDefine {
    @SuppressWarnings("unused")
    private static String ip_home = "192.168.1.105";
    private static String ip_neusoft_home = "192.168.149.105";
    private static String ip_neusoft = "192.168.174.41";
    public static InetSocketAddress address = new InetSocketAddress(ip_neusoft, FdfsMockSocketServer.PORT);
    public static InetSocketAddress store_address = new InetSocketAddress(ip_neusoft, FdfsMockSocketServer.STORE_PORT);
    public static final int soTimeout = 550;
    public static final int connectTimeout = 500;
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

}
