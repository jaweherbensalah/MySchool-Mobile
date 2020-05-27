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
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceMenu {

    public ArrayList<Menu> tasks;
    public ArrayList<Menu> listEvenements;
    
    
    public static ServiceMenu instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
//--------------------------------------------------------------------
    public ServiceMenu() {
         req = new ConnectionRequest();
    }

    public static ServiceMenu getInstance() {
        if (instance == null) {
            instance = new ServiceMenu();
        }
        return instance;
    }

    public boolean addTask(Menu t) {
        //String url = Statics.BASE_URL +"/new/"+ t.getNom() + "/" + t.getDescription()+ "/" + t.getAdresse()+ "/" + t.getDomaine(); //création de l'URL
        String url = Statics.BASE_URL+"/new?items="+t.getItems()+"&prix="+t.getPrix()+"&photo="
                + t.getPhoto();
       
        
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

    public ArrayList<Menu> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

 
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Menu t = new Menu();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setItems(obj.get("items").toString());
                t.setPrix(obj.get("prix").toString());
                t.setPhoto(obj.get("photo").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
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
    
    public ArrayList<Menu>        getAllTasks(){
        String url = Statics.BASE_URL+"/allMenu";
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
 

//***********Recherche***********************
    public ArrayList<Menu> Recherche(String item){        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl( "http://localhost/pi_final/web/app_dev.php/findMenu/"+item);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceMenu ser = new ServiceMenu();
                listEvenements = ser.parseTasks(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenements;
    }

}