import com.xxx.dao.UserDao01;
import com.xxx.service.UserService02;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Statr03 {

    /*
    构造器注入
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring03.xml");
        UserService02 userService02 =(UserService02)ac.getBean("usrService02");
        userService02.test();
    }
}
