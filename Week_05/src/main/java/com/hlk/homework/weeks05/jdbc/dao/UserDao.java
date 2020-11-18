package com.hlk.homework.weeks05.jdbc.dao;

import com.hlk.homework.weeks05.jdbc.entity.User;

public interface UserDao {

    void save(User user);

    void update(User user);

    User selectOneById(Long id);

    void deleteById(Long id);
}
