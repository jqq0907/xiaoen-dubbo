package com.example.security.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.example.jwt.JwtProvider;
import com.example.result.ResponseData;
import com.example.security.service.MyUserDetailsServiceImpl;
import com.example.security.dto.JwtUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jiangqiangqiang
 * @description: token处理过滤器
 * @date 2021/10/27 9:34 下午
 */
public class AuthorizationTokenFilter extends BasicAuthenticationFilter {
    private JwtProvider jwtProvider;
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    public AuthorizationTokenFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider, MyUserDetailsServiceImpl myUserDetailsServiceImpl) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.myUserDetailsServiceImpl = myUserDetailsServiceImpl;
    }

    public AuthorizationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (StrUtil.isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
        } else {
            String token = header.replace("Bearer ", "");
            boolean expired = jwtProvider.isExpired(token);
            if (expired) {
                ResponseData responseData = ResponseData.fali(406, "token过期", null);
                PrintWriter writer = response.getWriter();
                writer.write(JSONObject.toJSONString(responseData));
                response.setContentType("application/json");
                writer.flush();
            } else {
                String username = jwtProvider.parseToken(token);
                if (StrUtil.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    JwtUserDto jwtUserDto = myUserDetailsServiceImpl.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, jwtUserDto.getPassword(), jwtUserDto.getAuthorities());
                    authenticationToken.setDetails(jwtUserDto);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                chain.doFilter(request, response);
            }
        }
    }
}
