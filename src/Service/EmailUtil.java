package Service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import static main.Config.*;
public class EmailUtil {

    
    public static  void sendEmail(String content){
    
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl(MAIL_API_URL);
        r.setPost(true);
        
        r.addRequestHeader("x-apikey", "bcb8c66bd822cc8a807b574c5f4a20e54da07");
        //r.addRequestHeader("x-apikey", "bcb8c66bd822cc8a807b574c5f4a20e54da07");

        r.addArgument("to", "islamnov94bf@gmail.com");
        r.addArgument("subject", "Reservation");        
        r.addArgument("html", content);
        r.addArgument("sendername", "Holiday Now");
        r.addArgument("company", "Holiday Now");
        
        NetworkManager.getInstance().addToQueueAndWait(r);
          
    }
}