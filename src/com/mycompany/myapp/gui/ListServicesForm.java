/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Service;
import com.mycompany.myapp.services.ServiceService;
import java.util.ArrayList;

public class ListServicesForm extends Form{

    public ListServicesForm(Form previous) {
        setTitle("Liste Services");
        
       ArrayList<Service> data = new ArrayList<Service>();
 ServiceService v = new ServiceService();
 Container cnt = new Container(BoxLayout.y());
 data = v.getAllTasks(); 
 Label service = new Label( "Les Services De La Scolarité :");   
 cnt.add(service); 
   
 for(int i=0; i<data.size(); i++){

Label l1 = new Label( data.get(i).getMenu_name());
Label l2 = new Label( data.get(i).getPrice());

cnt.add(l1);
cnt.add(l2);
    }
getToolbar().addMaterialCommandToLeftBar("Retour",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 HomeForm h = new HomeForm();
       h.getHome().show();
            }
        });

    }

}