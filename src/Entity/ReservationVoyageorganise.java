/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Y520-I7-1TR-4G
 */
public class ReservationVoyageorganise {
    private int id_reservation;
    private int id_user;
    private int id_agence;
    private int nbrplace;
    private int prix;
    private int idVoyageorganise;

    public ReservationVoyageorganise() {
    }

    @Override
    public String toString() {
        return "ReservationVoyageorganise{" + "id_reservation=" + id_reservation + ", id_user=" + id_user + ", id_agence=" + id_agence + ", nbrplace=" + nbrplace + ", prix=" + prix + ", idVoyageorganise=" + idVoyageorganise + '}';
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdVoyageorganise() {
        return idVoyageorganise;
    }

    public void setIdVoyageorganise(int idVoyageorganise) {
        this.idVoyageorganise = idVoyageorganise;
    }

    public ReservationVoyageorganise(int id_reservation, int id_user, int id_agence, int nbrplace, int prix, int idVoyageorganise) {
        this.id_reservation = id_reservation;
        this.id_user = id_user;
        this.id_agence = id_agence;
        this.nbrplace = nbrplace;
        this.prix = prix;
        this.idVoyageorganise = idVoyageorganise;
    }

    
}
