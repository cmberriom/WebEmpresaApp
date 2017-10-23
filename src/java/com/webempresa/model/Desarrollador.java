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
public class Desarrollador extends Empleado{

	private String tarjetaProfesional;
	private String nivel;
	Proyecto proyecto;

        public Desarrollador(){

        }    
    
        public Desarrollador(long _cedula, String _companyNIT, boolean _activo, String _job, String _tarjetaProfesional, String _nivel) {
            super(_cedula, _companyNIT, _activo, _job);
            this.tarjetaProfesional = _tarjetaProfesional;
            this.nivel = _nivel;
        }
	
	public String getTarjetaProfesional(){
            return this.tarjetaProfesional;
	}
	
	public void setTarjetaProfesional(String _tarjetaProfesional){
            this.tarjetaProfesional = _tarjetaProfesional;
	}
	
	public String getNivel(){
            return this.nivel;
	}
	
	public void setNivel(String _nivel){
            this.nivel = _nivel;
	}

	public Proyecto getProyecto(){
            return this.proyecto;
	}
	
	public void setProyecto(Proyecto _proyecto){
            this.proyecto = _proyecto;
	}

	@Override
	public String trabajar() {
            this.setExperiencia(this.getExperiencia() + 0.2);
            String outMessage = "Desarrollador codificando software (Exp. + 0.2) ";
        return outMessage;		
	}

	@Override
	public String capacitacion() {
            this.setExperiencia(this.getExperiencia() + 0.3);
            String outMessage = "Desarrollador capacitándose en JAVA (Exp. + 0.3) ";
            outMessage+= super.capacitacion();
            return outMessage;
	}
	
	public String reunionDesarrollo() {
            String outMessage = "Desarrollador reunido con equipo de trabajo";
            return outMessage;
	}		
}
