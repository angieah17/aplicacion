package com.midominio.group.app.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midominio.group.app.spring.dto.RevisionPreguntaDTO;
import com.midominio.group.app.spring.dto.RespuestaDTO;
import com.midominio.group.app.spring.dto.TestHistorialDTO;
import com.midominio.group.app.spring.dto.TestPlayDTO;
import com.midominio.group.app.spring.dto.TestPreguntaDTO;
import com.midominio.group.app.spring.dto.TestResultDTO;
import com.midominio.group.app.spring.dto.TestSubmitDTO;
import com.midominio.group.app.spring.entity.*;
import com.midominio.group.app.spring.exception.BadRequestException;
import com.midominio.group.app.spring.exception.ResourceNotFoundException;
import com.midominio.group.app.spring.repository.PreguntaRepository;
import com.midominio.group.app.spring.repository.ResultadoTestRepository;
import com.midominio.group.app.spring.repository.UsuarioRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio para gestión de tests evaluables.
 * 
 * Responsabilidades:
 * - Generar tests usando filtros de PreguntaSearchService
 * - Aplicar límite y aleatorización de preguntas
 * - Corregir respuestas en memoria
 * - Calcular puntuación
 * - Guardar resumen de resultados
 * 
 * NO persiste las respuestas del usuario, solo el resultado final.
 */
@Service
public class TestService {
    
    private final PreguntaSearchService preguntaSearchService;
    private final PreguntaRepository preguntaRepository;
    private final ResultadoTestRepository resultadoTestRepository;
    private final UsuarioRepository usuarioRepository;
    
    public TestService(PreguntaSearchService preguntaSearchService,
                      PreguntaRepository preguntaRepository,
                      ResultadoTestRepository resultadoTestRepository,
                      UsuarioRepository usuarioRepository) {
        this.preguntaSearchService = preguntaSearchService;
        this.preguntaRepository = preguntaRepository;
        this.resultadoTestRepository = resultadoTestRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    /**
     * Genera un test usando los filtros especificados.
     * 
     * @param tematica Filtro de temática (opcional)
     * @param tipoPregunta Filtro de tipo de pregunta (opcional)
     * @param limite Número máximo de preguntas (por defecto 10)
     * @return TestPlayDTO con las preguntas seleccionadas
     */
    public TestPlayDTO generarTest(String tematica, String tipoPregunta, Integer limite) {
        
        // Validar y normalizar tipoPregunta
        tipoPregunta = validarYNormalizarTipoPregunta(tipoPregunta);
        
        // Validar límite
        if (limite == null || limite <= 0) {
            limite = 10; // Valor por defecto
        }
        
        // Usar PreguntaSearchService para buscar preguntas activas con los filtros
        // Solicitamos más preguntas de las necesarias para poder aleatorizar
        Pageable pageable = PageRequest.of(0, limite * 3);
        Page<Pregunta> preguntasPage = preguntaSearchService.buscarConFiltros(
                tematica, 
                tipoPregunta, 
                true, // Solo preguntas activas
                pageable
        );
        
        List<Pregunta> todasLasPreguntas = new ArrayList<>(preguntasPage.getContent());
        
        if (todasLasPreguntas.isEmpty()) {
            throw new ResourceNotFoundException(
                "No se encontraron preguntas activas con los filtros especificados"
            );
        }
        
        // Aleatorizar y limitar
        Collections.shuffle(todasLasPreguntas);
        List<Pregunta> preguntasSeleccionadas = todasLasPreguntas.stream()
                .limit(limite)
                .collect(Collectors.toList());
        
        // Convertir a DTOs sin exponer respuestas correctas
        List<TestPreguntaDTO> preguntasDTO = preguntasSeleccionadas.stream()
                .map(this::convertirAPreguntaDTO)
                .collect(Collectors.toList());
        
        return new TestPlayDTO(
                preguntasDTO,
                tematica,
                tipoPregunta,
                preguntasDTO.size()
        );
    }
    
    /**
     * Corrige un test, calcula puntuación y guarda el resultado.
     * 
     * @param submitDTO Respuestas del usuario
     * @param username Nombre del usuario autenticado (de Spring Security)
     * @param tematica Temática del test (para estadísticas)
     * @param tipoPregunta Tipo de pregunta del test (para estadísticas)
     * @return TestResultDTO con la corrección detallada
     */
    @Transactional
    public TestResultDTO corregirYGuardarTest(TestSubmitDTO submitDTO, String username, 
                                             String tematica, String tipoPregunta) {
        
        // Validar y normalizar tipoPregunta
        tipoPregunta = validarYNormalizarTipoPregunta(tipoPregunta);
        
        // Validar respuestas
        if (submitDTO.getRespuestas() == null || submitDTO.getRespuestas().isEmpty()) {
            throw new BadRequestException("No se recibieron respuestas para corregir");
        }
        
        // Buscar usuario
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + username));
        
