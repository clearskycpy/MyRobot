package cn.clearskycpy.myrobot.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description: 接口文档配置类  使用的Knife4j
 * @author：clearSky
 * @date: 2023/9/18
 * @Copyright： clearskycpy.cn
 */
@EnableKnife4j
@Configuration
public class Knife4jConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于讯飞大模型api 进行二次开发实现的聊天机器人")
                .description("本文档描述了该项目所有的接口信息")
                .version("0.0.1-SNAPSHOT")
                .contact(new Contact("clearSky", "clearskycpy.cn", "cpy20021234@163.com"))
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.clearskycpy.myrobot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
