package paquetePersistencia;

import java.sql.Connection;

public class TestConexion {

    public static void main(String[] args){
        Connection conConexion = ConecBasePrueba.conectar();

        if(conConexion != null) {
            try{
                conConexion.close();
                System.out.println("Conexion cerrada correctamente");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}