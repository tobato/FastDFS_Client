package com.github.tobato.fastdfs.cmd.storage;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import com.github.tobato.fastdfs.StorePath;
import com.github.tobato.fastdfs.cmd.tracker.CommandTestBase;

/**
 * 文件上传命令测试
 * 
 * @author wuyf
 *
 */
public class StorageUploadFileCommandTest extends CommandTestBase<StorePath> {

    private static final String FILE_PATH = "/images/cat.jpg";

    /**
     * 文件上传测试
     */
    @Test
    public void testStorageUploadFileCommand() {
        // 非append模式
        execStorageUploadFileCommand(false);
    }

    @Test
    public void testStorageUploadFileCommandByAppend() {
        // append模式
        execStorageUploadFileCommand(true);
    }

    /**
     * 文件上传操作
     * 
     * @param isAppenderFile
     */
    public void execStorageUploadFileCommand(boolean isAppenderFile) {
        byte storeIndex = 0;
        InputStream in = null;
        File file = getFile();
        String fileExtName = FilenameUtils.getExtension(file.getName());
        long fileSize = file.length();

        try {
            in = getFileInputStream();
            StorageUploadFileCommand command = new StorageUploadFileCommand(storeIndex, in, fileExtName, fileSize,
                    isAppenderFile);
            StorePath path = executeStoreCmd(command);
            assertNotNull(path);
            LOGGER.debug("isAppenderFile={}-----文件上传处理结果-----", isAppenderFile);
            LOGGER.debug(path.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public InputStream getFileInputStream() throws FileNotFoundException {
        return new FileInputStream(getFile());
    }

    public File getFile() {
        URL url = StorageUploadFileCommandTest.class.getResource(FILE_PATH);
        File file = new File(url.getFile());
        return file;
    }

}
