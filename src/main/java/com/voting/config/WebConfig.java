package com.voting.config;

import com.voting.listener.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ll
 * @description:
 * @create: 2019-10-11 23:30
 **/
@Configuration
public class WebConfig {
    
    /**
     * 创建监听器
     */
    @Bean
    public SessionListener getAppListener(){
        return new SessionListener();
    }

    /**
     * 注册listener
     */
    public ServletListenerRegistrationBean<SessionListener> servletContextListener(SessionListener sessionListener){
        ServletListenerRegistrationBean<SessionListener> servletListenerRegistrationBean=new ServletListenerRegistrationBean<>();
        //注入监听器
        servletListenerRegistrationBean.setListener(sessionListener);
        return servletListenerRegistrationBean;
    }
}
