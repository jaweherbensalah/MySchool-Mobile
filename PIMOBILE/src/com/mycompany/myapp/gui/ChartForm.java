/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.event;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Haddad
 */
public class ChartForm extends SideMenuBaseForm{
    
 Form f;
    ImageViewer ip;
    List<Club> lse = new ArrayList();
        ArrayList<event> form;
   
    EncodedImage encImg;
    Image img;
    ImageViewer imgV;

     Image videImg;
       int [] tabColeur = {0xd997f1,0x5ae29d,0x4dc2ff} ;
    public ChartForm(Form previous) {
        //Form previous
    Resources theme = UIManager.initFirstTheme("/theme");
     
     f = new Form(" Liste des clubs ", BoxLayout.y());
         setTitle("Chart");
    setLayout(BoxLayout.y());
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
          
        
             Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                           FlowLayout.encloseIn(back),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label("MySchool", "Title"),
                                    new Label("Se for1mer autrement", "SubTitle")
                                    )  
                                ).add(BorderLayout.WEST, profilePicLabel)
                        
                );
            
           Toolbar tb = f.getToolbar();
                     tb.setTitleCentered(false);
          //   FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        //fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
       // fab.getAllStyles().setMargin(BOTTOM, titleCmp.getPreferredH() - fab.getPreferredH() / 2);
       // tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        tb.setTitleComponent(titleCmp);
           setupSideMenu(theme);
        
         
    double[] values = new double[]{12, 14, 11};
        new Label("Statistiques", "CenterTitle");
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
    PieChart chart = new PieChart(buildCategoryDataset("Chart", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = this;
    f.add(c);
    f.show();
        
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
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

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        series.add("Chart " + ++k, value);
    }
    return series;
}

    @Override
    protected void showOtherForm(Resources res) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
    

