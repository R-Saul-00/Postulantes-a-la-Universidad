package paqueteServicios;

import javax.swing.JOptionPane;

public class ServicioRegistrarPostulante {
    private String strNombrePostulante;
    private String strApellidoPostulante;
    private String strCorreoElec;
    private String strContrasenia;
    private String strCI;
    private String strFechaNacimiento;

    public ServicioRegistrarPostulante() {
        this.strNombrePostulante = "";
        this.strApellidoPostulante = "";
        this.strCorreoElec = "";
        this.strContrasenia = "";
        this.strCI = "";
        this.strFechaNacimiento = "";
    }

    private void registrarNombre(String strNombre) {
        if (ServicioValidacionDatos.nombreApellidoValido(strNombre)) {
            this.strNombrePostulante = strNombre;
        } else {
            JOptionPane.showMessageDialog(null, "El nombre ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strNombrePostulante = "";
        }
    }
    
    private void registrarApellido(String strApellido) {
        if (ServicioValidacionDatos.nombreApellidoValido(strApellido)) {
            this.strApellidoPostulante = strApellido;
        } else {
            JOptionPane.showMessageDialog(null, "El apellido ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strApellidoPostulante = "";
        }
    }

    private void registrarCorreo(String strCorreo) {
        if (ServicioValidacionDatos.correoElecValido(strCorreo)) {
            this.strCorreoElec = strCorreo;
        } else {
            JOptionPane.showMessageDialog(null, "El correo electrónico ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strCorreoElec = "";
        }
    }

    private void registrarContrasenia(String strContrasenia) {
        if (ServicioValidacionDatos.contraseniaValida(strContrasenia)) {
            this.strContrasenia = strContrasenia;
        } else {
            JOptionPane.showMessageDialog(null, "La contraseña ingresada no es válida.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strContrasenia = "";
        }
    }

    private void registrarCI(String strCI) {
        if (ServicioValidacionDatos.ciValido(strCI)) {
            this.strCI = strCI;
        } else {
            JOptionPane.showMessageDialog(null, "El CI ingresado no es válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strCI = "";
        }
    }

    private void registrarFechaNacimiento(String strFecha) {
        if (ServicioValidacionDatos.validarEdad(strFecha)) {
            this.strFechaNacimiento = strFecha;
        } else {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no es válida o el postulante no es mayor de edad.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            this.strFechaNacimiento = "";
        }
    }
    
    private boolean verificarCamposCompletos() {
        return !strNombrePostulante.isEmpty() && !strApellidoPostulante.isEmpty() && !strCorreoElec.isEmpty() &&
               !strContrasenia.isEmpty() && !strCI.isEmpty() && !strFechaNacimiento.isEmpty();
    }
    
    private boolean guardarPostulante() {
        if (verificarCamposCompletos()) {
            System.out.println("Todos los datos obligatorios están completos. Procediendo a guardar en la base de datos...");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios antes de guardar.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public void registrarPostulante(String strNombre, String strApellido, String strCorreo, String strContrasenia,
                                   String strCI, String strFechaNacimiento) {
        
        registrarNombre(strNombre);
        registrarApellido(strApellido);
        registrarCorreo(strCorreo);
        registrarContrasenia(strContrasenia);
        registrarCI(strCI);
        registrarFechaNacimiento(strFechaNacimiento);
        
        guardarPostulante();
    }
}