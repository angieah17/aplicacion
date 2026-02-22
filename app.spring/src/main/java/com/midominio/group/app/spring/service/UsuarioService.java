package com.midominio.group.app.spring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.midominio.group.app.spring.dto.UsuarioAuthDTO;
import com.midominio.group.app.spring.dto.UsuarioDTO;
import com.midominio.group.app.spring.dto.UsuarioCreateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.midominio.group.app.spring.entity.RoleEnum;
import com.midominio.group.app.spring.entity.Usuario;
import com.midominio.group.app.spring.exception.BadRequestException;
import com.midominio.group.app.spring.exception.ResourceNotFoundException;
import com.midominio.group.app.spring.repository.UsuarioRepository;

//Servicio para manejar la lógica de negocio relacionada con los usuarios,
//como el registro y la obtención del perfil autenticado.
//  Utiliza el repositorio para interactuar con la base de datos y el password encoder para hashear las contraseñas. 
// Devuelve DTOs para evitar exponer información sensible.

/*Diferente a UserDetailsImpl que tiene responsabilidad de seguridad: cargar usuario para que Spring Security autentique. */

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioAuthDTO register(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new BadRequestException("El nombre de usuario ya existe");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRole(RoleEnum.USER);
        usuario.setEnabled(true);

        Usuario saved = usuarioRepository.save(usuario);
        return toDto(saved);
    }

    public UsuarioAuthDTO getAuthenticatedProfile(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return toDto(usuario);
    }

    public Page<UsuarioDTO> list(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(this::usuarioToDto);
    }

    public UsuarioDTO get(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return usuarioToDto(usuario);
    }

    public UsuarioDTO create(UsuarioCreateDTO dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("El nombre de usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        try {
            usuario.setRole(dto.getRole() == null ? RoleEnum.USER : RoleEnum.valueOf(dto.getRole()));
        } catch (Exception ex) {
            usuario.setRole(RoleEnum.USER);
        }
        usuario.setEnabled(dto.getEnabled() == null ? true : dto.getEnabled());

        Usuario saved = usuarioRepository.save(usuario);
        return usuarioToDto(saved);
    }

    public UsuarioDTO update(Long id, UsuarioCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (dto.getUsername() != null && !dto.getUsername().equals(usuario.getUsername())) {
            if (usuarioRepository.existsByUsername(dto.getUsername())) {
                throw new BadRequestException("El nombre de usuario ya existe");
            }
            usuario.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRole() != null) {
            try {
                usuario.setRole(RoleEnum.valueOf(dto.getRole()));
            } catch (Exception ex) {
                // ignore invalid role and keep existing
            }
        }

        if (dto.getEnabled() != null) {
            usuario.setEnabled(dto.getEnabled());
        }

        Usuario saved = usuarioRepository.save(usuario);
        return usuarioToDto(saved);
    }

    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    private UsuarioDTO usuarioToDto(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getRole() == null ? null : usuario.getRole().name(),
            usuario.isEnabled()
        );
    }

    private UsuarioAuthDTO toDto(Usuario usuario) {
        return new UsuarioAuthDTO(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getRole().name(),
            usuario.isEnabled()
        );
    }
}
