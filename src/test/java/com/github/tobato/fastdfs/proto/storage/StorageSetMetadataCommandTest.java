package com.github.tobato.fastdfs.proto.storage;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import com.github.tobato.fastdfs.domain.MataData;
import org.junit.Test;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.proto.storage.enums.StorageMetdataSetType;

/**
 * 文件上传命令测试
 * 
 * @author tobato
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
        Set<MataData> firstMataData = getFirstMataData();

        // 如果第一次设置MataData使用MERGE后台会报出错误(因为后台要做一次查询)，然后再增加MataData
        // 所以尽量第一次设置的时候采用OverWrite的方式进行设置
        LOGGER.debug("--第一次做测试OVERWRITE-----");
        StorageSetMetadataCommand command = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                firstMataData, StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_OVERWRITE);
        executeStoreCmd(command);
        LOGGER.debug("--设置文件元数据结果-----");

        StorageGetMetadataCommand getCommand = new StorageGetMetadataCommand(path.getGroup(), path.getPath());
        Set<MataData> firstMataDataResult = executeStoreCmd(getCommand);
        LOGGER.debug("--获取文件元数据结果-----{}", firstMataDataResult);
        assertEquals(firstMataData.size(), firstMataDataResult.size());

        LOGGER.debug("--第二次做测试FLAG_MERGE-----");
        Set<MataData> mergeMataData = getMergeMataData();
        StorageSetMetadataCommand mergeCommand = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                mergeMataData, StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_MERGE);
        executeStoreCmd(mergeCommand);
        LOGGER.debug("--设置文件元数据结果-----");

        Set<MataData> mergeMataDataResult = executeStoreCmd(getCommand);
        LOGGER.debug("--获取文件元数据结果-----{}", mergeMataDataResult);
        assertEquals(firstMataData.size() + 1, mergeMataDataResult.size());

        LOGGER.debug("--第三次做测试OverWrite-----");
        Set<MataData> overWriteMataData = getOverWriteMataData();
        StorageSetMetadataCommand overWriteCommand = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                overWriteMataData, StorageMetdataSetType.STORAGE_SET_METADATA_FLAG_MERGE);
        executeStoreCmd(overWriteCommand);
        LOGGER.debug("--设置文件元数据结果-----");

        Set<MataData> overWriteDataResult = executeStoreCmd(getCommand);
        LOGGER.debug("--获取文件元数据结果-----{}", overWriteDataResult);
        assertEquals(overWriteMataData.size(), overWriteDataResult.size());

    }

    /**
     * 获取初始化MataDate
     * 
     * @return
     */
    private Set<MataData> getFirstMataData() {
        Set<MataData> metaDataSet = new HashSet<MataData>();

        metaDataSet.add(new MataData("width", "800"));
        metaDataSet.add(new MataData("bgcolor", "FFFFFF"));
        metaDataSet.add(new MataData("author", "FirstMataData"));
        return metaDataSet;
    }

    /**
     * 获取需要合并的MataDate
     * 没有的条目增加，有则条目覆盖 MERGE
     * 
     * @return
     */
    private Set<MataData> getMergeMataData() {
        Set<MataData> metaDataSet = new HashSet<MataData>();

        metaDataSet.add(new MataData("heigth", "600"));
        metaDataSet.add(new MataData("author", "MergeMataData"));
        return metaDataSet;
    }

    /**
     * 获取需要覆盖的MataData
     * 
     * @return
     */
    private Set<MataData> getOverWriteMataData() {
        Set<MataData> metaDataSet = new HashSet<MataData>();

        metaDataSet.add(new MataData("width", "700"));
        metaDataSet.add(new MataData("heigth", "800"));
        metaDataSet.add(new MataData("bgcolor", "FFFFFF"));
        metaDataSet.add(new MataData("test", "tobato"));
        metaDataSet.add(new MataData("author", "OverWriteData"));
        return metaDataSet;
    }

}
