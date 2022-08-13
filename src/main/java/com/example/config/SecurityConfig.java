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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author : SGQ
 * @date : 2022-06-13 19:50
 **/

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService userService;
    // 加密对象注入IOC容器
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 正常配置其他安全相关的内容

        // 将登录框关闭

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasAnyAuthority("vip1")
                .antMatchers("/level2/**").hasAnyAuthority("vip2")
                .antMatchers("/level3/**").hasAnyAuthority("vip3");

        //没有权限直接跳登录页
        http.formLogin().loginPage("/toLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index");
//                .successHandler(successHandler());
        http.logout().logoutSuccessUrl("/");
        http.rememberMe();

        http.csrf().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*            auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                    .withUser("sgq").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2")
                    .and()
                    .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3");*/
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    SavedRequestAwareAuthenticationSuccessHandler successHandler(){
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("/index");
        handler.setTargetUrlParameter("target");
        return handler;
    }



}