/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webempresa.dao;

import com.webempresa.model.Empresa;
import java.util.ArrayList;

/**
 *
 * @author CATASTOR
 */
public interface DAOEmpresa {
    
    public void insert(Empresa empresa) throws Exception;
    public void update(Empresa empresa) throws Exception;
    public void delete(Empresa empresa) throws Exception;
    public Empresa find(String query) throws Exception;
    public ArrayList<Empresa> findAll() throws Exception;
}
