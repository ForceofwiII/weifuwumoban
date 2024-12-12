package com.fow.weifuwumoban.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String userName;
    private String email;
    private String phone;
    private String loginType;
    private String thirdPartyId;
    private String password;
    private String nickName;
    private String userAvatar;
    private String userProfile;
    private String userRole;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime editTime;

    @TableLogic("0")
    private Integer isDelete;
}
