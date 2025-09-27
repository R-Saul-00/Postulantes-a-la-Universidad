package paquetePersistencia;

import java.sql.*;

public class ConecBasePrueba {
    private final static String STRURL = "jdbc:mysql://127.0.0.1:3306/sistemas_info_2";
    private final static String STRUSUARIO = "PRUEBA";
    private final static String STRCONTRASENIA = "prueba1";
    
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
