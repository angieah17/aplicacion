package com.midominio.group.app.spring.service;

import com.midominio.group.app.spring.entity.PreguntaVF;
import com.midominio.group.app.spring.repository.PreguntaVFRepository;

import org.springframework.stereotype.Service;

/**
 * Servicio específico para preguntas Verdadero/Falso.
 */
@Service
public class PreguntaVFService extends AbstractPreguntaService<PreguntaVF> {

    /* El constructor es fundamental porque se necesita pasar la dependencia (repositorio) al padre (AbstractPreguntaService<PreguntaVF>).
    Spring necesita un constructor para inyectar el repositorio específico. 
    El autowired solo no funciona muy bien para garantizar la inmmutabilidad. */
    public PreguntaVFService(PreguntaVFRepository repository) {
        super(repository); // ← Pasa PreguntaVFRepository al padre
    }

    // Aquí luego se añadirán métodos específicos para VF
}