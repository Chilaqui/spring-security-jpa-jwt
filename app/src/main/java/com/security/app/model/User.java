package com.security.app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Permission permission : role.getPermissions()){
            authorities.add(new SimpleGrantedAuthority("PERM_" + permission.name()));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return authorities;
        
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Asumiendo que la cuenta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Asumiendo que la cuenta nunca está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Asumiendo que las credenciales nunca expiran
    }

    @Override
    public boolean isEnabled() {
        return true; // Asumiendo que el usuario siempre está habilitado
    }
    
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
