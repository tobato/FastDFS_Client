package com.github.tobato.fastdfs;

import java.nio.charset.Charset;
import java.util.Date;

import com.github.tobato.fastdfs.proto.BytesUtil;

/**
 * Utils of StateBuilder
 * 
 * @author tobato
 *
 */
class StateBuilderHelper {

    /**
     * get String
     * 
     * @param bs
     * @param offset
     * @param fd
     * @param charset
     * @return
     */
    static String stringValue(byte[] bs, int offset, FieldDefinition fd, Charset charset) {
        return (new String(bs, offset + fd.getOffset(), fd.getSize(), charset)).trim();
    }

    /**
     * get Long
     * 
     * @param bs
     * @param offset
     * @param fd
     * @return
     */
    static long longValue(byte[] bs, int offset, FieldDefinition fd) {
        return BytesUtil.buff2long(bs, offset + fd.getOffset());
    }

    /**
     * get int
     * 
     * @param bs
     * @param offset
     * @param fd
     * @return
     */
    static int intValue(byte[] bs, int offset, FieldDefinition fd) {
        return (int) BytesUtil.buff2long(bs, offset + fd.getOffset());
    }

    /**
     * get byte
     * 
     * @param bs
     * @param offset
     * @param fd
     * @return
     */
    static byte byteValue(byte[] bs, int offset, FieldDefinition fd) {
        return bs[offset + fd.getOffset()];
    }

    /**
     * get boolean
     * 
     * @param bs
     * @param offset
     * @param fd
     * @return
     */
    static boolean booleanValue(byte[] bs, int offset, FieldDefinition fd) {
        return bs[offset + fd.getOffset()] != 0;
    }

    /**
     * get date
     * 
     * @param bs
     * @param offset
     * @param fd
     * @return
     */
    static Date dateValue(byte[] bs, int offset, FieldDefinition fd) {
        return new Date(BytesUtil.buff2long(bs, offset + fd.getOffset()) * 1000);
    }

}
