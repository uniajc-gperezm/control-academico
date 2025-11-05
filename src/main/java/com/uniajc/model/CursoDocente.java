package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa la asignación de un docente a un curso.
 *
 * <p>Un curso puede tener múltiples docentes asignados con diferentes roles
 * (propietario, co-docente, monitor).</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class CursoDocente {

    public enum RolDocente {
        OWNER("Propietario"),
        CO("Co-docente"),
        MONITOR("Monitor");

        private final String descripcion;

        RolDocente(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private Integer cursoDocenteId;
    private Integer cursoId; // FK a cursos
    private String docenteId; // FK a docentes (UUID)
    private RolDocente rol;

    // Relaciones (opcionales)
    private Curso curso;
    private Docente docente;

    public CursoDocente() {
        this.rol = RolDocente.OWNER;
    }

    public CursoDocente(Integer cursoDocenteId, Integer cursoId, String docenteId, RolDocente rol) {
        this.cursoDocenteId = cursoDocenteId;
        this.cursoId = cursoId;
        this.docenteId = docenteId;
        this.rol = rol != null ? rol : RolDocente.OWNER;
    }

    public CursoDocente(Integer cursoId, String docenteId, RolDocente rol) {
        this.cursoId = cursoId;
        this.docenteId = docenteId;
        this.rol = rol;
    }

    public Integer getCursoDocenteId() {
        return cursoDocenteId;
    }

    public void setCursoDocenteId(Integer cursoDocenteId) {
        this.cursoDocenteId = cursoDocenteId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(String docenteId) {
        this.docenteId = docenteId;
    }

    public RolDocente getRol() {
        return rol;
    }

    public void setRol(RolDocente rol) {
        this.rol = rol;
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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
        if (docente != null) {
            this.docenteId = docente.getDocenteId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoDocente that = (CursoDocente) o;
        return Objects.equals(cursoDocenteId, that.cursoDocenteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursoDocenteId);
    }

    @Override
    public String toString() {
        String cursoStr = curso != null ? curso.getNombreCurso() : String.valueOf(cursoId);
        String docenteStr = docente != null && docente.getPersona() != null ?
                            docente.getPersona().getNombreCompleto() : docenteId;
        return cursoStr + " - " + docenteStr + " (" + rol.getDescripcion() + ")";
    }

    public String toDetailedString() {
        return "CursoDocente{" +
                "cursoDocenteId=" + cursoDocenteId +
                ", cursoId=" + cursoId +
                ", docenteId='" + docenteId + '\'' +
                ", rol=" + rol +
                '}';
    }
}
