package com.xxx.service;

import com.xxx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean checkA(String name){
        System.out.println("service");
        return userDao.check(name);
    }


}
