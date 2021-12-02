package com.example.utils;

import com.example.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author jiangqiangqiang
 * @Description: user工具
 * @date 2021/10/28 4:58 下午
 */
public class SysUserUtil {

    /**
     * 当前用户信息
     *
     * @return /
     */
    public static User getCurrentUserInfo() {
        return (User) getAuthentication().getDetails();
    }

    /**
     * 认证信息
     *
     * @return /
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
