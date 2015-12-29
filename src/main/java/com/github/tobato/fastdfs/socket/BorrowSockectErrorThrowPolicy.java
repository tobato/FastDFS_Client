package com.github.tobato.fastdfs.socket;

import java.net.InetSocketAddress;

import com.github.tobato.fastdfs.exception.FdfsConnectException;
import com.github.tobato.fastdfs.exception.FdfsUnavailableException;

/**
 * 连接池获取错误处理策略
 * 
 * @author tobato
 *
 */
public class BorrowSockectErrorThrowPolicy implements IBorrowSockectErrorPolicy {

    @Override
    public FdfsConnection handleWhenErrorOccur(FdfsConnectionPool pool, InetSocketAddress address, Exception ex) {

        Throwable e = ex;
        int i = 0;
        while (e != null && i < 5) {
            if (e instanceof FdfsConnectException) {
                throw (FdfsConnectException) e;
            }
            e = e.getCause();
            i++;
        }

        throw new FdfsUnavailableException("连接池中无可用资源", ex);
    }

}
