package com.midominio.group.app.spring.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.midominio.group.app.spring.entity.Pregunta;
import com.midominio.group.app.spring.entity.PreguntaMultiple;
import com.midominio.group.app.spring.entity.PreguntaUnica;
import com.midominio.group.app.spring.entity.PreguntaVF;
import com.midominio.group.app.spring.service.PreguntaMultipleService;
import com.midominio.group.app.spring.service.PreguntaSearchService;
import com.midominio.group.app.spring.service.PreguntaUnicaService;
import com.midominio.group.app.spring.service.PreguntaVFService;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

/**
 * Controller REST para la administración de preguntas.
 * 
 * Proporciona endpoints para:
 * - Ver todas las preguntas (activas e inactivas)
 * - Filtrar por temática, tipo y estado de manera combinable
 * - Crear, editar, activar y desactivar preguntas
 * - Soporte para paginación y ordenamiento
 * 
 * Este controller está pensado para interfaces de administración con listas desplegables.
 * NO incluye lógica de juego (eso está en JuegoPreguntaController).
 */
@RestController
@RequestMapping("/api/admin/preguntas")
@CrossOrigin(origins = "http://localhost:5173") 
@Tag(name = "Administración de preguntas", description = "Endpoints para crear/editar/filtrar preguntas (administración)")
public class AdminPreguntaController {
    
    private final PreguntaSearchService preguntaSearchService;
    private final PreguntaVFService preguntaVFService;
    private final PreguntaUnicaService preguntaUnicaService;
    private final PreguntaMultipleService preguntaMultipleService;

    public AdminPreguntaController(
            PreguntaSearchService preguntaSearchService,
            PreguntaVFService preguntaVFService,
            PreguntaUnicaService preguntaUnicaService,
            PreguntaMultipleService preguntaMultipleService) {
        this.preguntaSearchService = preguntaSearchService;
        this.preguntaVFService = preguntaVFService;
        this.preguntaUnicaService = preguntaUnicaService;
        this.preguntaMultipleService = preguntaMultipleService;
    }

    @Operation(summary = "Importar preguntas desde CSV")
    @PostMapping("/upload")
    public ResponseEntity<Integer> upload(@RequestParam("file") MultipartFile file) {
        int totalCreadas = preguntaSearchService.importarDesdeCSV(file);
        return ResponseEntity.ok(totalCreadas);
    }

    /**
     * Obtiene preguntas con filtros combinables.
     * 
     * Parámetros de filtrado (todos opcionales):
     * - tematica: filtra por temática exacta (String)
     * - tipo: filtra por tipo de pregunta (VERDADERO_FALSO, UNICA, MULTIPLE)
     * - activa: filtra por estado (true=activas, false=inactivas, sin parámetro=todas)
     * 
     * Parámetros de paginación (opcionales):
     * - page: número de página (por defecto 0)
     * - size: tamaño de página (por defecto 20)
     * - sort: campo y dirección de ordenamiento (por defecto id,desc)
     * 
     * Ejemplos de uso:
     * - GET /api/admin/preguntas → todas las preguntas
     * - GET /api/admin/preguntas?activa=true → solo activas
     * - GET /api/admin/preguntas?activa=false → solo inactivas
     * - GET /api/admin/preguntas?tematica=Programación → solo de Programación
     * - GET /api/admin/preguntas?tipo=MULTIPLE&activa=false → múltiples inactivas
     * - GET /api/admin/preguntas?tematica=Programación&tipo=VERDADERO_FALSO&activa=true&page=0&size=10
     * 
     * @param tematica Temática a buscar (opcional)
     * @param tipo Tipo de pregunta (opcional)
     * @param activa Estado de la pregunta (opcional)
     * @param pageable Configuración de paginación y ordenamiento
     * @return ResponseEntity con la página de preguntas que cumplen los criterios
     */
    @Operation(summary = "Obtener preguntas con filtros y paginación")
    @GetMapping
    public ResponseEntity<Page<Pregunta>> obtenerPreguntasConFiltros(
            @RequestParam(required = false) String tematica,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Boolean activa,
            @PageableDefault(size = 10) Pageable pageable) {
        
        Page<Pregunta> resultado = preguntaSearchService.buscarConFiltros(
            tematica, 
            tipo, 
            activa, 
            pageable
        );
        
        return ResponseEntity.ok(resultado);
    }

