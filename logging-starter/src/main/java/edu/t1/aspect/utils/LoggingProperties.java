package edu.t1.aspect.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("servlet.logging")
@Getter
@Setter
public class LoggingProperties {
    private LoggingLevel level;
    private Boolean enable;

}
