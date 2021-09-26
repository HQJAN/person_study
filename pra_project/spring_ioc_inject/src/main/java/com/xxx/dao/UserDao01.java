package com.xxx.dao;

import com.xxx.service.UserService01;

public class UserDao01 {

    UserService01 userService01;

    public void test(){
        System.out.println("dao01.....");
        userService01.test();
    }

    public void setUserService01(UserService01 userService01) {
        this.userService01 = userService01;
    }

    public UserService01 getUserService01() {
        return userService01;
    }
}
