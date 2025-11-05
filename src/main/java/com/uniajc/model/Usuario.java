package com.uniajc.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa una cuenta de usuario en el sistema.
 *
 * <p>Un usuario está vinculado a una persona y puede tener múltiples roles asignados.
 * Los usuarios se utilizan para autenticación y autorización en el sistema.</p>
 *
 * <p><b>Seguridad:</b> El campo passwordHash debe contener un hash BCrypt de la contraseña,
 * nunca la contraseña en texto plano.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Usuario {

    private String usuarioId; // UUID
    private String personaId; // FK a personas
    private String username;
    private String passwordHash; // BCrypt hash
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimoAcceso;

    // Relación (opcional)
    private Persona persona;

    /**
     * Constructor vacío.
     */
    public Usuario() {
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Constructor completo.
     */
    public Usuario(String usuarioId, String personaId, String username, String passwordHash,
                   Boolean activo, LocalDateTime fechaCreacion, LocalDateTime ultimoAcceso) {
        this.usuarioId = usuarioId;
        this.personaId = personaId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.activo = activo != null ? activo : true;
        this.fechaCreacion = fechaCreacion != null ? fechaCreacion : LocalDateTime.now();
        this.ultimoAcceso = ultimoAcceso;
    }

    /**
     * Constructor sin ID (para inserciones).
     */
    public Usuario(String personaId, String username, String passwordHash) {
        this.personaId = personaId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y Setters

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getActivo() {
        return activo;
    }

    /**
     * Alias para getActivo() con nombre más Java-friendly.
     */
    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
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

    /**
     * Registra el acceso actual del usuario.
     */
    public void registrarAcceso() {
        this.ultimoAcceso = LocalDateTime.now();
    }

    /**
     * Verifica si el usuario está activo.
     *
     * @return true si el usuario está activo, false en caso contrario
     */
    public boolean puedeAcceder() {
        return activo != null && activo;
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(usuarioId, usuario.usuarioId) &&
               Objects.equals(username, usuario.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, username);
    }

    @Override
    public String toString() {
        return username;
    }

    public String toDetailedString() {
        return "Usuario{" +
                "usuarioId='" + usuarioId + '\'' +
                ", personaId='" + personaId + '\'' +
                ", username='" + username + '\'' +
                ", activo=" + activo +
                ", fechaCreacion=" + fechaCreacion +
                ", ultimoAcceso=" + ultimoAcceso +
                ", persona=" + (persona != null ? persona.getNombreCompleto() : "N/A") +
                '}';
    }
}
