package com.wf.dcs.app.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ddevera
 */
@RestController
public class HelloResource {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
