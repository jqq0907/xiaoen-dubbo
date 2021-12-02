package com.example.security.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.convert.UserConvert;
import com.example.system.UserDto;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.security.dto.JwtUserDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangqiangqiang
 * @Description:
 * @date 2021/10/25 3:47 下午
 */
@Service("userDetailService")
public class MyUserDetailsServiceImpl implements UserDetailsService {
//    @Resource
//    private  UserService userService;
//    @Resource
//    private  RoleService roleService;
    @Resource
    private  UserConvert userConvert;

    @Override
    public JwtUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username));
        User user = new User();
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDto userDto = userConvert.entityToUserDto(user);
        if (userDto==null) {
            throw new UsernameNotFoundException("");
        }
        if (Boolean.FALSE.equals(userDto.getUserEnabled())) {
            throw new RuntimeException("账号未激活");
        }
//        Set<Role> roles = roleService.listByUserId(user.getUserId());
        var roles = new ArrayList<Role>();
        List<SimpleGrantedAuthority> grantedAuthorities = roles.stream().map(Role::getRoleName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return JwtUserDto.builder().user(userDto).build();
    }
}

