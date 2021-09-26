import com.xxx.dao.UserDao01;
import com.xxx.service.UserService01;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Statr01 {


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring01.xml");
        UserService01 userService01 = (UserService01)ac.getBean("userService01");
        userService01.test();


    }

}
