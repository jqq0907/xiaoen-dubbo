package com.example.exception.global;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.example.result.ResponseData;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jiangqiangqiang
 * @Description: 全局异常处理
 * @date 2021/10/25 3:51 下午
 */
@RestControllerAdvice
public class MyGlobalException {

    /**
     * 登录异常
     *
     * @param badCredentialsException /
     * @return /
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseData loginExceptionHandler(BadCredentialsException badCredentialsException) {
        return ResponseData.fali(101, "用户名或密码错误", null);
    }

//    @ExceptionHandler(ExpiredTokenException.class)
//    public ResponseData expiryToken(HttpServletRequest request, ExpiredTokenException expiredTokenException) {
//        return ResponseData.fali(406, "token过期", null);
//    }

    /**
     * 验证码异常
     *
     * @param kaptchaException /
     * @return /
     */
    @ExceptionHandler(value = KaptchaException.class)
    public ResponseData kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return ResponseData.fali(101, "验证码不正确", null);
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            return ResponseData.fali(101, "验证码未找到", null);
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            return ResponseData.fali(101, "验证码过期", null);
        } else {
            return ResponseData.fali(101, "验证码渲染失败", null);
        }
    }
}
