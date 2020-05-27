/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Transport;
import com.mycompany.myapp.services.ServiceTransport;
import java.io.IOException;
import java.util.ArrayList;

public class ListTransportForm extends Form{

     public Transport e;

    public ListTransportForm(Form previous) {
        setTitle(" Moyens Transport");
        ArrayList<Transport> data = new ArrayList<Transport>();
    ServiceTransport v = new ServiceTransport();
    data = v.getAllTransports(); 
    
    Label titre = new Label( "Les Moyens De Transport :");   
    Container cnt = new Container(BoxLayout.y());
    cnt.add(titre);
    for(int i=0; i<data.size(); i++){ 

Picker date_inscription = new Picker();
date_inscription.setType(Display.PICKER_TYPE_DATE);
date_inscription.setDate(data.get(i).getDate_inscription());
//***********************
Picker fin_inscription = new Picker();
fin_inscription.setType(Display.PICKER_TYPE_DATE);
fin_inscription.setDate(data.get(i).getFin_inscription());
//**********************************
Label montant = new Label(data.get(i).getMontant());


//************************
               
cnt.add(date_inscription);
cnt.add(fin_inscription);
cnt.add(montant);

} 
 add(cnt);

    
 Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
 FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
//getToolbar().addCommandToLeftBar("Left", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToOverflowMenu("Overflow", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));
 getToolbar().addMaterialCommandToLeftBar("Retour",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 HomeForm h = new HomeForm();
       h.getHome().show();
            }
        });
    }
     

    
    
}

