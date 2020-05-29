/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Punition;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServicePunition {
     public ArrayList<Punition> punitions;
    
    public static ServicePunition instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date current = new Date(); 
String s ; 
    public ServicePunition() { // une we7Da barka  instance 
         req = new ConnectionRequest();
    }
     public static ServicePunition getInstance() {
        if (instance == null) {
            instance = new ServicePunition();
        }
        return instance;
    }
     public ArrayList<Punition> parsePunition(String jsonText) {
        try {
            punitions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Punition t = new Punition();
                int  id = Integer.parseInt(obj.get("id").toString());
             //   t.setEleve((int)id);
               t.setId(id);
                t.setType(obj.get("type").toString());
              //  t.setDate(formatter.parse(obj.get("date").toString()));
               // t.setClasse(obj.get("classe").toString());
                punitions.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return punitions;
    }
 public ArrayList<Punition> getAllPunition(){
        String url = Statics.BASE_URL+"/punichment/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 punitions = parsePunition(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return punitions;
    }
   public String parseAbsenceCount(String jsonText) {
           
            JSONParser j = new JSONParser();
            String tasksListJson = jsonText.toLowerCase();
         
         
              s = tasksListJson.toString();
             
   
             
            
            
            
        
        return s;
    } 
 
 public String countpuneleve(String nom){
        String url = Statics.BASE_URL+"/sommepun/"+nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 s = parseAbsenceCount(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return s;
    }
 
 
 public ArrayList<Punition> findPunition(String heure){
        String url = Statics.BASE_URL+"/allpuneleve/"+heure;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  punitions = parsePunitionn(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }  
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return punitions;
  } 
 
 
 public ArrayList<Punition> parsePunitionn(String jsonText) {
        try {
            punitions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Punition t = new Punition();
               t.setType(obj.get("Type").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);  
                t.setClasse(obj.get("Classe").toString());
             t.setEleve(obj.get("Eleve").toString());
 //t.setDate(new Date(((Double) ((Map<String, Object>) obj.get("date")).get("timestamp")).longValue()* 1000));              
   String DateS = obj.get("date") .toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                       
              t.setDate(currentTime);
   
                punitions.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return punitions;
    }
}
