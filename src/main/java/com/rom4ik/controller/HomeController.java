package com.rom4ik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rom4ik
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping()
    public String home() {
        return "home";
    }
}
