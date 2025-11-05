package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un país en el catálogo geográfico.
 *
 * <p>Los países son el nivel superior de la jerarquía geográfica:
 * País → Departamento → Ciudad</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Pais {

    private Integer paisId;
    private String nombre;

    /**
     * Constructor vacío.
     */
    public Pais() {
    }

    /**
     * Constructor completo.
     *
     * @param paisId ID único del país
     * @param nombre Nombre del país
     */
    public Pais(Integer paisId, String nombre) {
        this.paisId = paisId;
        this.nombre = nombre;
    }

    /**
     * Constructor sin ID.
     *
     * @param nombre Nombre del país
     */
    public Pais(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return Objects.equals(paisId, pais.paisId) &&
               Objects.equals(nombre, pais.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paisId, nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toDetailedString() {
        return "Pais{" +
                "paisId=" + paisId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
