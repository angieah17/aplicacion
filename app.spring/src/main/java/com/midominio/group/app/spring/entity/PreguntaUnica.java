package com.midominio.group.app.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preguntas_unica")
@DiscriminatorValue("UNICA")
public class PreguntaUnica extends Pregunta {
    
    // 1. ATRIBUTOS
    
    @ElementCollection //Le indica a JPA que esta lista no es una entidad, sino una colección de valores, es decir dependen 100% de la entidad PreguntaUnica.
    @CollectionTable( //Esto le indica a JPA que debe crear una tabla separada para almacenar las opciones de cada pregunta única. La tabla se llamará "pregunta_unica_opciones" y tendrá una columna "pregunta_id" que se usará para relacionar cada opción con su pregunta correspondiente.
        name = "pregunta_unica_opciones",
        joinColumns = @JoinColumn(name = "pregunta_id")
    )
    @Column(name = "opcion", length = 500)
    @NotEmpty(message = "Debe haber al menos una opción")
    @OrderColumn(name = "orden") // Añade una columna extra que guarda la posición de cada elemento, evitando duplicaciones
    @Size(min = 3, message = "Debe haber al menos 3 opciones")
    private List<String> opciones = new ArrayList<>();
    
    @NotNull(message = "La respuesta correcta es obligatoria") //valida antes de enviar datos a la base de datos, si la validación falla no se ejecuta ningún SQL, se devuelve un error al cliente indicando que el campo es obligatorio.
    @Column(name = "respuesta_correcta", nullable = false) //valida a nivel de base de datos, no permite que se inserten registros con este campo nulo, garantiza la integridad, incluso si los datos se insertan por fuera de la aplicación Java 
    private Integer respuestaCorrecta; // Índice de la opción correcta (0-based)
        
    
    // 2. CONSTRUCTORES
    
    public PreguntaUnica() {
        
    }
    
    public PreguntaUnica(List<String> opciones, Integer respuestaCorrecta) {
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }
    
    
    // 3. METODOS
    
    @Override
    public String getTipoPregunta() {
        return "UNICA";
    }
    
    public boolean validarRespuesta(Integer respuesta) {
        return respuesta != null && respuesta.equals(this.respuestaCorrecta);
    }
    
    
    // 4. GETTERS Y SETTERS
    
    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public Integer getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(Integer respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

}
