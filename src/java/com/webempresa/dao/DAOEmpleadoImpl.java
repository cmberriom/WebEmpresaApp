/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webempresa.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.eq;
import com.webempresa.model.Desarrollador;
import com.webempresa.model.Empleado;
import com.webempresa.model.Gerente;
import com.webempresa.model.Recepcionista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author CATASTOR
 */
public class DAOEmpleadoImpl extends MongoDBConnection implements DAOEmpleado{
    
    private final String collectionName = "employee";
    private final String jobDesarrollador = "Desarrollador";
    private final String jobGerente = "Gerente";
    private final String jobRecepcionista = "Recepcionista";

    @Override
    public void insert(Empleado empleado) throws Exception {
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            
            //Agregar datos genéricos de empleado
            Document doc = new Document("Identification", empleado.getCedula()).append("CompanyNIT", empleado.getCompanyNIT())
                                                                .append("Name", empleado.getNombre())
                                                                .append("LastName", empleado.getApellidos())
                                                                .append("Salary", empleado.getSalario())
                                                                .append("Experience", empleado.getExperiencia())
                                                                .append("Active", empleado.getActivo())
                                                                .append("Job", empleado.getJob());
            
            this.appendDataByJob(doc, empleado);
            
            mongoCollection.insertOne(doc);
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }
    }

    @Override
    public void update(Empleado empleado) throws Exception {
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            Document doc = new Document("Identification", empleado.getCedula()).append("CompanyNIT", empleado.getCompanyNIT())
                                                                .append("Name", empleado.getNombre())
                                                                .append("LastName", empleado.getApellidos())
                                                                .append("Salary", empleado.getSalario())
                                                                .append("Experience", empleado.getExperiencia())
                                                                .append("Active", empleado.getActivo())
                                                                .append("Job", empleado.getJob());
            
            this.appendDataByJob(doc, empleado);
            
            mongoCollection.updateOne(eq("Identification", empleado.getCedula()), new Document("$set", doc));
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }
    }

    @Override
    public void delete(Empleado empleado) throws Exception {
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            mongoCollection.deleteOne(eq("Identification", empleado.getCedula()));
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }
    }
    
    public void deleteAll(String query) throws Exception {
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            mongoCollection.deleteMany(eq("CompanyNIT", query));
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }
    }

    @Override
    public Empleado find(String query) throws Exception {
        
        Empleado empleado;
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            FindIterable<Document> mongoCursor;
            mongoCursor = mongoCollection.find(eq("Identification", Long.parseLong(query)));  
            Document result = mongoCursor.first();          
            empleado = this.getEmpleadoByJob(result);
                        
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }    
        
        return empleado;
    }    
    
    @Override
    public ArrayList<Empleado> findAll(String query) throws Exception {
        
        ArrayList<Empleado> listEmpleados = new ArrayList<>();
        Empleado empleado;
        MongoCursor<Document> mongoCursor;
        
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName); 
            if (!"".equals(query))
            {
                mongoCursor = mongoCollection.find(eq("CompanyNIT", query)).iterator();                 
            }else
            {
                mongoCursor = mongoCollection.find().iterator();              
            }
            
            while (mongoCursor.hasNext()) {
                Document record = mongoCursor.next();     
                empleado = this.getEmpleadoByJob(record);        
                listEmpleados.add(empleado);  
            }
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }    
        
        return listEmpleados;
    }
    
    private void appendDataByJob(Document _doc, Empleado _empleado){
                
        //Si es desarrollador, agregar datos propios
        if (_empleado instanceof Desarrollador)
        {
            _doc.append("ProfCard", ((Desarrollador) _empleado).getTarjetaProfesional());
            _doc.append("Level", ((Desarrollador) _empleado).getNivel());
            _doc.append("Title", "");
            _doc.append("ExpYears", "");
        }
        //Si es gerente, agregar datos propios
        if (_empleado instanceof Gerente)
        {
            _doc.append("ProfCard", "");
            _doc.append("Level", "");
            _doc.append("Title", ((Gerente) _empleado).getTitulo());
            _doc.append("ExpYears", "");
        }
        //Si es recepcionista, agregar datos propios
        if (_empleado instanceof Recepcionista)
        {
            _doc.append("ProfCard", "");
            _doc.append("Level", "");
            _doc.append("Title", "");
            _doc.append("ExpYears", ((Recepcionista) _empleado).getAniosExperiencia());
        }
    }
    
    private Empleado getEmpleadoByJob(Document _doc) throws ParseException{
          
        Empleado empleado;
        
        //Retornar objeto extendido de Empleado según cargo
        //Desarrollador
        switch (_doc.getString("Job")) {   
        //Gerente
            case jobDesarrollador:
                empleado = new Desarrollador(_doc.getLong("Identification"),
                        _doc.getString("CompanyNIT"),
                        _doc.getBoolean("Active"),
                        _doc.getString("Job"),
                        _doc.getString("ProfCard"),
                        _doc.getString("Level"));
                break;       
        //Recepcionista
            case jobGerente:
                empleado = new Gerente(_doc.getLong("Identification"),
                        _doc.getString("CompanyNIT"),
                        _doc.getBoolean("Active"),
                        _doc.getString("Job"),
                        _doc.getString("Title"));
                break;
            case jobRecepcionista:
                empleado = new Recepcionista(_doc.getLong("Identification"),
                        _doc.getString("CompanyNIT"),
                        _doc.getBoolean("Active"),
                        _doc.getString("Job"),
                        _doc.getLong("ExpYears"));
                break;
            default:
                empleado = null;
                break;
        }
        
        if (empleado != null){            
            empleado.setNombre(_doc.getString("Name"));
            empleado.setApellidos(_doc.getString("LastName"));
            empleado.setSalario(_doc.getDouble("Salary"));            
            empleado.setExperiencia(_doc.getDouble("Experience"));
        }   
        
        return empleado;
    }
}
