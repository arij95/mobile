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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.cleanmodern.ModifierVolForm.isFloat;
import static com.codename1.uikit.cleanmodern.ModifierVolForm.isInteger;
import entites.Vols;
import services.ServiceVol;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class AjoutVolForm extends BaseForm {

    public AjoutVolForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                

        TextField tdescv = new TextField("", "Description", 20, TextField.ANY);
        TextField tplacev = new TextField("", "Place disponible", 20, TextField.ANY);
        TextField ttypev = new TextField("", "Type", 20, TextField.ANY);
        TextField tprixv = new TextField("", "Prix", 20, TextField.ANY);
        TextField tvillearrive = new TextField("", "Ville d'arrive", 20, TextField.ANY);
        TextField tvilledepartv = new TextField("", "Ville de depart", 20, TextField.ANY);
        Picker datedepart = new Picker();
        Picker datearrive = new Picker();
        
        tdescv.setSingleLineTextArea(false);
        tplacev.setSingleLineTextArea(false);
        ttypev.setSingleLineTextArea(false);
        tprixv.setSingleLineTextArea(false);
         tvillearrive.setSingleLineTextArea(false);
        tvilledepartv.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(tdescv),
                createLineSeparator(),
                new FloatingHint(tplacev),
                createLineSeparator(),
                new FloatingHint(ttypev),
                createLineSeparator(),
                new FloatingHint(tprixv),
                createLineSeparator(),
                new FloatingHint(tvillearrive),
                createLineSeparator(),
                new FloatingHint(tvilledepartv),
                createLineSeparator(),
                datedepart,
                datearrive
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e ->{
         if((tdescv.getText().equals(""))||(tvillearrive.getText().equals(""))||(tvilledepartv.getText().equals(""))||(ttypev.getText().equals(""))||(tplacev.getText().equals(""))||(tprixv.getText().equals("")))
                {
                                ToastBar.showInfoMessage("Tous les champs sont obligatoires").show();

                }
        else if(( !isInteger(tplacev.getText())||((int) Float.parseFloat(tplacev.getText())<=0) ) )
            {
                                ToastBar.showInfoMessage("verifier le nbre de place").show();

                }
         else if((!isFloat(tprixv.getText()))|| Float.parseFloat(tprixv.getText())<=0)
            {
                                ToastBar.showInfoMessage("verifier le prix").show();

                }
         else{
         ServiceVol ser = new ServiceVol();
          Vols t = new Vols();
          t.setDate_depart(datedepart.getDate());
          t.setDate_arrive(datearrive.getDate());
          t.setDescription(tdescv.getText());
          t.setVille_arrive(tvillearrive.getText());
          t.setPlacedisp((int) Float.parseFloat(tplacev.getText()));
          t.setVille_depart(tvilledepartv.getText());
          t.setType_vol(ttypev.getText());
          t.setPrix((int) Float.parseFloat(tprixv.getText()));
            ser.ajoutTask(t);
                                         ToastBar.showInfoMessage("Ajout avec succes").show();

         }
        });
    }
                
    
}
