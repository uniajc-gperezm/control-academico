package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un rol de usuario en el sistema académico.
 *
 * <p>Los roles definen los permisos y accesos que tienen los usuarios.
 * Un usuario puede tener múltiples roles asignados.</p>
 *
 * <p><b>Roles disponibles:</b></p>
 * <ul>
 *   <li><b>estudiante</b> - Acceso a portal estudiantil (ver notas, horarios)</li>
 *   <li><b>docente</b> - Gestión de cursos, calificaciones y asistencia</li>
 *   <li><b>administrativo</b> - Gestión de estudiantes, matrículas y reportes</li>
 *   <li><b>superadmin</b> - Acceso completo al sistema</li>
 * </ul>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Rol {

    private Integer rolId;
    private String nombre;
    private String descripcion;

    /**
     * Constructor vacío requerido por frameworks ORM y serialización.
     */
    public Rol() {
    }

    /**
     * Constructor completo para crear un rol.
     *
     * @param rolId ID único del rol
     * @param nombre Nombre del rol (ej: estudiante, docente, administrativo, superadmin)
     * @param descripcion Descripción del rol y sus permisos
     */
    public Rol(Integer rolId, String nombre, String descripcion) {
        this.rolId = rolId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Constructor sin ID (útil para inserciones donde el ID es auto-generado).
     *
     * @param nombre Nombre del rol
     * @param descripcion Descripción del rol
     */
    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(rolId, rol.rolId) &&
               Objects.equals(nombre, rol.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolId, nombre);
    }

    @Override
    public String toString() {
        return nombre; // Para uso en ComboBox y visualización simple
    }

    /**
     * Representación detallada del rol para debugging.
     *
     * @return String con todos los campos del rol
     */
    public String toDetailedString() {
        return "Rol{" +
                "rolId=" + rolId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
