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
import entites.UserCo;
import services.UserService;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class ClientHebergement extends BaseForm {

    public ClientHebergement(Resources res) {
        super( new BorderLayout());
        Toolbar tb=new Toolbar(true);
        setToolbar(tb);
        tb.setTitle("HOLIDAYSNOW");
        setUIID("ClientHeb");
        super.addSideMenu(res);
       getContentPane().setScrollVisible(false);
        
        Button btnajouter = new Button("Reserver");
add(BorderLayout.CENTER,btnajouter);
         Button btngerer = new Button("Mes Reservations");
add(BorderLayout.SOUTH,btngerer);
 btnajouter.addActionListener((e) -> {
            new affichepourreserv(res).show();
        });
       btngerer.addActionListener((e) -> {
            new MesreserHebergementForm(res).show();
        });
    }
    
}
