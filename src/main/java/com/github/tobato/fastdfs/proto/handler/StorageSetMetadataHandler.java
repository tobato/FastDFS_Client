package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.github.tobato.fastdfs.NameValuePair;
import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;

public class StorageSetMetadataHandler extends AbstractHandler<Void> {

    private static final byte cmd = CmdConstants.STORAGE_PROTO_CMD_SET_METADATA;

    private final String groupName;
    private final String path;
    private final NameValuePair[] metaList;
    private final Charset charset;
    private final byte opFlag;

    /**
     * @param ins
     * @param size
     * @param ext
     */
    public StorageSetMetadataHandler(Socket socket, String groupName, String path, NameValuePair[] metaList,
            byte opFlag, Charset charset) {
        super(socket);
        this.groupName = groupName;
        this.path = path;
        this.charset = charset;
        this.metaList = metaList;
        this.opFlag = opFlag;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {

        byte[] header;
        byte[] groupBytes;
        byte[] filenameBytes;
        byte[] meta_buff;
        byte[] bs;
        int groupLen;
        byte[] sizeBytes;

        if (metaList == null) {
            meta_buff = new byte[0];
        } else {
            meta_buff = packMetadata(metaList).getBytes(charset);
        }

        filenameBytes = path.getBytes(charset);
        sizeBytes = new byte[2 * OtherConstants.FDFS_PROTO_PKG_LEN_SIZE];
        Arrays.fill(sizeBytes, (byte) 0);

        bs = BytesUtil.long2buff(filenameBytes.length);
        System.arraycopy(bs, 0, sizeBytes, 0, bs.length);
        bs = BytesUtil.long2buff(meta_buff.length);
        System.arraycopy(bs, 0, sizeBytes, OtherConstants.FDFS_PROTO_PKG_LEN_SIZE, bs.length);

        groupBytes = new byte[OtherConstants.FDFS_GROUP_NAME_MAX_LEN];
        bs = groupName.getBytes(charset);

        Arrays.fill(groupBytes, (byte) 0);
        if (bs.length <= groupBytes.length) {
            groupLen = bs.length;
        } else {
            groupLen = groupBytes.length;
        }
        System.arraycopy(bs, 0, groupBytes, 0, groupLen);

        header = packHeader(cmd, 2 * OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 1 + groupBytes.length
                + filenameBytes.length + meta_buff.length);

        byte[] wholePkg = new byte[header.length + sizeBytes.length + 1 + groupBytes.length + filenameBytes.length];
        System.arraycopy(header, 0, wholePkg, 0, header.length);
        System.arraycopy(sizeBytes, 0, wholePkg, header.length, sizeBytes.length);
        wholePkg[header.length + sizeBytes.length] = opFlag;
        System.arraycopy(groupBytes, 0, wholePkg, header.length + sizeBytes.length + 1, groupBytes.length);
        System.arraycopy(filenameBytes, 0, wholePkg, header.length + sizeBytes.length + 1 + groupBytes.length,
                filenameBytes.length);
        ous.write(wholePkg);
        if (meta_buff.length > 0) {
            ous.write(meta_buff);
        }

    }

    private String packMetadata(NameValuePair[] meta_list) {
        if (meta_list.length == 0) {
            return "";
        }

        StringBuffer sb = new StringBuffer(32 * meta_list.length);
        sb.append(meta_list[0].getName()).append(OtherConstants.FDFS_FIELD_SEPERATOR).append(meta_list[0].getValue());
        for (int i = 1; i < meta_list.length; i++) {
            sb.append(OtherConstants.FDFS_RECORD_SEPERATOR);
            sb.append(meta_list[i].getName()).append(OtherConstants.FDFS_FIELD_SEPERATOR)
                    .append(meta_list[i].getValue());
        }

        return sb.toString();
    }

    @Override
    protected void receive(InputStream ins) throws IOException {

        receiveHeader(ins);

        if (contentLength != 0) {
            throw new IOException("读取到的数据长度与协议长度不符");
        }

    }

}
