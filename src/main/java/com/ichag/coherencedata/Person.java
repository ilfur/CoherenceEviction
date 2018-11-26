/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ichag.coherencedata;

//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import java.io.Serializable;
import com.tangosol.io.pof.annotation.Portable;
import com.tangosol.io.pof.annotation.PortableProperty;



/**
 *
 * @author marcelboermann
 */
//@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY,
//   property="name", defaultImpl = Person.class)

@Portable
public class Person  {
   @PortableProperty(0)
   private Long id;
   @PortableProperty(1)
   private String name;
   @PortableProperty(2)
   private Address address;

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
      return address;
   }

   public void setAddr(Address addr) {
      this.address = addr;
   }
}