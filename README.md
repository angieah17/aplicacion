# 📝 QuizApp — Plataforma de Tests Interactiva

> **Proyecto Final** — Desarrollo de Aplicaciones Multiplataforma (DAM)  
> Módulos: Acceso a Datos (AD) · Prog. Multimedia y Dispositivos Móviles (PMDM) · Optativa Spring Boot & Microservicios

---

## 📑 Índice

1. [Descripción del Proyecto](#-descripción-del-proyecto)
2. [Arquitectura General](#-arquitectura-general)
3. [Stack Tecnológico](#-stack-tecnológico)
4. [Estructura del Repositorio](#-estructura-del-repositorio)
5. [Modelo de Datos](#-modelo-de-datos)
6. [Seguridad y Autenticación](#-seguridad-y-autenticación)
7. [Endpoints de la API REST](#-endpoints-de-la-api-rest)
8. [Flujo del Sistema](#-flujo-del-sistema)
9. [Instalación y Ejecución](#-instalación-y-ejecución)
10. [Documentación Swagger](#-documentación-swagger)
11. [Datos de Prueba](#-datos-de-prueba)
12. [Autora](#-autora)

---

## 📖 Descripción del Proyecto

**QuizApp** es una plataforma full-stack de generación y resolución de tests diseñada como proyecto de entrega académica final. Permite la gestión completa de un banco de preguntas de tres tipos distintos, la generación dinámica de exámenes con filtros, la corrección automática con puntuación y la consulta de historial de resultados.

### Funcionalidades principales

| Rol | Funcionalidades |
|------|----------------|
| **Administrador** | CRUD completo de preguntas (Verdadero/Falso, Selección Única, Selección Múltiple), importación masiva por CSV, gestión de usuarios, visualización del panel de administración |
| **Usuario** | Registro e inicio de sesión, generación de tests con filtros (temática, tipo, cantidad), resolución interactiva de exámenes, consulta de resultados y calificaciones, historial de tests realizados |
| **Público** | Registro de cuenta, visualización de la página de inicio (Thymeleaf), consulta de preguntas activas (endpoint público), consulta de actividad aleatoria (API externa) |
| **Móvil** | Visualización de preguntas activas desde React Native (endpoint público sin autenticación) |

---

## 🏗 Arquitectura General

El proyecto sigue una arquitectura **cliente-servidor** con separación total entre backend y frontends:

```
┌──────────────────────────────────────────────────────────────────┐
│                        CLIENTES                                  │
│                                                                  │
│  ┌─────────────────┐  ┌──────────────────┐  ┌────────────────┐  │
│  │  React + Vite   │  │  React Native    │  │  Thymeleaf     │  │
│  │  (SPA Web)      │  │  (Expo - Móvil)  │  │  (SSR Home)    │  │
│  │  :5173          │  │  :8081           │  │  integrado     │  │
│  └────────┬────────┘  └────────┬─────────┘  └───────┬────────┘  │
│           │                    │                     │           │
└───────────┼────────────────────┼─────────────────────┼───────────┘
            │  HTTP Basic Auth   │  Sin Auth (público) │
            ▼                    ▼                     ▼
┌──────────────────────────────────────────────────────────────────┐
│                   BACKEND — Spring Boot :8080                    │
│                                                                  │
│  ┌──────────────┐  ┌──────────────┐  ┌────────────────────────┐ │
│  │ Spring       │  │  Spring      │  │ API REST               │ │
│  │ Security     │  │  Data JPA    │  │ (Controllers + DTOs)   │ │
│  │ (Basic Auth) │  │  + MongoDB   │  │                        │ │
│  └──────┬───────┘  └──────┬───────┘  └────────────────────────┘ │
│         │                 │                                      │
└─────────┼─────────────────┼──────────────────────────────────────┘
          │                 │
          ▼                 ▼
┌──────────────────┐  ┌──────────────────┐
│  MySQL 8.0       │  │  MongoDB 7.0     │
│  :3307           │  │  :27017          │
│  (Datos relac.)  │  │  (Test Logs)     │
└──────────────────┘  └──────────────────┘
```

---

## 🛠 Stack Tecnológico

### Backend (`app.spring/`)

| Tecnología | Versión | Propósito |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.2.5 | Framework backend |
| Spring Data JPA | — | ORM y acceso a datos MySQL |
| Spring Data MongoDB | — | Acceso a datos MongoDB |
| Spring Security | — | Autenticación HTTP Basic + autorización por roles |
| Thymeleaf | — | Renderizado server-side de la página de inicio |
| Springdoc OpenAPI | 2.5.0 | Documentación Swagger de la API |
| Lombok | — | Reducción de código boilerplate |
| MySQL | 8.0 | Base de datos relacional principal |
| MongoDB | 7.0 | Base de datos documental para logs de tests |
| Docker Compose | — | Orquestación de contenedores de BD |

### Frontend Web (`app-reactjs/`)

| Tecnología | Versión | Propósito |
|---|---|---|
| React | 19.2.0 | Librería de UI |
| TypeScript | 5.9.3 | Tipado estático |
| Vite | 7.2.4 | Bundler y dev server |
| React Router DOM | 7.13.0 | Enrutamiento SPA |
| Axios | 1.13.4 | Cliente HTTP |
| Bootstrap | 5.3.8 | Framework CSS |

### Aplicación Móvil (`app-native/`)

| Tecnología | Versión | Propósito |
|---|---|---|
| React Native | 0.81.5 | Framework móvil |
| Expo | ~54.0.33 | Plataforma de desarrollo |
| React | 19.1.0 | Motor de UI |
| Axios | 1.13.5 | Cliente HTTP |

---

## 📁 Estructura del Repositorio

```
aplicacion/
├── README.md                          ← Este archivo
│
├── app.spring/                        ← Backend Spring Boot
│   ├── docker-compose.yml             ← MySQL 8.0 + MongoDB 7.0
│   ├── pom.xml                        ← Dependencias Maven
│   ├── preguntas.csv                  ← Archivo CSV de ejemplo para importación
│   └── src/main/
│       ├── java/com/midominio/group/app/spring/
│       │   ├── Application.java
│       │   ├── config/                ← OpenAPI config
│       │   ├── controller/            ← REST controllers (8 controladores)
│       │   ├── dto/                   ← Objetos de transferencia (12 DTOs)
│       │   ├── entity/                ← Entidades JPA + enums
│       │   ├── exception/             ← Manejador global de errores
│       │   ├── mongo/                 ← Módulo MongoDB (entidad, repo, servicio, controller)
│       │   ├── repository/            ← Repositorios JPA + Specifications
│       │   ├── security/              ← SecurityConfig + UserDetailsService
│       │   └── service/               ← Lógica de negocio (8 servicios)
│       └── resources/
│           ├── application.properties ← Configuración
│           ├── data.sql               ← Seed: admin + 50 preguntas
│           ├── static/css/            ← Estilos CSS
│           └── templates/             ← Vistas Thymeleaf (home, errores, fragmentos)
│
├── app-reactjs/                       ← Frontend Web React + TypeScript
│   ├── package.json
│   ├── vite.config.ts
│   └── src/
│       ├── App.tsx                    ← Enrutamiento principal
│       ├── context/AuthContext.tsx     ← Estado global de autenticación
│       ├── services/                  ← Servicios API (6 módulos)
│       ├── components/                ← Componentes reutilizables
│       │   ├── auth/                  ← Login, Register, ProtectedRoute
│       │   ├── layout/               ← Navbar
│       │   └── usuarios/             ← UsuarioForm, UsuariosTable
│       └── pages/                     ← 10 páginas de la aplicación
│
└── app-native/                        ← App Móvil React Native
    ├── App.js                         ← Punto de entrada
    ├── package.json
    └── src/
        ├── hooks/usePublicPreguntas.js  ← Hook de consumo API
        └── screens/ListaPreguntasScreen.js ← Pantalla principal
```

---

## 🗄 Modelo de Datos

### Diagrama Entidad-Relación

```
┌────────────────────────┐          ┌──────────────────────────┐
│       USUARIO           │          │     RESULTADO_TEST       │
├────────────────────────┤          ├──────────────────────────┤
│ id          BIGINT (PK) │ 1────M  │ id            BIGINT (PK)│
│ username    VARCHAR(50)  │          │ usuario_id    BIGINT (FK)│
│ password    VARCHAR(255) │          │ puntuacion    DOUBLE     │
│ role        ENUM         │          │ tematica      VARCHAR    │
│ enabled     BOOLEAN      │          │ fecha_realiz. DATETIME   │
└────────────────────────┘          └──────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                    PREGUNTA (abstracta)                          │
│              Estrategia de herencia: JOINED                     │
├─────────────────────────────────────────────────────────────────┤
│ id              BIGINT (PK)     │ tematica     VARCHAR(100)     │
│ enunciado       VARCHAR(500)    │ activa       BOOLEAN          │
│ tipo_pregunta   VARCHAR (disc.) │ explicacion  VARCHAR(1000)    │
│ fecha_creacion  DATETIME        │                               │
└─────────────────┬───────────────┴──────────────┬────────────────┘
                  │                              │
      ┌───────────┼──────────────┐               │
      ▼           ▼              ▼               │
┌──────────┐ ┌──────────┐ ┌───────────┐         │
│PREGUNTA  │ │PREGUNTA  │ │PREGUNTA   │         │
│   VF     │ │  UNICA   │ │ MULTIPLE  │         │
├──────────┤ ├──────────┤ ├───────────┤         │
│respuesta │ │respuesta │ │opciones[] │         │
│_correcta │ │_correcta │ │respuestas │         │
│(BOOLEAN) │ │(INTEGER) │ │_correctas │         │
│          │ │opciones[]│ │  []       │         │
└──────────┘ └──────────┘ └───────────┘         │
                                                 │
                    ┌────────────────────────────┘
                    ▼
        ┌────────────────────────┐
        │  TEST_LOG (MongoDB)    │
        ├────────────────────────┤
        │ _id       ObjectId     │
        │ username  String       │
        │ fecha     DateTime     │
        │ nota      Double       │
        └────────────────────────┘
```

### Tipos de Pregunta

| Tipo | Discriminador | Tabla hija | Descripción |
|---|---|---|---|
| Verdadero/Falso | `VERDADERO_FALSO` | `preguntas_verdadero_falso` | Respuesta booleana |
| Selección Única | `UNICA` | `preguntas_unica` | Una opción correcta entre varias (+`ElementCollection` de opciones) |
| Selección Múltiple | `MULTIPLE` | `preguntas_multiple` | Varias opciones correctas (+`ElementCollection` de opciones y respuestas) |

### Base de datos dual

| Motor | Puerto | Base de datos | Uso |
|---|---|---|---|
| **MySQL 8.0** | 3307 | `proyecto_final` | Usuarios, preguntas (con herencia), resultados de tests |
| **MongoDB 7.0** | 27017 | `proyecto_final` | Logs de tests (registro de calificaciones por usuario) |

---

## 🔐 Seguridad y Autenticación

El sistema utiliza **Spring Security con HTTP Basic Authentication** y contraseñas cifradas con **BCrypt**.

### Configuración de seguridad

- **CSRF** deshabilitado (API REST stateless)
- **CORS** habilitado para orígenes `http://localhost:5173` (React) y `http://localhost:8081` (React Native)
- **Autorización por roles** mediante `@PreAuthorize` (Method Security habilitado)

### Matriz de permisos

| Recurso | Público | USER | ADMIN |
|---|:---:|:---:|:---:|
| `POST /auth/register` | ✅ | ✅ | ✅ |
| `GET /auth/me` | ❌ | ✅ | ✅ |
| `GET /` (Thymeleaf Home) | ✅ | ✅ | ✅ |
| `GET /api/public/preguntas` | ✅ | ✅ | ✅ |
| `GET /api/external/activity` | ✅ | ✅ | ✅ |
| `GET /api/tests/**` | ❌ | ✅ | ✅ |
| `POST /api/tests/submit` | ❌ | ✅ | ✅ |
| `*/api/mongo/logs/**` | ❌ | ✅ | ✅ |
| `*/api/admin/preguntas/**` | ❌ | ❌ | ✅ |
| `*/api/preguntas/**` | ❌ | ❌ | ✅ |
| `*/api/usuarios/**` | ❌ | ❌ | ✅ |
| Swagger UI (`/swagger-ui.html`) | ✅ | ✅ | ✅ |

### Flujo de autenticación en el frontend

1. El usuario introduce sus credenciales en el formulario de login.
2. El frontend codifica `username:password` en **Base64**.
3. Se realiza una petición `GET /auth/me` con la cabecera `Authorization: Basic <base64>`.
4. Si la respuesta es `200 OK`, las credenciales se almacenan en `localStorage`.
5. Un **interceptor de Axios** adjunta automáticamente la cabecera de autenticación a todas las peticiones.
6. Ante una respuesta `401`, se limpia el `localStorage` y se redirige a `/login`.

---

## 🌐 Endpoints de la API REST

### Autenticación

| Método | Ruta | Auth | Descripción |
|:---:|---|:---:|---|
| `POST` | `/auth/register` | Pública | Registrar nuevo usuario (rol USER por defecto) |
| `GET` | `/auth/me` | Autenticado | Obtener perfil del usuario actual |

### Administración de Preguntas — `ADMIN`

| Método | Ruta | Descripción |
|:---:|---|---|
| `GET` | `/api/admin/preguntas` | Listar preguntas con filtros (temática, tipo, activa) + paginación |
| `GET` | `/api/admin/preguntas/buscar` | Buscar preguntas por texto + filtros |
| `GET` | `/api/admin/preguntas/{id}` | Obtener pregunta por ID |
| `POST` | `/api/admin/preguntas/upload` | Importar preguntas desde archivo CSV (multipart) |
| `POST` | `/api/admin/preguntas/verdadero-falso` | Crear pregunta Verdadero/Falso |
| `POST` | `/api/admin/preguntas/unica` | Crear pregunta de Selección Única |
| `POST` | `/api/admin/preguntas/multiple` | Crear pregunta de Selección Múltiple |
| `PUT` | `/api/admin/preguntas/verdadero-falso/{id}` | Actualizar pregunta V/F |
| `PUT` | `/api/admin/preguntas/unica/{id}` | Actualizar pregunta Selección Única |
| `PUT` | `/api/admin/preguntas/multiple/{id}` | Actualizar pregunta Selección Múltiple |
| `PATCH` | `/api/admin/preguntas/{id}/activar` | Activar pregunta |
| `PATCH` | `/api/admin/preguntas/{id}/desactivar` | Desactivar pregunta (borrado lógico) |

### Tests — Autenticado

| Método | Ruta | Descripción |
|:---:|---|---|
| `GET` | `/api/tests` | Generar test con filtros opcionales (`tematica`, `tipoPregunta`, `limite`) |
| `POST` | `/api/tests/submit` | Enviar respuestas y obtener corrección con puntuación (0-10) |
| `GET` | `/api/tests/historial` | Consultar historial de tests del usuario (paginado) |

### Gestión de Usuarios — `ADMIN`

| Método | Ruta | Descripción |
|:---:|---|---|
| `GET` | `/api/usuarios` | Listar usuarios con paginación |
| `GET` | `/api/usuarios/{id}` | Obtener usuario por ID |
| `POST` | `/api/usuarios` | Crear usuario |
| `PUT` | `/api/usuarios/{id}` | Actualizar usuario |
| `DELETE` | `/api/usuarios/{id}` | Eliminar usuario |

### Logs de Tests (MongoDB) — Autenticado

| Método | Ruta | Descripción |
|:---:|---|---|
| `POST` | `/api/mongo/logs?nota=X` | Guardar log de test (nota + username del usuario autenticado) |
| `GET` | `/api/mongo/logs` | Obtener todos los logs |
| `GET` | `/api/mongo/logs/me` | Obtener logs del usuario actual |

### Endpoints Públicos

| Método | Ruta | Descripción |
|:---:|---|---|
| `GET` | `/api/public/preguntas` | Listar preguntas activas (solo id, enunciado, temática, tipo) |
| `GET` | `/api/external/activity` | Obtener actividad aleatoria de API externa ([Bored API](https://bored-api.appbrewery.com)) |
| `GET` | `/` | Página de inicio renderizada con Thymeleaf |

### Documentación

| Ruta | Descripción |
|---|---|
| `/swagger-ui.html` | Interfaz gráfica Swagger UI |
| `/v3/api-docs` | Especificación OpenAPI en formato JSON |

---

## 🔄 Flujo del Sistema

### 1. Registro e Inicio de Sesión

```
Usuario ──▶ Formulario registro ──▶ POST /auth/register
                                        │
                                        ▼
                              Contraseña hasheada (BCrypt)
                              Rol asignado: USER
                              Guardado en MySQL
                                        │
                                        ▼
Usuario ──▶ Formulario login ──▶ Codifica user:pass → Base64
                                        │
                                        ▼
                              GET /auth/me (Authorization: Basic)
                                        │
                                   ┌────┴────┐
                                   │ ¿200?   │
                                   ├── Sí ──▶ Credenciales en localStorage → Dashboard
                                   └── No ──▶ Error de autenticación
```

### 2. Generación y Resolución de un Test

```
┌─────────────────────────────────────────────────────────────────┐
│ 1. CONFIGURAR                                                   │
│    Usuario selecciona filtros: temática, tipo, nº preguntas     │
│                         │                                       │
│                         ▼                                       │
│ 2. GENERAR                                                      │
│    GET /api/tests?tematica=X&tipoPregunta=Y&limite=Z            │
│    → Backend filtra preguntas activas, baraja, limita           │
│    → Retorna TestPlayDTO (preguntas SIN respuestas correctas)   │
│                         │                                       │
│                         ▼                                       │
│ 3. RESPONDER                                                    │
│    Usuario responde cada pregunta en la interfaz:               │
│    • V/F → selección booleana                                   │
│    • Única → radio button (índice)                              │
│    • Múltiple → checkboxes (índices)                            │
│                         │                                       │
│                         ▼                                       │
│ 4. ENVIAR Y CORREGIR                                            │
│    POST /api/tests/submit  (TestSubmitDTO: mapa id→respuesta)   │
│    → Backend carga preguntas, compara respuestas                │
│    → Calcula puntuación sobre 10                                │
│    → Guarda ResultadoTest en MySQL                              │
│    → Frontend guarda log en MongoDB (POST /api/mongo/logs)      │
│                         │                                       │
│                         ▼                                       │
│ 5. REVISAR RESULTADOS                                           │
│    Se muestra: nota, porcentaje de acierto,                     │
│    revisión por pregunta con respuesta correcta + explicación   │
└─────────────────────────────────────────────────────────────────┘
```

### 3. Gestión Administrativa

```
Admin ──▶ Panel de preguntas (/admin)
              │
              ├──▶ Crear pregunta (formulario por tipo) ──▶ POST /api/admin/preguntas/{tipo}
              ├──▶ Editar pregunta ──▶ PUT /api/admin/preguntas/{tipo}/{id}
              ├──▶ Activar/Desactivar ──▶ PATCH /api/admin/preguntas/{id}/activar|desactivar
              ├──▶ Importar CSV ──▶ POST /api/admin/preguntas/upload (multipart/form-data)
              └──▶ Buscar/Filtrar ──▶ GET /api/admin/preguntas/buscar?texto=...&tematica=...
              
Admin ──▶ Panel de usuarios (/admin/usuarios)
              │
              ├──▶ Listar usuarios (paginado) ──▶ GET /api/usuarios
              ├──▶ Crear usuario ──▶ POST /api/usuarios
              ├──▶ Editar usuario ──▶ PUT /api/usuarios/{id}
              └──▶ Eliminar usuario ──▶ DELETE /api/usuarios/{id}
```

### 4. Acceso Móvil (React Native)

```
App React Native ──▶ GET /api/public/preguntas (sin autenticación)
                          │
                          ▼
                    Lista de preguntas activas
                    (FlatList con enunciado, temática y tipo)
```

### 5. Base de Datos Dual

```
                    ┌──────────────────────────────────┐
                    │           Spring Boot             │
                    │                                   │
                    │  ┌─ Spring Data JPA ────────────┐ │
                    │  │  • Usuarios                  │ │
                    │  │  • Preguntas (herencia)      │──────▶ MySQL 8.0
                    │  │  • ResultadoTest             │ │
                    │  └──────────────────────────────┘ │
                    │                                   │
                    │  ┌─ Spring Data MongoDB ────────┐ │
                    │  │  • TestLog (username,         │──────▶ MongoDB 7.0
                    │  │    fecha, nota)               │ │
                    │  └──────────────────────────────┘ │
                    └──────────────────────────────────┘
```

---

## 🚀 Instalación y Ejecución

### Prerrequisitos

| Software | Versión mínima |
|---|---|
| Java JDK | 21 |
| Node.js | 18+ |
| npm | 9+ |
| Docker + Docker Compose | Última estable |
| Git | 2.x |
| Expo CLI *(opcional, para móvil)* | Última estable |

### Paso 1 — Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd aplicacion
```

### Paso 2 — Levantar las bases de datos con Docker

```bash
cd app.spring
docker-compose up -d
```

Esto creará dos contenedores:

| Servicio | Contenedor | Puerto |
|---|---|---|
| MySQL 8.0 | `mysql_proyecto_final` | `3307` |
| MongoDB 7.0 | `proyecto_mongo` | `27017` |

> **Verificar:** `docker ps` debe mostrar ambos contenedores en estado `Up`.

### Paso 3 — Ejecutar el Backend (Spring Boot) desde Eclipse

1. **Importar el proyecto:**
   - Abrir **Eclipse IDE** (con soporte Spring Boot / Spring Tool Suite).
   - Ir a `File` → `Open projects from file system…`
   - En *Root Directory*, seleccionar la carpeta `app.spring/` y pulsar `Finish`.
   - Esperar a que Maven descargue todas las dependencias.

2. **Ejecutar la aplicación:**
   - En el **Package Explorer**, navegar hasta la clase principal:  
     `src/main/java` → `com.midominio.group.app.spring` → `Application.java`
   - Clic derecho sobre `Application.java` → `Run As` → `Spring Boot App`.
   - Alternativamente: clic derecho sobre el proyecto → `Run As` → `Spring Boot App`.

3. **Verificar el arranque:**
   - En la consola de Eclipse debe aparecer el banner de Spring Boot y el mensaje:  
     `Started Application in X seconds`
   - El servidor estará disponible en **http://localhost:8080**.

> **Nota:** Si Eclipse no muestra la opción *Spring Boot App*, asegurarse de tener instalado el plugin **Spring Tools (STS)** desde el Eclipse Marketplace (`Help` → `Eclipse Marketplace…` → buscar "Spring Tools").

Al iniciar, `data.sql` insertará automáticamente:
- 1 usuario administrador
- 50 preguntas de ejemplo en diversas temáticas

### Paso 4 — Ejecutar el Frontend Web (React)

```bash
cd app-reactjs
npm install
npm run dev
```

La aplicación web estará disponible en **http://localhost:5173**.

### Paso 5 — Ejecutar la Aplicación Móvil (React Native) *(opcional)*

```bash
cd app-native
npm install
npm run web
```

> **Dependencia requerida:** La aplicación móvil utiliza **Axios** como cliente HTTP para consumir la API REST. Esta dependencia se instala automáticamente con `npm install`, pero si por algún motivo no se resuelve correctamente, puede instalarse de forma manual:

> ```bash
> npm install axios
> ```


De esta manera se iniciará el bundler de **Expo** y se abrirá automáticamente la aplicación en el navegador mediante la plataforma **React Native for Web**. La interfaz renderiza un componente `FlatList` que consume el endpoint público `GET /api/public/preguntas` a través de un hook personalizado (`usePublicPreguntas`), mostrando el listado de preguntas activas con su enunciado, temática y tipo de pregunta.

### Resumen de puertos

| Servicio | URL |
|---|---|
| Backend Spring Boot | http://localhost:8080 |
| Frontend React | http://localhost:5173 |
| Expo (React Native) | http://localhost:8081 |
| MySQL | localhost:3307 |
| MongoDB | localhost:27017 |
| Swagger UI | http://localhost:8080/swagger-ui.html |

---

## 📘 Documentación Swagger

Una vez el backend esté en ejecución, la documentación interactiva de la API está disponible en:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Desde Swagger UI es posible probar todos los endpoints directamente, autenticándose con HTTP Basic Auth.

---

## 🧪 Datos de Prueba

### Usuario administrador precargado

| Campo | Valor |
|---|---|
| Username | `admin` |
| Password | `admin` *(hasheado con BCrypt en BD)* |
| Rol | `ADMIN` |

### Temáticas disponibles en seed

`Ciencia` · `Programación` · `Astronomía` · `Geografía` · `Tecnología` · `Informática` · `Biología` · `Matemáticas` · `Bases de Datos`

### Formato CSV para importación

```csv
tipo,enunciado,tematica,explicacion,respuestaCorrecta,opciones
VERDADERO_FALSO,¿Java es un lenguaje compilado?,Programación,Se compila a bytecode,true,
UNICA,¿Cuál es el planeta más grande?,Astronomía,Júpiter es el mayor,0,Júpiter;Saturno;Marte
MULTIPLE,Selecciona lenguajes de programación,Tecnología,Java y Python son lenguajes,0;1,Java;Python;HTML
```

---

## 👩‍💻 Autora

**Angie Amado**

*Proyecto Final — Ciclo Formativo de Grado Superior en Desarrollo de Aplicaciones Multiplataforma (DAM)*
