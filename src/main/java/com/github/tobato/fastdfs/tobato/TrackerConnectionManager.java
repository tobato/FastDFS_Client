package com.github.tobato.fastdfs.tobato;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.tobato.fastdfs.conn.Connection;
import com.github.tobato.fastdfs.conn.ConnectionPool;
import com.github.tobato.fastdfs.exception.FdfsConnectException;
import com.github.tobato.fastdfs.exception.FdfsUnavailableException;

/**
 * 目录服务连接管理
 * 
 * @author wuyf
 *
 */
public class TrackerConnectionManager {

    /** 连接池 */
    @Autowired
    private ConnectionPool connectionPool;
    /** 可用Tracker列表 */
    private String[] trackerServerValues;
    /** 目录服务地址 */
    private final CircularList<TrackerAddressHolder> trackerAddresses = new CircularList<TrackerAddressHolder>();

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackerConnectionManager.class);

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {

        String[] parts;

        Set<InetSocketAddress> trackerAddressSet = new HashSet<InetSocketAddress>();
        for (String trackerServersValue : trackerServerValues) {
            if (StringUtils.isBlank(trackerServersValue)) {
                continue;
            }
            parts = StringUtils.split(trackerServersValue, ":", 2);
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        "the value of item \"tracker_server\" is invalid, the correct format is host:port");
            }
            InetSocketAddress address = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            trackerAddressSet.add(address);
        }

        if (trackerAddressSet.size() == 0) {
            throw new IllegalArgumentException("item \"tracker_server\"  not found");
        }

        for (InetSocketAddress address : trackerAddressSet) {
            trackerAddresses.add(new TrackerAddressHolder(address));
        }
    }

    // public

    /**
     * 一个ip取不到就取下一个ip的连接，直到所有的ip都取过一遍还没取到报异常
     * 
     * @return
     */
    private Connection getTrackerConnection() {

        Connection conn = null;
        TrackerAddressHolder holder;
        for (int i = 0; i < trackerAddresses.size(); i++) {
            holder = trackerAddresses.next();
            // list中不是所有的的都被标记成不可用并且当前被标记成不可用时间小于10分钟的情况下，直接跳到下一个
            if (!holder.available && (System.currentTimeMillis() - holder.lastUnavailableTime) < 10 * 60 * 1000) {
                continue;
            }

            try {
                conn = connectionPool.borrowObject(holder.address);
                holder.setState(true);
            } catch (FdfsConnectException e) {
                // 如果连接错误，销毁连接
                try {
                    connectionPool.invalidateObject(holder.address, conn);
                } catch (Exception e1) {
                    LOGGER.error("使连接失效错误", e1);
                }
                holder.setState(false);
                holder.setLastUnavailableTime(System.currentTimeMillis());
            } catch (Exception ignore) {
                LOGGER.error("连接错误", ignore);
            }
            if (conn != null) {
                return conn;
            }
        }

        throw new FdfsUnavailableException("找不到可用的tracker");

    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private class TrackerAddressHolder {
        /** 连接地址 */
        private InetSocketAddress address;
        /** 是否有效 */
        private boolean available;
        /** 上次无效时间 */
        private long lastUnavailableTime;

        /**
         * @param address
         */
        private TrackerAddressHolder(InetSocketAddress address) {
            super();
            this.address = address;
            this.available = true;
        }

        private void setState(boolean available) {
            if (this.available != available) {
                this.available = available;
            }
        }

        public long getLastUnavailableTime() {
            return lastUnavailableTime;
        }

        public void setLastUnavailableTime(long lastUnavailableTime) {
            this.lastUnavailableTime = lastUnavailableTime;
        }

    }

}
