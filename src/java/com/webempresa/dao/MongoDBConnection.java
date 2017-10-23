package com.webempresa.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CATASTOR
 */
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    protected MongoClient mongoClient;
    protected MongoDatabase mongoDatabase;
    private final String url = "localhost";
    private final int port = 27017;
    private final String dbName = "DBCompany";
    
    public void connect() throws Exception{
        
        try{		
            mongoClient = new MongoClient(url,port);
            mongoDatabase = mongoClient.getDatabase(dbName);
            System.out.println("Connect to database successfully");

        }catch(Exception e){
           System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }  
    
    public void close() throws Exception{
        
        try{		
            mongoClient.close();

        }catch(Exception e){
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }    
}