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

public class StorageUploadSlaveHandler extends AbstractHandler<StorePath> {

    private static final byte cmd = CmdConstants.STORAGE_PROTO_CMD_UPLOAD_SLAVE_FILE;

    private final InputStream ins;
    private final long size;
    private final String masterFilename;
    private final String prefixName;
    private final String ext;
    private final Charset charset;

    /**
     * @param ins
     * @param size
     * @param ext
     */
    public StorageUploadSlaveHandler(Socket socket, InputStream ins, long size, String masterFilename,
            String prefixName, String ext, Charset charset) {
        super(socket);
        this.ins = ins;
        this.size = size;
        this.ext = ext;
        this.charset = charset;
        this.masterFilename = masterFilename;
        this.prefixName = prefixName;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] ext_name_bs;
        byte[] sizeBytes;
        byte[] hexLenBytes;
        int offset;
        long body_len;
        byte[] masterFilenameBytes;

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

        masterFilenameBytes = masterFilename.getBytes(charset);

        sizeBytes = new byte[2 * OtherConstants.FDFS_PROTO_PKG_LEN_SIZE];
        body_len = sizeBytes.length + OtherConstants.FDFS_FILE_PREFIX_MAX_LEN
                + OtherConstants.FDFS_FILE_EXT_NAME_MAX_LEN + masterFilenameBytes.length + size;

        hexLenBytes = BytesUtil.long2buff(masterFilename.length());
        System.arraycopy(hexLenBytes, 0, sizeBytes, 0, hexLenBytes.length);
        offset = hexLenBytes.length;

        hexLenBytes = BytesUtil.long2buff(size);
        System.arraycopy(hexLenBytes, 0, sizeBytes, offset, hexLenBytes.length);

        header = packHeader(cmd, body_len);
        byte[] wholePkg = new byte[(int) (header.length + body_len - size)];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(sizeBytes, 0, wholePkg, header.length, sizeBytes.length);
        offset = header.length + sizeBytes.length;

        byte[] prefix_name_bs = new byte[OtherConstants.FDFS_FILE_PREFIX_MAX_LEN];
        byte[] bs = prefixName.getBytes(charset);
        int prefix_name_len = bs.length;
        Arrays.fill(prefix_name_bs, (byte) 0);
        if (prefix_name_len > OtherConstants.FDFS_FILE_PREFIX_MAX_LEN) {
            prefix_name_len = OtherConstants.FDFS_FILE_PREFIX_MAX_LEN;
        }
        if (prefix_name_len > 0) {
            System.arraycopy(bs, 0, prefix_name_bs, 0, prefix_name_len);
        }

        System.arraycopy(prefix_name_bs, 0, wholePkg, offset, prefix_name_bs.length);
        offset += prefix_name_bs.length;

        System.arraycopy(ext_name_bs, 0, wholePkg, offset, ext_name_bs.length);
        offset += ext_name_bs.length;

        System.arraycopy(masterFilenameBytes, 0, wholePkg, offset, masterFilenameBytes.length);

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
