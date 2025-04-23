package edu.t1.configuration;

import edu.t1.aspect.LoggingAspect;
import edu.t1.aspect.utils.LoggingProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
public class LoggingConfiguration {

    @ConditionalOnProperty(value = "servlet.logging.enable", havingValue = "true")
    @Bean
    public LoggingAspect loggingSupport(LoggingProperties props) {
        return new LoggingAspect(props);
    }

}
