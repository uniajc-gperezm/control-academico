package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un proyecto integrador que abarca múltiples asignaturas.
 *
 * <p>Los proyectos integradores permiten la evaluación transversal de competencias
 * en múltiples materias simultáneamente.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class ProyectoIntegrador {

    private Integer proyectoId;
    private Integer periodoId; // FK a periodos_academicos
    private String nombre;
    private String descripcion;
    private String creadoPor; // Usuario ID que creó el proyecto

    // Relación (opcional)
    private PeriodoAcademico periodo;

    public ProyectoIntegrador() {
    }

    public ProyectoIntegrador(Integer proyectoId, Integer periodoId, String nombre,
                              String descripcion, String creadoPor) {
        this.proyectoId = proyectoId;
        this.periodoId = periodoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadoPor = creadoPor;
    }

    public ProyectoIntegrador(Integer periodoId, String nombre, String descripcion, String creadoPor) {
        this.periodoId = periodoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadoPor = creadoPor;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Integer getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Integer periodoId) {
        this.periodoId = periodoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectoIntegrador that = (ProyectoIntegrador) o;
        return Objects.equals(proyectoId, that.proyectoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proyectoId);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toDetailedString() {
        return "ProyectoIntegrador{" +
                "proyectoId=" + proyectoId +
                ", periodoId=" + periodoId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", creadoPor='" + creadoPor + '\'' +
                '}';
    }
}
