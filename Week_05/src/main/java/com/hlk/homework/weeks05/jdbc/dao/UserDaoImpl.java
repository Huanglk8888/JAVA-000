package com.hlk.homework.weeks05.jdbc.dao;

import com.hlk.homework.weeks05.jdbc.entity.User;
import com.hlk.homework.weeks05.jdbc.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {
        String sql = "insert into user (id,age,name) value (" + user.getId() + "," + user.getTel() + ",'" + user.getName() + "')";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            statement.execute(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }

    @Override
    public void update(User user) {
        String sql = "update user set name = '111' where id =1";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            statement.execute(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Override
    public User selectOneById(Long id) {
        String sql = "select  * from user  where id =1";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setTel(resultSet.getNString("123"));
                user.setAddress(resultSet.getNString("shanghai"));
                return user;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from user  where id =1";
        Statement statement = null;
        try {
            statement = ConnectionUtil.getStatement();
            statement.execute(sql);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }
}