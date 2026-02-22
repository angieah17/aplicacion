
INSERT IGNORE INTO usuarios (id, username, password, role, enabled)
VALUES (
  1,
  'admin',
  '$2a$10$jp2cISXEHqwzxHpICfQ1duw4..RgBu8kp.f4SeRMU6HOA/kIIAjb6',
  'ADMIN',
  true
);

-- ==========================================
-- DATA.SQL - Datos iniciales Verdadero/Falso
-- ==========================================

-- ===============================
-- PREGUNTA 1
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    1,
    'La Tierra es plana',
    'Ciencia',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'La evidencia científica demuestra que la Tierra tiene forma esférica.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    1,
    FALSE
);

-- ===============================
-- PREGUNTA 2
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    2,
    'Java permite herencia múltiple de clases',
    'Programación',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Java no soporta herencia múltiple de clases; usa interfaces.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    2,
    FALSE
);

-- ===============================
-- PREGUNTA 3 (inactiva)
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    3,
    'El Sol es una estrella',
    'Astronomía',
    NOW(),
    FALSE,
    'VERDADERO_FALSO',
    'El Sol es una estrella de tipo espectral G2V.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    3,
    TRUE
);

-- ===============================
-- PREGUNTA 4
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    4,
    'Spring Boot es un framework de Python',
    'Programación',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Spring Boot pertenece al ecosistema Java/Spring.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    4,
    FALSE
);

-- ===============================
-- PREGUNTA 5
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    5,
    'JPA significa Java Persistence API',
    'Programación',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'JPA es la especificación estándar para ORM en Java.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    5,
    TRUE
);

-- ===============================
-- PREGUNTA 6
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    6,
    'El agua hierve a 100°C a nivel del mar',
    'Ciencia',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'A 1 atm de presión, el agua hierve a 100°C.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    6,
    TRUE
);

-- ==========================================
-- Datos iniciales Pregunta Única
-- ==========================================

-- ===============================
-- PREGUNTA 7
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    7,
    '¿Cuál es la capital de Francia?',
    'Geografía',
    NOW(),
    TRUE,
    'UNICA',
    'París es la capital y ciudad más grande de Francia.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    7,
    1
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(7, 'Londres', 0),
(7, 'París', 1),
(7, 'Berlín', 2),
(7, 'Madrid', 3);

-- ===============================
-- PREGUNTA 8
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    8,
    '¿Qué lenguaje se utiliza principalmente para desarrollo web front-end?',
    'Programación',
    NOW(),
    TRUE,
    'UNICA',
    'JavaScript es el lenguaje estándar para desarrollo web front-end.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    8,
    2
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(8, 'Python', 0),
(8, 'Java', 1),
(8, 'JavaScript', 2),
(8, 'C++', 3);

-- ===============================
-- PREGUNTA 9
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    9,
    '¿Cuál es el planeta más grande del Sistema Solar?',
    'Ciencia',
    NOW(),
    TRUE,
    'UNICA',
    'Júpiter es el planeta más grande con un diámetro de 142,984 km.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    9,
    1
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(9, 'Saturno', 0),
(9, 'Júpiter', 1),
(9, 'Neptuno', 2),
(9, 'Urano', 3);

-- ===============================
-- PREGUNTA 10 (inactiva)
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    10,
    '¿Qué framework Java se utiliza para crear aplicaciones web?',
    'Programación',
    NOW(),
    FALSE,
    'UNICA',
    'Spring Boot es el framework más popular para aplicaciones Java web.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    10,
    0
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(10, 'Spring Boot', 0),
(10, 'Django', 1),
(10, 'Express', 2),
(10, 'Laravel', 3);

-- ==========================================
-- Datos iniciales Pregunta Múltiple
-- ==========================================

