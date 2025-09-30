package paqueteServicios;

public class TestValidacionDatos {
    public static void main(String[] args) {

        // Variables de prueba
        String strCorreoValido = "prueba@example.com";
        String strCorreoInvalido = "prueba@example";
        
        String strCiValido = "1234567";
        String strCiInvalido = "012345";
        
        String strContraseniaValida = "miClave123!";
        String strContraseniaInvalida = "corta";
        
        String strNombreValido = "Juan Pérez Ñuñez";
        String strNombreInvalido = "Juan-Perez";

        // Variables de prueba para la fecha y edad
        String strFechaValidaMayor = "15/01/2000"; 
        String strFechaValidaFormato = "20/12/2024"; 
        String strFechaInvalidaMenor = "15/01/2020"; 
        String strFechaInvalidaFormato = "15-01-2000"; 
        String strFechaInvalidaNoReal = "30/02/2023";

        // Resultados de la validacion
        System.out.println("--- INICIO DE PRUEBAS DE VALIDACION ---");
        System.out.println("");
        
        // Correo Electronico
        System.out.println("--- Correo Electrónico ---");
        System.out.println("Correo '" + strCorreoValido + "' es valido: " + ServicioValidacionDatos.correoElecValido(strCorreoValido)); 
        System.out.println("Correo '" + strCorreoInvalido + "' es valido: " + ServicioValidacionDatos.correoElecValido(strCorreoInvalido)); 
        System.out.println("");
        
        // Carnet de Identidad
        System.out.println("--- Carnet de Identidad (CI) ---");
        System.out.println("CI '" + strCiValido + "' es valido: " + ServicioValidacionDatos.numeroCIVálido(strCiValido)); // Método corregido
        System.out.println("CI '" + strCiInvalido + "' es valido: " + ServicioValidacionDatos.numeroCIVálido(strCiInvalido)); // Método corregido
        System.out.println("");
        
        // Contraseña
        System.out.println("--- Contraseña ---");
        System.out.println("Contraseña '" + strContraseniaValida + "' es valida: " + ServicioValidacionDatos.contraseniaValida(strContraseniaValida)); 
        System.out.println("Contraseña '" + strContraseniaInvalida + "' es valida: " + ServicioValidacionDatos.contraseniaValida(strContraseniaInvalida)); 
        System.out.println("");
        
        // Nombre y Apellido
        System.out.println("--- Nombre y Apellido ---");
        System.out.println("Nombre '" + strNombreValido + "' es valido: " + ServicioValidacionDatos.nombreApellidoValido(strNombreValido)); 
        System.out.println("Nombre '" + strNombreInvalido + "' es valido: " + ServicioValidacionDatos.nombreApellidoValido(strNombreInvalido)); 
        System.out.println("");

        // Solo Formato de Fecha
        System.out.println("--- Solo Formato de Fecha (dd/MM/yyyy) ---");
        System.out.println("Fecha '" + strFechaValidaFormato + "' es valida (formato y real): " + ServicioValidacionDatos.fechaValida(strFechaValidaFormato)); 
        System.out.println("Fecha '" + strFechaInvalidaFormato + "' es valida (formato): " + ServicioValidacionDatos.fechaValida(strFechaInvalidaFormato)); 
        System.out.println("Fecha '" + strFechaInvalidaNoReal + "' es valida (no real): " + ServicioValidacionDatos.fechaValida(strFechaInvalidaNoReal));
        System.out.println("");
        
        // Edad (Validación completa)
        System.out.println("--- Validación de Edad (Mayor de 18) ---");
        System.out.println("Fecha '" + strFechaValidaMayor + "' es valida y mayor de edad: " + ServicioValidacionDatos.validarEdad(strFechaValidaMayor)); 
        System.out.println("Fecha '" + strFechaInvalidaMenor + "' es valida y mayor de edad: " + ServicioValidacionDatos.validarEdad(strFechaInvalidaMenor)); 
        System.out.println("Fecha '" + strFechaInvalidaFormato + "' es valida y mayor de edad: " + ServicioValidacionDatos.validarEdad(strFechaInvalidaFormato)); 
        System.out.println("");
        
        System.out.println("--- FIN DE PRUEBAS DE VALIDACION ---");
    }
}