/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ichag.coherencedata;

//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import java.io.Serializable;
import com.ichag.coherencedata.Address;
import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author marcelboermann
 */
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@type", defaultImpl = Person.class)
@XmlRootElement(name = "Partner", namespace = "http://www.oracle.com/cloud/coherence")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
@Portable
public class Person  {
   @Id
   @PortableProperty(0)
   private Long id;
   @PortableProperty(1)
   private String name;
   @Embedded
   @PortableProperty(2)
   private Address addr;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   //@JsonProperty("address")
   public Address getAddr() {
      return addr;
   }

   public void setAddr(Address addr) {
      this.addr = addr;
   }
}