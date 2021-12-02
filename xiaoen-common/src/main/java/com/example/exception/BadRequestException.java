package com.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author jiangqiangqiang
 * @description: 请求失败exception
 * @date 2021/11/20 3:45 下午
 */
@Getter
public class BadRequestException extends RuntimeException {

    private Integer status = HttpStatus.BAD_REQUEST.value();

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }
}
