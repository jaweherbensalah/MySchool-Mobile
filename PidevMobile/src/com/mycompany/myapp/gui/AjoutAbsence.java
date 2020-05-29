/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceAbsence;
import com.mycompany.myapp.services.ServiceUtilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class AjoutAbsence extends Form{
    final Resources res;
    private Form c,LoginF;
    private Resources theme;
     String user = UserForm_1.nom; 
     String classe = homeEnseignants.t1;
                    ArrayList<Utilisateur> data = new ArrayList<Utilisateur>();
EncodedImage enc,enc1;
    public AjoutAbsence(Resources res) {
        this.res = res;
   
        
         c = new Form();  
             
               setTitle("My School");       
 setUIID("backgroundd");
        Label profilePicLabel = new Label(UserForm_1.nom, UserForm_1.background, "SideMenuTitle");

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("My School", FontImage.MATERIAL_DASHBOARD,  e -> { c = new  homeEnseignants(res); c.show();});
        getToolbar().addMaterialCommandToSideMenu("Mon Profile", FontImage.MATERIAL_TRENDING_UP, e-> {c = new profilFacebook(res); 
        c.show();});
        getToolbar().addMaterialCommandToSideMenu(" Notes", FontImage.MATERIAL_ACCESS_TIME,  null) ;
        getToolbar().addMaterialCommandToSideMenu(" Absences  ", FontImage.MATERIAL_BOOK,  e -> { c = new allpunenceEleve(res);
               c.show();   });
        getToolbar().addMaterialCommandToSideMenu("Réclamations ", FontImage.MATERIAL_SETTINGS,  e -> {new UserForm_1().show(); });
        getToolbar().addMaterialCommandToSideMenu("Se déconnecter ", FontImage.MATERIAL_EXIT_TO_APP,  e -> {new UserForm_1().show(); });


        
        
   Container c3 = new Container(BoxLayout.yCenter()); 
            Label l = new Label("Ajouter  absence ");
            l.setUIID("colour");        

        l.getStyle().setPaddingLeft(18);
         c3.add(l);
          Container c5 = new Container(BoxLayout.y());
                             ComboBox<String> tfHeure = new ComboBox<>(); 
        TextField tfmatiere = new TextField("", "matiere");
        tfmatiere.setUIID("colour"); 
        Label ll = new Label("Eleves : ");
       ComboBox<String> choices = new ComboBox<>(); 
         ServiceUtilisateur s = new ServiceUtilisateur();
           data = s.getAllEleveClasse(classe);
             for(int i=0; i<data.size(); i++){ 
                 choices.addItem(data.get(i).getUsername());
             }
             
                String a = "8h"; 
        String b = "9h"; 
             String c ="10h"; 
             String d = "11h"; 
             String e = "12"; 
             String f = "13h"; 
             String ff = "14h"; 
            String g = "15h"; 
             String k = "16h"; 
             String i = "17h"; 
     tfHeure.addItem(a);
     tfHeure.addItem(b);
     tfHeure.addItem(c);
     tfHeure.addItem(d);
     tfHeure.addItem(e);
     tfHeure.addItem(f);
     tfHeure.addItem(ff);
     tfHeure.addItem(g);
     tfHeure.addItem(k);
     tfHeure.addItem(i);
       
             c5.add(tfHeure);
             c5.add(tfmatiere);
             c5.add(choices);
             this.add(c3);
              this.add(c5);
        Container c4 = new Container(BoxLayout.yCenter());
                    Button login = new Button();
                   login.setText("Ajouter ");
                   login.setUIID("colour");        
  login.getStyle().setPaddingLeft(18);
                c4.add(login);
                  Button retour = new Button();
                   retour.setText("Annuler ");
                   retour.setUIID("colour");        
  retour.getStyle().setPaddingLeft(18);
                c4.add(retour);
                 this.add(c4);
                retour.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent evt) {
               LoginF = new ListAbsences(theme);
    LoginF.show();     
            }
            });
                login.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent evt) {
                  if ((tfHeure.getSelectedItem()==null)||(tfmatiere.getText().length()==0)||(choices.getSelectedItem()==null))
                    Dialog.show("Alert", "saisir les champs ", new Command("OK"));
                else
                {
                     ServiceAbsence s2 = new ServiceAbsence();
                   Absence t; 
                  Date cu = new Date(); 
                 //  t.setDate(cu); 
                 t = new Absence(tfHeure.getSelectedItem(), tfmatiere.getText(),choices.getSelectedItem(),user,classe,cu);   
                 if(s2.addAbsence(t) == true ) {
                            Dialog.show("Success","Absence est ajoutée ",new Command("OK"));
                             LoginF = new ListAbsences(theme);
                      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->LoginF.show());
LoginF = new ListAbsences(theme);
    LoginF.show(); 
                            //   LoginF = new ListAbsences(theme);
  //  LoginF.show(); 
           
                 } 
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                }
                  
            }
                    
                });
              
    }
                    
}
