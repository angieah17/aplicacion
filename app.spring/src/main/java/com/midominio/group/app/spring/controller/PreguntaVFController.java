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
import com.midominio.group.app.spring.entity.PreguntaVF;
import com.midominio.group.app.spring.service.PreguntaVFService;

import jakarta.validation.Valid;


/* Controlador específico para preguntas Verdadero/Falso.
Se sirve de AbstractPreguntaService pues esta clase tiene métodos genéricos para todas las preguntas.
*/
@Deprecated(since = "2026-02", forRemoval = false)
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/preguntas/vf")
public class PreguntaVFController {

	private final PreguntaVFService service;
	
	//inyección de dependencias por constructor y no autowired para garantizar la inmutabilidad
    public PreguntaVFController(PreguntaVFService service) {
        this.service = service;
    }
    
    // GET /api/preguntas/vf?page=0&size=10
    @GetMapping
    public Page<PreguntaVF> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findAll(pageable);
    }
    
    // GET /api/preguntas/vf/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PreguntaVF> obtener(@PathVariable Long id) {
    	
        Optional<PreguntaVF> pregunta = service.findById(id);
        
        return pregunta.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build()); // cuando se hagan excepciones más específicas se pueden mostrar estados HTTP más específicos
    }
    
    // POST /api/preguntas/vf
    @PostMapping
    public PreguntaVF crear(@Valid @RequestBody PreguntaVF pregunta) {
        return service.crear(pregunta); 
        //Más adelante se puede incluir validaciones como que la respuesta no es null
    }

    // DELETE lógico /api/preguntas/vf/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<PreguntaVF> desactivar(@PathVariable Long id) {
        PreguntaVF pregunta = service.desactivar(id);
        return ResponseEntity.ok(pregunta);
        //la excepción se maneja en el servicio y en el manejador global de excepciones
    }
    
    // Activar de nuevo
    @PutMapping("/activar/{id}")
    public ResponseEntity<PreguntaVF> activar(@PathVariable Long id) {
        PreguntaVF pregunta = service.activar(id);
        return ResponseEntity.ok(pregunta);
    }
    
    // PUT /api/preguntas/vf/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PreguntaVF> actualizar(@PathVariable Long id, @Valid @RequestBody PreguntaVF datos) {    	
    	return service.findById(id)
    			.map(p -> {
    				// Campos heredados
    	            p.setEnunciado(datos.getEnunciado());
    	            p.setTematica(datos.getTematica());
    	            // Campos específicos
    				p.setRespuestaCorrecta(datos.getRespuestaCorrecta());
    				p.setExplicacion(datos.getExplicacion());
                    return ResponseEntity.ok(service.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    	
    }
    
    
}
