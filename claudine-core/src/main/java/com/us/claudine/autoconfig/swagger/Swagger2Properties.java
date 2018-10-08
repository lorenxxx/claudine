package com.us.claudine.autoconfig.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liangliang
 * @date 2018/10/8 11:21 PM
 */
@Component
@ConfigurationProperties(prefix = "claudine.swagger")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Swagger2Properties {

    private Boolean enabled = false;

    private String title = "Claudine API";

    private String description = "Claudine | Write the code, change the world.";

    private String version = "1.0.0";

    private String author = "Loren";

    private String url = "https://www.us.com/claudine/";

    private String email = "liangliangpro@outlook.com";

    private String basePackage = "com";

    private String pathMapping = "/";

}
