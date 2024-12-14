package com.fow.weifuwumoban.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fow.weifuwumoban.annotation.Sensitive;
import com.fow.weifuwumoban.dto.UserLoginDto;
import com.fow.weifuwumoban.entity.User;
import com.fow.weifuwumoban.enums.SensitiveType;
import com.fow.weifuwumoban.enums.UserRoleEnum;
import com.fow.weifuwumoban.mapper.UserMapper;
import com.fow.weifuwumoban.service.UserService;
import com.fow.weifuwumoban.strategy.SensitiveContext;
import com.fow.weifuwumoban.vo.UserLoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    SensitiveContext sensitiveContext;

    @Override
    public User findByAccount(String userAccount) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        return this.getOne(queryWrapper);
    }

    @Override
    public String login(UserLoginDto userLoginDto) {





        if(userLoginDto.getUserName()!=null){

            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("user_name", userLoginDto.getUserName());
            User user = userMapper.selectOne(userQueryWrapper);
            if(user == null){
                throw new RuntimeException("用户名或密码错误");
            }
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if(bCryptPasswordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){


                StpUtil.login(user.getUserName());
              return  StpUtil.getTokenValue();

            }else{
                throw new RuntimeException("用户名或密码错误");
            }
        }




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

    @Override
    public UserLoginVo getUserInfo(String satoken) {


        String loginIdByToken = (String) StpUtil.getLoginIdByToken(satoken);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", loginIdByToken);
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null){
            throw new RuntimeException("登陆失败, 请重新登录");
        }


        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(user, userLoginVo);
        desentive(userLoginVo);


        return userLoginVo;
    }



    private  void desentive(UserLoginVo userLoginVo){


        // 获取所有字段
        Field[] declaredFields = userLoginVo.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            // 判断字段是否带有 @Sensitive 注解
            if (field.isAnnotationPresent(Sensitive.class)) {
                Sensitive annotation = field.getAnnotation(Sensitive.class);
                String type = annotation.type(); // 获取注解中的策略类型

                field.setAccessible(true); // 允许操作私有字段
                try {
                    Object value = field.get(userLoginVo); // 获取字段的值
                    if (value != null && value instanceof String) {
                        // 调用策略工厂进行脱敏
                        String desensitizedValue = sensitiveContext.desensitize(type, (String) value);
                        field.set(userLoginVo, desensitizedValue); // 将脱敏后的值设置回字段
                    }
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage());
                }
            }

        }


    }

    private  void desentiveList(List<UserLoginVo> userLoginVos){


        for (UserLoginVo userLoginVo : userLoginVos) {

            // 获取所有字段
            Field[] declaredFields = userLoginVo.getClass().getDeclaredFields();

            for (Field field : declaredFields) {
                // 判断字段是否带有 @Sensitive 注解
                if (field.isAnnotationPresent(Sensitive.class)) {
                    Sensitive annotation = field.getAnnotation(Sensitive.class);
                    String type = annotation.type(); // 获取注解中的策略类型

                    field.setAccessible(true); // 允许操作私有字段
                    try {
                        Object value = field.get(userLoginVo); // 获取字段的值
                        if (value != null && value instanceof String) {
                            // 调用策略工厂进行脱敏
                            String desensitizedValue = sensitiveContext.desensitize(type, (String) value);
                            field.set(userLoginVo, desensitizedValue); // 将脱敏后的值设置回字段
                        }
                    } catch (IllegalAccessException e) {
                        log.error(e.getMessage());
                    }
                }

            }
        }


    }
}
