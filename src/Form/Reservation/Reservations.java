package Form.Reservation;

import Entity.Reservation;
import Entity.Restaurant;
import Service.ReservationService;
import Service.RestaurantService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.util.List;


public class Reservations extends BaseForm {
    
    ReservationService rs;
    RestaurantService restservice;
    
    List<Reservation> reservations;
    List<Restaurant> restaurants;
    
    
    Container elementsContainer;
    
    Resources res;
    
    public Reservations(Resources res) {
        super("Réservations", BoxLayout.y());
        this.res = res;
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Réservations");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        rs = new ReservationService();
        restservice = new RestaurantService();
        
        reservations = rs.getMyReservations();
        restaurants = restservice.getList();
        
        elementsContainer = new Container(BoxLayout.y());
        
        
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("bg-holiday.jpg"), spacer1, "15 Likes  ", "85 Comments", "Mes Réservation");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();      
        Component.setSameSize( spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
  
        
        updateData(reservations);

    }
    
    public void updateData(List<Reservation> list)
    {
         elementsContainer.removeAll();
         elementsContainer.remove();
         for(Reservation reservation : list){
            addElement(reservation);
        }
         elementsContainer.refreshTheme();
         add(elementsContainer);
    }

    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
         
       Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addElement(Reservation reservation) {
      
       Restaurant restaurant = new Restaurant(); 
       for(Restaurant rt : restaurants){
           if(rt.getId()==reservation.getIdResto())
               restaurant = rt;
       }
       
       Container cnt = new Container();
       //cnt.setLeadComponent(image);
       TextArea ta = new TextArea("Restaurant: "+String.valueOf(restaurant.getNom()));
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       
       TextArea date = new TextArea("date: "+String.valueOf(reservation.getDate()));
       date.setUIID("NewsTopLine");
       date.setEditable(false);
       
       TextArea nbp = new TextArea("place: "+String.valueOf(reservation.getNbPersonne()));
       nbp.setUIID("NewsTopLine");
       nbp.setEditable(false);
       
       Container resvContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

       Container row = new Container(new GridLayout(1,2));
       Container firstColumn = new Container(new GridLayout(3,1));
       
       Label title = new Label("Résevation "+reservation.getId());
       title.setUIID("Form");
       title.getAllStyles().setMarginLeft(15);

       Button cancelBtn = new Button("Annuler reservation");
      
       cancelBtn.addActionListener((evt) -> {
           rs.cancelReservation(reservation);
           new Reservations(res).show();
       });
       
       firstColumn.add(ta);
       firstColumn.add(date);
       firstColumn.add(nbp);
       row.add(firstColumn);
       row.add(cancelBtn);
       
       resvContainer.addComponent(title);
       resvContainer.addComponent(row);
       
       resvContainer.getAllStyles().setMarginBottom(30);
       elementsContainer.add(resvContainer);
   }
    
}
