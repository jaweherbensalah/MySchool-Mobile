/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.services.ServiceMenu;

/**
 *
 * @author 
 */
public class AddMenuForm extends Form{

    public AddMenuForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter un menu");
        setLayout(BoxLayout.y());
        
        TextField tfItems = new TextField("","plat");
        TextField tfPrix= new TextField("", "prix");
        TextField tfPhoto= new TextField("", "photo");
      
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfItems.getText().length()==0)||(tfPrix.getText().length()==0))
                    Dialog.show("Alert", "Remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        Menu t = new Menu( tfItems.getText(),tfPrix.getText(),tfPhoto.getText());
                        if( ServiceMenu.getInstance().addTask(t))
                            Dialog.show("Success","Menu ajouté",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfItems,tfPrix,tfPhoto,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