-- ===============================
-- PREGUNTA 11
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    11,
    '¿Cuáles de los siguientes son lenguajes de programación orientados a objetos?',
    'Programación',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Java, Python y C++ son lenguajes OOP. JavaScript es un lenguaje orientado a prototipos, aunque también soporta características OOP.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    11
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(11, 'Java', 0),
(11, 'Python', 1),
(11, 'JavaScript', 2),
(11, 'C++', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(11, 0, 0),
(11, 1, 1),
(11, 3, 2);

-- ===============================
-- PREGUNTA 12
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    12,
    '¿Cuáles de las siguientes son características de Spring Boot?',
    'Programación',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Spring Boot proporciona configuración automática, servidor embebido y dependencias preconfiguradas. No requiere XML de configuración tradicional.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    12
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(12, 'Autoconfiguration', 0),
(12, 'Servidor embebido (Tomcat)', 1),
(12, 'Requiere configuración XML obligatoria', 2),
(12, 'Starters predefinidos', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(12, 0, 0),
(12, 1, 1),
(12, 3, 2);

-- ===============================
-- PREGUNTA 13
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    13,
    '¿Cuáles de los siguientes planetas tienen anillos?',
    'Astronomía',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Saturno, Júpiter, Urano y Neptuno tienen sistemas de anillos. Aunque Saturno es el más conocido por sus anillos visibles.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    13
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(13, 'Tierra', 0),
(13, 'Saturno', 1),
(13, 'Júpiter', 2),
(13, 'Urano', 3),
(13, 'Neptuno', 4);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(13, 1, 0),
(13, 2, 1),
(13, 3, 2),
(13, 4, 3);

-- ===============================
-- PREGUNTA 14 (inactiva)
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    14,
    '¿Cuáles de las siguientes son bases de datos SQL?',
    'Programación',
    NOW(),
    FALSE,
    'MULTIPLE',
    'MySQL, PostgreSQL y Oracle son bases de datos SQL. MongoDB es una base de datos NoSQL orientada a documentos.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    14
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(14, 'MySQL', 0),
(14, 'MongoDB', 1),
(14, 'PostgreSQL', 2),
(14, 'Oracle', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(14, 0, 0),
(14, 2, 1),
(14, 3, 2);

-- ==========================================
-- Datos adicionales de prueba (hasta 50)
-- ==========================================

-- ===============================
-- PREGUNTA 15
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    15,
    'El lenguaje Java fue desarrollado originalmente por Sun Microsystems',
    'Programación',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Java fue creado en Sun Microsystems antes de su adquisición por Oracle.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    15,
    TRUE
);

-- ===============================
-- PREGUNTA 16
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    16,
    'HTTP es un protocolo de base de datos',
    'Tecnología',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'HTTP es un protocolo de transferencia de hipertexto, no una base de datos.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    16,
    FALSE
);

-- ===============================
-- PREGUNTA 17
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    17,
    'Un byte está compuesto por 8 bits',
    'Informática',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'La unidad estándar byte equivale a 8 bits.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    17,
    TRUE
);

-- ===============================
-- PREGUNTA 18
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    18,
    'CSS se utiliza para manejar bases de datos relacionales',
    'Programación',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'CSS se utiliza para estilos visuales en páginas web.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    18,
    FALSE
);

-- ===============================
-- PREGUNTA 19
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    19,
    'La fotosíntesis ocurre en los cloroplastos',
    'Biología',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Los cloroplastos son los orgánulos donde ocurre la fotosíntesis.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    19,
    TRUE
);

-- ===============================
-- PREGUNTA 20
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    20,
    'El número pi es exactamente igual a 3.14',
    'Matemáticas',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Pi es un número irracional; 3.14 es una aproximación.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    20,
    FALSE
);

-- ===============================
-- PREGUNTA 21
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    21,
    'Git es un sistema de control de versiones distribuido',
    'Programación',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Git permite trabajo distribuido y control de versiones.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    21,
    TRUE
);

-- ===============================
-- PREGUNTA 22
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    22,
    'JSON es un lenguaje de programación compilado',
    'Tecnología',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'JSON es un formato de intercambio de datos, no un lenguaje compilado.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    22,
    FALSE
);

-- ===============================
-- PREGUNTA 23
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    23,
    'La unidad básica de información en una base de datos relacional es la tabla',
    'Bases de Datos',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Las tablas almacenan registros (filas) y campos (columnas).'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    23,
    TRUE
);

