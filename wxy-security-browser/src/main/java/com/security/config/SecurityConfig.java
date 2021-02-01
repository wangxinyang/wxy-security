package com.security.config;

import com.security.core.authorize.AuthorizeConfigManager;
import com.security.core.config.AbstractChannelSecurityConfig;
import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter filter = new ValidateCodeFilter();
//        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        applyPasswordAuthenticationConfig(http);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }


}
