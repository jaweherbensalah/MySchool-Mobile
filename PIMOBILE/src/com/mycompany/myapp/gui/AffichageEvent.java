/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;







import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
 
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.SelectionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.event;
import com.mycompany.myapp.entities.participation;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceParticipation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;






/**
 *
 * @author ASUS
 */
public class AffichageEvent extends SideMenuBaseForm  {
    
    Form f;
    ImageViewer ip;
    List<event> lse = new ArrayList();
        ArrayList<event> form;
   
    EncodedImage encImg;
    Image img;
    ImageViewer imgV;

     Image videImg;
  private static int idCat = -1;

    public AffichageEvent(Resources theme)   {
 
        f = new Form(" Liste des évenements ", BoxLayout.y());
          //-----------------------------RECHERCHES-----------------------------------------    
        //f.add(slider);
         Resources theme1 = UIManager.initFirstTheme("/theme");
        Toolbar tb1 = getToolbar();
        //tb1.setTitleCentered(false); 
          
        Toolbar.setGlobalToolbar(true);
        Toolbar.setCenteredDefault(false);
        Image profilePic = theme1.getImage("user-picture.jpg");
        Image mask = theme1.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        Button rech = new Button("rechercher par Nom ");
        
         //menu
        Button menuButton = new Button(""); 
        Button back = new Button("");
        menuButton.setUIID("Title");
        menuButton.setUIID("back");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
     //   FontImage.setMaterialIcon(back, FontImage.MATERIAL_BACKSPACE);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        

        
            Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                           FlowLayout.encloseIn(back),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("MySchool", "Title"),
                                    new Label("Se for1mer autrement", "SubTitle")
                                    )  
                                ).add(BorderLayout.WEST, profilePicLabel)                );
            
                          
                    Toolbar tb = f.getToolbar();
                     tb.setTitleCentered(false);
          //   FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        //fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
       // fab.getAllStyles().setMargin(BOTTOM, titleCmp.getPreferredH() - fab.getPreferredH() / 2);
       // tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        tb.setTitleComponent(titleCmp);
           setupSideMenu(theme);
         //  menuButton.addActionListener(e -> getToolbar().openSideMenu());
