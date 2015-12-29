package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.github.tobato.fastdfs.StorageClient;
import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class TrackerGetFetchStorageHandler extends AbstractHandler<StorageClient> {

    private static final byte fetchCmd = CmdConstants.TRACKER_PROTO_CMD_SERVICE_QUERY_FETCH_ONE;
    private static final byte updateCmd = CmdConstants.TRACKER_PROTO_CMD_SERVICE_QUERY_UPDATE;

    private final byte cmd;
    private final String groupName;
    private final String path;
    private final Charset charset;

    /**
     * @param toUpdate
     * @param groupName
     * @param path
     * @param charset
     */
    public TrackerGetFetchStorageHandler(Socket socket, boolean toUpdate, String groupName, String path,
            Charset charset) {
        super(socket);
        if (toUpdate) {
            this.cmd = updateCmd;
        } else {
            this.cmd = fetchCmd;
        }
        this.groupName = groupName;
        this.path = path;
        this.charset = charset;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] bFileName;
        byte[] bGroupName;
        byte[] bs;
        int len;

        bs = groupName.getBytes(charset);
        bGroupName = new byte[OtherConstants.FDFS_GROUP_NAME_MAX_LEN];
        bFileName = path.getBytes(charset);

        if (bs.length <= OtherConstants.FDFS_GROUP_NAME_MAX_LEN) {
            len = bs.length;
        } else {
            len = OtherConstants.FDFS_GROUP_NAME_MAX_LEN;
        }
        Arrays.fill(bGroupName, (byte) 0);
        System.arraycopy(bs, 0, bGroupName, 0, len);

        header = packHeader(cmd, OtherConstants.FDFS_GROUP_NAME_MAX_LEN + bFileName.length);
        byte[] wholePkg = new byte[header.length + bGroupName.length + bFileName.length];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(bGroupName, 0, wholePkg, header.length, bGroupName.length);
        System.arraycopy(bFileName, 0, wholePkg, header.length + bGroupName.length, bFileName.length);
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

        if (contentLength < OtherConstants.TRACKER_QUERY_STORAGE_FETCH_BODY_LEN) {
            throw new IOException("Invalid body length: " + contentLength);
        }

        if ((contentLength - OtherConstants.TRACKER_QUERY_STORAGE_FETCH_BODY_LEN)
                % (OtherConstants.FDFS_IPADDR_SIZE - 1) != 0) {
            throw new IOException("Invalid body length: " + contentLength);
        }

        String ip = new String(bytes, OtherConstants.FDFS_GROUP_NAME_MAX_LEN, OtherConstants.FDFS_IPADDR_SIZE - 1)
                .trim();
        int offset = OtherConstants.FDFS_GROUP_NAME_MAX_LEN + OtherConstants.FDFS_IPADDR_SIZE - 1;
        int port = (int) BytesUtil.buff2long(bytes, offset);

        result = new StorageClient(new InetSocketAddress(ip, port), charset, (byte) 0);

    }

}
