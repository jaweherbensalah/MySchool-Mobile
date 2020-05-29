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
import com.codename1.notifications.LocalNotification;
import com.codename1.sendgrid.SendGrid;
import com.codename1.ui.Display;
import com.codename1.ui.TextSelection.Char;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceAbsence {
     public ArrayList<Absence> absences;
    
    public static ServiceAbsence instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date current = new Date(); 
      Absence taa ;
      
      String s ; 
    public ServiceAbsence() {
         req = new ConnectionRequest();
    }
     public static ServiceAbsence getInstance() {
        if (instance == null) {
            instance = new ServiceAbsence();
        }
        return instance;
    }
     public ArrayList<Absence> parseAbsence(String jsonText) {
        try {
            absences=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Absence t = new Absence();
               t.setNom_eleve(obj.get("nomEleve").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);  
                t.setNom_enseignant(obj.get("nomEnseignant").toString());
             t.setMatier(obj.get("matiere").toString());
                 t.setHeure(obj.get("heure").toString());
t.setDate(new Date(((Double) ((Map<String, Object>) obj.get("date")).get("timestamp")).longValue()* 1000));              
   //String DateS = obj.get("date") .toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
     //                   Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                       
       //       t.setDate(currentTime);
   
                absences.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return absences;
    }
     
     
     
     
      public String parseAbsenceCount(String jsonText) {
            absences=new ArrayList<>();
            JSONParser j = new JSONParser();
            String tasksListJson = jsonText.toLowerCase();
         
         
              s = tasksListJson.toString();
             
   
             
            
            
            
        
        return s;
    } 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
      public Absence Absences(String jsonText) {
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                       taa = new Absence();
                        taa.setNom_eleve(obj.get("nomEleve").toString());
                        taa.setHeure(obj.get("heure").toString());
   String DateS = obj.get("date") .toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().indexOf("timestamp") + 21);
                        Date currentTime = new Date(Double.valueOf(DateS).longValue() * 1000);
                       
              taa.setDate(currentTime);
            }  
        } catch (IOException ex) {    
        }
        return taa;
    }
     
 public ArrayList<Absence> getAllAbsence(String classe,String nom){
        String url = Statics.BASE_URL+"/absCla/"+classe+"/"+nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 absences = parseAbsence(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return absences;
    }
 public String countabseleve(String nom){
        String url = Statics.BASE_URL+"/sommeabs/"+nom;
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
  public boolean addAbsence(Absence t) {
      
        String url = Statics.BASE_URL +"/absnew?heure="+t.getHeure()+"&matiere="+t.getMatier()
       +"&nom_eleve="+t.getNom_eleve()+"&nom_enseignant="+t.getNom_enseignant()+"&classe="
       +t.getClasse()+"&date="+t.getDate();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override 
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
  
                SendGrid s = SendGrid.create("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");
//        SendGrid s = SendGrid.create("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");
        s.sendSync("eya.ksouri@esprit.tn"
                , "eya.ksouri@esprit.tn"
                , "Absence eleve "
                , " absent "
                                           );
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        
          return resultOK;
    }
  public ArrayList<Absence> findAbsence(String heure){
        String url = Statics.BASE_URL+"/fronteleve/"+heure;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                  absences = parseAbsence(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }  
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return absences;
  } 
  public boolean ModifierAbsence(Absence t){
     String url = Statics.BASE_URL +"/absmodif/"+t.getId()+"?heure="+t.getHeure()+"&matiere="+t.getMatier()
       +"&nom_eleve="+t.getNom_eleve()+"&nom_enseignant="+t.getNom_enseignant()+"&classe="
       +t.getClasse()+"&date="+t.getDate();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
               resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
         }
            
        }); 
          NetworkManager.getInstance().addToQueueAndWait(req);
       
        
          return resultOK;
  }
public boolean SupprimerAbsence(Absence t){
      String url = Statics.BASE_URL +"/abssupp/"+t.getId();
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
               resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
          }
            
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
       
        
          return resultOK;
}



}
