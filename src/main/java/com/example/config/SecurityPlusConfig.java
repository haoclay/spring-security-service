package com.example.config;

import com.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author : SGQ
 * @date : 2022-06-15 17:32
 **/

//@EnableWebSecurity
public class SecurityPlusConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService userService;

    // 加密对象注入IOC容器
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 1.指定认证对象的来源(内存或者数据库),指定加密方式
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    //2. SpringSecurity配置相关信息
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 释放静态资源，指定拦截规则，指定自定义的认证和退出页面，csrf配置等
        http.authorizeRequests()
                // 指定拦截规则
                .antMatchers("/","/login","/toLogin","/register", "/failer", "/css/**", "/img/**", "/js/**").permitAll()  //释放这些资源，不拦截
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
              //所有资源都需要这些角色中的一个
                http.formLogin();
                http.csrf().disable();
    }
}
