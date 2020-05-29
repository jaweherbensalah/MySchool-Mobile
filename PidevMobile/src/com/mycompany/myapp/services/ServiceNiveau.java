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
import com.mycompany.myapp.entities.Niveau;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceNiveau {
      public ArrayList<Niveau> niveaux;
         Statics u= new Statics();
           public ArrayList<String> niv;


    public static ServiceNiveau instance =null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceNiveau() { 
         req = new ConnectionRequest();
    }
     public static ServiceNiveau getInstance() {
        if (instance == null) {
            instance = new ServiceNiveau();
        }
        return instance;
    }
     public ArrayList<Niveau> parseNiveaux(String jsonText) {
         try {
            niveaux=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Niveau t = new Niveau();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
             //   t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setNom(obj.get("nomEnseignant").toString());
                niveaux.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return niveaux;
       
    }
 public ArrayList<Niveau> getAllNiveau(){
        String url = Statics.BASE_URL+"/abs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                niveaux = parseNiveaux(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return niveaux;
    }
 
}
