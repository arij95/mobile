package Form.Restauration;
import Entity.Restaurant;
import Service.RestaurantService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import entites.UserCo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Restaurants extends BaseForm {
    
    RestaurantService restaurantService;
    
    List<Restaurant> restaurants;
    List<Restaurant> favoris;
    
    Container elementsContainer;
    
    Resources res;
    public Restaurants(Resources res) {
        super("Restaurants", BoxLayout.y());
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Restaurants");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        restaurantService = new RestaurantService();
        
        restaurants = restaurantService.getList();
        favoris = restaurantService.getFavoris();
        
        elementsContainer = new Container();
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
     
        
        // ajout swipe 
        addTab(swipe, res.getImage("bg-holiday.jpg"), spacer1, "15 Likes  ", "85 Comments", "Trouvez les meilleurs restaurants avec Holidaynow");
        addTab(swipe, res.getImage("bg-holiday.jpg"), spacer2, "100 Likes  ", "66 Comments", "Réservez votre restaurant preferé");
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        // fin ajout swipe
        
        
        
        // Douratte mta3 navigation
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        //fin douratte mta3 navigation
        
        
        //Ajout onglets
        ButtonGroup groupeOnglet = new ButtonGroup();
        
        RadioButton ongletTout = RadioButton.createToggle("Tout", groupeOnglet);
        ongletTout.setUIID("SelectBarBlack");
        
        RadioButton ongletRecherche = RadioButton.createToggle("Recherche", groupeOnglet);
        ongletRecherche.setUIID("SelectBarBlack");
        
        RadioButton OngletFaoris = RadioButton.createToggle("Favoris", groupeOnglet);
        OngletFaoris.setUIID("SelectBarBlack");
        
        Label triangle = new Label(res.getImage("news-tab-down-arrow-black.png"), "Container");
        
        if(UserCo.userCo.getRoles().contains("AGENT")){
              add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, ongletTout, ongletRecherche),
                FlowLayout.encloseBottom(triangle)
        ));
        }else{
              add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, ongletTout, ongletRecherche, OngletFaoris),
                FlowLayout.encloseBottom(triangle)
        ));
        }
      
        
        ongletTout.setSelected(true);
        
        triangle.setVisible(false);
        
        addShowListener(e -> {
            triangle.setVisible(true);
            updateArrowPosition(ongletTout, triangle);
        });
        
        
        bindButtonSelection(ongletTout, triangle);
        bindButtonSelection(ongletRecherche, triangle);
        bindButtonSelection(OngletFaoris, triangle);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(groupeOnglet.getRadioButton(groupeOnglet.getSelectedIndex()), triangle);
        });
        
        Button addBtn = new Button("Ajouter Restaurant");
        addBtn.addActionListener(e->{
            new Ajout(res).show();
        });
        if (UserCo.userCo.getRoles().contains("AGENT")){
            add(addBtn);
        }
              
    }
    // !!!!!!!!!!!!!!!!!!!! //
    public void updateData(List<Restaurant> list,String word){
       
        if(list.isEmpty()){
            Label l = new Label("la Liste est vide");
            
            if(word!=null)
            l = new Label("la Liste est vide pour le mot "+word);
            
            elementsContainer.add(l);
        }
        
         for(Restaurant r : list){
            int max = 4;
            int min = 1;
            Random random = new Random();
            int randomNumber = random.nextInt(max + 1 - min) + min;
            addElement(randomNumber, r);
        }
         elementsContainer.refreshTheme();
         add(elementsContainer);
         this.refreshTheme();
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        elementsContainer.removeAll();
        elementsContainer.remove();
        
        switch(b.getText()){
            case "Tout" : 
                updateData(restaurants,null);
            break;   
            case "Recherche" :
                SearchAction();
            break;
            case "Favoris":
               updateData(favoris,null); 
            break;
        }
        
        // positionner le triangle sous le button passer en parametre
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
    }
    
    public void SearchAction(){
        Button searchBtn = new Button("Recherche");
        
        TextField motField = new TextField();
        motField.setHint("Recherche");
        motField.getAllStyles().setFgColor(0x000000);
        motField.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));

        Container container = new Container(new GridLayout(1,2));
        container.add(motField);
        container.add(searchBtn);
        elementsContainer.add(container);
        updateData(new ArrayList<>(),null);

        searchBtn.addActionListener((evt) -> {
            String word = motField.getText();
            if(word.equals("")){
                //Affiche Message Erreur
                ToastBar.showErrorMessage("Chaine doit etre non vide");
            }else{
                 elementsContainer.removeAll();
                 elementsContainer.remove();
                 elementsContainer.add(container);
                 List<Restaurant> resultats = restaurantService.rechercheList(word);
                 updateData(resultats,word);
            }
        });
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

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
                           // FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    private void addElement(int number, Restaurant r) {
       Image img = res.getImage("rest"+number+".jpg");
       
       
       // ajout image container
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       //le style de l image
       image.setUIID("Label");
       
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       
       TextArea ta = new TextArea(r.getNom());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label adr = new Label("Adrese: "+r.getAdresse(), "NewsTopLine");
       adr.setTextPosition(RIGHT);
       FontImage.setMaterialIcon(adr, FontImage.MATERIAL_CHAT);
      
       Label spec = new Label("Spécialité: "+r.getSpecialite(), "NewsTopLine");
       FontImage.setMaterialIcon(spec, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(adr, spec)
               ));
       elementsContainer.add(cnt);
       image.addActionListener(e ->new Detail(res,r,number).show());
   }
    
   
   //Actualiser la position du triangle sous les onglet
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
