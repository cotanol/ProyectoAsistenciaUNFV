/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

import Util.Enums.EstadoEquipo;

/**
 *
 * @author brigi
 */
public class Equipo {
    private int codPatrimonial;
    private int numeroLaboratorio;
    private String tipoEquipo;
    private String numeroSerie;
    private EstadoEquipo estado;

    public int getCodPatrimonial() {
        return codPatrimonial;
    }

    public void setCodPatrimonial(int codPatrimonial) {
        this.codPatrimonial = codPatrimonial;
    }

    public int getNumeroLab() {
        return numeroLaboratorio;
    }

    public void setNumeroLab(int numeroLaboratorio) {
        this.numeroLaboratorio = numeroLaboratorio;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public EstadoEquipo getEstado() {
        return estado;
    }

    public void setEstado(EstadoEquipo estado) {
        this.estado = estado;
    }
    
    
    
}
