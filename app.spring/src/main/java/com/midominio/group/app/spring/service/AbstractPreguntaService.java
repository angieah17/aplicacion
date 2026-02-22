package com.midominio.group.app.spring.service;

import com.midominio.group.app.spring.entity.Pregunta;
import com.midominio.group.app.spring.exception.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Servicio abstracto que sirve como CRUD común por tipo (VF, Única, Múltiple)
 * @param <T> Tipo de pregunta que extiende de Pregunta
 * Esto permite que Java sustituya todas las T por el tipo concreto en tiempo de compilación.
 * Al final, gracias a los genéricos, todos estos métodos funcionarán con el tipo concreto de cada pregunta.
 */
public abstract class AbstractPreguntaService<T extends Pregunta> {

    protected final JpaRepository<T, Long> repository;
    // Para PreguntaVFService esto es: JpaRepository<PreguntaVF, Long>

    protected AbstractPreguntaService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }
    
    public T crear(T pregunta) {
        // reglas comunes a todas las preguntas
        pregunta.setId(null);      // seguridad: forzar INSERT
        pregunta.setActiva(true);  // por defecto activa
        return repository.save(pregunta);
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    //Se usa soft delete, en vez de eliminar campos de la base de datos, se hace un borrado lógico.
    public T desactivar(Long id) {
        T pregunta = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"));

        pregunta.setActiva(false);
        return repository.save(pregunta);
    }

    public T activar(Long id) {
        T pregunta = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada"));

        pregunta.setActiva(true);
        return repository.save(pregunta);
    }

    public long count() {
        return repository.count();
    }

    public T save(T entidad) {
        return repository.save(entidad);
    }
}
