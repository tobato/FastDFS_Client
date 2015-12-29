package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.github.tobato.fastdfs.StorePath;
import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class StorageUploadHandler extends AbstractHandler<StorePath> {

    private static final byte uploadCmd = CmdConstants.STORAGE_PROTO_CMD_UPLOAD_FILE;
    private static final byte uploadAppenderCmd = CmdConstants.STORAGE_PROTO_CMD_UPLOAD_APPENDER_FILE;

    private final byte cmd;
    private final InputStream ins;
    private final long size;
    private final String ext;
    private final Charset charset;
    private final byte storeIndex;

    /**
     * @param ins
     * @param size
     * @param ext
     */
    public StorageUploadHandler(Socket socket, boolean isAppenderFile, InputStream ins, long size, byte storeIndex,
            String ext, Charset charset) {
        super(socket);
        if (isAppenderFile) {
            cmd = uploadAppenderCmd;
        } else {
            cmd = uploadCmd;
        }
        this.ins = ins;
        this.size = size;
        this.ext = ext;
        this.charset = charset;
        this.storeIndex = storeIndex;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] ext_name_bs;
        byte[] sizeBytes;
        byte[] hexLenBytes;
        int offset;
        long body_len;

        ext_name_bs = new byte[OtherConstants.FDFS_FILE_EXT_NAME_MAX_LEN];
        Arrays.fill(ext_name_bs, (byte) 0);
        if (ext != null && ext.length() > 0) {
            byte[] bs = ext.getBytes(charset);
            int ext_name_len = bs.length;
            if (ext_name_len > OtherConstants.FDFS_FILE_EXT_NAME_MAX_LEN) {
                ext_name_len = OtherConstants.FDFS_FILE_EXT_NAME_MAX_LEN;
            }
            System.arraycopy(bs, 0, ext_name_bs, 0, ext_name_len);
        }

        sizeBytes = new byte[1 + 1 * OtherConstants.FDFS_PROTO_PKG_LEN_SIZE];
        body_len = sizeBytes.length + OtherConstants.FDFS_FILE_EXT_NAME_MAX_LEN + size;

        sizeBytes[0] = storeIndex;
        offset = 1;

        hexLenBytes = BytesUtil.long2buff(size);
        System.arraycopy(hexLenBytes, 0, sizeBytes, offset, hexLenBytes.length);

        header = packHeader(cmd, body_len);
        byte[] wholePkg = new byte[(int) (header.length + body_len - size)];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(sizeBytes, 0, wholePkg, header.length, sizeBytes.length);
        offset = header.length + sizeBytes.length;

        System.arraycopy(ext_name_bs, 0, wholePkg, offset, ext_name_bs.length);

        ous.write(wholePkg);

        sendFileContent(ins, size, ous);

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

        String groupName = new String(bytes, 0, OtherConstants.FDFS_GROUP_NAME_MAX_LEN).trim();
        String path = new String(bytes, OtherConstants.FDFS_GROUP_NAME_MAX_LEN,
                bytes.length - OtherConstants.FDFS_GROUP_NAME_MAX_LEN);

        this.result = new StorePath(groupName, path);
    }

}
