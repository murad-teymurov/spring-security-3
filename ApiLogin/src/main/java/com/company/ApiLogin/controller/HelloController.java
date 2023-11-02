package com.company.ApiLogin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String greeting() {
        return " Hello gradle";
    }

    @GetMapping("/secured")
    public String secured() {
        return "if you see this, then you are logged in!!";
    }
}
