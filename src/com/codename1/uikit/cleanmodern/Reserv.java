/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import Entity.Hebergement;
import Entity.ReservationHebergement;
import Service.ServiceReservHebergement;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class Reserv extends BaseForm{
    
     public static final String ACCOUNT_SID = "AC59ccf8cf8dc28020be0bb7f97e665ba0";
    public static final String AUTH_TOKEN = "49fe22c28264c5f80e59e7a14abc4418";
    Label im=new Label();
   ReservationHebergement ann=new ReservationHebergement();
    Hebergement hh= new Hebergement();
    public  Reserv(Resources res,Hebergement hh,ReservationHebergement ann,int a, int b,int s,int d ,int tc , int td)
    {
         super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      //  setTitle(e.getUser().getNom());
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
       // getTitleArea().setUIID("Container");
        setUIID("SignIn");
       TextField aa = new TextField();
                 TextField bbb = new TextField();
        TextField nbrplace = new TextField("", "Nombre de places", 20, TextField.ANY);
       
                  TextField nbrjour = new TextField("", "Nombre de nuits", 20, TextField.ANY);
        //TextField poids= new TextField("", "Type chambre", 20, TextField.ANY);
     /*  ComboBox<String> combo = new ComboBox<> (
          "Single",
          "Double"
          );
       ComboBox<String> combo1 = new ComboBox<> (
          "Taux demi",
          "taux complet"
          );*/
     RadioButton Single = new RadioButton("Single");
     RadioButton Double = new RadioButton("Double");
     Single.setSelected(true);
     new ButtonGroup(Single, Double);
     RadioButton Tauxc = new RadioButton("Taux complet");
     RadioButton Tauxd = new RadioButton("Taux demi");
     Tauxc.setSelected(true);
     new ButtonGroup(Tauxc, Tauxd);
    /*CheckBox c = new CheckBox("Single");
        CheckBox Double = new CheckBox("Double");
        CheckBox Tauxc = new CheckBox("Tauxcomplet");
        CheckBox Tauxd = new CheckBox("Tauxdemi");*/
        /*
       nom.setUIID("PictureWhiteBackgrond");
        age.setUIID("PictureWhiteBackgrond");
         type.setUIID("PictureWhiteBackgrond");
          poids.setUIID("PictureWhiteBackgrond");
        race.setUIID("PictureWhiteBackgrond");
        combo.setUIID("PictureWhiteBackgrond");
       */
        Button ajouter =new  Button("Reserver");
         Button rec =new  Button("Reclamer");
          Container content = BoxLayout.encloseY(
                 aa,
                createLineSeparator(),
                 bbb,
                createLineSeparator(),
                  nbrplace,
                createLineSeparator(),
                 nbrjour,
                createLineSeparator(),
                 Single,
               // createLineSeparator(),
                 Double,
                createLineSeparator(),
                Tauxc,
               // createLineSeparator(),
                 Tauxd,
                createLineSeparator(),
                
                ajouter,
                  rec
        );
        content.setScrollableY(true);
        
        setScrollableY(true);
        add( content);
        
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(verifnum(nbrplace.getText())/*&&verifstring((combo.getSelectedItem()))&&verifstring((combo1.getSelectedItem()))*/)
                {
                    boolean x ;
                    x = Dialog.show("\"voulez vous reserver ?" ,"","oui","non");
                    if(x ){
                        if(Single.isSelected()&& Tauxc.isSelected() ){
                            
                            ServiceReservHebergement bb =new ServiceReservHebergement();
                            int     cc=Integer.valueOf(nbrjour.getText());
                            int vv=Integer.valueOf(nbrplace.getText());
                            int prix;
                            prix=cc*vv*s*tc;
                            
                            ann.setNbrplace(Integer.valueOf(nbrplace.getText()));
                            ann.setPrix(prix);
                            
                            
                            ann.setNbrjour(Integer.valueOf(nbrjour.getText()));
                            ann.setTypechambre("Single");
                            ann.setTauxchambre("Tauxcomplet");
                            // bb.addreserv(ann,a,b);
                            
                            //  nbr=n-ann.getNbrplace();
                            
                            
                            boolean az;
                            az=Dialog.show("", "Prix de votre reservation est  " +prix +"  DT", "Valider", "Annuler");
                            if(az){
                                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                                
                                Message message = Message
                                        .creator(new PhoneNumber("+21625179742"), new PhoneNumber("+12182765419"),
                                                "Votre reservation d'hébergement a été prise en compte " )
                                        .create();
                        String details ="Prix:" +ann.getPrix() + ".";
        ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from(details).to(ImageType.JPG).stream();
         System.out.println("zzzzzzaaaaa");
     //   File f = new File("http://localhost/pidev/telechargement.jpg");
                                try {
                                    // FileOutputStream fos = new FileOutputStream(f);
                                    OutputStream file = new FileOutputStream(new File("C:\\wamp64\\www\\pidev\\reservationHebergement.jpg"));
                                     System.out.println("eeeeeeeeaaaaa");
                                    file.write(out.toByteArray());
                                } catch (IOException ex) {
                                     System.out.println("ennnnnn");
                                }
    
                                bb.addreserv(ann,a,b);
                                
                                
                                removeAll();
                            }
                            
                            
                            
                        }
                        else if (Double.isSelected()&& Tauxc.isSelected()){
                            ServiceReservHebergement bb =new ServiceReservHebergement();
                            
                            int     cc=Integer.valueOf(nbrjour.getText());
                            int vv=Integer.valueOf(nbrplace.getText());
                            int prix;
                            prix=cc*vv*d*tc;
                            
                            ann.setNbrplace(Integer.valueOf(nbrplace.getText()));
                            ann.setPrix(prix);
                            
                            
                            ann.setNbrjour(Integer.valueOf(nbrjour.getText()));
                            ann.setTypechambre("Double");
                            ann.setTauxchambre("Tauxcomplet");
                            
                            boolean az;
                            az=Dialog.show("", "Prix de votre reservation est   " +prix+"  DT", "Valider", "Annuler");
                            if(az){
                                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                                
                                Message message = Message
                                        .creator(new PhoneNumber("+21625179742"), new PhoneNumber("+12182765419"),
                                                "Votre reservation a été prise en compte " )
                                        .create();
                                bb.addreserv(ann,a,b);
                                removeAll();
                            }
                            
                        }
                        else if (Single.isSelected()&& Tauxd.isSelected()){
                            ServiceReservHebergement bb =new ServiceReservHebergement();
                            
                            int     cc=Integer.valueOf(nbrjour.getText());
                            int vv=Integer.valueOf(nbrplace.getText());
                            int prix;
                           
                            prix=cc*vv*s*td;
                            
                            ann.setNbrplace(Integer.valueOf(nbrplace.getText()));
                            ann.setPrix(prix);
                            
                            
                            ann.setNbrjour(Integer.valueOf(nbrjour.getText()));
                            ann.setTypechambre("Single");
                            ann.setTauxchambre("Tauxdemi");
                            
                            boolean az;
                            az=Dialog.show("", "Prix de votre reservation est   " +prix+"  DT", "Valider", "Annuler");
                            if(az){
                                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                                
                                Message message = Message
                                        .creator(new PhoneNumber("+21625179742"), new PhoneNumber("+12182765419"),
                                                "Votre reservation a été prise en compte " )
                                        .create();
                                bb.addreserv(ann,a,b);
                                removeAll();
                            }}
                        else if (Double.isSelected()&& Tauxd.isSelected()){
                            ServiceReservHebergement bb =new ServiceReservHebergement();
                            
                            int     cc=Integer.valueOf(nbrjour.getText());
                            int vv=Integer.valueOf(nbrplace.getText());
                            int prix;
                            prix=cc*vv*d*td;
                            
                            ann.setNbrplace(Integer.valueOf(nbrplace.getText()));
                            ann.setPrix(prix);
                            
                            
                            ann.setNbrjour(Integer.valueOf(nbrjour.getText()));
                            ann.setTypechambre("Double");
                            ann.setTauxchambre("Tauxdemi");
                            
                            boolean az;
                            az=Dialog.show("", "Prix de votre reservation est" +prix, "Valider", "Annuler");
                            if(az){
                                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                                
                                Message message = Message
                                        .creator(new PhoneNumber("+21625179742"), new PhoneNumber("+12182765419"),
                                                "Votre reservation a été prise en compte " )
                                        .create();
                                bb.addreserv(ann,a,b);
                                removeAll();
                            }}
                    }
                    
                }
                else{
                    Dialog.show("erreur", "veuillez remplir tous les champs", "ok",null);
                }   }
        });
        rec.addActionListener(e->{
            new SendReclamationAdmin(res).show();
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
