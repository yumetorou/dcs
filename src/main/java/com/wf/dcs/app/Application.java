package com.wf.dcs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author ddevera
 */
@SpringBootApplication
@Controller
@EnableAutoConfiguration
public class Application {

    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "app/index.html";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
