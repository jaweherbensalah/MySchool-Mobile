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
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceUtilisateur {
      public ArrayList<Utilisateur> users;
         Statics u= new Statics();
           public ArrayList<String> niv;


    public static ServiceUtilisateur instance =null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUtilisateur() {
         req = new ConnectionRequest();
    }
     public static ServiceUtilisateur getInstance() {
        if (instance == null) {
            instance = new ServiceUtilisateur();
        }
        return instance;
    }
      public ArrayList<Utilisateur> parseUser(String jsonText) {
         try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Utilisateur t = new Utilisateur();
             //   t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setUsername(obj.get("username").toString());
                t.setRoles(obj.get("roles").toString());
                t.setEmail(obj.get("email").toString());
            String w = obj.get("classe").toString();
            if (w != null ) {
               t.setClasse(obj.get("classe").toString());
            }
          /*if(obj.get("classe2") == null )
           
           {
               t.setClasse2(obj.get("classe2").toString());
           }*/

                users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return users;
       
    }
 public ArrayList<Utilisateur> getAllNiveau(String nom){
        String url = Statics.BASE_URL+ "/pp/" + nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
 
 
 
 
 public ArrayList<Utilisateur> getAllEleveClasse(String nom){
        String url = Statics.BASE_URL+ "/elec/" + nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUser(new String(req.getResponseData()));
              
                req.removeResponseListener(this);
                
            }
        });
        
        System.out.println("jefioejfer");
        NetworkManager.getInstance().addToQueueAndWait(req);
         
       
        return users;
    }
  public ArrayList<Utilisateur> getEleveNiveau(String nom){
        String url = Statics.BASE_URL+ "/pp/" + nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
  public ArrayList<Utilisateur> rechercheByNom(String nom){
        String url = Statics.BASE_URL+ "/recherche/" + nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUser(new String(req.getResponseData()));
              
                req.removeResponseListener(this);
                
            }
        });
        
        System.out.println("jefioejfer");
        NetworkManager.getInstance().addToQueueAndWait(req);
         
       
        return users;
    }
}
