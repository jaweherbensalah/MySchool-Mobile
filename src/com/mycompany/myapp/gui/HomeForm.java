/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import java.util.HashMap;
import java.util.Map;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;




public class HomeForm  extends SideMenuBaseForm {
    Form home = new Form("Espace Elève ");
   public Form current;
   public   Resources theme;
  


    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
    }
    public HomeForm() {
                
        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);
        Log.bindCrashProtection(true);
         Image bg = theme.getImage("livres.jpg");
           bg.scaled(10, 20);

    Container cn = BorderLayout.center(new Label(bg));
    cn.add(BorderLayout.SOUTH, new Label("Sevices De La Scolarité :"));
    home.add(cn);
  
       // current = this; //Récupération de l'interface(Form) en cours
        home.setTitle("Home");
        home.setLayout(BoxLayout.y());
//********************************************************
   
      
       
       
        Button btnAddMenu = new Button("ajouter un menu ");
        Button btnListMenu = new Button("Liste des menus");
        Button btnListServices = new Button("Liste des services");
         Button btnAddService = new Button("Ajouter des services");
        Button btnSinginRestau = new Button("Inscription Au Restaurant");
        Button btnListeTransport = new Button("Liste des Moyens Transport ");
        Button btnSinginTransport = new Button("Inscription Au Transport");
        Button bntListEleveRestau = new Button("Abonnés Au Restaurant");
        Button bntListAbonnesTransport = new Button("Abonnés Au Transport");
        Button bntStatistiques = new Button("Statistiques");
        Button btnRecherche = new Button("Recherche");

     home. addAll(btnSinginRestau,btnSinginTransport, bntStatistiques);
        
        
        btnAddMenu.addActionListener(e -> new AddMenuForm(current).show());
        btnListMenu.addActionListener(e -> {
            try {
                new ListMenuForm(current).show();
            } catch (IOException ex) {
            }
        });
        btnListServices.addActionListener(e -> new ListServicesForm(current).show());
        btnAddService.addActionListener(e -> new AddServiceForm(current).show());
        btnSinginRestau.addActionListener(e -> new SignInRestauForm(current).show());
        btnListeTransport.addActionListener(e -> new ListTransportForm(current).show());
        btnSinginTransport.addActionListener(e -> new SignInTransportForm(current).show());
        bntListEleveRestau.addActionListener(e -> new ElevesRestauForm(current).show());
        bntListAbonnesTransport.addActionListener(e -> new AbonnesTransportForm(current).show());
       bntStatistiques.addActionListener(e -> new StatistiquesForm(current).show());
       btnRecherche.addActionListener(e -> {
            try {
                new RechercheForm(current).show();
            } catch (IOException ex) {
            }
        });


        
 
      

        
        Toolbar tb = home.getToolbar();
        Image icon = theme.getImage("logo.png");
        icon.scaled(20, 20);
        Container topBar = BorderLayout.east(new Label(icon));
        topBar.add(BorderLayout.SOUTH, new Label("Espace Elève :"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);
        
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {});
        tb.addMaterialCommandToSideMenu("Liste Services", FontImage.MATERIAL_WEB, e -> {  
            new ListServicesForm(current).show();});
        tb.addMaterialCommandToSideMenu("Liste Des Menus", FontImage.MATERIAL_WEB, (ActionEvent e) -> {  
            try {
                new ListMenuForm(current).show();
            } catch (IOException ex) {
            }
});
        tb.addMaterialCommandToSideMenu("Liste Moyen Transport", FontImage.MATERIAL_WEB, e -> {  
            new ListTransportForm(current).show();});
        tb.addMaterialCommandToSideMenu("Inscription Au Restaurant", FontImage.MATERIAL_FASTFOOD, e -> {  
            new SignInRestauForm(current).show();});
        tb.addMaterialCommandToSideMenu("Inscription Au Transport", FontImage.MATERIAL_BUS_ALERT,  e -> {  
            new SignInTransportForm(current).show();});
        tb.addMaterialCommandToSideMenu("Trouvez-nous!",FontImage.MATERIAL_ADD_LINK,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 MapForm h = new MapForm();
                 h.getF().show();}});
       tb.addMaterialCommandToSideMenu("Statistiques ", FontImage.MATERIAL_STAR_HALF,  e -> {  
            new StatistiquesForm(current).show();});
       
      /*  tb.addMaterialCommandToSideMenu("Contact",FontImage.MATERIAL_CONTACTS,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Contact h = new Contact();
        h.Contact();
            }
        });*/
        
          
        
    
    
    
    
    }
//**************************
    
     public Form getHome() {
        return home;
    }
    public void setHome(Form home) {
        this.home = home;  
}

    @Override
    protected void showOtherForm(Resources res) {
    }
}