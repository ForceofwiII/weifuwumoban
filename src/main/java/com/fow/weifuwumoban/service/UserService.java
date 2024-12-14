package com.fow.weifuwumoban.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fow.weifuwumoban.dto.UserLoginDto;
import com.fow.weifuwumoban.entity.User;
import com.fow.weifuwumoban.vo.UserLoginVo;
import jakarta.validation.Valid;


public interface UserService extends IService<User> {
    // 定义额外业务方法
    User findByAccount(String userAccount);

    String login(UserLoginDto userLoginDto);

    void register(@Valid UserLoginDto userLoginDto);

    UserLoginVo getUserInfo(String satoken);
}
