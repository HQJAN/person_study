package com.xxx.service;

import com.xxx.dao.UserDao03;
import com.xxx.dao.UserDao04;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class UserService04 {
    //自动注入@Autowired
    @Autowired
    private UserDao04 userDao04;


    public void test(){
        System.out.println("service01.......");
        userDao04.test();
    }
}
