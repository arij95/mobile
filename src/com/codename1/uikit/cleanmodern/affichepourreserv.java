/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Hebergement;
import Entity.ReservationHebergement;
import Entity.User2;
import Service.ServiceReservHebergement;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entites.UserCo;
import java.util.ArrayList;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class affichepourreserv extends BaseForm{
     public affichepourreserv(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Holidays now");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        
        addTab(swipe, res.getImage("images.jpg"), spacer1, " ", "", "");
        addTab(swipe, res.getImage("images.jpg"), spacer2, "", "", "");
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
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
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        ServiceReservHebergement ser = new ServiceReservHebergement();
					ArrayList<Hebergement> Tab = ser.getList5();
                                        Hebergement ee=new Hebergement();
                                        ReservationHebergement p=new ReservationHebergement();
					for (int i = 0; i < Tab.size(); i++) {
						addButton(res,Tab.get(i).getImage()," Nom hebergement: "+Tab.get(i).getNom_Hebergement()+"\n Type: "+Tab.get(i).getType_Hebergement()+"\n Adresse: "+Tab.get(i).getAdresse_Hebergement()+"\n Nombre d'étoiles: "+Tab.get(i).getNombre_etoile()+"\n Prix à partir de: "+Tab.get(i).getPrix_single()+" DT"+"\n Pension compléte: "+Tab.get(i).getTaux_complet()+"% ou Demi: "+Tab.get(i).getTaux_demi()+"%"+"\n Description: "+Tab.get(i).getDescription()+"\n Cette annonce est ajoutée par: "+Tab.get(i).getNomAgence()+"\n Pour plus de détail contacter "+Tab.get(i).getTel(), false, 11, 9,Tab.get(i).getIdHebergement(),Tab.get(i).getIdAgence(),ee,Tab.get(i).getNomAgence(),Tab.get(i).getType_Hebergement(),Tab.get(i).getNombre_etoile(),Tab.get(i).getAdresse_Hebergement(),Tab.get(i).getNombre_chambre(),Tab.get(i).getPrix_single(),Tab.get(i).getPrix_double(),Tab.get(i).getTaux_demi(),Tab.get(i).getTaux_complet(),Tab.get(i).getTel(),Tab.get(i).getDescription(),p);
                                       
					}
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
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
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
  private void addButton(Resources res,String imageUrl, String title, boolean liked, int likeCount, int commentCount,int idHebergement,int id, Hebergement ee,String agg,String a, int b, String c, int d, int f, int j, int h, int i,int g, String t,ReservationHebergement p) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
        
     // Button image = new Button(img.fill(width, height));
     Button image = new Button(photo(height, width, imageUrl));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       System.out.println("");
       //System.out.println(img);
      
                   image.addActionListener(e -> {
                       if(d==0){
                       boolean y;
                      y = Dialog.show("Pas de places disponibles","","ok","");
                       }
                       else{
                       boolean x ;
			x = Dialog.show("\"voulez vous reserver ?","","oui","non");
                        if(x){
                            ServiceReservHebergement bb =new ServiceReservHebergement();
                         
                            p.setIdAgence(id);
                            p.setIdUtilisateur(UserCo.userCo.getId());
                            p.setIdHebergement(idHebergement);
                            ee.setPrix_double(j);
                            ee.setPrix_single(f);
                            ee.setTaux_complet(i);
                            ee.setTaux_demi(h);
                           
                            new Reserv(res,ee,p,id,idHebergement,f,j,i,h).show();
                        }
                       }
                        
		});
                        
              
       
   }
    
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
     public URLImage photo(int w,int h ,String pa)
     {
         URLImage photo2 = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(h, w , 0x00ff00), true), pa,
                "http://localhost/Holidaysnow01/web/resources/" + pa
        );
        photo2.fetch();
         System.out.println(photo2);
         System.out.println(pa);
        return photo2;
}
}
