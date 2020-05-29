/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Utilisateur;
import static com.mycompany.myapp.gui.UserForm_1.nom;
import  com.mycompany.myapp.gui.ListAbsences;

import com.mycompany.myapp.services.ServiceUtilisateur;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Asus
 */
public class homeEnseignants extends Form {
     final Resources res;
    private Form c,LoginF,LoginFormm;
    private Resources theme;
    public static String t1 ; 
    String user = UserForm_1.nom; 
    EncodedImage enc,enc1;
                    ArrayList<Utilisateur> data = new ArrayList<Utilisateur>();
     public homeEnseignants(Resources res)  {
                      this.res = UIManager.initFirstTheme("/theme");

      c = new Form();  
               setTitle("My School");       
          setUIID("backgroundd");
          
        Label profilePicLabel = new Label(UserForm_1.nom, UserForm_1.background, "SideMenuTitle");

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("My School", FontImage.MATERIAL_DASHBOARD,  e -> { LoginFormm = new  homeEnseignants(res); LoginFormm.show();});
        getToolbar().addMaterialCommandToSideMenu("Mon Profile", FontImage.MATERIAL_TRENDING_UP, e-> {c = new profilFacebook(res); 
        LoginFormm.show();});
        getToolbar().addMaterialCommandToSideMenu("Mes Notes", FontImage.MATERIAL_ACCESS_TIME,  null) ;
        getToolbar().addMaterialCommandToSideMenu("Mes punitions  ", FontImage.MATERIAL_BOOK,  e -> { LoginFormm = new allpunenceEleve(res);
               LoginFormm.show();   });
        getToolbar().addMaterialCommandToSideMenu("Réclamations ", FontImage.MATERIAL_SETTINGS,  e -> {new UserForm_1().show(); });
        getToolbar().addMaterialCommandToSideMenu("Se déconnecter ", FontImage.MATERIAL_EXIT_TO_APP,  e -> { UserForm_1 u = new UserForm_1(); u.facebookLogout(); });

               
               
               
               
               
               
               
               
               
               
           
        
    
                        
        Container c1 = new Container(BoxLayout.y());       
         Label l = new Label("Classes : ");
         l.getStyle().setPaddingLeft(18);
        // l.getStyle().setPaddingTop(3);
        // l.setUIID("labels");
         ComboBox<String> choices = new ComboBox<>(); 
        // choices.getStyle().setPaddingTop(0);
          ServiceUtilisateur s = new ServiceUtilisateur();
          
         SpanLabel spp = new SpanLabel("  \n ");
           TextField tfmatiere = new TextField("", "recherche");
            Button rechercher = new Button();
                   rechercher.setText("rechercher");
                   
                   
                   
              data = s.getAllNiveau(user); 
               for(int i=0; i<data.size(); i++){ 
             t1 = data.get(i).getClasse();
         //   String t2 = data.get(i).getClasse2();
               
               choices.addItem(t1);
             //  choices.addItem(t2);
               }
             c1.add(spp); 
               c1.add(tfmatiere); 
               c1.add(rechercher); 
               c1.add(l); 
               c1.add(choices);
             //  c1.getStyle().setPaddingTop(2);
               this.add(c1);
              
               
               rechercher.addActionListener(new ActionListener() { 
                          @Override
                          public void actionPerformed(ActionEvent evt) {
                              if (tfmatiere.getText().length() != 0) {
                               Form f2 = new Form(BoxLayout.y());
 f2.setTitle("My School");
 f2.setUIID("backgroundd");
 Container c1 = new Container(BoxLayout.y());
 ServiceUtilisateur s = new ServiceUtilisateur(); 
   data = s.getAllNiveau(tfmatiere.getText()); 
 
  
    for(int i=0; i<data.size(); i++){ 
                     Label l4 = new Label("\n");
                     l4.setUIID("labels");
                     Label l5 = new Label("Nom : "+data.get(i).getUsername());
                     l5.setUIID("labels");
                     Label l6 = new Label("Email : "+data.get(i).getEmail());
                     l6.setUIID("labels");
                      
c1.add(l4);
c1.add(l5);
c1.add(l6);
    } 
Button retour = new Button();
                   retour.setText("retour ");
                   retour.setUIID("colour");        
  retour.getStyle().setPaddingLeft(18);
                c1.add(retour);
                retour.addActionListener(new ActionListener() { 
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                             LoginFormm = new homeEnseignants(res);
                                    LoginFormm.show();
                         }
                    
                });
                f2.add(c1); 

    
                                  f2.show(); 

                          }
                              
                          else if (tfmatiere.getText().length() == 0) {
                                                Dialog.show("Alert", "saisir nom ", new Command("OK"));
  
                          }
                          } 
                          
               });
               
               
               
                      Container c2 = new Container(BoxLayout.y()); 
                   Label lp = new Label();
               choices.addActionListener(new ActionListener() { 
             @Override
             public void actionPerformed(ActionEvent evt) {
                  c2.add(lp);
                 System.out.println("lll");
                 SpanLabel ml = new SpanLabel();
                     
                       ServiceUtilisateur s = new ServiceUtilisateur();
                       System.out.println(choices.getSelectedItem());
                        data = s.getAllEleveClasse(choices.getSelectedItem());
                          LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
                //  SpanLabel l3 = new SpanLabel("Les eleves  ");
                 for(int i=0; i<data.size(); i++){ 
                     Label l4 = new Label("\n");
                     l4.setUIID("labels");
                     Label l5 = new Label("Nom : "+data.get(i).getUsername());
                     l5.setUIID("labels");
                     Label l6 = new Label("Email : "+data.get(i).getEmail());
                     l6.setUIID("labels");
                       Label vw = new Label("********************");
                  vw.setUIID("labels");
c2.add(l4);
c2.add(l5);
c2.add(l6);
c2.add(vw);
                 }
                  Button login = new Button();
                   login.setText("Afficher les absences ");
                                    Button lp2 = new Button(" les punitions \n");
                                     login.setUIID("colour");        
  login.getStyle().setPaddingLeft(18);
                c2.add(login);
              
                 lp2.addActionListener(new ActionListener() { 
                      @Override
                      public void actionPerformed(ActionEvent evt) {
                       
                      }
                     
                 });
                 login.addActionListener(new ActionListener() { 
             @Override
             public void actionPerformed(ActionEvent evt) {
  LoginF = new ListAbsences(theme);
    LoginF.show(); 
             }     
                 });
             }
                   
               });
               
                
               this.add(c2);
     
     }
       
    }

