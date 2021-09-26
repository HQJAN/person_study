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

   ```java
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
   ```

2. dao

   ```java
   public class UserDao02 {
   // 构造器注入
   
       public void test(){
           System.out.println("dao02.....");
       }
   }
   ```

3. xml

   ```
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:p="http://www.springframework.org/schema/p"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans.xsd">
   
   <!--
       构造器注入
   -->
       <bean name="userDao02" class="com.xxx.dao.UserDao02"/>
       <bean id="usrService02" class="com.xxx.service.UserService02">
           <constructor-arg name="userDao02" ref="userDao02"/>
           <constructor-arg name="host" value="loacal"/>
           <constructor-arg name="port" value="222"/>
       </bean>
   
   
   </beans>
   ```

4. main -- to test

5. ```java
       /*
       构造器注入
        */
       public static void main(String[] args) {
           ApplicationContext ac = new ClassPathXmlApplicationContext("spring03.xml");
           UserService02 userService02 =(UserService02)ac.getBean("usrService02");
           userService02.test();
       }
   }
   ```







#### （三）自动注入——@Resource

#### 直接使用语法糖@Resource注入

通过反射实现

默认通过byname得方式实现，如果找不到名字，则通过byType实现

实现Bean对象的自动注入

- 默认会根据bean标签的id属性值查找（属性字段名与bean标签的id属性值相等）
- 如果属性名名称未找到，则会根据类型（class）查找
- 属性字段可以提供set方法，也可不提供
- 注解可以声明属性在属性级别或set方法
- 可以设置name属性值，name属性值必须与bean标签的id属性值一致，如果设置了name属性值，就只会按照name属性值查找bean对象
- 当注入接口时，如果接口只有一个实现则正常实例化；如果接口存在多个实现，则需要使用name属性指定需要被实例化的bean对象



###### example

1. service

   ```java
   public class UserService03 {
       //自动注入，@Resource
       @Resource
       private UserDao03 userDao03;
   
   
       public void test(){
           System.out.println("service01.......");
           userDao03.test();
       }
   }
   ```

2. dao

   ```java
   public class UserDao03 {
       //自动注入，@Resource
       public void test(){
           System.out.println("dao02.....");
       }
   }
   ```

3. xml

   ```
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd">
   
   <!--    开启自动给装配（注入）-->
       <context:annotation-config/>
       <bean id="userDao03" class="com.xxx.dao.UserDao03"></bean>
       <bean id="userService03" class="com.xxx.service.UserService03"></bean>
       
   </beans>
   
   ```

4. main -- to test

   ```java
   public class Statr04 {
       /*
       自动注入@Resource
        */
       public static void main(String[] args) {
           ApplicationContext ac = new ClassPathXmlApplicationContext("spring04.xml");
           UserService03 userService02 =(UserService03)ac.getBean("userService03");
           userService02.test();
       }
   }
   ```






#### （四）自动注入——@Autowired



- 默认通过类型（Class类型）查找bean对象，与属性字段的名称无关
- 属性可以提供set方法，也可以不提供
- 注解可以声明在属性级别或set方法级别
- 可以添加@Qualifier结合，通过value属性值查找bean对象（value属性值必须设置，且值要与bean标签的id值一致）









## SpringIOC 扫描器

作用：可以对Bean对象进行统一的管理，简化的配置信息，提高开发效率

作用：

1. 在配置文件中开启自动扫描，设置扫描范围（引入context命名空间）

2. 在需求被实例化的Bean对象上添加指定的注解（声明在类级别，bean对象的id属性默认）

   -  Controller层：（控制层） @Controller

   - Service层：（业务逻辑层） @Service

   - Dao层：（数据访问层） @Repository

   - 任意类：@Compinent

     注：在开发过程中建议按照指定规则声明注释





## SpringIOC中Bean对象的作用域和生命周期

#### （一）Bean对象的作用域

容器在启动的情况下就实例化所有singleton的bean对象，并缓存在容器中

（默认情况下，被管理的bean只会IOC容器中存在一个实例，对于所有获取该Bean的操作Spring容器将只返回同一个Bean）

（lazy-init属性）（懒加载）

​	1） 如果false，则在IOC容器启动时会实例化bean对象，默认false

​	2）如果true，则在IOC容器启动时不会实例化Bean对象，在使用bean对象时才会实例化



使用懒加载的好处：

​	1）可以提前发现潜在的配置问题

​	2）Bean对象存在于缓存中，使用时不用再去实例化bean，加快程序运行效率



什么对象适合作为单例对象？

​	一般来说对于无状态或状态不可改变的对象适合使用单例模式。（不存在改变对象状态的成员变量）



prototype作用域

​	通过 scope="prototype" 设置bean的类型，每次向spring容器请求获取Bean都返回一个全新的Bean，相对于 singleton 来说就是不缓存Bean，每次都是一个根据Bean定义创建的全新Bean。



总结：

Bean对象作用域（主要区别是否放于缓存中）

1. 单例作用域（singleton 作用域）
2. 原型作用域





#### （二）Bean对象的生命周期

Spring容器中，Bean的生命周期分为四个阶段：

	- Bean的定义
	- Bean的初始化
	- Bean的使用
	- Bean的销毁



 1. 阶段一：Bean的定义

    在配置文件中定义bean标签，设置对应的id与class属性值

    

 2. 阶段二：Bean的初始化

    在Spring容器中，默认容器启动实例化Bean对象

    Spring查看实例化的两种方式：

    **方法一**：在bean标签上通过指定 init-methond 属性，属性值对应Bean对象中的方法，当bean对象实例化时，方法会被调用

    ```java
    public class RoleService{
        //定义初始化需要被调用的方法
        public void init(){
            System.out.println("testing...");
        }
    }
    ```

    ```xml
    <!-- 通过init-method属性指定方法-->
    <bean id ="roleService" class="com.xxx.service.RoleService" init-methond="init"></bean>
    ```

    

    方法二：实现 org.springframework.beans.factory.InitalizingBean 接口，接口中的方法会在Bean对象实例化时调用

    ```java
    /*
    通过接口查看Bean对象实例化的时机
    */
    
    public class RoleService implements InitalizingBean{
        @Override
        public void afterPropertiesSet() throw Exception{
            System.out.println("testing....");
        }
    }
    ```

    ```xml
    <bean id ="roleService" class="com.xxx.service.RoleService" ></bean>
    ```

    

 3. 阶段三：Bean的使用

    常用的两种使用方式：

    ```java
    //第一种
    ApplicationContext ac = new ClassPathXmlApplicationContext("配置文件");
    ac.getBean("id属性值")；
     
    //第二种
    BeanFactory ac =  new ClassPathXmlApplicationContext("配置文件");
    ac.getBean("id属性值")；
    ```

    

 4. 阶段四：Bean的销毁

    实现销毁方式（Spring容器会维护bean对象的管理，可以指定bean对象的销毁所要执行的方法）。

    1. 实现销毁方式（Spring容器会维护bean对象的管理，可以指定bean对象的销毁所要执行的方法）

    ```xml
    <bean id ="roleService" class="com.xxx.service.RoleService" destory-method = "destroy"></bean>
    ```

    2. 通过AbstractApplicationContext 对象，调用其close方法实现bean的销毁过程

    ```java
    //不能直接使用factory去调用
    AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    ctx.close();
    ```

    
