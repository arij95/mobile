/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Hebergement;
import Service.ServiceHebergement;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * GUI builder created Form
 *
 * @author Y520-I7-1TR-4G
 */
public class AjoutHebergement extends BaseForm {
 
    Label im=new Label();
  public AjoutHebergement(Resources res) {
       super("Newsfeed", BoxLayout.y());
        ajouthebergement(res);
    }
  
   public void ajouthebergement(Resources res)
    {
               
    
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle(e.getUser().getNom());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
    
        setUIID("SignIn");
      //  TextField idAgence = new TextField("", "id agence", 20, TextField.ANY);
      TextField vide= new TextField("", "", 20, TextField.ANY);
        TextField nomAgence= new TextField("", "nom agence", 20, TextField.ANY);
        TextField type_Hebergement= new TextField("", "Type Hebergement", 20, TextField.ANY);
        TextField nom_Hebergement= new TextField("", "Nom Hebergement", 20, TextField.ANY);
        TextField Adresse_Hebergement= new TextField("", "Adresse Hebergement", 20, TextField.ANY);
        TextField nombre_etoile= new TextField("", "Nombre etoile", 20, TextField.ANY);
        TextField nombre_chambre= new TextField("", "Nombre chambre", 20, TextField.ANY);
        TextField prix_single= new TextField("", "Prix single", 20, TextField.ANY);
        TextField prix_double= new TextField("", "Prix double", 20, TextField.ANY);
        TextField taux_demi= new TextField("", "Taux demi", 20, TextField.ANY);
        TextField taux_complet= new TextField("", "Taux complet", 20, TextField.ANY);
        TextField tel= new TextField("", "Téléphone", 20, TextField.ANY);
        TextField description= new TextField("", "Description", 20, TextField.ANY);
      // TextField image= new TextField("", "image", 20, TextField.ANY);
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
       int fileNameIndex = filePath.lastIndexOf("temp") + 4;
                    String fileName = filePath.substring(fileNameIndex);
                    String ss=fileName.substring(0,fileName.length()-4)+"jpeg";
 im.setText(filePath);
   try {
          
           
           FileOutputStream file = new FileOutputStream(new File("C:\\wamp64\\www\\Holidaysnow01\\web\\resources\\" + filePath));
           
            file.flush();
       } catch (IOException ex) {
          System.out.println("eeeeeeeeaaaaa");
       }
 
    
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
                //createLineSeparator(),
                nomAgence,
                createLineSeparator(),
                 type_Hebergement,
                createLineSeparator(),
                 nom_Hebergement,
                createLineSeparator(),
                
                 Adresse_Hebergement,
                createLineSeparator(),
                nombre_etoile,
                createLineSeparator(),
                nombre_chambre,
                createLineSeparator(),
                prix_single,
                createLineSeparator(),
                prix_double,
                createLineSeparator(),
                 taux_demi,
                createLineSeparator(),
                 taux_complet,
                createLineSeparator(),
                
                 tel,
                createLineSeparator(),
                description,
                
                createLineSeparator(),
                imga,
       createLineSeparator(),
                ajouter
        );
        content.setScrollableY(true);
        
        setScrollableY(true);
        add( content);

ajouter.addActionListener(e->{ 
           if((Integer.valueOf(nombre_chambre.getText())) >0 && (Integer.valueOf(nombre_chambre.getText()))>0 &&(Integer.valueOf(nombre_etoile.getText()))<6 &&(Integer.valueOf(nombre_etoile.getText()))>0 &&(Integer.valueOf(prix_single.getText()))>0 && (Integer.valueOf(prix_double.getText())) >0 && (Integer.valueOf(taux_demi.getText()))>0 && (Integer.valueOf(taux_complet.getText()))>0 && (Integer.valueOf(tel.getText()))>0  &&verifstring(nomAgence.getText())&& verifstring(type_Hebergement.getText())&& verifnum(nombre_chambre.getText())&& verifstring(nom_Hebergement.getText())&& verifstring(Adresse_Hebergement.getText())&& verifnum(nombre_chambre.getText())&&verifnum(prix_single.getText())&& verifnum(prix_double.getText())&& verifnum(taux_demi.getText())&& verifnum(taux_complet.getText())&& verifnum(tel.getText())&& verifstring(tel.getText())&& verifstring(description.getText()))
         {
             Hebergement ann=new Hebergement();
         
        
       ann.setNomAgence(nomAgence.getText());
       ann.setType_Hebergement(type_Hebergement.getText());
       ann.setNom_Hebergement(nom_Hebergement.getText());
         ann.setNombre_etoile(Integer.valueOf(nombre_etoile.getText()));
      
       ann.setAdresse_Hebergement(Adresse_Hebergement.getText());
         ann.setNombre_chambre(Integer.valueOf(nombre_chambre.getText()));
       ann.setPrix_single(Integer.valueOf(prix_single.getText()));
       ann.setPrix_double(Integer.valueOf(prix_double.getText()));
         ann.setTaux_demi(Integer.valueOf(taux_demi.getText()));
      
       ann.setTaux_complet(Integer.valueOf(taux_complet.getText()));
       ann.setTel(Integer.valueOf(tel.getText()));
      
       ann.setDescription(description.getText());
      
        ann.setIdAgence(Integer.valueOf(UserCo.userCo.getId()));
             System.out.println(UserCo.userCo.getId());
ann.setImage(im.getText());
           ServiceHebergement sa=new ServiceHebergement();
          
        
            sa.ajoutHebergement(ann);
            Dialog.show("Ajout", "Ajout éffectué avec succés", "ok", null);
            
             new MesPropresHebergementss(res).show();
        
         }
         else{
            Dialog.show("erreur", "veuillez verifiez les champs", "ok",null);
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
