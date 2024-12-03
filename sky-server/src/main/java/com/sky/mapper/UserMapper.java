package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 新增用户
     * @param user
     */
    @Insert("insert into user(openid, create_time) values(#{openid}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addUser(User user);

    /**
     * 根据openid查找用户
     * @param openId
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getUserByOpenId(String openId);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getById(Long id);
}
