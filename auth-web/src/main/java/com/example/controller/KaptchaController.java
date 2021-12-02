package com.example.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.example.result.ResponseData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangqiangqiang
 * @Description:
 * @date 2021/10/27 7:08 下午
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Resource
    private Kaptcha kaptcha;


    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public ResponseData validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        kaptcha.validate(code);
        return ResponseData.success();
    }

    @PostMapping("/validTime")
    public ResponseData validCustomTime(@RequestParam String code) {
        kaptcha.validate(code, 60);
        return ResponseData.success();
    }


}
