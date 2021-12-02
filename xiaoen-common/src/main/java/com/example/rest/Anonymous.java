package com.example.rest;

import java.lang.annotation.*;

/**
 * @author jiangqiangqiang
 * @description: 匿名访问
 * @date 2021/11/20 5:51 下午
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Anonymous {
}
