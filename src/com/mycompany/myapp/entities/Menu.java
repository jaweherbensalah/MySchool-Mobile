
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

public class Menu {
    private int id;
    private String items , prix,photo;
    
    public Menu( int id ,String items,String prix,String photo) {
        this.id=id;
        this.items = items;
        this.prix = prix;
        this.photo=photo;
       
    }
    public Menu(String items,String prix,String photo) {
        this.items = items;
        this.prix = prix;
        this.photo=photo;
       
    }


    public Menu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Menu{" + "items=" + items + ", prix=" + prix + ", photo=" + photo + '}';
    }

//---------------------------------------------

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
   
}
