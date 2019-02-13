package com.niallmcq.hello.world.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "UserServiceApi";

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        final OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        resources.expressionHandler(expressionHandler);
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Create user
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/1.0/users").hasAuthority("ROLE_ADMIN");

        // Get all users
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/1.0/users").hasAuthority("ROLE_ADMIN");

        // Get user
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/1.0/users/{userId:\\d+}").access("hasAuthority('ROLE_ADMIN') or @fineGrainedPermissionEvaluator.hasPermission(#userId, authentication)");

        // Update user
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/1.0/users/{userId:\\d+}").hasAuthority("ROLE_ADMIN");

        // Delete user
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/1.0/users/{userId:\\d+}").hasAuthority("ROLE_ADMIN");

        // Everything else has to be authenticated
        http.authorizeRequests().anyRequest().authenticated();
    }
}
