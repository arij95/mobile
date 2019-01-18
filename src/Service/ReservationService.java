/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Reservation;
import Entity.Restaurant;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import entites.UserCo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static main.Config.*;


public class ReservationService {
    
    
    public void addReservation(Reservation r){
        String url = RESTAURANT_API+"book";
        ConnectionRequest connReq = new ConnectionRequest();
        connReq.setPost(true);
        System.out.println(r.toString());
        connReq.setUrl(url);
        connReq.setHttpMethod("POST");
        connReq.addArgument("idr",String.valueOf(r.getIdResto()));
        connReq.addArgument("idu",String.valueOf(r.getIdUser()));
        connReq.addArgument("date",r.getDate());
        connReq.addArgument("nbp",String.valueOf(r.getNbPersonne()));
        NetworkManager.getInstance().addToQueueAndWait(connReq);

        EmailUtil.sendEmail(
                "Votre réservation dans le Restaurant <strong>"+r.getIdResto()
                        +"</strong> de "+r.getNbPersonne()+" places le "+r.getDate()
                        +" a été effectué avec succes"
        );
    }
      
    
    public boolean cancelReservation(Reservation r){
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"cancelBook/"+r.getId();
        if(r.getIdUser()== UserCo.userCo.getId()){
             con.setUrl(Url);
             NetworkManager.getInstance().addToQueueAndWait(con);
             System.out.println("canceled");
             return true;
        }
            System.out.println("can't cancel");
            return false;
    }
    
    public List<Reservation> getMyReservations() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"myReservations/"+UserCo.userCo.getId();

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
                        reservations.add(fillData(e));
                    }
                }
               
            } catch (IOException ex) {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return reservations;
    }
    
    
    public void getRestaurant(Restaurant r){
        
    }
     
    public Reservation fillData(Map<String,Object> e){
          Reservation r = new Reservation();
          r.setId(((Double)e.get("id_res")).intValue());
          r.setIdResto(((Double)e.get("id_resto")).intValue());
          r.setIdUser(((Double)e.get("_id_agence")).intValue());
          r.setNbPersonne(((Double)e.get("nb_personnes")).intValue());
          r.setDate((String)e.get("date_res"));
          return r;
    }
    
    public boolean isReserved(Restaurant r){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = RESTAURANT_API+"isReserved/"+UserCo.userCo.getId()+"/"+r.getId();
        final boolean[] liked = {false};
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent evt) -> {
            //listTasks = getListTask(new String(con.getResponseData()));
            JSONParser jsonp = new JSONParser();
            String response = new String(con.getResponseData());
            if(response.equals("true")){
                liked[0] = true;
            }
            else
                liked[0] = false;
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return liked[0];
        
    }
    
   
    
}
