package com.github.tobato.fastdfs.proto.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

import com.github.tobato.fastdfs.GroupState;
import com.github.tobato.fastdfs.GroupStateBuilder;
import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.socket.PooledFdfsSocket;

public class TrackerListGroupsHandler extends AbstractHandler<GroupState[]> {

    private static final byte cmd = CmdConstants.TRACKER_PROTO_CMD_SERVER_LIST_GROUP;
    private final Charset charset;

    /**
     * @param cmd
     * @param groupName
     * @param path
     * @param charset
     */
    public TrackerListGroupsHandler(Socket socket, Charset charset) {
        super(socket);
        this.charset = charset;
    }

    @Override
    protected void send(OutputStream ous) throws IOException {
        byte[] header = packHeader(cmd, 0);
        ous.write(header);
    }

    @Override
    protected void receive(InputStream ins) throws IOException {
        receiveHeader(ins);

        if (this.errorCode != 0) {
            return;
        }

        byte[] bytes = new byte[(int) contentLength];
        int contentSize = ins.read(bytes);
        // 此处fastdfs的服务端有bug
        if (contentSize != contentLength) {
            try {
                result = decode(bytes);
                if (socket instanceof PooledFdfsSocket) {
                    ((PooledFdfsSocket) socket).setNeedDestroy(true);
                }
            } catch (Exception e) {
                throw new IOException("读取到的数据长度与协议长度不符");
            }

        } else {
            result = decode(bytes);
        }
    }

    private GroupState[] decode(byte[] bs) throws IOException {
        if (bs.length % GroupStateBuilder.getFieldsTotalSize() != 0) {
            throw new IOException("byte array length: " + bs.length + " is invalid!");
        }

        int count = bs.length / GroupStateBuilder.getFieldsTotalSize();
        int offset;
        GroupState[] results = new GroupState[count];

        offset = 0;
        for (int i = 0; i < results.length; i++) {
            results[i] = GroupStateBuilder.convert(bs, offset, charset);
            offset += GroupStateBuilder.getFieldsTotalSize();
        }

        return results;
    }

}
