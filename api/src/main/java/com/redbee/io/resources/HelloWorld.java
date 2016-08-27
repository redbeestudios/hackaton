package com.redbee.io.resources;

import com.redbee.io.representation.Hello;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Channo on 14/07/16.
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
