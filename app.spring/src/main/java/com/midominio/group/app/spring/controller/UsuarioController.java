package com.midominio.group.app.spring.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.midominio.group.app.spring.dto.UsuarioCreateDTO;
import com.midominio.group.app.spring.dto.UsuarioDTO;
import com.midominio.group.app.spring.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios (administración)")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Listar usuarios (paginado)")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UsuarioDTO> list(Pageable pageable) {
        return usuarioService.list(pageable);
    }

    @Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.get(id));
    }

    @Operation(summary = "Crear nuevo usuario")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioCreateDTO dto) {
        UsuarioDTO created = usuarioService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Actualizar usuario por ID")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioCreateDTO dto) {
        return ResponseEntity.ok(usuarioService.update(id, dto));
    }

    @Operation(summary = "Eliminar usuario por ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
