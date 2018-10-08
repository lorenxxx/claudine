package com.us.claudine.autoconfig.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author liangliang
 * @date 2018/10/6 1:25 PM
 */
@ConditionalOnClass({Docket.class})
@Slf4j
public class Swagger2AutoConfiguration {

    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket createRestApi() {
        StopWatch watch = new StopWatch();
        watch.start();
        log.debug("starting Swagger2 auto configuration...");

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .enable(swaggerEnabled)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.us.user"))
            .paths(PathSelectors.any())
            .build()
            .pathMapping("/");

        watch.stop();
        log.debug("started Swagger2 auto configuration in {} ms", watch.getTotalTimeMillis());

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("US-User")
            .description("Loren | Write the code, change the world.")
            .contact(new Contact("Loren", "https://lorenxxx.github.io/", "liangliangpro@outlook.com"))
            .version("1.0.0")
            .build();
    }

}
