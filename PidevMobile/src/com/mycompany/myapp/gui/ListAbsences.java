/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Absence;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceAbsence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.gui.AjoutAbsence;

import com.mycompany.myapp.services.ServiceUtilisateur;
import java.io.IOException;

public class ListAbsences extends Form {
     final Resources res;
        private Form c,ff;
          private Form LoginF;
           private Resources theme;
     String user = UserForm_1.nom; 
         String classe = homeEnseignants.t1;
                     EncodedImage enc,enc1;
                    ArrayList<Absence> data = new ArrayList<Absence>();
                    ArrayList<Utilisateur> data1 = new ArrayList<Utilisateur>();
       Absence taa = new Absence();
            public static Absence pp; 
            EncodedImage encc; 
    Image imgs; 
    public ListAbsences(Resources res) {
              this.res = UIManager.initFirstTheme("/theme");

      
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

        
         
   Container c3 = new Container(BoxLayout.y()); 
            Label l = new Label("les absences ");
            l.setUIID("colour");        

        l.getStyle().setPaddingLeft(18);
         c3.add(l);
       
ServiceAbsence seabs = new ServiceAbsence();
  data = seabs.getAllAbsence(classe,user); 
              for(int i=0; i<data.size(); i++){ 
                   Label l5 = new Label(data.get(i).getNom_eleve());
                   l5.setUIID("labels");
                 Label l22 = new Label(data.get(i).getHeure());
                 l22.setUIID("labels");
               Label l23 = new Label(data.get(i).getMatier());
               l23.setUIID("labels");
                           //   Label vw = new Label("********************");
                          int uu = data.get(i).getId();
      
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(data.get(i).getDate());
            Label l6 = new Label("Date :" + dateString);
            l6.setUIID("labels");
            Button b = new Button("Modifier");
            b.setUIID("colour");
             Button bb = new Button("Supprimer");
             bb.setUIID("colour");
		     b.getAllStyles().setBorder(Border.createEmpty());
		     b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
                       
                     //Label l7 = new Label("Date : "+data.get(i).getDate());
c3.add(l5);
c3.add(l23);
c3.add(l22);
//c2.add(vw);
c3.add(l6);
c3.add(b);
c3.add(bb);
 //c2.add(l7)
        System.out.println("fin");
		    b.addActionListener(new ActionListener() {
                       @Override
  public void actionPerformed(ActionEvent evt){ 
               Form f2 = new Form(BoxLayout.y());
 f2.setTitle("My School");
 f2.setUIID("backgroundd");
           f2.getToolbar().addCommandToOverflowMenu(user,null,null);
                           getToolbar().addCommandToOverflowMenu("Exit",
                                   null, ev->{Display.getInstance().exitApplication();}); 
                                     setTitle("My School");
           getToolbar().addCommandToOverflowMenu(user,null,null);
                           getToolbar().addCommandToOverflowMenu("Exit",
                                   null, ev->{Display.getInstance().exitApplication();});
                          
    TextField  matieref = new TextField(l23.getText());
    matieref.setUIID("colour");
              //     ComboBox<String> elevef =  new ComboBox<>(); 
               Label elevef = new Label(); 
                      elevef.setText(l5.getText()); 
                             ComboBox<String> tfHeure = new ComboBox<>(); 
                  tfHeure.setSelectedItem(l22.getText());
       ServiceUtilisateur s = new ServiceUtilisateur();
       
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
       
       
               Button  btnMod = new Button("Modifier");
               btnMod.setUIID("colour");
               btnMod.getStyle().setPaddingLeft(18);
      Button  btnAnnuler=new Button("Annuler");
       
      btnAnnuler.setUIID("colour");
      btnAnnuler.getStyle().setPaddingLeft(18);
        f2.add(tfHeure);
        f2.add(matieref);
        f2.add(elevef);
          f2.add(btnMod);
        f2.add(btnAnnuler);                     
                        f2.show();              
                         btnMod.addActionListener(new ActionListener() {             
                                         @Override
                                         public void actionPerformed(ActionEvent evt) {
                                              Date cu = new Date(); 
                            
            ServiceAbsence ser = new ServiceAbsence();
            Absence w = new Absence();
            w.setDate(cu);
            w.setHeure(tfHeure.getSelectedItem());
            w.setMatier(matieref.getText());
            w.setNom_eleve(l5.getText());
            w.setClasse(classe);
             w.setNom_enseignant(user); 
             w.setId(uu);
                 if(ser.ModifierAbsence(w) == true ) {
                            Dialog.show("Success","Absence est modifiée  ",new Command("OK"));
                            LoginF = new ListAbsences(theme);
                                            LoginF.show(); 
                 } 
                                         }
                                     
                        
                         });
                         btnAnnuler.addActionListener(new ActionListener() {   
                                         @Override
                                         public void actionPerformed(ActionEvent evt) {
                                             LoginF = new ListAbsences(theme);
                                            LoginF.show(); 
                                         }
                         
                         
                         
                         });
                       }
                       });
                    
                      bb.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           ServiceAbsence ser = new ServiceAbsence();
                          Absence w = new Absence();
           
             w.setId(uu);
                 if(ser.SupprimerAbsence(w) == true ) {
                            Dialog.show("Success","Absence est supprimer ",new Command("OK"));
                             LoginF = new ListAbsences(theme);
                                            LoginF.show(); 
                 }  
                       }
                          
                      }); 

               }
                this.add(c3);
                 //this.add(c2);
                   Container c4 = new Container(BoxLayout.xCenter());
                    Button login = new Button();
                   login.setText("Ajouter absence ");
                 
                   login.setUIID("colour");
                     login.getStyle().setPaddingLeft(18);
                c4.add(login);
                 login.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent evt) {
       ff = new AjoutAbsence(theme);
    ff.show();  
            }
                 });
                  this.add(c4);
    }
    
}
