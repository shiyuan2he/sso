package com.hsy.sso.web.better;
import com.hsy.java.util.spring.SpringWebJunitBase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
/**
 * @author heshiyuan
 * @description <p></p>
 * @path sso/com.hsy.sso.web.better
 * @date 2017/11/14 20:05
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@sina.com
 * Copyright (c) 2017 shiyuan4work@sina.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        {
            "classpath:/spring/spring-context.xml",
            "classpath:/spring/spring-webmvc.xml"
        }
)
public class SpringJunitBase extends SpringWebJunitBase{

    @Autowired private WebApplicationContext wac ;
    private MockMvc mockMvc ;

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(new CharacterEncodingFilter(),"/*")
                .build();
    }
    @Override
    public MockMvc getMockMvc() {
        return this.mockMvc;
    }
}