-- ===============================
-- PREGUNTA 24
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    24,
    'Docker solo funciona en sistemas Linux y no en Windows',
    'DevOps',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'Docker puede ejecutarse en Windows usando Docker Desktop.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    24,
    FALSE
);

-- ===============================
-- PREGUNTA 25
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    25,
    'Una API REST suele utilizar métodos HTTP como GET y POST',
    'Programación',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'REST se apoya en verbos HTTP para operaciones sobre recursos.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    25,
    TRUE
);

-- ===============================
-- PREGUNTA 26
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    26,
    'En SQL, la sentencia DELETE agrega nuevas filas a una tabla',
    'Bases de Datos',
    NOW(),
    TRUE,
    'VERDADERO_FALSO',
    'DELETE elimina filas; para insertar se usa INSERT.'
);

INSERT IGNORE INTO preguntas_verdadero_falso (
    id, respuesta_correcta
) VALUES (
    26,
    FALSE
);

-- ===============================
-- PREGUNTA 27
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    27,
    '¿Cuál es el resultado de 7 x 8?',
    'Matemáticas',
    NOW(),
    TRUE,
    'UNICA',
    '7 multiplicado por 8 es 56.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    27,
    2
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(27, '54', 0),
(27, '58', 1),
(27, '56', 2),
(27, '64', 3);

-- ===============================
-- PREGUNTA 28
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    28,
    '¿Qué protocolo se usa comúnmente para navegación web segura?',
    'Tecnología',
    NOW(),
    TRUE,
    'UNICA',
    'HTTPS cifra la comunicación entre cliente y servidor.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    28,
    1
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(28, 'FTP', 0),
(28, 'HTTPS', 1),
(28, 'SMTP', 2),
(28, 'Telnet', 3);

-- ===============================
-- PREGUNTA 29
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    29,
    '¿Qué estructura de datos sigue la política LIFO?',
    'Informática',
    NOW(),
    TRUE,
    'UNICA',
    'Una pila (stack) utiliza Last In, First Out.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    29,
    0
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(29, 'Pila', 0),
(29, 'Cola', 1),
(29, 'Árbol', 2),
(29, 'Grafo', 3);

-- ===============================
-- PREGUNTA 30
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    30,
    '¿Cuál de estos es un sistema gestor de bases de datos relacional?',
    'Bases de Datos',
    NOW(),
    TRUE,
    'UNICA',
    'PostgreSQL es un sistema relacional ampliamente utilizado.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    30,
    3
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(30, 'Redis', 0),
(30, 'MongoDB', 1),
(30, 'Cassandra', 2),
(30, 'PostgreSQL', 3);

-- ===============================
-- PREGUNTA 31
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    31,
    '¿Qué anotación de Spring Boot se usa para definir un controlador REST?',
    'Programación',
    NOW(),
    TRUE,
    'UNICA',
    '@RestController combina @Controller y @ResponseBody.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    31,
    2
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(31, '@Service', 0),
(31, '@Entity', 1),
(31, '@RestController', 2),
(31, '@Repository', 3);

-- ===============================
-- PREGUNTA 32
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    32,
    '¿Cuál es el océano más grande del planeta?',
    'Geografía',
    NOW(),
    TRUE,
    'UNICA',
    'El océano Pacífico es el de mayor extensión.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    32,
    1
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(32, 'Atlántico', 0),
(32, 'Pacífico', 1),
(32, 'Índico', 2),
(32, 'Ártico', 3);

-- ===============================
-- PREGUNTA 33
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    33,
    '¿Qué comando de Git crea una copia local de un repositorio remoto?',
    'Programación',
    NOW(),
    TRUE,
    'UNICA',
    'git clone descarga y crea la copia local del repositorio remoto.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    33,
    0
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(33, 'git clone', 0),
(33, 'git add', 1),
(33, 'git commit', 2),
(33, 'git merge', 3);

