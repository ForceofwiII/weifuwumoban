package com.fow.weifuwumoban.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.fow.weifuwumoban.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 自定义 SQL 查询可在这里定义
}
