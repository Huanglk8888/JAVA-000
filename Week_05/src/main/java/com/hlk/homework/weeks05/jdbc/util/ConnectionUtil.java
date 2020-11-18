package com.hlk.homework.weeks05.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * 数据库连接工具类
 *
 * @author huanglk
 */
public class ConnectionUtil {
    private static Connection con;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        if (con == null){
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
        }else {
            return con;
        }
        return con;
    }

    public static Statement getStatement()  {
        try {
            Connection connection = getConnection();
            return connection.createStatement();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
