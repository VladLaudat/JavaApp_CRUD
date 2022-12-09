package com.vladlaudat.javaapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String showhomePage(){
        System.out.println("main controller");
        return "index";
    }

}
