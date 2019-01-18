package Form.Restauration;

import Entity.Restaurant;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import entites.UserCo;
import java.util.List;
import main.Config;


public class Ajout extends BaseForm {
    
    RestaurantService restaurantService;
    List<Restaurant> restaurants;

    public Ajout(Resources res) {
        super("Détail", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Restaurant");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});

        restaurantService = new RestaurantService();
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        
        addTab(swipe, res.getImage("bg-holiday.jpg"), spacer1, "15 Likes  ", "Integer ut placerat purued non dignissim neque. ");
      
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setScrollableY(true);
        
        
        Button saveBtn = new Button("Sauvegarder");
        saveBtn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,15));
        
        TextField nomtxt = new TextField();
        nomtxt.setHint("Titre");
        nomtxt.getAllStyles().setFgColor(0x000000);
        nomtxt.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
        
        TextField adrTxt = new TextField();
        adrTxt.setHint("Adresse");
        adrTxt.getAllStyles().setFgColor(0x000000);
        adrTxt.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
       
        TextField typetxt = new TextField();
        typetxt.setHint("Type");
        typetxt.getAllStyles().setFgColor(0x000000);
        typetxt.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
      
        TextField specTxt = new TextField();
        specTxt.setHint("specialité");
        specTxt.getAllStyles().setFgColor(0x000000);
        specTxt.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
      
        TextField nbTxt = new TextField();
        nbTxt.setHint("nombre place");
        nbTxt.getAllStyles().setFgColor(0x000000);
        nbTxt.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
      
        
      
        center.add(nomtxt);
        center.add(typetxt);
        center.add(adrTxt);
        center.add(specTxt);
        center.add(nbTxt);
        
        Component.setSameSize( spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
        
        add(center);
        add(saveBtn);
        
        saveBtn.addActionListener(e->{
            String nom = nomtxt.getText();
            String type = typetxt.getText();
            String spec = specTxt.getText();
            String adr = adrTxt.getText();
            String nbtxt = nbTxt.getText();
            int nb = 0;
            
            if(nom.equals("")){
                ToastBar.showErrorMessage("Tous les champs sont obligatoires");
            }
            else if(type.equals("")){
                ToastBar.showErrorMessage("Tous les champs sont obligatoires");
            }
            else if(spec.equals("")){
                ToastBar.showErrorMessage("Tous les champs sont obligatoires");
            }
            else if(adr.equals("")){
                ToastBar.showErrorMessage("Tous les champs sont obligatoires");
            }
            else if( !isInteger(nbtxt)||(nbtxt.equals(""))||(nbtxt.equals("0"))){
                ToastBar.showErrorMessage("Vérifiez le nombre de places !! ");
            }else{
                nb = Integer.parseInt(nbtxt);
                Restaurant r = new Restaurant(0, nom, nb, spec, type, adr, UserCo.userCo.getId());
                restaurantService.addRestaurant(r);
                new Restaurants(res).show();
            }
            
            
        });

        
    }
    
    public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException | NullPointerException e) { 
        return false; 
    }
    // only got here if we didn't return false
    return true;
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
