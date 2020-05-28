/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class Club {
    private int id;
    private String nom_club;
    private String description;
    private int effectif;
    private String domaine;

    public Club(int id, String nom_club, String description, int effectif, String domaine) {
        this.id = id;
        this.nom_club = nom_club;
        this.description = description;
        this.effectif = effectif;
        this.domaine = domaine;
    }

    public Club(String nom_club, String description, int effectif, String domaine) {
        this.nom_club = nom_club;
        this.description = description;
        this.effectif = effectif;
        this.domaine = domaine;
    }

    public Club() {
    }

    public Club(String nom_club, int effectif) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_club() {
        return nom_club;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", nom_club=" + nom_club + ", description=" + description + ", effectif=" + effectif + ", domaine=" + domaine + '}';
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
    
   
}
