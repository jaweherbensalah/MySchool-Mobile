/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;
import com.mycompany.myapp.gui.homeEleve;
import com.mycompany.myapp.gui.homeEnseignants;

import com.codename1.components.ScaleImageLabel;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.facebook.User;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Tiburcio
 */
public class UserForm_1 extends com.codename1.ui.Form {
 Resources res; 
 EncodedImage enc,enc1; 
    Image imgs;
    TextField nomTxt;
     Form f;
 Form ff,c; 
 
       public static  URLImage background;
              public static String nom ;
               ArrayList<Utilisateur> data = new ArrayList<Utilisateur>();
    public UserForm_1() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public UserForm_1(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        showFormElements();
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("UserForm");
        setName("UserForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!

    private void showFormElements() {
        
       
        this.setScrollable(false);
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        showData(this);
    }

    private void showData(UserForm_1 form) {
        String token = (String) Storage.getInstance().readObject("token");
        if(token == null || token.equals("")){
            showIfNotLoggedIn();
        } else {
            showIfLoggedIn(form);
        }
    }

    private void showIfNotLoggedIn() {
        getContentPane().removeAll();
            Storage.getInstance().writeObject("token", "");
           
        
          res   = UIManager.initFirstTheme("/theme");
         setUIID("LoginForm");
         setTitle("My School ");
        Container welcome = FlowLayout.encloseCenter(
                new Label(),
                new Label()
        );
        
        getTitleArea().setUIID("Container");
       /* 
        Image profilePic = res.getImage("user-picture.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask()); */
        
        TextField login = new TextField("", "Login", 20, TextField.EMAILADDR) ;
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        login.getAllStyles().setMarginTop(18);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("LOGIN");
        loginButton.getAllStyles().setBgColor(0x008072);
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
            Toolbar.setGlobalToolbar(false);      
            Toolbar.setGlobalToolbar(true);
            if(login.getText().length()!=0 && password.getText().length()!=0) {
            nom = login.getText();
                     
                        String pwd = password.getText();
                        System.out.println(nom);
                   ServiceUtilisateur s = new ServiceUtilisateur();
                       data = s.getAllNiveau(nom);
                     System.out.println(data);
                     if (data == null) {
                     System.out.println("data est null ");      
                      //   msg.setVisible(true);   
                       }
                       else {
                          
                          for(int i=0; i<data.size(); i++){
                            System.out.println(data.get(i).getRoles());
              if(data.get(i).getRoles().equals("a:1:{i:0;s:10:\"ROLE_ELEVE\";}")) {
                                    System.out.println("lllllllllllll");
                                    System.out.println("espace eleve ");
                                  
                                    ff = new homeEleve(res);
                                    ff.show(); 
                               }
          else  if(data.get(i).getRoles().equals("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {
                                    System.out.println("espace enseignants ");
                                  
                                    ff = new homeEnseignants(res);
                                    ff.show();
                                    //  msg.setVisible(true);
                                    //    msg.setText("espace enseignants");
                                } 
                                else
                                {
                                     System.out.println("vous n estes pas inscrit "); 
                                }
                            }
                        } 
                
            } else if(login.getText().length()==0 || password.getText().length()==0) 
            {
        Dialog.show("Alert", "remplir les champs ", new Command("OK"));    
            } 
        });
    
        Button createNewAccount = new Button("Se connecter via facebook"); 
        createNewAccount.setUIID("CreateNewAccountButton");
         createNewAccount.addActionListener((e) -> {
                facebookLogin(this);
            });
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
  
        Container by = BoxLayout.encloseY(
                welcome,
                //profilePicLabel,
                spaceLabel,
                BorderLayout.center(login),
                       
                BorderLayout.center(password),
                loginButton,
                createNewAccount
        );
        add(by);
//        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(false);
           
        
             
         
                
          
    }

    private void showIfLoggedIn(UserForm_1 form) {
        String token = (String) Storage.getInstance().readObject("token");
        FaceBookAccess.setToken(token);
            final User me = new User();
            try {
                FaceBookAccess.getInstance().getUser("me", me, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String miNombre = "";//me.getName();
                        
                        getContentPane().removeAll();
                        
                      add(new Label(miNombre));                        
                        Button buttonLogout = new Button("Logout");
                        buttonLogout.addActionListener((e) -> {
                            facebookLogout();
                            showIfNotLoggedIn();
                        });
            
                        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
                         background = URLImage.createToStorage(placeholder, "fbuser.jpg",
                                "https://graph.facebook.com/v2.11/me/picture?access_token=" + token);
                        background.fetch();
                        ScaleImageLabel myPic = new ScaleImageLabel();
                        myPic.setIcon(background);
                       nom = "dali";  
              ff = new profilFacebook(res);
                                    ff.show(); 
 
                        add(myPic);
                       add(buttonLogout);
                       
                       
                       revalidate();
                        //form.show();
                    }

                    
                });
            } catch (IOException ex) {
                ex.printStackTrace();
                showIfNotLoggedIn();
            }
    }

    public void facebookLogout() {
        String clientId = "PON TU FACEBOOK APP ID";
        String redirectURI = "http://localhost/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "PON TU CONTRASEÑA DE TU APP DE FACEBOOK";
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
        fb.doLogout();
        Storage.getInstance().writeObject("token", "");
        showIfNotLoggedIn();
    }
    
    private void facebookLogin(UserForm_1 form) {
        //use your own facebook app identifiers here   
        //These are used for the Oauth2 web login process on the Simulator.
        String clientId = "242893170481930";
        String redirectURI = "http://localhost/"; //Una URI cualquiera. Si la pones en tu equipo debes crear un Servidor Web. Yo usé XAMPP
        String clientSecret = "1c223b76f2e26947d1f299f685518e50";
        Login fb = FacebookConnect.getInstance();
        fb.setClientId(clientId);
        fb.setRedirectURI(redirectURI);
        fb.setClientSecret(clientSecret);
        //Sets a LoginCallback listener
        fb.setCallback(new LoginCallback() {
            @Override
            public void loginFailed(String errorMessage) {
                System.out.println("Échec de la connexion");
                Storage.getInstance().writeObject("token", "");
                showIfNotLoggedIn();
            }

            @Override
            public void loginSuccessful() {
                System.out.println("Funcionó el login");
                String token = fb.getAccessToken().getToken();
                Storage.getInstance().writeObject("token", token);
                showIfLoggedIn(form);
            }
            
        });
        //trigger the login if not already logged in
        if(!fb.isUserLoggedIn()){
            fb.doLogin();
        }else{
            //get the token and now you can query the facebook API
            String token = fb.getAccessToken().getToken();
            Storage.getInstance().writeObject("token", token);
            showIfLoggedIn(form);
        }
    }
}
