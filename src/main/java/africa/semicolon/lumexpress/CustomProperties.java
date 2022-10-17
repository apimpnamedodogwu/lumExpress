package africa.semicolon.lumexpress;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "com.cloudinary")
public class CustomProperties {

    private String cloud_name;
    private String api_key;
    private String api_secret;

}

