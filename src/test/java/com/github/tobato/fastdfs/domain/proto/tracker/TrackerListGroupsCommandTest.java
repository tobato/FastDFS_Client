package com.github.tobato.fastdfs.domain.proto.tracker;

import com.github.tobato.fastdfs.domain.fdfs.GroupState;
import com.github.tobato.fastdfs.domain.proto.CommandTestBase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 列举存储目录分组情况
 *
 * @author tobato
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