//            f.getAllStyles().setMargin(BOTTOM, titleCmp.getPreferredH() - f.getPreferredH() / 2);
                //   f.setTitleComponent(f.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        
         
        
        
        //============================================================================================
        //===================================RECHERCHE GLOBALE========================================
        ServiceEvent serv = new ServiceEvent();
       MultiButton b = new MultiButton("Filter par categorie");
          f.add(b);     
    b.addActionListener(e -> {
    Dialog d = new Dialog();
    f.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    String[] characters = { "All", "Nom", "PlaceDispo","Date"};
     for(int iter = 0 ; iter < characters.length ; iter++) {
            MultiButton mb = new MultiButton(characters[iter]);
            d.add(mb);
            mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
            int i;
            switch(mb.getTextLine1())
                {
                    case "Nom":
                        i=1;
                        break;
                    case "PlaceDispo":
                        i=2;
                        break;
                    case "Date":
                        i=3;
                        break;
                    default:
                        i=0;
                        break;
                }
                ArrayList<event> form1 = null;
                if (i!=0)
                { 
                form1 = serv.getCProducts();
                System.out.println("ok");
               
              AddTaskForm ap = new AddTaskForm();
                    ap.getF().show();
                
                }
               else
                {
                AffichageEvent add = null;
                add = new AffichageEvent(theme);
                    add.getF().show();
                }   
                
        });
    }
    d.showPopupDialog(b);
});
  
        
         
        Container list = new Container(new FlowLayout(Component.CENTER));
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        list.getAllStyles().setPaddingTop(size / 4);
              
            
      
             
      System.out.println(serv.getListEvenements().get(0));  
         
        ComboBox cb = new ComboBox(" ","NomEvent", "", "");
       f.add(cb);
       

           cb.addActionListener(new ActionListener() {

          
             @Override
            public void actionPerformed(ActionEvent evt) {
                Form f2 = new Form("khawla", BoxLayout.y());
               if(cb.getSelectedItem() == "NomEvent") {
                   ConnectionRequest con = new ConnectionRequest();
                       con.setUrl("http://localhost/symfony-api/web/app_dev.php/event/tasks/tri");
                    System.out.println("trié"); 
                     con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                
                      Tri detail_form1;
                        try {
                            detail_form1 = new Tri();
                              detail_form1.getF().show();
                        } catch (IOException ex) {
                        }

                            System.out.println("Le produit  a été trié par succées.");
                            ToastBar.showMessage("liste des evenements trié",FontImage.MATERIAL_DONE);

                        }
                        //f2.add(BorderLayout.CENTER,cb);
                    });
                       f2.show();
                        con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                   // nonparticiper.setVisible(false);   
                     // f.refreshTheme();
                }
             //////  img.setImage(theme.getImage("nader.jpg"));
           //if(cb.getSelectedItem() == "Sana ben Fadhel")
             //  img.setImage(theme.getImage("sana.jpg"));
          // if(cb.getSelectedItem() == "Bassem Htira")
              // img.setImage(theme.getImage("bassem.jpg"));
           
         //  f2.add(BorderLayout.NORTH,img);
           
              
            
            
            }
            
            
        }
          );
     
    
      

    
        
  

     
    
      

        //===================================================================================================
      //  Slider slider = new Slider();
       
        //--------------------------------ToolBar---------------
        f.getToolbar().addCommandToOverflowMenu("Ajouter Club",videImg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    AddClub add=new AddClub();
                    add.getF().show();
                } catch (IOException ex) {
                    
                }
            }
        });
         f.getToolbar().addCommandToOverflowMenu("afficher Club",videImg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             

                AffichageClub add = null;
                try {
                    add = new AffichageClub();
                } catch (IOException ex) {
                   // Logger.getLogger(AffichageEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
                add.getF().show();
            }
        });
         
     //   Toolbar tb = f.getToolbar();
        
       
        lse = new ServiceEvent().getListEvenements();
        for (int i = 0; i < lse.size(); i++) {
            int id = lse.get(i).getId();
            addItem(lse.get(i));
            String nomevent=lse.get(i).getNom_event().toString();
             //String dateevent = lse.get(i).getDateEvent().toString();
            String description = lse.get(i).getDescription().toString();
//            String HeureEvent = lse.get(i).getHeureEvent().toString();
     
            Button participer = new Button("Participer");
            
             Button nonparticiper = new Button("annuler participation");
             f.add(nonparticiper);
            nonparticiper.setVisible(false);
            
            //f.add(participer);
           // TextField comment=new TextField(null,"add feed_back");
            //f.add(comment);
           // Button addfeed = new Button("Détails");
           // f.add(addfeed);
            participer.addActionListener(new ActionListener() {
                private String textAttachmentUri;
                private String imageAttachmentUri;
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //User p = new User(1,"Hnana", "Alaeddine", 12345678);
                   // ParticipationService es = new ParticipationService();
                   // float id = Float.parseFloat(lse.get(i).getIdevent());
                    
                   //es.ajouterPartEvenement(id, Authentification.PERSONNECONNECTEE.getId());
                     try {  
                
               /// e.setCreateur(User.user.getUsername());
                //lblusernametest.setText("");
                 // e.setNom_club(tf_nom.getText());
              
                // e.setQuantity_Product(1);
                //   e.setPaid(1);
                        
               
            
                 
                    //User p = new User(1,"Hnana", "Alaeddine", 12345678);
                  ServiceParticipation es = new ServiceParticipation();
                   // float id = Float.parseFloat(lse.get(i).getIdevent());
                   //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                    Map<String, Object> cratedat = null;
//                datep = (Map<String, Object>) obj.get("datedeparti");
//                Date londdatep = new Date((long) Float.parseFloat(datep.get("timestamp").toString()) * 1000);
//                SimpleDateFormat formatterp = new SimpleDateFormat("yyyy-MM-dd");
//                String dp = formatterp.format(londdatep);
//                p.setDateParti(londdatep);
//               
                           participation pa = new participation(2,12, (Date) cratedat);
                   
                      //  pa.setIdevent(lse.get(i));
                       // pa.setIdUser(User.user);
                        es.ParticipEvent(pa);
                        
                      
                  // es.ajouterPartEvenement
                       
//  
                                 nonparticiper.setVisible(true);
                                 participer.setVisible(false);
                            
                    
                   //}
             } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }


           }
            
        });
        //d(uploadCont);
        
       f.add(participer);
             nonparticiper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
            
                    
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://localhost/symfony-api/web/app_dev.php/event/event/supp/"+25);
                                  
           
                    
                            
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            
                            System.out.println("L'Evenement a été supprimé avec succées.");
                            ToastBar.showMessage("La participation est annule",FontImage.MATERIAL_DONE);

                        }
                    });
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    nonparticiper.setVisible(false);
                    participer.setVisible(true);
 
            }
        });
        }
        
        /*tb.addCommandToLeftBar("< Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                precedent.show();
            }
        });*/
    }

    private AffichageEvent(ArrayList<event> form) {
     
    }

    private AffichageEvent(String textLine1, ArrayList<event> form, Form f) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    

    public void addItem(event e) {

        Image imgs;
        try {
            // EncodedImage enc = EncodedImage.create("/load.png");
            //  imgs = URLImage.createToStorage(enc, e.getAffiche(), url).scaled(400, 350);
            //ip = new ImageViewer(imgs);
            encImg = EncodedImage.create("/load.png");
        } catch (IOException ex) {
            
        }
                imgV = new ImageViewer();
                String url = "http://localhost/symfony-api/web/img/" + e.getImage();
                System.out.println(url);
                img=URLImage.createToStorage(encImg, url,url, URLImage.RESIZE_SCALE);
           // photoProfilViewer=new ImageViewer(img);
                //img = URLImage.createToStorage(encImg, e.getImage(), url);
               // img = img.scaled(125, 100);
               //  img = img.scaled(400, 400);
                 imgV = new ImageViewer(img);
                // img = img.scaled(10, 10);
                          

               // imgV.setImage(img);
       
                
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l1 = new Label("Nom Event:"+e.getNom_event());
        Label l2 = new Label("Description:"+e.getDescription());
        Label l = new Label("Nombre des places:" +e.getPlaceDispo());
        Label l3 = new Label("Heure:" +e.getDateEvent());
        
        //Label id1 = new Label(""+e.getIdevent());
        //Button participer = new Button("Participate");
        /*l1.addPointerPressedListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        DetailEvenement de = new DetailEvenement(user, e, f);
        de.getF().show();
        }
        });*/
          c1.add(imgV);
        //c1.add(ip);
        //c2.add(id1);
        c2.add(l1);
        c2.add(l2);
        c2.add(l);
        c2.add(l3);
        //c2.add(participer);
        //c2.add(participer);
        /* id1.addPointerPressedListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        Accueil a = new Accueil();
        a.getAcc().show();
        }
        });*/
        
        /*participer.addActionListener(new ActionListener() {
        @Ossverride
        public void actionPerformed(ActionEvent evt) {
        Accueil a = new Accueil();
        a.getAcc().show();
        //
        }
        });*/
        // c2.add(l4);
        c1.add(c2);
        c1.setLeadComponent(l1);
        f.add(c1);
        f.refreshTheme();
        /*participer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        User p = new User(1,"Hnana", "Alaeddine", 12345678);
        ParticipationService es = new ParticipationService();
        es.ajouterPartEvenement(e,p);
        
        }
        });*/
        
       
        
    }
    
    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    private void add(MultiButton b) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
private Map<String, Object> createListEntry(String name) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    return entry;
      }

    @Override
    protected void showOtherForm(Resources res) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      }
   

