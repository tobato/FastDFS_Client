package com.github.tobato.fastdfs.cmd.tracker;

import org.junit.Test;

import com.github.tobato.fastdfs.cmd.RemoteServiceDefine;
import com.github.tobato.fastdfs.cmd.tracker.internal.TrackerListGroupsRequest;

/**
 * 列举Groups请求
 * 
 * @author wuyf
 *
 */
public class TrackerListGroupsRequestTest {

    @Test
    public void testGetByteContent() {
        TrackerListGroupsRequest request = new TrackerListGroupsRequest();
        printRequest(request.getHeadByte(RemoteServiceDefine.DEFAULT_CHARSET));
    }

    private void printRequest(byte[] request) {
        for (int i = 0; i < request.length; i++) {
            System.out.print(request[i]);
            System.out.print(" ");
        }
    }

}
