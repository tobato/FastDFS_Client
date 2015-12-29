package com.github.tobato.fastdfs.cmd.tracker;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.github.tobato.fastdfs.GroupState;

/**
 * 列举存储目录服务器状态
 * 
 * @author wuyf
 *
 */
public class TrackerListGroupsCommandTest extends CommandTestBase<List<GroupState>> {

    @Test
    public void test() {
        List<GroupState> list = executeTrackerCmd(new TrackerListGroupsCommand());
        assertTrue(list.size() > 0);
        LOGGER.debug("-----列举存储目录服务器状态处理结果-----");
        LOGGER.debug(list.toString());
    }

}
