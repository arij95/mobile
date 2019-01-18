/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Hebergement;
import Entity.ReservationHebergement;
import Entity.User2;
import Service.ServiceHebergement;
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
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entites.UserCo;
import java.util.ArrayList;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class MesreserHebergementForm extends   BaseForm {
    
    public MesreserHebergementForm(Resources res) {
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
        
       
        ServiceReservHebergement ser = new ServiceReservHebergement();
       
					ArrayList<ReservationHebergement> Tab = ser.getListreservh(UserCo.userCo.getId());
                                        ArrayList<Hebergement> Tabb = ser.getList5();
                                        Hebergement ee=new Hebergement();
                                         ReservationHebergement et=new ReservationHebergement();
					for (int i = 0; i < Tab.size(); i++) {
						/*addButton(res,Tab.get(i).getImage(),Tab.get(i).getNom_Hebergement(), false, 11, 9,Tab.get(i).getIdHebergement(),Tab.get(i).getIdAgence(),ee,Tab.get(i).getNomAgence(),Tab.get(i).getType_Hebergement(),Tab.get(i).getNombre_etoile(),Tab.get(i).getAdresse_Hebergement(),Tab.get(i).getNombre_chambre(),Tab.get(i).getPrix_single(),Tab.get(i).getPrix_double(),Tab.get(i).getTaux_demi(),Tab.get(i).getTaux_complet(),Tab.get(i).getTel(),Tab.get(i).getDescription());*/
                                               addButton(res,Tabb.get(i).getImage(), false, 11, 9,Tab.get(i).getIdHebergement(),Tabb.get(i).getNom_Hebergement(),ee,Tabb.get(i).getNomAgence(),Tabb.get(i).getType_Hebergement(),Tab.get(i).getIdReservationh(),Tab.get(i).getPrix(),Tab.get(i).getTauxchambre(),Tab.get(i).getTypechambre(),Tab.get(i).getNbrjour(),Tab.get(i).getNbrplace(),et,Tab.get(i).getIdUtilisateur());
                                        
                                         
                
                        

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
     public URLImage photo(int w,int h ,String pa)
     {
         URLImage photo2 = URLImage.createToStorage(EncodedImage.createFromImage(Image.createImage(w, h , 0x00ff00), true), pa,
                "http://localhost/pidev/imagee.php?image=" + pa
        );
        photo2.fetch();
        
        return photo2;
}
    
  private void addButton(Resources res,String URLImage, boolean liked, int likeCount, int commentCount,int idHebergement, String title, Hebergement ee,String agg,String a, int b, int d, String f, String j, int h, int l, ReservationHebergement et, int gt) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
        
     // Button image = new Button(img.fill(width, height));
     Button image = new Button(photo(height, width, URLImage));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(a);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
 TextArea tc = new TextArea(title);
       tc.setUIID("NewsTopLine");
       tc.setEditable(false);
        
       
     
       Label likes = new Label(liked + " Likes  ", "NewsBottomLine");
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
                       tc,
                       
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
      // System.out.println("");
       System.out.println(image);
       
       image.addActionListener(e -> {
                       boolean x ;
			x = Dialog.show("Que voulez vous faire ?","","Annuler reservation","Reclamer");
                        if(x){
                        ServiceReservHebergement bb =new ServiceReservHebergement();
                           ArrayList<ReservationHebergement> p= bb.getListreservh(gt);
                           bb.supprimerreservHebergment(b);
                           new MesreserHebergementForm(res).show();

                        }
                        else{
                            			
                                                  
                           new SendReclamationAdmin(res).show();
                                                
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
    
    
}
