package com.niallmcq.hello.world.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "UserServiceApi";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
                .requestMatchers().antMatchers("/user/**")
                .and().authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ADMIN')");

        // Create user
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/users")
                .access("hasRole('ADMIN')");

        // Get all users
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users")
                .access("hasRole('ADMIN')");

        // Get user
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/*")
                .access("hasRole('ADMIN')");

        // Update user
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/users/*")
                .access("hasRole('ADMIN')");

        // Delete user
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/users/*")
                .access("hasRole('ADMIN')");

        // Everything else has to be authenticated
        http.authorizeRequests().anyRequest().authenticated();
    }
}
