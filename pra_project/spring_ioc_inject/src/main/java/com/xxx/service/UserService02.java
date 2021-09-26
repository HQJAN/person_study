package com.xxx.service;

import com.xxx.dao.UserDao01;
import com.xxx.dao.UserDao02;

public class UserService02 {
    //构造器注入

    private UserDao02 userDao02;

    private String host;

    private Integer port;

    public UserService02(UserDao02 userDao02, String host, Integer port) {
        this.userDao02 = userDao02;
        this.host = host;
        this.port = port;
    }


    public void test(){
        System.out.println("service01.......");
        System.out.println("host:"+host+"\n"+"port:"+port);
        userDao02.test();
    }
}
