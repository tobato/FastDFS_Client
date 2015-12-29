package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class TrackerDeleteStorageHandler extends AbstractHandler<Void> {

    private static final byte cmd = CmdConstants.TRACKER_PROTO_CMD_SERVER_DELETE_STORAGE;
    private final Charset charset;
    private final String groupName;
    private final String storageIpAddr;

    /**
     * @param charset
     * @param groupName
     * @param storageIpAddr
     */
    public TrackerDeleteStorageHandler(Socket socket, Charset charset, String groupName, String storageIpAddr) {
        super(socket);
        this.charset = charset;
        this.groupName = groupName;
        this.storageIpAddr = storageIpAddr;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] bGroupName;
        byte[] bs;
        int len;

        bs = groupName.getBytes(charset);
        bGroupName = new byte[OtherConstants.FDFS_GROUP_NAME_MAX_LEN];

        if (bs.length <= OtherConstants.FDFS_GROUP_NAME_MAX_LEN) {
            len = bs.length;
        } else {
            len = OtherConstants.FDFS_GROUP_NAME_MAX_LEN;
        }
        Arrays.fill(bGroupName, (byte) 0);
        System.arraycopy(bs, 0, bGroupName, 0, len);

        int ipAddrLen;
        byte[] bIpAddr = storageIpAddr.getBytes(charset);
        if (bIpAddr.length < OtherConstants.FDFS_IPADDR_SIZE) {
            ipAddrLen = bIpAddr.length;
        } else {
            ipAddrLen = OtherConstants.FDFS_IPADDR_SIZE - 1;
        }

        header = packHeader(cmd, OtherConstants.FDFS_GROUP_NAME_MAX_LEN + ipAddrLen);
        byte[] wholePkg = new byte[header.length + bGroupName.length + ipAddrLen];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(bGroupName, 0, wholePkg, header.length, bGroupName.length);
        System.arraycopy(bIpAddr, 0, wholePkg, header.length + bGroupName.length, ipAddrLen);
        ous.write(wholePkg);
    }

    @Override
    protected void receive(InputStream ins) throws IOException {

        receiveHeader(ins);

        if (this.errorCode != 0) {
            return;
        }

        if (contentLength != 0) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }

    }

}
