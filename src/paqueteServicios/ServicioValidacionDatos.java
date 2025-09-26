package paqueteServicios;

//import java.util.regex.Matcher;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Clase para implementar metodos de validacion de datos del postulante
 * antes de registrar en la base de datos
 */
public class ServicioValidacionDatos {
    //Estructura permitida para los datos
    private static final Pattern PATRON_CORREO_ELEC = Pattern.compile("^[A-Za-z0-9._+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    private static final Pattern PATRON_CONTRASENIA = Pattern.compile("[A-Za-z0-9!@#$%^&*()_+]{8,20}");
    private static final Pattern PATRON_NOMBRE_APELLIDO = Pattern.compile("^[A-Za-z\\s]+$");
    private static final Pattern PATRON_CI = Pattern.compile("^[1-9][0-9]{5,10}$");
    private static final Pattern PATRON_FECHA = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

    /**
     * Verifica que la fecha de nacimiento sea válida y que el postulante sea mayor o igual a 18 años.
     * @param strFechaNacimiento la fecha de nacimiento en formato dd/MM/yyyy
     * @return boolean bValido; true si la fecha es valida y el postulante es mayor de edad
     */
    public static boolean validarEdad(String strFechaNacimiento) {
        if (strFechaNacimiento == null || !PATRON_FECHA.matcher(strFechaNacimiento).matches()) {
            return false;
        }
        try {
            int diaNacimiento = Integer.parseInt(strFechaNacimiento.substring(0, 2));
            int mesNacimiento = Integer.parseInt(strFechaNacimiento.substring(3, 5));
            int anioNacimiento = Integer.parseInt(strFechaNacimiento.substring(6, 10));
            
            Calendar cal = Calendar.getInstance();
            int diaActual = cal.get(Calendar.DAY_OF_MONTH);
            int mesActual = cal.get(Calendar.MONTH) + 1;
            int anioActual = cal.get(Calendar.YEAR);
            
            if (mesNacimiento < 1 || mesNacimiento > 12 || diaNacimiento < 1 || diaNacimiento > 31) {
                return false;
            }
            int edad = anioActual - anioNacimiento;
            
            if (mesActual < mesNacimiento || (mesActual == mesNacimiento && diaActual < diaNacimiento)) {
                edad--;
            }
            return edad >= 18;
            
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return false;
        }
    }
    
    /**
     * Verifica que el correo no este vacio y su estructura
     * @param strCorreoElec el correo electronico a validar
     * @return boolean bValidacion; true si el correo es valido
     */
    public static boolean correoElecValido(String strCorreoElec) {
        if (strCorreoElec == null || strCorreoElec.isEmpty()) {
            return false;
        }
        return PATRON_CORREO_ELEC.matcher(strCorreoElec).matches();
    }
    
    /**
     * Verifica que el carnet no este vacio, que sean solo numeros y su longitud
     * @param strCI el carnet de identidad a validar
     * @return boolean bValido; True si el CI es valido
     */
    public static boolean ciValido(String strCI) {
        if (strCI == null || strCI.isEmpty()) {
            return false;
        }
        return PATRON_CI.matcher(strCI).matches();
    }
    
    /**
     * Verifica la longitud y caracteres permitidos en la contraseña
     * @param strContrasenia la contrasenia a validar
     * @return boolean bValido; true si la contrasenia es valida
     */
    public static boolean contraseniaValida(String strContrasenia) {
        if (strContrasenia == null || strContrasenia.isEmpty()) {
            return false;
        }
        return PATRON_CONTRASENIA.matcher(strContrasenia).matches();
    }
    
    /**
     * Verifica que el nombre o apellido solo tenga letras, espacios y no este vacio
     * @param strNombreApellido nombre o apellido a validar
     * @return boolean bValido; true si el nombre o apellido es valido
     */
    public static boolean nombreApellidoValido(String strNombreApellido) {
        if (strNombreApellido == null || strNombreApellido.isEmpty()) {
            return false;
        }
        return PATRON_NOMBRE_APELLIDO.matcher(strNombreApellido).matches();
    }
}