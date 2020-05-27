
package com.mycompany.myapp.gui;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.AbonnesTransport;
import com.mycompany.myapp.entities.EleveRestau;
import com.mycompany.myapp.services.ServiceAbonnesTransport;
import com.mycompany.myapp.services.ServiceEleveRestau;
import java.io.IOException;
import java.util.ArrayList;

public class StatistiquesForm extends Form{

     public EleveRestau e;
     public int n=0;
     public int nT=0;
     
//**********************************************
/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
public CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        series.add("Nbre Abonnés  " + ++k, value);
    }

    return series;
}
//**************************************************
      
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

/********** Afficher La liste des Abonnés Restau**/
     
 public  StatistiquesForm(Form previous) 
 {
 setTitle("Statistiques ");
 
 ArrayList<EleveRestau> data = new ArrayList<EleveRestau>();
 ServiceEleveRestau v = new ServiceEleveRestau();
 Container cnt = new Container(BoxLayout.y());
 data = v.getAllTasks(); 
Label AboRestau = new Label( "Les Abonnés Restaurant :");   
cnt.add(AboRestau); 
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
  ArrayList<AbonnesTransport> dataT = new ArrayList<AbonnesTransport>();
 ServiceAbonnesTransport vT = new ServiceAbonnesTransport();

 dataT = vT.getAllTasks(); 
 
Label AboTrans = new Label( "Les Abonnés Transport :");  
cnt.add(AboTrans);
 for(int i=0; i<dataT.size(); i++){

Label l1T = new Label( dataT.get(i).getEmail());
Label l2T = new Label( dataT.get(i).getUsername());
Label l3T = new Label( dataT.get(i).getDuree_abonnement());
cnt.add(l1T);
cnt.add(l2T);
cnt.add(l3T);

nT+=1; 

}

 
//*****************************************
System.out.println("nbr Abonnés Restaurant :"+n);
System.out.println("nbr Abonnés Transport :"+nT);
 double[] values = new double[]{n, nT};

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

    // Create a form and show it.
   // Form f = new Form("Statistiques ", new BorderLayout());
add(c);
add(cnt);
    
 /*
 Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
 FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
//getToolbar().addCommandToLeftBar("Left", icon, (e) -> Log.p("Clicked"));
//getToolbar().addCommandToRightBar("Right", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToOverflowMenu("Overflow", icon, (e) -> Log.p("Clicked"));
getToolbar().addCommandToSideMenu("Sidemenu", icon, (e) -> Log.p("Clicked"));
      
   */
  getToolbar().addMaterialCommandToLeftBar("Retour",FontImage.MATERIAL_HOME,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 HomeForm h = new HomeForm();
       h.getHome().show();
            }
        });
    }    
//********************************

}

