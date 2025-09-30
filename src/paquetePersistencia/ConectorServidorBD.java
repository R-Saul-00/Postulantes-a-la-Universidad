package paquetePersistencia;

import java.sql.*;

/*
Clase para conectarse al servidor de la base de datos
*/
public class ConectorServidorBD {
    private final static String STRURL = "jdbc:mysql://25.16.211.8:3306/sistemas_info_2";
    private final static String STRUSUARIO = "SIS_INFO2";
    private final static String STRCONTRASENIA = "13498512A@";
    
    public static Connection conectar(){
        Connection conConector = null;
        
        try {
            conConector = DriverManager.getConnection(STRURL,STRUSUARIO,STRCONTRASENIA);
            System.out.println("Conexion exitosa");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return conConector;
    }
}