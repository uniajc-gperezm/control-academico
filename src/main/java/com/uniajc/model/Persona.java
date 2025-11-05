package com.uniajc.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa una persona en el sistema académico.
 *
 * <p>Esta es la tabla maestra que contiene la información personal básica.
 * Las personas pueden tener diferentes roles: estudiantes, docentes, administrativos, etc.</p>
 *
 * <p><b>Relaciones:</b></p>
 * <ul>
 *   <li>Una persona puede ser un estudiante (1:1 con Estudiante)</li>
 *   <li>Una persona puede ser un docente (1:1 con Docente)</li>
 *   <li>Una persona puede tener una cuenta de usuario (1:1 con Usuario)</li>
 *   <li>Una persona pertenece a una ciudad</li>
 * </ul>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Persona {

    /**
     * Tipos de documento de identificación soportados.
     */
    public enum TipoDocumento {
        CC("Cédula de Ciudadanía"),
        CE("Cédula de Extranjería"),
        TI("Tarjeta de Identidad"),
        PP("Pasaporte"),
        NIT("NIT"),
        OTRO("Otro");

        private final String descripcion;

        TipoDocumento(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    /**
     * Géneros soportados en el sistema.
     */
    public enum Genero {
        MASCULINO("Masculino"),
        FEMENINO("Femenino"),
        OTRO("Otro"),
        NO_DECLARA("Prefiero no declarar");

        private final String descripcion;

        Genero(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private String personaId; // UUID
    private TipoDocumento documentoTipo;
    private String documentoNumero;
    private String nombres;
    private String apellidos;
    private Genero genero;
    private String email;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private Integer ciudadId;

    // Relación (opcional)
    private Ciudad ciudad;

    /**
     * Constructor vacío.
     */
    public Persona() {
        this.genero = Genero.NO_DECLARA;
    }

    /**
     * Constructor completo.
     */
    public Persona(String personaId, TipoDocumento documentoTipo, String documentoNumero,
                   String nombres, String apellidos, Genero genero, String email,
                   String telefono, String direccion, LocalDate fechaNacimiento, Integer ciudadId) {
        this.personaId = personaId;
        this.documentoTipo = documentoTipo;
        this.documentoNumero = documentoNumero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero != null ? genero : Genero.NO_DECLARA;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudadId = ciudadId;
    }

    /**
     * Constructor sin ID (para inserciones - el UUID se genera en BD).
     */
    public Persona(TipoDocumento documentoTipo, String documentoNumero, String nombres,
                   String apellidos, Genero genero, String email, String telefono,
                   String direccion, LocalDate fechaNacimiento, Integer ciudadId) {
        this.documentoTipo = documentoTipo;
        this.documentoNumero = documentoNumero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero != null ? genero : Genero.NO_DECLARA;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudadId = ciudadId;
    }

    // Getters y Setters

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public TipoDocumento getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(TipoDocumento documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el nombre completo (nombres + apellidos).
     *
     * @return Nombre completo de la persona
     */
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        if (ciudad != null) {
            this.ciudadId = ciudad.getCiudadId();
        }
    }

    /**
     * Calcula la edad de la persona basada en su fecha de nacimiento.
     *
     * @return Edad en años, o null si no hay fecha de nacimiento
     */
    public Integer getEdad() {
        if (fechaNacimiento == null) {
            return null;
        }
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(personaId, persona.personaId) &&
               Objects.equals(documentoNumero, persona.documentoNumero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personaId, documentoNumero);
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }

    public String toDetailedString() {
        return "Persona{" +
                "personaId='" + personaId + '\'' +
                ", documentoTipo=" + documentoTipo +
                ", documentoNumero='" + documentoNumero + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", genero=" + genero +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + getEdad() +
                ", ciudadId=" + ciudadId +
                '}';
    }
}
