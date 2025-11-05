package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa una ciudad en el catálogo geográfico.
 *
 * <p>Las ciudades pertenecen a un departamento.
 * Jerarquía: País → Departamento → <b>Ciudad</b></p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Ciudad {

    private Integer ciudadId;
    private Integer dptoId;
    private String nombre;

    // Relación (opcional para carga lazy/eager)
    private Departamento departamento;

    /**
     * Constructor vacío.
     */
    public Ciudad() {
    }

    /**
     * Constructor completo.
     *
     * @param ciudadId ID único de la ciudad
     * @param dptoId ID del departamento al que pertenece
     * @param nombre Nombre de la ciudad
     */
    public Ciudad(Integer ciudadId, Integer dptoId, String nombre) {
        this.ciudadId = ciudadId;
        this.dptoId = dptoId;
        this.nombre = nombre;
    }

    /**
     * Constructor sin ID.
     *
     * @param dptoId ID del departamento
     * @param nombre Nombre de la ciudad
     */
    public Ciudad(Integer dptoId, String nombre) {
        this.dptoId = dptoId;
        this.nombre = nombre;
    }

    // Getters y Setters

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Integer getDptoId() {
        return dptoId;
    }

    public void setDptoId(Integer dptoId) {
        this.dptoId = dptoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
        if (departamento != null) {
            this.dptoId = departamento.getDptoId();
        }
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(ciudadId, ciudad.ciudadId) &&
               Objects.equals(dptoId, ciudad.dptoId) &&
               Objects.equals(nombre, ciudad.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ciudadId, dptoId, nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toDetailedString() {
        return "Ciudad{" +
                "ciudadId=" + ciudadId +
                ", dptoId=" + dptoId +
                ", nombre='" + nombre + '\'' +
                ", departamento=" + (departamento != null ? departamento.getNombre() : "N/A") +
                '}';
    }
}
