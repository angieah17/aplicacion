package com.midominio.group.app.spring.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.midominio.group.app.spring.entity.PreguntaMultiple;
import com.midominio.group.app.spring.service.PreguntaMultipleService;

import jakarta.validation.Valid;


/* Controlador específico para preguntas de opción múltiple.
Se sirve de AbstractPreguntaService pues esta clase tiene métodos genéricos para todas las preguntas.
*/
@Deprecated(since = "2026-02", forRemoval = false)
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/preguntas/multiple")
public class PreguntaMultipleController {

    private final PreguntaMultipleService service;
    
    //inyección de dependencias por constructor y no autowired para garantizar la inmutabilidad
    public PreguntaMultipleController(PreguntaMultipleService service) {
        this.service = service;
    }
    
    // GET /api/preguntas/multiple?page=0&size=10
    @GetMapping
    public Page<PreguntaMultiple> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findAll(pageable);
    }
    
    // GET /api/preguntas/multiple/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PreguntaMultiple> obtener(@PathVariable Long id) {
        
        Optional<PreguntaMultiple> pregunta = service.findById(id);
        
        return pregunta.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/preguntas/multiple
    @PostMapping
    public PreguntaMultiple crear(@Valid @RequestBody PreguntaMultiple pregunta) {
        return service.crear(pregunta);
    }

    // DELETE lógico /api/preguntas/multiple/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<PreguntaMultiple> desactivar(@PathVariable Long id) {
        PreguntaMultiple pregunta = service.desactivar(id);
        return ResponseEntity.ok(pregunta);
    }
    
    // Activar de nuevo
    @PutMapping("/activar/{id}")
    public ResponseEntity<PreguntaMultiple> activar(@PathVariable Long id) {
        PreguntaMultiple pregunta = service.activar(id);
        return ResponseEntity.ok(pregunta);
    }
    
    // PUT /api/preguntas/multiple/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PreguntaMultiple> actualizar(@PathVariable Long id, @Valid @RequestBody PreguntaMultiple datos) {    	
        return service.findById(id)
                .map(p -> {
                    // Campos heredados
                    p.setEnunciado(datos.getEnunciado());
                    p.setTematica(datos.getTematica());
                    // Campos específicos
                    p.setOpciones(datos.getOpciones());
                    p.setRespuestasCorrectas(datos.getRespuestasCorrectas());
                    p.setExplicacion(datos.getExplicacion());
                    return ResponseEntity.ok(service.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
        
    }
    
}