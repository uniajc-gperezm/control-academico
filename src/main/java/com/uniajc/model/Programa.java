package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un programa académico (carrera).
 *
 * <p>Ejemplos: Ingeniería de Sistemas, Administración de Empresas, Derecho.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Programa {

    public enum NivelPrograma {
        PREGRADO("Pregrado"),
        POSTGRADO("Postgrado"),
        ESPECIALIZACION("Especialización"),
        MAESTRIA("Maestría"),
        DOCTORADO("Doctorado");

        private final String descripcion;

        NivelPrograma(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private Integer programaId;
    private String nombre;
    private NivelPrograma nivel;
    private Integer duracionSemestres;
    private Boolean activo;

    public Programa() {
        this.nivel = NivelPrograma.PREGRADO;
        this.activo = true;
    }

    public Programa(Integer programaId, String nombre, NivelPrograma nivel, Integer duracionSemestres, Boolean activo) {
        this.programaId = programaId;
        this.nombre = nombre;
        this.nivel = nivel != null ? nivel : NivelPrograma.PREGRADO;
        this.duracionSemestres = duracionSemestres;
        this.activo = activo != null ? activo : true;
    }

    public Programa(String nombre, NivelPrograma nivel, Integer duracionSemestres) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.duracionSemestres = duracionSemestres;
        this.activo = true;
    }

    public Integer getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Integer programaId) {
        this.programaId = programaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NivelPrograma getNivel() {
        return nivel;
    }

    public void setNivel(NivelPrograma nivel) {
        this.nivel = nivel;
    }

    public Integer getDuracionSemestres() {
        return duracionSemestres;
    }

    public void setDuracionSemestres(Integer duracionSemestres) {
        this.duracionSemestres = duracionSemestres;
    }

    public Boolean getActivo() {
        return activo;
    }

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programa programa = (Programa) o;
        return Objects.equals(programaId, programa.programaId) &&
               Objects.equals(nombre, programa.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programaId, nombre);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toDetailedString() {
        return "Programa{" +
                "programaId=" + programaId +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", duracionSemestres=" + duracionSemestres +
                ", activo=" + activo +
                '}';
    }
}
