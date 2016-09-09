package com.sample.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the Jersey Servlet.
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        this.packages("com.sample.rest");
    }
}
