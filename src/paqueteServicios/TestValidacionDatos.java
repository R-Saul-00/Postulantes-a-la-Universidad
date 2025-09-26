package paqueteServicios;

public class TestValidacionDatos {
    public static void main(String[] args) {

        // Valores de prueba
        String strCorreoValido = "prueba@example.com";
        String strCorreoInvalido = "prueba@example";
        
        String strCiValido = "1234567";
        String strCiInvalido = "012345";
        
        String strContraseniaValida = "miClave123!";
        String strContraseniaInvalida = "corta";
        
        String strNombreValido = "Juan Perez";
        String strNombreInvalido = "Juan-Perez";

        // Valores de prueba para la edad
        String strFechaValidaMayor = "15/01/2000"; // Debería ser > 18
        String strFechaInvalidaMenor = "15/01/2020"; // Debería ser < 18
        String strFechaInvalidaFormato = "15-01-2000"; // Formato incorrecto
        
        // Resultados de la validacion
        System.out.println("--- PRUEBAS DE VALIDACION ---");
        
        // Correo Electronico
        System.out.println("Correo '" + strCorreoValido + "' es valido: " + ServicioValidacionDatos.correoElecValido(strCorreoValido)); // Debería ser true
        System.out.println("Correo '" + strCorreoInvalido + "' es valido: " + ServicioValidacionDatos.correoElecValido(strCorreoInvalido)); // Debería ser false
        System.out.println("-------------------------");
        
        // Carnet de Identidad
        System.out.println("CI '" + strCiValido + "' es valido: " + ServicioValidacionDatos.ciValido(strCiValido)); // Debería ser true
        System.out.println("CI '" + strCiInvalido + "' es valido: " + ServicioValidacionDatos.ciValido(strCiInvalido)); // Debería ser false
        System.out.println("-------------------------");
        
        // Contraseña
        System.out.println("Contraseña '" + strContraseniaValida + "' es valida: " + ServicioValidacionDatos.contraseniaValida(strContraseniaValida)); // Debería ser true
        System.out.println("Contraseña '" + strContraseniaInvalida + "' es valida: " + ServicioValidacionDatos.contraseniaValida(strContraseniaInvalida)); // Debería ser false
        System.out.println("-------------------------");
        
        // Nombre y Apellido
        System.out.println("Nombre '" + strNombreValido + "' es valido: " + ServicioValidacionDatos.nombreApellidoValido(strNombreValido)); // Debería ser true
        System.out.println("Nombre '" + strNombreInvalido + "' es valido: " + ServicioValidacionDatos.nombreApellidoValido(strNombreInvalido)); // Debería ser false
        System.out.println("-------------------------");

        // Edad
        System.out.println("Fecha '" + strFechaValidaMayor + "' es valida y mayor de edad: " + ServicioValidacionDatos.validarEdad(strFechaValidaMayor)); // Debería ser true
        System.out.println("Fecha '" + strFechaInvalidaMenor + "' es valida y mayor de edad: " + ServicioValidacionDatos.validarEdad(strFechaInvalidaMenor)); // Debería ser false
        System.out.println("Fecha '" + strFechaInvalidaFormato + "' es valida y mayor de edad: " + ServicioValidacionDatos.validarEdad(strFechaInvalidaFormato)); // Debería ser false
        System.out.println("-------------------------");
    }
}