package tue.shop4cf.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "fiware")
public class FiwareProperties {
    private String orionldUrl;
    private String subscriptionUrl;
}
