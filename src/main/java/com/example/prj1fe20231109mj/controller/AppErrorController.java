package com.example.prj1fe20231109mj.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(){
        return "/";
    }

}