-- ===============================
-- PREGUNTA 34
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    34,
    '¿Qué planeta es conocido como el planeta rojo?',
    'Astronomía',
    NOW(),
    TRUE,
    'UNICA',
    'Marte recibe ese nombre por su color rojizo.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    34,
    2
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(34, 'Venus', 0),
(34, 'Júpiter', 1),
(34, 'Marte', 2),
(34, 'Saturno', 3);

-- ===============================
-- PREGUNTA 35
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    35,
    '¿Qué lenguaje se ejecuta principalmente en la JVM además de Java?',
    'Programación',
    NOW(),
    TRUE,
    'UNICA',
    'Kotlin corre sobre la JVM y es interoperable con Java.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    35,
    1
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(35, 'Ruby', 0),
(35, 'Kotlin', 1),
(35, 'PHP', 2),
(35, 'Swift', 3);

-- ===============================
-- PREGUNTA 36
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    36,
    '¿Cuál es la extensión típica de un archivo de estilos web?',
    'Tecnología',
    NOW(),
    TRUE,
    'UNICA',
    'Los archivos de hojas de estilo usan la extensión .css.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    36,
    3
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(36, '.js', 0),
(36, '.html', 1),
(36, '.sql', 2),
(36, '.css', 3);

-- ===============================
-- PREGUNTA 37
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    37,
    '¿Cuál de estas opciones representa un número primo?',
    'Matemáticas',
    NOW(),
    TRUE,
    'UNICA',
    '13 es primo porque solo es divisible por 1 y por sí mismo.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    37,
    2
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(37, '12', 0),
(37, '15', 1),
(37, '13', 2),
(37, '21', 3);

-- ===============================
-- PREGUNTA 38
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    38,
    '¿Cuál es el puerto por defecto de HTTP?',
    'Redes',
    NOW(),
    TRUE,
    'UNICA',
    'HTTP utiliza por defecto el puerto 80.'
);

INSERT IGNORE INTO preguntas_unica (
    id, respuesta_correcta
) VALUES (
    38,
    0
);

INSERT IGNORE INTO pregunta_unica_opciones (pregunta_id, opcion, orden) VALUES
(38, '80', 0),
(38, '21', 1),
(38, '25', 2),
(38, '3306', 3);

