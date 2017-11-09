package com.hsy.sso.web.better.web;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.hsy.bean.web.BaseController;
import com.hsy.java.base.utils.VerificationCodeHelper;
import com.hsy.sso.base.common.constants.CommonConstant;
import com.hsy.sso.dao.redis.cache.SpringRedisTemplateCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author heshiyuan
 * @description <p>验证码</p>
 * @path sso/com.hsy.sso.web.better.web
 * @date 2017/10/27 10:40
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@RestController
@RequestMapping("/sso/image")
public class ImageController extends BaseController {

    private Logger _logger = LoggerFactory.getLogger(this.getClass()) ;

    @Autowired
    SpringRedisTemplateCache springRedisTemplateCache ;

    @RequestMapping(value = "/code",method = RequestMethod.GET)
    public void getCode(HttpServletResponse response) throws IOException {
        _logger.info("【验证码生成器】正在生成验证码");
        VerificationCodeHelper verificationCodeHelper = VerificationCodeHelper.getInstance();
        String strCode = verificationCodeHelper.getStr() ;
        _logger.info("【验证码生成器】生成的验证码是：{}",strCode);
        strCode = strCode.toUpperCase() ;
        springRedisTemplateCache.putCacheWithExpireTime(CommonConstant.IMAGE_CODE_CACHE_KEY+strCode,strCode, 60) ;
        _logger.info("【验证码生成器】生成验证码放入redis中，key={}",CommonConstant.IMAGE_CODE_CACHE_KEY+strCode);
        ServletOutputStream outputStream = response.getOutputStream() ;
        ImageIO.write(verificationCodeHelper.getImage(),"JPEG",outputStream) ;
        outputStream.flush();
        outputStream.close();
        _logger.info("【验证码生成器】生成验证码完毕");
    }
    private Producer captchaProducer = null;
    @Autowired
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }
    @RequestMapping(value = "/kaptchaCode",method = RequestMethod.GET)
    public void kaptchaCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
        _logger.info("【验证码生成器】正在生成验证码");
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText().toUpperCase();
        _logger.info("【验证码生成器】生成的验证码是：{}",capText);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        springRedisTemplateCache.putCacheWithExpireTime(CommonConstant.IMAGE_CODE_CACHE_KEY+capText,capText, 60) ;
        _logger.info("【验证码生成器】生成验证码放入redis中，key={}",CommonConstant.IMAGE_CODE_CACHE_KEY+capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        _logger.info("【验证码生成器】生成验证码完毕");
    }
}
