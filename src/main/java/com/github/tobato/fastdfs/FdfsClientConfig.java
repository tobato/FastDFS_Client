package com.github.tobato.fastdfs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 导入FdfsClient配置
 * 
 * @author tobato
 *
 */
@Configuration
@ComponentScan
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FdfsClientConfig {
    // auto import
}
