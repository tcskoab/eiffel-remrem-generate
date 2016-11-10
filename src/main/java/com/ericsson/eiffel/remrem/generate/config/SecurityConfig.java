package com.ericsson.eiffel.remrem.generate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

// TODO Uncomment to enable AD backed security
//@Profile("!integration-test")
//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${activedirectory.domain")
    private String domain;

    @Value("${activedirectory.url}")
    private String url;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider = new
                ActiveDirectoryLdapAuthenticationProvider(domain, url);

        auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                .httpBasic()
                    .and()
                .csrf()
                    .disable();
    }
}
