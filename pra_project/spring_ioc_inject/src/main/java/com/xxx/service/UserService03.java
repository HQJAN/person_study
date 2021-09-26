package com.xxx.service;

import com.xxx.dao.UserDao02;
import com.xxx.dao.UserDao03;

import javax.annotation.Resource;

public class UserService03 {
    //自动注入，@Resource
    @Resource
    private UserDao03 userDao03;


    public void test(){
        System.out.println("service01.......");
        userDao03.test();
    }
}
