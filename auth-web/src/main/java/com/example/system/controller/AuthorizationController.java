package com.example.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.kaptcha.Kaptcha;
import com.example.rest.AnonymousGetMapping;
import com.example.rest.AnonymousPostMapping;
import com.example.exception.BadRequestException;
import com.example.jwt.JwtProvider;
import com.example.system.dto.LoginUserDto;
import com.example.redis.RedisUtil;
import com.example.result.ResponseData;
import com.example.security.dto.JwtUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangqiangqiang
 * @description:
 * @date 2021/11/20 3:00 下午
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "系统:系统认证授权接口")
public class AuthorizationController {
    private final RedisUtil redisUtil;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtProvider jwtProvider;
    @Resource
    private Kaptcha kaptcha;

    @PostMapping("/login")
    @Operation(summary = "登录授权", method = "POST")
    public ResponseData<Map<String, Object>> login(@Validated @RequestBody LoginUserDto loginUserDto, HttpServletRequest request) {

        // 服务端验证码
        String code = (String) redisUtil.get(loginUserDto.getVerKey());
        // 清楚验证码
        redisUtil.del(loginUserDto.getVerKey());
        // 判断验证码
        if (StrUtil.isNotBlank(loginUserDto.getVerKey()) && loginUserDto.getVerKey().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword());
        // authenticate()认证方法
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        JwtUserDto jwtUserDto = (JwtUserDto) authenticate.getPrincipal();
        // 创建token
        String token = jwtProvider.createToken(jwtUserDto.getUsername());
        Map<String, Object> map = new HashMap<>(4);
        map.put("tokenId", token);
        map.put("user", jwtUserDto);
        return ResponseData.success(map);
    }

    /**
     * 获取验证码
     */
    @AnonymousGetMapping("/code")
    public ResponseEntity getVerCode(HttpServletRequest request, HttpServletResponse response) {
        String code = kaptcha.render();
        return ResponseEntity.ok(code);
    }

    /**
     * 验证验证码
     *
     * @param code 验证码
     */
    @AnonymousPostMapping("/validTime")
    public void validCustomTime(@RequestParam String code) {
        boolean validate = kaptcha.validate(code, 60);
    }

    @GetMapping("/demo")
    public ResponseData demo(@RequestParam String code) {
        return ResponseData.success();
    }
}
