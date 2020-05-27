package com.mycompany.myapp.gui;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Menu;
import com.mycompany.myapp.services.ServiceMenu;
import java.io.IOException;
import java.util.ArrayList;

public class ListMenuForm extends Form{
 Form current;
 Resources theme;
 EncodedImage encImg;
 ImageViewer imgV;
 Image videImg;
 public Menu e;
 //********************************************
 public Button btnrech;
  public  TextField rtitre;
  public  Label lb;
  public  Button btnaff;
//*********************************************
 

    public ListMenuForm(Form previous) throws IOException {
        
        
        
        
        
        Image rech_icon = Image.createImage("/load.png");
        rech_icon.scaled(20, 20);

         btnrech=new Button("Chercher");
         rtitre = new TextField("","Plat");
         lb = new Label("");
         Image back;
       
         back = Image.createImage("/logo.png");
         back.scaled(1000, 1000);
             
             
       
           //btnaff=new Button("show list");
         
         
        add(rtitre);    
        add(btnrech);
       
          // f.add(btnaff);
        btnrech.addActionListener((e)->{
        //Recherche r=new Recherche();
        //r.getR().show();
        if(rtitre.getText().equalsIgnoreCase("") ){
             Dialog.show("alert","Entrer Le non D'un Plat !!", "ok", null);
                 ;}
        else{
        ServiceMenu ser=new ServiceMenu();
        lb.setText(ser.Recherche(rtitre.getText()).toString());
        }});
       add(lb);
    
        
        
        
        
        
        setTitle("Liste Des Menus");
    ArrayList<Menu> data = new ArrayList<Menu>();
    ServiceMenu v = new ServiceMenu();
    data = v.getAllTasks(); 
    Label titre = new Label("Liste Des Menus : ");
    Container cnt = new Container(BoxLayout.y());

                    
for(int i=0; i<data.size(); i++){ 
Label l1 = new Label(data.get(i).getItems());
Label l2 = new Label(data.get(i).getPrix());
//Label l3 = new Label(data.get(i).getPhoto());
    Image img;
        try {
            
         encImg = EncodedImage.create("/images.jfif");
        } catch (IOException ex) {
            
        }
                imgV = new ImageViewer();
                String url = "http://localhost/symfony-api/web/menu"; //hedha url dossier elli yetzedou fih les images f symfony
                System.out.println(url);
                img=URLImage.createToStorage(encImg, url,url, URLImage.RESIZE_SCALE);
                 imgV = new ImageViewer(img);
              
cnt.add(l1);
cnt.add(l2); 
cnt.add(imgV);

}  
    
add(cnt);
    
 
 Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
 FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
//getToolbar().addCommandToLeftBar("Left", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToOverflowMenu("Overflow", icon, (e) ->  new ElevesRestauForm(current).show());
//getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));

getToolbar().addMaterialCommandToLeftBar("Retour",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 HomeForm h = new HomeForm();
       h.getHome().show();
            }
        });
    }    

    ListMenuForm() {
    }

}
