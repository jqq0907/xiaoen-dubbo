package com.example.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.result.ResponseData;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jiangqiangqiang
 * @description: 权限异常
 * @date 2021/10/27 5:29 下午
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseData responseData = ResponseData.fali(401, "没有权限", null);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSONObject.toJSONString(responseData));
        writer.flush();
    }
}
