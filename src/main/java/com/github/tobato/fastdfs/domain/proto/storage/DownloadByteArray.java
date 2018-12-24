package com.github.tobato.fastdfs.domain.proto.storage;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * 下载为byte流
 *
 * @author tobato
 */
public class DownloadByteArray implements DownloadCallback<byte[]> {

    @Override
    public byte[] recv(InputStream ins) throws IOException {
        return IOUtils.toByteArray(ins);
    }
}
