package kr.megaptera.wherewego.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String home() {
        return "Hello, world!";
    }
}
