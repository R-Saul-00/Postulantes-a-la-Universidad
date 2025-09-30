package paqueteServicios;

import java.util.Calendar;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;

/**
 * Clase para implementar metodos de validacion de datos del postulante
 * antes de registrar en la base de datos
 */
public class ServicioValidacionDatos {
    private static final Pattern PATRON_CORREO_ELEC = Pattern.compile("^[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PATRON_CONTRASENIA = Pattern.compile("[A-Za-z0-9!@#$%^&*()_+]{8,20}");
    private static final Pattern PATRON_NOMBRE_APELLIDO = Pattern.compile("^[A-Za-zñÑáéíóúÁÉÍÓÚ\\s]+$");
    private static final Pattern PATRON_CI = Pattern.compile("^[1-9][0-9]{5,10}$");
    private static final Pattern PATRON_FECHA = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
    
    /**
     * Verifica que el nombre o apellido solo tenga letras, espacios y no este vacio.
     * @param strNombreApellido nombre o apellido a validar.
     * @return boolean bolValido; true si el nombre o apellido es valido.
     */
    public static boolean nombreApellidoValido(String strNombreApellido) {
        if (strNombreApellido == null || strNombreApellido.isEmpty()) {
            return false;
        }
        return PATRON_NOMBRE_APELLIDO.matcher(strNombreApellido).matches();
    }

    /**
     * Verifica que el carnet no este vacio, que sean solo numeros y su longitud.
     * Este nombre de método es consistente con la clase ServicioRegistrarPostulante modificada.
     * @param strNumeroCI el carnet de identidad a validar.
     * @return boolean bolValido; True si el CI es valido.
     */
    public static boolean numeroCIVálido(String strNumeroCI) {
        if (strNumeroCI == null || strNumeroCI.isEmpty()) {
            return false;
        }
        return PATRON_CI.matcher(strNumeroCI).matches();
    }
    
    /**
     * Verifica que el correo no este vacio y su estructura.
     * @param strCorreoElectronico el correo electronico a validar.
     * @return boolean bolValido; true si el correo es valido.
     */
    public static boolean correoElecValido(String strCorreoElectronico) {
        if (strCorreoElectronico == null || strCorreoElectronico.isEmpty()) {
            return false;
        }
        return PATRON_CORREO_ELEC.matcher(strCorreoElectronico).matches();
    }
    
    /**
     * Verifica la longitud y caracteres permitidos en la contraseña.
     * @param strContrasenia la contrasenia a validar.
     * @return boolean bolValido; true si la contrasenia es valida.
     */
    public static boolean contraseniaValida(String strContrasenia) {
        if (strContrasenia == null || strContrasenia.isEmpty()) {
            return false;
        }
        return PATRON_CONTRASENIA.matcher(strContrasenia).matches();
    }

    /**
     * Verifica solo el formato de la fecha dd/MM/yyyy y que la fecha sea real.
     * **ESTE MÉTODO FALTABA Y SE HA AÑADIDO.**
     * @param strFecha la fecha a validar.
     * @return boolean bolValido; true si el formato es correcto y la fecha es real.
     */
    public static boolean fechaValida(String strFecha) {
        if (strFecha == null || strFecha.trim().isEmpty() || !PATRON_FECHA.matcher(strFecha).matches()) {
            return false;
        }
        
        try {
            DateTimeFormatter objFormatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(strFecha, objFormatoFecha);
            return true;
        } catch (DateTimeParseException objExcepcionFormato) {
            return false;
        }
    }
    
    /**
     * Verifica que la fecha de nacimiento sea válida y que el postulante sea mayor o igual a 18 años.
     * Se utiliza java.time (LocalDate/Period) para una validación más robusta.
     * @param strFechaNacimiento la fecha de nacimiento en formato dd/MM/yyyy.
     * @return boolean bolValido; true si la fecha es válida y el postulante es mayor de edad.
     */
    public static boolean validarEdad(String strFechaNacimiento) {
        final int intEdadMinima = 18;
        
        if (!fechaValida(strFechaNacimiento)) {
            return false;
        }

        try {
            DateTimeFormatter objFormatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate objFechaNacimiento = LocalDate.parse(strFechaNacimiento, objFormatoFecha);
            LocalDate objFechaActual = LocalDate.now();
            
            if (objFechaNacimiento.isAfter(objFechaActual)) {
                return false;
            }

            Period objPeriodo = Period.between(objFechaNacimiento, objFechaActual);
            int intEdadCalculada = objPeriodo.getYears();

            return intEdadCalculada >= intEdadMinima;
            
        } catch (DateTimeParseException objExcepcionFormato) {
            return false;
        }
    }
}