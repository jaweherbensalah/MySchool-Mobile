/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    private Form f;
    public HomeForm() {
        
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Task");
        Button btnListTasks = new Button("List Tasks");
        Button btnAffichageEvent = new Button("affiche Event");
        
       //btnAddTask.addActionListener(e-> new AffichageEvent(current).show());
       // btnListTasks.addActionListener(e-> new ListTasksForm(current).show());
        addAll(btnAddTask,btnListTasks,btnAffichageEvent);
       // btnAffichageEvent.addActionListener((ActionEvent e)-> new AffichageEvent(current).show());
        
        
    }

    public Form getF() {
    Form f = null;
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
}
