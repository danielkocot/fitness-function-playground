package de.codecentric.fitnessfunctionplayground.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting() throws InterruptedException {
        //Thread.sleep(300);
        return "Hello, World!";
    }
}
