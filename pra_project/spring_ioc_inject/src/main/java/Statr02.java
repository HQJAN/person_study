import com.xxx.dao.UserDao01;
import com.xxx.service.UserService01;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.rmi.server.UnicastServerRef;

public class Statr02 {

    /*
    set 方式注入
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring02.xml");
        UserDao01 userDao01 =(UserDao01) ac.getBean("userDao01");
        userDao01.test();
    }

}
