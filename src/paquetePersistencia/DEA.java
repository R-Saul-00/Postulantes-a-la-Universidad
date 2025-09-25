/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquetePersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DEA {
    public boolean verificarLogin(String correoElectronico, String contrasenaHash) {
        String strConsulta = "SELECT COUNT(*) FROM usuario WHERE Correo_Electronico = ? AND Contrasena_Hash = ?";
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta)) {
            preparedStatement.setString(1, correoElectronico);
            preparedStatement.setString(2, contrasenaHash);
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
    
    public void insertarUsuario(String correoElectronico, String contrasenaHash, String nombres, String apellidos, String numeroCI, Date fechaNacimiento) {
        String strConsulta = "INSERT INTO usuario (Correo_Electronico, Contrasena_Hash, Nombres, Apellidos, Numero_CI, Fecha_Nacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta)) {
            preparedStatement.setString(1, correoElectronico);
            preparedStatement.setString(2, contrasenaHash);
            preparedStatement.setString(3, nombres);
            preparedStatement.setString(4, apellidos);
            preparedStatement.setString(5, numeroCI);
            preparedStatement.setDate(6, (java.sql.Date) fechaNacimiento);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar usuario: " + ex.getMessage());
        }
    }
    
    public void insertarPostulacion(int idUsuario, String numeroRegistroUnico, String fechaEnvio, String confirmacionEnviada, String celular) {
        String strConsulta = "INSERT INTO postulacion (ID_Usuario, Numero_Registro_Unico, Fecha_Envio, Confirmacion_Enviada, Celular) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = ConectorServidorBD.conectar();
             PreparedStatement preparedStatement = conexion.prepareStatement(strConsulta)) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.setString(2, numeroRegistroUnico);
            preparedStatement.setString(3, fechaEnvio);
            preparedStatement.setString(4, confirmacionEnviada);
            preparedStatement.setString(5, celular);
            preparedStatement.executeUpdate();
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
                String id = String.valueOf(resultSet.getInt("ID_Usuario"));
                String correo = resultSet.getString("Correo_Electronico");
                String hash = resultSet.getString("Contrasena_Hash");
                String nombres = resultSet.getString("Nombres");
                String apellidos = resultSet.getString("Apellidos");
                String ci = resultSet.getString("Numero_CI");
                String fecha = String.valueOf(resultSet.getDate("Fecha_Nacimiento"));
                String[] fila = {id, correo, hash, nombres, apellidos, ci, fecha};
                bdUsuarios.add(fila);
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
                String idPost = String.valueOf(resultSet.getInt("ID_Postulacion"));
                String idUser = String.valueOf(resultSet.getInt("ID_Usuario"));
                String registro = resultSet.getString("Numero_Registro_Unico");
                String fechaEnvio = resultSet.getString("Fecha_Envio");
                String confirmacion = resultSet.getString("Confirmacion_Enviada");
                String celular = resultSet.getString("Celular");
                String[] fila = {idPost, idUser, registro, fechaEnvio, confirmacion, celular};
                bdPostulaciones.add(fila);
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
                String idUser = String.valueOf(resultSet.getInt("ID_Usuario"));
                String correo = resultSet.getString("Correo_Electronico");
                String nombres = resultSet.getString("Nombres");
                String apellidos = resultSet.getString("Apellidos");
                String ci = resultSet.getString("Numero_CI");
                String fechaNac = String.valueOf(resultSet.getDate("Fecha_Nacimiento"));
                String idPost = String.valueOf(resultSet.getInt("ID_Postulacion"));
                String registro = resultSet.getString("Numero_Registro_Unico");
                String fechaEnvio = resultSet.getString("Fecha_Envio");
                String confirmacion = resultSet.getString("Confirmacion_Enviada");
                String celular = resultSet.getString("Celular");
                String[] fila = {idUser, correo, nombres, apellidos, ci, fechaNac, idPost, registro, fechaEnvio, confirmacion, celular};
                bdCompletos.add(fila);
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener datos completos: " + ex.getMessage());
        }
        return bdCompletos;
    }
}