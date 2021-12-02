package com.example.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.result.ResponseData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author jiangqiangqiang
 * @Description: 认证异常
 * @date 2021/10/27 5:17 下午
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未登录");
        ResponseData responseData = ResponseData.fali(401, "登录失败", null);
        PrintWriter writer = httpServletResponse.getWriter();
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        writer.write(JSONObject.toJSONString(responseData));
        writer.flush();
    }
}
