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
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import Service.UserService2;
import Entity.User2;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {
     
    public SignUpForm(Resources res) {
        
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        
        TextField nom = new TextField("", "nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
       
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmpassword = new TextField("", "Password", 20, TextField.PASSWORD);


        
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
      
        confirmpassword.setSingleLineTextArea(false);

        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        next.addActionListener(e -> previous.showBack());
        next.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
               
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmpassword),
                createLineSeparator()
                 
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
               
                if ( nom.getText().equals("")||nom.getText().length()<3){
                Dialog.show("erreur", "le nom doit contenir au moins 3 caracteres", "ok", null);
                        }
                else if ( prenom.getText().equals("")||prenom.getText().length()<3){
                Dialog.show("erreur", "le prenom doit contenir au moins 3 caracteres", "ok", null);
                        }
               
                else if ( !Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}$").matcher(email.getText()).find()){
                Dialog.show("erreur", "veuillez choisir un email valide", "ok", null);
                        }
                else if(username.getText().equals("")||username.getText().length()<4){
                Dialog.show("erreur", "username doit contenir au moins 4 caracteres", "ok", null);
                }
                else if ( password.getText().equals("")||password.getText().length()<4){
                Dialog.show("erreur", "password doit contnir au moins 4 caracteres", "ok", null);
                        }
                
                else if (!(password.getText().equals(confirmpassword.getText()))){
                Dialog.show("erreur", "mot de passe n'est pas identique", "ok", null);
                
                }
                
                else{
                    
                UserService2 uu =new UserService2();
                User2 u = new User2();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date datesys = new Date();
        u.setLast_login(datesys);
                u.setUsername(username.getText());
                u.setNom(nom.getText());
                u.setPrenom(prenom.getText());
                u.setEmail(email.getText());
                String hashed=BCrypt.hashpw(password.getText(), BCrypt.gensalt(13));
                
             String s = hashed;
             // Remplace le 3 (position 2) par un 4
             int positionRemplacee = 2;
             char nouveauChar = 'y';
             s = s.substring(0, positionRemplacee) + nouveauChar + s.substring(positionRemplacee+1);
             
                u.setMdp(s);
               
                u.setRole("a:0:{}");
                uu.adduser(u);
              uu.adduser(u);
                


                } 
            }
        });
        
        
    }

    
}
