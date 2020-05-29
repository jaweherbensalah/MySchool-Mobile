/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Utilisateur;
import static com.mycompany.myapp.gui.UserForm_1.background;
import static com.mycompany.myapp.gui.UserForm_1.nom;
import java.util.ArrayList;
import com.mycompany.myapp.services.ServiceUtilisateur;

/**
 *
 * @author Asus
 */
public class profilFacebook extends Form {
     final Resources res;
    private Form LoginF,LoginFormm;
    private Resources theme;
    public static String t1 ; 
    String user ; 
    EncodedImage enc,enc1;
int abs = 0; 
int pun = 0;
    ArrayList<Utilisateur> data = new ArrayList<Utilisateur>();
                                     ArrayList<Absence> data1 = new ArrayList<Absence>();
   Label l6,l23,l22,l5;

    public profilFacebook(Resources res) {
        this.res = res;
         setTitle("My School");    
          
         
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

        
        Container c = new Container(BoxLayout.xCenter());
         ScaleImageLabel myPic = new ScaleImageLabel();
                        myPic.setIcon(UserForm_1.background);
         c.add(myPic);
         add(c);
         Container cc = new Container(BoxLayout.y());
           SpanLabel l = new SpanLabel(" "); 
           cc.add(l);
         ServiceUtilisateur uti = new ServiceUtilisateur(); 
           data = uti.getAllNiveau("dali");
           for(int i=0; i<data.size(); i++){
               l5 = new Label("Nom : "+data.get(i).getUsername());
                      l6 = new Label("Email : "+data.get(i).getEmail());
           }
        cc.add(l5);
        cc.add(l6);
        add(cc);
        show();
    }
    
}
