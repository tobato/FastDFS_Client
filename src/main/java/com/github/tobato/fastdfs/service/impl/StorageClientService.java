package com.github.tobato.fastdfs.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.github.tobato.fastdfs.FileInfo;
import com.github.tobato.fastdfs.NameValuePair;
import com.github.tobato.fastdfs.StorageClient;
import com.github.tobato.fastdfs.StorePath;
import com.github.tobato.fastdfs.exception.FdfsIOException;
import com.github.tobato.fastdfs.proto.OtherConstants;
import com.github.tobato.fastdfs.proto.handler.ICmdProtoHandler;
import com.github.tobato.fastdfs.proto.handler.StorageAppendHandler;
import com.github.tobato.fastdfs.proto.handler.StorageDeleteHandler;
import com.github.tobato.fastdfs.proto.handler.StorageDownloadHandler;
import com.github.tobato.fastdfs.proto.handler.StorageGetMetadataHandler;
import com.github.tobato.fastdfs.proto.handler.StorageModifyHandler;
import com.github.tobato.fastdfs.proto.handler.StorageQueryFileInfoHandler;
import com.github.tobato.fastdfs.proto.handler.StorageSetMetadataHandler;
import com.github.tobato.fastdfs.proto.handler.StorageTruncateHandler;
import com.github.tobato.fastdfs.proto.handler.StorageUploadHandler;
import com.github.tobato.fastdfs.proto.handler.StorageUploadSlaveHandler;
import com.github.tobato.fastdfs.service.IFdfsFileInputStreamHandler;
import com.github.tobato.fastdfs.service.IStorageClientService;
import com.github.tobato.fastdfs.service.TrackerClientService;
import com.github.tobato.fastdfs.socket.FdfsInputStream;
import com.github.tobato.fastdfs.socket.FdfsConnection;
import com.github.tobato.fastdfs.socket.FdfsSocketService;
import com.github.tobato.fastdfs.socket.PooledFdfsSocket;

/**
 * 存储服务实现
 * 
 * @author wuyf
 *
 */
public class StorageClientService implements IStorageClientService {

    private FdfsSocketService fdfsSocketService;

    private TrackerClientService trackerClientService;

    private <T> T process(FdfsConnection socket, ICmdProtoHandler<T> handler) {
        try {
            return handler.handle();
        } finally {
            IOUtils.closeQuietly(socket);
        }
    }

    @Override
    public StorePath uploadFile(String groupName, InputStream ins, long size, String ext) {
        StorageClient storageClient = trackerClientService.getStoreStorage(groupName);

        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<StorePath> handler = new StorageUploadHandler(socket, false, ins, size,
                storageClient.getStoreIndex(), ext, storageClient.getCharset());
        return process(socket, handler);
    }

    @Override
    public StorePath uploadAppenderFile(String groupName, InputStream ins, long size, String ext) {
        StorageClient storageClient = trackerClientService.getStoreStorage(groupName);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<StorePath> handler = new StorageUploadHandler(socket, true, ins, size,
                storageClient.getStoreIndex(), ext, storageClient.getCharset());
        return process(socket, handler);
    }

    @Override
    public StorePath uploadSlaveFile(String groupName, String masterFilename, InputStream ins, long size,
            String prefixName, String ext) {
        StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, masterFilename);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<StorePath> handler = new StorageUploadSlaveHandler(socket, ins, size, masterFilename,
                prefixName, ext, storageClient.getCharset());
        return process(socket, handler);
    }

    @Override
    public void appendFile(String groupName, String path, InputStream ins, long size) {
        StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<Void> handler = new StorageAppendHandler(socket, ins, size, path, storageClient.getCharset());
        process(socket, handler);
    }

    @Override
    public void modifyFile(String groupName, String path, long offset, InputStream ins, long size) {
        StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<Void> handler = new StorageModifyHandler(socket, ins, size, path, offset,
                storageClient.getCharset());
        process(socket, handler);
    }

    @Override
    public void deleteFile(String groupName, String path) {
        StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);

        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<Void> handler = new StorageDeleteHandler(socket, groupName, path, storageClient.getCharset());
        process(socket, handler);
    }

    @Override
    public void truncateFile(String groupName, String path, long truncatedFileSize) {
        StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);

        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<Void> handler = new StorageTruncateHandler(socket, path, truncatedFileSize,
                storageClient.getCharset());
        process(socket, handler);

    }

    @Override
    public <T> T downloadFile(String groupName, String path, IFdfsFileInputStreamHandler<T> handling) {
        long offset = 0;
        long size = 0;

        return downloadFile(groupName, path, offset, size, handling);
    }

    @Override
    public <T> T downloadFile(String groupName, String path, long offset, long size,
            IFdfsFileInputStreamHandler<T> handling) {
        StorageClient storageClient = trackerClientService.getFetchStorage(groupName, path);

        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<FdfsInputStream> handler = new StorageDownloadHandler(socket, groupName, path, offset, size,
                storageClient.getCharset());

        try {
            FdfsInputStream fdfsInputStream = handler.handle();
            T result = handling.deal(fdfsInputStream);

            if (!fdfsInputStream.isReadCompleted() && socket instanceof PooledFdfsSocket) {
                ((PooledFdfsSocket) socket).setNeedDestroy(true);
            }
            IOUtils.closeQuietly(fdfsInputStream);
            return result;
        } catch (IOException e) {
            if (socket instanceof PooledFdfsSocket) {
                ((PooledFdfsSocket) socket).setNeedDestroy(true);
            }
            throw new FdfsIOException(e);
        } finally {
            IOUtils.closeQuietly(socket);
        }

    }

    @Override
    public NameValuePair[] getMetadata(String groupName, String path) {
        StorageClient storageClient = trackerClientService.getFetchStorage(groupName, path);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<NameValuePair[]> handler = new StorageGetMetadataHandler(socket, groupName, path,
                storageClient.getCharset());
        return process(socket, handler);
    }

    @Override
    public void overwriteMetadata(String groupName, String path, NameValuePair[] metaList) {
        StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<Void> handler = new StorageSetMetadataHandler(socket, groupName, path, metaList,
                OtherConstants.STORAGE_SET_METADATA_FLAG_OVERWRITE, storageClient.getCharset());
        process(socket, handler);

    }

    @Override
    public void mergeMetadata(String groupName, String path, NameValuePair[] metaList) {
        StorageClient storageClient = trackerClientService.getUpdateStorage(groupName, path);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<Void> handler = new StorageSetMetadataHandler(socket, groupName, path, metaList,
                OtherConstants.STORAGE_SET_METADATA_FLAG_MERGE, storageClient.getCharset());
        process(socket, handler);

    }

    @Override
    public FileInfo queryFileInfo(String groupName, String path) {
        StorageClient storageClient = trackerClientService.getFetchStorage(groupName, path);
        FdfsConnection socket = fdfsSocketService.getSocket(storageClient.getInetSocketAddress());
        ICmdProtoHandler<FileInfo> handler = new StorageQueryFileInfoHandler(socket, groupName, path,
                storageClient.getCharset());
        return process(socket, handler);
    }

    /**
     * @param trackerClientService
     */
    public void setTrackerClientService(TrackerClientService trackerClientService) {
        this.trackerClientService = trackerClientService;
    }

    /**
     * @param fdfsSocketService
     *            the fdfsSocketService to set
     */
    public void setFdfsSocketService(FdfsSocketService fdfsSocketService) {
        this.fdfsSocketService = fdfsSocketService;
    }

}
