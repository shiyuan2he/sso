package com.hsy.sso.web.better;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.web.better
 * @date 2017/11/25 16:00
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class GenerateConfigToDb {

    @Test
    public void execute(){
        InputStream inputStream = GenerateConfigToDb.class.getResourceAsStream("/profiles/dev.properties") ;

        Properties properties = new Properties() ;
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
