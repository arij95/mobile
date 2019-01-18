/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class User2 {
    
     
    public static User2 connected;

    public static User2 getConnected() {
        return connected;
    }

    public static void setConnected(User2 connected) {
        User2.connected = connected;
    }
        private int id_utilisateur;

   

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    private int nd;
    private String nom;
        private String prenom;
    private Date last_login;
    private String mdp;
        private String username;
    private String email;
        private String role;

    public User2() {
    }

    public User2(String mdp, String username, String role) {
        this.mdp = mdp;
        this.username = username;
        this.role = role;
    }

    public User2(int id_utilisateur, String nom, String prenom, Date last_login, String mdp, String username, String email, String role) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.last_login = last_login;
        this.mdp = mdp;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public User2(int id_utilisateur, String mdp, String username) {
        this.id_utilisateur = id_utilisateur;
        this.mdp = mdp;
        this.username = username;
    }

    public User2(int id_utilisateur, String mdp, String username, String role) {
        this.id_utilisateur = id_utilisateur;
        this.mdp = mdp;
        this.username = username;
        this.role = role;
    }
    

    public User2( String nom, String prenom, Date last_login, String mdp, String username, String email, String role) {
      
        this.nom = nom;
        this.prenom = prenom;
        this.last_login = last_login;
        this.mdp = mdp;
        this.username = username;
        this.email = email;
        this.role = role;
    }
public User2(String nom,String email)
    {
        
        this.nom=nom;
        this.email=email;
       
        
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }
  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

   

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getNd() {
        return nd;
    }

    public void setNd(int nd) {
        this.nd = nd;
    }

    public User2(int id_utilisateur, int nd, String nom, String prenom, Date last_login, String mdp, String username, String email, String role) {
        this.id_utilisateur = id_utilisateur;
        this.nd = nd;
        this.nom = nom;
        this.prenom = prenom;
        this.last_login = last_login;
        this.mdp = mdp;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public User2(String nom, String prenom, Date last_login, String mdp, String username, String email) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.last_login = last_login;
        this.mdp = mdp;
        this.username = username;
        this.email = email;
    }

    /**
     * @return the abonnes
     */
    




    
}
