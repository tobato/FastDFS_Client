package com.github.tobato.fastdfs.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.tobato.fastdfs.FastdfsTestApplication;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;

/**
 * StorageClient测试基类
 * 
 * @author tobato
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FastdfsTestApplication.class)
public class StorageClientTestBase {

    @Autowired
    protected AppendFileStorageClient storageClient;

    /** 日志 */
    protected static Logger LOGGER = LoggerFactory.getLogger(StorageClientTestBase.class);

}
