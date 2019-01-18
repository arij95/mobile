/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.cleanmodern.ModifierVolForm.isInteger;
import com.twilio.Twilio;
import entites.ReservationActivite;
import entites.UserCo;
import services.ServiceReservationActivite;
import services.UserService;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class ReservationActiviteForm extends BaseForm {
    public static final String ACCOUNT_SID = "AC8e036098d97a5901324606f509b29256";
    public static final String AUTH_TOKEN = "aab3d1636670275481cfc54601ebc47e";

    public ReservationActiviteForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb=new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("HOLIDAYSNOW");
        setUIID("ReservationActivite");
        super.addSideMenu(res);
       getContentPane().setScrollVisible(false);
       Label label = new Label("Welcome \n" + UserCo.userCo.getUsername());
       label.setUIID("LabelWelcome");
        add(BorderLayout.CENTER, label);
              TextField tnbreplace = new TextField("","Nombres de places");
add(BorderLayout.CENTER_BEHAVIOR_TOTAL_BELOW,tnbreplace);
     Button btnreserveractivite = new Button("reserver une activite");
add(BorderLayout.SOUTH,btnreserveractivite);
  btnreserveractivite.addActionListener((e) -> {
     
      
      if(( !isInteger(tnbreplace.getText())||((int) Float.parseFloat(tnbreplace.getText())<=0) ) )
            {
                                ToastBar.showInfoMessage("Nbre de place insuffisant").show();

                }
      else if
      
          (UserCo.nbplace==0||(UserCo.nbplace<(int) Float.parseFloat(tnbreplace.getText())))
      {
                                          ToastBar.showInfoMessage("il n'y a pas de place disponible").show();

      }
          else
          {
            ServiceReservationActivite ser = new ServiceReservationActivite();
            ReservationActivite t = new ReservationActivite();
            t.setId_activite(UserCo.idAct);
            t.setId_user(UserCo.userCo.getId());
            t.setNbredeplace((int) Float.parseFloat(tnbreplace.getText()));
            ser.ajoutTask(t);
             Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                    Message message = Message
                            .creator(new PhoneNumber("+21627130976"), new PhoneNumber("+13143107633"),
                                    "Votre reservation a bien ete prise en compte " )
                            .create();
  
                                        ToastBar.showInfoMessage("Reservation avec succes et vous recevez un msg").show();

  }
        });
        

       
    }
    
}
