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
public class CreateIdx extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/plain");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();

        NamedCache<String, Person> cache = CacheFactory.getCache("partner-cache");
        
        long startTime = System.currentTimeMillis();
        
        out.println("creating index on addr.country and name");
        ValueExtractor ex = new ChainedExtractor("getAddr.getCountry");
        cache.addIndex(ex, true, null);
        ex = new ReflectionExtractor("getName");
        cache.addIndex(ex, true, null);
        long duration = System.currentTimeMillis() - startTime;
        
        out.println ("done... took "+duration+" ms");
    }
            
}
