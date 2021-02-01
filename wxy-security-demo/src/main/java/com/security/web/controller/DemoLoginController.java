package com.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoLoginController {

    @RequestMapping("/demo/login")
    public String demo() {
        return "demo-login";
    }
}
