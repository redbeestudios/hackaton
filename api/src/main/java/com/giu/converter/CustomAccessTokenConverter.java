package com.giu.converter;

import com.giu.domain.GcbaAuthentication;
import com.giu.domain.GcbaUserDetail;
import com.giu.domain.User;
import com.giu.exception.EntityNotFoundException;
import com.giu.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by julian on 16/08/16.
 */
@Component
public class CustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserConverter converter;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication result = super.extractAuthentication(map);

        User user = userRepo.findByEmployeeID((String)map.get("user_name")).orElseThrow(EntityNotFoundException::new);//(String)map.get("user_name")
        GcbaAuthentication authentication = new GcbaAuthentication(result.getUserAuthentication(),
                converter.convertToUserDetail(user));
        return new OAuth2Authentication(result.getOAuth2Request(),authentication);
    }
}
