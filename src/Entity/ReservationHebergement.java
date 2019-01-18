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
public class ReservationHebergement {
    private int idReservationh;
    private int idAgence;
    private int idHebergement;
    private int idUtilisateur;
    private int nbrplace;
    private int nbrjour;
 private int prix;
    private String typechambre;
    private String tauxchambre;
    public ReservationHebergement(int idReservationh, int idAgence, int idHebergement, int idUtilisateur, int nbrplace, int nbrjour, int prix, String typechambre, String tauxchambre) {
        this.idReservationh = idReservationh;
        this.idAgence = idAgence;
        this.idHebergement = idHebergement;
        this.idUtilisateur = idUtilisateur;
        this.nbrplace = nbrplace;
        this.nbrjour = nbrjour;
        this.prix = prix;
        this.typechambre = typechambre;
        this.tauxchambre = tauxchambre;
    }
   

    public ReservationHebergement() {
    }

    public ReservationHebergement(int idReservationh, int idAgence, int idHebergement, int idUtilisateur, int nbrplace, int prix, String typechambre, String tauxchambre) {
        this.idReservationh = idReservationh;
        this.idAgence = idAgence;
        this.idHebergement = idHebergement;
        this.idUtilisateur = idUtilisateur;
        this.nbrplace = nbrplace;
        this.prix = prix;
        this.typechambre = typechambre;
        this.tauxchambre = tauxchambre;
    }

    public ReservationHebergement(int idReservationh, int idAgence, int idHebergement, int idUtilisateur) {
        this.idReservationh = idReservationh;
        this.idAgence = idAgence;
        this.idHebergement = idHebergement;
        this.idUtilisateur = idUtilisateur;
    }

    public ReservationHebergement( int idHebergement, int idUtilisateur,int idAgence) {
        this.idAgence = idAgence;
        this.idHebergement = idHebergement;
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdReservationh() {
        return idReservationh;
    }

    public void setIdReservationh(int idReservationh) {
        this.idReservationh = idReservationh;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public int getNbrjour() {
        return nbrjour;
    }

    public void setNbrjour(int nbrjour) {
        this.nbrjour = nbrjour;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public int getIdHebergement() {
        return idHebergement;
    }

    public void setIdHebergement(int idHebergement) {
        this.idHebergement = idHebergement;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public String getTypechambre() {
        return typechambre;
    }

    public void setTypechambre(String typechambre) {
        this.typechambre = typechambre;
    }

    public String getTauxchambre() {
        return tauxchambre;
    }

    public void setTauxchambre(String tauxchambre) {
        this.tauxchambre = tauxchambre;
    }

    
    
    
}
