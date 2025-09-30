package paqueteServicios;

import javax.swing.JOptionPane;
import paquetePersistencia.DAO;

public class ServicioRegistrarPostulante {
    private String strNombrePostulante;
    private String strApellidoPostulante;
    private String strCorreoElec;
    private String strContrasenia;
    private String strCI;
    private String strFechaNacimiento;
    
    private final DAO objDAO;
    
    private Postulante objPostulanteCreado; 

    public ServicioRegistrarPostulante() {
        this.strNombrePostulante = "";
        this.strApellidoPostulante = "";
        this.strCorreoElec = "";
        this.strContrasenia = "";
        this.strCI = "";
        this.strFechaNacimiento = "";
        this.objDAO = new DAO();
        this.objPostulanteCreado = null;
    }
    
    private boolean registrarNombre(String strNombre) {
        if (ServicioValidacionDatos.nombreApellidoValido(strNombre)) {
            this.strNombrePostulante = strNombre;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El nombre ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strNombrePostulante = "";
            return false;
        }
    }
    
    private boolean registrarApellido(String strApellido) {
        if (ServicioValidacionDatos.nombreApellidoValido(strApellido)) {
            this.strApellidoPostulante = strApellido;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El apellido ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strApellidoPostulante = "";
            return false;
        }
    }

    private boolean registrarCorreo(String strCorreo) {
        if (!ServicioValidacionDatos.correoElecValido(strCorreo)) {
            JOptionPane.showMessageDialog(null, "El correo electrónico ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strCorreoElec = "";
            return false;
        }
        this.strCorreoElec = strCorreo;
        return true;
    }
    
    private boolean registrarFechaNacimiento(String strFecha) {
        if (ServicioValidacionDatos.fechaValida(strFecha)) {
            this.strFechaNacimiento = strFecha;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El formato de la fecha de nacimiento no es válido (debe ser dd/mm/aa).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strFechaNacimiento = "";
            return false;
        }
    }
    
    private boolean registrarCI(String strCI) {
        if (!ServicioValidacionDatos.numeroCIVálido(strCI)) {
            JOptionPane.showMessageDialog(null, "El número de carnet de identidad (CI) ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strCI = "";
            return false;
        }
        
        if (objDAO.verificarExistenciaCI(strCI)) {
            JOptionPane.showMessageDialog(null, "El número de carnet de identidad (CI) ya se encuentra registrado.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            this.strCI = "";
            return false;
        }
        
        this.strCI = strCI;
        return true;
    }

    private boolean registrarContrasenia(String strContrasenia) {
        if (ServicioValidacionDatos.contraseniaValida(strContrasenia)) {
            this.strContrasenia = strContrasenia;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strContrasenia = "";
            return false;
        }
    }

    public Postulante registrarPostulante(String strNombre, String strApellido, String strCorreo, String strContrasenia, String strCI, String strFechaNacimiento) {
        if (!registrarNombre(strNombre)) return null;
        if (!registrarApellido(strApellido)) return null;
        if (!registrarFechaNacimiento(strFechaNacimiento)) return null;
        if (!registrarCI(strCI)) return null;
        if (!registrarCorreo(strCorreo)) return null;

        if (objDAO.verificarExistenciaCorreo(this.strCorreoElec)) {
            JOptionPane.showMessageDialog(null, "El correo electrónico ya se encuentra registrado.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if (!registrarContrasenia(strContrasenia)) return null;
        
        Postulante objNuevoPostulante = objDAO.insertarPostulante(
            this.strCorreoElec, this.strContrasenia, this.strNombrePostulante, 
            this.strApellidoPostulante, this.strCI, this.strFechaNacimiento
        );

        if (objNuevoPostulante != null) {
            RegistroDocumentacion objRegistroInicial = new RegistroDocumentacion(
                0, 
                objNuevoPostulante.getIntIdUsuario(), 
                "",
                false, 
                null,
                false,
                null,
                null,
                null,
                null,
                null
            );
            RegistroDocumentacion objRegDocCreado = objDAO.insertarRegistroDocumentacion(objRegistroInicial);
            
            if (objRegDocCreado != null) {
                this.objPostulanteCreado = objNuevoPostulante;
                return this.objPostulanteCreado;
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear el registro de documentación inicial. Por favor, contacte a soporte.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        
        return null;
    }
}