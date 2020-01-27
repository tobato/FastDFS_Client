package com.github.tobato.fastdfs.socket;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * 模拟socket处理类
 *
 * @author tobato
 */
public class FdfsMockHandler extends Thread {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FdfsMockHandler.class);

    private Socket client;

    DataInputStream in = null;

    private boolean stop = false;

    public FdfsMockHandler(Socket client) {
        this.client = client;
    }

    public void run() {

        try {
            while (!stop && client.isConnected()) {
                if (in == null) {
                    in = new DataInputStream(client.getInputStream());
                }
                // java客户端与服务端C使用数据流的方式进行通讯
                byte[] b = new byte[in.available()];
                for (int i = 0; i < b.length; i++) {
                    b[i] = (byte) in.read();
                }
                if (b.length > 0) {
                    // String s = new String(b);
                    LOGGER.debug("[MockHandler]接收到请求数据 data={}", b);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopHandler() {
        stop = true;
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(client);
    }
}
