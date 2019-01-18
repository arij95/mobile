/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.codename1.ui.Command;
import com.codename1.ui.util.Resources;
import java.util.Date;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class Voyageorganise {
    
  private int id;
  private int prix_voyage;
  private String date_depart;
  private String date_retour;
  private String origine;
  private String pays_destination;
  private String ville_destination;
  private int nb_places;
  private String hotel;
  private int id_agence;
  private String nom_agence;
  private String image;

    public Voyageorganise() {
    }

    @Override
    public String toString() {
        return "Voyageorganise{" + "id=" + id + ", prix_voyage=" + prix_voyage + ", date_depart=" + date_depart + ", date_retour=" + date_retour + ", origine=" + origine + ", pays_destination=" + pays_destination + ", ville_destination=" + ville_destination + ", nb_places=" + nb_places + ", hotel=" + hotel + ", id_agence=" + id_agence + ", nom_agence=" + nom_agence + ", image=" + image + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix_voyage() {
        return prix_voyage;
    }

    public void setPrix_voyage(int prix_voyage) {
        this.prix_voyage = prix_voyage;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public String getPays_destination() {
        return pays_destination;
    }

    public void setPays_destination(String pays_destination) {
        this.pays_destination = pays_destination;
    }

    public String getVille_destination() {
        return ville_destination;
    }

    public void setVille_destination(String ville_destination) {
        this.ville_destination = ville_destination;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public String getNom_agence() {
        return nom_agence;
    }

    public void setNom_agence(String nom_agence) {
        this.nom_agence = nom_agence;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Voyageorganise(int id, int prix_voyage, String date_depart, String date_retour, String origine, String pays_destination, String ville_destination, int nb_places, String hotel, int id_agence, String nom_agence, String image) {
        this.id = id;
        this.prix_voyage = prix_voyage;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
        this.origine = origine;
        this.pays_destination = pays_destination;
        this.ville_destination = ville_destination;
        this.nb_places = nb_places;
        this.hotel = hotel;
        this.id_agence = id_agence;
        this.nom_agence = nom_agence;
        this.image = image;
    }
     
  


    

}
