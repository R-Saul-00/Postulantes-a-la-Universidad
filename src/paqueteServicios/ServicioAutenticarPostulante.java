package paqueteServicios;

import paquetePersistencia.DAO;

public class ServicioAutenticarPostulante {
    
    private final DAO objDAO;

    public ServicioAutenticarPostulante() {
        this.objDAO = new DAO();
    }
    
    public Postulante autenticarPostulante(String strCorreo, String strContrasenia) {
        
        if (strCorreo == null || strCorreo.trim().isEmpty() || strContrasenia == null || strContrasenia.trim().isEmpty()) {
            System.err.println("Error: Correo o contraseña vacíos.");
            return null;
        }
        
        boolean blnAutenticacionExitosa = objDAO.autenticarUsuario(strCorreo, strContrasenia);
        
        if (blnAutenticacionExitosa) {
            Postulante objPostulante = objDAO.obtenerPostulantePorCorreo(strCorreo);
            
            if (objPostulante != null) {
                System.out.println("Autenticación exitosa para: " + strCorreo);
                return objPostulante;
            } else {
                System.err.println("Error: Postulante autenticado, pero datos no encontrados.");
                return null;
            }
        } else {
            System.err.println("Autenticación fallida: Credenciales incorrectas para " + strCorreo);
            return null;
        }
    }
    
    public Postulante autenticarYObtenerDatos(String strCorreo, String strContrasenia) {
        
        if (strCorreo == null || strCorreo.trim().isEmpty() || strContrasenia == null || strContrasenia.trim().isEmpty()) {
            System.err.println("Error: Correo o contraseña vacíos.");
            return null;
        }
        
        boolean blnAutenticacionExitosa = objDAO.autenticarUsuario(strCorreo, strContrasenia);
        
        if (!blnAutenticacionExitosa) {
            System.err.println("Autenticación fallida: Credenciales incorrectas para " + strCorreo);
            return null;
        }
        
        Postulante objPostulante = objDAO.obtenerPostulantePorCorreo(strCorreo);
        
        if (objPostulante == null) {
            System.err.println("Error: Postulante autenticado, pero datos de usuario no encontrados.");
            return null;
        }
        
        int intIdUsuario = objPostulante.getIntIdUsuario();
        RegistroDocumentacion objRegistroDocumentacion = objDAO.obtenerRegistroDocumentacionPorIdUsuario(intIdUsuario);
        
        if (objRegistroDocumentacion != null) {
            objPostulante.setObjRegistroDocumentacion(objRegistroDocumentacion);
            System.out.println("Documentación encontrada y asignada para el Postulante ID: " + intIdUsuario);
        } else {
            System.out.println("Advertencia: No se encontró documentación registrada para el Postulante ID: " + intIdUsuario);
        }
        
        System.out.println("Autenticación y carga de datos completada exitosamente para: " + strCorreo);
        return objPostulante;
    }
}