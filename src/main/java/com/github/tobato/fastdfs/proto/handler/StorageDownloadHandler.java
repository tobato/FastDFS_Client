package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;
import com.github.tobato.fastdfs.socket.FdfsInputStream;

public class StorageDownloadHandler extends AbstractHandler<FdfsInputStream> {

    private static final byte cmd = CmdConstants.STORAGE_PROTO_CMD_DOWNLOAD_FILE;

    private final String groupName;
    private final String path;
    private final long offset;
    private final long size;
    private final Charset charset;

    /**
     * @param groupName
     * @param path
     * @param offset
     * @param size
     * @param charset
     */
    public StorageDownloadHandler(Socket socket, String groupName, String path, long offset, long size,
            Charset charset) {
        super(socket);
        this.groupName = groupName;
        this.path = path;
        this.offset = offset;
        this.size = size;
        this.charset = charset;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] bsOffset;
        byte[] bsDownBytes;
        byte[] groupBytes;
        byte[] filenameBytes;
        byte[] bs;
        int groupLen;

        bsOffset = BytesUtil.long2buff(offset);
        bsDownBytes = BytesUtil.long2buff(size);
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

        header = packHeader(cmd, bsOffset.length + bsDownBytes.length + groupBytes.length + filenameBytes.length);
        byte[] wholePkg = new byte[header.length + bsOffset.length + bsDownBytes.length + groupBytes.length
                + filenameBytes.length];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(bsOffset, 0, wholePkg, header.length, bsOffset.length);
        System.arraycopy(bsDownBytes, 0, wholePkg, header.length + bsOffset.length, bsDownBytes.length);
        System.arraycopy(groupBytes, 0, wholePkg, header.length + bsOffset.length + bsDownBytes.length,
                groupBytes.length);
        System.arraycopy(filenameBytes, 0, wholePkg,
                header.length + bsOffset.length + bsDownBytes.length + groupBytes.length, filenameBytes.length);

        ous.write(wholePkg);

    }

    @Override
    protected void receive(InputStream ins) throws IOException {

        receiveHeader(ins);

        if (this.errorCode != 0) {
            return;
        }
        result = new FdfsInputStream(ins, contentLength);
    }

}
