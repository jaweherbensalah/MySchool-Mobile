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
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.AbonnesTransport;
import com.mycompany.myapp.services.ServiceAbonnesTransport;
import java.io.IOException;
import java.util.ArrayList;

public class AbonnesTransportForm extends Form{

     public AbonnesTransport e;
     public int n=0;
     
//**********************************************
 
      
/**
 * Creates a renderer for the specified colors.
 */
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
        series.add("Nbre Abonnés  " + ++k, value);
    }

    return series;
}
/********** Afficher La liste des Abonnés Restau**/
     
 public  AbonnesTransportForm(Form previous) 
 {
 setTitle("Abonnés Transport ");
 
 ArrayList<AbonnesTransport> data = new ArrayList<AbonnesTransport>();
 ServiceAbonnesTransport v = new ServiceAbonnesTransport();
 Container cnt = new Container(BoxLayout.y());
 data = v.getAllTasks(); 
 
 
Label AboTrans = new Label( "Les Abonnés Transport :");  
cnt.add(AboTrans);
   
 for(int i=0; i<data.size(); i++){

Label l1 = new Label( data.get(i).getEmail());
Label l2 = new Label( data.get(i).getUsername());
Label l3 = new Label( data.get(i).getDuree_abonnement());

cnt.add(l1);
cnt.add(l2);
cnt.add(l3);
n+=1; 

}    
 
//*****************************************
 

System.out.println("nbr  :"+n);
 double[] values = new double[]{n, 14};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.MAGENTA};
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
    PieChart chart = new PieChart(buildCategoryDataset("Statistiques", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    
add(cnt);
add(c);
    
 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());   
 /*
 Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
 FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
//getToolbar().addCommandToLeftBar("Left", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToOverflowMenu("Overflow", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));
      
   */
  
    }    
//*********************************************************************



    

    

    




























}

