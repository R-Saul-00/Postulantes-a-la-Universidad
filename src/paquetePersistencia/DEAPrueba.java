package paquetePersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import paqueteServicios.Postulante; // La clase Postulante está en paqueteServicios

public class DEAPrueba {
    
    /**
     * Autentica un usuario verificando la existencia de su correo y la contraseña en texto plano.
     * ⚠️ NOTA: Asume que el campo 'Contrasena_Hash' en la BD contiene la contraseña sin hashear.
     * * @param strCorreoElectronico El correo del usuario.
     * @param strContrasenia La contraseña en texto plano.
     * @return boolean True si las credenciales coinciden, False en caso contrario.
     */
    public boolean autenticarUsuario(String strCorreoElectronico, String strContrasenia) {
        // La consulta compara el correo y el valor guardado en el campo de la contraseña.
        String strConsulta = "SELECT COUNT(*) FROM usuario WHERE Correo_Electronico = ? AND Contrasena_Hash = ?"; 
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
            
            objPreparedStatement.setString(1, strCorreoElectronico);
            objPreparedStatement.setString(2, strContrasenia); // Ahora pasa el texto plano
            
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
                    return objResultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al autenticar usuario para inicio de sesión: " + objExcepcionSQL.getMessage());
        }
        return false;
    }

    //-------------------------------------------------------------------------
    
    /**
     * Recupera todos los datos de un usuario a partir de su correo electrónico.
     * @param strCorreoElectronico El correo del usuario.
     * @return Postulante Objeto Postulante completo, o null si no se encuentra.
     */
    public Postulante obtenerPostulantePorCorreo(String strCorreoElectronico) {
        String strConsulta = "SELECT ID_Usuario, Correo_Electronico, Nombre, Apellidos, Numero_CI, Fecha_Nacimiento FROM usuario WHERE Correo_Electronico = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
            
            objPreparedStatement.setString(1, strCorreoElectronico);
            
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
                    // Mapeo de datos a la clase Postulante
                    int intId = objResultSet.getInt("ID_Usuario");
                    String strCorreo = objResultSet.getString("Correo_Electronico");
                    String strNombre = objResultSet.getString("Nombre");
                    String strApellido = objResultSet.getString("Apellidos");
                    String strCI = objResultSet.getString("Numero_CI");
                    String strFecha = objResultSet.getString("Fecha_Nacimiento");
                    
                    Postulante objPostulante = new Postulante(intId, strCorreo, strNombre, strApellido, strCI, strFecha);
                    return objPostulante;
                }
            }
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al obtener datos del postulante: " + objExcepcionSQL.getMessage());
        }
        return null; 
    }

    /**
     * Verifica si un postulante ya existe en la base de datos por su correo electrónico.
     * @param strCorreoElectronico El correo a verificar.
     * @return boolean True si el correo ya existe, False en caso contrario.
     */
    public boolean verificarExistenciaCorreo(String strCorreoElectronico) {
        String strConsulta = "SELECT COUNT(*) FROM usuario WHERE Correo_Electronico = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
            
            objPreparedStatement.setString(1, strCorreoElectronico);
            
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
                    return objResultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al verificar existencia de correo: " + objExcepcionSQL.getMessage());
        }
        return false;
    }

    /**
     * Inserta un nuevo postulante y devuelve el objeto Postulante creado con el ID autogenerado.
     * ⚠️ NOTA: Recibe la contraseña en texto plano para guardarla directamente en la BD.
     * * @param strCorreoElectronico Correo del nuevo postulante.
     * @param strContrasenia Contraseña en texto plano.
     * @return Postulante Devuelve el objeto Postulante creado con el ID autogenerado, o null si falla.
     */
    public Postulante insertarPostulante(String strCorreoElectronico, String strContrasenia, String strNombre, String strApellido, String strNumeroCI, String strFechaNacimiento) {
        
        String strConsulta = "INSERT INTO usuario (Correo_Electronico, Contrasena_Hash, Nombre, Apellidos, Numero_CI, Fecha_Nacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        
        // Solicitamos la devolución de las claves generadas (ID_Usuario)
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta, Statement.RETURN_GENERATED_KEYS)) {
            
            objPreparedStatement.setString(1, strCorreoElectronico);
            objPreparedStatement.setString(2, strContrasenia); // Se inserta el texto plano
            objPreparedStatement.setString(3, strNombre);
            objPreparedStatement.setString(4, strApellido);
            objPreparedStatement.setString(5, strNumeroCI);
            objPreparedStatement.setString(6, strFechaNacimiento);
            
            int intFilasAfectadas = objPreparedStatement.executeUpdate();
            
            if (intFilasAfectadas > 0) {
                // Si la inserción fue exitosa, recuperamos el ID generado
                try (ResultSet objClavesGeneradas = objPreparedStatement.getGeneratedKeys()) {
                    if (objClavesGeneradas.next()) {
                        int intIdGenerado = objClavesGeneradas.getInt(1); 
                        
                        // Creamos el objeto Postulante completo con el ID generado
                        Postulante objPostulante = new Postulante(
                            intIdGenerado, 
                            strCorreoElectronico, 
                            strNombre, 
                            strApellido, 
                            strNumeroCI, 
                            strFechaNacimiento
                        );
                        return objPostulante;
                    }
                }
            }
            
            return null;
            
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al insertar postulante: " + objExcepcionSQL.getMessage());
            return null;
        }
    }
}