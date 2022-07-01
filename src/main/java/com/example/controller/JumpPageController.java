package com.example.controller;

import com.example.pojo.SysUser;
import com.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : SGQ
 * @date : 2022-06-16 16:16
 **/
@Controller
public class JumpPageController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @PostMapping("/register")
    public String register(@RequestBody SysUser sysUser){
        System.out.println("user-->"+sysUser);
        if(sysUserService.addSysUser(sysUser)){
            return "views/login";
        }else {
            return "redirect:/toRegister";
        }

    }

    @GetMapping({"/toLogin"})
    public String login(){
        return "views/login";
    }

    @GetMapping("/toRegister")
    public String toRegister(HttpServletRequest request) {
        return "register";
    }
    @GetMapping("/toSuccess")
    public String toSuccess(){
        return "success";
    }
}
