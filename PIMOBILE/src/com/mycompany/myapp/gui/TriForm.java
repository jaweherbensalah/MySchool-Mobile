/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceEvent;

import java.io.IOException;

/**
 *
 * @author Hp
 */
public class TriForm extends SideMenuBaseForm {
     
        Form f;
    
    
     private Form current;
    private Resources theme;

    
    
     Button btnrech;
      TextField rtitre;
       SpanLabel lb;
        Button btnaff;
       
   public TriForm()throws IOException {
       
       Resources theme = UIManager.initFirstTheme("/theme");
     
     f = new Form(" Rechercher dans la liste des évènements ", BoxLayout.y());
      
        Toolbar tb1 = getToolbar();
        //tb1.setTitleCentered(false); 
          
        Toolbar.setGlobalToolbar(true);
        Toolbar.setCenteredDefault(false);
        Image profilePic = theme.getImage("user-picture.jpg");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
             
       
        //menu
        Button menuButton = new Button(""); 
        Button back = new Button("");
        menuButton.setUIID("Title");
        menuButton.setUIID("back");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        //FontImage.setMaterialIcon(back, FontImage.MATERIAL_BACKSPACE);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
       // back.addActionListener(e -> {
          
        
       
    

        
            Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                           FlowLayout.encloseIn(back),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("MySchool", "Title"),
                                    new Label("Se for1mer autrement", "SubTitle")
                                    )  
                                ).add(BorderLayout.WEST, profilePicLabel)
                );
            
                          
                    Toolbar tb = f.getToolbar();
                     tb.setTitleCentered(false);
          //   FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        //fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
       // fab.getAllStyles().setMargin(BOTTOM, titleCmp.getPreferredH() - fab.getPreferredH() / 2);
       // tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        tb.setTitleComponent(titleCmp);
           setupSideMenu(theme);
         //  menuButton.addActionListener(e -> getToolbar().openSideMenu());
//            f.getAllStyles().setMargin(BOTTOM, titleCmp.getPreferredH() - f.getPreferredH() / 2);
                //   f.setTitleComponent(f.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        
         
        
  

         
            
        
           
           
           
                  Image rech_icon = Image.createImage("/rech_icon.png");
        rech_icon.scaled(20, 20);

          btnrech=new Button("Search");
         rtitre = new TextField("","Nom");
          lb = new SpanLabel("");
        //  Image back;
       
          //   back = Image.createImage("/back-command.png");
            //back.scaled(1000, 1000);
             
               
       
           //btnaff=new Button("show list");
         
         
        f.add(rtitre);
        
         f.add(btnrech);
          f.add(lb);
          // f.add(btnaff);
         
            ////recherche/////////
        btnrech.addActionListener((e)->{
        //Recherche r=new Recherche();
        //r.getR().show();
        if(rtitre.getText().equalsIgnoreCase("") ){
            //
            
             Dialog.show("alert","Please, try to fill the text field title !!", "ok", null);
                 ;}
                         else{
        ServiceEvent ser=new ServiceEvent();
        lb.setText(ser.Recherche(rtitre.getText()).toString());
        }});
        
        
//         btnaff.addActionListener((e)->{
   // new AffichageProduct(this).show();        //a.getF().show();
       // });
       

    
        
   
   }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
