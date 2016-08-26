package com.giu.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by julian on 09/08/16.
 */

@Service
public class PasswordGeneratorService {


    public String encodePassword(String pwd) {
        // Method la cual seteamos las password
        String quotedPassword = "\"" + pwd + "\"";
        char unicodePwd[] = quotedPassword.toCharArray();
        byte pwdArray[] = new byte[unicodePwd.length * 2];

        for (int i = 0; i < unicodePwd.length; i++) {
            pwdArray[i * 2 + 1] = (byte) (unicodePwd[i] >>> 8);
            pwdArray    [i * 2 + 0] = (byte) (unicodePwd[i] & 0xff);

        }

        return new String(pwdArray);
    }


    public String generatorPassword(){
        String SALTCHARS = "ABVWXYZ123456*#";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
