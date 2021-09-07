package com.codegym.config;

import com.codegym.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and().authorizeRequests().antMatchers("/api/blogs/user").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .and().authorizeRequests().antMatchers("/api/blogs/home").hasRole("ADMIN")
                .and()
                .formLogin().successHandler(new SuccessHandler())
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Autowired
    AppUserService appUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }



}
