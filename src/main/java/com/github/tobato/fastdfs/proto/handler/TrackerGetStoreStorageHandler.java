package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.github.tobato.fastdfs.StorageClient;
import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class TrackerGetStoreStorageHandler extends AbstractHandler<StorageClient> {

    private static final byte withoutGroupCmd = CmdConstants.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITHOUT_GROUP_ONE;
    private static final byte withGroupCmd = CmdConstants.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITH_GROUP_ONE;

    private final byte cmd;
    private final String groupName;
    private final Charset charset;

    /**
     * @param groupName
     * @param charset
     */
    public TrackerGetStoreStorageHandler(Socket socket, String groupName, Charset charset) {
        super(socket);
        if (StringUtils.isBlank(groupName)) {
            this.cmd = withoutGroupCmd;
        } else {
            this.cmd = withGroupCmd;
        }
        this.groupName = groupName;
        this.charset = charset;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        if (StringUtils.isBlank(groupName)) {
            byte[] header = packHeader(cmd, 0);
            ous.write(header);
            return;
        }

        byte[] header = packHeader(cmd, OtherConstants.FDFS_GROUP_NAME_MAX_LEN);
        ous.write(header);

        byte[] bs = groupName.getBytes(charset);
        byte[] bGroupName = new byte[OtherConstants.FDFS_GROUP_NAME_MAX_LEN];
        int group_len;
        if (bs.length <= OtherConstants.FDFS_GROUP_NAME_MAX_LEN) {
            group_len = bs.length;
        } else {
            group_len = OtherConstants.FDFS_GROUP_NAME_MAX_LEN;
        }
        Arrays.fill(bGroupName, (byte) 0);
        System.arraycopy(bs, 0, bGroupName, 0, group_len);
        ous.write(bGroupName);

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

        String ip = new String(bytes, OtherConstants.FDFS_GROUP_NAME_MAX_LEN, OtherConstants.FDFS_IPADDR_SIZE - 1)
                .trim();

        int port = (int) BytesUtil.buff2long(bytes,
                OtherConstants.FDFS_GROUP_NAME_MAX_LEN + OtherConstants.FDFS_IPADDR_SIZE - 1);
        byte storeIndex = bytes[OtherConstants.TRACKER_QUERY_STORAGE_STORE_BODY_LEN - 1];

        result = new StorageClient(new InetSocketAddress(ip, port), charset, storeIndex);
    }

}
