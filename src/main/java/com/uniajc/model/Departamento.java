package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un departamento (estado/provincia) en el catálogo geográfico.
 *
 * <p>Los departamentos pertenecen a un país y contienen ciudades.
 * Jerarquía: País → <b>Departamento</b> → Ciudad</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Departamento {

    private Integer dptoId;
    private Integer paisId;
    private String nombre;

    // Relación (opcional para carga lazy/eager)
    private Pais pais;

    /**
     * Constructor vacío.
     */
    public Departamento() {
    }

    /**
     * Constructor completo.
     *
     * @param dptoId ID único del departamento
     * @param paisId ID del país al que pertenece
     * @param nombre Nombre del departamento
     */
    public Departamento(Integer dptoId, Integer paisId, String nombre) {
        this.dptoId = dptoId;
        this.paisId = paisId;
        this.nombre = nombre;
    }

    /**
     * Constructor sin ID.
     *
     * @param paisId ID del país
     * @param nombre Nombre del departamento
     */
    public Departamento(Integer paisId, String nombre) {
        this.paisId = paisId;
        this.nombre = nombre;
    }

    // Getters y Setters

    public Integer getDptoId() {
        return dptoId;
    }

    public void setDptoId(Integer dptoId) {
        this.dptoId = dptoId;
    }

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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        if (pais != null) {
            this.paisId = pais.getPaisId();
        }
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(dptoId, that.dptoId) &&
               Objects.equals(paisId, that.paisId) &&
               Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dptoId, paisId, nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toDetailedString() {
        return "Departamento{" +
                "dptoId=" + dptoId +
                ", paisId=" + paisId +
                ", nombre='" + nombre + '\'' +
                ", pais=" + (pais != null ? pais.getNombre() : "N/A") +
                '}';
    }
}