    /**
     * Busca preguntas por texto en el enunciado, opcionalmente combinado con otros filtros.
     * 
     * Parámetros:
     * - texto: texto a buscar en el enunciado (requerido)
     * - tematica: filtra por temática (opcional)
     * - tipo: filtra por tipo de pregunta (opcional)
     * - activa: filtra por estado (opcional)
     * 
     * Ejemplo:
     * - GET /api/admin/preguntas/buscar?texto=capital&tematica=Geografia&activa=true
     * 
     * @param texto Texto a buscar en el enunciado
     * @param tematica Temática a buscar (opcional)
     * @param tipo Tipo de pregunta (opcional)
     * @param activa Estado de la pregunta (opcional)
     * @param pageable Configuración de paginación
     * @return ResponseEntity con la página de preguntas encontradas
     */
    @Operation(summary = "Buscar preguntas por texto y filtros")
    @GetMapping("/buscar")
    public ResponseEntity<Page<Pregunta>> buscarPorTexto(
            @RequestParam String texto,
            @RequestParam(required = false) String tematica,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) Boolean activa,
            @PageableDefault(size = 10) Pageable pageable) {
        
        Page<Pregunta> resultado = preguntaSearchService.buscarPorTextoYFiltros(
            texto,
            tematica, 
            tipo, 
            activa, 
            pageable
        );
        
        return ResponseEntity.ok(resultado);
    }

    // ==================== OPERACIONES SOBRE PREGUNTA ESPECÍFICA ====================

    /**
     * Obtiene una pregunta específica por su ID.
     * 
     * @param id El ID de la pregunta
     * @return ResponseEntity con la pregunta encontrada
     */
    @Operation(summary = "Obtener pregunta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pregunta> obtenerPreguntaPorId(@PathVariable Long id) {
        Pregunta pregunta = preguntaSearchService.obtenerPorId(id);
        return ResponseEntity.ok(pregunta);
    }

    /**
     * Actualiza una pregunta de tipo Verdadero/Falso.
     * Actualiza todos los campos: enunciado, temática, explicación y respuesta correcta.
     * 
     * @param id El ID de la pregunta a actualizar
     * @param datos Los datos actualizados
     * @return ResponseEntity con la pregunta actualizada
     */
    @Operation(summary = "Actualizar pregunta Verdadero/Falso")
    @PutMapping("/verdadero-falso/{id}")
    public ResponseEntity<PreguntaVF> actualizarPreguntaVF(
            @PathVariable Long id, 
            @Valid @RequestBody PreguntaVF datos) {
        
        return preguntaVFService.findById(id)
                .map(p -> {
                    // Campos heredados
                    p.setEnunciado(datos.getEnunciado());
                    p.setTematica(datos.getTematica());
                    p.setExplicacion(datos.getExplicacion());
                    // Campo específico
                    p.setRespuestaCorrecta(datos.getRespuestaCorrecta());
                    return ResponseEntity.ok(preguntaVFService.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza una pregunta de opción única.
     * Actualiza todos los campos: enunciado, temática, explicación, opciones y respuesta correcta.
     * 
     * @param id El ID de la pregunta a actualizar
     * @param datos Los datos actualizados
     * @return ResponseEntity con la pregunta actualizada
     */
    @Operation(summary = "Actualizar pregunta de opción única")
    @PutMapping("/unica/{id}")
    public ResponseEntity<PreguntaUnica> actualizarPreguntaUnica(
            @PathVariable Long id, 
            @Valid @RequestBody PreguntaUnica datos) {
        
        return preguntaUnicaService.findById(id)
                .map(p -> {
                    // Campos heredados
                    p.setEnunciado(datos.getEnunciado());
                    p.setTematica(datos.getTematica());
                    p.setExplicacion(datos.getExplicacion());
                    // Campos específicos
                    p.getOpciones().clear();
                    p.getOpciones().addAll(datos.getOpciones());
                    p.setRespuestaCorrecta(datos.getRespuestaCorrecta());
                    return ResponseEntity.ok(preguntaUnicaService.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza una pregunta de opción múltiple.
     * Actualiza todos los campos: enunciado, temática, explicación, opciones y respuestas correctas.
     * 
     * @param id El ID de la pregunta a actualizar
     * @param datos Los datos actualizados
     * @return ResponseEntity con la pregunta actualizada
     */
    @Operation(summary = "Actualizar pregunta de opción múltiple")
    @PutMapping("/multiple/{id}")
    public ResponseEntity<PreguntaMultiple> actualizarPreguntaMultiple(
            @PathVariable Long id, 
            @Valid @RequestBody PreguntaMultiple datos) {
        
        return preguntaMultipleService.findById(id)
                .map(p -> {
                    // Campos heredados
                    p.setEnunciado(datos.getEnunciado());
                    p.setTematica(datos.getTematica());
                    p.setExplicacion(datos.getExplicacion());
                    // Campos específicos
                    p.getOpciones().clear();
                    p.getOpciones().addAll(datos.getOpciones());
                    p.getRespuestasCorrectas().clear();
                    p.getRespuestasCorrectas().addAll(datos.getRespuestasCorrectas());
                    return ResponseEntity.ok(preguntaMultipleService.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Activa una pregunta (la hace visible para el juego).
     * 
     * @param id El ID de la pregunta a activar
     * @return ResponseEntity con la pregunta activada
     */
    @Operation(summary = "Activar pregunta por ID")
    @PatchMapping("/{id}/activar")
    public ResponseEntity<Pregunta> activarPregunta(@PathVariable Long id) {
        Pregunta pregunta = preguntaSearchService.activar(id);
        return ResponseEntity.ok(pregunta);
    }

    /**
     * Desactiva una pregunta (soft delete - la oculta del juego).
     * 
     * @param id El ID de la pregunta a desactivar
     * @return ResponseEntity con la pregunta desactivada
     */
    @Operation(summary = "Desactivar pregunta por ID")
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Pregunta> desactivarPregunta(@PathVariable Long id) {
        Pregunta pregunta = preguntaSearchService.desactivar(id);
        return ResponseEntity.ok(pregunta);
    }

    // ==================== CREAR PREGUNTAS POR TIPO ====================

    /**
     * Crea una nueva pregunta de tipo Verdadero/Falso.
     * 
     * @param pregunta La pregunta a crear
     * @return ResponseEntity con la pregunta creada y status 201 CREATED
     */
    @Operation(summary = "Crear pregunta Verdadero/Falso")
    @PostMapping("/verdadero-falso")
    public ResponseEntity<PreguntaVF> crearPreguntaVF(@Valid @RequestBody PreguntaVF pregunta) {
        PreguntaVF preguntaCreada = preguntaVFService.crear(pregunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(preguntaCreada);
    }

    /**
     * Crea una nueva pregunta de opción única.
     * 
     * @param pregunta La pregunta a crear
     * @return ResponseEntity con la pregunta creada y status 201 CREATED
     */
    @Operation(summary = "Crear pregunta de opción única")
    @PostMapping("/unica")
    public ResponseEntity<PreguntaUnica> crearPreguntaUnica(@Valid @RequestBody PreguntaUnica pregunta) {
        PreguntaUnica preguntaCreada = preguntaUnicaService.crear(pregunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(preguntaCreada);
    }

    /**
     * Crea una nueva pregunta de opción múltiple.
     * 
     * @param pregunta La pregunta a crear
     * @return ResponseEntity con la pregunta creada y status 201 CREATED
     */
    @Operation(summary = "Crear pregunta de opción múltiple")
    @PostMapping("/multiple")
    public ResponseEntity<PreguntaMultiple> crearPreguntaMultiple(@Valid @RequestBody PreguntaMultiple pregunta) {
        PreguntaMultiple preguntaCreada = preguntaMultipleService.crear(pregunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(preguntaCreada);
    }
}
