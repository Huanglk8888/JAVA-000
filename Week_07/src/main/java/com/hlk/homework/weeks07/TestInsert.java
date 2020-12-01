package com.hlk.homework.weeks07;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class TestInsert {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        final int insert = insert();
        System.out.println("成功插入数据:"+insert);
    }

    //批量导入
    public static int insert() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root","password");
        connection.setAutoCommit(false);
        String sql = "insert into order(oder_no,user_id,seller_id," +
                "item_id,item_name,status," +
                "price,number,total_price," +
                "address,gmt_create) values(?,?,?," +
                "?,?,0," +
                "?,?,?," +
                "?,now())";
        final PreparedStatement ps = connection.prepareStatement(sql);
        for(int i = 1; i<=1000000; i++){
            ps.setString(1, UUID.randomUUID().toString());
            ps.setInt(2, i);
            ps.setInt(3, i);
            ps.setString(4, "i_"+i);
            ps.setString(5, "测试商品"+i);
            ps.setBigDecimal(6, new BigDecimal(i));
            ps.setInt(7, i);
            ps.setBigDecimal(8, new BigDecimal(i*i));
            ps.setString(9, "测试地址"+i);
            ps.addBatch();
        }
        final int[] ints = ps.executeBatch();
        connection.commit();
        ps.close();
        connection.close();
        return ints.length;
    }
}
