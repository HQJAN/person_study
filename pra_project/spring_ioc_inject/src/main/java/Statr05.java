import com.xxx.service.UserService03;
import com.xxx.service.UserService04;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Statr05 {

    /*
    自动注入@Autowired
     */
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring05.xml");
        UserService04 userService02 =(UserService04)ac.getBean("userService04");
        userService02.test();
    }
}
