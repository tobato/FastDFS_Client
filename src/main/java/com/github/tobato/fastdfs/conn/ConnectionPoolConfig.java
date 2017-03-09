package com.github.tobato.fastdfs.conn;

import com.github.tobato.fastdfs.FdfsClientConstants;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 连接池配置
 *
 * @author tobato
 */
@Component
@ConfigurationProperties(prefix = FdfsClientConstants.POOL_CONFIG_PREFIX)
public class ConnectionPoolConfig extends GenericKeyedObjectPoolConfig {

    public ConnectionPoolConfig() {
        setMaxTotal(50); // 从池中借出的对象的最大数目50
        setTestWhileIdle(true);
        setBlockWhenExhausted(true);
        setMaxWaitMillis(100);
        setMinEvictableIdleTimeMillis(180000); // 视休眠时间超过了 180 秒的对象为过期
        setTimeBetweenEvictionRunsMillis(60000); // 每过 60 秒进行一次后台对象清理的行动
        setNumTestsPerEvictionRun(-1);
    }

}