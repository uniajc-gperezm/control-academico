package com.uniajc.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa la matrícula de un estudiante en un curso.
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Matricula {

    public enum EstadoMatricula {
        INSCRITO("Inscrito"),
        RETIRADO("Retirado"),
        APROBADO("Aprobado"),
        REPROBADO("Reprobado"),
        CONVALIDADO("Convalidado");

        private final String descripcion;

        EstadoMatricula(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private Integer matriculaId;
    private String estudianteId; // FK a estudiantes (UUID)
    private Integer cursoId; // FK a cursos
    private LocalDate fechaInscripcion;
    private LocalDate fechaRetiro;
    private EstadoMatricula estado;

    // Relaciones (opcionales)
    private Estudiante estudiante;
    private Curso curso;

    public Matricula() {
        this.estado = EstadoMatricula.INSCRITO;
        this.fechaInscripcion = LocalDate.now();
    }

    public Matricula(Integer matriculaId, String estudianteId, Integer cursoId,
                     LocalDate fechaInscripcion, LocalDate fechaRetiro, EstadoMatricula estado) {
        this.matriculaId = matriculaId;
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.fechaInscripcion = fechaInscripcion != null ? fechaInscripcion : LocalDate.now();
        this.fechaRetiro = fechaRetiro;
        this.estado = estado != null ? estado : EstadoMatricula.INSCRITO;
    }

    public Matricula(String estudianteId, Integer cursoId) {
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.fechaInscripcion = LocalDate.now();
        this.estado = EstadoMatricula.INSCRITO;
    }

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public String getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public LocalDate getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(LocalDate fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public EstadoMatricula getEstado() {
        return estado;
    }

    public void setEstado(EstadoMatricula estado) {
        this.estado = estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        if (estudiante != null) {
            this.estudianteId = estudiante.getEstudianteId();
        }
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

    public boolean isActiva() {
        return estado == EstadoMatricula.INSCRITO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(matriculaId, matricula.matriculaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matriculaId);
    }

    @Override
    public String toString() {
        String estudianteStr = estudiante != null && estudiante.getPersona() != null ?
                               estudiante.getPersona().getNombreCompleto() : estudianteId;
        String cursoStr = curso != null ? curso.getNombreCurso() : String.valueOf(cursoId);
        return estudianteStr + " - " + cursoStr + " [" + estado.getDescripcion() + "]";
    }

    public String toDetailedString() {
        return "Matricula{" +
                "matriculaId=" + matriculaId +
                ", estudianteId='" + estudianteId + '\'' +
                ", cursoId=" + cursoId +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaRetiro=" + fechaRetiro +
                ", estado=" + estado +
                '}';
    }
}
