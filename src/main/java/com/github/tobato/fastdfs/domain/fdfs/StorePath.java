package com.github.tobato.fastdfs.domain.fdfs;

import org.apache.commons.lang3.Validate;

import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.domain.proto.OtherConstants;
import com.github.tobato.fastdfs.domain.proto.mapper.DynamicFieldType;
import com.github.tobato.fastdfs.domain.proto.mapper.FdfsColumn;

/**
 * 存储文件的路径信息
 *
 * @author tobato
 */
public class StorePath {

    @FdfsColumn(index = 0, max = OtherConstants.FDFS_GROUP_NAME_MAX_LEN)
    private String group;

    @FdfsColumn(index = 1, dynamicField = DynamicFieldType.allRestByte)
    private String path;

    /**
     * 解析路径
     */
    private static final String SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR = "/";

    /**
     * group
     */
    private static final String SPLIT_GROUP_NAME = "group";

    /**
     * 存储文件路径
     */
    public StorePath() {
        super();
    }

    /**
     * 存储文件路径
     *
     * @param group
     * @param path
     */
    public StorePath(String group, String path) {
        super();
        this.group = group;
        this.path = path;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取文件全路径
     *
     * @return
     */
    public String getFullPath() {
        return this.group.concat(SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR).concat(this.path);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StorePath [group=" + group + ", path=" + path + "]";
    }

    /**
     * 从Url当中解析存储路径对象
     *
     * @param filePath 有效的路径样式为(group/path) 或者
     *                 (http://ip/group/path),路径地址必须包含group
     * @return
     */
    public static StorePath parseFromUrl(String filePath) {
        Validate.notNull(filePath, "解析文件路径不能为空");

        String group = getGroupName(filePath);

        // 获取group起始位置
        int pathStartPos = filePath.indexOf(group) + group.length() + 1;
        String path = filePath.substring(pathStartPos, filePath.length());
        return new StorePath(group, path);
    }

    /**
     * 获取Group名称
     *
     * @param filePath
     * @return
     */
    private static String getGroupName(String filePath) {
        //先分隔开路径
        String[] paths = filePath.split(SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR);
        if (paths.length == 1) {
            throw new FdfsUnsupportStorePathException("解析文件路径错误,有效的路径样式为(group/path) 而当前解析路径为".concat(filePath));
        }
        for (String item : paths) {
            if (item.indexOf(SPLIT_GROUP_NAME) != -1) {
                return item;
            }
        }
        throw new FdfsUnsupportStorePathException("解析文件路径错误,被解析路径url没有group,当前解析路径为".concat(filePath));
    }

}
