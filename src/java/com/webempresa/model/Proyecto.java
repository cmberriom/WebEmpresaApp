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
public class Proyecto {
    private int numeroProyecto;
	
    public Proyecto(int _numeroProyecto){
            this.numeroProyecto = _numeroProyecto;
    }

    public int getNumeroProyecto(){
            return this.numeroProyecto;
    }

    public void setNumeroProyecto(int _numeroProyecto){
            this.numeroProyecto = _numeroProyecto;
    }

    public void verEstadoProyecto(){
            System.out.println("Mostrando estado actual del proyecto " + this.getNumeroProyecto());	
    }
}
