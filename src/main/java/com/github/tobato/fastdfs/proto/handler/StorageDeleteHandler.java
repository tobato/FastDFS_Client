package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class StorageDeleteHandler extends AbstractHandler<Void> {

    private static final byte cmd = CmdConstants.STORAGE_PROTO_CMD_DELETE_FILE;

    private final String groupName;
    private final String path;
    private final Charset charset;

    /**
     * @param groupName
     * @param path
     * @param charset
     */
    public StorageDeleteHandler(Socket socket, String groupName, String path, Charset charset) {
        super(socket);
        this.groupName = groupName;
        this.path = path;
        this.charset = charset;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] groupBytes;
        byte[] filenameBytes;
        byte[] bs;
        int groupLen;

        groupBytes = new byte[OtherConstants.FDFS_GROUP_NAME_MAX_LEN];
        bs = groupName.getBytes(charset);
        filenameBytes = path.getBytes(charset);

        Arrays.fill(groupBytes, (byte) 0);
        if (bs.length <= groupBytes.length) {
            groupLen = bs.length;
        } else {
            groupLen = groupBytes.length;
        }
        System.arraycopy(bs, 0, groupBytes, 0, groupLen);

        header = packHeader(cmd, groupBytes.length + filenameBytes.length);
        byte[] wholePkg = new byte[header.length + groupBytes.length + filenameBytes.length];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(groupBytes, 0, wholePkg, header.length, groupBytes.length);
        System.arraycopy(filenameBytes, 0, wholePkg, header.length + groupBytes.length, filenameBytes.length);

        ous.write(wholePkg);

    }

    @Override
    protected void receive(InputStream ins) throws IOException {

        receiveHeader(ins);

        if (contentLength != 0) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }

    }

}
