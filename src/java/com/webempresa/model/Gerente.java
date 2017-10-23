/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webempresa.model;

/**
 *
 * @author CATASTOR
 */
public class Gerente extends Empleado{
    private String titulo;
	    
    public Gerente(){

    }    
        
    public Gerente(long _cedula, String _companyNIT, boolean _activo, String _job, String _titulo) {
        super(_cedula, _companyNIT, _activo, _job);
        this.titulo = _titulo;		
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setTitulo(String _titulo){
        this.titulo = _titulo;
    }

    @Override
    public String trabajar() {
        this.setExperiencia(this.getExperiencia() + 0.3);
        String outMessage = "Gerente trabajando en proyecto (Exp. + 0.3) ";
        return outMessage;		
    }

    @Override
    public String capacitacion() {
        this.setExperiencia(this.getExperiencia() + 0.1);
        String outMessage = "Gerente capacitándose en Gestión (Exp. + 0.1) ";
        outMessage+= super.capacitacion();
        return outMessage;
    }

    public String asignarTareas() {
        String outMessage = "Gerente asignando tareas a sus empleados";
        return outMessage;
    }
}
