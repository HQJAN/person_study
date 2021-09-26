package com.xxx;

import com.xxx.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");

        UserController userController = (UserController)ac.getBean("userController");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号");
        String s = sc.nextLine();
        userController.check(s);



    }
}
