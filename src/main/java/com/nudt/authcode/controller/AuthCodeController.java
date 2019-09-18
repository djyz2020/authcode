package com.nudt.authcode.controller;

import com.google.code.kaptcha.Producer;
import com.nudt.authcode.config.KaptchaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by haibozhang on 2019/9/18.
 */
@Controller
public class AuthCodeController {

    private Logger logger = LoggerFactory.getLogger(KaptchaConfig.class);
    @Autowired
    private Producer producer;

    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        logger.info("系统后台生成文字验证码；" + text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
