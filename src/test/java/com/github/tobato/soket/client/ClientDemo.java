package com.github.tobato.soket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDemo {

    public static void main(String[] args) throws UnknownHostException, IOException {

        Socket s = new Socket("192.168.99.100", 22122);

        OutputStream out = s.getOutputStream();

        DataOutputStream dout = new DataOutputStream(out);

        // dout.writeUTF("oftenlin");

        InputStream in = s.getInputStream();
        DataInputStream din = new DataInputStream(in);

        String st = din.readUTF();

        in.close();
        out.close();
        s.close();
    }
}
