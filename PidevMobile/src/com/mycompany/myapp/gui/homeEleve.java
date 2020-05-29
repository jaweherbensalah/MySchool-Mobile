/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;
import com.mycompany.myapp.services.ServicePunition;

import java.io.IOException;
import java.util.ArrayList;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Utilisateur;
import static com.mycompany.myapp.gui.UserForm_1.nom;
import com.mycompany.myapp.services.ServiceAbsence;
import com.mycompany.myapp.services.ServiceUtilisateur;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.scene.control.ChoiceBox;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
 
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Map;
/**
 *
 * @author Asus
 */

public class homeEleve extends Form {
     final Resources res;
    private Form c,LoginF,LoginFormm;
    private Resources theme;
    public static String t1 ; 
    String user ; 
    EncodedImage enc,enc1,enccc;
int abs = 0; 
int pun = 0;
    ArrayList<Utilisateur> data = new ArrayList<Utilisateur>();
                                     ArrayList<Absence> data1 = new ArrayList<Absence>();
   Label l6,l23,l22,l5;
     public homeEleve(Resources res)  {
         this.res = res;
        c = new Form();  
             
               setTitle("My School");   
               setUIID("backgroundd");
   Toolbar tb = this.getToolbar();
  
 
        Label profilePicLabel = new Label(UserForm_1.nom, UserForm_1.background, "SideMenuTitle");

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("My School", FontImage.MATERIAL_DASHBOARD,  e -> { c = new  homeEnseignants(res); c.show();});
        getToolbar().addMaterialCommandToSideMenu("Mon Profile", FontImage.MATERIAL_TRENDING_UP, e-> {c = new profilFacebook(res); 
        c.show();});
        getToolbar().addMaterialCommandToSideMenu("Mes Notes", FontImage.MATERIAL_ACCESS_TIME,  null) ;
        getToolbar().addMaterialCommandToSideMenu("Mes punitions  ", FontImage.MATERIAL_BOOK,  e -> { c = new allpunenceEleve(res);
               c.show();   });
        getToolbar().addMaterialCommandToSideMenu("Evenements  ", FontImage.MATERIAL_ATTACHMENT,  e -> {new UserForm_1().show(); });
                  getToolbar().addMaterialCommandToSideMenu("Club  ", FontImage.MATERIAL_ATTACHMENT,  e -> {new UserForm_1().show(); });    
        getToolbar().addMaterialCommandToSideMenu("Réclamations ", FontImage.MATERIAL_SETTINGS,  e -> {new UserForm_1().show(); });
        getToolbar().addMaterialCommandToSideMenu("Se déconnecter ", FontImage.MATERIAL_EXIT_TO_APP,  e -> {UserForm_1 u = new UserForm_1(); u.facebookLogout(); });

               
        

        
        
        
             Container c2 = new Container(BoxLayout.y()); 
          ServiceUtilisateur s = new ServiceUtilisateur();
                        data = s.getEleveNiveau(UserForm_1.nom);
                for(int i=0;i<data.size();i++) { 
                      Label l99 = new Label("Classe : "+data.get(i).getClasse());
                    // c2.add(l99);
            }               
         
             
         try {
              Container c = new Container(BoxLayout.xCenter());
              
            ServiceAbsence abss = new ServiceAbsence();
              ServicePunition punn = new ServicePunition();
             enc = EncodedImage.create("/unnamed.png");
             ImageViewer logo = new ImageViewer(enc);
                          Container cc = new Container(BoxLayout.x()); 
              Button absences = new Button("absences:");
              absences.setUIID("labels");
            Label countabs = new Label("");
                             countabs.setText(abss.countabseleve(UserForm_1.nom));
            
             enc1 = EncodedImage.create("/unnamed (1).png");
             ImageViewer logo1 = new ImageViewer(enc1);
                Container ccc = new Container(BoxLayout.x()); 
                           Button punitions = new Button("Punition:");
                           punitions.setUIID("labels");
 Label countpun = new Label("");
                 countpun.setText(punn.countpuneleve(UserForm_1.nom));
              c.add(enc);
              ccc.add(absences);
               ccc.add(countabs);
              c.add("     ");
              c.add(enc1);
              ccc.add(punitions);
              ccc.add(countpun);
              
              add(c);
              add(cc);
              add(ccc);
               Container cccc = new Container(BoxLayout.y());
                enccc = EncodedImage.create("/icon.png");
             ImageViewer empl = new ImageViewer(enccc);
             cccc.add(empl);
              Button devGuide = new Button("emplois du temps ");
              devGuide.setUIID("labels");
           Container cc4cc = new Container(BoxLayout.y());    
devGuide.addActionListener(e -> {
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + "Test.pdf";
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile("http://127.0.0.1:8000/imprimerEmplois/3A25", fileName, true);
       
    }
    Display.getInstance().execute(fileName);
});
cccc.add(devGuide);
add(cccc);
              absences.addActionListener((e) -> {    
               
LoginF = new allabsenceEleve(res);
                                    LoginF.show();  
              });
              
              punitions.addActionListener((e) -> {    
              LoginF = new allpunenceEleve(res);
               LoginF.show();  

              });
              
               
         } catch (IOException ex) {
         }
         
              
              
           show();
            
           
               
     
     }
       
    }


