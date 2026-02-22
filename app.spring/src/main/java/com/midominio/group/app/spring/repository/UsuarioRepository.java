package com.midominio.group.app.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midominio.group.app.spring.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Busca un usuario por su nombre de usuario.
     * 
     * @param username El nombre de usuario
     * @return Optional con el usuario si se encuentra
     */
    Optional<Usuario> findByUsername(String username);
    
    /**
     * Verifica si existe un usuario con el username especificado.
     * 
     * @param username El nombre de usuario
     * @return true si existe, false en caso contrario
     */
    boolean existsByUsername(String username);
}
