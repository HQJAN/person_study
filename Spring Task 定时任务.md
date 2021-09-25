### Spring Task 定时任务

##### Java 开发种定时任务的主要三种解决方案：

- 使用JDK自带的Timer (使用范围小)
  - 使用第三方组件Quartz（笨重）
- 使用Spring Task （结合前面两种）



### Spring Task配置方式：

- XML 配置
- 注解配置



#### （一）使用XML配置实现定时任务

```xml
<!--
	配置定时规则
		ref：指定的类，即任务类
		method：指定的即需要运行的方法
		corn：cornExpression表达式
-->

<task:scheduled-task>
    <!-- 每个两秒执行一次任务-->
    <task:scheduled ref ="taskJob" method="job1" cron="0/2  * * * *?"/>
     <!-- 每个五次任务-->
    <task:scheduled ref ="taskJob" method="job2" cron="0/5  * * * *?"/>
</task:scheduled-task>
```

