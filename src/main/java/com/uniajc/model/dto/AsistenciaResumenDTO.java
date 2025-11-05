package com.uniajc.model.dto;

import java.math.BigDecimal;

/**
 * DTO para el resumen de asistencia de un estudiante en un curso.
 *
 * <p>Proporciona estadísticas agregadas de asistencia.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class AsistenciaResumenDTO {

    // Información del estudiante
    private String estudianteId;
    private String codigoEstudiante;
    private String nombreCompleto;

    // Información del curso
    private Integer cursoId;
    private String nombreCurso;
    private String periodo;

    // Estadísticas de asistencia
    private Integer totalClases;
    private Integer clasesPresente;
    private Integer clasesAusente;
    private Integer clasesTardanza;
    private Integer clasesJustificado;
    private BigDecimal porcentajeAsistencia;

    /**
     * Constructor vacío.
     */
    public AsistenciaResumenDTO() {
    }

    /**
     * Constructor completo.
     */
    public AsistenciaResumenDTO(String estudianteId, String codigoEstudiante, String nombreCompleto,
                                Integer cursoId, String nombreCurso, String periodo,
                                Integer totalClases, Integer clasesPresente, Integer clasesAusente,
                                Integer clasesTardanza, Integer clasesJustificado,
                                BigDecimal porcentajeAsistencia) {
        this.estudianteId = estudianteId;
        this.codigoEstudiante = codigoEstudiante;
        this.nombreCompleto = nombreCompleto;
        this.cursoId = cursoId;
        this.nombreCurso = nombreCurso;
        this.periodo = periodo;
        this.totalClases = totalClases;
        this.clasesPresente = clasesPresente;
        this.clasesAusente = clasesAusente;
        this.clasesTardanza = clasesTardanza;
        this.clasesJustificado = clasesJustificado;
        this.porcentajeAsistencia = porcentajeAsistencia;
    }

    // Getters y Setters

    public String getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getTotalClases() {
        return totalClases;
    }

    public void setTotalClases(Integer totalClases) {
        this.totalClases = totalClases;
    }

    public Integer getClasesPresente() {
        return clasesPresente;
    }

    public void setClasesPresente(Integer clasesPresente) {
        this.clasesPresente = clasesPresente;
    }

    public Integer getClasesAusente() {
        return clasesAusente;
    }

    public void setClasesAusente(Integer clasesAusente) {
        this.clasesAusente = clasesAusente;
    }

    public Integer getClasesTardanza() {
        return clasesTardanza;
    }

    public void setClasesTardanza(Integer clasesTardanza) {
        this.clasesTardanza = clasesTardanza;
    }

    public Integer getClasesJustificado() {
        return clasesJustificado;
    }

    public void setClasesJustificado(Integer clasesJustificado) {
        this.clasesJustificado = clasesJustificado;
    }

    public BigDecimal getPorcentajeAsistencia() {
        return porcentajeAsistencia;
    }

    public void setPorcentajeAsistencia(BigDecimal porcentajeAsistencia) {
        this.porcentajeAsistencia = porcentajeAsistencia;
    }

    /**
     * Verifica si el estudiante cumple con el porcentaje mínimo de asistencia (80%).
     *
     * @return true si asistencia >= 80%
     */
    public boolean cumpleAsistenciaMinima() {
        return porcentajeAsistencia != null &&
               porcentajeAsistencia.compareTo(new BigDecimal("80.0")) >= 0;
    }

    @Override
    public String toString() {
        return "AsistenciaResumenDTO{" +
                "codigoEstudiante='" + codigoEstudiante + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", porcentajeAsistencia=" + porcentajeAsistencia +
                '}';
    }
}
