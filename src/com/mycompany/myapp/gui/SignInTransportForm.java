
package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.myapp.entities.EleveRestau;
import com.mycompany.myapp.services.ServiceEleveRestau;

import java.util.Map;
public class SignInTransportForm  extends Form{
  ComboBox<String> cp;
    public SignInTransportForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Inscription");
        setLayout(BoxLayout.y());
        
        TextField tfE = new TextField("","email");
        TextField tfU= new TextField("", "username");
        
        TextField tfP= new TextField("", "password");
     
                cp = new ComboBox<>();
   cp.addItem("Durée D'abonnement");
   cp.addItem("Trimestre");
    cp.addItem("Semestre");
     cp.addItem("Année");
          
      
      
        Button btnValider = new Button("Valider");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfE.getText().length()==0)||(tfU.getText().length()==0))
                    Dialog.show("Alert", "Remplir tous les champs", new Command("OK"));
                else
                {
                    try {
                        EleveRestau t = new EleveRestau( tfE.getText(),tfU.getText(),tfP.getText()
                                ,cp.getHint());
                        if( ServiceEleveRestau.getInstance().addTask(t))
                            Dialog.show("Success","Inscription confirmée ",new Command("OK"));
                        else
                            Dialog.show("Alert", "Ce nom d'utilisateur ou email existe déjà ", new Command("OK"));
                    } catch (Exception E ) {
                        Dialog.show("Alert", "Ce nom d'utilisateur ou email existe déjà", new Command("OK"));
                    }
                    
                }
            }
        });
        
        addAll(tfE,tfU,tfP,cp,btnValider);
        getToolbar().addMaterialCommandToLeftBar("Retour",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 HomeForm h = new HomeForm();
       h.getHome().show();
            }
        });
                
    }
    
    
}
