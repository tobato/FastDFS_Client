package com.github.tobato.fastdfs.proto;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.StorageUploadFileCommand;
import com.github.tobato.fastdfs.proto.storage.StorageUploadSlaveFileCommandTest;

/**
 * command测试基类
 * 
 * @author wuyf
 *
 */
public abstract class StorageCommandTestBase extends CommandTestBase {

    protected static final String FILE_PATH = "/images/cat.jpg";

    /**
     * 文件上传操作
     * 
     * @param isAppenderFile
     */
    public StorePath execStorageUploadFileCommand(String filePath, boolean isAppenderFile) {
        InputStream in = null;
        File file = getFile(filePath);
        String fileExtName = FilenameUtils.getExtension(file.getName());
        long fileSize = file.length();

        try {
            in = getFileInputStream(filePath);
            return uploadInputStream(in, fileExtName, fileSize, isAppenderFile);
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
        return null;
    }

    protected InputStream getFileInputStream(String path) throws FileNotFoundException {
        return new FileInputStream(getFile(path));
    }

    protected File getFile(String path) {
        URL url = StorageUploadSlaveFileCommandTest.class.getResource(path);
        File file = new File(url.getFile());
        return file;
    }

    /**
     * 获取传输文件第一个部分
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    protected InputStream getTextInputStream(String text) throws IOException {
        // 在内存当中生成缩略图
        return new ByteArrayInputStream(text.getBytes(RemoteServiceDefine.DEFAULT_CHARSET));
    }

    /**
     * 上传默认文件
     * 
     * @return
     */
    protected StorePath uploadDefaultFile() {
        // 上传文件
        return execStorageUploadFileCommand(FILE_PATH, false);
    }

    /**
     * 文件上传操作
     * 
     * @param isAppenderFile
     */
    protected StorePath uploadInputStream(InputStream in, String fileExtName, long fileSize, boolean isAppenderFile) {
        byte storeIndex = 0;
        StorageUploadFileCommand command = new StorageUploadFileCommand(storeIndex, in, fileExtName, fileSize,
                isAppenderFile);
        StorePath path = executeStoreCmd(command);
        assertNotNull(path);
        LOGGER.debug("isAppenderFile={}-----文件上传处理结果-----", false);
        LOGGER.debug(path.toString());
        return path;
    }

}
