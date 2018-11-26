/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ichag;

import com.ichag.coherencedata.Person;
import com.ichag.coherencedata.Address;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;
import com.tangosol.util.ValueExtractor;
import com.tangosol.util.extractor.ReflectionExtractor;
import com.tangosol.util.extractor.ChainedExtractor;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 *
 * @author marcelboermann
 */
public class PumpFromDB extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/plain");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println ("Filling Cache...");

        NamedCache<String, Person> cache = CacheFactory.getCache("partner-cache");
        java.util.Map<String, Person> buffer = new java.util.HashMap();
        
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = factory.createEntityManager();
        
        
        
        long startTime = System.currentTimeMillis();
        Query q = em.createQuery("SELECT p FROM Person p");
        List<Person> s = q.getResultList();
        Iterator<Person> it = s.iterator();
        Person p = null;
        int i=0;
        while (it.hasNext()) {
            if (i < 25000) {
                i=i+1;
                p = it.next();
                buffer.put(String.valueOf(p.getId()), p);
            } else {
                i=0;
                cache.putAll(buffer);
                buffer = new java.util.HashMap();
            }
        }
        cache.putAll(buffer);
        long duration = System.currentTimeMillis() - startTime;
        
        buffer = new java.util.HashMap();
        out.println("Done filling in took "+duration+" ms, now creating index");
        ValueExtractor ex = new ChainedExtractor("getAddr.getCountry");
        cache.addIndex(ex, true, null);
        ex = new ReflectionExtractor("getName");
        cache.addIndex(ex, true, null);
        
        out.println ("done...");
    }
            
}
