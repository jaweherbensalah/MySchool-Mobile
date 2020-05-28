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
public class participation {
      private int id;
      private int iduser;
      private int idevent;
      private Date createdat;
   

    public participation() {
    }

    public participation(int id, int iduser, int idevent, Date createdat) {
        this.id = id;
        this.iduser = iduser;
        this.idevent = idevent;
        this.createdat = createdat;
    }

    public participation(int iduser, int idevent, Date createdat) {
        this.iduser = iduser;
        this.idevent = idevent;
        this.createdat = createdat;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    @Override
    public String toString() {
        return "participation{" + "id=" + id + ", iduser=" + iduser + ", idevent=" + idevent + ", createdat=" + createdat + '}';
    }

  
      
}
