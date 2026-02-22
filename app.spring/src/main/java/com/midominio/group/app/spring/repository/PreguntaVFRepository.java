package com.midominio.group.app.spring.repository;

import com.midominio.group.app.spring.entity.PreguntaVF;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio específico para preguntas Verdadero/Falso.
 * Por ahora no contiene métodos adicionales; servirá como base
 * para futuras consultas específicas de este tipo.
 */
public interface PreguntaVFRepository extends JpaRepository<PreguntaVF, Long> {

}
