
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.sendgrid.SendGrid;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.AbonnesTransport;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceAbonnesTransport{

    public ArrayList<AbonnesTransport> tasks;
    
    public static ServiceAbonnesTransport instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAbonnesTransport() {
         req = new ConnectionRequest();
    }

    public static  ServiceAbonnesTransport getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnesTransport();
        }
        return instance;
    }

    public boolean addTask(AbonnesTransport t) {
        //String url = Statics.BASE_URL +"/new/"+ t.getNom() + "/" + t.getDescription()+ "/" + t.getAdresse()+ "/" + t.getDomaine(); //création de l'URL
        String url = Statics.BASE_URL+"/newAbonnesTransport?email="+t.getEmail()+"&username="+t.getUsername()+"&password="
                + t.getPassword()+"&duree_abonnement="+ t.getDuree_abonnement();
      
       
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
        
                      SendGrid s = SendGrid.create("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");
//          SendGrid s = SendGrid.create("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");
            s.sendSync("jaweherbensalahmpsi@gmail.com"
                , "jaweherbensalahmpsi@gmail.com"
                , "MySchool"
                , "Inscription confirmée "
               );
                
                
                
                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<AbonnesTransport> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

 
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
           
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                AbonnesTransport t = new AbonnesTransport();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                //  t.setNom(((int)Float.parseFloat(obj.get("status").toString())));
                t.setEmail(obj.get("email").toString());
                t.setUsername(obj.get("username").toString());
              //  t.setSolde(obj.get("solde").toString());
              //  t.setPassword(obj.get("password").toString());
                t.setDuree_abonnement(obj.get("dureeAbonnement").toString());
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
    
    public ArrayList<AbonnesTransport>        getAllTasks(){
        String url = Statics.BASE_URL+"/allAbonnesTransport";
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
   
  
}


