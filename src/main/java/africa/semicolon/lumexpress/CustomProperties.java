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

    String cloud_name;
    String api_key;
    String api_secret;

}

