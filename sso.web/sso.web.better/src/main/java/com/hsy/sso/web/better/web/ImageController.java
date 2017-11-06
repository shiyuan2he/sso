package com.hsy.sso.web.better.web;

import com.hsy.bean.dto.ResponseBodyBean;
import com.hsy.bean.vo.SessionBean;
import com.hsy.bean.web.BaseController;
import com.hsy.java.base.utils.VerificationCodeHelper;
import com.hsy.sso.base.common.constants.CommonConstant;
import com.hsy.sso.service.better.cache.SpringRedisTemplateCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/{length}",method = RequestMethod.GET)
    @ResponseBody
    public void getTicket(@PathVariable(value = "length") long length, HttpServletResponse response) throws IOException {
        _logger.info("【验证码生成器】正在生成{}位的验证码",length);
        VerificationCodeHelper image = VerificationCodeHelper.Instance() ;
        springRedisTemplateCache.putCacheWithExpireTime(CommonConstant.IMAGE_CODE_CACHE_KEY+image.getStr(),image.getStr(),1000 * 60) ;
        ServletOutputStream outputStream = response.getOutputStream() ;
        ImageIO.write(image.getImage(),"JPEG",outputStream) ;
        outputStream.flush();
        outputStream.close();
    }
}
