package com.giu.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.repository.config.EnableLdapRepositories;

import javax.naming.ldap.LdapContext;

/**
 * Created by martin on 18/07/16.
 */
@Configuration
@EnableLdapRepositories(basePackages = "com.giu.domain")
public class LdapConfig {


    @Value("${ldap.url}")
    private String ldapURL;

    @Value("${ldap.userDn}")
    private String userDn;

    @Value("${ldap.baseDn}")
    private String baseDn;

    @Value("${ldap.password}")
    private String password;

    @Bean
    public LdapContextSource contextSource () {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(ldapURL);
        contextSource.setBase(baseDn);
        contextSource.setUserDn(userDn);
        contextSource.setPassword(password);
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        LdapTemplate template = new LdapTemplate(contextSource());
        template.setIgnorePartialResultException(true);
        template.setIgnoreNameNotFoundException(true);
        return template;
    }


}



