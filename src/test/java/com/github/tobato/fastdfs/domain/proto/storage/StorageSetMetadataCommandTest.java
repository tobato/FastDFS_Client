package com.github.tobato.fastdfs.domain.proto.storage;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.StorageCommandTestBase;
import com.github.tobato.fastdfs.domain.proto.storage.enums.StorageMetadataSetType;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * 文件上传命令测试
 *
 * @author tobato
 */
public class StorageSetMetadataCommandTest extends StorageCommandTestBase {

    /**
     * 文件上传测试
     */
    @Test
    public void testStorageSetMetadataCommand() {
        // 上传主文件
        StorePath path = execStorageUploadFileCommand(TestConstants.CAT_IMAGE_FILE, false);
        Set<MetaData> firstMetaData = getFirstMetaData();

        // 如果第一次设置MetaData使用MERGE后台会报出错误(因为后台要做一次查询)，然后再增加MetaData
        // 所以尽量第一次设置的时候采用OverWrite的方式进行设置
        LOGGER.debug("--第一次做测试OVERWRITE-----");
        StorageSetMetadataCommand command = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                firstMetaData, StorageMetadataSetType.STORAGE_SET_METADATA_FLAG_OVERWRITE);
        executeStoreCmd(command);
        LOGGER.debug("--设置文件元数据结果-----");

        StorageGetMetadataCommand getCommand = new StorageGetMetadataCommand(path.getGroup(), path.getPath());
        Set<MetaData> firstMetaDataResult = executeStoreCmd(getCommand);
        LOGGER.debug("--获取文件元数据结果-----{}", firstMetaDataResult);
        assertEquals(firstMetaData.size(), firstMetaDataResult.size());

        LOGGER.debug("--第二次做测试FLAG_MERGE-----");
        Set<MetaData> mergeMetaData = getMergeMetaData();
        StorageSetMetadataCommand mergeCommand = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                mergeMetaData, StorageMetadataSetType.STORAGE_SET_METADATA_FLAG_MERGE);
        executeStoreCmd(mergeCommand);
        LOGGER.debug("--设置文件元数据结果-----");

        Set<MetaData> mergeMetaDataResult = executeStoreCmd(getCommand);
        LOGGER.debug("--获取文件元数据结果-----{}", mergeMetaDataResult);
        assertEquals(firstMetaData.size() + 1, mergeMetaDataResult.size());

        LOGGER.debug("--第三次做测试OverWrite-----");
        Set<MetaData> overWriteMetaData = getOverWriteMetaData();
        StorageSetMetadataCommand overWriteCommand = new StorageSetMetadataCommand(path.getGroup(), path.getPath(),
                overWriteMetaData, StorageMetadataSetType.STORAGE_SET_METADATA_FLAG_MERGE);
        executeStoreCmd(overWriteCommand);
        LOGGER.debug("--设置文件元数据结果-----");

        Set<MetaData> overWriteDataResult = executeStoreCmd(getCommand);
        LOGGER.debug("--获取文件元数据结果-----{}", overWriteDataResult);
        assertEquals(overWriteMetaData.size(), overWriteDataResult.size());

    }

    /**
     * 获取初始化MataDate
     *
     * @return
     */
    private Set<MetaData> getFirstMetaData() {
        Set<MetaData> metaDataSet = new HashSet<MetaData>();

        metaDataSet.add(new MetaData("width", "800"));
        metaDataSet.add(new MetaData("bgcolor", "FFFFFF"));
        metaDataSet.add(new MetaData("author", "FirstMetaData"));
        return metaDataSet;
    }

    /**
     * 获取需要合并的MataDate
     * 没有的条目增加，有则条目覆盖 MERGE
     *
     * @return
     */
    private Set<MetaData> getMergeMetaData() {
        Set<MetaData> metaDataSet = new HashSet<MetaData>();

        metaDataSet.add(new MetaData("heigth", "600"));
        metaDataSet.add(new MetaData("author", "MergeMetaData"));
        return metaDataSet;
    }

    /**
     * 获取需要覆盖的MetaData
     *
     * @return
     */
    private Set<MetaData> getOverWriteMetaData() {
        Set<MetaData> metaDataSet = new HashSet<MetaData>();

        metaDataSet.add(new MetaData("width", "700"));
        metaDataSet.add(new MetaData("heigth", "800"));
        metaDataSet.add(new MetaData("bgcolor", "FFFFFF"));
        metaDataSet.add(new MetaData("test", "tobato"));
        metaDataSet.add(new MetaData("author", "OverWriteData"));
        return metaDataSet;
    }

}
