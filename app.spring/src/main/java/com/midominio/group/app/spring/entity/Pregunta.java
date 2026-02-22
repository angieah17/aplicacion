package com.midominio.group.app.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "preguntas")
@Inheritance(strategy = InheritanceType.JOINED) //Una tabla para la clase padre y tablas separadas para cada hija
@DiscriminatorColumn(name = "tipo_pregunta", discriminatorType = DiscriminatorType.STRING) //Crea una columna para distinguir entre los tipos de preguntas
public abstract class Pregunta {
    
	//1. ATRIBUTOS
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El enunciado no puede estar vacío") //estas tres se usan a nivel de controller con @Valid y column a nivel de BD
    @NotNull(message = "El enunciado es obligatorio")
    @Size(max = 500, message = "El enunciado no puede superar los 500 caracteres")
    @Column(nullable = false, length = 500)
    private String enunciado;
    
    /* Las BeanValidation de Java dan mensajes claros al usuario
        Las de BD son una última línea de defensa por si alguien accede directamente a la BD */

    @NotBlank(message = "La tematica no puede estar vacía") 
    @NotNull(message = "La tematica es obligatoria")
    @Column(nullable = false, length = 100)
    private String tematica;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "activa") //esto permitirá el borrado lógico, es decir, no borra como tal la pregunta de la BD, la esconde
    private Boolean activa = true;
    
    @PrePersist //esta anotación sirve para ejecutar código antes de que la entidad se persista (es decir, se inserte) en la BD
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now(); //Establece la fecha de creación automáticamente
    }
    
    @Column(length = 1000)
    private String explicacion;
    
    //2. CONSTRUCTORES
    
    public Pregunta () {
    	
    }
    
    public Pregunta(Long id, String enunciado, String tematica, LocalDateTime fechaCreacion, Boolean activa, String explicacion) {
		this.id = id;
		this.enunciado = enunciado;
		this.tematica = tematica;
		this.fechaCreacion = fechaCreacion;
		this.activa = activa;
		this.explicacion = explicacion;
	}

    
    
    //3. METODOS
	// Método abstracto que cada tipo de pregunta implementará
    public abstract String getTipoPregunta();
    
    
    


	//4. GETTERS Y SETTERS
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	
    
    public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}

	//5. HASHCODE y EQUALS
    //Para mantener la persistencia en el mapeo de datos 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pregunta)) return false;
        Pregunta pregunta = (Pregunta) o;
        return id != null && id.equals(pregunta.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

/* Beneficios de usar InheritanceType.JOINED:

1. Normalización de Datos: Cada subclase tiene su propia tabla, lo que reduce la redundancia y mejora la integridad de los datos.
2. Flexibilidad: Permite agregar nuevas subclases sin afectar la estructura de las tablas existentes.
3. Mantenimiento Sencillo: Facilita el mantenimiento y la evolución del modelo de datos a lo largo del tiempo.

Inconvenitente: requiere JOINs en las consultas, lo que puede afectar el rendimiento en ciertos escenarios con muchas subclases o datos complejos.
*/