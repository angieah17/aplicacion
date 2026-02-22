package com.midominio.group.app.spring.entity;

/**
 * Enum que define los roles disponibles en el sistema.
 */
public enum RoleEnum {
    USER("user"),      // Usuario regular que realiza tests
    ADMIN("admin");    // Administrador que gestiona preguntas
    
    private final String value;
    
    RoleEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    /**
     * Obtiene el rol a partir de su valor en string.
     */
    public static RoleEnum fromValue(String value) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Rol inválido: " + value);
    }
}
