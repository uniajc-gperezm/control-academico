package com.uniajc.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para el Kardex estudiantil (historial académico).
 *
 * <p>Contiene toda la información necesaria para generar el informe de historial académico
 * de un estudiante, incluyendo todas las matr ículas y calificaciones.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class KardexDTO {

    // Información del estudiante
    private String estudianteId;
    private String codigoEstudiante;
    private String nombreCompleto;
    private String documento;
    private String programa;

    // Información del curso/matrícula
    private String periodo;
    private String asignatura;
    private String codigoAsignatura;
    private Integer creditos;
    private LocalDate fechaMatricula;

    // Calificaciones
    private BigDecimal notaC1;
    private BigDecimal notaC2;
    private BigDecimal notaC3Notas;
    private BigDecimal notaC3Integrador;
    private BigDecimal notaFinal;
    private String estadoMatricula; // INSCRITO, APROBADO, REPROBADO, etc.

    // Estadísticas
    private BigDecimal porcentajeAsistencia;

    /**
     * Constructor vacío.
     */
    public KardexDTO() {
    }

    /**
     * Constructor completo.
     */
    public KardexDTO(String estudianteId, String codigoEstudiante, String nombreCompleto,
                     String documento, String programa, String periodo, String asignatura,
                     String codigoAsignatura, Integer creditos, LocalDate fechaMatricula,
                     BigDecimal notaC1, BigDecimal notaC2, BigDecimal notaC3Notas,
                     BigDecimal notaC3Integrador, BigDecimal notaFinal, String estadoMatricula,
                     BigDecimal porcentajeAsistencia) {
        this.estudianteId = estudianteId;
        this.codigoEstudiante = codigoEstudiante;
        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.programa = programa;
        this.periodo = periodo;
        this.asignatura = asignatura;
        this.codigoAsignatura = codigoAsignatura;
        this.creditos = creditos;
        this.fechaMatricula = fechaMatricula;
        this.notaC1 = notaC1;
        this.notaC2 = notaC2;
        this.notaC3Notas = notaC3Notas;
        this.notaC3Integrador = notaC3Integrador;
        this.notaFinal = notaFinal;
        this.estadoMatricula = estadoMatricula;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
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

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public BigDecimal getNotaC1() {
        return notaC1;
    }

    public void setNotaC1(BigDecimal notaC1) {
        this.notaC1 = notaC1;
    }

    public BigDecimal getNotaC2() {
        return notaC2;
    }

    public void setNotaC2(BigDecimal notaC2) {
        this.notaC2 = notaC2;
    }

    public BigDecimal getNotaC3Notas() {
        return notaC3Notas;
    }

    public void setNotaC3Notas(BigDecimal notaC3Notas) {
        this.notaC3Notas = notaC3Notas;
    }

    public BigDecimal getNotaC3Integrador() {
        return notaC3Integrador;
    }

    public void setNotaC3Integrador(BigDecimal notaC3Integrador) {
        this.notaC3Integrador = notaC3Integrador;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(String estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public BigDecimal getPorcentajeAsistencia() {
        return porcentajeAsistencia;
    }

    public void setPorcentajeAsistencia(BigDecimal porcentajeAsistencia) {
        this.porcentajeAsistencia = porcentajeAsistencia;
    }

    @Override
    public String toString() {
        return "KardexDTO{" +
                "codigoEstudiante='" + codigoEstudiante + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", periodo='" + periodo + '\'' +
                ", asignatura='" + asignatura + '\'' +
                ", notaFinal=" + notaFinal +
                ", estadoMatricula='" + estadoMatricula + '\'' +
                '}';
    }
}
