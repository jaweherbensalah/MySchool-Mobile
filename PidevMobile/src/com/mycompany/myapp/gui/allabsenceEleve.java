/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceAbsence;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

/**
 *
 * @author Asus
 */
public class allabsenceEleve extends Form {
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

    public allabsenceEleve(Resources res) {
        this.res = res;
          setTitle("My School");    
          setUIID("backgroundd");
          Image profilePic = res.getImage("2732x2732-tea-rose-rose-solid-color-background.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(UserForm_1.nom, UserForm_1.background, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());
        ImageView  bn = new ImageView(); 
        Label l = new Label(); 
        l.setMask(profilePic.createMask()); 
        add(l); 
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
Container cr = new Container(BoxLayout.y());
            ServiceAbsence abss = new ServiceAbsence();
            Container c1 = new Container(BoxLayout.y());
         Label lp = new Label(" les absences \n");
       //   lp.setUIID("labels");
         // lp.setUIID("colour");        
       Label countabs = new Label("");
       countabs.setUIID("labels");
       countabs.setText(abss.countabseleve(UserForm_1.nom));      
        Label ss = new Label("les absences : "+ "  "+abss.countabseleve(UserForm_1.nom));
        ss.setUIID("colour");        

        ss.getStyle().setPaddingLeft(18);
        cr.add(ss);
    this.add(c1);
                      
  Container c11 = new Container(BoxLayout.x());  
                       data1= abss.findAbsence(UserForm_1.nom);
                        for (int i=0;i<data1.size();i++) {
                //  SpanLabel l3 = new SpanLabel("Les eleves  ");
                  l5 = new Label("nom enseignant :"+data1.get(i).getNom_enseignant());
                  l5.setUIID("labels");
                  l22 = new Label("heure :"+data1.get(i).getHeure());
                  l22.setUIID("labels");
                l23 = new Label("matier :"+data1.get(i).getMatier());
                l23.setUIID("labels");
                  Label vw = new Label("********************");
                  vw.setUIID("labels");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(data1.get(i).getDate());
           l6 = new Label("Date :" + dateString);
                             l6.setUIID("labels");
                  cr.add(l5);
                  cr.add(l22);
                  cr.add(l23);
                  cr.add(l6); 
                  cr.add(vw);           
    }
                         Container c158 = new Container(BoxLayout.y());
                         Button retour = new Button("retour");
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