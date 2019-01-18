package Form.Reservation;

import Entity.Reservation;
import Entity.Restaurant;
import static Form.Restauration.Ajout.isInteger;
import Service.ReservationService;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import entites.UserCo;
import java.util.Date;


public class Reserver extends BaseForm {
    
    ReservationService rs;

    public Reserver(Resources res,Restaurant r) {
        super("Réservation", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Réservation");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});

        rs = new ReservationService();
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        
        addTab(swipe, res.getImage("bg-holiday.jpg"), spacer1, "15 Likes  ", "Integer ut placerat purued non dignissim neque. ");

      
        Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        center.setScrollableY(true);
        
        Container likeContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        Button reserveBtn = new Button("Réserver");
        reserveBtn.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,15));
        reserveBtn.setUIID("btn-primary");
        
        Picker datePicker = new Picker();
        datePicker.getAllStyles().setFgColor(0x000000);
        datePicker.setType(Display.PICKER_TYPE_DATE);
        
        TextField nbTxt = new TextField();
        nbTxt.setHint("nombre place");
        nbTxt.getAllStyles().setFgColor(0x000000);
        nbTxt.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,12));
      
        center.add(datePicker);
        center.add(nbTxt);
        
        Component.setSameSize( spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe));
        add(likeContainer);
        add(center);
        add(reserveBtn);
        
        reserveBtn.addActionListener(e->{
            String nbtxt = nbTxt.getText();
            Reservation  resv = new Reservation();
            
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date = formatter.format(datePicker.getDate());
            if( !isInteger(nbtxt)||(nbtxt.equals(""))){
                ToastBar.showErrorMessage("Vérifiez le nombre de places !! ");
            }
            else
            {int nbplace = Integer.parseInt(nbtxt);
            resv.setNbPersonne(nbplace);
           
            
            resv.setDate(date);
            resv.setIdResto(r.getId());
            resv.setIdUser(UserCo.userCo.getId());
            
            
            Date dateCourant = new Date();
            Date d = datePicker.getDate();
            System.out.println(dateCourant.toString()+ " courant "+dateCourant.getTime());
            System.out.println(d.toString()+" date res "+d.getTime());

            long diff  = (long) (dateCourant.getTime() - d.getTime());
            System.out.println("diff "+diff);
            if(r.getPlace()<nbplace){
                ToastBar.showErrorMessage("Places insuffisantes");
            }else if(diff > 0 ){
                ToastBar.showErrorMessage("Veuillez choisir une date ultérieure");
            }
           else{
                rs.addReservation(resv);
                ToastBar.showErrorMessage("Email a été envoyé, veuillez consulter votre boite mail");
                new Reservations(res).show();
            }
 }
  
            
        });

        
    }
    
    public static boolean isInteger(String s) {
    try { 
        Integer.parseInt(s); 
    } catch(NumberFormatException | NullPointerException e) { 
        return false; 
    }
    // only got here if we didn't return false
    return true;
}
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
      
  
}
