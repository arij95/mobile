/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Restaurant;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import entites.UserCo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static main.Config.*;


public class RestaurantService {
    
    
    public void addRestaurant(Restaurant r){
         String url = RESTAURANT_API+"add";
        ConnectionRequest connReq = new ConnectionRequest();
        connReq.setPost(true);
        connReq.setUrl(url);
        connReq.setHttpMethod("POST");
        connReq.addArgument("nom", r.getNom());
        connReq.addArgument("type",r.getType());
        connReq.addArgument("adresse",r.getAdresse());
        connReq.addArgument("specialite",r.getSpecialite());
        connReq.addArgument("place",String.valueOf(r.getPlace()));
        connReq.addArgument("agence",String.valueOf(UserCo.userCo.getId()));
        NetworkManager.getInstance().addToQueueAndWait(connReq);
        System.out.println("added Succefully");
    }
    
     public void sendMail(){
       
         Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                            "Check out Codename One at https://www.codenameone.com/");
         System.out.println("success: "+success);
    }
    
    public void modifyRestaurant(Restaurant r){
         String url = RESTAURANT_API+"modify";
        ConnectionRequest connReq = new ConnectionRequest();
        connReq.setPost(true);
        connReq.setUrl(url);
        connReq.setHttpMethod("POST");
        connReq.addArgument("id",String.valueOf(r.getId()));
        connReq.addArgument("nom", r.getNom());
        connReq.addArgument("type",r.getType());
        connReq.addArgument("adresse",r.getAdresse());
        connReq.addArgument("specialite",r.getSpecialite());
        connReq.addArgument("place",String.valueOf(r.getPlace()));
        connReq.addArgument("agence",String.valueOf(UserCo.userCo.getId()));
        NetworkManager.getInstance().addToQueueAndWait(connReq);
        System.out.println("added Succefully");
    }
    
    public boolean deleteRestaurant(Restaurant r){
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"delete/"+r.getId();
        if(r.getIdagence() == UserCo.userCo.getId()){
             con.setUrl(Url);
             NetworkManager.getInstance().addToQueueAndWait(con);
             System.out.println("deleted Succefully");
             return true;
        }
            System.out.println("can't delete : not owner");
            return false;
    }
    
    public List<Restaurant> getList() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"all";

        con.setUrl(Url);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> tasks = jsonp
                        .parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                if(tasks.get("root")!=null){
                     List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for(Map<String, Object> e : list) {
                        restaurants.add(fillData(e));
                    }
                }
               
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return restaurants;
    }
    
     public List<Restaurant> rechercheList(String word) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"find/"+word;
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> tasks = jsonp
                        .parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                if(tasks.get("root")!=null){
                     List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for(Map<String, Object> e : list) {
                        restaurants.add(fillData(e));
                    }
                }
               
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return restaurants;
    }
    
    
     public void updateRestaurant(Restaurant restaurant){
        ConnectionRequest connReq = new ConnectionRequest();
        String Url = RESTAURANT_API+"update";
        connReq.setPost(true);
        connReq.setUrl(Url);
        connReq.setHttpMethod("POST");
        connReq.addArgument("id_resto",String.valueOf(restaurant.getId()));
        connReq.addArgument("nom_resto",restaurant.getNom());
        connReq.addArgument("specialite_resto",restaurant.getSpecialite());
        connReq.addArgument("nb_places_tot_resto",String.valueOf(restaurant.getPlace()));
        connReq.addArgument("_id_agence",String.valueOf(restaurant.getIdagence()));
        connReq.addArgument("type_resto",restaurant.getType());
        connReq.addArgument("adresse_resto",restaurant.getAdresse());

        NetworkManager.getInstance().addToQueueAndWait(connReq);
    }
    
    public Restaurant fillData(Map<String,Object> e){
          Restaurant restaurant = new Restaurant();
          restaurant.setId(((Double)e.get("id_resto")).intValue());
          restaurant.setPlace(((Double)e.get("nb_places_tot_resto")).intValue());
          restaurant.setIdAgence(((Double)e.get("_id_agence")).intValue());
          restaurant.setNom((String)e.get("nom_resto"));
          restaurant.setSpecialite((String)e.get("specialite_resto"));
          restaurant.setAdresse((String)e.get("adresse_resto"));
          restaurant.setType((String)e.get("type_resto"));
          return restaurant;
    }
    
    public void likeAction(Restaurant r, boolean action){
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"like/"+UserCo.userCo.getId()+"/"+r.getId();
        if(!action){
           Url = RESTAURANT_API+"dislike/"+UserCo.userCo.getId()+"/"+r.getId(); 
           con.setUrl(Url);
           NetworkManager.getInstance().addToQueueAndWait(con);
        }else{
           con.setUrl(Url);
           NetworkManager.getInstance().addToQueueAndWait(con);
        }
        
    }
    
    public boolean isLiked(Restaurant r){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"isliked/"+UserCo.userCo.getId()+"/"+r.getId();
        final boolean[] liked = {false};
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            String response = new String(con.getResponseData());
            if(response.equals("\"true\"")){
                liked[0] = true;
            }
            else
                liked[0] = false;
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return liked[0];
        
    }
    
    public List<Restaurant> getFavoris() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"favoris/"+UserCo.userCo.getId();

        con.setUrl(Url);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            
            try {
                Map<String, Object> tasks = jsonp
                        .parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                if(tasks.get("root")!=null){
                     List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for(Map<String, Object> e : list) {
                        restaurants.add(fillData(e));
                    }
                }
               
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return restaurants;
    }
    
   
}
