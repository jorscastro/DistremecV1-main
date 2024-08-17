package com.distrimec.web.servicios;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.distrimec.web.modelos.entidades.Usuario;
import com.distrimec.web.repositorios.UsuarioRepository;

@Service
public class PasswordResetService {
    @Autowired
    private JavaMailSender mailSender;

    private final UsuarioRepository usuarioRepository;

 
    public PasswordResetService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void sendPasswordResetEmail(String email) {
        // Generar un token único para el restablecimiento de contraseña
        String token = UUID.randomUUID().toString();

        // Guardar el token y el correo electrónico en la base de datos (omitir por simplicidad)
        Usuario usuario = usuarioRepository.findByCorreo(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setToken(token);
        usuarioRepository.save(usuario);
        // Crear el enlace de restablecimiento de contraseña
        String resetUrl = "http://localhost:8080/olvido/reset-password?token=" + token;

        // Crear el mensaje de correo electrónico
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Restablecimiento de contraseña");
        message.setText("Para restablecer tu contraseña, haz clic en el siguiente enlace: " + resetUrl);

        // Enviar el correo electrónico
        mailSender.send(message);
    }

    public boolean resetPassword(String token, String password){
        // Verificar el token y restablecer la contraseña (omitir por simplicidad)
        Usuario usuario = usuarioRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Token no válido"));
        usuario.setToken(null);
        // Encriptar la contraseña
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        usuario.setContraseña(encodedPassword);
        usuarioRepository.save(usuario);
        return true;
    
    }
}
