package com.github.tobato.fastdfs.socket;

import java.net.InetSocketAddress;

/**
 * Socket 获取错误处理策略
 * 
 * @author tobato
 *
 */
public interface IBorrowSockectErrorPolicy {

    FdfsConnection handleWhenErrorOccur(FdfsConnectionPool pool, InetSocketAddress address, Exception ex);

}
