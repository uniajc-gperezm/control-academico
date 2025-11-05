package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa la relación entre un proyecto integrador y una asignatura.
 *
 * <p>Un proyecto integrador puede involucrar múltiples asignaturas,
 * y una asignatura puede participar en múltiples proyectos.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class ProyectoAsignatura {

    private Integer proyectoAsignaturaId;
    private Integer proyectoId; // FK a proyectos_integradores
    private Integer asignaturaId; // FK a asignaturas

    // Relaciones (opcionales)
    private ProyectoIntegrador proyecto;
    private Asignatura asignatura;

    public ProyectoAsignatura() {
    }

    public ProyectoAsignatura(Integer proyectoAsignaturaId, Integer proyectoId, Integer asignaturaId) {
        this.proyectoAsignaturaId = proyectoAsignaturaId;
        this.proyectoId = proyectoId;
        this.asignaturaId = asignaturaId;
    }

    public ProyectoAsignatura(Integer proyectoId, Integer asignaturaId) {
        this.proyectoId = proyectoId;
        this.asignaturaId = asignaturaId;
    }

    public Integer getProyectoAsignaturaId() {
        return proyectoAsignaturaId;
    }

    public void setProyectoAsignaturaId(Integer proyectoAsignaturaId) {
        this.proyectoAsignaturaId = proyectoAsignaturaId;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public ProyectoIntegrador getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoIntegrador proyecto) {
        this.proyecto = proyecto;
        if (proyecto != null) {
            this.proyectoId = proyecto.getProyectoId();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectoAsignatura that = (ProyectoAsignatura) o;
        return Objects.equals(proyectoAsignaturaId, that.proyectoAsignaturaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proyectoAsignaturaId);
    }

    @Override
    public String toString() {
        String proyectoStr = proyecto != null ? proyecto.getNombre() : String.valueOf(proyectoId);
        String asignaturaStr = asignatura != null ? asignatura.getNombre() : String.valueOf(asignaturaId);
        return proyectoStr + " - " + asignaturaStr;
    }

    public String toDetailedString() {
        return "ProyectoAsignatura{" +
                "proyectoAsignaturaId=" + proyectoAsignaturaId +
                ", proyectoId=" + proyectoId +
                ", asignaturaId=" + asignaturaId +
                '}';
    }
}
