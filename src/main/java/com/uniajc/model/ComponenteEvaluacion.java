package com.uniajc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa un componente de evaluación dentro de un corte.
 *
 * <p>Ejemplo: Dentro del corte C1 (30%) puede haber componentes como:
 * Parcial (40%), Talleres (30%), Quiz (30%)</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class ComponenteEvaluacion {

    private Integer componenteId;
    private Integer corteId; // FK a cortes_curso
    private String nombre; // Ej: "Parcial", "Taller 1", "Quiz"
    private BigDecimal porcentaje; // % del componente dentro del corte
    private String descripcion;
    private LocalDate fechaPublicacion;

    // Relación (opcional)
    private CorteCurso corte;

    public ComponenteEvaluacion() {
    }

    public ComponenteEvaluacion(Integer componenteId, Integer corteId, String nombre,
                                BigDecimal porcentaje, String descripcion, LocalDate fechaPublicacion) {
        this.componenteId = componenteId;
        this.corteId = corteId;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public ComponenteEvaluacion(Integer corteId, String nombre, BigDecimal porcentaje, String descripcion) {
        this.corteId = corteId;
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
    }

    public Integer getComponenteId() {
        return componenteId;
    }

    public void setComponenteId(Integer componenteId) {
        this.componenteId = componenteId;
    }

    public Integer getCorteId() {
        return corteId;
    }

    public void setCorteId(Integer corteId) {
        this.corteId = corteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public CorteCurso getCorte() {
        return corte;
    }

    public void setCorte(CorteCurso corte) {
        this.corte = corte;
        if (corte != null) {
            this.corteId = corte.getCorteId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponenteEvaluacion that = (ComponenteEvaluacion) o;
        return Objects.equals(componenteId, that.componenteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componenteId);
    }

    @Override
    public String toString() {
        return nombre + " (" + porcentaje + "%)";
    }

    public String toDetailedString() {
        return "ComponenteEvaluacion{" +
                "componenteId=" + componenteId +
                ", corteId=" + corteId +
                ", nombre='" + nombre + '\'' +
                ", porcentaje=" + porcentaje +
                ", descripcion='" + descripcion + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
