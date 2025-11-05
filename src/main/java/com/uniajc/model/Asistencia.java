package com.uniajc.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa un registro de asistencia de un estudiante a una clase.
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Asistencia {

    public enum EstadoAsistencia {
        PRESENTE("Presente"),
        AUSENTE("Ausente"),
        TARDANZA("Tardanza"),
        JUSTIFICADO("Justificado");

        private final String descripcion;

        EstadoAsistencia(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private Integer asistenciaId;
    private Integer matriculaId; // FK a matriculas
    private Integer claseId; // FK a clases
    private EstadoAsistencia estado;
    private String observaciones;
    private String registradoPor; // Usuario ID que registró la asistencia
    private LocalDateTime fechaRegistro;

    // Relaciones (opcionales)
    private Matricula matricula;
    private Clase clase;

    public Asistencia() {
        this.estado = EstadoAsistencia.PRESENTE;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Asistencia(Integer asistenciaId, Integer matriculaId, Integer claseId,
                      EstadoAsistencia estado, String observaciones, String registradoPor,
                      LocalDateTime fechaRegistro) {
        this.asistenciaId = asistenciaId;
        this.matriculaId = matriculaId;
        this.claseId = claseId;
        this.estado = estado != null ? estado : EstadoAsistencia.PRESENTE;
        this.observaciones = observaciones;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro != null ? fechaRegistro : LocalDateTime.now();
    }

    public Asistencia(Integer matriculaId, Integer claseId, EstadoAsistencia estado, String registradoPor) {
        this.matriculaId = matriculaId;
        this.claseId = claseId;
        this.estado = estado;
        this.registradoPor = registradoPor;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Integer getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(Integer asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Integer getClaseId() {
        return claseId;
    }

    public void setClaseId(Integer claseId) {
        this.claseId = claseId;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencia estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(String registradoPor) {
        this.registradoPor = registradoPor;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
        if (matricula != null) {
            this.matriculaId = matricula.getMatriculaId();
        }
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
        if (clase != null) {
            this.claseId = clase.getClaseId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asistencia that = (Asistencia) o;
        return Objects.equals(asistenciaId, that.asistenciaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asistenciaId);
    }

    @Override
    public String toString() {
        return estado.getDescripcion();
    }

    public String toDetailedString() {
        return "Asistencia{" +
                "asistenciaId=" + asistenciaId +
                ", matriculaId=" + matriculaId +
                ", claseId=" + claseId +
                ", estado=" + estado +
                ", observaciones='" + observaciones + '\'' +
                ", registradoPor='" + registradoPor + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
