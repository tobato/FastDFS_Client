package com.github.tobato.fastdfs.domain.proto.storage;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.StorageCommandTestBase;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件截取命令
 *
 * @author tobato
 */
public class StorageTruncateCommandTest extends StorageCommandTestBase {

    @Test
    public void testStorageTruncateCommandText() throws IOException {
        String text = "Tobato is a good man. this is a test of StorageTruncateCommand";
        InputStream firstIn = getTextInputStream(text);
        long firstSize = firstIn.available();
        // 上载文字
        LOGGER.debug("文件大小={}", firstSize);
        StorePath path = uploadInputStream(firstIn, "txt", firstSize, true);
        // 文件截取
        StorageTruncateCommand command = new StorageTruncateCommand(path.getPath(), 0);
        executeStoreCmd(command);
        LOGGER.debug("--文件截取处理成功--");
    }

}
