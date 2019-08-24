package com.github.tobato.soket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class Handler implements Runnable {
    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            LOGGER.debug("新连接={}:{}", socket.getInetAddress(), socket.getPort());
            // Thread.sleep(10000);
            InputStream in = socket.getInputStream();
            DataInputStream din = new DataInputStream(in);
            String name = din.readUTF();
            OutputStream out = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeUTF(socket + "your name :" + name);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                LOGGER.debug("关闭连接={}:{}", socket.getInetAddress(), socket.getPort());
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
