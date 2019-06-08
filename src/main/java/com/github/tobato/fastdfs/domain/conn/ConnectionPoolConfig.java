package com.github.tobato.fastdfs.domain.conn;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.github.tobato.fastdfs.FdfsClientConstants;

/**
 * 连接池配置
 *
 * @author tobato
 */
@Component
@ConfigurationProperties(prefix = FdfsClientConstants.POOL_CONFIG_PREFIX)
public class ConnectionPoolConfig extends GenericKeyedObjectPoolConfig {

    /**
     * 从池中借出的对象的最大数目
     */
    public static final int FDFS_MAX_TOTAL = -1;

    /**
     * 每个Key最大连接数
     */
    public static final int FDFS_MAX_TOTAL_PER_KEY = 50;

    /**
     * 每个key对应的连接池最大空闲连接数
     */
    public static final int FDFS_MAX_IDLE_PER_KEY = 10;

    /**
     * 每个key对应的连接池最小空闲连接数
     */
    public static final int FDFS_MIN_IDLE_PER_KEY = 5;

    /**
     * 在空闲时检查有效性, 默认false
     */
    public static final boolean FDFS_TEST_WHILE_IDLE = true;

    /**
     * 连接耗尽时是否阻塞(默认true)
     * false报异常,ture阻塞直到超时
     */
    public static final boolean FDFS_BLOCK_WHEN_EXHAUSTED = true;

    /**
     * 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted)
     * 如果超时就抛异常,小于零:阻塞不确定的时间,默认-1
     */
    public static final long FDFS_MAX_WAIT_MILLIS = 1000 * 5;

    public static final long FDFS_MIN_EVICTABLE_IDLETIME_MILLIS = 180000;

    /**
     * 逐出扫描的时间间隔(毫秒) 每过60秒进行一次后台对象清理的行动
     * 如果为负数,则不运行逐出线程, 默认-1
     */
    public static final long FDFS_TIME_BETWEEN_EVICTION_RUNS_MILLIS = 60000;

    /**
     * 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
     * －1表示清理时检查所有线程
     */
    public static final int FDFS_NUM_TESTS_PEREVICTION_RUN = -1;

    /**
     * 默认jmx域名
     */
    public static final String FDFS_JMX_NAME_BASE = "com.github.tobato.fastdfs.conn:type=FdfsConnectionPool";

    /**
     * 默认jmx prefix名称
     */
    public static final String FDFS_JMX_NAME_PREFIX = "fdfsPool";


    public ConnectionPoolConfig() {
        // 从池中借出的对象的最大数目
        setMaxTotal(FDFS_MAX_TOTAL);
        // 在空闲时检查有效性
        setTestWhileIdle(FDFS_TEST_WHILE_IDLE);
        // 连接耗尽时是否阻塞(默认true)
        setBlockWhenExhausted(FDFS_BLOCK_WHEN_EXHAUSTED);
        // 获取连接时的最大等待5秒
        setMaxWaitMillis(FDFS_MAX_WAIT_MILLIS);
        // 每个key对应的池最大连接数
        setMaxTotalPerKey(FDFS_MAX_TOTAL_PER_KEY);
        // 每个key对应的连接池最大空闲连接数
        setMaxIdlePerKey(FDFS_MAX_IDLE_PER_KEY);
        // 每个key对应的连接池最小空闲连接数
        setMinIdlePerKey(FDFS_MIN_IDLE_PER_KEY);
        // 视休眠时间超过了180秒的对象为过期
        setMinEvictableIdleTimeMillis(FDFS_MIN_EVICTABLE_IDLETIME_MILLIS);
        // 每过60秒进行一次后台对象清理的行动
        setTimeBetweenEvictionRunsMillis(FDFS_TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        // 清理时候检查所有线程
        setNumTestsPerEvictionRun(FDFS_NUM_TESTS_PEREVICTION_RUN);
        // 配置jmx
        this.setJmxNameBase(FDFS_JMX_NAME_BASE);
        this.setJmxNamePrefix(FDFS_JMX_NAME_PREFIX);
    }

}