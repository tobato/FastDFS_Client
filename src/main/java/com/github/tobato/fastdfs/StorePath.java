package com.github.tobato.fastdfs;

import com.github.tobato.fastdfs.cmd.mark.FdfsColumn;
import com.github.tobato.fastdfs.proto.OtherConstants;

/**
 * 存储文件的路径信息
 * 
 * @author yuqih
 *
 */
public class StorePath {

    @FdfsColumn(index = 0, max = OtherConstants.FDFS_GROUP_NAME_MAX_LEN)
    private String group;

    @FdfsColumn(index = 1, isAllRestByte = true)
    private String path;

    /**
     * 
     */
    public StorePath() {
        super();
    }

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
     * @param group
     *            the group to set
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
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
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

}
