package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

public interface UserService {

    /**
     * 微信登陆
     * @param userLoginDTO
     * @return
     */
    User wechatLogin(UserLoginDTO userLoginDTO);
}