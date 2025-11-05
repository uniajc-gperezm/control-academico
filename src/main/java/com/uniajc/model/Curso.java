package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa una instancia de curso (asignatura + período + sección).
 *
 * <p>Un curso es la oferta concreta de una asignatura en un período específico.
 * Por ejemplo: "Base de Datos 2025-2 Sección A" es un curso.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Curso {

    private Integer cursoId;
    private Integer asignaturaId; // FK a asignaturas
    private Integer periodoId; // FK a periodos_academicos
    private String seccion; // Ej: "A", "B", "C"
    private Integer cupoMax;

    // Relaciones (opcionales)
    private Asignatura asignatura;
    private PeriodoAcademico periodo;

    public Curso() {
        this.seccion = "A";
        this.cupoMax = 40;
    }

    public Curso(Integer cursoId, Integer asignaturaId, Integer periodoId, String seccion, Integer cupoMax) {
        this.cursoId = cursoId;
        this.asignaturaId = asignaturaId;
        this.periodoId = periodoId;
        this.seccion = seccion != null ? seccion : "A";
        this.cupoMax = cupoMax != null ? cupoMax : 40;
    }

    public Curso(Integer asignaturaId, Integer periodoId, String seccion, Integer cupoMax) {
        this.asignaturaId = asignaturaId;
        this.periodoId = periodoId;
        this.seccion = seccion;
        this.cupoMax = cupoMax;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Integer getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Integer periodoId) {
        this.periodoId = periodoId;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Integer getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(Integer cupoMax) {
        this.cupoMax = cupoMax;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
        if (asignatura != null) {
            this.asignaturaId = asignatura.getAsignaturaId();
        }
    }

    public PeriodoAcademico getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoAcademico periodo) {
        this.periodo = periodo;
        if (periodo != null) {
            this.periodoId = periodo.getPeriodoId();
        }
    }

    /**
     * Obtiene un nombre descriptivo del curso.
     *
     * @return Nombre del curso en formato "Asignatura - Periodo - Sección"
     */
    public String getNombreCurso() {
        StringBuilder sb = new StringBuilder();
        if (asignatura != null) {
            sb.append(asignatura.getNombre());
        }
        if (periodo != null) {
            sb.append(" - ").append(periodo.getNombre());
        }
        if (seccion != null) {
            sb.append(" - Sec ").append(seccion);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(cursoId, curso.cursoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursoId);
    }

    @Override
    public String toString() {
        return getNombreCurso();
    }

    public String toDetailedString() {
        return "Curso{" +
                "cursoId=" + cursoId +
                ", asignaturaId=" + asignaturaId +
                ", periodoId=" + periodoId +
                ", seccion='" + seccion + '\'' +
                ", cupoMax=" + cupoMax +
                '}';
    }
}
