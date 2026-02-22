package com.midominio.group.app.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.midominio.group.app.spring.entity.ResultadoTest;
import com.midominio.group.app.spring.entity.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ResultadoTestRepository extends JpaRepository<ResultadoTest, Long> {
    
    /**
     * Busca todos los resultados de un usuario específico, ordenados por fecha descendente.
     * 
     * @param usuario El usuario
     * @param pageable Configuración de paginación
     * @return Página con los resultados del usuario
     */
    Page<ResultadoTest> findByUsuarioOrderByFechaRealizacionDesc(Usuario usuario, Pageable pageable);
    
    /**
     * Busca resultados por usuario y temática.
     * 
     * @param usuario El usuario
     * @param tematica La temática
     * @return Lista de resultados
     */
    List<ResultadoTest> findByUsuarioAndTematica(Usuario usuario, String tematica);
    
    /**
     * Busca resultados en un rango de fechas.
     * 
     * @param fechaInicio Fecha inicial
     * @param fechaFin Fecha final
     * @return Lista de resultados
     */
    List<ResultadoTest> findByFechaRealizacionBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
