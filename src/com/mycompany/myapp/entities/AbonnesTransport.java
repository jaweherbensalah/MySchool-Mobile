
package com.mycompany.myapp.entities;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.ui.Display;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbonnesTransport {
    private int id;
    private String email , solde,username ,password,duree_abonnement;
    
    public AbonnesTransport( int id ,String email,String username
            ,String solde,String password, String duree_abonnement) {
        this.id=id;
        this.email = email;
        this.username = username;
        this.solde=solde;
        this.password=password;
        this.duree_abonnement=duree_abonnement;
    }
     public AbonnesTransport( String email,String username
                       ,String password, String duree_abonnement) {

        this.email = email;
        this.username = username;
        this.password=password;
        this.duree_abonnement=duree_abonnement;
    }


    public AbonnesTransport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDuree_abonnement() {
        return duree_abonnement;
    }

    public void setDuree_abonnement(String duree_abonnement) {
        this.duree_abonnement = duree_abonnement;
    }

    @Override
    public String toString() {
        return "EleveRestau{" + "id=" + id + ", email=" + email + ", solde=" + solde + ", username=" + username + ", password=" + password + ", duree_abonnement=" + duree_abonnement + '}';
    }
    
}