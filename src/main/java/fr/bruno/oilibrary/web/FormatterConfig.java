package fr.bruno.oilibrary.web;

import fr.bruno.oilibrary.model.BaseBookType;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class used to add some formatters
 *
 * @author Bruno Maury
 */
@Configuration
public class FormatterConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, BaseBookType.class, BaseBookType::fromJsonValue);
    }
}
