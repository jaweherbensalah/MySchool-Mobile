/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.notifications.LocalNotification;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.regex.RE;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.services.ServiceClub;
import com.mycompany.myapp.utils.SMSAPI;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.BrowserWindow;
import java.io.IOException;
import java.util.Date;







/**
 *
 * @author Salma
 */
public class AddClub extends SideMenuBaseForm {
    
     Form f;

    com.codename1.io.File file;
    String fileName;

//    public static int idUser = User.user.getId();

    TextField tf_nom;
    TextField tf_desc;
    TextField tf_effectif;
    TextField tf_domaine;
   // Label lblemailtest;
    Label testVide;
      
         LocalNotification n;
    public AddClub() throws IOException {

        f = new Form("Ajouter Nouveau club.", new BoxLayout(BoxLayout.Y_AXIS));
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
                        
         
        
  

        ServiceClub es = new ServiceClub();
      
        
       
        
       
      
        


       
        ServiceClub prodServ = new ServiceClub();

        //-------------------------------Components-------------------------------------
        tf_nom = new TextField("", "nom_club");
        tf_desc = new TextField("", "Descrption");
       
        tf_effectif=new TextField("", "effectif");
        tf_domaine= new TextField("", "domaine");
         testVide = new Label();
           //Button fbButton = new Button("Facebook");
           Button btnChart=new Button("Chart");
      
      // lblemailtest.getAllStyles().setFgColor(0xe00b0b);
   
        
        f.add(tf_nom);
        f.add(tf_desc);
       
        f.add(tf_effectif);
        f.add(tf_domaine);
         f.add(testVide);
        // f.add(fbButton);
         
//      ComboBox Categorie
      
 
        btnChart.addActionListener(e-> new ChartForm(f).show());
      

            

        
        Button add = new Button("Ajouter");
      
         n = new LocalNotification();
       
     Container voca=new Container(BoxLayout.y());
       
     Label ll=new Label("Si vous avez des remarques les enregistrer... ");
        
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_MIC, s);
        Button voice= new Button(icon);
        voice.setUIID("LoginButton");
        voca.addAll(ll,voice);
        
        FileSystemStorage fs = FileSystemStorage.getInstance();
        String recordings = fs.getAppHomePath() + "recording/";
        fs.mkdir(recordings);
        try {
            for (String file : fs.listFiles(recordings)) {
                MultiButton mb = new MultiButton(file.substring(file.lastIndexOf("/") + 1));
                mb.addActionListener((e) -> {
                    try {
                        Media m = MediaManager.createMedia(recordings + file, false);
                        m.play();
                    } catch (Throwable err) {
                      //  Log.e(err);
                    }
                });
              
               voca.add(mb);        
            }

            voice.addActionListener( (ev) -> {
                try {
                    String file = Capture.captureAudio();
                    if (file != null) {
                        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMM-dd-kk-mm");
                        String fileName = sd.format(new Date());
                        String filePath = recordings + fileName;
                        Util.copy(fs.openInputStream(file), fs.openOutputStream(filePath));
                        MultiButton mb = new MultiButton(fileName);
                        mb.addActionListener((e) -> {
                            try {
                                Media m = MediaManager.createMedia(filePath, false);
                                m.play();
                            } catch (IOException err) {
                          //      Log.e(err);
                            }
                        });
                        voca.add(mb);
                        voca.revalidate();
                    }
                } catch (IOException err) {
                   
                }
            });
        } catch (IOException err) {
          
        }
            f.add(add);
             f.add(btnChart);
     f.add(voca);
      


        //=======================================================================================
        //=====================================TRAITEMENT========================================
        
