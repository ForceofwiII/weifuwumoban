package com.fow.weifuwumoban.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fow.weifuwumoban.dto.UserLoginDto;
import com.fow.weifuwumoban.entity.User;
import com.fow.weifuwumoban.enums.UserRoleEnum;
import com.fow.weifuwumoban.mapper.UserMapper;
import com.fow.weifuwumoban.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User findByAccount(String userAccount) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        return this.getOne(queryWrapper);
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        return "";
    }

    @Override
    public void register(UserLoginDto userLoginDto) {

        // 判断用户名是否重复
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.eq("user_name", userLoginDto.getUserName() );
        Long l = userMapper.selectCount(userQueryWrapper);
        if(l>0){
            throw new RuntimeException("用户名重复");
        }

        User user = new User();

        BeanUtils.copyProperties(userLoginDto, user);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();  //对密码进行加密

        user.setPassword(bCryptPasswordEncoder.encode(userLoginDto.getPassword()));
        user.setUserRole(UserRoleEnum.CUSTOMER.getMsg());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setEditTime(LocalDateTime.now());
        user.setLoginType("account");
        int insert = userMapper.insert(user);
        if(insert == 0){
            throw new RuntimeException("注册失败");
        }



    }
}
