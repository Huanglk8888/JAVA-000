package com.hlk.homework.weeks07.master_slave.aop;

import com.hlk.homework.weeks07.master_slave.contast.DataSourceTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceChange {
    DataSourceTypeEnum type() default DataSourceTypeEnum.MASTER;
}
