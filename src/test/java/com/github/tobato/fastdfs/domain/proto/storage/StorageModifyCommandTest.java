package com.github.tobato.fastdfs.domain.proto.storage;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.StorageCommandTestBase;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件修改命令
 *
 * @author tobato
 */
public class StorageModifyCommandTest extends StorageCommandTestBase {

    @Test
    public void testStorageModifyCommand() throws IOException {
        String text = "Tobato is a good man. this is a test of StorageTruncateCommand.";
        InputStream firstIn = getTextInputStream(text);
        long firstSize = firstIn.available();
        // 上载文字
        LOGGER.debug("文件大小={}", firstSize);
        StorePath path = uploadInputStream(firstIn, "txt", firstSize, true);
        // 文件修改
        String Modifytext = "This is a test of StorageModifyCommand";
        InputStream modifyIn = getTextInputStream(Modifytext);
        long modifySize = modifyIn.available();
        // 观察运行效果:
        // fileOffset参数0 结果为 This is a test of StorageModifyCommandf
        // StorageTruncateCommand
        // fileOffset参数为20 结果为 Tobato is a good manThis is a test of
        // StorageModifyCommandmand
        StorageModifyCommand command = new StorageModifyCommand(path.getPath(), modifyIn, modifySize, 0);
        executeStoreCmd(command);
        LOGGER.debug("--文件修改处理成功--");
    }

}
