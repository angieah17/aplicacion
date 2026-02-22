package com.midominio.group.app.spring.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.midominio.group.app.spring.dto.TestHistorialDTO;
import com.midominio.group.app.spring.dto.TestPlayDTO;
import com.midominio.group.app.spring.dto.TestResultDTO;
import com.midominio.group.app.spring.dto.TestSubmitDTO;
import com.midominio.group.app.spring.service.TestService;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestión de tests evaluables.
 * 
 * Endpoints:
 * - GET /api/tests - Genera un nuevo test con filtros opcionales
 * - POST /api/tests/submit - Corrige un test y guarda el resultado
 * - GET /api/tests/historial - Obtiene el historial de tests realizados por el usuario
 * 
 * Requiere autenticación para todos los endpoints.
 */
@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "http://localhost:5173") 
@Tag(name = "Tests", description = "Operaciones para generar, enviar y consultar tests")
public class TestController {
    
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * Genera un nuevo test usando filtros opcionales.
     * 
     * Parámetros opcionales:
     * - tematica: Filtra por temática específica
     * - tipoPregunta: Filtra por tipo (VERDADERO_FALSO, UNICA, MULTIPLE)
     * - limite: Número de preguntas (por defecto 10)
     * 
     * @param tematica Filtro de temática (opcional)
     * @param tipoPregunta Filtro de tipo de pregunta (opcional)
     * @param limite Número máximo de preguntas (opcional, default 10)
     * @return TestPlayDTO con las preguntas del test
     */
    @Operation(summary = "Generar nuevo test con filtros opcionales")
    @GetMapping
    public ResponseEntity<TestPlayDTO> generarTest(
            @RequestParam(required = false) String tematica,
            @RequestParam(required = false) String tipoPregunta,
            @RequestParam(required = false, defaultValue = "10") Integer limite) {
        
        TestPlayDTO test = testService.generarTest(tematica, tipoPregunta, limite);
        return ResponseEntity.ok(test);
    }

    /**
     * Corrige un test enviado por el usuario.
     * 
     * - Valida las respuestas en memoria
     * - Calcula la puntuación
     * - Guarda solo el resumen del resultado (no las respuestas)
     * - Devuelve corrección detallada con explicaciones
     * 
     * Requiere que el usuario esté autenticado.
     * 
     * @param submitDTO Las respuestas del usuario
     * @param tematica Temática del test (para estadísticas)
     * @param tipoPregunta Tipo de pregunta del test (para estadísticas)
     * @return TestResultDTO con la corrección y puntuación
     */
    @Operation(summary = "Enviar y corregir un test (guardar resultado)")
    @PostMapping("/submit")
    public ResponseEntity<TestResultDTO> corregirTest(
            @Valid @RequestBody TestSubmitDTO submitDTO,
            @RequestParam(required = false) String tematica,
            @RequestParam(required = false) String tipoPregunta) {
        
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        TestResultDTO resultado = testService.corregirYGuardarTest(
                submitDTO, 
                username, 
                tematica, 
                tipoPregunta
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    /**
     * Obtiene el historial de tests realizados por el usuario autenticado.
     * 
     * Permite paginación para navegar a través de los resultados.
     * Los resultados se ordenan por fecha de realización en orden descendente (más recientes primero).
     * 
     * Parámetros opcionales:
     * - page: Número de página (0-indexed, por defecto 0)
     * - pageSize: Cantidad de resultados por página (por defecto 10)
     * 
     * @param page Número de página (opcional, default 0)
     * @param pageSize Tamaño de página (opcional, default 10)
     * @return Page con los registros del historial del usuario
     */
    @Operation(summary = "Obtener historial de tests del usuario autenticado")
    @GetMapping("/historial")
    public ResponseEntity<Page<TestHistorialDTO>> obtenerHistorialTests(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Page<TestHistorialDTO> historial = testService.obtenerHistorialTests(username, page, pageSize);
        return ResponseEntity.ok(historial);
    }
}
