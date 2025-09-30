package paquetePersistencia;

import java.sql.*;

public class ConecBasePrueba {
    private final static String STRURL = "jdbc:mysql://127.0.0.1:3306/postulante";
    private final static String STRUSUARIO = "root";
    private final static String STRCONTRASENIA = "1234";
    
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
