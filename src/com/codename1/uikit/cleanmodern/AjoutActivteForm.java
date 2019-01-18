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
import entites.Activite;
import entites.Vols;
import services.ServiceTask;
import services.ServiceVol;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class AjoutActivteForm extends BaseForm {

    public AjoutActivteForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                

        TextField tnom = new TextField("", "Description", 20, TextField.ANY);
        TextField tadd = new TextField("", "Adresse", 20, TextField.ANY);
        TextField tdesc = new TextField("", "Description", 20, TextField.ANY);
        TextField tpays = new TextField("", "Pays", 20, TextField.ANY);
        TextField tplace = new TextField("", "Place disponible", 20, TextField.ANY);
        TextField treg = new TextField("", "Region", 20, TextField.ANY);
        TextField ttype = new TextField("", "Type", 20, TextField.ANY);
        TextField tprix = new TextField("", "Prix", 20, TextField.ANY);
        
        
        tnom.setSingleLineTextArea(false);
        tadd.setSingleLineTextArea(false);
        tdesc.setSingleLineTextArea(false);
        tpays.setSingleLineTextArea(false);
         tplace.setSingleLineTextArea(false);
        treg.setSingleLineTextArea(false);
        ttype.setSingleLineTextArea(false);
        tprix.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(tnom),
                createLineSeparator(),
                new FloatingHint(tadd),
                createLineSeparator(),
                new FloatingHint(tdesc),
                createLineSeparator(),
                new FloatingHint(tpays),
                createLineSeparator(),
                new FloatingHint(tplace),
                createLineSeparator(),
                new FloatingHint(treg),
                createLineSeparator(),
                new FloatingHint(ttype),
                createLineSeparator(),
                new FloatingHint(tprix),
                createLineSeparator()
                
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e ->{
        if((tadd.getText().equals(""))||(tdesc.getText().equals(""))||(tpays.getText().equals(""))||(tplace.getText().equals(""))||(treg.getText().equals(""))||(ttype.getText().equals(""))||(tprix.getText().equals("")))
                {
                                ToastBar.showInfoMessage("Tous les champs sont obligatoires").show();

                }
        else if(( !isInteger(tplace.getText())||((int) Float.parseFloat(tplace.getText())<=0) ) )
            {
                                ToastBar.showInfoMessage("verifier le nbre de place").show();

                }
         else if((!isFloat(tprix.getText()))|| Float.parseFloat(tprix.getText())<=0)
            {
                                ToastBar.showInfoMessage("verifier le prix").show();

                }
         else{
         ServiceTask ser = new ServiceTask();
          Activite t = new Activite(tnom.getText());
          t.setAdresse(tadd.getText());
          t.setDescription(tdesc.getText());
          t.setPays(tpays.getText());
          t.setPlacedisponible((int) Float.parseFloat(tplace.getText()));
          t.setRegion(treg.getText());
          t.setType(ttype.getText());
          t.setPrix((int) Float.parseFloat(tprix.getText()));
            ser.ajoutTask(t);
            
            
                                            ToastBar.showInfoMessage("Ajout avec succes").show();

         }
        });
    }
    
}
