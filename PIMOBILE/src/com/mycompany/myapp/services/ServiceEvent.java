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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ServiceEvent {
    
    public ArrayList<event> parseListEvenementsJson(String json) {
        
        ArrayList<event> listEvenements = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");
            for (Map<String, Object> obj : list) {
                event e = new event();
              
                float id = Float.parseFloat(obj.get("id").toString());
                    e.setId((int) id);
                     e.setNom_event(obj.get("nomEvent").toString());

                        e.setDescription(obj.get("description").toString());
                        float placeDispo = Float.parseFloat(obj.get("placeDispo").toString());
              
                        e.setPlaceDispo((int) placeDispo); 
                        
                       
                        e.setImage(obj.get("image").toString());
                        
                       
//                        e.setHeureEvent(obj.get("HeureEvent").toString());
                        
                      //  float club_id = Float.parseFloat(obj.get("club_id").toString());
                       // e.setClub_id((int) club_id);
                //System.out.println(e);
                
             e.setDateEvent(new Date(((Double) ((Map<String, Object>) obj.get("dateevent")).get("timestamp")).longValue()* 1000));

                listEvenements.add(e);
            }
        } catch (IOException ex) {
        }
        return listEvenements;
    
    }
    
    

    ArrayList<event> listEvenements = new ArrayList();
    String date = new String();

    public String getDateEvenement(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/CiteCulture/web/app_dev.php/mobile/getDateEvent/"+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                date = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return date;
    }
     ArrayList<event> produitsParCategorie = new ArrayList<event>();
     public ArrayList<event> getListParCategorie(String cat) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony-api/web/app_dev.php/event/tasks/searchD?"
                + cat);
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent serv = new ServiceEvent();
                produitsParCategorie = serv.parseListEvenementsJson(new String(con.getResponseData()));
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return produitsParCategorie;
    }
    
    public ArrayList<event> getListEvenements() {
        ConnectionRequest cnx = new ConnectionRequest();
        cnx.setUrl("http://localhost/symfony-api/web/app_dev.php/event/affichageEvent");
        cnx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listEvenements = ser.parseListEvenementsJson(new String(cnx.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cnx);
        return listEvenements;
    }
    
    
    
       
    
    
    //public ArrayList<event> getListProdRecherches(String json) {

      //  ArrayList<event> produits = new ArrayList<event>();

        //try {
          //  System.out.println(json);
            //JSONParser j = new JSONParser();

            //Map<String, Object> prods = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.out.println(prods);

            //List<Map<String, Object>> list = (List<Map<String, Object>>) prods.get("root");

            //for (Map<String, Object> obj : list) {

                //---------------------------------------------------------------------------------------
                //-----------------------------------USER----------------------------------------------------
//                Map<String, Object> listRecupUser = null;
//                User u = new User();
//                if (obj.get("iduser") != null) {
//
//                    listRecupUser = (Map<String, Object>) obj.get("iduser");
//
//                    u.setId((int) Float.parseFloat(listRecupUser.get("id").toString()));
//                    u.setUsername(listRecupUser.get("username").toString());
//                    u.setMailUser(listRecupUser.get("email").toString());
//
//                }

                //---------------------------------------------------------------------------------------
                //-----------------------------------CATEGORY----------------------------------------------------
              //  Map<String, Object> listRecupCategory = null;

                //event c = new event();
                //if (obj.get("idCategorie") != null) {

                  //  listRecupCategory = (Map<String, Object>) obj.get("idCategorie");

                    //c.setId((int) Float.parseFloat(listRecupCategory.get("idCategorie").toString()));
                  //  c.setNom(listRecupCategory.get("nom").toString());
                  

               // }

                //---------------------------------------------------------------------------------------
                //-----------------------------PRODUCT----------------------------------------------------------
               // event p = new event();

                //p.setId((int) Float.parseFloat(obj.get("id").toString()));

                 //event e = new event();
              
                //float id = Float.parseFloat(obj.get("id").toString());
                  //  e.setId((int) id);
                    // e.setNom_event(obj.get("nomEvent").toString());

                      //  e.setDescription(obj.get("description").toString());
                       // float placeDispo = Float.parseFloat(obj.get("placeDispo").toString());
              
                        //e.setPlaceDispo((int) placeDispo); 
                        
                       
                        //e.setImage(obj.get("image").toString());
                        
                       
//                        e.setHeureEvent(obj.get("HeureEvent").toString());
                        
                      //  float club_id = Float.parseFloat(obj.get("club_id").toString());
                       // e.setClub_id((int) club_id);
                //System.out.println(e);
                
             //e.setDateEvent(new Date(((Double) ((Map<String, Object>) obj.get("dateevent")).get("timestamp")).longValue()* 1000));

          //      produits.add(p);

            //}

       // } catch (IOException ex) { 
            
       // }
        //System.out.println(produits);
        //return produits;

    //}

    
    
      //ArrayList<event> produitsRecherches = new ArrayList<event>();
        
   
    
       // ArrayList<event> fRecherches = new ArrayList<event>();
      ArrayList<event> produitsRecherches = new ArrayList<event>();

  public ArrayList<event> getListRecherches(String tag) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony-api/web/app_dev.php/event/tasks/searchD"  +tag);
         System.out.println("OK");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent serv = new ServiceEvent();
                produitsRecherches = serv.parseListEvenementsJson(new String(con.getResponseData()));
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return produitsRecherches;
    }   
  public ArrayList<event> Recherche(String nomEvent){        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl( "http://localhost/symfony-api/web/app_dev.php/event/event/findMobile/"+nomEvent);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEvent ser = new ServiceEvent();
                listEvenements = ser.parseListEvenementsJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenements;
    }
  public ArrayList<event> getCProducts() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl( "http://localhost/symfony-api/web/app_dev.php/event/tasks/tri");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               ServiceEvent ser = new ServiceEvent();
                listEvenements = ser.parseListEvenementsJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvenements;
    }
}