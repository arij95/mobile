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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entites.Vols;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jdk.nashorn.internal.objects.NativeString;
/**
 *
 * @author sana
 */
public class ServiceVol {

    public void ajoutTask(Vols ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Holidaysnow01/web/app_dev.php/ajoutervol.json";
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
    
    
    
    
    
    
    
    
 public void modifierTask(Vols ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Holidaysnow01/web/app_dev.php/modifiervol/"+ta.getId_vol()+".json";
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
 
 
 
 
 
 
  public void supprimerTask(Vols ta) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/Holidaysnow01/web/app_dev.php/suppvol/"+10+".json";
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

  
  
  
  
  
  
  
  
  
  
  
    public ArrayList<Vols> getListVol(String json) {

        ArrayList<Vols> listTasks = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();
            System.out.println(json);
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
 Vols e = new Vols();
                float id = Float.parseFloat(obj.get("idVol").toString());
                System.out.println((int)id);
                String s,s1,s2,s3;
                s=(obj.get("dateDepart").toString());
                s=NativeString.slice(s, 114, s.length()-3);
                s1="1"+s+"00";
                if(s1.length()<10)
                {
                    s1=s1+"0";
                }
                Long time;
                time=Long.parseLong(s1);
                Date d = new Date(time*1000 );
                 s2=(obj.get("dateArrive").toString());
                s2=NativeString.slice(s2, 114, s.length()-3);
                s3="1"+s2+"00";
                if(s3.length()<10)
                {
                    s3=s3+"0";
                }
                Long time2;
                time2=Long.parseLong(s3);
                Date d2 = new Date(time2 *1000);
                e.setDate_depart(d);
                e.setDate_arrive(d2);
                e.setId_vol((int) id);
                e.setVille_depart(obj.get("villeDepart").toString());
                e.setVille_arrive(obj.get("villeArrive").toString());
                e.setType_vol((obj.get("type").toString()));
                e.setDescription(obj.get("description").toString());
                e.setPrix((float) Float.parseFloat(obj.get("prix").toString()));
                e.setPlacedisp((int) Float.parseFloat(obj.get("nbPlaces").toString()));
                e.setId_agence((int) Float.parseFloat(obj.get("idAgence").toString()));
                System.out.println(e);
                listTasks.add(e);
                System.out.println(s1);
                System.out.println(time);
                DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(f.format(d));
            }

        } catch (IOException ex) {
        }
        return listTasks;

    }
    
    
    ArrayList<Vols> listTasks = new ArrayList<>();
    
    public ArrayList<Vols> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Holidaysnow01/web/app_dev.php/afficheVol.json");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceVol ser = new ServiceVol();
                listTasks = ser.getListVol(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
    
    
     
    
    
    
    
    
    
    
 public ArrayList<Vols> getListVolParIdAgence(String json) {

        ArrayList<Vols> listTasks = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();
            System.out.println(json);
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Set <String> employes = obj.keySet();
                for( String e : employes)
                {    System.out.println(e+" "+obj.get(e));}


                Vols e = new Vols();
                float id = Float.parseFloat(obj.get("idVol").toString());
                System.out.println((int)id);
                String s,s1,s2,s3;
                s=(obj.get("dateDepart").toString());
                s=NativeString.slice(s, 114, s.length()-3);
                s1="1"+s+"00";
                if(s1.length()<10)
                {
                    s1=s1+"0";
                }
                Long time;
                time=Long.parseLong(s1);
                Date d = new Date(time*1000 );
                 s2=(obj.get("dateArrive").toString());
                s2=NativeString.slice(s2, 114, s.length()-3);
                s3="1"+s2+"00";
                if(s3.length()<10)
                {
                    s3=s3+"0";
                }
                Long time2;
                time2=Long.parseLong(s3);
                Date d2 = new Date(time2 *1000);
                e.setDate_depart(d);
                e.setDate_arrive(d2);
                e.setId_vol((int) id);
                e.setVille_depart(obj.get("villeDepart").toString());
                e.setVille_arrive(obj.get("villeArrive").toString());
                e.setType_vol((obj.get("type").toString()));
                e.setDescription(obj.get("description").toString());
                e.setPrix((float) Float.parseFloat(obj.get("prix").toString()));
                e.setPlacedisp((int) Float.parseFloat(obj.get("nbPlaces").toString()));
                e.setId_agence((int) Float.parseFloat(obj.get("idAgence").toString()));
                System.out.println(e);
                listTasks.add(e);
                System.out.println(s1);
                System.out.println(time);
                DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(f.format(d));
  
            }

        } catch (IOException ex) {
        }
        return listTasks;

    }
  public ArrayList<Vols> getList3(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Holidaysnow01/web/app_dev.php/index4.json"); 
        con.setPost(true);
        con.setContentType("application/json");        
        con.setRequestBody("{\"id\":"+"\""+id+"\""+"}");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceVol ser = new ServiceVol();
                listTasks = ser.getListVolParIdAgence(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
}
