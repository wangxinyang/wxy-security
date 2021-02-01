package com.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/filter")
public class FilterController {

    @GetMapping("/norequest")
    public String noRequestFilter(String username, Model model){
        System.out.println("username is:" + username);
//        System.out.println("password is:" + password);
        model.addAttribute("username", username);
//        model.addAttribute("password", password);
        return "test";
    }

    @GetMapping("/request")
    @ResponseBody
    public String requestFilter(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username is:"+username);
        System.out.println("password is:"+password);
        return "username is:" + username + "and password is:"+password;
    }
}
