package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class StorageModifyHandler extends AbstractHandler<Void> {

    private static final byte cmd = CmdConstants.STORAGE_PROTO_CMD_MODIFY_FILE;

    private final InputStream ins;
    private final long size;
    private final String path;
    private final Charset charset;
    private final long fileOffset;

    /**
     * @param ins
     * @param size
     * @param ext
     */
    public StorageModifyHandler(Socket socket, InputStream ins, long size, String path, long fileOffset,
            Charset charset) {
        super(socket);
        this.ins = ins;
        this.size = size;
        this.charset = charset;
        this.path = path;
        this.fileOffset = fileOffset;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] hexLenBytes;
        int offset;
        long body_len;

        byte[] pathBytes = path.getBytes(charset);
        body_len = 3 * OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + pathBytes.length + size;

        header = packHeader(cmd, body_len);
        byte[] wholePkg = new byte[(int) (header.length + body_len - size)];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        offset = header.length;

        hexLenBytes = BytesUtil.long2buff(path.length());
        System.arraycopy(hexLenBytes, 0, wholePkg, offset, hexLenBytes.length);
        offset += hexLenBytes.length;

        hexLenBytes = BytesUtil.long2buff(fileOffset);
        System.arraycopy(hexLenBytes, 0, wholePkg, offset, hexLenBytes.length);
        offset += hexLenBytes.length;

        hexLenBytes = BytesUtil.long2buff(size);
        System.arraycopy(hexLenBytes, 0, wholePkg, offset, hexLenBytes.length);
        offset += hexLenBytes.length;

        System.arraycopy(pathBytes, 0, wholePkg, offset, pathBytes.length);
        offset += pathBytes.length;

        ous.write(wholePkg);

        sendFileContent(ins, size, ous);

    }

    @Override
    protected void receive(InputStream ins) throws IOException {

        receiveHeader(ins);

        if (contentLength != 0) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }

    }

}
