package com.mycompany.myapp.services;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.mycompany.myapp.entities.Transport;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceTransport {
    public ArrayList<Transport> tasks;
    public static ServiceTransport instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    //----------------
    public ServiceTransport() {
         req = new ConnectionRequest();
    }
    public static ServiceTransport getInstance() {
        if (instance == null) {
            instance = new ServiceTransport();
        }
        return instance;
    }

    public boolean addTransport(Transport t) {
        //String url = Statics.BASE_URL +"/new/"+ t.getNom() + "/" + t.getDescription()+ "/" + t.getAdresse()+ "/" + t.getDomaine(); //création de l'URL
        String url = Statics.BASE_URL+"/new?items="+t.getDate_inscription()+"&prix="+t.getFin_inscription()+"&montant="
                + t.getMontant();
       
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Transport> parseTransports(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

 
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Transport t = new Transport();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
               
              //  t.setDate_inscription(obj.get("dateInscription").toString());
               // t.setFin_inscription(obj.get("finInscription").toString());
                t.setMontant(obj.get("montant").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
               
          Map<String, Object> date2  = (Map<String, Object>) obj.get("dateInscription");
                 float da = Float.parseFloat(date2.get("timestamp").toString());
                 Date d = new Date((long)(da-3600 )*1000);
                  Map<String, Object> date3  = (Map<String, Object>) obj.get("finInscription");
                 float da2 = Float.parseFloat(date3.get("timestamp").toString());
                 Date d2 = new Date((long)(da2-3600)*1000);
                 System.out.println(d);
                 
                 
                 
                 t.setDate_inscription(d);
              
                t.setFin_inscription(d2);
                
                
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return tasks;
    }
//------------------------------------------------------------
    
    public ArrayList<Transport>                getAllTransports(){
        String url = Statics.BASE_URL+"/allTransport";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTransports(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
 


}