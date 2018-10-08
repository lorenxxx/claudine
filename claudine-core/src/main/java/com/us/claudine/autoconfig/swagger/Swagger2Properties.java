package com.us.claudine.autoconfig.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author liangliang
 * @date 2018/10/8 11:21 PM
 */
@ConfigurationProperties(prefix = "claudine.swagger", ignoreInvalidFields = false)
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
