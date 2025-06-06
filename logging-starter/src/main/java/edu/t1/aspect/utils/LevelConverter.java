package edu.t1.aspect.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LevelConverter implements Converter<String, LoggingLevel> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public LoggingLevel convert(String source) {
        if (!source.isBlank()) {
            return LoggingLevel.valueOf(source.toUpperCase());
        } else {
            return LoggingLevel.DEBUG;
        }
    }
}
