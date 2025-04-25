package edu.t1;

import edu.t1.configuration.LoggingConfiguration;
import edu.t1.controller.UserController;
import edu.t1.controller.UserControllerV2;
import edu.t1.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        UserController bean2 = context.getBean(UserController.class);
        bean2.getSomething();
        bean2.getById("hello");
        UserControllerV2 b3 = context.getBean(UserControllerV2.class);
        b3.gett();
    }
}