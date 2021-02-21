/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Couty
 */
public class DaoSql {
    //Objet de Connexion
    private Connection conn=null;
    //Executer les requetes
    PreparedStatement ps=null;

    public PreparedStatement getPs() {
        return ps;
    }
    public void ouvrirConnexionBD(){
        try {
            //1-Chargement du Driver
              Class.forName("com.mysql.jdbc.Driver");
              //2-Ouvrir la Connexion
               conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_etudiant_liage3", "root", "");
        }catch (ClassNotFoundException ex) {
            System.out.println("Erreur de Chargement de Driver");
        } catch (SQLException ex) {
            System.out.println("Erreur d'Ouverture de la Base Donnée");
        }
    }
    public void preparerRequete(String sql) {
        try {
                if(sql.toLowerCase().startsWith("insert")){
                        ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                }else{
                     ps=conn.prepareStatement(sql); 
                }
        } catch (SQLException ex) {
                Logger.getLogger(DaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public int executeMisAJour() {
        int nbreLigne=0;
        try {
            nbreLigne=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbreLigne;
    }
    
    public ResultSet executeSelect(){
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void closeConnexion(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
