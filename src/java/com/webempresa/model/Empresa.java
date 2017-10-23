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
public class Empresa {
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;

    public Empresa(){
        
    }
    
    public Empresa(String _nit, String _nombre, String _direccion, String _telefono){
        this.nit = _nit;
        this.nombre = _nombre;
        this.direccion = _direccion;
        this.telefono = _telefono;
    }

    public String getNit(){
        return this.nit;
    }

    public void setNit(String _nit){
        this.nit = _nit;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String _nombre){
        this.nombre = _nombre;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public void setDireccion(String _direccion){
        this.direccion = _direccion;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public void setTelefono(String _telefono){
        this.telefono = _telefono;
    }
}
