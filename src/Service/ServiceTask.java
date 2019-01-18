/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Hebergement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author sana
 */
public class ServiceTask {

   
    
    
    
    
    
    
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
      
       public ArrayList<Hebergement> getList4(int id){ 
          //id=User.connected.getNd();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/pidev/affichHebergement.php?idAgence="+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listHebergementt = ser.getListHebergement(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listHebergementt ;
    }

      
      
      
    }

      
      
