package com.midominio.group.app.spring.service;

import com.midominio.group.app.spring.entity.PreguntaUnica;
import com.midominio.group.app.spring.repository.PreguntaUnicaRepository;

import org.springframework.stereotype.Service;

/**
 * Servicio específico para preguntas Únicas
 */
@Service
public class PreguntaUnicaService extends AbstractPreguntaService<PreguntaUnica> {

    /* El constructor es fundamental porque se necesita pasar la dependencia (repositorio) al padre (AbstractPreguntaService<PreguntaUnica>).
    Spring necesita un constructor para inyectar el repositorio específico. 
    El autowired solo no funciona muy bien para garantizar la inmmutabilidad. */
    public PreguntaUnicaService(PreguntaUnicaRepository repository) {
        super(repository); // ← Pasa PreguntaUnicaRepository al padre
    }

    // Aquí luego se añadirán métodos específicos para Unica
}