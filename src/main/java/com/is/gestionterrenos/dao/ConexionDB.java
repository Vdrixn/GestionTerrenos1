package com.is.gestionterrenos.dao;
import java.sql.*;

//TODO: Terminar conexionDB
public class ConexionDB {
    public static void main(String[] args) {
        try {
            //CAMBIAR "password" por vuestra contraseña en mySql
            Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionterrenos", "root", "password");
			
			PreparedStatement miStatement=miConexion.prepareStatement("INSERT INTO parcelas VALUES (1,null,'treinta', 'limite')");
			miStatement.executeUpdate();
			/*int n=0;
			while(rs.next()){
				n++;
			}*/
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}