        // Obtener las preguntas del test
        Set<Long> idsPreguntas = submitDTO.getRespuestas().keySet();
        List<Pregunta> preguntas = preguntaRepository.findAllById(idsPreguntas);
        
        if (preguntas.size() != idsPreguntas.size()) {
            throw new BadRequestException("Algunas preguntas del test no existen en la base de datos");
        }
        
        // Corregir cada pregunta en memoria
        List<RevisionPreguntaDTO> revision = new ArrayList<>();
        int preguntasCorrectas = 0;
        
        for (Pregunta pregunta : preguntas) {
            RespuestaDTO respuestaUsuario = submitDTO.getRespuestas().get(pregunta.getId());
            
            if (respuestaUsuario == null) {
                continue; // Usuario no respondió esta pregunta
            }
            
            boolean esCorrecta = validarRespuesta(pregunta, respuestaUsuario);
            if (esCorrecta) {
                preguntasCorrectas++;
            }
            
            // Crear revisión de la pregunta
            RevisionPreguntaDTO revisionPregunta = crearRevision(
                    pregunta, 
                    respuestaUsuario, 
                    esCorrecta
            );
            revision.add(revisionPregunta);
        }
        
        // Calcular puntuación
        int totalPreguntas = preguntas.size();
        double puntuacion = totalPreguntas > 0 
                ? (double) preguntasCorrectas / totalPreguntas * 10.0 
                : 0.0;
        double porcentajeAcierto = totalPreguntas > 0
                ? (double) preguntasCorrectas / totalPreguntas * 100.0
                : 0.0;
        
        // Guardar resultado en la base de datos (solo resumen)
        ResultadoTest resultado = new ResultadoTest(
                usuario,
                Math.round(puntuacion * 100.0) / 100.0, // Redondear a 2 decimales
                tematica
        );
        resultadoTestRepository.save(resultado);
        
