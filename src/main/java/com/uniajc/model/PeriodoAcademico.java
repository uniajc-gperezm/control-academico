package com.uniajc.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa un período académico (semestre).
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class PeriodoAcademico {

    public enum EstadoPeriodo {
        ABIERTO, CERRADO, PROXIMO, CANCELADO
    }

    private Integer periodoId;
    private String nombre; // Ej: "2025-2"
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoPeriodo estado;

    public PeriodoAcademico() {
        this.estado = EstadoPeriodo.PROXIMO;
    }

    public PeriodoAcademico(Integer periodoId, String nombre, LocalDate fechaInicio,
                            LocalDate fechaFin, EstadoPeriodo estado) {
        this.periodoId = periodoId;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado != null ? estado : EstadoPeriodo.PROXIMO;
    }

    public PeriodoAcademico(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = EstadoPeriodo.PROXIMO;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoPeriodo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPeriodo estado) {
        this.estado = estado;
    }

    public boolean isActivo() {
        return estado == EstadoPeriodo.ABIERTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodoAcademico that = (PeriodoAcademico) o;
        return Objects.equals(periodoId, that.periodoId) &&
               Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodoId, nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
