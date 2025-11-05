package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un aula o salón de clases.
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Aula {

    private Integer aulaId;
    private String codigo; // Ej: "A-101", "B-205"
    private Integer capacidad;
    private String ubicacion;
    private Boolean activa;

    public Aula() {
        this.activa = true;
    }

    public Aula(Integer aulaId, String codigo, Integer capacidad, String ubicacion, Boolean activa) {
        this.aulaId = aulaId;
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.activa = activa != null ? activa : true;
    }

    public Aula(String codigo, Integer capacidad, String ubicacion) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.activa = true;
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
        this.aulaId = aulaId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public Boolean isActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(aulaId, aula.aulaId) &&
               Objects.equals(codigo, aula.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aulaId, codigo);
    }

    @Override
    public String toString() {
        return codigo + (ubicacion != null ? " (" + ubicacion + ")" : "");
    }

    public String toDetailedString() {
        return "Aula{" +
                "aulaId=" + aulaId +
                ", codigo='" + codigo + '\'' +
                ", capacidad=" + capacidad +
                ", ubicacion='" + ubicacion + '\'' +
                ", activa=" + activa +
                '}';
    }
}
