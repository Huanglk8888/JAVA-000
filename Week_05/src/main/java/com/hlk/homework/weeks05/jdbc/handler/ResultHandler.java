package com.hlk.homework.weeks05.jdbc.handler;

import java.sql.ResultSet;

/**
 * 结果集处理
 *
 * @author huanglk
 */
public interface ResultHandler<T> {
    T handler(ResultSet set) throws Exception;
}
