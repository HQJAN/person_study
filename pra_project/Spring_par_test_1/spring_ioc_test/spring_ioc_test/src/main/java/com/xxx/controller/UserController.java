package com.xxx.controller;

import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public void check(String name){
        System.out.println("controller");
        boolean flag = userService.checkA(name);
        if(flag){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
    }

}
