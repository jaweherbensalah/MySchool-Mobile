/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;

import com.mycompany.myapp.services.ServiceMenu;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Service;
import com.mycompany.myapp.services.ServiceService;
import java.io.IOException;
import java.util.ArrayList;

public class RechercheForm extends Form{
  
    public Form current;
    public Resources theme;
   public Button btnrech;
  public  TextField rtitre;
  public  Label lb;
  public  Button btnaff;
public RechercheForm(Form previous) throws IOException {
  
        Image rech_icon = Image.createImage("/load.png");
        rech_icon.scaled(20, 20);

          btnrech=new Button("Chercher");
         rtitre = new TextField("","Plat");
          lb = new Label("");
          Image back;
       
          back = Image.createImage("/logo.png");
            back.scaled(1000, 1000);
             
             
       
           //btnaff=new Button("show list");
         
         
        add(rtitre);    
        add(btnrech);
       
          // f.add(btnaff);
        btnrech.addActionListener((e)->{
        //Recherche r=new Recherche();
        //r.getR().show();
        if(rtitre.getText().equalsIgnoreCase("") ){
             Dialog.show("alert","Entrer Le non D'un Plat !!", "ok", null);
                 ;}
        else{
        ServiceMenu ser=new ServiceMenu();
        lb.setText(ser.Recherche(rtitre.getText()).toString());
        }});
       add(lb);
    
    
    
    
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
  }
}