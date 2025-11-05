package com.uniajc.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa un estudiante en el sistema académico.
 *
 * <p>Un estudiante está vinculado a una persona (relación 1:1) y contiene
 * información específica del perfil estudiantil.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Estudiante {

    private String estudianteId; // UUID
    private String personaId; // FK a personas (UUID)
    private String codigo; // Código único del estudiante (ej: 2025001)
    private LocalDate fechaIngreso;
    private Boolean activo;

    // Relación (opcional)
    private Persona persona;

    /**
     * Constructor vacío.
     */
    public Estudiante() {
        this.activo = true;
        this.fechaIngreso = LocalDate.now();
    }

    /**
     * Constructor completo.
     */
    public Estudiante(String estudianteId, String personaId, String codigo,
                      LocalDate fechaIngreso, Boolean activo) {
        this.estudianteId = estudianteId;
        this.personaId = personaId;
        this.codigo = codigo;
        this.fechaIngreso = fechaIngreso != null ? fechaIngreso : LocalDate.now();
        this.activo = activo != null ? activo : true;
    }

    /**
     * Constructor sin ID.
     */
    public Estudiante(String personaId, String codigo, LocalDate fechaIngreso) {
        this.personaId = personaId;
        this.codigo = codigo;
        this.fechaIngreso = fechaIngreso != null ? fechaIngreso : LocalDate.now();
        this.activo = true;
    }

    // Getters y Setters

    public String getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
        if (persona != null) {
            this.personaId = persona.getPersonaId();
        }
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(estudianteId, that.estudianteId) &&
               Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudianteId, codigo);
    }

    @Override
    public String toString() {
        if (persona != null) {
            return codigo + " - " + persona.getNombreCompleto();
        }
        return codigo;
    }

    public String toDetailedString() {
        return "Estudiante{" +
                "estudianteId='" + estudianteId + '\'' +
                ", personaId='" + personaId + '\'' +
                ", codigo='" + codigo + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", activo=" + activo +
                ", persona=" + (persona != null ? persona.getNombreCompleto() : "N/A") +
                '}';
    }
}
