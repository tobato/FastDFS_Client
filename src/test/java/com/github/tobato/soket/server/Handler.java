package com.github.tobato.soket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("新连接:" + socket.getInetAddress() + ":" + socket.getPort());
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
                System.out.println("关闭连接:" + socket.getInetAddress() + ":" + socket.getPort());
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
