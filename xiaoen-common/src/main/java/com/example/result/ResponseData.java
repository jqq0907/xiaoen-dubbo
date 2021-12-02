package com.example.result;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jiangqiangqiang
 * @description: 统一返回对象
 * @date 2021/10/27 5:43 下午
 */
@Data
@Builder
public class ResponseData<T> implements Serializable {

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回code
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 是否成功
     */
    private boolean success;

    public static <T> ResponseData<T> success() {
        return ResponseData.<T>builder().code(ResponseCode.SUCCESS.getCode()).success(true).message(ResponseCode.SUCCESS.getMessage()).build();
    }

    public static <T> ResponseData<T> success(T data) {
        return ResponseData.<T>builder().code(ResponseCode.SUCCESS.getCode()).success(true).message(ResponseCode.SUCCESS.getMessage()).data(data).build();
    }

    public static <T> ResponseData<T> success(Integer code, String message, T data) {
        return ResponseData.<T>builder().code(code).success(true).message(message).data(data).build();
    }

    public static <T> ResponseData<T> success(ResponseCode responseCode, T data) {
        return ResponseData.<T>builder().code(responseCode.getCode()).success(true).message(responseCode.getMessage()).data(data).build();
    }

    public static <T> ResponseData<T> fali() {
        return ResponseData.<T>builder().code(ResponseCode.FAIL.getCode()).success(false).message(ResponseCode.FAIL.getMessage()).build();
    }

    public static <T> ResponseData<T> fali(ResponseCode responseCode, T data) {
        return ResponseData.<T>builder().code(responseCode.getCode()).success(false).message(responseCode.getMessage()).data(data).build();
    }

    public static <T> ResponseData<T> fali(Integer code, String message, T data) {
        return ResponseData.<T>builder().code(code).success(false).message(message).data(data).build();
    }

    public static <T> ResponseData<T> ofResponse(boolean success) {
        if (success) {
            return ResponseData.<T>builder().code(ResponseCode.SUCCESS.getCode()).success(true).message(ResponseCode.SUCCESS.getMessage()).build();
        } else {
            return ResponseData.<T>builder().code(ResponseCode.SUCCESS.getCode()).success(false).message(ResponseCode.SUCCESS.getMessage()).build();
        }
    }
}
