/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class event {
    private int id;
    private String nom_event;
    private String description;
    private int placeDispo;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nom_event);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + this.placeDispo;
        hash = 67 * hash + Objects.hashCode(this.DateEvent);
        hash = 67 * hash + Objects.hashCode(this.HeureEvent);
        hash = 67 * hash + Objects.hashCode(this.image);
        hash = 67 * hash + this.club_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final event other = (event) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom_event, other.nom_event)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.placeDispo != other.placeDispo) {
            return false;
        }
        if (!Objects.equals(this.DateEvent, other.DateEvent)) {
            return false;
        }
        if (!Objects.equals(this.HeureEvent, other.HeureEvent)) {
            return false;
        }
        
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (this.club_id != other.club_id) {
            return false;
        }
        return true;
    }
    
      public int compareTo(event o) {
  return (this.DateEvent.compareTo(o.getDateEvent()) 
          );
    }

    public void setClub_id(String catVal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
