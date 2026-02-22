package com.midominio.group.app.spring.dto;


/* Clase DTO para representar la información del usuario autenticado que se devolverá al cliente. Contiene campos como id, username, role y enabled.
Con esto se evita exponer directamente la password hasheada en la entidad */


public class UsuarioAuthDTO {

    private Long id;
    private String username;
    private String role;
    private boolean enabled;

    public UsuarioAuthDTO() {
    }

    public UsuarioAuthDTO(Long id, String username, String role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
