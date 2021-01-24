package com.github.tobato.fastdfs.domain.fdfs;

import com.github.tobato.fastdfs.FdfsClientConstants;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 存储节点配置参数
 * @author Rong.Jia
 * @date 2021/01/24 10:38
 */
@Component
@ConfigurationProperties(prefix = FdfsClientConstants.STORAGE_CONFIG_PREFIX)
public class StorageNodeConfig {

    /***
     * storage real IP and port mapping to proxy IP and port
     * <p>
     *     格式：
     *     (yml文件)
     *     group0:
     *      172.10.0.3-23000: 192.168.130.14-23000
     *      172.10.0.4-23000: 192.168.130.13-23000
     *
     * </p>
     */
    private Map<String, Map<String, String>> ipMapping;

    public Map<String, Map<String, String>> getIpMapping() {
        return ipMapping;
    }

    public void setIpMapping(Map<String, Map<String, String>> ipMapping) {
        this.ipMapping = ipMapping;
    }


}
