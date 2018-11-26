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
/**
 *
 * @author marcelboermann
 */
public class PumpDemoData extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/plain");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println ("Filling Cache...");

        NamedCache<String, Person> cache = CacheFactory.getCache("weaponry-cache");
        java.util.Map<String, Person> buffer = new java.util.HashMap();
            Person p = null;
            Address a = null;
        long startTime = System.currentTimeMillis();
        for (int i=0; i<1000000; i++) {
            p = new Person();
            a = new Address();
            p.setId(Integer.toUnsignedLong(i));
            p.setName("icke_"+i);
            a.setCity("New York_"+i);
            a.setCountry("USA_"+i);
            a.setStreet("Broadway_"+i);
            p.setAddr(a);
            buffer.put(String.valueOf(i), p);
        }
        cache.putAll(buffer);
        long duration = System.currentTimeMillis() - startTime;
        
        out.println("Done filling in took "+duration+" ms, now creating index");
        ValueExtractor ex = new ChainedExtractor("getAddr.getCountry");
        cache.addIndex(ex, true, null);
        ex = new ReflectionExtractor("getName");
        cache.addIndex(ex, true, null);
        
        out.println ("done...");
    }
            
}
