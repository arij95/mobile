/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Service.HomeService;
import Service.RestaurantService;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;

public class Home extends BaseForm{
    
    HomeService homeService = new HomeService();
    public Home(Resources theme){

        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        RestaurantService restaurantService = new RestaurantService();
      
        super.addSideMenu(theme);

        
    }
    
}
