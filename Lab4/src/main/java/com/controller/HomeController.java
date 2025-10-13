package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/index")
    public String index(Model model) {
        return "/home/index";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "/home/about";
    }
}
