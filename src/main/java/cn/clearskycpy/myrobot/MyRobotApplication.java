package cn.clearskycpy.myrobot;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.sql.DataSourceDefinition;

/**
 * @description:
 * @author：ClearSky
 * @date: ${DATE}
 * @Copyright： https://clearskycpy.cn
 */
// @MapperScan("cn.clearskycpy.myrobot.mapper")
@SpringBootApplication// (exclude={DataSourceAutoConfiguration.class})  // 不自动注入数据源
public class MyRobotApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyRobotApplication.class, args);
    }
}
