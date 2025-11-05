package com.uniajc.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa una calificación de un estudiante en un componente de evaluación.
 *
 * <p>Las calificaciones son en escala de 0.0 a 5.0.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Calificacion {

    private Integer calificacionId;
    private Integer matriculaId; // FK a matriculas
    private Integer componenteId; // FK a componentes_evaluacion
    private BigDecimal nota; // Nota de 0.0 a 5.0
    private Boolean publicado; // Si la nota está visible para el estudiante
    private String registradoPor; // Usuario ID que registró la nota
    private LocalDateTime fechaRegistro;

    // Relaciones (opcionales)
    private Matricula matricula;
    private ComponenteEvaluacion componente;

    public Calificacion() {
        this.publicado = false;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Calificacion(Integer calificacionId, Integer matriculaId, Integer componenteId,
                        BigDecimal nota, Boolean publicado, String registradoPor, LocalDateTime fechaRegistro) {
        this.calificacionId = calificacionId;
        this.matriculaId = matriculaId;
        this.componenteId = componenteId;
        this.nota = nota;
        this.publicado = publicado != null ? publicado : false;
        this.registradoPor = registradoPor;
        this.fechaRegistro = fechaRegistro != null ? fechaRegistro : LocalDateTime.now();
    }

    public Calificacion(Integer matriculaId, Integer componenteId, BigDecimal nota, String registradoPor) {
        this.matriculaId = matriculaId;
        this.componenteId = componenteId;
        this.nota = nota;
        this.registradoPor = registradoPor;
        this.publicado = false;
        this.fechaRegistro = LocalDateTime.now();
    }

    public Integer getCalificacionId() {
        return calificacionId;
    }

    public void setCalificacionId(Integer calificacionId) {
        this.calificacionId = calificacionId;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Integer getComponenteId() {
        return componenteId;
    }

    public void setComponenteId(Integer componenteId) {
        this.componenteId = componenteId;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public Boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
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

    public ComponenteEvaluacion getComponente() {
        return componente;
    }

    public void setComponente(ComponenteEvaluacion componente) {
        this.componente = componente;
        if (componente != null) {
            this.componenteId = componente.getComponenteId();
        }
    }

    /**
     * Verifica si la nota es aprobatoria (>= 3.0).
     *
     * @return true si la nota es >= 3.0
     */
    public boolean isAprobado() {
        return nota != null && nota.compareTo(new BigDecimal("3.0")) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calificacion that = (Calificacion) o;
        return Objects.equals(calificacionId, that.calificacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calificacionId);
    }

    @Override
    public String toString() {
        return nota != null ? nota.toString() : "N/A";
    }

    public String toDetailedString() {
        return "Calificacion{" +
                "calificacionId=" + calificacionId +
                ", matriculaId=" + matriculaId +
                ", componenteId=" + componenteId +
                ", nota=" + nota +
                ", publicado=" + publicado +
                ", registradoPor='" + registradoPor + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
