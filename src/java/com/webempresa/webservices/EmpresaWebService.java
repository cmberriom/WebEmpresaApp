/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webempresa.webservices;

import com.webempresa.dao.DAOEmpresaImpl;
import com.webempresa.model.Empresa;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author CATASTOR
 */
@WebService(serviceName = "EmpresaWebService")
public class EmpresaWebService {

    private final DAOEmpresaImpl daoEmpresa;    
    
    public EmpresaWebService() {
        super();
        daoEmpresa = new DAOEmpresaImpl();      
    }   
    
    @WebMethod(operationName = "createCompany")
    public void createCompany(@WebParam(name = "_empresa") Empresa _empresa) {
               
        try {        
            daoEmpresa.insert(_empresa);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @WebMethod(operationName = "updateCompany")
    public void updateCompany(@WebParam(name = "_empresa") Empresa _empresa){
        
        try {   
            Empresa empresa = this.findCompany(_empresa.getNit()); 
            empresa.setNombre(_empresa.getNombre());
            empresa.setDireccion(_empresa.getDireccion());
            empresa.setTelefono(_empresa.getTelefono());
            daoEmpresa.update(empresa);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpresaWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
     
    @WebMethod(operationName = "deleteCompany")
    public void deleteCompany(@WebParam(name = "_empresa") Empresa _empresa){
        
        try {   
            Empresa empresa = this.findCompany(_empresa.getNit());            
            daoEmpresa.delete(empresa);  
            
            //Eliminar empleados asociados
            EmpleadoWebService empleadoWebService = new EmpleadoWebService();
            empleadoWebService.deleteAllEmployees(_empresa.getNit());
            
        } catch (Exception ex) {
            Logger.getLogger(EmpresaWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    @WebMethod(operationName = "findCompany")
    public Empresa findCompany(@WebParam(name = "query") String query){
        
        Empresa empresa;
        
        try {   
            empresa = daoEmpresa.find(query);  
            return empresa;   
            
        } catch (Exception ex) {
            Logger.getLogger(EmpresaWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return null;
    } 
    
    @WebMethod(operationName = "listCompanies")
    public ArrayList<Empresa> listCompanies(){
        
        ArrayList<Empresa> listEmpresas = new ArrayList<>();
        try {
            listEmpresas = daoEmpresa.findAll();
        } catch (Exception ex) {
            Logger.getLogger(EmpresaWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listEmpresas;
    }    
}
