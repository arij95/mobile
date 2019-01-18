/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Hebergement;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Voyageorganise;
import Entity.User2;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import entites.UserCo;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class ServiceVoyageorganise {
    public void ajoutVoyageorganise(Voyageorganise he){
    ConnectionRequest con = new ConnectionRequest();
   
        String Url ="http://localhost/pidev/ajout_voyageorganise.php?"
               
                +"&prix_voyage=" + he.getPrix_voyage()
                + "&date_depart=" + he.getDate_depart()
                + "&date_retour="+ he.getDate_retour()
                + "&origine=" + he.getOrigine()
                + "&pays_destination=" + he.getPays_destination()
                + "&ville_destination=" + he.getVille_destination()
                + "&nb_places=" + he.getNb_places()
                + "&hotel=" + he.getHotel()
                +"&id_agence=" + UserCo.userCo.getId()
                 + "&nom_agence=" + he.getNom_agence()
                +"&image=" +he.getImage()
                
              ;
         con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
  
    /*  ArrayList<Voyageorganise> listVoyageorganise = new ArrayList<>();

      public ArrayList<Voyageorganise> getListVoyageorganise(int id){ 
         // id=User.connected.getNd();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/affich_voyageorganise.php?id_agence="+ id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                listVoyageorganise = getListVoyageorganise(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVoyageorganise ;
    }*/
     ArrayList<Voyageorganise> listVoyageorganise = new ArrayList<>();

      public ArrayList<Voyageorganise> getListVoyageorganise(int id){ 
         // id=User.connected.getNd();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/affich_voyageorganise.php?id_agence="+ id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                listVoyageorganise = getListVoyageorganisee(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVoyageorganise ;
      }
      public ArrayList<Voyageorganise> getListVoyageorganisee(String json) {

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
                 float bbb =  Float.parseFloat((obj.get("id").toString()));
 a.setId((int)bbb);
                 float aaa =  Float.parseFloat((obj.get("id_agence").toString()));
                 
                a.setId_agence((int)aaa);
              
                a.setNom_agence(obj.get("nom_agence").toString());
                float baa =  Float.parseFloat((obj.get("prix_voyage").toString()));
                a.setPrix_voyage((int)baa);
                a.setDate_depart(obj.get("date_depart").toString());
                   a.setDate_retour(obj.get("date_retour").toString());
                   a.setOrigine(obj.get("origine").toString());
                   a.setPays_destination(obj.get("pays_destination").toString());
                   a.setVille_destination(obj.get("ville_destination").toString());
                 
                 float eaaa =  Float.parseFloat((obj.get("nb_places").toString()));
                a.setNb_places((int)eaaa);
               
                
                a.setHotel(obj.get("hotel").toString());
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
    
    
    
      public void setVoyageorganise(Voyageorganise h , int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/Set_voyageorganise.php?id="
                +h.getId()
                +"&id_agence="+ h.getId_agence()
                +"&nom_agence="+ h.getNom_agence()
                +"&prix_voyage="+ h.getPrix_voyage()
                +"&date_depart="+ h.getDate_depart()
                +"&date_retour="+h.getDate_retour()
                +"&origine="+ h.getOrigine()
                +"&pays_destination="+ h.getPays_destination()
                +"&ville_destination="+ h.getVille_destination()
                +"&nb_places="+ h.getNb_places()
                +"&hotel="+ h.getHotel()
                +"&image="+ h.getImage()
        );  
      
                NetworkManager.getInstance().addToQueueAndWait(con);
 
    }
      
     public void supprimerVoyageorganise(int id ){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/Delete_voyageorganise.php?id="+id);  
       
                NetworkManager.getInstance().addToQueueAndWait(con);

    }

 
   ArrayList<Voyageorganise> listVoyageorganiise = new ArrayList<>();
      
       public ArrayList<Voyageorganise> getList5(){ 
          
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/affichpourreserv_voyageorganise.php?");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                listVoyageorganiise = getListVoyageorganisee(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVoyageorganiise ;
    }
     
  
    }


