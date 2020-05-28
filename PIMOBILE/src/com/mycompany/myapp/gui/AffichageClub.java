/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;







import com.codename1.components.FloatingActionButton;
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
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
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
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.event;
import com.mycompany.myapp.entities.participation;
import com.mycompany.myapp.services.ServiceClub;
import com.mycompany.myapp.services.ServiceEvent;
import com.mycompany.myapp.services.ServiceParticipation;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Map;





/**
 *
 * @author ASUS
 */
public class AffichageClub extends SideMenuBaseForm {
    
    Form f;
    ImageViewer ip;
    List<Club> lse = new ArrayList();
        ArrayList<event> form;
   
    EncodedImage encImg;
    Image img;
    ImageViewer imgV;

     Image videImg;
       int [] tabColeur = {0xd997f1,0x5ae29d,0x4dc2ff} ;
  private static int idCat = -1;

    public AffichageClub() throws IOException {
     Resources theme = UIManager.initFirstTheme("/theme");
     
     f = new Form(" Liste des clubs ", BoxLayout.y());
      
        Toolbar tb1 = getToolbar();
        //tb1.setTitleCentered(false); 
          
        Toolbar.setGlobalToolbar(true);
        Toolbar.setCenteredDefault(false);
        Image profilePic = theme.getImage("user-picture.jpg");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
             
       
        //menu
        Button menuButton = new Button(""); 
        Button back = new Button("");
        menuButton.setUIID("Title");
        menuButton.setUIID("back");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        //FontImage.setMaterialIcon(back, FontImage.MATERIAL_BACKSPACE);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
       // back.addActionListener(e -> {
          
        
        
         Container remainingTasks = BoxLayout.encloseY(
                        new Label("12", "CenterTitle"),
                        new Label("Nombre des clubs", "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label("Nombre des évenements", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        
            Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                           FlowLayout.encloseIn(back),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("MySchool", "Title"),
                                    new Label("Se for1mer autrement", "SubTitle")
                                    )  
                                ).add(BorderLayout.WEST, profilePicLabel),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks
                        )
                );
            
                          
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
         
         
  

     
    
      

        //===================================================================================================
        Slider slider = new Slider();
        slider.setPreferredSize(new Dimension(256, 2));
        f.add(slider);
        //--------------------------------ToolBar---------------
       
         
     //   Toolbar tb = f.getToolbar();
        
      
        lse = new ServiceClub().getAllTasks();
          FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        for (int i = 0; i < lse.size(); i++) {
            int id = lse.get(i).getId();
            addItem(lse.get(i));
             
              // addButtonBottom (arrowDown, "Finish landing page concept", 0xd997f1, true);
            String nomclub=lse.get(i).getNom_club().toString();
            // addButtonBottom(arrowDown, lse.get(i).getNom_club(), tabColeur[i], true);
             //String dateevent = lse.get(i).getDateEvent().toString();
            String description = lse.get(i).getDescription().toString();
//            String HeureEvent = lse.get(i).getHeureEvent().toString();
                
              // f.add(arrowDown);
                 
           // Button participer = new Button("Détails");
            // Button nonparticiper = new Button("annuler participation");
             //f.add(nonparticiper);
            //nonparticiper.setVisible(false);
          
              
        
       
            Label lNote = new Label("Note : 5");
                Container cNote = new Container(BoxLayout.y());
                cNote.add(lNote);
                Slider starRank = new Slider();
                starRank.setIncrements(1);
                starRank.setMaxValue(10);
                starRank.setMinValue(0);
                starRank.setProgress(5);
                starRank.setEnabled(true);
                starRank.setEditable(true);
                cNote.add(starRank);
                Font fnt = Font.createSystemFont(3, 5, 7);
                Style s = new Style(0xffff33, 0, fnt, (byte)0);
                Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                s.setOpacity(100);
                s.setFgColor(0);
                Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
                initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
                initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
                initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
                initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
                starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
                f.add((cNote));
       Button btnValider = new Button("Valider");
                this.add(btnValider);

                starRank.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {

                    lNote.setText("Note : "+starRank.getProgress());
                    }

                });
       
             // f.add(participer);
           // TextField comment=new TextField(null,"add feed_back");
            //f.add(comment);
           // Button addfeed = new Button("Détails");
           // f.add(addfeed);
            //participer.addActionListener(new ActionListener() {
               
        }
    }

    AffichageClub(Resources theme) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
       // finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    

    public void addItem(Club e) {

       
                // img = img.scaled(10, 10);
                          

               // imgV.setImage(img);
            
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label l1 = new Label("Nom Club:"+e.getNom_club());
        Label l2 = new Label("Description:"+e.getDescription());
        Label l = new Label("effectif:" +e.getEffectif());
        Label l3 = new Label("Domaine:" +e.getDomaine());
        
        //Label id1 = new Label(""+e.getIdevent());
        //Button participer = new Button("Participate");
        /*l1.addPointerPressedListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
        DetailEvenement de = new DetailEvenement(user, e, f);
        de.getF().show();
        }
        });*/
        
       /// c1.add(ip);
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

    @Override
    protected void showOtherForm(Resources res) {
       
    }

    
      private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBgTransparency(0);}


    

  
 
    
}


