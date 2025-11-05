package com.uniajc.model.dto;

import java.math.BigDecimal;

/**
 * DTO para el reporte de notas de un curso.
 *
 * <p>Contiene las calificaciones de un estudiante en todos los cortes de un curso específico.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class NotasReporteDTO {

    // Información del estudiante
    private String estudianteId;
    private String codigoEstudiante;
    private String nombreCompleto;

    // Información del curso
    private Integer cursoId;
    private String nombreCurso;
    private String periodo;
    private String asignatura;

    // Calificaciones por corte
    private BigDecimal notaC1;
    private BigDecimal notaC2;
    private BigDecimal notaC3Notas;
    private BigDecimal notaC3Integrador;

    // Cálculo ponderado
    private BigDecimal porcentajeC1; // 30%
    private BigDecimal porcentajeC2; // 30%
    private BigDecimal porcentajeC3Notas; // 20%
    private BigDecimal porcentajeC3Integrador; // 20%

    // Nota final
    private BigDecimal notaFinal;
    private String estado; // "APROBADO" / "REPROBADO" / "EN CURSO"

    /**
     * Constructor vacío.
     */
    public NotasReporteDTO() {
    }

    /**
     * Constructor completo.
     */
    public NotasReporteDTO(String estudianteId, String codigoEstudiante, String nombreCompleto,
                           Integer cursoId, String nombreCurso, String periodo, String asignatura,
                           BigDecimal notaC1, BigDecimal notaC2, BigDecimal notaC3Notas,
                           BigDecimal notaC3Integrador, BigDecimal notaFinal, String estado) {
        this.estudianteId = estudianteId;
        this.codigoEstudiante = codigoEstudiante;
        this.nombreCompleto = nombreCompleto;
        this.cursoId = cursoId;
        this.nombreCurso = nombreCurso;
        this.periodo = periodo;
        this.asignatura = asignatura;
        this.notaC1 = notaC1;
        this.notaC2 = notaC2;
        this.notaC3Notas = notaC3Notas;
        this.notaC3Integrador = notaC3Integrador;
        this.notaFinal = notaFinal;
        this.estado = estado;

        // Calcular porcentajes
        calcularPorcentajes();
    }

    /**
     * Calcula los porcentajes ponderados de cada corte.
     */
    private void calcularPorcentajes() {
        if (notaC1 != null) {
            porcentajeC1 = notaC1.multiply(new BigDecimal("0.30"));
        }
        if (notaC2 != null) {
            porcentajeC2 = notaC2.multiply(new BigDecimal("0.30"));
        }
        if (notaC3Notas != null) {
            porcentajeC3Notas = notaC3Notas.multiply(new BigDecimal("0.20"));
        }
        if (notaC3Integrador != null) {
            porcentajeC3Integrador = notaC3Integrador.multiply(new BigDecimal("0.20"));
        }
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

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public BigDecimal getNotaC1() {
        return notaC1;
    }

    public void setNotaC1(BigDecimal notaC1) {
        this.notaC1 = notaC1;
        calcularPorcentajes();
    }

    public BigDecimal getNotaC2() {
        return notaC2;
    }

    public void setNotaC2(BigDecimal notaC2) {
        this.notaC2 = notaC2;
        calcularPorcentajes();
    }

    public BigDecimal getNotaC3Notas() {
        return notaC3Notas;
    }

    public void setNotaC3Notas(BigDecimal notaC3Notas) {
        this.notaC3Notas = notaC3Notas;
        calcularPorcentajes();
    }

    public BigDecimal getNotaC3Integrador() {
        return notaC3Integrador;
    }

    public void setNotaC3Integrador(BigDecimal notaC3Integrador) {
        this.notaC3Integrador = notaC3Integrador;
        calcularPorcentajes();
    }

    public BigDecimal getPorcentajeC1() {
        return porcentajeC1;
    }

    public BigDecimal getPorcentajeC2() {
        return porcentajeC2;
    }

    public BigDecimal getPorcentajeC3Notas() {
        return porcentajeC3Notas;
    }

    public BigDecimal getPorcentajeC3Integrador() {
        return porcentajeC3Integrador;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Verifica si la materia fue aprobada (nota final >= 3.0).
     *
     * @return true si notaFinal >= 3.0
     */
    public boolean isAprobado() {
        return notaFinal != null && notaFinal.compareTo(new BigDecimal("3.0")) >= 0;
    }

    @Override
    public String toString() {
        return "NotasReporteDTO{" +
                "codigoEstudiante='" + codigoEstudiante + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", asignatura='" + asignatura + '\'' +
                ", notaFinal=" + notaFinal +
                ", estado='" + estado + '\'' +
                '}';
    }
}
