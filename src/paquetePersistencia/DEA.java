package paquetePersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DEA {
    public boolean verificarLogin(String strCorreoElectronico, String strContrasenaHash) {
        String strConsulta = "SELECT COUNT(*) FROM usuario WHERE Correo_Electronico = ? AND Contrasena_Hash = ?";
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta)) {
            preparedStatement.setString(1, strCorreoElectronico);
            preparedStatement.setString(2, strContrasenaHash);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al verificar login: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean insertarUsuario(String strCorreoElectronico, String strContrasenaHash, String strNombres, String strApellidos, String strNumeroCI, String strFechaNacimiento) {
        String strConsulta = "INSERT INTO usuario (Correo_Electronico, Contrasena_Hash, Nombres, Apellidos, Numero_CI, Fecha_Nacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta)) {
            
            preparedStatement.setString(1, strCorreoElectronico);
            preparedStatement.setString(2, strContrasenaHash);
            preparedStatement.setString(3, strNombres);
            preparedStatement.setString(4, strApellidos);
            preparedStatement.setString(5, strNumeroCI);
            preparedStatement.setString(6, strFechaNacimiento);
            
            int intFilasAfectadas = preparedStatement.executeUpdate();
            
            return intFilasAfectadas > 0;
            
        } catch (SQLException ex) {
            System.err.println("Error al insertar usuario: " + ex.getMessage());
            return false;
        }
    }
    
    /**
    * Inserta una nueva postulación en la base de datos.
    * Usa esta versión si ya tienes los tipos de datos correctos.
    * @param intIdUsuario ID del usuario.
    * @param strNumeroRegistroUnico Número de registro único.
    * @param dteFechaEnvio Fecha de envío.
    * @param strCelular Número de celular.
    */
    public void insertarPostulacion(int intIdUsuario, String strNumeroRegistroUnico, java.util.Date dteFechaEnvio, String strCelular) {
        String strConsulta = "INSERT INTO postulacion (ID_Usuario, Numero_Registro_Unico, Fecha_Envio, Celular) VALUES (?, ?, ?, ?)";
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta)) {
            
            preparedStatement.setInt(1, intIdUsuario);
            preparedStatement.setString(2, strNumeroRegistroUnico);
            preparedStatement.setDate(3, new java.sql.Date(dteFechaEnvio.getTime()));
            preparedStatement.setString(4, strCelular);
            
            preparedStatement.executeUpdate();
            System.out.println("Postulación insertada correctamente.");
        } catch (SQLException ex) {
            System.err.println("Error al insertar postulación: " + ex.getMessage());
        }
    }
    
    public List<String[]> obtenerUsuarios() {
        String strConsulta = "SELECT ID_Usuario, Correo_Electronico, Contrasena_Hash, Nombres, Apellidos, Numero_CI, Fecha_Nacimiento FROM usuario";
        List<String[]> bdUsuarios = new ArrayList<>();
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String strId = String.valueOf(resultSet.getInt("ID_Usuario"));
                String strCorreo = resultSet.getString("Correo_Electronico");
                String strHash = resultSet.getString("Contrasena_Hash");
                String strNombres = resultSet.getString("Nombres");
                String strApellidos = resultSet.getString("Apellidos");
                String strCi = resultSet.getString("Numero_CI");
                String strFecha = String.valueOf(resultSet.getDate("Fecha_Nacimiento"));
                String[] arrFila = {strId, strCorreo, strHash, strNombres, strApellidos, strCi, strFecha};
                bdUsuarios.add(arrFila);
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener usuarios: " + ex.getMessage());
        }
        return bdUsuarios;
    }
    
    public List<String[]> obtenerPostulaciones() {
        String strConsulta = "SELECT ID_Postulacion, ID_Usuario, Numero_Registro_Unico, Fecha_Envio, Confirmacion_Enviada, Celular FROM postulacion";
        List<String[]> bdPostulaciones = new ArrayList<>();
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String strIdPost = String.valueOf(resultSet.getInt("ID_Postulacion"));
                String strIdUser = String.valueOf(resultSet.getInt("ID_Usuario"));
                String strRegistro = resultSet.getString("Numero_Registro_Unico");
                String strFechaEnvio = resultSet.getString("Fecha_Envio");
                String strConfirmacion = String.valueOf(resultSet.getBoolean("Confirmacion_Enviada"));
                String strCelular = resultSet.getString("Celular");
                String[] arrFila = {strIdPost, strIdUser, strRegistro, strFechaEnvio, strConfirmacion, strCelular};
                bdPostulaciones.add(arrFila);
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener postulaciones: " + ex.getMessage());
        }
        return bdPostulaciones;
    }
    
    public List<String[]> obtenerDatosCompletos() {
        String strConsulta = "SELECT u.ID_Usuario, u.Correo_Electronico, u.Nombres, u.Apellidos, u.Numero_CI, u.Fecha_Nacimiento, p.ID_Postulacion, p.Numero_Registro_Unico, p.Fecha_Envio, p.Confirmacion_Enviada, p.Celular FROM usuario u LEFT JOIN postulacion p ON u.ID_Usuario = p.ID_Usuario";
        List<String[]> bdCompletos = new ArrayList<>();
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String strIdUser = String.valueOf(resultSet.getInt("ID_Usuario"));
                String strCorreo = resultSet.getString("Correo_Electronico");
                String strNombres = resultSet.getString("Nombres");
                String strApellidos = resultSet.getString("Apellidos");
                String strCi = resultSet.getString("Numero_CI");
                String strFechaNac = String.valueOf(resultSet.getDate("Fecha_Nacimiento"));
                String strIdPost = String.valueOf(resultSet.getInt("ID_Postulacion"));
                String strRegistro = resultSet.getString("Numero_Registro_Unico");
                String strFechaEnvio = resultSet.getString("Fecha_Envio");
                String strConfirmacion = String.valueOf(resultSet.getBoolean("Confirmacion_Enviada"));
                String strCelular = resultSet.getString("Celular");
                String[] arrFila = {strIdUser, strCorreo, strNombres, strApellidos, strCi, strFechaNac, strIdPost, strRegistro, strFechaEnvio, strConfirmacion, strCelular};
                bdCompletos.add(arrFila);
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener datos completos: " + ex.getMessage());
        }
        return bdCompletos;
    }
}