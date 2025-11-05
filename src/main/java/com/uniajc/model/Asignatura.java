package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa una asignatura (materia/curso) en el plan de estudios.
 *
 * <p>Una asignatura puede tener prerrequisitos (otras asignaturas que deben ser
 * aprobadas antes de poder cursarla).</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Asignatura {

    private Integer asignaturaId;
    private Integer programaId; // FK a programas
    private String codigo; // Código único (ej: "BD101", "IS201")
    private String nombre;
    private Integer creditos;
    private Integer horasSemana;
    private Boolean activa;

    // Relación (opcional)
    private Programa programa;

    public Asignatura() {
        this.activa = true;
    }

    public Asignatura(Integer asignaturaId, Integer programaId, String codigo, String nombre,
                      Integer creditos, Integer horasSemana, Boolean activa) {
        this.asignaturaId = asignaturaId;
        this.programaId = programaId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemana = horasSemana;
        this.activa = activa != null ? activa : true;
    }

    public Asignatura(Integer programaId, String codigo, String nombre, Integer creditos, Integer horasSemana) {
        this.programaId = programaId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemana = horasSemana;
        this.activa = true;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Integer getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Integer programaId) {
        this.programaId = programaId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getHorasSemana() {
        return horasSemana;
    }

    public void setHorasSemana(Integer horasSemana) {
        this.horasSemana = horasSemana;
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

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
        if (programa != null) {
            this.programaId = programa.getProgramaId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return Objects.equals(asignaturaId, that.asignaturaId) &&
               Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignaturaId, codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    public String toDetailedString() {
        return "Asignatura{" +
                "asignaturaId=" + asignaturaId +
                ", programaId=" + programaId +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", horasSemana=" + horasSemana +
                ", activa=" + activa +
                '}';
    }
}
