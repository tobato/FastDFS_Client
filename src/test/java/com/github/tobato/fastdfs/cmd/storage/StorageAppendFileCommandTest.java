package com.github.tobato.fastdfs.cmd.storage;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.github.tobato.fastdfs.StorePath;
import com.github.tobato.fastdfs.cmd.RemoteServiceDefine;
import com.github.tobato.fastdfs.cmd.StorageCommandTestBase;

/**
 * 文件续传命令
 * 
 * @author wuyf
 *
 */
public class StorageAppendFileCommandTest extends StorageCommandTestBase {

    /**
     * 文件续传需要先使用 append模式Save一个可以续传的文件
     * 然后才能使用续传命令续传文件
     * 
     * @throws IOException
     */
    @Test
    public void testStorageAppendFileCommand() throws IOException {
        String firstText = "Tobato is a good man.他是一个好人\r\n";
        InputStream firstIn = getTextInputStream(firstText);
        long firstSize = firstIn.available();
        // 先上载第一段文字
        StorePath path = uploadInputStream(firstIn, "txt", firstSize, true);
        // 添加第二段文字
        String secendText = "Work hard and hard. 努力工作啊\r\n";
        InputStream secendIn = getTextInputStream(secendText);
        long secendSize = secendIn.available();
        // 文件续传
        execStorageAppendFileCommand(secendIn, secendSize, path.getPath());
        firstIn.close();
        secendIn.close();
    }

    /**
     * 获取传输文件第一个部分
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    private InputStream getTextInputStream(String text) throws IOException {
        // 在内存当中生成缩略图
        return new ByteArrayInputStream(text.getBytes(RemoteServiceDefine.DEFAULT_CHARSET));
    }

    /**
     * 文件续传操作
     * 
     * @param isAppenderFile
     */
    public void execStorageAppendFileCommand(InputStream in, long fileSize, String path) {
        StorageAppendFileCommand command = new StorageAppendFileCommand(in, fileSize, path);
        executeStoreCmd(command);
        assertNotNull(path);
        LOGGER.debug("--文件续传操作结果-----");
    }

}
