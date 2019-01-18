/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Hebergement;
import Entity.ReservationHebergement;
import Entity.ReservationVoyageorganise;
import Entity.Voyageorganise;
import Service.ServiceReservHebergement;
import Service.ServiceReservVoyageorganise;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.cleanmodern.Reserv.ACCOUNT_SID;
import static com.codename1.uikit.cleanmodern.Reserv.AUTH_TOKEN;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class AjoutReservationVoyageorg extends BaseForm{
    Label im=new Label();
    public static final String ACCOUNT_SID = "AC59ccf8cf8dc28020be0bb7f97e665ba0";
    public static final String AUTH_TOKEN = "49fe22c28264c5f80e59e7a14abc4418";
   ReservationVoyageorganise ann=new ReservationVoyageorganise();
    Hebergement hh= new Hebergement();
    public  AjoutReservationVoyageorg(Resources res,Voyageorganise hh,ReservationVoyageorganise ann,int a, int b,int p)
    {
           super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle(e.getUser().getNom());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
      
        setUIID("SignIn");
       
        TextField nbrplace = new TextField("", "Nombre de places", 20, TextField.ANY);
        TextField vide = new TextField("", "vide", 20, TextField.ANY);
         Form f = new Form("Form to play Video");
        f.setLayout(new BorderLayout());

        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/video.mp4");
            Media media = MediaManager.createMedia("src\\music\\Around.mkv", true);
            f.addComponent(BorderLayout.CENTER, media.getVideoComponent());
            f.show();
            media.setNativePlayerMode(true);
            media.play();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
        Button ajouter =new  Button("Reserver");
       
          Container content = BoxLayout.encloseY(
                  vide,
                   createLineSeparator(),
                nbrplace,
                createLineSeparator(),
                 f,
                createLineSeparator(),
                ajouter
        );
        content.setScrollableY(true);
        
        setScrollableY(true);
        add( content);
        
        ajouter.addActionListener(e->{ 
        if(verifnum(nbrplace.getText())/*&&verifstring((combo.getSelectedItem()))&&verifstring((combo1.getSelectedItem()))*/)
         {
              boolean x ;
			x = Dialog.show("\"voulez vous reserver ?" ,"","oui","non");
                        if(x ){
                           
             
                            ServiceReservVoyageorganise bb =new ServiceReservVoyageorganise();
                           
                  int vv=Integer.valueOf(nbrplace.getText());
                            int prix;
                         
                            prix=p*vv;
                  
       ann.setNbrplace(Integer.valueOf(nbrplace.getText()));
       ann.setPrix(prix);
       
            boolean az;
            az=Dialog.show("", "Prix de votre reservation est  " +prix +"  DT", "Valider", "Annuler");
            if(az){
                 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                                
                                Message message = Message
                                        .creator(new PhoneNumber("+21625179742"), new PhoneNumber("+12182765419"),
                                                "Votre reservation de voyage organise a été prise en compte " )
                                        .create();
                  bb.addreserv(ann,a,b);
      
                     removeAll();
            }
         
                          
                            
                        
         else{
            Dialog.show("erreur", "veuillez remplir tous les champs", "ok",null);
            }
        }
     
}
        }); }

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
