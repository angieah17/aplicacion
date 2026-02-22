package com.midominio.group.app.spring.security;

import java.util.Collection;
import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.midominio.group.app.spring.entity.Usuario;

public class UserDetailsImpl implements UserDetails {
    

	private static final long serialVersionUID = 1L;
	
	private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name())
        );
    }

	@Override
	public @Nullable String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getUsername();
	}
    
	@Override
	public boolean isEnabled() {
		return usuario.isEnabled();
	}

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }


}