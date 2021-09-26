import com.xxx.service.UserService02;
import com.xxx.service.UserService03;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
