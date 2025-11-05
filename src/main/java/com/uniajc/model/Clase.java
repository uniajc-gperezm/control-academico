package com.uniajc.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa una sesión de clase específica.
 *
 * <p>Cada curso tiene múltiples clases (sesiones) a lo largo del período académico.</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class Clase {

    private Integer claseId;
    private Integer cursoId; // FK a cursos
    private Integer aulaId; // FK a aulas
    private Integer numero; // Número de clase (1, 2, 3, ...)
    private LocalDate fecha;
    private String tema; // Tema tratado en la clase

    // Relaciones (opcionales)
    private Curso curso;
    private Aula aula;

    public Clase() {
    }

    public Clase(Integer claseId, Integer cursoId, Integer aulaId, Integer numero,
                 LocalDate fecha, String tema) {
        this.claseId = claseId;
        this.cursoId = cursoId;
        this.aulaId = aulaId;
        this.numero = numero;
        this.fecha = fecha;
        this.tema = tema;
    }

    public Clase(Integer cursoId, Integer aulaId, Integer numero, LocalDate fecha, String tema) {
        this.cursoId = cursoId;
        this.aulaId = aulaId;
        this.numero = numero;
        this.fecha = fecha;
        this.tema = tema;
    }

    public Integer getClaseId() {
        return claseId;
    }

    public void setClaseId(Integer claseId) {
        this.claseId = claseId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
        this.aulaId = aulaId;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
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

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
        if (aula != null) {
            this.aulaId = aula.getAulaId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clase clase = (Clase) o;
        return Objects.equals(claseId, clase.claseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claseId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Clase #").append(numero);
        if (fecha != null) {
            sb.append(" - ").append(fecha);
        }
        if (tema != null) {
            sb.append(" - ").append(tema);
        }
        return sb.toString();
    }

    public String toDetailedString() {
        return "Clase{" +
                "claseId=" + claseId +
                ", cursoId=" + cursoId +
                ", aulaId=" + aulaId +
                ", numero=" + numero +
                ", fecha=" + fecha +
                ", tema='" + tema + '\'' +
                '}';
    }
}
