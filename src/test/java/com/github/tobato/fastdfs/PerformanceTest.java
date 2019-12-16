package com.github.tobato.fastdfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 性能测试
 *
 * @author tobato
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FastdfsTestApplication.class)
public class PerformanceTest {

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(PerformanceTest.class);

    @Autowired
    protected AppendFileStorageClient storageClient;

    /**
     * 上传性能测试
     */
    @Test
    public void testUploadPerformance() {
        final Set pathStore = new HashSet();
        final AtomicInteger failCount = new AtomicInteger(0);
        final AtomicInteger count = new AtomicInteger(0);
        int totalCount = 20;
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.afterPropertiesSet();
        for (int i = 0; i < totalCount; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        URL path = PerformanceTest.class.getResource(TestConstants.PERFORM_FILE_PATH);
                        byte[] bytes = FileUtils.readFileToByteArray(new File(path.getPath()));
                        StorePath storePath = storageClient.uploadFile(null, new ByteArrayInputStream(bytes),
                                bytes.length, "jpg");
                        if (pathStore.contains(storePath.getPath())) {
                            LOGGER.error("并发上传文件冲突！！！！！");
                            LOGGER.error("并发上传文件冲突！！！！！");
                            LOGGER.error("并发上传文件冲突！！！！！");
                        } else {
                            LOGGER.info("upload: {}", storePath.getPath());
                            pathStore.add(storePath.getPath());
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        failCount.incrementAndGet();
                    } finally {
                        count.incrementAndGet();
                    }

                }
            });

        }
        while (count.get() < totalCount) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
        executor.destroy();
        LOGGER.debug("total count: {}", count.get());
        LOGGER.debug("fail count: {}", failCount.get());
    }


    /**
     * 下载性能测试
     */
    @Test
    public void testDownloadPerformance() throws IOException {
        final AtomicInteger failCount = new AtomicInteger(0);
        final AtomicInteger count = new AtomicInteger(0);
        int totalCount = 1000;
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.afterPropertiesSet();

        final StorePath path = this.uploadFile();

        for (int i = 0; i < totalCount; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        storageClient.downloadFile(path.getGroup(), path.getPath(), new DownloadByteArray());
                    } catch (Exception e) {
                        e.printStackTrace();
                        failCount.incrementAndGet();
                    } finally {
                        count.incrementAndGet();
                    }

                }
            });

        }
        while (count.get() < totalCount) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
        executor.destroy();
        LOGGER.debug("total count: {}", count.get());
        LOGGER.debug("fail count: {}", failCount.get());
    }

    /**
     * 上传测试文件
     *
     * @return
     * @throws IOException
     */
    private StorePath uploadFile() throws IOException {
        URL path = PerformanceTest.class.getResource(TestConstants.PERFORM_FILE_PATH);
        byte[] bytes = FileUtils.readFileToByteArray(new File(path.getPath()));
        return storageClient.uploadFile(null, new ByteArrayInputStream(bytes),
                bytes.length, "jpg");
    }

}
