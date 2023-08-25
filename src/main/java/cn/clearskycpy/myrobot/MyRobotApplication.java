package cn.clearskycpy.myrobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.sql.DataSourceDefinition;

/**
 * @description:
 * @author：ClearSky
 * @date: ${DATE}
 * @Copyright： https://clearskycpy.cn
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class MyRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRobotApplication.class, args);
    }

}
