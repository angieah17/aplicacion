package com.midominio.group.app.spring.repository;

import com.midominio.group.app.spring.entity.PreguntaMultiple;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio específico para preguntas de selección múltiple.
 * Por ahora no contiene métodos adicionales; servirá como base
 * para futuras consultas específicas de este tipo.
 */
public interface PreguntaMultipleRepository extends JpaRepository<PreguntaMultiple, Long> {

}