        add.addActionListener(new ActionListener() {
            private String textAttachmentUri;
            private String imageAttachmentUri;
            
            @Override
            public void actionPerformed(ActionEvent evt) {
               
     
          // if (controle_AntiNull() == false)
            //    {
              //      testVide.setText("Veuillez Remplir Tout Les Champs");
                //}
            //else {
                
                     
                try {  
                Club e = new Club();
               /// e.setCreateur(User.user.getUsername());
                //lblusernametest.setText("");
                e.setNom_club(tf_nom.getText());
                e.setDescription(tf_desc.getText());
                
                e.setDomaine(tf_domaine.getText());
               
                e.setEffectif(Integer.parseInt(tf_effectif.getText()));
                    if ( prodServ.addTask(e)) 
                        
             //  prodServ.addTask(e);
               //  System.out.println("ajout avec succés ");
                 {        SMSAPI sms = new SMSAPI("ajout  "+tf_nom.getText()+"  succés  ", "+21629284613");  
                      /*  Message m = new Message("<html>hi<body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
boolean success = m.sendMessageViaCloudSync("Codename One", "cirine.charrad@gmail.com", "Name Of User", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");
                  System.out.println("ajout avec succés ");*/
                       // Message m = new Message("Body of message");
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
//Display.getInstance().sendMessage(new String[] {"sirine.charrad@esprit.tn"}, "hello ", m);
                        Dialog.show("SUCCESS", "Nouveau club ajouté ", "OK", null);
                //   SMSAPI sms = new SMSAPI("marekkk  "+tfNom_Demande.getText()+"  mgalbaaa  ", "+21652272411");
              //   SMSAPI sms = new SMSAPI("nouveau event  ", "+21629851315");
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
      //  n.setAlertBody(.getString("NOTIF_BODY"));
//       n.setAlertSound("/notification_sound_juntos.mp3"); //file name must begin with notification_sound

          System.out.println("ok");
         Display.getInstance().scheduleLocalNotification(
                
              
                n,
                System.currentTimeMillis() + 10 * 10000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
        
               
               // List.getF().show();
                 }
             
                //prodServ.addTask(e);
               // System.out.println("ok");
                 //}
             } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "vérifier les champs", "OK", null);
                }


           }
        });
        //d(uploadCont);
        
    


         // fbButton.addActionListener(e->{
         //  String token="EAACAEBbvH2QBAJKXp9ZCa4FwStGlSrvWMvJlyZBfIzuyMwvx1XXZAlNUFUfLLkoLozsZCVYYd9mK28Qh7lbRzU2gG8L3mmkXktu5jUQzY0ZA9xwgkTzZBfsIOh3ExqMxBNAEU2ZAdHH2nRU98Faivo3Tfbpq1rCJkgYNDJIuc9tvgZDZD"; 
         //  FacebookClient facebookClient = new DefaultFacebookClient(token);
           // FacebookType response= facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", Name.getText()+", Nouvelle Annonce!! Rendez-Vous a "+adresse.getText()));
           // System.out.println("Partage avec succes");
        //});

    }

    //---------------------------------------------------------------------
    //                           CONTROLE DE SAISIE
    //---------------------------------------------------------------------
 

    private Boolean controle_AntiNull() {

        if (tf_nom.getText() != null
                && tf_desc.getText()!= null
                && tf_effectif.getText()!=  null
                && tf_domaine.getText() != null ) {
            System.out.println("temchi");

            return true;
        }

        return false;
    }
  private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        RE pat = new RE(emailRegex);
        if (tf_desc.getText() == null) {
            System.out.println("emaaiill1111111");
            return false;
        } else if (pat.match(tf_desc.getText()) == false) {
            System.out.println("emaaii22222222");
            return false;

        } else {
            System.out.println("emaaiil333333333");
            return true;
        }

    }
  
  private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        series.add("Project " + ++k, value);
    }

    return series;
}
  public Form createPieChartForm() {
    // Generate the values
    double[] values = new double[]{12, 14, 11, 10, 19};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Budget", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    return f;
  }
    //---------------------------------------------------------------------
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    @Override
    protected void showOtherForm(Resources res) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

