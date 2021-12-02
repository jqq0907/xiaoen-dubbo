package com.example.controller;

import com.example.jwt.JwtProvider;
import com.example.system.dto.LoginUserDto;
import com.example.result.ResponseData;
import com.example.security.dto.JwtUserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangqiangqiang
 * @Description:
 * @date 2021/10/29 2:29 下午
 */
@RestController
@RequestMapping("/api")
public class AuthController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseData login(@RequestBody LoginUserDto loginUserDto) {
        // authenticate()认证方法
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword()));
        JwtUserDto jwtUserDto = (JwtUserDto) authenticate.getPrincipal();
        // 创建token
        String token = jwtProvider.createToken(jwtUserDto.getUsername());
        Map<String, Object> map = new HashMap<>(4);
        map.put("tokenId", token);
        map.put("user", jwtUserDto);
        return ResponseData.success(map);
    }

    /**
     * PreAuthorize在方法执行前进行权限校验
     *
     * @return /
     */
    @PreAuthorize("hasRole('a')")
    @GetMapping("/test")
    public ResponseData test() {
        return ResponseData.success("访问成功");
    }

    /**
     * index
     *
     * @return /
     */
    @GetMapping("/index")
    public ResponseData index() {
        return ResponseData.success("请登录");
    }
}
