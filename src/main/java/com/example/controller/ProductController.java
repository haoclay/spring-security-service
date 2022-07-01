package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : SGQ
 * @date : 2022-06-15 18:40
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/findAll")
    //表示当前类中findAll方法需要 ROLE_ADMIN或者 ROLE_PRODUCT才能访问
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PRODUCT')")
    public String findAll(){
        return "product-list";
    }
}
