package com.giu.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.management.openmbean.ArrayType;
import java.util.Collection;

/**
 * Created by julian on 16/08/16.
 */
public class GcbaAuthentication implements Authentication {

    private Authentication authentication;
    private GcbaUserDetail gcbaUserDetail;

    public GcbaAuthentication(Authentication authentication, GcbaUserDetail gcbaUserDetail) {
        this.authentication = authentication;
        this.gcbaUserDetail = gcbaUserDetail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authentication.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return authentication.getCredentials();
    }

    @Override
    public Object getDetails() {
        return authentication.getDetails();
    }

    @Override
    public Object getPrincipal() {
        return gcbaUserDetail;
    }

    @Override
    public boolean isAuthenticated() {
        return authentication.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authentication.setAuthenticated(isAuthenticated);

    }

    @Override
    public String getName() {
        return authentication.getName();
    }
}
