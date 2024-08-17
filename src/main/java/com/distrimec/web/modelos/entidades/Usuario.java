package com.distrimec.web.modelos.entidades;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{

    public Usuario() {
    }   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codusuario", nullable = false)
    private Integer codUsuario;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "apellido", length = 20)
    private String apellido;

    @Column(name = "cedula")
    private Integer cedula;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @Column(name = "token")
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudadid", referencedColumnName = "idciudad")
    private Ciudad ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo", referencedColumnName = "idcargo")
    private Cargo cargo;

    @Column(name = "contraseña")
    private String contraseña;

    @Enumerated(EnumType.STRING) 
    private Role role;

    


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null) {
            return List.of(); // Or handle the null case as appropriate for your application
        }
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.contraseña;
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.cedula);
    }
}