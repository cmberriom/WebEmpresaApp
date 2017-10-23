/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webempresa.dao;

import com.webempresa.model.Empleado;
import java.util.ArrayList;

/**
 *
 * @author CATASTOR
 */
public interface DAOEmpleado {
    
    public void insert(Empleado empleado) throws Exception;
    public void update(Empleado empleado) throws Exception;
    public void delete(Empleado empleado) throws Exception;
    public Empleado find(String query) throws Exception;
    public ArrayList<Empleado> findAll(String query) throws Exception;
}
