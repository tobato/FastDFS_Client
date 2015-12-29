package com.github.tobato.fastdfs.cmd.storage;

import org.junit.Test;

import com.github.tobato.fastdfs.cmd.tracker.CommandTestBase;

public class StorageDeleteFileCommandTest extends CommandTestBase<Void> {

    /**
     * 文件删除测试
     */
    @Test
    public void testStorageDeleteFileCommand() {
        // 非append模式
        StorageDeleteFileCommand command = new StorageDeleteFileCommand("group1",
                "M00/00/00/wKiuKVaCpFqEGvBjAAAAAN1CckA008.jpg");
        executeStoreCmd(command);
    }

}
