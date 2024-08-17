package com.distrimec.web.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.distrimec.web.config.ApplicationConfig;
import com.distrimec.web.modelos.entidades.Usuario;
import com.distrimec.web.servicios.CiudadService;
import com.distrimec.web.servicios.PasswordResetService;
import com.distrimec.web.servicios.UsuarioServicio;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, Model model) {
        logger.info("GET /login");
        if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
        return "login"; // Nombre de la vista de la página de login
    }
    
    @GetMapping("/olvido")
    public String showForgotPasswordForm() {
        return "olvido";
    }

    @PostMapping("/olvido")
    public String handleForgotPassword(@RequestParam("email") String email, Model model) {
        // Lógica para manejar el restablecimiento de contraseña
        passwordResetService.sendPasswordResetEmail(email);
        model.addAttribute("message", "Si el correo electrónico existe en nuestro sistema, recibirás un enlace para restablecer tu contraseña.");
        return "olvido";
    }

    @GetMapping("olvido/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        // Verificar el token (omitir por simplicidad)
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("olvido/reset-password")
    public String handleResetPassword(@RequestParam("token") String token, @RequestParam("password") String password, Model model) {
        // Verificar el token y restablecer la contraseña (omitir por simplicidad)
        boolean passwordResetSuccessful = passwordResetService.resetPassword(token, password);
        if (passwordResetSuccessful) {
            model.addAttribute("message", "Tu contraseña ha sido restablecida con éxito.");
        } else {
            model.addAttribute("error", "No se pudo restablecer la contraseña. Por favor, intenta nuevamente.");
        }
        return "login";
    }

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("ciudades", ciudadService.obtenerTodasLasCiudades());
        model.addAttribute("cargos", usuarioServicio.listarCargos());
        return "registro";
    }

    @PostMapping("/registro")
    public String registerUser(Usuario usuario, Model model) {
        usuarioServicio.registrarUsuario(usuario);
        model.addAttribute("message", "Usuario registrado con éxito.");
        return "login";
    }
}
