package com.github.tobato.fastdfs.cmd.mark;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.sql.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.github.tobato.fastdfs.proto.BytesUtil;
import com.github.tobato.fastdfs.proto.OtherConstants;

/**
 * 属性映射MateData定义
 * 
 * @author wuyf
 *
 */
class FieldMateData {

    /** 列 */
    private Field field;
    /** 列索引 */
    private int index;
    /** 单元最大长度 */
    private int max;
    /** 单元长度 */
    private int size;
    /** 列偏移量 */
    private int offsize;
    /** 是否所有剩余数据 */
    private boolean isAllRestByte;

    /**
     * 构造函数
     * 
     * @param mapedfield
     * @param offsize
     */
    public FieldMateData(Field mapedfield, int offsize) {
        FdfsColumn column = mapedfield.getAnnotation(FdfsColumn.class);
        this.field = mapedfield;
        this.index = column.index();
        this.max = column.max();
        this.size = getFieldSize(field);
        this.offsize = offsize;
        this.isAllRestByte = column.isAllRestByte();
    }

    /**
     * 获取Field大小
     * 
     * @param field
     * @return
     */
    private int getFieldSize(Field field) {//
        if (String.class.equals(field.getType())) {
            return this.max;
        } else if (long.class.equals(field.getType())) {
            return OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;
        } else if (int.class.equals(field.getType())) {
            return OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;
        } else if (Date.class.equals(field.getType())) {
            return OtherConstants.FDFS_PROTO_PKG_LEN_SIZE;
        } else if (byte.class.equals(field.getType())) {
            return 1;
        }
        throw new FdfsColumnMapException("获取Field大小时未识别的FdfsColumn类型");
    }

    /**
     * 获取值
     * 
     * @param bs
     * @return
     */
    public Object getValue(byte[] bs, Charset charset) {
        if (String.class.equals(field.getType())) {
            if (isAllRestByte) {
                return (new String(bs, offsize, bs.length - offsize, charset)).trim();
            }
            return (new String(bs, offsize, size, charset)).trim();
        } else if (long.class.equals(field.getType())) {
            return BytesUtil.buff2long(bs, offsize);
        } else if (int.class.equals(field.getType())) {
            return (int) BytesUtil.buff2long(bs, offsize);
        } else if (Date.class.equals(field.getType())) {
            return new Date(BytesUtil.buff2long(bs, offsize) * 1000);
        } else if (byte.class.equals(field.getType())) {
            return bs[offsize];
        } else if (boolean.class.equals(field.getType())) {
            return bs[offsize] != 0;
        }
        throw new FdfsColumnMapException("获取值时未识别的FdfsColumn类型");
    }

    public Field getField() {
        return field;
    }

    public int getIndex() {
        return index;
    }

    public int getMax() {
        return max;
    }

    public String getFieldName() {
        return field.getName();
    }

    public int getSize() {
        return size;
    }

    public int getOffsize() {
        return offsize;
    }

    @Override
    public String toString() {
        return "FieldMateData [field=" + getFieldName() + ", index=" + index + ", max=" + max + ", size=" + size
                + ", offsize=" + offsize + "]";
    }

    /**
     * 将属性值转换为byte
     * 
     * @param charset
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public byte[] toByte(Object bean, Charset charset)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Object value = this.getFieldValue(bean);
        if (String.class.equals(field.getType())) {
            // 如果是动态属性
            if (isAllRestByte) {
                return BytesUtil.objString2Byte((String) value, charset);
            }
            return BytesUtil.objString2Byte((String) value, max, charset);
        } else if (long.class.equals(field.getType())) {
            return BytesUtil.long2buff((long) value);
        } else if (int.class.equals(field.getType())) {
            return BytesUtil.long2buff((int) value);
        } else if (Date.class.equals(field.getType())) {
            throw new FdfsColumnMapException("Date 还不支持");
        } else if (byte.class.equals(field.getType())) {
            byte[] result = new byte[1];
            result[0] = (byte) value;
            return result;
        } else if (boolean.class.equals(field.getType())) {
            throw new FdfsColumnMapException("boolean 还不支持");
        }
        throw new FdfsColumnMapException("将属性值转换为byte时未识别的FdfsColumn类型" + field.getName());
    }

    /**
     * 获取单元对应值
     * 
     * @param bean
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private Object getFieldValue(Object bean)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return PropertyUtils.getProperty(bean, field.getName());
    }

    /**
     * 获取动态属性长度
     * 
     * @param bean
     * @param charset
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public int getDynamicFieldByteSize(Object bean, Charset charset)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String value = (String) PropertyUtils.getProperty(bean, field.getName());
        if (null == value) {
            return 0;
        } else {
            return value.getBytes(charset).length;
        }
    }

    public boolean isAllRestByte() {
        return isAllRestByte;
    }

}
