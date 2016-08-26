package com.giu.service;

import com.giu.domain.User;

/**
 * Created by martin on 26/07/16.
 */
public interface RegistrationService {

    public void register(User user);

    public void sendMeNewPassword(User user,String password);

}
