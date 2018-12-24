package com.github.tobato.fastdfs.domain.proto.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * param对象与byte映射器
 *
 * @author tobato
 */
public class FdfsParamMapper {

    private FdfsParamMapper() {
        // hide for utils
    }

    /**
     * 对象映射缓存
     */
    private static Map<String, ObjectMetaData> mapCache = new HashMap<String, ObjectMetaData>();

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(FdfsParamMapper.class);


    /**
     * 将byte解码为对象
     *
     * @param content
     * @param genericType
     * @param charset
     * @param <T>
     * @return
     */
    public static <T> T map(byte[] content, Class<T> genericType, Charset charset) {
        // 获取映射对象
        ObjectMetaData objectMap = getObjectMap(genericType);
        if (LOGGER.isDebugEnabled()) {
            objectMap.dumpObjectMetaData();
        }

        try {
            return mapByIndex(content, genericType, objectMap, charset);
        } catch (InstantiationException ie) {
            LOGGER.debug("Cannot instantiate: ", ie);
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
     * 获取对象映射定义
     *
     * @param genericType
     * @return
     */
    public static <T> ObjectMetaData getObjectMap(Class<T> genericType) {
        if (null == mapCache.get(genericType.getName())) {
            // 还未缓存过
            mapCache.put(genericType.getName(), new ObjectMetaData(genericType));
        }
        return mapCache.get(genericType.getName());
    }

    /**
     * 按列顺序映射
     *
     * @param content
     * @param genericType
     * @param objectMap
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static <T> T mapByIndex(byte[] content, Class<T> genericType, ObjectMetaData objectMap, Charset charset)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {

        List<FieldMetaData> mappingFields = objectMap.getFieldList();
        T obj = genericType.newInstance();
        for (int i = 0; i < mappingFields.size(); i++) {
            FieldMetaData field = mappingFields.get(i);
            // 设置属性值
            LOGGER.debug("设置值是 " + field + field.getValue(content, charset));
            BeanUtils.setProperty(obj, field.getFieldName(), field.getValue(content, charset));
        }

        return obj;
    }

    /**
     * 序列化为Byte
     *
     * @param object
     * @param charset
     * @return
     */
    public static byte[] toByte(Object object, Charset charset) {
        ObjectMetaData objectMap = getObjectMap(object.getClass());
        try {
            return convertFieldToByte(objectMap, object, charset);
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
     * 将属性转换为byte
     *
     * @param objectMap
     * @param object
     * @param charset
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private static byte[] convertFieldToByte(ObjectMetaData objectMap, Object object, Charset charset)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<FieldMetaData> mappingFields = objectMap.getFieldList();
        // 获取报文长度 (固定长度+动态长度)
        int size = objectMap.getFieldsSendTotalByteSize(object, charset);
        byte[] result = new byte[size];
        int offsize = 0;
        for (int i = 0; i < mappingFields.size(); i++) {
            FieldMetaData field = mappingFields.get(i);
            byte[] fieldByte = field.toByte(object, charset);
            if (null != fieldByte) {
                System.arraycopy(fieldByte, 0, result, offsize, fieldByte.length);
                offsize += fieldByte.length;
            }
        }
        return result;
    }

}