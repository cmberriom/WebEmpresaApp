package com.webempresa.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   //--------------------------------------------------------------------------
   // Static members
   //--------------------------------------------------------------------------
  
   private final static QName _Empleado_QNAME = new QName("http://model.example5.test/", "Empleado");
   private final static QName _Desarrollador_QNAME = new QName("http://model.example5.test/", "Desarrollador");
   private final static QName _Gerente_QNAME = new QName("http://model.example5.test/", "Gerente");
   private final static QName _Recepcionista_QNAME = new QName("http://model.example5.test/", "Recepcionista");

   //-------------------------------------------------------------------------
   // Constructor
   //-------------------------------------------------------------------------
  
   /**
    * Create a new ObjectFactory that can be used to create new instances
    *
    */
   public ObjectFactory() {
   }

   /**
    * Create an instance of {@link JAXBElement }{@code <}{@link Empleado}{@code >}}
    *
    */
   @XmlElementDecl(namespace = "http://model.example5.test/", name = "Empleado")
   public JAXBElement createEmpleado(Empleado value) {
       return new JAXBElement(_Empleado_QNAME, Empleado.class, null, value);
   }

   /**
    * Create an instance of {@link Desarrollador }
    *
    */
   public Desarrollador createDesarrollador(Empleado value) {
       return new Desarrollador(value.getCedula(), value.getCompanyNIT(), value.getActivo(), value.getJob(), ((Desarrollador) value).getTarjetaProfesional(), ((Desarrollador) value).getNivel());
   }


   /**
    * Create an instance of {@link JAXBElement }{@code <}{@link Desarrollador}{@code >}}
    *
    */
   @XmlElementDecl(namespace = "http://model.example5.test/", name = "Desarrollador")
   public JAXBElement createDesarrollador(Desarrollador value) {
       return new JAXBElement(_Desarrollador_QNAME, Desarrollador.class, null, value);
   }
  
   /**
    * Create an instance of {@link Gerente }
    *
    */
   public Gerente createGerente(Empleado value) {
       return new Gerente(value.getCedula(), value.getCompanyNIT(), value.getActivo(), value.getJob(), ((Gerente) value).getTitulo());
   }


   /**
    * Create an instance of {@link JAXBElement }{@code <}{@link Gerente}{@code >}}
    *
    */
   @XmlElementDecl(namespace = "http://model.example5.test/", name = "Gerente")
   public JAXBElement createGerente(Gerente value) {
       return new JAXBElement(_Gerente_QNAME, Gerente.class, null, value);
   }
   
   /**
    * Create an instance of {@link Gerente }
    *
    */
   public Recepcionista createRecepcionista(Empleado value) {
       return new Recepcionista(value.getCedula(), value.getCompanyNIT(), value.getActivo(), value.getJob(), ((Recepcionista) value).getAniosExperiencia());
   }


   /**
    * Create an instance of {@link JAXBElement }{@code <}{@link Gerente}{@code >}}
    *
    */
   @XmlElementDecl(namespace = "http://model.example5.test/", name = "Recepcionista")
   public JAXBElement createRecepcionista(Recepcionista value) {
       return new JAXBElement(_Gerente_QNAME, Recepcionista.class, null, value);
   }
}