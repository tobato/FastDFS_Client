package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.github.tobato.fastdfs.StorageState;
import com.github.tobato.fastdfs.StorageStateBuilder;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class TrackerListStoragesHandler extends AbstractHandler<StorageState[]> {

    private static final byte cmd = CmdConstants.TRACKER_PROTO_CMD_SERVER_LIST_STORAGE;
    private final Charset charset;
    private final String groupName;
    private final String storageIpAddr;

    /**
     * @param charset
     * @param groupName
     * @param storageIpAddr
     */
    public TrackerListStoragesHandler(Socket socket, Charset charset, String groupName, String storageIpAddr) {
        super(socket);
        this.charset = charset;
        this.groupName = groupName;
        this.storageIpAddr = storageIpAddr;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

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
        byte[] bIpAddr;
        if (storageIpAddr != null && storageIpAddr.length() > 0) {
            bIpAddr = storageIpAddr.getBytes(charset);
            if (bIpAddr.length < OtherConstants.FDFS_IPADDR_SIZE) {
                ipAddrLen = bIpAddr.length;
            } else {
                ipAddrLen = OtherConstants.FDFS_IPADDR_SIZE - 1;
            }
        } else {
            bIpAddr = null;
            ipAddrLen = 0;
        }

        byte[] header = packHeader(cmd, OtherConstants.FDFS_GROUP_NAME_MAX_LEN + ipAddrLen);
        byte[] wholePkg = new byte[header.length + bGroupName.length + ipAddrLen];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(bGroupName, 0, wholePkg, header.length, bGroupName.length);
        if (ipAddrLen > 0) {
            System.arraycopy(bIpAddr, 0, wholePkg, header.length + bGroupName.length, ipAddrLen);
        }
        ous.write(wholePkg);
    }

    @Override
    protected void receive(InputStream ins) throws IOException {

        receiveHeader(ins);

        if (this.errorCode != 0) {
            return;
        }

        byte[] bytes = new byte[(int) contentLength];
        int contentSize = ins.read(bytes);
        if (contentSize != contentLength) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }

        result = decode(bytes);
    }

    private StorageState[] decode(byte[] bs) throws IOException {
        if (bs.length % StorageStateBuilder.getFieldsTotalSize() != 0) {
            throw new IOException("byte array length: " + bs.length + " is invalid!");
        }

        int count = bs.length / StorageStateBuilder.getFieldsTotalSize();
        int offset;
        StorageState[] results = new StorageState[count];

        offset = 0;
        for (int i = 0; i < results.length; i++) {
            results[i] = StorageStateBuilder.convert(bs, offset, charset);
            offset += StorageStateBuilder.getFieldsTotalSize();
        }

        return results;
    }

}
