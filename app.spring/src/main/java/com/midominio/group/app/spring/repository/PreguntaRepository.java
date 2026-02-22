package com.midominio.group.app.spring.repository;

import com.midominio.group.app.spring.entity.Pregunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repositorio base para todas las preguntas.
 * Contiene únicamente consultas genéricas aplicables a todos los tipos.
 */
public interface PreguntaRepository extends JpaRepository<Pregunta, Long>, JpaSpecificationExecutor<Pregunta> {

    // Buscar todas las preguntas activas 
    Page<Pregunta> findByActivaTrue(Pageable pageable);

    // Buscar preguntas por temática
    Page<Pregunta> findByTematica(String tematica, Pageable pageable);

    // Buscar preguntas activas por temática
    Page<Pregunta> findByTematicaAndActivaTrue(String tematica, Pageable pageable);


    // Contar cuántas preguntas activas existen
    long countByActivaTrue();

    // NOTE: La búsqueda por tipo se realiza mediante Specifications (ver PreguntaSpecifications.conTipoPregunta)
    // y no a través de métodos query-derived, porque el tipo se almacena en la columna discriminator
    // `tipo_pregunta` y no existe como atributo mapeado en la entidad base. Usar:
    //   preguntaRepository.findAll(spec, pageable)
    //   preguntaRepository.count(spec)
    
    
    /*JpaSpecificationExecutor: Spring Data JPA necesita saber que el repositorio acepta Specifications. 
    Ahora se tienen disponibles métodos: 

    // 1. Encontrar UN solo elemento que cumpla la Specification
    Optional<T> findOne(Specification<T> spec);
    
    // 2. Encontrar TODOS los elementos que cumplan la Specification
    List<T> findAll(Specification<T> spec);
    
    // 3. Encontrar todos con Specification Y paginación
    Page<T> findAll(Specification<T> spec, Pageable pageable);
    
    // 4. Encontrar todos con Specification Y ordenamiento
    List<T> findAll(Specification<T> spec, Sort sort);
    
    // 5. CONTAR cuántos elementos cumplen la Specification
    long count(Specification<T> spec);

     * */

}
