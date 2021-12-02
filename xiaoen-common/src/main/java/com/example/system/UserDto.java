package com.example.system;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author jiangqiangqiang
 * @description: 用户dto
 * @date 2021/11/20 4:32 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDTO {
    private Long userId;
    private String userCode;
    private String username;
    @JSONField(serialize = false)
    private String password;
    private Long departmentId;
    private String departmentName;
    private String nickName;
    private String gender;
    private String profilePhoto;
    private String profilePhotoSrc;
    private String email;
    private String phone;
    private String address;
    private String city;
    private Boolean userEnabled;
    private Boolean idAdmin = false;
    private LocalDateTime pwdResetTime;
    private String departmentCode;
}
