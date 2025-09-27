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
        
        if (objDAO.verificarExistenciaCorreo(strCorreo)) {
            JOptionPane.showMessageDialog(null, "El correo electrónico ya se encuentra registrado en el sistema.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
            this.strCorreoElec = "";
            return false;
        }
        
        this.strCorreoElec = strCorreo;
        return true;
    }

    private boolean registrarContrasenia(String strContrasenia) {
        if (ServicioValidacionDatos.contraseniaValida(strContrasenia)) {
            this.strContrasenia = strContrasenia;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La contraseña ingresada no es válida.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strContrasenia = "";
            return false;
        }
    }

    private boolean registrarCI(String strCI) {
        if (ServicioValidacionDatos.ciValido(strCI)) {
            this.strCI = strCI;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El CI ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strCI = "";
            return false;
        }
    }

    private boolean registrarFechaNacimiento(String strFecha) {
        if (ServicioValidacionDatos.validarEdad(strFecha)) {
            this.strFechaNacimiento = strFecha;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida o el postulante no es mayor de edad.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strFechaNacimiento = "";
            return false;
        }
    }
    
    private boolean verificarCamposCompletos() {
        return !strNombrePostulante.isEmpty() && !strApellidoPostulante.isEmpty() && !strCorreoElec.isEmpty() &&
               !strContrasenia.isEmpty() && !strCI.isEmpty() && !strFechaNacimiento.isEmpty();
    }
    
    private Postulante guardarPostulante() {
        if (verificarCamposCompletos()) {
            System.out.println("Todos los datos obligatorios están completos. Procediendo a guardar en la base de datos...");
            
            this.objPostulanteCreado = this.objDAO.insertarPostulante(
                this.strCorreoElec,
                this.strContrasenia,
                this.strNombrePostulante,
                this.strApellidoPostulante,
                this.strCI,
                this.strFechaNacimiento
            );

            if (this.objPostulanteCreado != null) {
                JOptionPane.showMessageDialog(null, "Registro exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return this.objPostulanteCreado;
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el usuario en la base de datos.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                return null;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios antes de guardar.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public Postulante registrarPostulante(String strNombre, String strApellido, String strCorreo, String strContrasenia,
                                         String strCI, String strFechaNacimiento) {
        
        if (!registrarNombre(strNombre)) return null;
        if (!registrarApellido(strApellido)) return null;
        if (!registrarCorreo(strCorreo)) return null;
        if (!registrarContrasenia(strContrasenia)) return null;
        if (!registrarCI(strCI)) return null;
        if (!registrarFechaNacimiento(strFechaNacimiento)) return null;
        
        return guardarPostulante();
    }
}