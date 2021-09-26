package com.xxx.dao;

import org.springframework.stereotype.Repository;

import javax.sql.rowset.spi.SyncProvider;

@Repository
public class UserDao {
    public boolean check(String name) {
        System.out.println("Dao");
        if(name.equals("123")){
            return true;
        }else {
            return false;
        }
    }
}
