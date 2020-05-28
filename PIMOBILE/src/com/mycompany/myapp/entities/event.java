/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;


import java.util.Date;
/**
 *
 * @author ASUS
 */
public class event {
    private int id;
    private String nom_event;
    private String description;
    public int placeDispo;
    private Date DateEvent;
    private String HeureEvent;
    private String image;
    private int club_id;
 
  

    
     public event() {
    }

    public event(int id, String nom_event, String description, int placeDispo, Date DateEvent, String HeureEvent, String image, int club_id) {
        this.id = id;
        this.nom_event = nom_event;
        this.description = description;
        this.placeDispo = placeDispo;
        this.DateEvent = DateEvent;
        this.HeureEvent = HeureEvent;
        this.image = image;
        this.club_id = club_id;
    }

    public event(String nom_event, String description, int placeDispo, Date DateEvent, String HeureEvent, String image, int club_id) {
        this.nom_event = nom_event;
        this.description = description;
        this.placeDispo = placeDispo;
        this.DateEvent = DateEvent;
        this.HeureEvent = HeureEvent;
        this.image = image;
        this.club_id = club_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlaceDispo() {
        return placeDispo;
    }

    public void setPlaceDispo(int placeDispo) {
        this.placeDispo = placeDispo;
    }

    public Date getDateEvent() {
        return DateEvent;
    }

    public void setDateEvent(Date DateEvent) {
        this.DateEvent = DateEvent;
    }

    public String getHeureEvent() {
        return HeureEvent;
    }

    public void setHeureEvent(String HeureEvent) {
        this.HeureEvent = HeureEvent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", nom_event=" + nom_event + ", description=" + description + ", placeDispo=" + placeDispo + ", DateEvent=" + DateEvent + ", HeureEvent=" + HeureEvent + ", image=" + image + ", club_id=" + club_id + '}';
    }

    
 public String getNcat(int i)
    {
    if (i==1)
        return "Nom";
    else if (i==2)
        return "PlaceDispo";
    else if (i==3)
        return "Dtae";
    else
        return "Erreur";
    }
   
}
