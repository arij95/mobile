package com.codename1.uikit.cleanmodern;


import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import Entity.Voyageorganise;
import Service.ServiceVoyageorganise;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class ModifVoyageorganise extends BaseForm{
   
    TextField nomAgence;
    TextField Prix;
    TextField depart;
    TextField retour;
     TextField origine;
    TextField pays_destination;
    TextField ville_destination;
    TextField nbrplace;
     TextField hotel;
  
    TextField image;
//TextField idHebergement;
    Button b;
     Voyageorganise e= new Voyageorganise();
     
    public   ModifVoyageorganise(Resources res, Voyageorganise e,int idd)
    {
        
       
     super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle(e.getUser().getNom());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
       
    
        setUIID("SignIn");
        tb.addSearchCommand(z -> {});
        
        
        Image img = res.getImage("Beyrouth-liban-06.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        nomAgence = new TextField();
        Prix = new TextField();
        depart = new TextField();
        retour = new TextField();
         origine = new TextField();
        pays_destination = new TextField();
        ville_destination = new TextField();
        nbrplace = new TextField();
         hotel = new TextField();
       
        image = new TextField();
     
      
        nomAgence.setText(e.getNom_agence());
        nomAgence.setUIID("TextFieldBlack");
        Prix.setText(Integer.toString(e.getPrix_voyage()));
        Prix.setUIID("TextFieldBlack");
        depart.setText(e.getDate_depart());
        depart.setUIID("TextFieldBlack");
       
        retour.setText(e.getDate_retour());
        retour.setUIID("TextFieldBlack");
        origine.setText(e.getOrigine());
        origine.setUIID("TextFieldBlack");
        pays_destination.setText(e.getPays_destination());
        pays_destination.setUIID("TextFieldBlack");
        ville_destination.setText(e.getVille_destination());
        ville_destination.setUIID("TextFieldBlack");
        
        nbrplace.setText(Integer.toString(e.getNb_places()));
        nbrplace.setUIID("TextFieldBlack");
        hotel.setText(e.getHotel());
        hotel.setUIID("TextFieldBlack");
        
        image.setText(e.getImage());
        image.setUIID("TextFieldBlack");
         b = new Button("Modifier");
        b.addActionListener(al->{
            
            ServiceVoyageorganise se = new ServiceVoyageorganise();
           /* if(nombre_chambre.getText().length()>0 || nombre_etoile.getText().length()<=5 || prix_single.getText().length()>0)
            {
                Dialog.show("Error", "Vérifiez les données saisies", "ok", null);
            }
            
            else
            {*/
            e.setNom_agence(nomAgence.getText());
             e.setPrix_voyage(Integer.valueOf(Prix.getText()));
             e.setDate_depart(depart.getText());
             e.setDate_retour(retour.getText());
              e.setOrigine(origine.getText());
              e.setPays_destination(pays_destination.getText());
              e.setVille_destination(ville_destination.getText());
              e.setNb_places(Integer.valueOf(nbrplace.getText()));
              e.setHotel(hotel.getText());
             
                e.setImage(image.getText());
             
            se.setVoyageorganise(e, idd);
          new AfficheVoyageorganise(res).show();
            });
        
        addAll(nomAgence,Prix,depart,retour,origine,pays_destination,ville_destination,nbrplace,hotel,image,b);
       
      
        
    }
    

    
}
