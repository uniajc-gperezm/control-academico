package com.uniajc.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa la relación muchos-a-muchos entre usuarios y roles.
 *
 * <p>Un usuario puede tener múltiples roles asignados, y un rol puede ser
 * asignado a múltiples usuarios. Esta tabla de unión permite esa flexibilidad.</p>
 *
 * <p><b>Ejemplo:</b> Un docente puede tener los roles "docente" y "administrativo"
 * si también realiza tareas administrativas.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class UsuarioRol {

    private Integer usuarioRolId;
    private String usuarioId; // FK a usuarios (UUID)
    private Integer rolId; // FK a roles
    private LocalDateTime fechaAsignacion;

    // Relaciones (opcionales)
    private Usuario usuario;
    private Rol rol;

    /**
     * Constructor vacío.
     */
    public UsuarioRol() {
        this.fechaAsignacion = LocalDateTime.now();
    }

    /**
     * Constructor completo.
     */
    public UsuarioRol(Integer usuarioRolId, String usuarioId, Integer rolId, LocalDateTime fechaAsignacion) {
        this.usuarioRolId = usuarioRolId;
        this.usuarioId = usuarioId;
        this.rolId = rolId;
        this.fechaAsignacion = fechaAsignacion != null ? fechaAsignacion : LocalDateTime.now();
    }

    /**
     * Constructor sin ID (para inserciones).
     */
    public UsuarioRol(String usuarioId, Integer rolId) {
        this.usuarioId = usuarioId;
        this.rolId = rolId;
        this.fechaAsignacion = LocalDateTime.now();
    }

    // Getters y Setters

    public Integer getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(Integer usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) {
            this.usuarioId = usuario.getUsuarioId();
        }
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
        if (rol != null) {
            this.rolId = rol.getRolId();
        }
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioRol that = (UsuarioRol) o;
        return Objects.equals(usuarioRolId, that.usuarioRolId) &&
               Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(rolId, that.rolId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioRolId, usuarioId, rolId);
    }

    @Override
    public String toString() {
        String usuarioStr = usuario != null ? usuario.getUsername() : usuarioId;
        String rolStr = rol != null ? rol.getNombre() : String.valueOf(rolId);
        return usuarioStr + " - " + rolStr;
    }

    public String toDetailedString() {
        return "UsuarioRol{" +
                "usuarioRolId=" + usuarioRolId +
                ", usuarioId='" + usuarioId + '\'' +
                ", rolId=" + rolId +
                ", fechaAsignacion=" + fechaAsignacion +
                ", usuario=" + (usuario != null ? usuario.getUsername() : "N/A") +
                ", rol=" + (rol != null ? rol.getNombre() : "N/A") +
                '}';
    }
}
