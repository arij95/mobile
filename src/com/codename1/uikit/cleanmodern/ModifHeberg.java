/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Hebergement;
import Service.ServiceHebergement;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class ModifHeberg extends BaseForm{
   
    TextField nomAgence;
    TextField type_Hebergement;
    TextField nom_Hebergement;
    TextField nombre_etoile;
     TextField Adresse_Hebergement;
    TextField nombre_chambre;
    TextField prix_single;
    TextField prix_double;
     TextField taux_demi;
    TextField taux_complet;
    TextField tel;
    TextField description;
    TextField image;
//TextField idHebergement;
    Button b;
     Hebergement e= new Hebergement();
     
    public   ModifHeberg(Resources res, Hebergement e,int idd)
    {
        
       
     super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle(e.getUser().getNom());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        
        
    
        setUIID("SignIn");
       // tb.addSearchCommand(z -> {});
        
        
        Image img = res.getImage("hotel.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        nomAgence = new TextField();
        type_Hebergement = new TextField();
        nom_Hebergement = new TextField();
        nombre_etoile = new TextField();
         Adresse_Hebergement = new TextField();
        nombre_chambre = new TextField();
        prix_single = new TextField();
        prix_double = new TextField();
         taux_demi = new TextField();
        taux_complet = new TextField();
        tel = new TextField();
        description = new TextField();
        image = new TextField();
     
      
        nomAgence.setText(e.getNomAgence());
        nomAgence.setUIID("TextFieldBlack");
        type_Hebergement.setText(e.getType_Hebergement());
        type_Hebergement.setUIID("TextFieldBlack");
        nom_Hebergement.setText(e.getNom_Hebergement());
        nom_Hebergement.setUIID("TextFieldBlack");
        nombre_etoile.setText(Integer.toString(e.getNombre_etoile()));
        nombre_etoile.setUIID("TextFieldBlack");
        Adresse_Hebergement.setText(e.getAdresse_Hebergement());
        Adresse_Hebergement.setUIID("TextFieldBlack");
        nombre_chambre.setText(Integer.toString(e.getNombre_chambre()));
        nombre_chambre.setUIID("TextFieldBlack");
        prix_single.setText(Integer.toString(e.getPrix_single()));
        prix_single.setUIID("TextFieldBlack");
        prix_double.setText(Integer.toString(e.getPrix_double()));
        prix_double.setUIID("TextFieldBlack");
        
        taux_demi.setText(Integer.toString(e.getTaux_demi()));
        taux_demi.setUIID("TextFieldBlack");
        taux_complet.setText(Integer.toString(e.getTaux_complet()));
        taux_complet.setUIID("TextFieldBlack");
        tel.setText(Integer.toString(e.getTel()));
        tel.setUIID("TextFieldBlack");
        description.setText(e.getDescription());
        description.setUIID("TextFieldBlack");
        image.setText(e.getImage());
        image.setUIID("TextFieldBlack");
         b = new Button("Modifier");
        b.addActionListener(al->{  if((Integer.valueOf(nombre_chambre.getText())) >0 && (Integer.valueOf(nombre_chambre.getText()))>0 &&(Integer.valueOf(nombre_etoile.getText()))<6 &&(Integer.valueOf(nombre_etoile.getText()))>0 &&(Integer.valueOf(prix_single.getText()))>0 && (Integer.valueOf(prix_double.getText())) >0 && (Integer.valueOf(taux_demi.getText()))>0 && (Integer.valueOf(taux_complet.getText()))>0 && (Integer.valueOf(tel.getText()))>0  &&verifstring(nomAgence.getText())&& verifstring(type_Hebergement.getText())&& verifnum(nombre_chambre.getText())&& verifstring(nom_Hebergement.getText())&& verifstring(Adresse_Hebergement.getText())&& verifnum(nombre_chambre.getText())&&verifnum(prix_single.getText())&& verifnum(prix_double.getText())&& verifnum(taux_demi.getText())&& verifnum(taux_complet.getText())&& verifnum(tel.getText())&& verifstring(tel.getText())&& verifstring(description.getText()))
        {
            
            ServiceHebergement se = new ServiceHebergement();
          
            
           
            e.setNomAgence(nomAgence.getText());
             e.setType_Hebergement(type_Hebergement.getText());
             e.setNom_Hebergement(nom_Hebergement.getText());
             e.setNombre_etoile(Integer.valueOf(nombre_etoile.getText()));
              e.setAdresse_Hebergement(Adresse_Hebergement.getText());
              e.setNombre_chambre(Integer.valueOf(nombre_chambre.getText()));
              e.setPrix_single(Integer.valueOf(prix_single.getText()));
              e.setPrix_double(Integer.valueOf(prix_double.getText()));
              e.setTaux_demi(Integer.valueOf(taux_demi.getText()));
              e.setTaux_complet(Integer.valueOf(taux_complet.getText()));
               e.setTel(Integer.valueOf(tel.getText()));
              e.setDescription(description.getText());
                e.setImage(image.getText());
             
            se.setHeberg(e, idd);
          new MesPropresHebergementss(res).show();}
        else {
             Dialog.show("Error", "Vérifiez les données saisies", "ok", null);
        }
            });
        
        addAll(nomAgence,type_Hebergement,nom_Hebergement,nombre_etoile,Adresse_Hebergement,nombre_chambre,prix_single,prix_double,taux_demi,taux_complet,tel,description,image,b);
       
      
        
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
