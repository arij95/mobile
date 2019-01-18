/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Hebergement;
import Entity.User2;
import entites.UserCo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class ServiceHebergement {
    public void ajoutHebergement(Hebergement he){
    ConnectionRequest con = new ConnectionRequest();
   
        String Url ="http://localhost/pidev/ajoutHebergement.php?"
                +"&idAgence=" + UserCo.userCo.getId()
                + "&nomAgence=" + he.getNomAgence()
                +"&type_Hebergement=" + he.getType_Hebergement()
                + "&nom_Hebergement=" + he.getNom_Hebergement() 
                + "&nombre_etoile="+ he.getNombre_etoile() 
                + "&Adresse_Hebergement=" + he.getAdresse_Hebergement()
                + "&nombre_chambre=" + he.getNombre_chambre() 
                + "&prix_single=" + he.getPrix_single() 
                + "&prix_double=" + he.getPrix_double()
                + "&taux_demi=" + he.getTaux_demi()
                + "&taux_complet=" + he.getTaux_complet() 
                + "&tel=" + he.getTel() 
                + "&description=" + he.getDescription()
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
    
     public ArrayList<Hebergement> getListHebergement(String json) {

        ArrayList<Hebergement> listAnnonces = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                  Hebergement a = new Hebergement();

                // System.out.println(obj.get("id"));
                 float bbb =  Float.parseFloat((obj.get("idHebergement").toString()));
 a.setIdHebergement((int)bbb);
                 float aaa =  Float.parseFloat((obj.get("idAgence").toString()));
                 
                a.setIdAgence((int)aaa);
              
                a.setNomAgence(obj.get("nomAgence").toString());
               a.setType_Hebergement(obj.get("type_Hebergement").toString());
                a.setNom_Hebergement(obj.get("nom_Hebergement").toString());
                
               
                float baa =  Float.parseFloat((obj.get("nombre_etoile").toString()));
                a.setNombre_etoile((int)baa);
                a.setAdresse_Hebergement(obj.get("Adresse_Hebergement").toString());
                 float baaa =  Float.parseFloat((obj.get("nombre_chambre").toString()));
                a.setNombre_chambre((int)baaa);
                float zaa =  Float.parseFloat((obj.get("prix_single").toString()));
                a.setPrix_single((int)zaa);
                 float zaaa =  Float.parseFloat((obj.get("prix_double").toString()));
                a.setPrix_double((int)zaaa);
                float eaa =  Float.parseFloat((obj.get("taux_demi").toString()));
                a.setTaux_demi((int)eaa);
                 float eaaa =  Float.parseFloat((obj.get("taux_complet").toString()));
                a.setTaux_complet((int)eaaa);
                float raa =  Float.parseFloat((obj.get("tel").toString()));
                a.setTel((int)raa);
                
                a.setDescription(obj.get("description").toString());
                a.setImage(obj.get("image").toString());
           
                System.out.println(a);
                listAnnonces.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(listAnnonces);
        return listAnnonces;

    }
  
      ArrayList<Hebergement> listHebergementt = new ArrayList<>();

      public ArrayList<Hebergement> getListHebergement(int id){ 
         // id=User.connected.getNd();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/affichHebergement.php?idAgence="+ id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                listHebergementt = getListHebergement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listHebergementt ;
    }
     
      
      public void setHeberg(Hebergement h , int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/SetHeberg.php?idHebergement="+h.getIdHebergement()
                +"&idAgence="+ h.getIdAgence()
                +"&nomAgence="+ h.getNomAgence()
                +"&type_Hebergement="+ h.getType_Hebergement()
                +"&nom_Hebergement="+ h.getNom_Hebergement()
                +"&nombre_etoile="+h.getNombre_etoile()
                +"&Adresse_Hebergement="+ h.getAdresse_Hebergement()
                +"&nombre_chambre="+ h.getNombre_chambre()
                +"&prix_single="+ h.getPrix_single()
                +"&prix_double="+ h.getPrix_double()
                +"&taux_demi="+ h.getTaux_demi()
                +"&taux_complet="+ h.getTaux_complet()
                +"&tel="+ h.getTel()
                +"&description="+ h.getDescription()
                +"&image="+ h.getImage()
        );  
      
                NetworkManager.getInstance().addToQueueAndWait(con);

    }
      
     public void supprimerHebergment(int id ){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/DeleteHeberg.php?idHebergement="+id);  
       
                NetworkManager.getInstance().addToQueueAndWait(con);

    }

 
     

  
    }


