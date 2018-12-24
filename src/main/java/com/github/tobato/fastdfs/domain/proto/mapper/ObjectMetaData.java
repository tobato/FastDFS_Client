package com.github.tobato.fastdfs.domain.proto.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 映射对象元数据
 * <p>
 * <pre>
 * 映射对象元数据必须由{@code @FdfsColumn}注解
 * </pre>
 *
 * @author tobato
 */
public class ObjectMetaData {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ObjectMetaData.class);

    /**
     * 映射对象类名
     */
    private String className;

    /**
     * 映射列(全部)
     */
    private List<FieldMetaData> fieldList = new ArrayList<FieldMetaData>();

    /**
     * 动态计算列(部分)fieldList包含dynamicFieldList
     */
    private List<FieldMetaData> dynamicFieldList = new ArrayList<FieldMetaData>();

    /**
     * FieldsTotalSize
     */
    private int fieldsTotalSize = 0;

    /**
     * 映射对象元数据构造函数
     *
     * @param genericType
     */
    public <T> ObjectMetaData(Class<T> genericType) {
        // 获得对象类名
        this.className = genericType.getName();
        this.fieldList = praseFieldList(genericType);
        // 校验映射定义
        validatFieldListDefine();
    }

    public String getClassName() {
        return className;
    }

    public List<FieldMetaData> getFieldList() {
        return Collections.unmodifiableList(fieldList);
    }

    /**
     * 解析映射对象数据映射情况
     *
     * @return
     */
    private <T> List<FieldMetaData> praseFieldList(Class<T> genericType) {
        Field[] fields = genericType.getDeclaredFields();
        List<FieldMetaData> mapedFieldList = new ArrayList<FieldMetaData>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(FdfsColumn.class)) {
                FieldMetaData fieldMetaData = new FieldMetaData(fields[i], fieldsTotalSize);
                mapedFieldList.add(fieldMetaData);
                // 计算偏移量
                fieldsTotalSize += fieldMetaData.getRealeSize();
                // 如果是动态计算列
                if (fieldMetaData.isDynamicField()) {
                    dynamicFieldList.add(fieldMetaData);
                }
            }
        }
        return mapedFieldList;
    }

    /**
     * 检查数据列定义
     * <p>
     * <pre>
     * 为了减少编码的错误，检查数据列定义是否存在列名相同或者索引定义相同(多个大于0相同的)的
     * </pre>
     */
    private void validatFieldListDefine() {
        for (FieldMetaData field : fieldList) {
            validatFieldItemDefineByIndex(field);
        }
    }

    /**
     * 检查按索引映射
     *
     * @param field
     */
    private void validatFieldItemDefineByIndex(FieldMetaData field) {
        for (FieldMetaData otherfield : fieldList) {
            if (!field.equals(otherfield) && (field.getIndex() == otherfield.getIndex())) {
                Object[] param = {className, field.getFieldName(), otherfield.getFieldName(), field.getIndex()};
                LOGGER.warn("在类{}映射定义中{}与{}索引定义相同为{}(请检查是否为程序错误)", param);
            }
        }
    }

    /**
     * 是否有动态数据列
     *
     * @return
     */
    private boolean hasDynamicField() {
        for (FieldMetaData field : fieldList) {
            if (field.isDynamicField()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取动态数据列长度
     *
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private int getDynamicFieldSize(Object obj, Charset charset)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        int size = 0;
        for (FieldMetaData field : dynamicFieldList) {
            size = size + field.getDynamicFieldByteSize(obj, charset);
        }
        return size;
    }

    /**
     * 获取固定参数对象总长度
     *
     * @return
     */
    public int getFieldsFixTotalSize() {
        if (hasDynamicField()) {
            throw new FdfsColumnMapException(
                    className + " class hasDynamicField, unsupport operator getFieldsTotalSize");
        }
        return fieldsTotalSize;
    }

    /**
     * 获取需要发送的报文长度
     *
     * @param bean
     * @param charset
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public int getFieldsSendTotalByteSize(Object bean, Charset charset) {
        if (!hasDynamicField()) {
            return fieldsTotalSize;
        } else {
            return getDynamicTotalFieldSize(bean, charset);
        }
    }

    /**
     * 获取动态属性长度
     *
     * @param bean
     * @param charset
     * @return
     */
    private int getDynamicTotalFieldSize(Object bean, Charset charset) {
        try {
            int dynamicFieldSize = getDynamicFieldSize(bean, charset);
            return fieldsTotalSize + dynamicFieldSize;
        } catch (NoSuchMethodException ie) {
            LOGGER.debug("Cannot invoke get methed: ", ie);
            throw new FdfsColumnMapException(ie);
        } catch (IllegalAccessException iae) {
            LOGGER.debug("Illegal access: ", iae);
            throw new FdfsColumnMapException(iae);
        } catch (InvocationTargetException ite) {
            LOGGER.debug("Cannot invoke method: ", ite);
            throw new FdfsColumnMapException(ite);
        }
    }

    /**
     * 导出调试信息
     */
    public void dumpObjectMetaData() {
        LOGGER.debug("dump class={}", className);
        LOGGER.debug("----------------------------------------");
        for (FieldMetaData md : fieldList) {
            LOGGER.debug(md.toString());
        }
    }

}
