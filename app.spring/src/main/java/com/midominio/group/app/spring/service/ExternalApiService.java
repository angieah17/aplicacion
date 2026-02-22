package com.midominio.group.app.spring.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.midominio.group.app.spring.dto.ExternalActivityDTO;

@Service
public class ExternalApiService {

    private static final String EXTERNAL_URL = "https://bored-api.appbrewery.com/random";

    private final RestTemplate restTemplate;

    public ExternalApiService() {
        this.restTemplate = new RestTemplate();
    }

    public ExternalActivityDTO obtenerActividadAleatoria() {
        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    EXTERNAL_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error al consumir API externa: código HTTP " + response.getStatusCode().value());
            }

            Map<String, Object> body = response.getBody();
            if (body == null) {
                throw new RuntimeException("La API externa devolvió una respuesta vacía");
            }

            return new ExternalActivityDTO(
                    body.get("activity") != null ? body.get("activity").toString() : null,
                    body.get("type") != null ? body.get("type").toString() : null,
                    body.get("participants") instanceof Number
                            ? ((Number) body.get("participants")).intValue()
                            : null);
        } catch (ResourceAccessException e) {
            throw new RuntimeException("No se pudo conectar con la API externa", e);
        } catch (RestClientException e) {
            throw new RuntimeException("Error al consumir la API externa", e);
        }
    }
}