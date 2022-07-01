package com.example.controller;

import com.example.pojo.SysUser;
import com.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author : SGQ
 * @date : 2022-06-15 18:39
 **/
@Controller
@RequestMapping("/user")
@Secured("ROLE_ADMIN") 	//表示当前类中所有方法需要 ROLE_ADMIN才能访问
public class UserController {

    @Autowired
    private SysUserService userService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<SysUser> list = userService.findAll();
        model.addAttribute("list", list);
        return "user-list";
    }

}
