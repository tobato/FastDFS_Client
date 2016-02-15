package com.github.tobato.fastdfs.proto.mapper;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.proto.OtherConstants;

/**
 * 文件标签（元数据）映射对象
 * 
 * @author tobato
 *
 */
public class MetadataMapper {

    private MetadataMapper() {
        // hide for utils
    }

    /**
     * 将元数据映射为byte
     * 
     * @param list
     * @param charset
     * @return
     */
    public static byte[] toByte(Set<MateData> metadataSet, Charset charset) {
        if (null == metadataSet || metadataSet.isEmpty()) {
            return new byte[0];
        }
        StringBuilder sb = new StringBuilder(32 * metadataSet.size());
        for (MateData md : metadataSet) {
            sb.append(md.getName()).append(OtherConstants.FDFS_FIELD_SEPERATOR).append(md.getValue());
            sb.append(OtherConstants.FDFS_RECORD_SEPERATOR);
        }
        // 去除最后一个分隔符
        sb.delete(sb.length() - OtherConstants.FDFS_RECORD_SEPERATOR.length(), sb.length());
        return sb.toString().getBytes(charset);
    }

    /**
     * 将byte映射为对象
     * 
     * @param content
     * @param charset
     * @return
     */
    public static Set<MateData> fromByte(byte[] content, Charset charset) {
        Set<MateData> mdSet = new HashSet<MateData>();
        if (null == content) {
            return mdSet;
        }
        String meta_buff = new String(content, charset);
        String[] rows = meta_buff.split(OtherConstants.FDFS_RECORD_SEPERATOR);

        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split(OtherConstants.FDFS_FIELD_SEPERATOR, 2);
            MateData md = new MateData(cols[0]);
            if (cols.length == 2) {
                md.setValue(cols[1]);
            }
            mdSet.add(md);
        }

        return mdSet;
    }

}
