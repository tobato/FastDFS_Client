package com.github.tobato.fastdfs;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tobato.fastdfs.proto.CmdConstants;
import com.github.tobato.fastdfs.proto.OtherConstants;
import com.github.tobato.fastdfs.proto.mapper.BytesUtil;

public class CodeTest {
    /** 日志 */
    protected final static Logger LOGGER = LoggerFactory.getLogger(CodeTest.class);

    public static void main(String[] args) throws IOException {

        byte[] header;
        byte[] hex_len;

        header = new byte[OtherConstants.FDFS_PROTO_PKG_LEN_SIZE + 2];
        Arrays.fill(header, (byte) 0);

        hex_len = BytesUtil.long2buff(561111);
        System.arraycopy(hex_len, 0, header, 0, hex_len.length);
        header[OtherConstants.PROTO_HEADER_CMD_INDEX] = CmdConstants.FDFS_PROTO_CMD_QUIT;
        header[OtherConstants.PROTO_HEADER_STATUS_INDEX] = (byte) 0;

        for (int i = 0; i < header.length; i++) {
            System.out.print(header[i]);
            System.out.print(" ");
        }

    }

}
