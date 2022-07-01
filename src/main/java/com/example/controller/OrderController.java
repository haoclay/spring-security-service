package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

/**
 * @author : SGQ
 * @date : 2022-06-15 18:41
 **/
@Controller
@RequestMapping("/order")
@RolesAllowed({"ROLE_ADMIN","ROLE_USER"}) //表示当前类中所有方法都需要ROLE_ADMIN或者ROLE_User才能访问
public class OrderController {

    @RequestMapping("/findAll")
    public String findAll(){
        return "order-list";
    }
}
