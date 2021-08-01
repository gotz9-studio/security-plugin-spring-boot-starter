package com.gotz9.app;

import com.gotz9.plugin.security.boot.EnableSecurityPlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableSecurityPlugin(enableStorage = true)
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/index")
    public String index() {
        return "Hello";
    }

    @GetMapping("/access")
    public String access() {
        return "protected";
    }

}
