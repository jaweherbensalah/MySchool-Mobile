/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServicePunition;
import java.util.ArrayList;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Punition;
import java.text.SimpleDateFormat;
/**
 *
 * @author Asus
 */
public class allpunenceEleve extends Form {
     final Resources res;
    private Form LoginF,LoginFormm;
    private Resources theme;
    public static String t1 ; 
    String user ; 
    EncodedImage enc,enc1;
int abs = 0; 
int pun = 0;
    ArrayList<Utilisateur> data = new ArrayList<Utilisateur>();
                                     ArrayList<Punition> data1 = new ArrayList<Punition>();
   Label l6,l23,l22,l5;

    public allpunenceEleve(Resources res) {
        this.res = res;
          setTitle("My School");    
           setUIID("backgroundd");
        
        Label profilePicLabel = new Label(UserForm_1.nom, UserForm_1.background, "SideMenuTitle");

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("My School", FontImage.MATERIAL_DASHBOARD,  e -> { LoginFormm = new  homeEnseignants(res); LoginFormm.show();});
        getToolbar().addMaterialCommandToSideMenu("Mon Profile", FontImage.MATERIAL_TRENDING_UP, e-> {LoginFormm = new profilFacebook(res); 
        LoginFormm.show();});
        getToolbar().addMaterialCommandToSideMenu("Mes Notes", FontImage.MATERIAL_ACCESS_TIME,  null) ;
        getToolbar().addMaterialCommandToSideMenu("Mes punitions  ", FontImage.MATERIAL_BOOK,  e -> { LoginFormm = new allpunenceEleve(res);
               LoginFormm.show();   });
        getToolbar().addMaterialCommandToSideMenu("Evenements  ", FontImage.MATERIAL_ATTACHMENT,  e -> {new UserForm_1().show(); });
         getToolbar().addMaterialCommandToSideMenu("Club  ", FontImage.MATERIAL_ATTACHMENT,  e -> {new UserForm_1().show(); });    
        getToolbar().addMaterialCommandToSideMenu("Réclamations ", FontImage.MATERIAL_SETTINGS,  e -> {new UserForm_1().show(); });
        getToolbar().addMaterialCommandToSideMenu("Se déconnecter ", FontImage.MATERIAL_EXIT_TO_APP,  e -> {new UserForm_1().show(); });

            ServicePunition abss = new ServicePunition();
            Container c1 = new Container(BoxLayout.y());
         Label lp = new Label(" les absences \n");
       Label countabs = new Label("");
                             Container cr = new Container(BoxLayout.y());
       countabs.setText(abss.countpuneleve(UserForm_1.nom));      
        Label ss = new Label("les punitions : "+ "  "+abss.countpuneleve(UserForm_1.nom));
         ss.setUIID("colour");        

        ss.getStyle().setPaddingLeft(18);
        cr.add(ss);
                      SpanLabel l = new SpanLabel(" ");         
                     // cr.add(l);

                       data1= abss.findPunition(UserForm_1.nom);
                        for (int i=0;i<data1.size();i++) {
                //  SpanLabel l3 = new SpanLabel("Les eleves  ");
                  l5 = new Label("type :"+data1.get(i).getType());
      l5.setUIID("labels");
        Label vw = new Label("********************");
                  vw.setUIID("labels");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(data1.get(i).getDate());
           l6 = new Label("Date :" + dateString);
                     l6.setUIID("labels");        
                  cr.add(l5);
                  cr.add(l6); 
                  cr.add(vw); 
                            
    }
                         Container c158 = new Container(BoxLayout.x());
                         Button retour = new Button("retour:");
                          retour.setUIID("colour");        
  retour.getStyle().setPaddingLeft(18);
                cr.add(retour);
                 retour.addActionListener((e) -> {    
               LoginF = new homeEleve(res);
                                    LoginF.show();  

              });
   this.add(cr);
   this.show();
    
    }
    
}
