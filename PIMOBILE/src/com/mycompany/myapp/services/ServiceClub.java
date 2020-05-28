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
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.utils.Statics;
import com.codename1.sendgrid.SendGrid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bhk
 */
public class ServiceClub {

    public ArrayList<Club> tasks;
    
    public static ServiceClub instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     
      String s ; 

    public ServiceClub() {
         req = new ConnectionRequest();
    }

    public static ServiceClub getInstance() {
        if (instance == null) {
            instance = new ServiceClub();
        }
        return instance;
    }

    public boolean addTask(Club t) {
       String url ="http://localhost/symfony-api/web/app_dev.php/event/tasks/newC?nom_club="+ t.getNom_club() +"&description="+ t.getDescription() +"&effectif="+  t.getEffectif() +"&domaine="+ t.getDomaine() ;
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
                  SendGrid s = SendGrid.create("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");
//        SendGrid s = SendGrid.create("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");
        s.sendSync("sirine.charrad@esprit.tn"
                , "sirine.charradi@esprit.tn"
                , "Nouveau Club  "
                , "Bonjour Myschool vous informe que vous avez ajouté un nouveau  club avec succés "
                                           );
        //SendGrid s = SendGrid.create("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");
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

   public ArrayList<Club> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Club t = new Club();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setNom_club(obj.get("nomClub").toString());
                t.setDescription(obj.get("description").toString());
                float effectif = Float.parseFloat(obj.get("effectif").toString());
                t.setEffectif((int)effectif);
                t.setDomaine(obj.get("domaine").toString());
               
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    
    public ArrayList<Club> getAllTasks(){
        String url = Statics.BASE_URL+"/affichageMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
      public String parseAbsenceCount(String jsonText) {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            String tasksListJson = jsonText.toLowerCase();
         
         
              s = tasksListJson.toString();
             
   
             
            
            
            
        
        return s;
    } 
    public String count(String nom){
        String url = Statics.BASE_URL+"/somme/"+nom;
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
    }

