
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

public class Service {
    private int menu_id;
    private String menu_name , price;
    
    public Service( int menu_id ,String menu_name,String price) {
        this.menu_id=menu_id;
        this.menu_name = menu_name;
        this.price = price;
    }
       public Service( String menu_name,String price) {
        this.menu_name = menu_name;
        this.price = price;
    }


    public Service() {
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service{" + "menu_id=" + menu_id + ", menu_name=" + menu_name + ", price=" + price + '}';
    }

}
