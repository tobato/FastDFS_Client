package com.github.tobato.fastdfs.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.service.impl.DefaultTrackerClientService;
import com.github.tobato.fastdfs.socket.FdfsSocketService;

/**
 * unit test for TrackerClientService
 * 
 * @author wuyf
 *
 */
public class TrackerClientServiceTest {

    @Test
    public void testGetStoreStorage() {
        // fail("Not yet implemented");
    }

    @Test
    public void testGetStoreStorageString() {

        // fail("Not yet implemented");
    }

    @Test
    public void testGetFetchStorage() {
        // f/ail("Not yet implemented");
    }

    @Test
    public void testGetUpdateStorage() {
        // fail("Not yet implemented");
    }

    @Test
    public void testListGroups() {

        FdfsSocketService fdfsSocketService = new FdfsSocketService();

        DefaultTrackerClientService trackerClientService = new DefaultTrackerClientService();
        String[] ips = { "192.168.1.105:22122" };
        trackerClientService.setTrackerServerValues(ips);
        trackerClientService.setFdfsSocketService(fdfsSocketService);
        trackerClientService.init();

        GroupState[] states = trackerClientService.listGroups();
        for (GroupState state : states) {
            System.out.println(ToStringBuilder.reflectionToString(state));
        }
    }

    @Test
    public void testListStoragesString() {
        // fail("Not yet implemented");
    }

    @Test
    public void testListStoragesStringString() {
        // fail("Not yet implemented");
    }

    @Test
    public void testDeleteStorage() {
        // fail("Not yet implemented");
    }

}