-- ===============================
-- PREGUNTA 39
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    39,
    '¿Cuáles de estos son sistemas operativos?',
    'Tecnología',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Windows, Linux y macOS son sistemas operativos; Chrome es un navegador.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    39
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(39, 'Windows', 0),
(39, 'Linux', 1),
(39, 'Chrome', 2),
(39, 'macOS', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(39, 0, 0),
(39, 1, 1),
(39, 3, 2);

-- ===============================
-- PREGUNTA 40
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    40,
    '¿Cuáles de estas son bases de datos NoSQL?',
    'Bases de Datos',
    NOW(),
    TRUE,
    'MULTIPLE',
    'MongoDB, Redis y Cassandra son NoSQL; PostgreSQL es relacional.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    40
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(40, 'MongoDB', 0),
(40, 'Redis', 1),
(40, 'PostgreSQL', 2),
(40, 'Cassandra', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(40, 0, 0),
(40, 1, 1),
(40, 3, 2);

-- ===============================
-- PREGUNTA 41
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    41,
    '¿Cuáles de estos son métodos HTTP válidos?',
    'Redes',
    NOW(),
    TRUE,
    'MULTIPLE',
    'GET, POST y DELETE son métodos HTTP; CONNECTOR no lo es.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    41
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(41, 'GET', 0),
(41, 'POST', 1),
(41, 'CONNECTOR', 2),
(41, 'DELETE', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(41, 0, 0),
(41, 1, 1),
(41, 3, 2);

-- ===============================
-- PREGUNTA 42
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    42,
    '¿Qué elementos químicos son gases nobles?',
    'Ciencia',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Helio, neón y argón pertenecen al grupo de gases nobles.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    42
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(42, 'Helio', 0),
(42, 'Oxígeno', 1),
(42, 'Neón', 2),
(42, 'Argón', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(42, 0, 0),
(42, 2, 1),
(42, 3, 2);

-- ===============================
-- PREGUNTA 43
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    43,
    '¿Cuáles de estos lenguajes son compilados típicamente?',
    'Programación',
    NOW(),
    TRUE,
    'MULTIPLE',
    'C, C++ y Go se compilan de forma típica antes de ejecutarse.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    43
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(43, 'C', 0),
(43, 'Python', 1),
(43, 'C++', 2),
(43, 'Go', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(43, 0, 0),
(43, 2, 1),
(43, 3, 2);

-- ===============================
-- PREGUNTA 44
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    44,
    '¿Cuáles de estas son características de una base de datos relacional?',
    'Bases de Datos',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Tablas, claves foráneas y SQL son rasgos típicos del modelo relacional.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    44
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(44, 'Tablas', 0),
(44, 'Claves foráneas', 1),
(44, 'SQL', 2),
(44, 'Colecciones sin esquema fijo', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(44, 0, 0),
(44, 1, 1),
(44, 2, 2);

-- ===============================
-- PREGUNTA 45
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    45,
    '¿Cuáles de estas herramientas se usan para contenerización?',
    'DevOps',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Docker y Podman son herramientas de contenedores; VirtualBox se centra en máquinas virtuales.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    45
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(45, 'Docker', 0),
(45, 'Podman', 1),
(45, 'VirtualBox', 2),
(45, 'containerd', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(45, 0, 0),
(45, 1, 1),
(45, 3, 2);

-- ===============================
-- PREGUNTA 46
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    46,
    '¿Cuáles de los siguientes son navegadores web?',
    'Tecnología',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Firefox, Edge y Safari son navegadores web; Ubuntu es un sistema operativo.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    46
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(46, 'Firefox', 0),
(46, 'Edge', 1),
(46, 'Ubuntu', 2),
(46, 'Safari', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(46, 0, 0),
(46, 1, 1),
(46, 3, 2);

-- ===============================
-- PREGUNTA 47
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    47,
    '¿Cuáles de estas capas forman parte del modelo TCP/IP?',
    'Redes',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Aplicación, transporte e internet son capas de TCP/IP; Presentación es del modelo OSI.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    47
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(47, 'Aplicación', 0),
(47, 'Transporte', 1),
(47, 'Presentación', 2),
(47, 'Internet', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(47, 0, 0),
(47, 1, 1),
(47, 3, 2);

-- ===============================
-- PREGUNTA 48
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    48,
    '¿Cuáles de estos son servicios de computación en la nube?',
    'Tecnología',
    NOW(),
    TRUE,
    'MULTIPLE',
    'AWS, Azure y Google Cloud son plataformas cloud ampliamente usadas.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    48
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(48, 'AWS', 0),
(48, 'Azure', 1),
(48, 'Google Cloud', 2),
(48, 'Photoshop', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(48, 0, 0),
(48, 1, 1),
(48, 2, 2);

-- ===============================
-- PREGUNTA 49
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    49,
    '¿Qué números son pares?',
    'Matemáticas',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Los números pares son divisibles entre 2 sin residuo.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    49
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(49, '7', 0),
(49, '10', 1),
(49, '13', 2),
(49, '18', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(49, 1, 0),
(49, 3, 1);

-- ===============================
-- PREGUNTA 50
-- ===============================
INSERT IGNORE INTO preguntas (
    id, enunciado, tematica, fecha_creacion, activa, tipo_pregunta, explicacion
) VALUES (
    50,
    '¿Cuáles de estos son frameworks o librerías del ecosistema Java?',
    'Programación',
    NOW(),
    TRUE,
    'MULTIPLE',
    'Spring, Hibernate y Jakarta EE pertenecen al ecosistema Java.'
);

INSERT IGNORE INTO preguntas_multiple (
    id
) VALUES (
    50
);

INSERT IGNORE INTO pregunta_multiple_opciones (pregunta_id, opcion, orden) VALUES
(50, 'Spring', 0),
(50, 'Hibernate', 1),
(50, 'React', 2),
(50, 'Jakarta EE', 3);

INSERT IGNORE INTO pregunta_multiple_respuestas (pregunta_id, respuesta_correcta, orden) VALUES
(50, 0, 0),
(50, 1, 1),
(50, 3, 2);