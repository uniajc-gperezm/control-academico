-- ======================================================================
-- Universidad - Sistema Académico (MySQL 8.0+)
-- Esquema + SEED listo para importar
-- Autor: ChatGPT
-- Fecha: 2025-10-29
-- ======================================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS `academico-v2` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `academico-v2`;

-- =========================
-- 1) CATÁLOGOS GEOGRÁFICOS
-- =========================
CREATE TABLE IF NOT EXISTS paises (
  pais_id INT AUTO_INCREMENT PRIMARY KEY,
  nombre  VARCHAR(120) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS departamentos (
  dpto_id INT AUTO_INCREMENT PRIMARY KEY,
  pais_id INT NOT NULL,
  nombre  VARCHAR(120) NOT NULL,
  UNIQUE KEY uq_departamento (pais_id, nombre),
  CONSTRAINT fk_dpto_pais FOREIGN KEY (pais_id) REFERENCES paises(pais_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ciudades (
  ciudad_id INT AUTO_INCREMENT PRIMARY KEY,
  dpto_id   INT NOT NULL,
  nombre    VARCHAR(120) NOT NULL,
  UNIQUE KEY uq_ciudad (dpto_id, nombre),
  CONSTRAINT fk_ciudad_dpto FOREIGN KEY (dpto_id) REFERENCES departamentos(dpto_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 2) IDENTIDAD & ACCESO
-- =========================
CREATE TABLE IF NOT EXISTS personas (
  persona_id       CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
  documento_tipo   ENUM('CC','CE','TI','PP','NIT','OTRO') NOT NULL,
  documento_numero VARCHAR(40) NOT NULL,
  nombres          VARCHAR(120) NOT NULL,
  apellidos        VARCHAR(120) NOT NULL,
  genero           ENUM('masculino','femenino','otro','no_declara') NOT NULL DEFAULT 'no_declara',
  email            VARCHAR(190) NOT NULL,
  telefono         VARCHAR(40),
  ciudad_id        INT,
  direccion        VARCHAR(200),
  fecha_nacimiento DATE,
  created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_persona_doc (documento_tipo, documento_numero),
  UNIQUE KEY uq_persona_email (email),
  CONSTRAINT fk_persona_ciudad FOREIGN KEY (ciudad_id) REFERENCES ciudades(ciudad_id)
    ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS usuarios (
  usuario_id    CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
  persona_id    CHAR(36) NOT NULL,
  username      VARCHAR(100) NOT NULL,
  password_hash TEXT NOT NULL,
  activo        TINYINT(1) NOT NULL DEFAULT 1,
  created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_username (username),
  UNIQUE KEY uq_usuario_persona (persona_id),
  CONSTRAINT fk_usuario_persona FOREIGN KEY (persona_id) REFERENCES personas(persona_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS roles (
  rol_id INT AUTO_INCREMENT PRIMARY KEY,
  nombre ENUM('estudiante','docente','administrativo','superadmin') NOT NULL UNIQUE,
  descripcion TEXT
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS usuario_roles (
  usuario_id CHAR(36) NOT NULL,
  rol_id     INT NOT NULL,
  PRIMARY KEY (usuario_id, rol_id),
  CONSTRAINT fk_ur_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(usuario_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_ur_rol FOREIGN KEY (rol_id) REFERENCES roles(rol_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS estudiantes (
  estudiante_id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
  persona_id    CHAR(36) NOT NULL,
  codigo        VARCHAR(30) NOT NULL,
  fecha_ingreso DATE NOT NULL,
  activo        TINYINT(1) NOT NULL DEFAULT 1,
  created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_est_codigo (codigo),
  UNIQUE KEY uq_est_persona (persona_id),
  CONSTRAINT fk_est_persona FOREIGN KEY (persona_id) REFERENCES personas(persona_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS docentes (
  docente_id  CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID()),
  persona_id  CHAR(36) NOT NULL,
  titulo      VARCHAR(120),
  categoria   VARCHAR(60),
  activo      TINYINT(1) NOT NULL DEFAULT 1,
  created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_doc_persona (persona_id),
  CONSTRAINT fk_doc_persona FOREIGN KEY (persona_id) REFERENCES personas(persona_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 3) ESTRUCTURA ACADÉMICA
-- =========================
CREATE TABLE IF NOT EXISTS periodos_academicos (
  periodo_id  INT AUTO_INCREMENT PRIMARY KEY,
  nombre      VARCHAR(40) NOT NULL UNIQUE,
  fecha_inicio DATE NOT NULL,
  fecha_fin    DATE NOT NULL,
  estado       VARCHAR(20) NOT NULL DEFAULT 'abierto',
  CHECK (fecha_fin > fecha_inicio)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS programas (
  programa_id INT AUTO_INCREMENT PRIMARY KEY,
  nombre      VARCHAR(140) NOT NULL UNIQUE,
  nivel       VARCHAR(40)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS asignaturas (
  asignatura_id INT AUTO_INCREMENT PRIMARY KEY,
  programa_id   INT,
  codigo        VARCHAR(40) NOT NULL UNIQUE,
  nombre        VARCHAR(160) NOT NULL,
  creditos      INT NOT NULL,
  horas_semana  INT,
  activa        TINYINT(1) NOT NULL DEFAULT 1,
  CONSTRAINT fk_asig_prog FOREIGN KEY (programa_id) REFERENCES programas(programa_id)
    ON DELETE SET NULL ON UPDATE CASCADE,
  CHECK (creditos > 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS asignatura_requisitos (
  asignatura_id INT NOT NULL,
  requisito_id  INT NOT NULL,
  PRIMARY KEY (asignatura_id, requisito_id),
  CONSTRAINT fk_ar_asig FOREIGN KEY (asignatura_id) REFERENCES asignaturas(asignatura_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_ar_req FOREIGN KEY (requisito_id) REFERENCES asignaturas(asignatura_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CHECK (asignatura_id <> requisito_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS cursos (
  curso_id           INT AUTO_INCREMENT PRIMARY KEY,
  asignatura_id      INT NOT NULL,
  periodo_id         INT NOT NULL,
  seccion            VARCHAR(10) NOT NULL DEFAULT 'A',
  cupo_max           INT NOT NULL DEFAULT 40,
  observaciones      TEXT,
  created_at         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at         TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_curso (asignatura_id, periodo_id, seccion),
  CONSTRAINT fk_curso_asig FOREIGN KEY (asignatura_id) REFERENCES asignaturas(asignatura_id)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_curso_periodo FOREIGN KEY (periodo_id) REFERENCES periodos_academicos(periodo_id)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CHECK (cupo_max > 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS curso_docentes (
  curso_id   INT NOT NULL,
  docente_id CHAR(36) NOT NULL,
  rol        ENUM('owner','co','monitor') NOT NULL DEFAULT 'owner',
  PRIMARY KEY (curso_id, docente_id),
  CONSTRAINT fk_cd_curso FOREIGN KEY (curso_id) REFERENCES cursos(curso_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_cd_doc FOREIGN KEY (docente_id) REFERENCES docentes(docente_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS aulas (
  aula_id   INT AUTO_INCREMENT PRIMARY KEY,
  codigo    VARCHAR(40) NOT NULL UNIQUE,
  capacidad INT,
  CHECK (capacidad IS NULL OR capacidad > 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS clases (
  clase_id   INT AUTO_INCREMENT PRIMARY KEY,
  curso_id   INT NOT NULL,
  numero     INT NOT NULL,
  fecha      DATE NOT NULL,
  tema       VARCHAR(240),
  aula_id    INT,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_clase_num (curso_id, numero),
  UNIQUE KEY uq_clase_fecha (curso_id, fecha),
  CONSTRAINT fk_clase_curso FOREIGN KEY (curso_id) REFERENCES cursos(curso_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_clase_aula FOREIGN KEY (aula_id) REFERENCES aulas(aula_id)
    ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 4) MATRÍCULAS & ASISTENCIAS
-- =========================
CREATE TABLE IF NOT EXISTS matriculas (
  matricula_id  INT AUTO_INCREMENT PRIMARY KEY,
  estudiante_id CHAR(36) NOT NULL,
  curso_id      INT NOT NULL,
  estado        ENUM('inscrito','retirado','aprobado','reprobado','convalidado') NOT NULL DEFAULT 'inscrito',
  fecha_inscripcion DATE NOT NULL,
  fecha_retiro      DATE NULL,
  observaciones     TEXT,
  created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_matricula (estudiante_id, curso_id),
  CONSTRAINT fk_mat_est FOREIGN KEY (estudiante_id) REFERENCES estudiantes(estudiante_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_mat_curso FOREIGN KEY (curso_id) REFERENCES cursos(curso_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS asistencias (
  asistencia_id INT AUTO_INCREMENT PRIMARY KEY,
  matricula_id  INT NOT NULL,
  clase_id      INT NOT NULL,
  estado        ENUM('presente','ausente','tardanza','justificado') NOT NULL,
  novedades     TEXT,
  registrado_por CHAR(36) NULL,
  created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_asistencia (matricula_id, clase_id),
  CONSTRAINT fk_asist_matr FOREIGN KEY (matricula_id) REFERENCES matriculas(matricula_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_asist_clase FOREIGN KEY (clase_id) REFERENCES clases(clase_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_asist_usuario FOREIGN KEY (registrado_por) REFERENCES usuarios(usuario_id)
    ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 5) EVALUACIÓN
-- =========================
CREATE TABLE IF NOT EXISTS cortes_curso (
  corte_id   INT AUTO_INCREMENT PRIMARY KEY,
  curso_id   INT NOT NULL,
  codigo     VARCHAR(20) NOT NULL, -- C1, C2, C3_NOTAS, C3_INTEGRADOR
  nombre     VARCHAR(120) NOT NULL,
  porcentaje DECIMAL(5,2) NOT NULL,
  UNIQUE KEY uq_corte (curso_id, codigo),
  CONSTRAINT fk_corte_curso FOREIGN KEY (curso_id) REFERENCES cursos(curso_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CHECK (porcentaje > 0 AND porcentaje <= 100)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS componentes_evaluacion (
  componente_id INT AUTO_INCREMENT PRIMARY KEY,
  corte_id      INT NOT NULL,
  nombre        VARCHAR(160) NOT NULL,
  porcentaje    DECIMAL(5,2) NOT NULL,
  descripcion   TEXT,
  fecha_publicacion DATE NULL,
  created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_comp (corte_id, nombre),
  CONSTRAINT fk_comp_corte FOREIGN KEY (corte_id) REFERENCES cortes_curso(corte_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CHECK (porcentaje > 0 AND porcentaje <= 100)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS calificaciones (
  calificacion_id INT AUTO_INCREMENT PRIMARY KEY,
  matricula_id    INT NOT NULL,
  componente_id   INT NOT NULL,
  nota            DECIMAL(4,2) NOT NULL,
  comentarios     TEXT,
  publicado       TINYINT(1) NOT NULL DEFAULT 0,
  registrado_por  CHAR(36) NULL,
  created_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY uq_calif (matricula_id, componente_id),
  CONSTRAINT fk_calif_matr FOREIGN KEY (matricula_id) REFERENCES matriculas(matricula_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_calif_comp FOREIGN KEY (componente_id) REFERENCES componentes_evaluacion(componente_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_calif_usuario FOREIGN KEY (registrado_por) REFERENCES usuarios(usuario_id)
    ON DELETE SET NULL ON UPDATE CASCADE,
  CHECK (nota >= 0 AND nota <= 5)
) ENGINE=InnoDB;

-- =========================
-- 6) PROYECTO INTEGRADOR
-- =========================
CREATE TABLE IF NOT EXISTS proyectos_integradores (
  proyecto_id  INT AUTO_INCREMENT PRIMARY KEY,
  periodo_id   INT NOT NULL,
  nombre       VARCHAR(200) NOT NULL,
  descripcion  TEXT,
  creado_por   CHAR(36) NULL,
  created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_proy_periodo FOREIGN KEY (periodo_id) REFERENCES periodos_academicos(periodo_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_proy_usuario FOREIGN KEY (creado_por) REFERENCES usuarios(usuario_id)
    ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS proyecto_asignaturas (
  proyecto_id   INT NOT NULL,
  asignatura_id INT NOT NULL,
  PRIMARY KEY (proyecto_id, asignatura_id),
  CONSTRAINT fk_pa_proy FOREIGN KEY (proyecto_id) REFERENCES proyectos_integradores(proyecto_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_pa_asig FOREIGN KEY (asignatura_id) REFERENCES asignaturas(asignatura_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS entregas_integrador (
  entrega_id   INT AUTO_INCREMENT PRIMARY KEY,
  proyecto_id  INT NOT NULL,
  numero       INT NOT NULL,
  nombre       VARCHAR(160) NOT NULL,
  descripcion  TEXT,
  fecha_limite DATE,
  UNIQUE KEY uq_entrega (proyecto_id, numero),
  CONSTRAINT fk_ent_proy FOREIGN KEY (proyecto_id) REFERENCES proyectos_integradores(proyecto_id)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS componente_entrega_integrador (
  componente_id INT NOT NULL PRIMARY KEY,
  entrega_id    INT NOT NULL,
  CONSTRAINT fk_cei_comp FOREIGN KEY (componente_id) REFERENCES componentes_evaluacion(componente_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_cei_ent FOREIGN KEY (entrega_id) REFERENCES entregas_integrador(entrega_id)
    ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- =========================
-- 7) TRIGGERS DE INTEGRIDAD
-- =========================
DROP TRIGGER IF EXISTS trg_cortes_validate_ins;
DROP TRIGGER IF EXISTS trg_cortes_validate_upd;
DELIMITER $$
CREATE TRIGGER trg_cortes_validate_ins
BEFORE INSERT ON cortes_curso
FOR EACH ROW
BEGIN
  IF NEW.codigo NOT IN ('C1','C2','C3_NOTAS','C3_INTEGRADOR') THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Código de corte inválido';
  END IF;
  DECLARE total DECIMAL(6,2);
  SELECT IFNULL(SUM(porcentaje),0) INTO total FROM cortes_curso WHERE curso_id = NEW.curso_id;
  IF total + NEW.porcentaje > 100.00 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Suma de cortes excede 100%';
  END IF;
END$$

CREATE TRIGGER trg_cortes_validate_upd
BEFORE UPDATE ON cortes_curso
FOR EACH ROW
BEGIN
  IF NEW.codigo NOT IN ('C1','C2','C3_NOTAS','C3_INTEGRADOR') THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Código de corte inválido';
  END IF;
  DECLARE total DECIMAL(6,2);
  SELECT IFNULL(SUM(porcentaje),0) - OLD.porcentaje INTO total FROM cortes_curso WHERE curso_id = NEW.curso_id;
  IF total + NEW.porcentaje > 100.00 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Suma de cortes excede 100%';
  END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS trg_comp_validate_ins;
DROP TRIGGER IF EXISTS trg_comp_validate_upd;
DELIMITER $$
CREATE TRIGGER trg_comp_validate_ins
BEFORE INSERT ON componentes_evaluacion
FOR EACH ROW
BEGIN
  DECLARE total DECIMAL(6,2);
  SELECT IFNULL(SUM(porcentaje),0) INTO total FROM componentes_evaluacion WHERE corte_id = NEW.corte_id;
  IF total + NEW.porcentaje > 100.00 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Suma de componentes del corte excede 100%';
  END IF;
END$$

CREATE TRIGGER trg_comp_validate_upd
BEFORE UPDATE ON componentes_evaluacion
FOR EACH ROW
BEGIN
  DECLARE total DECIMAL(6,2);
  SELECT IFNULL(SUM(porcentaje),0) - OLD.porcentaje INTO total FROM componentes_evaluacion WHERE corte_id = NEW.corte_id;
  IF total + NEW.porcentaje > 100.00 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Suma de componentes del corte excede 100%';
  END IF;
END$$
DELIMITER ;

-- =========================
-- 8) VISTAS
-- =========================
DROP VIEW IF EXISTS vw_asistencia_porcentaje;
CREATE VIEW vw_asistencia_porcentaje AS
SELECT
  m.matricula_id,
  m.estudiante_id,
  m.curso_id,
  ROUND(100.0 * SUM(CASE WHEN a.estado='presente' THEN 1 ELSE 0 END) / NULLIF(COUNT(a.asistencia_id),0), 2) AS porcentaje_asistencia
FROM matriculas m
LEFT JOIN asistencias a ON a.matricula_id = m.matricula_id
GROUP BY m.matricula_id;

DROP VIEW IF EXISTS vw_notas_por_corte_y_final;
CREATE VIEW vw_notas_por_corte_y_final AS
WITH comp AS (
  SELECT c.corte_id, c.curso_id, c.codigo, c.porcentaje AS pct_corte,
         ce.componente_id, ce.porcentaje AS pct_comp
  FROM cortes_curso c
  JOIN componentes_evaluacion ce ON ce.corte_id = c.corte_id
),
cal AS (
  SELECT cal.matricula_id, comp.corte_id, comp.codigo,
         cal.nota, comp.pct_corte, comp.pct_comp
  FROM calificaciones cal
  JOIN comp ON comp.componente_id = cal.componente_id
)
SELECT
  m.matricula_id, m.estudiante_id, m.curso_id,
  ROUND(SUM(CASE WHEN cal.codigo='C1' THEN cal.nota * (cal.pct_corte/100.0) * (cal.pct_comp/100.0) ELSE 0 END),2) AS nota_c1,
  ROUND(SUM(CASE WHEN cal.codigo='C2' THEN cal.nota * (cal.pct_corte/100.0) * (cal.pct_comp/100.0) ELSE 0 END),2) AS nota_c2,
  ROUND(SUM(CASE WHEN cal.codigo='C3_NOTAS' THEN cal.nota * (cal.pct_corte/100.0) * (cal.pct_comp/100.0) ELSE 0 END),2) AS nota_c3_notas,
  ROUND(SUM(CASE WHEN cal.codigo='C3_INTEGRADOR' THEN cal.nota * (cal.pct_corte/100.0) * (cal.pct_comp/100.0) ELSE 0 END),2) AS nota_c3_integrador,
  ROUND(SUM(cal.nota * (cal.pct_corte/100.0) * (cal.pct_comp/100.0)),2) AS nota_final
FROM matriculas m
LEFT JOIN cal ON cal.matricula_id = m.matricula_id
GROUP BY m.matricula_id;

DROP VIEW IF EXISTS vw_kardex_estudiante;
CREATE VIEW vw_kardex_estudiante AS
SELECT
  e.estudiante_id,
  p.nombre AS periodo,
  a.codigo AS asignatura_codigo,
  a.nombre AS asignatura_nombre,
  c.curso_id,
  n.nota_c1, n.nota_c2, n.nota_c3_notas, n.nota_c3_integrador, n.nota_final
FROM estudiantes e
JOIN matriculas m ON m.estudiante_id = e.estudiante_id
JOIN cursos c      ON c.curso_id = m.curso_id
JOIN periodos_academicos p ON p.periodo_id = c.periodo_id
JOIN asignaturas a ON a.asignatura_id = c.asignatura_id
LEFT JOIN vw_notas_por_corte_y_final n ON n.matricula_id = m.matricula_id;

-- =========================
-- 9) ÍNDICES EXTRA
-- =========================
CREATE INDEX idx_personas_ciudad ON personas(ciudad_id);
CREATE INDEX idx_estudiantes_persona ON estudiantes(persona_id);
CREATE INDEX idx_docentes_persona ON docentes(persona_id);
CREATE INDEX idx_cursos_periodo ON cursos(periodo_id);
CREATE INDEX idx_cursos_asignatura ON cursos(asignatura_id);
CREATE INDEX idx_matriculas_est ON matriculas(estudiante_id);
CREATE INDEX idx_matriculas_cur ON matriculas(curso_id);
CREATE INDEX idx_clases_curso_fecha ON clases(curso_id, fecha);
CREATE INDEX idx_asist_matr ON asistencias(matricula_id);
CREATE INDEX idx_asist_clase ON asistencias(clase_id);
CREATE INDEX idx_cortes_curso ON cortes_curso(curso_id);
CREATE INDEX idx_comp_corte ON componentes_evaluacion(corte_id);
CREATE INDEX idx_calif_matr ON calificaciones(matricula_id);
CREATE INDEX idx_calif_comp ON calificaciones(componente_id);

-- =========================
-- 10) SEED (DEMO)
-- =========================
INSERT IGNORE INTO roles (nombre, descripcion) VALUES
('estudiante','Acceso para estudiantes'),
('docente','Acceso para docentes'),
('administrativo','Acceso para administrativos'),
('superadmin','Acceso total');

INSERT IGNORE INTO paises (nombre) VALUES ('Colombia');
INSERT IGNORE INTO departamentos (pais_id, nombre)
SELECT pais_id, 'Valle del Cauca' FROM paises WHERE nombre='Colombia';
INSERT IGNORE INTO ciudades (dpto_id, nombre)
SELECT dpto_id, 'Cali' FROM departamentos WHERE nombre='Valle del Cauca';

-- Personas demo
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','100000001','Ana','Admin','femenino','ana.admin@uni.edu','3001111111', c.ciudad_id
FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','200000001','Diego','Docente','masculino','diego.docente@uni.edu','3002222222', c.ciudad_id
FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','200000002','Laura','Docente','femenino','laura.docente@uni.edu','3003333333', c.ciudad_id
FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;

-- Estudiantes
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','300000001','Camila','Pérez','femenino','camila.est1@uni.edu','3004444444', c.ciudad_id FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','300000002','Juan','López','masculino','juan.est2@uni.edu','3005555555', c.ciudad_id FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','300000003','Sofía','García','femenino','sofia.est3@uni.edu','3006666666', c.ciudad_id FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','300000004','Andrés','Martínez','masculino','andres.est4@uni.edu','3007777777', c.ciudad_id FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','300000005','Valentina','Rojas','femenino','valentina.est5@uni.edu','3008888888', c.ciudad_id FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;
INSERT IGNORE INTO personas (documento_tipo, documento_numero, nombres, apellidos, genero, email, telefono, ciudad_id)
SELECT 'CC','300000006','Felipe','Torres','masculino','felipe.est6@uni.edu','3009999999', c.ciudad_id FROM ciudades c WHERE c.nombre='Cali' LIMIT 1;

-- Usuarios
INSERT IGNORE INTO usuarios (persona_id, username, password_hash)
SELECT persona_id, 'ana.admin', 'hash_demo' FROM personas WHERE email='ana.admin@uni.edu';
INSERT IGNORE INTO usuarios (persona_id, username, password_hash)
SELECT persona_id, 'diego.docente', 'hash_demo' FROM personas WHERE email='diego.docente@uni.edu';
INSERT IGNORE INTO usuarios (persona_id, username, password_hash)
SELECT persona_id, 'laura.docente', 'hash_demo' FROM personas WHERE email='laura.docente@uni.edu';

-- Roles a usuarios
INSERT IGNORE INTO usuario_roles (usuario_id, rol_id)
SELECT u.usuario_id, r.rol_id
FROM usuarios u JOIN roles r ON r.nombre='administrativo'
WHERE u.username='ana.admin';

INSERT IGNORE INTO usuario_roles (usuario_id, rol_id)
SELECT u.usuario_id, r.rol_id
FROM usuarios u JOIN roles r ON r.nombre='docente'
WHERE u.username IN ('diego.docente','laura.docente');

-- Perfiles
INSERT IGNORE INTO docentes (persona_id, titulo, categoria)
SELECT persona_id, 'MSc. Ingeniería', 'TC' FROM personas WHERE email='diego.docente@uni.edu';
INSERT IGNORE INTO docentes (persona_id, titulo, categoria)
SELECT persona_id, 'Ing. de Sistemas', 'Cátedra' FROM personas WHERE email='laura.docente@uni.edu';

INSERT IGNORE INTO estudiantes (persona_id, codigo, fecha_ingreso)
SELECT persona_id, CONCAT('EST-', SUBSTRING_INDEX(email,'@',1)), CURRENT_DATE()
FROM personas WHERE email LIKE '%.est%';

-- Programa/Asignaturas
INSERT IGNORE INTO programas (nombre, nivel) VALUES ('Ingeniería de Sistemas','Pregrado');

INSERT IGNORE INTO asignaturas (programa_id, codigo, nombre, creditos, horas_semana)
SELECT p.programa_id, v.codigo, v.nombre, v.creditos, v.horas
FROM programas p
JOIN (SELECT 'BD101' AS codigo, 'Base de Datos' AS nombre, 3 AS creditos, 4 AS horas UNION ALL
      SELECT 'IS201','Ingeniería de Software',3,4 UNION ALL
      SELECT 'PRG202','Programación II (Java)',3,4) AS v
ON p.nombre='Ingeniería de Sistemas';

-- Periodo y cursos
INSERT IGNORE INTO periodos_academicos (nombre, fecha_inicio, fecha_fin, estado)
VALUES ('2025-2', '2025-07-15', '2025-12-15', 'abierto');

INSERT IGNORE INTO cursos (asignatura_id, periodo_id, seccion, cupo_max)
SELECT a.asignatura_id, p.periodo_id, 'A', 40
FROM asignaturas a
JOIN periodos_academicos p ON p.nombre='2025-2'
WHERE a.codigo IN ('BD101','IS201','PRG202');

-- Asignar docente owner por curso (PRG202 -> Diego; BD/IS -> Laura)
INSERT IGNORE INTO curso_docentes (curso_id, docente_id, rol)
SELECT c.curso_id, d.docente_id, 'owner'
FROM cursos c
JOIN asignaturas a ON a.asignatura_id=c.asignatura_id
JOIN docentes d ON d.persona_id IN (
  SELECT persona_id FROM personas WHERE email=IF(a.codigo='PRG202','diego.docente@uni.edu','laura.docente@uni.edu')
);

-- Aulas y clases
INSERT IGNORE INTO aulas (codigo, capacidad) VALUES ('A-101',45),('A-102',40);

-- 8 clases por curso (una por semana desde 2025-08-01)
INSERT IGNORE INTO clases (curso_id, numero, fecha, tema, aula_id)
SELECT c.curso_id, n.num, DATE_ADD('2025-08-01', INTERVAL (n.num-1) WEEK), CONCAT('Tema ', n.num), a.aula_id
FROM cursos c
JOIN aulas a ON a.codigo='A-101'
JOIN (SELECT 1 AS num UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL
      SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8) n;

-- Matrículas: 6 estudiantes x curso
INSERT IGNORE INTO matriculas (estudiante_id, curso_id, estado, fecha_inscripcion)
SELECT e.estudiante_id, c.curso_id, 'inscrito', CURRENT_DATE()
FROM (SELECT estudiante_id FROM estudiantes ORDER BY estudiante_id LIMIT 6) e
CROSS JOIN cursos c;

-- Cortes (30/30/20/20)
INSERT IGNORE INTO cortes_curso (curso_id, codigo, nombre, porcentaje)
SELECT curso_id, 'C1', 'Primer Corte', 30.00 FROM cursos;
INSERT IGNORE INTO cortes_curso (curso_id, codigo, nombre, porcentaje)
SELECT curso_id, 'C2', 'Segundo Corte', 30.00 FROM cursos;
INSERT IGNORE INTO cortes_curso (curso_id, codigo, nombre, porcentaje)
SELECT curso_id, 'C3_NOTAS', 'Tercer Corte - Notas adicionales', 20.00 FROM cursos;
INSERT IGNORE INTO cortes_curso (curso_id, codigo, nombre, porcentaje)
SELECT curso_id, 'C3_INTEGRADOR', 'Tercer Corte - Proyecto Integrador', 20.00 FROM cursos;

-- Componentes
-- C1: Talleres 40 / Parcial 1 60
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Talleres/Actividades', 40.00, 'Actividades del C1' FROM cortes_curso WHERE codigo='C1';
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Parcial 1', 60.00, 'Parcial del C1' FROM cortes_curso WHERE codigo='C1';

-- C2: Talleres 40 / Parcial 2 60
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Talleres/Actividades', 40.00, 'Actividades del C2' FROM cortes_curso WHERE codigo='C2';
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Parcial 2', 60.00, 'Parcial del C2' FROM cortes_curso WHERE codigo='C2';

-- C3_NOTAS: 100%
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Notas Adicionales', 100.00, 'Quices/Labs/Otros' FROM cortes_curso WHERE codigo='C3_NOTAS';

-- C3_INTEGRADOR: Entrega 1 (33) / Entrega 2 (33) / Demo (34)
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Entrega 1 (Aproximación y diseño)', 33.00, 'Primera entrega' FROM cortes_curso WHERE codigo='C3_INTEGRADOR';
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Entrega 2 (Implementación y documento)', 33.00, 'Segunda entrega' FROM cortes_curso WHERE codigo='C3_INTEGRADOR';
INSERT IGNORE INTO componentes_evaluacion (corte_id, nombre, porcentaje, descripcion)
SELECT corte_id, 'Demo / Presentación', 34.00, 'Tercera entrega' FROM cortes_curso WHERE codigo='C3_INTEGRADOR';

-- Proyecto Integrador por periodo 2025-2
INSERT IGNORE INTO proyectos_integradores (periodo_id, nombre, descripcion)
SELECT p.periodo_id, 'Proyecto Integrador - 2025-2', 'BD + IS + PRG II (Java). 3 entregas.'
FROM periodos_academicos p WHERE p.nombre='2025-2';

-- Vincular asignaturas al proyecto
INSERT IGNORE INTO proyecto_asignaturas (proyecto_id, asignatura_id)
SELECT pi.proyecto_id, a.asignatura_id
FROM proyectos_integradores pi
JOIN asignaturas a ON a.codigo IN ('BD101','IS201','PRG202')
WHERE pi.periodo_id = (SELECT periodo_id FROM periodos_academicos WHERE nombre='2025-2');

-- Entregas 1..3
INSERT IGNORE INTO entregas_integrador (proyecto_id, numero, nombre, descripcion, fecha_limite)
SELECT proyecto_id, 1, 'Entrega 1', 'Primera entrega: alcance y diseño', '2025-10-01'
FROM proyectos_integradores WHERE periodo_id=(SELECT periodo_id FROM periodos_academicos WHERE nombre='2025-2');
INSERT IGNORE INTO entregas_integrador (proyecto_id, numero, nombre, descripcion, fecha_limite)
SELECT proyecto_id, 2, 'Entrega 2', 'Segunda entrega: implementación + documento', '2025-11-01'
FROM proyectos_integradores WHERE periodo_id=(SELECT periodo_id FROM periodos_academicos WHERE nombre='2025-2');
INSERT IGNORE INTO entregas_integrador (proyecto_id, numero, nombre, descripcion, fecha_limite)
SELECT proyecto_id, 3, 'Demo', 'Presentación final', '2025-11-20'
FROM proyectos_integradores WHERE periodo_id=(SELECT periodo_id FROM periodos_academicos WHERE nombre='2025-2');

-- Mapear componentes del C3_INTEGRADOR a entregas
INSERT IGNORE INTO componente_entrega_integrador (componente_id, entrega_id)
SELECT ce.componente_id,
       CASE
         WHEN ce.nombre LIKE 'Entrega 1 %' THEN (SELECT e1.entrega_id FROM entregas_integrador e1 WHERE e1.numero=1 ORDER BY e1.entrega_id DESC LIMIT 1)
         WHEN ce.nombre LIKE 'Entrega 2 %' THEN (SELECT e2.entrega_id FROM entregas_integrador e2 WHERE e2.numero=2 ORDER BY e2.entrega_id DESC LIMIT 1)
         ELSE (SELECT e3.entrega_id FROM entregas_integrador e3 WHERE e3.numero=3 ORDER BY e3.entrega_id DESC LIMIT 1)
       END AS entrega_id
FROM componentes_evaluacion ce
JOIN cortes_curso cc ON cc.corte_id=ce.corte_id AND cc.codigo='C3_INTEGRADOR';

-- Asistencias demo: 3 primeras clases = presente
INSERT IGNORE INTO asistencias (matricula_id, clase_id, estado)
SELECT m.matricula_id, cl.clase_id, 'presente'
FROM matriculas m
JOIN clases cl ON cl.curso_id=m.curso_id AND cl.numero IN (1,2,3);

-- Notas demo
-- C1 Talleres = 3.80, C1 Parcial1 = 4.20, C2 Talleres = 4.50
INSERT IGNORE INTO calificaciones (matricula_id, componente_id, nota, publicado)
SELECT m.matricula_id, ce.componente_id, 3.80, 1
FROM matriculas m
JOIN cursos c ON c.curso_id=m.curso_id
JOIN cortes_curso cc ON cc.curso_id=c.curso_id AND cc.codigo='C1'
JOIN componentes_evaluacion ce ON ce.corte_id=cc.corte_id AND ce.nombre='Talleres/Actividades';

INSERT IGNORE INTO calificaciones (matricula_id, componente_id, nota, publicado)
SELECT m.matricula_id, ce.componente_id, 4.20, 1
FROM matriculas m
JOIN cursos c ON c.curso_id=m.curso_id
JOIN cortes_curso cc ON cc.curso_id=c.curso_id AND cc.codigo='C1'
JOIN componentes_evaluacion ce ON ce.corte_id=cc.corte_id AND ce.nombre='Parcial 1';

INSERT IGNORE INTO calificaciones (matricula_id, componente_id, nota, publicado)
SELECT m.matricula_id, ce.componente_id, 4.50, 1
FROM matriculas m
JOIN cursos c ON c.curso_id=m.curso_id
JOIN cortes_curso cc ON cc.curso_id=c.curso_id AND cc.codigo='C2'
JOIN componentes_evaluacion ce ON ce.corte_id=cc.corte_id AND ce.nombre='Talleres/Actividades';

SET FOREIGN_KEY_CHECKS=1;
-- ======================================================================
-- FIN
-- ======================================================================
