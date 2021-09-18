## spring的配置方式

pom.xml注入依赖

```
<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>5.2.4.RELEASE</version>
</dependency>
```



src.main.resources

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--
    定义Bean对象
    id          bean对象的唯一标识符
    class       bean对象（需要被实例化的bean对象）的全路径 包名+类名
    -->
    <bean id="userService" class="com.test.service.UserServices"/>

</beans>
```



## Java-SpringIOC 的四种注入方式

###### Java- Four ways to inject SpringIOC

#### （一）手动注入——set方法

背景：

在调用一些服务器的时候，存在形参为空的情况，因此在配置文件中，可以使用set方法注入

需要在类中添加set方法

主要方式：通过<bean>中添加<property>的方式注入

```
set 方法注入
    通过property 属性实现set方法注入
    name: 属性字段名称 ---也就不一定为属性的名字
    ref：对应bean标签的id属性值
    value：具体的值
```



###### example

1. service

```Java
package com.xxx.service;

import com.xxx.dao.UserDao01;

public class UserService01 {

    private UserDao01 userDao01;

    public void test(){
        System.out.println("service01.......");
    }
}
```

2. dao

```java
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
```

3. xml

```xml
<bean id="userService01" class="com.xxx.service.UserService01"/>
<bean name="userDao01" class="com.xxx.dao.UserDao01">
    <property name="userService01" ref="userService01"/>
</bean>
```

4. main ---use to test

```java
public static void main(String[] args) {
      ApplicationContext ac = new ClassPathXmlApplicationContext("spring02.xml");
      UserDao01 userDao01 =(UserDao01) ac.getBean("userDao01");
      userDao01.test();
 }
```



#### （二）手动注入——构造器注入

set方法需要一一注入，每次通过set的方式进行注入，但是这样会导致注入十分麻烦

需要在类中设置一个构造函数

构造器注入

​	通过constructor-arg标签实现构造器注入

​	name: 属性字段的名称

​	ref：对应bean标签的id属性值

​	value： 具体的值

​	index：参数的下标



###### example

1. service
2. dao
3. xml
4. main -- to test













