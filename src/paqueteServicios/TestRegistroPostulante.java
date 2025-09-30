package paqueteServicios;

import javax.swing.JOptionPane;

public class TestRegistroPostulante {
    public static void main(String[] args) {
        ServicioRegistrarPostulante objRegistrador = new ServicioRegistrarPostulante();
        
        // Prueba con datos validos
        String strNombre = "Juan";
        String strApellido = "Perez";
        String strCorreo = "juan.perez@correo.com";
        String strContrasenia = "MiContra123!";
        String strCI = "1234567";
        String strFechaNacimiento = "15/05/2000"; // Agregado para que coincida con el metodo
        
        JOptionPane.showMessageDialog(null, "Iniciando prueba con datos validos.", "Test", JOptionPane.INFORMATION_MESSAGE);
        
        // Se llama al metodo con los 6 parametros que acepta
        objRegistrador.registrarPostulante(strNombre, strApellido, strCorreo, strContrasenia, strCI, strFechaNacimiento);
        
        // Prueba con datos invalidos
        String strNombreInvalido = "12345";
        String strApellidoInvalido = "Gomez1";
        String strCorreoInvalido = "correo-invalido";
        String strCIInvalido = "01234";
        String strFechaNacimientoInvalida = "15/05/2020"; // Fecha de menor de edad
        
        JOptionPane.showMessageDialog(null, "Iniciando prueba con datos invalidos.", "Test", JOptionPane.INFORMATION_MESSAGE);
        
        // Se llama al metodo con los 6 parametros que acepta
        objRegistrador.registrarPostulante(strNombreInvalido, strApellidoInvalido, strCorreoInvalido, strContrasenia, strCIInvalido, strFechaNacimientoInvalida);
    }
}