package com.redbee.io;

import com.redbee.io.filter.CORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * Created by Channo on 14/12/15.
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class})
public class Application {

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }



    public void configure(HttpSecurity http) throws Exception {

        http

                .addFilterBefore(new CORSFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .csrf().disable()

                .authorizeRequests().anyRequest().permitAll().and().httpBasic().disable();

    }

}





