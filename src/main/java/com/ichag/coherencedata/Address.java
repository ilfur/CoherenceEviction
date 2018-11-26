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
//@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY,
//   property="@type")
@Portable
public class Address  {
   @PortableProperty(0)
   private String street;
   @PortableProperty(1)
   private String city;
   @PortableProperty(2)
   private String country;

   public String getStreet() {
       return street;
   }

   public void setStreet(String street) {
       this.street = street;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }
}

