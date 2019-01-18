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
import entites.ReservationVols;
import entites.UserCo;
import services.ServiceReservationVol;
import services.UserService;
import Service.emailVol;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.regex.*;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class ReservationVolForm extends BaseForm {

    public ReservationVolForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb=new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("HOLIDAYSNOW");
        setUIID("ReservationVol");
        super.addSideMenu(res);
       getContentPane().setScrollVisible(false);
       Label label = new Label("Welcome \n" + UserCo.userCo.getUsername());
       label.setUIID("LabelWelcome");
        add(BorderLayout.CENTER, label);
              TextField tnbreplace = new TextField("","Nombres de places");
add(BorderLayout.CENTER_BEHAVIOR_TOTAL_BELOW,tnbreplace);
     Button btnreservervol = new Button("reserver un vol");
add(BorderLayout.SOUTH,btnreservervol);
  btnreservervol.addActionListener((ActionEvent e) -> {
      emailVol s=new emailVol(); 
           s.sendMail();
      
      if(( !isInteger(tnbreplace.getText())||((int) Float.parseFloat(tnbreplace.getText())<=0) ) )
            {
                                ToastBar.showInfoMessage("verifier le nbre de place").show();

                }
      else
      if(UserCo.nbplace==0||(UserCo.nbplace<(int) Float.parseFloat(tnbreplace.getText())))
      {
                                          ToastBar.showInfoMessage("il n'y a pas de place disponible").show();

      }
      else
      
      { ServiceReservationVol ser = new ServiceReservationVol();
            ReservationVols t = new ReservationVols();
            t.setId_vol(UserCo.idAct);
            t.setId_user(UserCo.userCo.getId());
            t.setNbredeplace((int) Float.parseFloat(tnbreplace.getText()));
            ser.ajoutTask(t);
          
           
      
                                      ToastBar.showInfoMessage("reservation avec succes ").show();

      }
        });
        

       
    }
    
}
