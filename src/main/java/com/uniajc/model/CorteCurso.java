package com.uniajc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidad que representa un corte de evaluación para un curso.
 *
 * <p>El sistema usa 4 cortes:</p>
 * <ul>
 *   <li>C1: 30%</li>
 *   <li>C2: 30%</li>
 *   <li>C3_NOTAS: 20%</li>
 *   <li>C3_INTEGRADOR: 20%</li>
 * </ul>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class CorteCurso {

    private Integer corteId;
    private Integer cursoId; // FK a cursos
    private String codigo; // "C1", "C2", "C3_NOTAS", "C3_INTEGRADOR"
    private BigDecimal porcentaje; // % del corte (30, 30, 20, 20)

    // Relación (opcional)
    private Curso curso;

    public CorteCurso() {
    }

    public CorteCurso(Integer corteId, Integer cursoId, String codigo, BigDecimal porcentaje) {
        this.corteId = corteId;
        this.cursoId = cursoId;
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    public CorteCurso(Integer cursoId, String codigo, BigDecimal porcentaje) {
        this.cursoId = cursoId;
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    public Integer getCorteId() {
        return corteId;
    }

    public void setCorteId(Integer corteId) {
        this.corteId = corteId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
        if (curso != null) {
            this.cursoId = curso.getCursoId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CorteCurso that = (CorteCurso) o;
        return Objects.equals(corteId, that.corteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corteId);
    }

    @Override
    public String toString() {
        return codigo + " (" + porcentaje + "%)";
    }

    public String toDetailedString() {
        return "CorteCurso{" +
                "corteId=" + corteId +
                ", cursoId=" + cursoId +
                ", codigo='" + codigo + '\'' +
                ", porcentaje=" + porcentaje +
                '}';
    }
}
