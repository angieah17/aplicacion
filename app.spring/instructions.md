### Desarrollo de requisito #24

Quiero crear una app móvil mínima con React Native (Expo + TypeScript) que consuma mi backend Spring Boot existente.

Objetivo: cumplir el requisito de “Acceso móvil con React Native (funcionalidades seleccionadas)” con la implementación más sencilla posible.

BACKEND

Voy a crear un endpoint público:

GET /api/public/preguntas

Este endpoint:
- Devuelve solo preguntas activas
- Solo incluye estos campos:
  - id
  - enunciado
  - tematica
  - tipo
- No requiere autenticación
- No expone respuestas ni explicación

Solo debo permitir acceso público a esta ruta en SecurityConfig sin modificar la seguridad del resto del sistema.

APP MÓVIL

La app debe implementar SOLO:

Una pantalla llamada ListaPreguntasScreen que:

- Tiene un botón "Cargar preguntas"
- Hace una petición GET a:
  http://10.0.2.2:8080/api/public/preguntas
- Muestra los resultados en una FlatList
- Muestra:
  - Enunciado
  - Temática
  - Tipo
- Tiene indicador de carga
- Tiene manejo básico de error

Requisitos técnicos:

- Proyecto con Expo + TypeScript
- Estructura simple:
  /src/screens
  /src/services
- Crear un apiClient.ts para centralizar la base URL
- No implementar autenticación
- No usar AsyncStorage
- No usar contexto global
- No modificar lógica de negocio existente en el backend

Solo demostrar integración móvil-backend mediante consumo REST.
