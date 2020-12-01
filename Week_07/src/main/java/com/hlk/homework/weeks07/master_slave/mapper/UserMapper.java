package com.hlk.homework.weeks07.master_slave.mapper;

import com.hlk.homework.weeks07.master_slave.aop.DataSourceChange;
import com.hlk.homework.weeks07.master_slave.contast.DataSourceTypeEnum;
import com.hlk.homework.weeks07.master_slave.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @DataSourceChange(type = DataSourceTypeEnum.SLAVE)
    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password")
    })
    User getOne(Long id);

    @DataSourceChange(type = DataSourceTypeEnum.MASTER)
    @Insert("INSERT INTO user(user_name,password) VALUES(#{userName}, #{password})")
    void insert(User user);
}