        // Retornar resultado completo
        return new TestResultDTO(
                Math.round(puntuacion * 100.0) / 100.0,
                totalPreguntas,
                preguntasCorrectas,
                totalPreguntas - preguntasCorrectas,
                Math.round(porcentajeAcierto * 100.0) / 100.0,
                revision
        );
    }
    
    /**
     * Convierte una Pregunta a TestPreguntaDTO sin exponer respuestas correctas.
     */
    private TestPreguntaDTO convertirAPreguntaDTO(Pregunta pregunta) {
        TestPreguntaDTO dto = new TestPreguntaDTO();
        dto.setId(pregunta.getId());
        dto.setEnunciado(pregunta.getEnunciado());
        dto.setTipoPregunta(pregunta.getTipoPregunta());
        
        // Solo incluir opciones para preguntas de tipo UNICA o MULTIPLE
        if (pregunta instanceof PreguntaUnica) {
            PreguntaUnica pUnica = (PreguntaUnica) pregunta;
            dto.setOpciones(new ArrayList<>(pUnica.getOpciones()));
        } else if (pregunta instanceof PreguntaMultiple) {
            PreguntaMultiple pMultiple = (PreguntaMultiple) pregunta;
            dto.setOpciones(new ArrayList<>(pMultiple.getOpciones()));
        }
        
        return dto;
    }
    
    /**
     * Valida y normaliza el tipoPregunta usando el enum TipoPreguntaEnum.
     * 
     * @param tipoPregunta El tipo de pregunta a validar (puede ser null)
     * @return El tipoPregunta normalizado en mayúsculas, o null si el parámetro era null/vacío
     * @throws BadRequestException si el tipoPregunta no es válido
     */
    private String validarYNormalizarTipoPregunta(String tipoPregunta) {
        try {
            TipoPreguntaEnum tipo = TipoPreguntaEnum.fromValue(tipoPregunta);
            return tipo != null ? tipo.getValue() : null;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    
    /**
     * Valida una respuesta según el tipo de pregunta.
     */
    private boolean validarRespuesta(Pregunta pregunta, RespuestaDTO respuesta) {
        
        if (pregunta instanceof PreguntaVF) {
            PreguntaVF pvf = (PreguntaVF) pregunta;
            return pvf.validarRespuesta(respuesta.getRespuestaVF());
            
        } else if (pregunta instanceof PreguntaUnica) {
            PreguntaUnica pUnica = (PreguntaUnica) pregunta;
            return pUnica.validarRespuesta(respuesta.getRespuestaUnica());
            
        } else if (pregunta instanceof PreguntaMultiple) {
            PreguntaMultiple pMultiple = (PreguntaMultiple) pregunta;
            return pMultiple.validarRespuesta(respuesta.getRespuestaMultiple());
        }
        
        return false;
    }
    
    /**
     * Crea la revisión de una pregunta individual.
     */
    private RevisionPreguntaDTO crearRevision(
            Pregunta pregunta, 
            RespuestaDTO respuestaUsuario,
            boolean esCorrecta) {
        
        String respuestaUsuarioTexto = "";
        String respuestaCorrectaTexto = "";
        
        if (pregunta instanceof PreguntaVF) {
            PreguntaVF pvf = (PreguntaVF) pregunta;
            respuestaUsuarioTexto = respuestaUsuario.getRespuestaVF() != null 
                    ? (respuestaUsuario.getRespuestaVF() ? "Verdadero" : "Falso")
                    : "Sin respuesta";
            respuestaCorrectaTexto = pvf.getRespuestaCorrecta() ? "Verdadero" : "Falso";
            
        } else if (pregunta instanceof PreguntaUnica) {
            PreguntaUnica pUnica = (PreguntaUnica) pregunta;
            Integer indiceUsuario = respuestaUsuario.getRespuestaUnica();
            
            if (indiceUsuario != null && indiceUsuario >= 0 && indiceUsuario < pUnica.getOpciones().size()) {
                respuestaUsuarioTexto = pUnica.getOpciones().get(indiceUsuario);
            } else {
                respuestaUsuarioTexto = "Sin respuesta o índice inválido";
            }
            
            Integer indiceCorrecta = pUnica.getRespuestaCorrecta();
            if (indiceCorrecta >= 0 && indiceCorrecta < pUnica.getOpciones().size()) {
                respuestaCorrectaTexto = pUnica.getOpciones().get(indiceCorrecta);
            }
            
        } else if (pregunta instanceof PreguntaMultiple) {
            PreguntaMultiple pMultiple = (PreguntaMultiple) pregunta;
            List<Integer> indicesUsuario = respuestaUsuario.getRespuestaMultiple();
            
            if (indicesUsuario != null && !indicesUsuario.isEmpty()) {
                respuestaUsuarioTexto = indicesUsuario.stream()
                        .filter(i -> i >= 0 && i < pMultiple.getOpciones().size())
                        .map(i -> pMultiple.getOpciones().get(i))
                        .collect(Collectors.joining(", "));
            } else {
                respuestaUsuarioTexto = "Sin respuesta";
            }
            
            List<Integer> indicesCorrectas = pMultiple.getRespuestasCorrectas();
            respuestaCorrectaTexto = indicesCorrectas.stream()
                    .filter(i -> i >= 0 && i < pMultiple.getOpciones().size())
                    .map(i -> pMultiple.getOpciones().get(i))
                    .collect(Collectors.joining(", "));
        }
        
        return new RevisionPreguntaDTO(
                pregunta.getId(),
                pregunta.getEnunciado(),
                pregunta.getTipoPregunta(),
                esCorrecta,
                respuestaUsuarioTexto,
                respuestaCorrectaTexto,
                pregunta.getExplicacion()
        );
    }


    /**
     * Obtiene el historial de tests realizados por un usuario.
     * 
     * @param username Nombre del usuario autenticado
     * @param page Número de página (0-indexed)
     * @param pageSize Cantidad de resultados por página
     * @return Page con los registros del historial del usuario ordenados por fecha descendente
     */
    public Page<TestHistorialDTO> obtenerHistorialTests(String username, int page, int pageSize) {
        // Buscar usuario
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + username));
        
        // Crear paginación
        Pageable pageable = PageRequest.of(page, pageSize);
        
        // Obtener resultados del usuario ordenados por fecha descendente
        Page<ResultadoTest> resultados = resultadoTestRepository
                .findByUsuarioOrderByFechaRealizacionDesc(usuario, pageable);
        
        // Convertir a DTOs
        return resultados.map(resultado -> new TestHistorialDTO(
                resultado.getId(),
                resultado.getPuntuacion(),
                resultado.getTematica(),
                resultado.getFechaRealizacion()
        ));
    }
}
