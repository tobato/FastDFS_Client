package com.github.tobato.fastdfs.proto.tracker.internal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.github.tobato.fastdfs.domain.GroupState;
import com.github.tobato.fastdfs.proto.FdfsResponse;
import com.github.tobato.fastdfs.proto.mapper.FdfsParamMapper;
import com.github.tobato.fastdfs.proto.mapper.ObjectMateData;

/**
 * 列出分组信息执行结果
 * 
 * @author tobato
 *
 */
public class TrackerListGroupsResponse extends FdfsResponse<List<GroupState>> {

    /**
     * 解析反馈内容
     */
    @Override
    public List<GroupState> decodeContent(InputStream in, Charset charset) throws IOException {
        // 解析报文内容
        byte[] bytes = new byte[(int) getContentLength()];
        int contentSize = in.read(bytes);
        // 此处fastdfs的服务端有bug
        if (contentSize != getContentLength()) {
            try {
                return decode(bytes, charset);
            } catch (Exception e) {
                throw new IOException("读取到的数据长度与协议长度不符");
            }

        } else {
            return decode(bytes, charset);
        }
    }

    /**
     * 解析Group
     * 
     * @param bs
     * @param charset
     * @return
     * @throws IOException
     */
    private List<GroupState> decode(byte[] bs, Charset charset) throws IOException {
        // 获取对象转换定义
        ObjectMateData objectMateData = FdfsParamMapper.getObjectMap(GroupState.class);
        int fixFieldsTotalSize = objectMateData.getFieldsFixTotalSize();
        if (bs.length % fixFieldsTotalSize != 0) {
            throw new IOException("byte array length: " + bs.length + " is invalid!");
        }
        // 计算反馈对象数量
        int count = bs.length / fixFieldsTotalSize;
        int offset = 0;
        List<GroupState> results = new ArrayList<GroupState>(count);

        for (int i = 0; i < count; i++) {
            byte[] one = new byte[fixFieldsTotalSize];
            System.arraycopy(bs, offset, one, 0, fixFieldsTotalSize);
            results.add(FdfsParamMapper.map(one, GroupState.class, charset));
            offset += fixFieldsTotalSize;
        }

        return results;
    }
}
