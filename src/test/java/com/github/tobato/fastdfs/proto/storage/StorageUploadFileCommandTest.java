package com.github.tobato.fastdfs.proto.storage;

import org.junit.Test;

import com.github.tobato.fastdfs.proto.StorageCommandTestBase;

/**
 * 文件上传命令测试
 * 
 * @author wuyf
 *
 */
public class StorageUploadFileCommandTest extends StorageCommandTestBase {

    /**
     * 文件上传测试
     */
    @Test
    public void testStorageUploadFileCommand() {
        // 非append模式
        execStorageUploadFileCommand(FILE_PATH, false);
    }

    @Test
    public void testStorageUploadFileCommandByAppend() {
        // append模式
        execStorageUploadFileCommand(FILE_PATH, true);
    }

}
