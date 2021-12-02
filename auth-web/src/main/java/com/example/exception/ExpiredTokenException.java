package com.example.exception;

/**
 * @author jiangqiangqiang
 * @Description: 没有登录异常
 * @date 2021/11/3 3:59 下午
 */
public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException(String msg){
        super(msg);
    }
}
