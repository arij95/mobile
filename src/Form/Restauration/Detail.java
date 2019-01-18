package Form.Restauration;

import Entity.Restaurant;
import Form.Reservation.Reserver;
import Service.RestaurantService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import entites.UserCo;
import java.util.List;


public class Detail extends BaseForm {
    
    RestaurantService restaurantService;
    List<Restaurant> restaurants;

    public Detail(Resources res,Restaurant restaurant,int number) {
        super("Détail", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Détail : "+restaurant.getNom());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        restaurantService = new RestaurantService();
        
        boolean isliked = restaurantService.isLiked(restaurant);
        
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("rest"+number+".jpg"), spacer1, restaurant.getType(), "");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
      
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setScrollableY(true);
        
        Container likeContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container actionContainer = new Container(new GridLayout(1,2));
       
        Button deletebtn = new Button("Supprimer");
        deletebtn.setUIID("btn-secondary");
        deletebtn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
        deletebtn.addActionListener(e->{
            restaurantService.deleteRestaurant(restaurant);
            ToastBar.showErrorMessage("Suppression");
            new Restaurants(res).show();
        });

        Button reserveBtn = new Button("Reservé");
        reserveBtn.setUIID("btn-primary");
        reserveBtn.addActionListener(l->{
            new Reserver(res,restaurant).show();
        });
        
        Button modifyBtn = new Button("Modifier");
        modifyBtn.setUIID("btn-primary");
        modifyBtn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
        modifyBtn.addActionListener(e->{
            new Modifier(res, restaurant).show();
        });
        
        Button likeBtn = new Button("J'aime");
        likeBtn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,15));
       
        Button dislikeBtn = new Button("je n'aime pas");
        dislikeBtn.setUIID("btn-secondary");
        dislikeBtn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,15));
        
        
        likeBtn.addActionListener(e->{
           restaurantService.likeAction(restaurant, true);
            likeContainer.replace(likeBtn,dislikeBtn,CommonTransitions.createSlideFadeTitle(true, 200));
        });
         
        dislikeBtn.addActionListener(e->{
           restaurantService.likeAction(restaurant, false);
           likeContainer.replace(dislikeBtn,likeBtn,CommonTransitions.createSlideFadeTitle(true, 200));
        });
        
        Label nom       = new Label("Nom          : "+restaurant.getNom());
        Label adresse   = new Label("Adresse    : "+restaurant.getAdresse());
        Label specialite= new Label("Spécialité  : "+restaurant.getSpecialite());
        Label place     = new Label("place disponible  : "+restaurant.getPlace());

        nom.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_BOLD,15));
        nom.getAllStyles().setFgColor(0x000000);

        adresse.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_BOLD,15));
        adresse.getAllStyles().setFgColor(0x000000);

        specialite.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_BOLD,15));
        specialite.getAllStyles().setFgColor(0x000000);
        
        likeContainer.getAllStyles().setMargin(0,0,20,20);
        
        if(isliked){
            likeContainer.addComponent(dislikeBtn);
        }else{
            likeContainer.addComponent(likeBtn);
        }
        center.add(nom);
        center.add(adresse);
        center.add(specialite);
        Component.setSameSize( spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
        
         if(restaurant.getIdagence() == UserCo.userCo.getId()){
            actionContainer.addComponent(modifyBtn);
            actionContainer.addComponent(deletebtn);
            add(actionContainer);
        }else{
          add(likeContainer);
          add(reserveBtn);
        }
        add(center);

        
    }
        
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr) {
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
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
   
}
