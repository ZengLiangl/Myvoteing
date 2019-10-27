package com.voting.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.voting.interceptor.LoginInterceptor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author: ll
 * @description:
 * @create: 2019-10-11 20:23
 **/
@Configuration
@Data
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //转换器
        FastJsonHttpMessageConverter fc=new FastJsonHttpMessageConverter();
        //创建配置
        FastJsonConfig config=new FastJsonConfig();
        config.setCharset(Charset.forName("UTF-8"));
        //注入配置
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fc.setFastJsonConfig(config);
        //注册转换器
        converters.add(fc);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建拦截器
        LoginInterceptor loginInterceptor=new LoginInterceptor();
        //注册
        List<String>  pattern=new ArrayList<>();
        pattern.add("/**");
        List<String> excludePattern=new ArrayList<>();
        excludePattern.add("/static/**");
        excludePattern.add("/toLogin");
        excludePattern.add("/toRegist");
        excludePattern.add("/login/**");
        excludePattern.add("/");
        excludePattern.add("/**/*.css");
        excludePattern.add("/**/*.js");
        excludePattern.add("/**/*.png");
        excludePattern.add("/**/*.jpg");
        excludePattern.add("/**/*.jpeg");
        excludePattern.add("/**/*.gif");
        excludePattern.add("/**/fonts/*");
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(pattern)
                .excludePathPatterns(excludePattern);
    
    }
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            @Override
            public String print(Date object, Locale locale) {
                return null;
            }
            /**
             * @param text 页面传过来的要转换的内容
             * @param locale
             * @return
             * @throws ParseException
             */
            @Override
            public Date parse(String text, Locale locale) throws ParseException {
//                System.out.println("传入日期：" + text);
                SimpleDateFormat[] sdf = new SimpleDateFormat[1];
                sdf[0] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                for (SimpleDateFormat sf : sdf) {
                    try {
                        return sf.parse(text);
                    } catch (ParseException e) {
                        continue;// 失败继续循环
                    }
                }
                return new Date();// 如果全部符合系统默认时间
            }
        });
    }
}
