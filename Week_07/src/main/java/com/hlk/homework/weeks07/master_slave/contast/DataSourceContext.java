package com.hlk.homework.weeks07.master_slave.contast;

public class DataSourceContext {
    private static ThreadLocal<String> local=new ThreadLocal<>();

    public static void setDataSource(String dbType){
        local.set(dbType);
    }

    public static String getDataSource(){
        return local.get();
    }

    public static void clear(){
        local.remove();
    }
}
