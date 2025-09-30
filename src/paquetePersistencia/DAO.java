package paquetePersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;

public class DAO {
    private void setStringOrNull(PreparedStatement ps, int index, String value) throws SQLException {
        if (value == null || value.isEmpty()) {
            ps.setNull(index, java.sql.Types.VARCHAR);
        } else {
            ps.setString(index, value);
        }
    }

    public boolean autenticarUsuario(String strCorreoElectronico, String strContrasenia) {
        String strConsulta = "SELECT COUNT(*) FROM usuario WHERE Correo_Electronico = ? AND Contrasena_Hash = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
                 
            objPreparedStatement.setString(1, strCorreoElectronico);
            objPreparedStatement.setString(2, strContrasenia);
                 
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
    
    public Postulante obtenerPostulantePorCorreo(String strCorreoElectronico) {
        String strConsulta = "SELECT ID_Usuario, Correo_Electronico, Nombre, Apellidos, Numero_CI, Fecha_Nacimiento FROM usuario WHERE Correo_Electronico = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
                 
            objPreparedStatement.setString(1, strCorreoElectronico);
                 
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
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
            System.err.println("Error al obtener datos del postulante por correo: " + objExcepcionSQL.getMessage());
        }
        return null; 
    }
    
    public Postulante obtenerPostulantePorCI(String strNumeroCI) {
        String strConsulta = "SELECT ID_Usuario, Correo_Electronico, Nombre, Apellidos, Numero_CI, Fecha_Nacimiento FROM usuario WHERE Numero_CI = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
                 
            objPreparedStatement.setString(1, strNumeroCI);
                 
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
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
            System.err.println("Error al obtener datos del postulante por CI: " + objExcepcionSQL.getMessage());
        }
        return null; 
    }
    
    public boolean verificarExistenciaCI(String strNumeroCI) {
        String strConsulta = "SELECT COUNT(*) FROM usuario WHERE Numero_CI = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
                 
            objPreparedStatement.setString(1, strNumeroCI);
                 
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
                    return objResultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al verificar existencia de CI: " + objExcepcionSQL.getMessage());
        }
        return false;
    }
    
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
    
    public boolean existeCodigoCertificadoPromo(String strCodigo) {
        String strConsulta = "SELECT COUNT(*) FROM registro_documentacion WHERE codigo_certificado_promo = ? AND codigo_certificado_promo IS NOT NULL";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
                 
            setStringOrNull(objPreparedStatement, 1, strCodigo); 
                 
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
                    return objResultSet.getInt(1) > 0; 
                }
            }
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al verificar existencia de código de certificado promocional: " + objExcepcionSQL.getMessage());
        }
        return false;
    }

    public Postulante insertarPostulante(String strCorreoElectronico, String strContrasenia, String strNombre, String strApellido, String strNumeroCI, String strFechaNacimiento) {
        
        String strConsulta = "INSERT INTO usuario (Correo_Electronico, Contrasena_Hash, Nombre, Apellidos, Numero_CI, Fecha_Nacimiento) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta, Statement.RETURN_GENERATED_KEYS)) {
                 
            objPreparedStatement.setString(1, strCorreoElectronico);
            objPreparedStatement.setString(2, strContrasenia);
            objPreparedStatement.setString(3, strNombre);
            objPreparedStatement.setString(4, strApellido);
            objPreparedStatement.setString(5, strNumeroCI);
            objPreparedStatement.setString(6, strFechaNacimiento);
                 
            int intFilasAfectadas = objPreparedStatement.executeUpdate();
                 
            if (intFilasAfectadas > 0) {
                try (ResultSet objClavesGeneradas = objPreparedStatement.getGeneratedKeys()) {
                    if (objClavesGeneradas.next()) {
                        int intIdGenerado = objClavesGeneradas.getInt(1); 
                            
                        Postulante objPostulante = new Postulante(
                            intIdGenerado, strCorreoElectronico, strNombre, strApellido, 
                            strNumeroCI, strFechaNacimiento
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
    
    public RegistroDocumentacion insertarRegistroDocumentacion(RegistroDocumentacion objRegistroDoc) {
        String strConsulta = "INSERT INTO registro_documentacion (ID_Usuario, codigo_certificado_promo, Estado_Documentacion, Fecha_Registro_Admin, Estado_De_Pago, Municipio, Departamento, Facultad, Carrera, Celular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta, Statement.RETURN_GENERATED_KEYS)) {
            
            objPreparedStatement.setInt(1, objRegistroDoc.getIntIdUsuario());
            setStringOrNull(objPreparedStatement, 2, objRegistroDoc.getStrCodigoCertificadoPromo());
            objPreparedStatement.setBoolean(3, objRegistroDoc.getBolEstadoDocumentacion());
            setStringOrNull(objPreparedStatement, 4, objRegistroDoc.getStrFechaRegistroAdmin());
            objPreparedStatement.setBoolean(5, objRegistroDoc.getBolEstadoDePago());
            setStringOrNull(objPreparedStatement, 6, objRegistroDoc.getStrMunicipio());
            setStringOrNull(objPreparedStatement, 7, objRegistroDoc.getStrDepartamento());
            setStringOrNull(objPreparedStatement, 8, objRegistroDoc.getStrFacultad());
            setStringOrNull(objPreparedStatement, 9, objRegistroDoc.getStrCarrera());
            setStringOrNull(objPreparedStatement, 10, objRegistroDoc.getStrCelular());
            
            int intFilasAfectadas = objPreparedStatement.executeUpdate();
            
            if (intFilasAfectadas > 0) {
                try (ResultSet objClavesGeneradas = objPreparedStatement.getGeneratedKeys()) {
                    if (objClavesGeneradas.next()) {
                        int intIdGenerado = objClavesGeneradas.getInt(1);
                        
                        RegistroDocumentacion objNuevoRegistro = new RegistroDocumentacion(
                            intIdGenerado,
                            objRegistroDoc.getIntIdUsuario(),
                            objRegistroDoc.getStrCodigoCertificadoPromo(),
                            objRegistroDoc.getBolEstadoDocumentacion(),
                            objRegistroDoc.getStrFechaRegistroAdmin(), 
                            objRegistroDoc.getBolEstadoDePago(),
                            objRegistroDoc.getStrMunicipio(),
                            objRegistroDoc.getStrDepartamento(),
                            objRegistroDoc.getStrFacultad(),
                            objRegistroDoc.getStrCarrera(),
                            objRegistroDoc.getStrCelular()
                        );
                        return objNuevoRegistro;
                    }
                }
            }
            return null;
            
        } catch (SQLException objExcepcionSQL) {
            // Esto es importante para ver qué error está lanzando la BD
            System.err.println("Error CRÍTICO al insertar registro de documentación: " + objExcepcionSQL.getMessage()); 
            return null;
        }
    }
    
    public boolean actualizarEstadoDePago(int intIdUsuario) {
        String strConsulta = "UPDATE registro_documentacion SET Estado_De_Pago = TRUE WHERE ID_Usuario = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
            
            objPreparedStatement.setInt(1, intIdUsuario);
            
            return objPreparedStatement.executeUpdate() > 0;
            
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al actualizar el estado de pago: " + objExcepcionSQL.getMessage());
            return false;
        }
    }
    
    public boolean actualizarEstadoDocumentacion(int intIdUsuario) {
        String strConsulta = "UPDATE registro_documentacion SET Estado_Documentacion = TRUE, Fecha_Registro_Admin = CURDATE() WHERE ID_Usuario = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
            
            objPreparedStatement.setInt(1, intIdUsuario);
            
            return objPreparedStatement.executeUpdate() > 0;
            
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al actualizar el estado de la documentación: " + objExcepcionSQL.getMessage());
            return false;
        }
    }
    
    public RegistroDocumentacion obtenerRegistroDocumentacionPorIdUsuario(int intIdUsuario) {
        
        String strConsulta = "SELECT ID_Registro_Doc, ID_Usuario, codigo_certificado_promo, Estado_Documentacion, Fecha_Registro_Admin, Estado_De_Pago, Municipio, Departamento, Facultad, Carrera, Celular FROM registro_documentacion WHERE ID_Usuario = ?";
        
        try (Connection objConexion = ConecBasePrueba.conectar();
             PreparedStatement objPreparedStatement = objConexion.prepareStatement(strConsulta)) {
            
            objPreparedStatement.setInt(1, intIdUsuario);
            
            try (ResultSet objResultSet = objPreparedStatement.executeQuery()) {
                if (objResultSet.next()) {
                    int intIdRegistroDoc = objResultSet.getInt("ID_Registro_Doc");
                    int intIdUsuarioResultado = objResultSet.getInt("ID_Usuario"); 
                    String strCodigoCertificadoPromo = objResultSet.getString("codigo_certificado_promo");
                    boolean bolEstadoDocumentacion = objResultSet.getBoolean("Estado_Documentacion");
                    String strFechaRegistroAdmin = objResultSet.getString("Fecha_Registro_Admin");
                    boolean bolEstadoDePago = objResultSet.getBoolean("Estado_De_Pago");
                    String strMunicipio = objResultSet.getString("Municipio");
                    String strDepartamento = objResultSet.getString("Departamento");
                    String strFacultad = objResultSet.getString("Facultad");
                    String strCarrera = objResultSet.getString("Carrera");
                    String strCelular = objResultSet.getString("Celular");

                    RegistroDocumentacion objRegistroDocumentacion = new RegistroDocumentacion(
                        intIdRegistroDoc,
                        intIdUsuarioResultado,
                        strCodigoCertificadoPromo,
                        bolEstadoDocumentacion,
                        strFechaRegistroAdmin,
                        bolEstadoDePago,
                        strMunicipio,
                        strDepartamento,
                        strFacultad,
                        strCarrera,
                        strCelular
                    );
                    return objRegistroDocumentacion;
                }
            }
        } catch (SQLException objExcepcionSQL) {
            System.err.println("Error al obtener el registro de documentación por ID de usuario: " + objExcepcionSQL.getMessage());
        }
        return null;
    }
}