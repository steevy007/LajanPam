/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lajanpam;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanon
*/
public class BD {
    private Statement st;
    private Connection con;
    private ResultSet result;
    public BD(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/cashconvert";
            con=DriverManager.getConnection(url,"root","steevy");
            st=con.createStatement();
             
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Pilote introuvable");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erreur Connexion\n"+e);
        }
    }
    
    public Statement getSt(){
        return st;
    }
    
    public Connection getCon(){
        return con;
    }
    
    public ResultSet getResult(){
        return result;
    }
    public void setSt(Statement st){
        this.st=st;
    }
    
    public void setCon(Connection con){
        this.con=con;
    }
    public void setResult(ResultSet result){
        this.result=result;
    }
}
