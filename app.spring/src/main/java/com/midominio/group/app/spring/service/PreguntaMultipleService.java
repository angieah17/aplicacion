package com.midominio.group.app.spring.service;

import com.midominio.group.app.spring.entity.PreguntaMultiple;
import com.midominio.group.app.spring.repository.PreguntaMultipleRepository;

import org.springframework.stereotype.Service;

/**
 * Servicio específico para preguntas de selección multiple
 */
@Service
public class PreguntaMultipleService extends AbstractPreguntaService<PreguntaMultiple> {

    /* El constructor es fundamental porque se necesita pasar la dependencia (repositorio) al padre (AbstractPreguntaService<PreguntaMultiple>).
    Spring necesita un constructor para inyectar el repositorio específico. 
    El autowired solo no funciona muy bien para garantizar la inmmutabilidad. */
    public PreguntaMultipleService(PreguntaMultipleRepository repository) {
        super(repository); // ← Pasa PreguntaMultiple Repository al padre
    }

    // Aquí luego se añadirán métodos específicos para preguntas de selección multiple
}