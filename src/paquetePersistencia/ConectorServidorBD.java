package paquetePersistencia;

import java.sql.*;

/*
Clase para conectarse al servidor de la base de datos
*/
public class ConectorServidorBD {
    private final static String STRURL = "jdbc:mysql://unpsuk9vxnl6nvv8:"
            + "w1KSsbckkqpMJsrZlD1i@bumfzhthb6geduye0otq-mysql.services.clever-cloud.com:"
            + "3306/bumfzhthb6geduye0otq";
    private final static String STRUSUARIO = "unpsuk9vxnl6nvv8";
    private final static String STRCONTRASENIA = "w1KSsbckkqpMJsrZlD1i";
    
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