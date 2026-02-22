package com.midominio.group.app.spring.repository;

import com.midominio.group.app.spring.entity.PreguntaMultiple;
import com.midominio.group.app.spring.entity.Pregunta;
import com.midominio.group.app.spring.entity.PreguntaUnica;
import com.midominio.group.app.spring.entity.PreguntaVF;
import com.midominio.group.app.spring.entity.TipoPreguntaEnum;
import org.springframework.data.jpa.domain.Specification;

/**
 * Especificaciones para construir consultas dinámicas sobre Pregunta.
 * Permite combinar criterios de forma flexible (temática, tipo, activa).
 * 
 * root: obtener las columnas o hacer los joins.
 * criteriaBuilder: herramienta para construir las condiciones
 */
public class PreguntaSpecifications {

    /**
     * Filtra por temática exacta. Si es null o vacía, no aplica filtro.
     */
    public static Specification<Pregunta> conTematica(String tematica) {
        return (root, query, criteriaBuilder) -> {
            if (tematica == null || tematica.trim().isEmpty()) {
                return null; // No aplica filtro
            }
            return criteriaBuilder.equal(root.get("tematica"), tematica.trim());// // lado izquierdo: p.tematica - lado derecho: valor
        };
    }

    /**
     * Filtra por tipo de pregunta usando la columna discriminadora.
     * Si es null o vacío, no aplica filtro.
     * La comparación es case-insensitive.
     */
    public static Specification<Pregunta> conTipoPregunta(String tipoPregunta) {
        return (root, query, criteriaBuilder) -> {
            if (tipoPregunta == null || tipoPregunta.trim().isEmpty()) {
                return null; // No aplica filtro
            }

            TipoPreguntaEnum tipo = TipoPreguntaEnum.fromValue(tipoPregunta);

            return switch (tipo) {
                case VERDADERO_FALSO -> criteriaBuilder.equal(root.type(), PreguntaVF.class);
                case UNICA -> criteriaBuilder.equal(root.type(), PreguntaUnica.class);
                case MULTIPLE -> criteriaBuilder.equal(root.type(), PreguntaMultiple.class);
            };
        };
    }

    /**
     * Filtra solo preguntas activas (activa = true).
     */
    public static Specification<Pregunta> activas() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.isTrue(root.get("activa"));
    }

    /**
     * Filtra solo preguntas inactivas (activa = false).
     */
    public static Specification<Pregunta> inactivas() {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.isFalse(root.get("activa"));
    }

    /**
     * Filtra preguntas por estado (activa/inactiva).
     * Si es null, no aplica filtro (útil para admin que quiere ver todas).
     * 
     * @param activa true para activas, false para inactivas, null para todas
     */
    public static Specification<Pregunta> conEstado(Boolean activa) {
        return (root, query, criteriaBuilder) -> {
            if (activa == null) {
                return null; // No aplica filtro, devuelve todas
            }
            if (activa) {
                return criteriaBuilder.isTrue(root.get("activa"));
            } else {
                return criteriaBuilder.isFalse(root.get("activa"));
            }
        };
    }
    
    //Búsqueda busqueda por texto (like)
    public static Specification<Pregunta> textoEnEnunciado(String texto) {
        return (root, query, criteriaBuilder) -> {
            if (texto == null || texto.trim().isEmpty()) {
                return null;
            }
            // WHERE LOWER(p.enunciado) LIKE '%texto%'
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("enunciado")),
                "%" + texto.toLowerCase() + "%"
            );
        };
    }
    
    
}