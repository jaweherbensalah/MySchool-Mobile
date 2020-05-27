package com.mycompany.myapp.entities;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Display;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Transport {
     private String montant;
    private Date date_inscription;
   private Date fin_inscription;
    private int id;

    
    public Transport( int id ,String montant, Date date_inscription,Date fin_inscription) {
        this.id=id;
        this.montant = montant;
        this.date_inscription = this.date_inscription;
        this.fin_inscription=this.fin_inscription;
    }
      public Transport(String montant, Date date_inscription,Date fin_inscription) {
        this.montant = montant;
        this.date_inscription = this.date_inscription;
        this.fin_inscription=this.fin_inscription;
    }
    public Transport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public Date getFin_inscription() {
        return fin_inscription;
    }

    public void setFin_inscription(Date fin_inscription) {
        this.fin_inscription = fin_inscription;
    }

    //--------------------------------------------------------

    @Override
    public String toString() {
        return "Transport{" + "montant=" + montant + ", date_inscription=" + date_inscription + ", fin_inscription=" + fin_inscription + ", id=" + id + '}';
    }
    
}

