package com.distrimec.web.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.distrimec.web.config.ApplicationConfig;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);


    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, Model model) {
        logger.info("GET /login");
        if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
        return "login"; // Nombre de la vista de la página de login
    }
    

}
