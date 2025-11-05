package com.uniajc.model;

import java.util.Objects;

/**
 * Entidad que representa un prerrequisito entre asignaturas.
 *
 * <p>Define que una asignatura requiere que otra haya sido aprobada previamente.
 * Por ejemplo: "Programación II" requiere "Programación I".</p>
 *
 * @author Sistema de Gestión Académica - Universidad AJC
 * @version 1.0.0
 */
public class AsignaturaRequisito {

    private Integer requisitoId;
    private Integer asignaturaId; // La asignatura que tiene el requisito
    private Integer asignaturaRequisitoId; // La asignatura que es requisito

    // Relaciones (opcionales)
    private Asignatura asignatura;
    private Asignatura asignaturaRequisito;

    public AsignaturaRequisito() {
    }

    public AsignaturaRequisito(Integer requisitoId, Integer asignaturaId, Integer asignaturaRequisitoId) {
        this.requisitoId = requisitoId;
        this.asignaturaId = asignaturaId;
        this.asignaturaRequisitoId = asignaturaRequisitoId;
    }

    public AsignaturaRequisito(Integer asignaturaId, Integer asignaturaRequisitoId) {
        this.asignaturaId = asignaturaId;
        this.asignaturaRequisitoId = asignaturaRequisitoId;
    }

    public Integer getRequisitoId() {
        return requisitoId;
    }

    public void setRequisitoId(Integer requisitoId) {
        this.requisitoId = requisitoId;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaId) {
        this.asignaturaId = asignaturaId;
    }

    public Integer getAsignaturaRequisitoId() {
        return asignaturaRequisitoId;
    }

    public void setAsignaturaRequisitoId(Integer asignaturaRequisitoId) {
        this.asignaturaRequisitoId = asignaturaRequisitoId;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
        if (asignatura != null) {
            this.asignaturaId = asignatura.getAsignaturaId();
        }
    }

    public Asignatura getAsignaturaRequisito() {
        return asignaturaRequisito;
    }

    public void setAsignaturaRequisito(Asignatura asignaturaRequisito) {
        this.asignaturaRequisito = asignaturaRequisito;
        if (asignaturaRequisito != null) {
            this.asignaturaRequisitoId = asignaturaRequisito.getAsignaturaId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsignaturaRequisito that = (AsignaturaRequisito) o;
        return Objects.equals(requisitoId, that.requisitoId) &&
               Objects.equals(asignaturaId, that.asignaturaId) &&
               Objects.equals(asignaturaRequisitoId, that.asignaturaRequisitoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisitoId, asignaturaId, asignaturaRequisitoId);
    }

    @Override
    public String toString() {
        String asigStr = asignatura != null ? asignatura.getCodigo() : String.valueOf(asignaturaId);
        String reqStr = asignaturaRequisito != null ? asignaturaRequisito.getCodigo() : String.valueOf(asignaturaRequisitoId);
        return asigStr + " requiere " + reqStr;
    }

    public String toDetailedString() {
        return "AsignaturaRequisito{" +
                "requisitoId=" + requisitoId +
                ", asignaturaId=" + asignaturaId +
                ", asignaturaRequisitoId=" + asignaturaRequisitoId +
                '}';
    }
}
