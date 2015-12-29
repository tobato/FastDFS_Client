package com.github.tobato.fastdfs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.github.tobato.fastdfs.service.IStorageClientService;
import com.github.tobato.fastdfs.service.TrackerClientService;
import com.github.tobato.fastdfs.service.impl.ByteArrayFdfsFileInputStreamHandler;

@ContextConfiguration("classpath:test/spring.xml")
public class TestFastdfs extends AbstractJUnit4SpringContextTests {

    @Autowired
    private TrackerClientService trackerClientService;

    @Autowired
    private IStorageClientService storageClientService;

    @Test
    public void testGroupState() {
        GroupState[] states = trackerClientService.listGroups();
        for (GroupState state : states) {
            System.out.println(ToStringBuilder.reflectionToString(state));
        }
    }

    @Test
    public void testUploadFile() throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("C:/Users/yuqihuang/Desktop/tmp/img/comp11.jpg"));
        StorePath storePath = storageClientService.uploadFile("image01", new ByteArrayInputStream(bytes), bytes.length,
                "abcdef");
        System.out.println(storePath);
        System.out.println(storePath.getPath().length());

    }

    @Test
    public void testUploadSlaveFile() throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("C:/Users/yuqihuang/Desktop/tmp/img/comp11.jpg"));
        StorePath storePath = storageClientService.uploadSlaveFile("image01",
                "M00/00/00/rBBynlPsiXeAdH8xASKM8lGiSWc346.png", new ByteArrayInputStream(bytes), bytes.length, "!png",
                "jpg");
        System.out.println(storePath);
    }

    @Test
    public void testDownloadFile() {
        byte[] bytes = storageClientService.downloadFile("image01", "M00/00/00/rBBynlPsiXeAdH8xASKM8lGiSWc346[png].jpg",
                new ByteArrayFdfsFileInputStreamHandler());
        System.out.println(bytes.length);
    }

    @Test
    public void testUploadMultiFile() throws IOException {
        int totalCount = 1000;
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);

        executor.afterPropertiesSet();
        final AtomicInteger failCount = new AtomicInteger(0);
        final AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < totalCount; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        byte[] bytes = FileUtils
                                .readFileToByteArray(new File("C:/Users/yuqihuang/Desktop/tmp/img/comp11.jpg"));
                        StorePath storePath = storageClientService.uploadFile(null, new ByteArrayInputStream(bytes),
                                bytes.length, "jpg");
                        System.out.println(storePath);
                        // System.out.println(storePath.getPath().length());
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
        System.out.println("fail count: " + failCount.get());

    }

}
