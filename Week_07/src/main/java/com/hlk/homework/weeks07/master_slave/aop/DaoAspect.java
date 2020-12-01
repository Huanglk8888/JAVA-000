package com.hlk.homework.weeks07.master_slave.aop;

import com.hlk.homework.weeks07.master_slave.contast.DataSourceContext;
import com.hlk.homework.weeks07.master_slave.contast.DataSourceEnum;
import com.hlk.homework.weeks07.master_slave.contast.DataSourceTypeEnum;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class DaoAspect {
    @Pointcut(value="execution(* com.hlk.homework.weeks07.master_slave.mapper.*.*(..))")
    public void daoPoint(){}

    @Before(value="daoPoint()&&@annotation(dataSourceChange)")
    public void before(DataSourceChange dataSourceChange){
        if(dataSourceChange.type()== DataSourceTypeEnum.MASTER){
            DataSourceContext.setDataSource(DataSourceEnum.PRIMARY.getValue());
            return;
        }

        DataSourceEnum dataSourceEnum=DataSourceEnum.getRandomSlaveEnum();
        DataSourceContext.setDataSource(dataSourceEnum.getValue());
    }

    @After(value="daoPoint()")
    public void after(){
        DataSourceContext.clear();
    }
}
