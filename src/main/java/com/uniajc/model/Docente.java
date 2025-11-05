package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un docente en el sistema académico.
 *
 * <p>Un docente está vinculado a una persona (relación 1:1) y contiene
 * información específica del perfil docente.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Docente {

    private String docenteId; // UUID
    private String personaId; // FK a personas (UUID)
    private String titulo; // Título académico (ej: "Ingeniero de Sistemas", "Magíster en Educación")
    private String categoria; // Categoría docente (ej: "Titular", "Asistente", "Catedrático")

    // Relación (opcional)
    private Persona persona;

    /**
     * Constructor vacío.
     */
    public Docente() {
    }

    /**
     * Constructor completo.
     */
    public Docente(String docenteId, String personaId, String titulo, String categoria) {
        this.docenteId = docenteId;
        this.personaId = personaId;
        this.titulo = titulo;
        this.categoria = categoria;
    }

    /**
     * Constructor sin ID.
     */
    public Docente(String personaId, String titulo, String categoria) {
        this.personaId = personaId;
        this.titulo = titulo;
        this.categoria = categoria;
    }

    // Getters y Setters

    public String getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(String docenteId) {
        this.docenteId = docenteId;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
        Docente docente = (Docente) o;
        return Objects.equals(docenteId, docente.docenteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docenteId);
    }

    @Override
    public String toString() {
        if (persona != null) {
            return persona.getNombreCompleto() + " - " + titulo;
        }
        return titulo;
    }

    public String toDetailedString() {
        return "Docente{" +
                "docenteId='" + docenteId + '\'' +
                ", personaId='" + personaId + '\'' +
                ", titulo='" + titulo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", persona=" + (persona != null ? persona.getNombreCompleto() : "N/A") +
                '}';
    }
}
