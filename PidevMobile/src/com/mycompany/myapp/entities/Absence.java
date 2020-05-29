/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

public class Absence {
    String nom_enseignant; 
    String nom_eleve; 
    Date date; 
    int id; 
    String heure; 
    String classe; 
    String matier; 

    public Absence() {
   
    }

    public String getNom_enseignant() {
        return nom_enseignant;
    }

    public void setNom_enseignant(String nom_enseignant) {
        this.nom_enseignant = nom_enseignant;
    }

    public String getNom_eleve() {
        return nom_eleve;
    }

    public void setNom_eleve(String nom_eleve) {
        this.nom_eleve = nom_eleve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    @Override
    public String toString() {
        return  nom_enseignant + nom_eleve +  date +  id + heure +  classe + matier ;
    }

    public Absence( String heure,String matier, String nom_eleve, String nom_enseignant,String classe,Date date) {
        this.nom_enseignant = nom_enseignant;
        this.nom_eleve = nom_eleve;
        this.date = date;
        this.heure = heure;
        this.classe = classe;
        this.matier = matier;
    }

    public Absence(int id, String heure,  String matier, String nom_eleve, String nom_enseignant,  String classe,Date date) {
       this.id = id; 
        this.nom_enseignant = nom_enseignant;
        this.nom_eleve = nom_eleve;
        this.heure = heure;
        this.classe = classe;
        this.matier = matier;
        this.date = date; 
    }




    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new Date();
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMatier() {
        return matier;
    }

    public void setMatier(String matier) {
        this.matier = matier;
    }
    
    
}
