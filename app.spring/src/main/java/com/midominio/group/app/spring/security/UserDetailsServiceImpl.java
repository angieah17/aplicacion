package com.midominio.group.app.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.midominio.group.app.spring.entity.Usuario;
import com.midominio.group.app.spring.repository.UsuarioRepository;

/* Servicio para cargar al usuario por su nombre de usuario */


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado"));

        return new UserDetailsImpl(usuario);
    }
}

