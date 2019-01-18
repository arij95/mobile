/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Lenovo
 */
public class Restaurant {
    
    int id;
    String nom;
    int place;
    String specialite;
    String type;
    String adresse;
    int idagence;

    public Restaurant(int id, String nom, int place, String specialite, String type, String adresse, int agence) {
        this.id = id;
        this.nom = nom;
        this.place = place;
        this.specialite = specialite;
        this.type = type;
        this.adresse = adresse;
        this.idagence = agence;
    }

    public Restaurant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdagence() {
        return idagence;
    }

    public void setIdAgence(int agence) {
        this.idagence = agence;
    }
    
    

    
    
    
}
