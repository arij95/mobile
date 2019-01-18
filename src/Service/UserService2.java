/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;

import Entity.User2;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author asus
 */
public class UserService2 {
    
    private ConnectionRequest connectionRequest;
    boolean test = false;

    public void adduser(User2 u) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Succes", "ajoute avec succes", "ok", null);

            }
        };
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date datesys = new Date();
        connectionRequest.setUrl("http://localhost/pidev/insert.php?"
                + "username=" + u.getUsername()
                 + "&email=" + u.getEmail()
                + "&password=" + u.getMdp()
               
                + "&last_login=" + dateFormat.format(datesys)
                + "&role=" + u.getRole()
                + "&name=" + u.getNom()
                + "&prenom=" + u.getPrenom()
        );
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
   /*  public boolean verification(String username, String password) {
        List<User2> u = new ArrayList<>();

        connectionRequest = new ConnectionRequest("http://localhost/pidev/login.php");
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        Map<String, Object> result = null;


        try {
            result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(connectionRequest.getResponseData()), "UTF-8"));
            List<Map<String, Object>> response = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : response) {
                u.add(new User2(
                        Integer.parseInt(obj.get("id").toString()),
                        
                        (String) obj.get("password"),
                        (String) obj.get("username"),
                       
                         (String) obj.get("roles")


                       
                ));
            }
            for (User2 user : u) {
                String s=user.getMdp();
                System.out.println("aaaaaaaaaaaaaa"+s);
                int positionRemplacee = 2;
             char nouveauChar = 'a';
             s = s.substring(0, positionRemplacee) + nouveauChar + s.substring(positionRemplacee+1);
                if (username.equals(user.getUsername())
                        &&BCrypt.checkpw(password,s)) {

                    System.out.println("it works!");
                    staticUser = new User2();
                    User2.setConnected(user);
                    
                    staticUser.setUsername(user.getUsername());
                    
                    staticUser.setMdp(user.getMdp());
                   // staticUser.setAdresse(user.getAdresse());
                    staticUser.setRole(user.getRole());
                    

                    test = true;
                    return true;
                }
            }
        } catch (IOException ex) {
            System.out.println("EXCEPTION : " + ex);

        }
        System.out.println("wrong ");
        return false;

    }*/
    


    
}
