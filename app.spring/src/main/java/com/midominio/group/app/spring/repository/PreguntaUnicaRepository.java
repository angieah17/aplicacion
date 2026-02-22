package com.midominio.group.app.spring.repository;

import com.midominio.group.app.spring.entity.PreguntaUnica;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio específico para preguntas Unicas.
 * Por ahora no contiene métodos adicionales; servirá como base
 * para futuras consultas específicas de este tipo.
 */
public interface PreguntaUnicaRepository extends JpaRepository<PreguntaUnica, Long> {

}
