/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main;

import Entity.Hebergement;
import Entity.User2;
import Entity.Voyageorganise;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.models.SeriesSelection;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;






/**
 * Budget demo pie chart.
 */
public class BudgetPieChart extends AbstractDemoChart {
  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  public String getName() {
    return "Budget chart";
  }

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  
   
       ArrayList<Voyageorganise> listVoyageorganise = new ArrayList<>();
int avgg=0;
int avggg=0;
int avgggg=0;
int avg=0;
      public int getListVoyageorganise(){ 
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/moyenneprixvo.php?");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                avg = getListVoyageorganisee(new String(con.getResponseData()));
                System.out.println("this avg:"+avg);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return avg ;
      }
      public int getListVoyageorganisee(String json) {

        ArrayList<Voyageorganise> listAnnonces = new ArrayList<>();
      int aa=0;

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
 List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
 
            for (Map<String, Object> obj : list) {
                  Voyageorganise a = new Voyageorganise();
 System.out.println("this avg:"+avg);
                // System.out.println(obj.get("id"));
                 float bbb =  Float.parseFloat((obj.get("X").toString()));
                     aa=((int)bbb);
                      System.out.println("this avg:"+avg);
                 
                

            }

        } catch (IOException ex) {
        }
        
        return aa;

    }

       public int getListheberg(){ 
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/moyenneprixhebr.php?");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                avgg = getListVoyageorganiseee(new String(con.getResponseData()));
                System.out.println("this avg:"+avg);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return avgg ;
      }
      public int getListVoyageorganiseee(String json) {

        ArrayList<Voyageorganise> listAnnonces = new ArrayList<>();
      int aa=0;

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
 List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
 
            for (Map<String, Object> obj : list) {
                  Voyageorganise a = new Voyageorganise();
 System.out.println("this avg:"+avg);
                // System.out.println(obj.get("id"));
                 float bbb =  Float.parseFloat((obj.get("Y").toString()));
                     aa=((int)bbb);
                      System.out.println("this avg:"+avg);
                 
                

            }

        } catch (IOException ex) {
        }
        
        return aa;

    }
      
             public int getListact(){ 
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/moyenneprixac.php?");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                avggg = getListVoyageorganiseeee(new String(con.getResponseData()));
                System.out.println("this avg:"+avg);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return avggg ;
      }
      public int getListVoyageorganiseeee(String json) {

        ArrayList<Voyageorganise> listAnnonces = new ArrayList<>();
      int aa=0;

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
 List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
 
            for (Map<String, Object> obj : list) {
                  Voyageorganise a = new Voyageorganise();
 System.out.println("this avg:"+avg);
                // System.out.println(obj.get("id"));
                 float bbb =  Float.parseFloat((obj.get("Z").toString()));
                     aa=((int)bbb);
                      System.out.println("this avg:"+avg);
                 
                

            }

        } catch (IOException ex) {
        }
        
        return aa;

    }

       public int getListvol(){ 
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/moyenneprixvol.php?");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                avgggg = getListVoyageorganiseeeee(new String(con.getResponseData()));
                System.out.println("this avg:"+avg);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return avgggg ;
      }
      public int getListVoyageorganiseeeee(String json) {

        ArrayList<Voyageorganise> listAnnonces = new ArrayList<>();
      int aa=0;

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
 List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
 
            for (Map<String, Object> obj : list) {
                  Voyageorganise a = new Voyageorganise();
 System.out.println("this avg:"+avg);
                // System.out.println(obj.get("id"));
                 float bbb =  Float.parseFloat((obj.get("A").toString()));
                     aa=((int)bbb);
                      System.out.println("this avg:"+avg);
                 
                

            }

        } catch (IOException ex) {
        }
        
        return aa;

    }

  
  
  public String getDesc() {
    return "The budget per project for this year (pie chart)";
  }

  /**
   * Executes the chart demo.
   * 
   * @param context the context
   * @return the built intent
   */
  public Form execute() {
      Voyageorganise vo=new Voyageorganise();
     int a=getListVoyageorganise();
     int b=getListheberg();
     int c=getListact();
     int d=getListvol();
              
            
    double[] values = new double[] { a, b, c, d};
    int[] colors = new int[] { ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.BLACK };
    final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
   
    final CategorySeries seriesSet = buildCategoryDataset("Project budget", values);
    final PieChart chart = new PieChart(seriesSet, renderer);
    ChartComponent comp = new ChartComponent(chart){

        private boolean inDrag = false;
        
        @Override
        public void pointerPressed(int x, int y) {
            inDrag = false;
            super.pointerPressed(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pointerDragged(int x, int y) {
            inDrag = true;
            super.pointerDragged(x, y); //To change body of generated methods, choose Tools | Templates.
        }

        
        
        @Override
        protected void seriesReleased(SeriesSelection sel) {
            
            if ( inDrag ){
                // Don't do this if it was a drag operation
                return;
            }
            
            for ( SimpleSeriesRenderer r : renderer.getSeriesRenderers()){
                r.setHighlighted(false);
            }
            SimpleSeriesRenderer r = renderer.getSeriesRendererAt(sel.getPointIndex());
            r.setHighlighted(true);
            
            Shape seg = chart.getSegmentShape(sel.getPointIndex());
            Rectangle bounds = seg.getBounds();
            bounds = new Rectangle(
                    bounds.getX()-40,
                    bounds.getY()-40,
                    bounds.getWidth()+80,
                    bounds.getHeight()+80
            );
            
            this.zoomToShapeInChartCoords(bounds, 500);
            
            
            
        }
       
        
        
    };
    comp.setZoomEnabled(true);
    comp.setPanEnabled(true);
    
    return wrap("Budget", comp);
    
  }

}
