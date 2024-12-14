package com.fow.weifuwumoban.vo;


import com.fow.weifuwumoban.annotation.Sensitive;
import com.fow.weifuwumoban.enums.SensitiveType;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserLoginVo implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;
    @Sensitive(type = "email")
    private String email;
    @Sensitive(type = "phone")
    private String phone;
    private String loginType;
    private String thirdPartyId;
    private String nickName;
    private String userAvatar;
    private String userProfile;
    private String userRole;






}
