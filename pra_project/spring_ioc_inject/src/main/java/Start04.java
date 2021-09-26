import com.xxx.dao.UserDao03;
import com.xxx.service.UserService01;
import com.xxx.service.UserService03;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start04 {

    ApplicationContext ac = new ClassPathXmlApplicationContext("spring03.xml");
    UserService03  userService01 = (UserService03) ac.getBean("userService03");


}
