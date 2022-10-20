package co.uk.code.challenge.samuel.catalano.zilch.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@Configuration
public class DataSourceConfiguration {

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        final ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
}