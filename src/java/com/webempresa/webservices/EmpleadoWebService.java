/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webempresa.webservices;

import com.webempresa.dao.DAOEmpleadoImpl;
import com.webempresa.model.Desarrollador;
import com.webempresa.model.Empleado;
import com.webempresa.model.Gerente;
import com.webempresa.model.Recepcionista;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author CATASTOR
 */
@WebService(serviceName = "EmpleadoWebService")
@XmlSeeAlso({
   com.webempresa.model.ObjectFactory.class
})
public class EmpleadoWebService {

    private final String jobDesarrollador = "Desarrollador";
    private final String jobGerente = "Gerente";
    private final String jobRecepcionista = "Recepcionista";  
    private final DAOEmpleadoImpl daoEmpleado;
    
    public EmpleadoWebService() {
        super();
        daoEmpleado = new DAOEmpleadoImpl();      
    } 
    
    @WebMethod(operationName = "createEmployee")
    public void createEmployee(@WebParam(name = "_empleado") Empleado _empleado) throws ParseException{
        
        try {     
            Empleado empleado = this.generateEmployeeByType(_empleado);   
            empleado.setActivo(false);
            empleado.setExperiencia(50);
            daoEmpleado.insert(empleado);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @WebMethod(operationName = "updateEmployee")
    public void updateEmployee(@WebParam(name = "_empleado") Empleado _empleado){
        
        try {   
            Empleado empleado = this.generateEmployeeByType(_empleado); 
            empleado.setActivo(_empleado.getActivo());
            empleado.setExperiencia(_empleado.getExperiencia());
            daoEmpleado.update(empleado);              
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @WebMethod(operationName = "deleteEmployee")
    public void deleteEmployee(@WebParam(name = "_empleado") Empleado _empleado){
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula()));            
            daoEmpleado.delete(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
        
    @WebMethod(operationName = "deleteAllEmployees")
    public void deleteAllEmployees(@WebParam(name = "query") String query){
        
        try {              
            daoEmpleado.deleteAll(query);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @WebMethod(operationName = "findEmployee")
    public Empleado findEmployee(@WebParam(name = "query") String query){
        
        Empleado empleado;
        
        try {   
            empleado = daoEmpleado.find(query);  
            return empleado;   
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return null;
    } 
    
    @WebMethod(operationName = "listEmployees")
    public ArrayList<Empleado> listEmployees(@WebParam(name = "query") String query){
        
        ArrayList<Empleado> listEmpleados = new ArrayList<>();
        try {
            listEmpleados = daoEmpleado.findAll(query);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listEmpleados;
    }
    
    public Empleado generateEmployeeByType(Empleado _empleado) throws ParseException{
        
        Empleado empleado = null;
        try {   
            switch (_empleado.getJob()){
                case jobDesarrollador:
                    empleado = new Desarrollador(_empleado.getCedula(), _empleado.getCompanyNIT(),
                                                _empleado.getActivo(), _empleado.getJob(), 
                                                ((Desarrollador) _empleado).getTarjetaProfesional(),
                                                ((Desarrollador) _empleado).getNivel());
                    break;
                case jobGerente:
                    empleado = new Gerente(_empleado.getCedula(), _empleado.getCompanyNIT(),
                                                _empleado.getActivo(), _empleado.getJob(), 
                                                ((Gerente) _empleado).getTitulo());
                    break;
                case jobRecepcionista:
                    empleado = new Recepcionista(_empleado.getCedula(), _empleado.getCompanyNIT(),
                                                _empleado.getActivo(), _empleado.getJob(), 
                                                ((Recepcionista) _empleado).getAniosExperiencia());
                    break;
                default:
                    empleado = null;
                    break;
            }            
            
            if (empleado != null){            
                empleado.setNombre(_empleado.getNombre());
                empleado.setApellidos(_empleado.getApellidos());
                empleado.setSalario(_empleado.getSalario());              
            }   
            
        } catch (NumberFormatException ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return empleado;
    }
    
    @WebMethod(operationName = "raiseSalaries")
    public String raiseSalaries(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";        
        
        try {   
            ArrayList<Empleado> listEmpleados = this.listEmployees(_empleado.getCompanyNIT());
            
            for (Empleado empleado : listEmpleados) {
                    empleado.setSalario(empleado.getSalario() + (empleado.getSalario() * 0.05)); 
                    daoEmpleado.update(empleado);  
		}
             
            outMessage = "Salarios actualizados";
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    } 
    
    @WebMethod(operationName = "employeeEnterWork")
    public String employeeEnterWork(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula()));   
            outMessage = empleado.ingresarTrabajo();
            daoEmpleado.update(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    } 
    
    @WebMethod(operationName = "employeeExitWork")
    public String employeeExitWork(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula()));   
            outMessage = empleado.salirTrabajo();
            daoEmpleado.update(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    } 
    
    @WebMethod(operationName = "employeeDoWork")
    public String employeeDoWork(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula()));   
            outMessage = empleado.trabajar();
            daoEmpleado.update(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    } 
    
    @WebMethod(operationName = "employeeTraining")
    public String employeeTraining(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula()));   
            outMessage = empleado.capacitacion();
            daoEmpleado.update(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    } 
    
    @WebMethod(operationName = "employeeDevelopmentMeeting")
    public String employeeDevelopmentMeeting(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula()));   
            outMessage = ((Desarrollador) empleado).reunionDesarrollo();
            daoEmpleado.update(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    } 
    
    @WebMethod(operationName = "employeeAssignTasks")
    public String employeeAssignTasks(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula()));
            outMessage = ((Gerente) empleado).asignarTareas();
            daoEmpleado.update(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    } 
    
    @WebMethod(operationName = "employeeAnswerPhone")
    public String employeeAnswerPhone(@WebParam(name = "_empleado") Empleado _empleado){
        
        String outMessage = "";
        
        try {   
            Empleado empleado = this.findEmployee(Long.toString(_empleado.getCedula())); 
            outMessage = ((Recepcionista) empleado).contestarTelefono();
            daoEmpleado.update(empleado);  
            
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return outMessage;
    }     
}
