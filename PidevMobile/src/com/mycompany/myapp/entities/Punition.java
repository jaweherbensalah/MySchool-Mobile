/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Punition {
   String eleve; 
   int id; 
   String type,classe; 
   Date date; 

    public Punition() {
    }

    public Punition(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Punition(String eleve, String type, String classe, Date date) {
        this.eleve = eleve;
        this.type = type;
        this.classe = classe;
        this.date = date;
    }

    @Override
    public String toString() {
        return id + type ;
    }

  

    public String getEleve() {
        return eleve;
    }

    public void setEleve(String eleve) {
        this.eleve = eleve;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
}
