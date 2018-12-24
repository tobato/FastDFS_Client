package com.github.tobato.fastdfs.domain.proto.mapper;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 传输参数定义标签
 *
 * @author tobato
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FdfsColumn {
    /**
     * 映射顺序(从0开始)
     */
    int index() default 0;

    /**
     * String最大值
     */
    int max() default 0;

    /**
     * 动态属性
     */
    DynamicFieldType dynamicField() default DynamicFieldType.NULL;

}
