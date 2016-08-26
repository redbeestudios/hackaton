package com.giu.resources;

import com.giu.representation.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by martin on 14/07/16.
 */
@RestController
public class HelloWorld {


    @RequestMapping(value = "/hello")
    public Hello sayHello() {
        Hello result = new Hello();
        result.setMessage("Hello world!");
        return result;
    }

}
