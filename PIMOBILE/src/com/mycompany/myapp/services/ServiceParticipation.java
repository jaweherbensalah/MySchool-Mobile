/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.event;
import com.mycompany.myapp.entities.participation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceParticipation {
    public ArrayList<event> parsePartJson(String json) {
        
        ArrayList<event> listpart = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                event e = new event();
                ServiceEvent es =new ServiceEvent();
                
        float id = Float.parseFloat(obj.get("idevent").toString());
                //e.setIdevent((int) id);
              // float id = Float.parseFloat(obj.get("idevent").toString());
                //e.setIdevent((int) id);
                //e.setNomevent(obj.get("nomevent").toString());
               // e.setCategorieevent(obj.get("categorieevent").toString());
                //float nbrplace = Float.parseFloat(obj.get("nbrplacedispo").toString());
                //e.setNbrplacedispo((int)nbrplace);
               // e.setHeureevent(obj.get("heureevent").toString());
                //e.setDateevent(es.getDateEvenement((int) id));
                //e.setAffiche(obj.get("affiche").toString());
                    
                
                listpart.add(e);
                
            }
        } catch (IOException ex) {
        }
        return listpart;
    
    }

   public void ajouterPartEvenement(int idevent,int iduser) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony-api/web/app_dev.php/event/tasks/new?iduser" + idevent + "/" + iduser ;// création de l'URL
       
         
        con.setUrl(Url);
         //String Url = "http://localhost/CiteCulture/web/app_dev.php/mobile/ajoutParticip/" + idevent + "/" + iduser ;// création de l'URL
        //con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   
   public void deletePartEvenement(int idevent,int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/CiteCulture/web/app_dev.php/mobile/UserDeletePart?idevent=" + idevent+ "&id=" +id;// création de l'URL
       // new?name=" + t.getName() + "&status=" + t.getStatus()
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

   
    public void ParticipEvent(participation p) {
        System.out.println(p);
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/symfony-api/web/app_dev.php/event/tasks/new?iduser=" + p.getIduser() + "&idevent=" + p.getIdevent() +"&createdat=" +p.getCreatedat();
        System.out.println(Url);
   
// création de l'URLgetIdUser
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
            ToastBar.showMessage(" participation avec succées.",FontImage.MATERIAL_DONE);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

   
   
}
