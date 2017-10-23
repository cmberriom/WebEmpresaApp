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
public abstract class Empleado implements IEmpleado{
    
    private long cedula;
    private String companyNIT;
    private String nombre;
    private String apellidos;
    private double salario;
    private double experiencia;
    private boolean activo;
    private String job;
    
    public Empleado(){
    
    }    

    public Empleado(long _cedula, String _companyNIT, boolean _activo, String _job){		
            this.cedula = _cedula;
            this.companyNIT = _companyNIT;
            this.activo = _activo;
            this.job = _job;
    }

    public long getCedula() {		
        return this.cedula;
    }
    public void setCedula(long _cedula) {		
        this.cedula = _cedula;
    }
    
    public String getCompanyNIT() {		
        return this.companyNIT;
    }

    public void setCompanyNIT(String _companyNIT) {		
        this.companyNIT = _companyNIT;
    }
    
    public String getNombre() {		
        return this.nombre;
    }

    public void setNombre(String _nombre) {		
        this.nombre = _nombre;
    }

    public String getApellidos() {		
        return this.apellidos;
    }
    public void setApellidos(String _apellidos) {		
        this.apellidos = _apellidos;
    }

    public double getSalario() {		
        return this.salario;
    }

    public void setSalario(double _salario) {		
        this.salario = _salario;
    }
        
    public double getExperiencia() {		
        return this.experiencia;
    }
    public void setExperiencia(double _experiencia) {		
        this.experiencia = _experiencia;
    }
    
    public boolean getActivo() {		
        return this.activo;
    }
    public void setActivo(boolean _activo) {		
        this.activo = _activo;
    }

    public String getJob() {		
        return this.job;
    }
    public void setJob(String _job) {		
        this.job = _job;
    }
    
    @Override
    public String ingresarTrabajo() {
        this.setActivo(true);
        String outMessage = "Empleado " + this.getCedula() + " ingresa a la oficina";
        return outMessage;
    }

    @Override
    public abstract String trabajar();

    @Override
    public String capacitacion() {
        this.setExperiencia(this.getExperiencia() + 0.1);
        String outMessage = "Empleado asiste a capacitación (Exp + 0.1%)";
        return outMessage;
    }

    @Override
    public String salirTrabajo() {
        this.setActivo(false);
        String outMessage = "Empleado " + this.getCedula() + " sale de la oficina";
        return outMessage;
    }    
}
