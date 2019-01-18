/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Hebergement;
import Entity.User2;
import Entity.Voyageorganise;
import Service.ServiceHebergement;
import Service.ServiceVoyageorganise;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entites.UserCo;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class AjoutVoyageorg extends BaseForm{
     Label im=new Label();
  public AjoutVoyageorg(Resources res) {
      super("Newsfeed", BoxLayout.y());
        AjoutVoyageorg(res);
    }
  
   public void AjoutVoyageorg(Resources res)
    {
          Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle(e.getUser().getNom());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
     setUIID("SignIn");
       
        TextField vide = new TextField("", "", 20, TextField.ANY);
        TextField nomAgence= new TextField("", "nom agence", 20, TextField.ANY);
        TextField Prix= new TextField("", "Prix voyage", 20, TextField.ANY);
        TextField depart= new TextField("", "Date depart", 20, TextField.ANY);
        TextField retour= new TextField("", "Date retour", 20, TextField.ANY);
        TextField origine= new TextField("", "origine", 20, TextField.ANY);
        TextField pays_destination= new TextField("", "pays destination", 20, TextField.ANY);
        TextField ville_destination= new TextField("", "ville destination", 20, TextField.ANY);
        TextField nbrplace= new TextField("", "Nombre de places", 20, TextField.ANY);
        TextField hotel= new TextField("", "hotel", 20, TextField.ANY);
        
         Button ajouter =new  Button("Ajouter");
       
       Label ajout1=new Label("ajout");
       
       Label imga=new Label("Importer image");
      
        Style imgaStyle = new Style(imga.getUnselectedStyle());
        imgaStyle.setFgColor(0x2576f9);
        FontImage imgaImage = FontImage.createMaterial(FontImage.MATERIAL_COLLECTIONS, imgaStyle);
        imga.setIcon(imgaImage);
        imga.setTextPosition(RIGHT);
        imga.addPointerPressedListener(l-> {
      ActionListener callback = e->{
   if (e != null && e.getSource() != null) {
       String filePath = (String)e.getSource();
    
     //  Hebergement az= new Hebergement();
      // String nomm = az.getImage();
       System.out.println(filePath);

 im.setText(filePath);
 
    
   }
};

if (FileChooser.isAvailable()) {
    FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
} else {
    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
}
  
       });   
        
Container content = BoxLayout.encloseY(
                vide,
                createLineSeparator(),
                nomAgence,
                createLineSeparator(),
                 Prix,
                createLineSeparator(),
                 depart,
                createLineSeparator(),
                
                 retour,
                createLineSeparator(),
                origine,
                createLineSeparator(),
                pays_destination,
                createLineSeparator(),
                ville_destination,
                createLineSeparator(),
                nbrplace,
                createLineSeparator(),
                 hotel,
                createLineSeparator(),
                
                imga,
       createLineSeparator(),
                ajouter
        );
        content.setScrollableY(true);
        
        setScrollableY(true);
        add( content);

ajouter.addActionListener(e->{ 
          if(verifnum(Prix.getText())&&verifstring(nomAgence.getText())&&verifstring(origine.getText())&&verifstring(pays_destination.getText()))
         {
             Voyageorganise ann=new Voyageorganise();
         
        
       ann.setNom_agence(nomAgence.getText());
       ann.setPrix_voyage(Integer.valueOf(Prix.getText()));
       ann.setDate_depart(depart.getText());
         ann.setDate_retour(retour.getText());
      
       ann.setOrigine(origine.getText());
         ann.setPays_destination(pays_destination.getText());
       ann.setVille_destination(ville_destination.getText());
       ann.setNb_places(Integer.valueOf(nbrplace.getText()));
         ann.setHotel(hotel.getText());
      
      
      
        ann.setId_agence(Integer.valueOf(UserCo.userCo.getId()));
             System.out.println(UserCo.userCo.getId());
ann.setImage(im.getText());
           ServiceVoyageorganise sa=new ServiceVoyageorganise();
          
            sa.ajoutVoyageorganise(ann);
            Dialog.show("Ajout", "Ajout éffectué avec succés", "ok", null);
             removeAll();
            //  new MesPropresHebergementss(res).show();
        
         }
         else{
            Dialog.show("erreur", "veuillez remplir tous les champs", "ok",null);
        }
        });
    }

    public  boolean verifstring(String entry)
    {         if (entry.equals(""))
        return false; 
 
         return true;   }
 
public  boolean verifnum(String entry)
    {
        if (entry.equals(""))
        {return false; }
        if(entry.toLowerCase().equals(entry.toUpperCase()))
        {
            return true;
        }
        else{
            return false;
        }
    }
   
    
 
}
