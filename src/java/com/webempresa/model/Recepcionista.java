/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webempresa.model;

import java.util.Date;

/**
 *
 * @author CATASTOR
 */
public class Recepcionista extends Empleado{

    private long aniosExperiencia;
    
    public Recepcionista(){

    }    
    public Recepcionista(long _cedula, String _companyNIT, boolean _activo, String _job, long _aniosExperiencia) {
        super(_cedula, _companyNIT, _activo, _job);
        this.aniosExperiencia = _aniosExperiencia;		
    }	

    public long getAniosExperiencia(){
        return this.aniosExperiencia;
    }

    public void setAniosExperiencia(long _aniosExperiencia){
        this.aniosExperiencia = _aniosExperiencia;
    }

    @Override
    public String trabajar() {
        this.setExperiencia(this.getExperiencia() + 0.1);
        String outMessage = "Recepcionista atendiendo clientes en la entrada (Exp. + 0.1) ";
        return outMessage;		
    }

    @Override
    public String capacitacion() {
        this.setExperiencia(this.getExperiencia() + 0.4);
        String outMessage = "Recepcionista capacitándose en inglés (Exp. + 0.4) ";
        outMessage+= super.capacitacion();
        return outMessage;
    }

    public String contestarTelefono() {
        String outMessage = "Recepcionista contesta el teléfono";
        return outMessage;
    }
}
