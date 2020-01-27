package com.github.tobato.fastdfs.domain.proto.mapper;

import com.github.tobato.fastdfs.TestConstants;
import com.github.tobato.fastdfs.domain.proto.OtherConstants;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * 测试对象序列化
 *
 * @author tobato
 */
public class FdfsParamMapperTest {

    private static final Charset charset = TestConstants.DEFAULT_CHARSET;

    private static final long PORT = 23000;
    private static final String GROUP_NAME = TestConstants.DEFAULT_GROUP;
    private static final String IP = TestConstants.DEFAULT_STORAGE_IP;

    /**
     * 日志
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(FdfsParamMapperTest.class);

    /**
     * byte to object
     */
    @Test
    public void testByteToObject() {
        byte[] content = prepareByte();
        Foo foo = FdfsParamMapper.map(content, Foo.class, charset);
        assertEquals(foo.getGroupName(), GROUP_NAME);
        assertEquals(foo.getIp(), IP);
        assertEquals(foo.getPort(), PORT);
    }

    /**
     * object to byte
     */
    @Test
    public void testObjectToByte() {
        Foo foo = new Foo();
        foo.setGroupName(GROUP_NAME);
        foo.setIp(IP);
        foo.setPort(PORT);
        byte[] result = FdfsParamMapper.toByte(foo, charset);
        byte[] content = prepareByte();
        assertNotNull(result);
        assertNotNull(content);
        assertEquals(content.length, result.length);
        assertArrayEquals(content, result);
    }

    /**
     * object to byte
     */
    @Test
    public void testObjectToByteWithNullString() {
        Foo foo = new Foo();
        foo.setGroupName(null);
        foo.setIp(IP);
        foo.setPort(PORT);
        byte[] result = FdfsParamMapper.toByte(foo, charset);
        byte[] content = prepareByte();
        assertNotNull(result);
        assertNotNull(content);
        assertEquals(content.length, result.length);
    }

    /**
     * 准备Byte数据
     */
    public byte[] prepareByte() {
        // ------------
        long port = PORT;
        byte[] bytePort = BytesUtil.long2buff(port);
        printRequest(port, bytePort);

        // -------------group
        String groupName = GROUP_NAME;
        byte[] groupBytes = new byte[OtherConstants.FDFS_GROUP_NAME_MAX_LEN];
        byte[] gname = groupName.getBytes(charset);

        Arrays.fill(groupBytes, (byte) 0);
        int groupLen;
        if (gname.length <= groupBytes.length) {
            groupLen = gname.length;
        } else {
            groupLen = groupBytes.length;
        }
        System.arraycopy(gname, 0, groupBytes, 0, groupLen);
        printRequest(groupName, groupBytes);

        // ------------ip
        String ip = IP;
        byte[] ipBytes = new byte[OtherConstants.FDFS_IPADDR_SIZE - 1];
        byte[] ipName = ip.getBytes(charset);

        Arrays.fill(ipBytes, (byte) 0);
        int ipLen;
        if (ipName.length <= ipBytes.length) {
            ipLen = ipName.length;
        } else {
            ipLen = ipBytes.length;
        }
        System.arraycopy(ipName, 0, ipBytes, 0, ipLen);
        printRequest(ip, ipBytes);

        byte[] result = new byte[bytePort.length + groupBytes.length + ipBytes.length];

        System.arraycopy(groupBytes, 0, result, 0, groupBytes.length);
        System.arraycopy(ipBytes, 0, result, groupBytes.length, ipBytes.length);
        System.arraycopy(bytePort, 0, result, groupBytes.length + ipBytes.length, bytePort.length);
        printRequest("result", result);
        return result;
    }

    /**
     * 打印消息
     *
     * @param src
     * @param request
     */
    private void printRequest(Object src, byte[] request) {
        LOGGER.debug("------------------------");
        LOGGER.debug("--length{}--------------", request.length);
        LOGGER.debug("原始数据:{}", src);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < request.length; i++) {
            builder.append(request[i]).append(" ");
        }
        LOGGER.debug("{}", builder);
        LOGGER.debug("-------------------");
    }


//    @Test
//    public void testInstance() throws IllegalAccessException, InstantiationException {
//        Object obj = java.lang.Void.class.newInstance();
//    }

}
