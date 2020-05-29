/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Asus
 */
public class Utilisateur {
    String username,matiere,classe,email,roles,password,classe2; 
  private static int splash=0;
  private static boolean Auth=false;

    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Utilisateur(String username, String matiere, String classe, String email,String classe2) {
        this.username = username;
        this.matiere = matiere;
        this.classe = classe;
         this.classe2 = classe2;
        this.email = email;
    }

    public Utilisateur() {
    }

    @Override
    public String toString() {
        return username + matiere + classe + email ;
    }

    public String getClasse2() {
        return classe2;
    }

    public void setClasse2(String classe2) {
        this.classe2 = classe2;
    }

    public static int getSplash() {
        return splash;
    }

    public static void setSplash(int splash) {
        Utilisateur.splash = splash;
    }

    public static boolean isAuth() {
        return Auth;
    }

    public static void setAuth(boolean Auth) {
        Utilisateur.Auth = Auth;
    }

    

  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
