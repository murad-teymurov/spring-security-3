package com.company.ApiLogin.controller;

import com.company.ApiLogin.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String secured(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "if you see this, then you are logged in!!" + userPrincipal.getEmail();
    }
}
