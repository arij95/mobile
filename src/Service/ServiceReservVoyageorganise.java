/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Hebergement;
import Entity.Voyageorganise;
import Entity.ReservationHebergement;
import Entity.ReservationVoyageorganise;
import Entity.User2;
import Entity.Voyageorganise;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import entites.UserCo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class ServiceReservVoyageorganise {
      public ArrayList<Voyageorganise> getListVoyageorganise(String json) {

        ArrayList<Voyageorganise> listAnnonces = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                  Voyageorganise a = new Voyageorganise();

                // System.out.println(obj.get("id"));
                 float bbb =  Float.parseFloat((obj.get("idVoyageorganise").toString()));
 a.setId((int)bbb);
                 float aaa =  Float.parseFloat((obj.get("idAgence").toString()));
                 
                a.setId_agence((int)aaa);
              //a.setDate_depart(obj.get("date depart").toString());
              //a.setDate_retour(obj.get("date retour").toString());
                a.setNom_agence(obj.get("nomAgence").toString());
               a.setOrigine(obj.get("origine").toString());
                a.setPays_destination(obj.get("Pays destination").toString());
                a.setVille_destination(obj.get("ville destination").toString());
               a.setHotel(obj.get("hotel").toString());
                float baa =  Float.parseFloat((obj.get("Prix").toString()));
                a.setPrix_voyage((int)baa);
               
                 float baaa =  Float.parseFloat((obj.get("nombre_chambre").toString()));
                a.setNb_places((int)baaa);
                
                a.setImage(obj.get("image").toString());
               // a.setDate_annonce(obj.get("date_annonce").getEditor().toString());

                System.out.println(a);
                listAnnonces.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(listAnnonces);
        return listAnnonces;

    }
    
    
    
     

    
    
   
       
        public void addreserv(ReservationVoyageorganise he, int id,int ind){
    ConnectionRequest con = new ConnectionRequest();
           
        String Url ="http://localhost/pidev/addreserv_voyageorganise.php?"
                
                + "&idAgence=" + id
                +"&idUtilisateur=" + UserCo.userCo.getId()
                +"&idVoyageorganise=" + ind
                 
                + "&nb_places=" + he.getNbrplace()
               
                + "&prix=" + he.getPrix()
               
                
                
              ;
         con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//          
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      public ArrayList<ReservationVoyageorganise> getListReservVoyageorganise(String json) {

        ArrayList<ReservationVoyageorganise> listVoyageorgreserv = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                  ReservationVoyageorganise a = new ReservationVoyageorganise();

                // System.out.println(obj.get("id"));
                 float bbb =  Float.parseFloat((obj.get("Id_reservation").toString()));
 a.setId_reservation((int)bbb);
                 float aaa =  Float.parseFloat((obj.get("id_user").toString()));
                 
                a.setId_user((int)aaa);
              
                float baa =  Float.parseFloat((obj.get("id_agence").toString()));
                a.setId_agence((int)baa);
               
                 float braaa =  Float.parseFloat((obj.get("nbrplace").toString()));
                a.setNbrplace((int)braaa);
                float btaa =  Float.parseFloat((obj.get("prix").toString()));
                a.setPrix((int)btaa);
               
                 float baaa =  Float.parseFloat((obj.get("idVoyageorganise").toString()));
                a.setIdVoyageorganise((int)baaa);
               
                System.out.println(a);
                listVoyageorgreserv.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(listVoyageorgreserv);
        return listVoyageorgreserv;

    }
    
     ArrayList<ReservationVoyageorganise> listrervevo = new ArrayList<>();
      
       public ArrayList<ReservationVoyageorganise> getListmesvo(int id){ 
          
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/MesReservationVoyageorg.php?id_user=" +id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                listrervevo = getListReservVoyageorganise(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listrervevo ;
    }
    public void supprimerreservvoyageorg(int id ){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/supprimerReservationVoyageorg.php?Id_reservation="+id);  
       
                NetworkManager.getInstance().addToQueueAndWait(con);

    }    
}
