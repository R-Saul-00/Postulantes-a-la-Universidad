package paqueteServicios;

import javax.swing.JOptionPane;
import paquetePersistencia.DAO;

public class ServicioRegistrarDocumentacion {
    
    private final DAO objDAO;

    public ServicioRegistrarDocumentacion() {
        this.objDAO = new DAO();
    }
    
    public RegistroDocumentacion registrarDocumentacion(Postulante objPostulante, String strCodigoCertificadoPromo, 
                                                      String strMunicipio, String strDepartamento, String strFacultad, 
                                                      String strCarrera, String strCelular, String strFechaRegistro) {
        
        // Validar existencia del usuario/postulante (ID_Usuario)
        if (objPostulante == null || objPostulante.getIntIdUsuario() <= 0) {
            JOptionPane.showMessageDialog(null, "Error: El objeto Postulante no es válido o no tiene un ID de usuario.", 
                                          "Error de Lógica", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if (!validarTodosLosCampos(strCodigoCertificadoPromo, strMunicipio, strDepartamento, strFacultad, strCarrera, strCelular)) {
             return null;
        }
        
        RegistroDocumentacion objRegistroExistente = objDAO.obtenerRegistroDocumentacionPorIdUsuario(objPostulante.getIntIdUsuario());
        if (objRegistroExistente != null) {
            JOptionPane.showMessageDialog(null, "El postulante ya tiene un registro de documentación. Se permite solo uno.", 
                                          "Advertencia", JOptionPane.WARNING_MESSAGE);
            return objRegistroExistente; 
        }
        
        RegistroDocumentacion objRegistroDocNuevo = new RegistroDocumentacion(
            0,
            objPostulante.getIntIdUsuario(),
            (strCodigoCertificadoPromo != null ? strCodigoCertificadoPromo : null),
            false,
            (strFechaRegistro != null ? strFechaRegistro : null),
            false,
            strMunicipio, 
            strDepartamento, 
            strFacultad, 
            strCarrera, 
            strCelular
        );
        
        RegistroDocumentacion objRegistroPersistido = objDAO.insertarRegistroDocumentacion(objRegistroDocNuevo);
        
        if (objRegistroPersistido != null) {
            objPostulante.setObjRegistroDocumentacion(objRegistroPersistido);
            JOptionPane.showMessageDialog(null, "Registro de documentación guardado exitosamente!", 
                                          "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return objRegistroPersistido;
        } else {
            JOptionPane.showMessageDialog(null, "Error fatal al guardar el registro de documentación en la base de datos.", 
                                          "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private boolean validarTodosLosCampos(String strCodigoCertificadoPromo, String strMunicipio, String strDepartamento, 
                                         String strFacultad, String strCarrera, String strCelular) {
        
        if (!ServicioValidacionDocumentacion.validarMunicipio(strMunicipio)) {
            JOptionPane.showMessageDialog(null, "El municipio ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!ServicioValidacionDocumentacion.validarDepartamento(strDepartamento)) {
            JOptionPane.showMessageDialog(null, "El departamento ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!ServicioValidacionDocumentacion.validarFacultad(strFacultad)) {
            JOptionPane.showMessageDialog(null, "La facultad ingresada no es válida.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!ServicioValidacionDocumentacion.validarCarrera(strCarrera)) {
            JOptionPane.showMessageDialog(null, "La carrera ingresada no es válida.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!ServicioValidacionDocumentacion.validarCelular(strCelular)) {
            JOptionPane.showMessageDialog(null, "El número de celular ingresado no es válido (Debe empezar con 6 o 7 y tener 8 dígitos).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!ServicioValidacionDocumentacion.validarCodigoCertificado(strCodigoCertificadoPromo)) {
            JOptionPane.showMessageDialog(null, "El código de certificado de promoción no es válido (Debe ser numérico de 7 a 10 dígitos).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}