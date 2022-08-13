package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author : SGQ
 * @date : 2022-06-13 19:38
 **/
@Controller
public class RouterController {


    @RequestMapping({"/level1/{id}"})
    public String level1(@PathVariable("id") Integer id){
        return "views/level1/"+id;
    }
    @RequestMapping({"/level2/{id}"})
    public String level2(@PathVariable("id") Integer id){
        return "views/level2/"+id;
    }
    @RequestMapping({"/level3/{id}"})
    public String level3(@PathVariable("id") Integer id){
        return "views/level3/"+id;
    }
}