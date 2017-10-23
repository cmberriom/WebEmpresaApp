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
import com.webempresa.model.Empresa;
import java.util.ArrayList;
import org.bson.Document;

/**
 *
 * @author CATASTOR
 */
public class DAOEmpresaImpl extends MongoDBConnection implements DAOEmpresa{
    
    private final String collectionName = "company";

    @Override
    public void insert(Empresa empresa) throws Exception {
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            Document doc = new Document("NIT", empresa.getNit()).append("Name", empresa.getNombre())
                                                                .append("Address", empresa.getDireccion())
                                                                .append("Phone", empresa.getTelefono());
            mongoCollection.insertOne(doc);
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }
    }

    @Override
    public void update(Empresa empresa) throws Exception {
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            mongoCollection.updateOne(eq("NIT", empresa.getNit()),
                new Document("$set", new Document("NIT", empresa.getNit())
                                        .append("Name", empresa.getNombre())
                                        .append("Address", empresa.getDireccion())
                                        .append("Phone", empresa.getTelefono())));
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }
    }

    @Override
    public void delete(Empresa empresa) throws Exception {
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            mongoCollection.deleteOne(eq("NIT", empresa.getNit()));
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }
    }

    @Override
    public Empresa find(String query) throws Exception {
        
        Empresa empresa;
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName);
            FindIterable<Document> mongoCursor;
            mongoCursor = mongoCollection.find(eq("NIT", query));  
            Document result = mongoCursor.first();            
            empresa = new Empresa(result.getString("NIT"), result.getString("Name"), result.getString("Address"), result.getString("Phone"));           
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }    
        
        return empresa;
    }    

    @Override
    public ArrayList<Empresa> findAll() throws Exception {
        
        ArrayList<Empresa> listEmpresas = new ArrayList<>();
        Empresa empresa;
        try{
            this.connect();
            MongoCollection mongoCollection = this.mongoDatabase.getCollection(collectionName); 
            MongoCursor<Document> mongoCursor = mongoCollection.find().iterator();   
            
            while (mongoCursor.hasNext()) {
                Document record = mongoCursor.next();
                empresa = new Empresa(record.getString("NIT"), record.getString("Name"), record.getString("Address"), record.getString("Phone"));
                listEmpresas.add(empresa);
            }
            
        }catch (Exception e){
            throw e;
        }finally{
            this.close();
        }    
        
        return listEmpresas;
    }
}
