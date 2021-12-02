package com.example.result;

import lombok.Getter;

/**
 * @author jiangqiangqiang
 * @Description: 状态码enum
 * @date 2021/10/27 5:47 下午
 */
@Getter
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(200, "请求成功"),
    FAIL(500,"服务器内部错误");

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
