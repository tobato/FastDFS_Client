package com.github.tobato.fastdfs.proto.storage;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.proto.storage.enums.StorageMetdataSetType;

/**
 * 文件上传命令测试
 * 
 * @author wuyf
 *
 */
public class StorageSetMetadataCommandTest extends StorageCommandTestBase {

    /**
     * 文件上传测试
     */
    @Test
    public void testStorageSetMetadataCommand() {
        // 上传主文件
        StorePath path = execStorageUploadFileCommand(TestConstants.CAT_IMAGE_FILE, false);
        Set<MateData> metaDataSet = new HashSet<MateData>();

        metaDataSet.add(new MateData("width", "800"));
        metaDataSet.add(new MateData("heigth", "600"));
        metaDataSet.add(new MateData("bgcolor", "FFFFFF"));
        metaDataSet.add(new MateData("author", "Mike"));

        StorageSetMetadataCommand command = new StorageSetMetadataCommand(path.getGroup(), path.getPath(), metaDataSet,
                StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_OVERWRITE);
        executeStoreCmd(command);
        LOGGER.debug("--设置文件元数据结果-----");

        StorageGetMetadataCommand getCommand = new StorageGetMetadataCommand(path.getGroup(), path.getPath());
        Set<MateData> set = executeStoreCmd(getCommand);
        LOGGER.debug("--获取文件元数据结果-----{}", set);
    }

}
