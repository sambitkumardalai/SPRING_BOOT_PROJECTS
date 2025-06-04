package com.scm.backend.scm_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;

import com.scm.backend.scm_backend.entity.User;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
            org.springframework.web.servlet.config.annotation.CorsRegistry cors) {

        config.setBasePath(AppConstants.API_BASE_PATH);
        config.exposeIdsFor(User.class);
        config.setDefaultPageSize(AppConstants.PAGE_SIZE);
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
    }
}