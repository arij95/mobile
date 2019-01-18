/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entites.Activite;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceTask {

    public void ajoutTask(Activite ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Holidaysnow01/web/app_dev.php/ajouteractivite.json";
        con.setUrl(Url);
        con.setPost(true);
        
        
        
        con.setContentType("application/json");        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(ta));
        
        con.setRequestBody(gson.toJson(ta));
        
        
        
        
        
        
        
        
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);                                                                                
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
 public void modifierTask(Activite ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Holidaysnow01/web/app_dev.php/modifieractivite/"+ta.getId()+".json";
        con.setUrl(Url);
        con.setPost(true);
        
        
        
        con.setContentType("application/json");        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(ta));
        
        con.setRequestBody(gson.toJson(ta));
        
        
        
        
        
        
      
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);                                                                                
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  public void supprimerTask(Activite ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Holidaysnow01/web/app_dev.php/supprimeractivite/"+1+".json";
        con.setUrl(Url);
        con.setPost(true);
        
        
        
        con.setContentType("application/json");        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(ta));
        
        con.setRequestBody(gson.toJson(ta));
        
        
        
        
        
        
        
        
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);                                                                                
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Activite> getListTask(String json) {

        ArrayList<Activite> listTasks = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();
            System.out.println(json);
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                
                Activite e = new Activite();
                float id = Float.parseFloat(obj.get("idActivite").toString());
                System.out.println((int)id);
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setType(obj.get("type").toString());
                e.setAdresse(obj.get("adresse").toString());
                e.setPays(obj.get("pays").toString());
                e.setRegion ((obj.get("region").toString()));
                e.setDescription(obj.get("description").toString());
                e.setPrix((float) Float.parseFloat(obj.get("prix").toString()));
                e.setPlacedisponible ((int) Float.parseFloat(obj.get("placedisponible").toString()));
                e.setIdAgence((int) Float.parseFloat(obj.get("idagence").toString()));
                System.out.println(e);
                listTasks.add(e);
            }

        } catch (IOException ex) {
        }
        return listTasks;

    }
    
    
    ArrayList<Activite> listTasks = new ArrayList<>();
    
    public ArrayList<Activite> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Holidaysnow01/web/app_dev.php/afficheractresr.json");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listTasks = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
 public ArrayList<Activite> getListActiviteParIdAgence(String json) {

        ArrayList<Activite> listTasks = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();
            System.out.println(json);
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                
                Activite e = new Activite();
                float id = Float.parseFloat(obj.get("idActivite").toString());
                System.out.println((int)id);
                e.setId((int) id);
                e.setNom(obj.get("nom").toString());
                e.setType(obj.get("type").toString());
                e.setAdresse(obj.get("adresse").toString());
                e.setPays(obj.get("pays").toString());
                e.setRegion ((obj.get("region").toString()));
                e.setDescription(obj.get("description").toString());
                e.setPrix((float) Float.parseFloat(obj.get("prix").toString()));
                e.setPlacedisponible ((int) Float.parseFloat(obj.get("placedisponible").toString()));
                e.setIdAgence((int) Float.parseFloat(obj.get("idagence").toString()));
                System.out.println(e);
                listTasks.add(e);
            }

        } catch (IOException ex) {
        }
        return listTasks;

    }
  public ArrayList<Activite> getList3(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Holidaysnow01/web/app_dev.php/afficherctiviteparid.json"); 
        con.setPost(true);
        con.setContentType("application/json");        
        con.setRequestBody("{\"id\":"+"\""+id+"\""+"}");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                listTasks = ser.getListActiviteParIdAgence(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
}
