/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ichag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichag.coherencedata.Person;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelboermann
 */
public class RestCacheLoader implements com.tangosol.net.cache.CacheLoader {

    String sUrl = null;
    
    public RestCacheLoader (String sUrl) {
        this.sUrl = sUrl;
    }
    
    @Override
    public Object load(Object k) {
        Person person = null;
        try {
            URL url = new URL(sUrl+(String)k+".json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
            }

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
                String output = null;
                String personJson = "";
                while ((output = br.readLine()) != null) {
			personJson = personJson.concat(output);
		}
                ObjectMapper mapper = new ObjectMapper();
                person = mapper.readValue(personJson, Person.class);
                
                

        } catch (MalformedURLException ex) {
            Logger.getLogger(RestCacheLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestCacheLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person;
    }

    @Override
    public Map loadAll(Collection clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
