package com.github.tobato.fastdfs.domain;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.github.tobato.fastdfs.exception.FdfsUnavailableException;

/**
 * 表示Tracker服务器位置
 * 
 * @author wuyf
 *
 */
public class TrackerLocator {

    /** 10分钟以后重试连接 */
    private static final int DEFAULT_RETRY_AFTER_SECEND = 10 * 60;

    /** tracker服务配置地址列表 */
    private List<String> trackerList = new ArrayList<String>();

    /** 目录服务地址 */
    private Map<InetSocketAddress, TrackerAddressHolder> trackerAddressMap = new HashMap<InetSocketAddress, TrackerAddressHolder>();

    /** 连接中断以后经过N秒重试 */
    private int retryAfterSecend = DEFAULT_RETRY_AFTER_SECEND;

    /**
     * 初始化Tracker服务器地址
     * 配置方式为 ip:port 如 192.168.1.2:21000
     * 
     * @param trackerList
     */
    public TrackerLocator(List<String> trackerList) {
        super();
        this.trackerList = trackerList;
        buildTrackerAddresses();
    }

    /**
     * 分析TrackerAddress
     */
    private void buildTrackerAddresses() {
        for (String item : trackerList) {
            if (StringUtils.isBlank(item)) {
                continue;
            }
            String[] parts = StringUtils.split(item, ":", 2);
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        "the value of item \"tracker_server\" is invalid, the correct format is host:port");
            }
            InetSocketAddress address = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            trackerAddressMap.put(address, new TrackerAddressHolder(address));
        }

    }

    public void setTrackerList(List<String> trackerList) {
        this.trackerList = trackerList;
    }

    public void setRetryAfterSecend(int retryAfterSecend) {
        this.retryAfterSecend = retryAfterSecend;
    }

    /**
     * 获取Tracker服务器地址
     * 
     * @return
     */
    public InetSocketAddress getTrackerAddress() {
        // 遍历连接地址,抓取当前有效的地址
        for (TrackerAddressHolder value : trackerAddressMap.values()) {
            if (value.canTryToConnect(retryAfterSecend)) {
                return value.getAddress();
            }
        }
        throw new FdfsUnavailableException("找不到可用的tracker");
    }

    /**
     * 设置连接有效
     * 
     * @param address
     */
    public void setActive(InetSocketAddress address) {
        TrackerAddressHolder holder = trackerAddressMap.get(address);
        holder.setActive();
    }

    /**
     * 设置连接无效
     * 
     * @param address
     */
    public void setInActive(InetSocketAddress address) {
        TrackerAddressHolder holder = trackerAddressMap.get(address);
        holder.setInActive();
    }
}
