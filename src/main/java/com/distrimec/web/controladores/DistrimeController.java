package com.distrimec.web.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class DistrimeController {
 @GetMapping("/")
    public String mostrarhome(Model model) {
       
        return "home/home";
    }

    
}

