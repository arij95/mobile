/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.cleanmodern.ActiviteForm;
import com.codename1.uikit.cleanmodern.Main;
import entites.User;
import entites.UserCo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author challakh
 */
public class UserService {
    
    public User getUserCo(String json) {

        UserCo.userCo = new User();
        
        try {
            User user = new User();   
            UserCo.userCo.setId(0);

            JSONParser j = new JSONParser();
            System.out.println(json);
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
          
            if ( tasks.get("message").equals("failed")){
                ToastBar.showInfoMessage("Login failed").show();
            } else {
            float id = Float.parseFloat(tasks.get("id").toString());
            user.setId((int)id);
            user.setUsername(tasks.get("username").toString());
            user.setEmail(tasks.get("email").toString());
            user.setPassword(tasks.get("password").toString());

            user.setRoles(tasks.get("roles").toString());

            UserCo.userCo = user;
            
            return user;
            }
        } catch (IOException ex) {
        }
           return null;
    }
    
    User user;
    
  public User login(String username, String password){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/login.php"); 
        con.setPost(true);
        con.addArgument("username", username);
        con.addArgument("password", password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {                
                UserService us=new UserService();
                user = us.getUserCo(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return user;
    }
    
}
