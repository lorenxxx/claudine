package com.us.claudine.autoconfig.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liangliang
 * @date 2018/10/6 1:25 PM
 */
@EnableSwagger2
@Configuration
@ConditionalOnClass({Docket.class})
@Slf4j
public class Swagger2AutoConfiguration {

    @Autowired
    Swagger2Properties swagger2Properties;

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket createRestApi(Swagger2Properties swagger2Properties) {
        StopWatch watch = new StopWatch();
        watch.start();
        log.debug("starting Swagger2 auto configuration...");

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(swagger2Properties.getTitle())
                .description(swagger2Properties.getDescription())
                .contact(new Contact(swagger2Properties.getAuthor(), swagger2Properties.getUrl(), swagger2Properties.getEmail()))
                .version(swagger2Properties.getVersion())
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .enable(swagger2Properties.getEnabled())
            .select()
            .apis(RequestHandlerSelectors.basePackage(swagger2Properties.getBasePackage()))
            .paths(PathSelectors.any())
            .build()
            .pathMapping(swagger2Properties.getPathMapping());

        watch.stop();
        log.debug("started Swagger2 auto configuration in {} ms...", watch.getTotalTimeMillis());

        return docket;
    }

}
