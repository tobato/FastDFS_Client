package com.github.tobato.fastdfs.proto.tracker;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.proto.CommandTestBase;
import com.github.tobato.fastdfs.proto.tracker.TrackerListGroupsCommand;

/**
 * 列举存储目录分组情况
 * 
 * @author tobato
 *
 */
public class TrackerListGroupsCommandTest extends CommandTestBase {

    @Test
    public void test() {
        List<GroupState> list = executeTrackerCmd(new TrackerListGroupsCommand());
        assertTrue(list.size() > 0);
        LOGGER.debug("-----列举存储服务器分组状态处理结果-----");
        LOGGER.debug(list.toString());
    }

}
