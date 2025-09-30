package paqueteServicios;

import java.util.regex.Pattern;

public class ServicioValidacionDocumentacion {
    private static final Pattern PATRON_CERTIFICADO_PROMO = Pattern.compile("[0-9]{7,10}$");
    private static final Pattern PATRON_NOMBRE_DEPARTAMENTO = Pattern.compile("^[A-Za-z\\s]+$");
    private static final Pattern PATRON_NOMBRE_MUNICIPIO = Pattern.compile("^[A-Za-z0-9\\.\\s-]+$");
    private static final Pattern PATRON_FACULTAD = Pattern.compile("^[A-Za-z0-9]+$");
    private static final Pattern PATRON_CARRERA = Pattern.compile("^[A-Za-z0-9]+$");
    private static final Pattern PATRON_CELULAR = Pattern.compile("^[6-7][0-9]{7}$"); 

    public static boolean validarMunicipio(String strMunicipio) {
        if (strMunicipio == null || strMunicipio.trim().isEmpty()) {
            return false;
        }
        return PATRON_NOMBRE_MUNICIPIO.matcher(strMunicipio).matches();
    }
    
    public static boolean validarDepartamento(String strDepartamento) {
        if (strDepartamento == null || strDepartamento.trim().isEmpty()) {
            return false;
        }
        return PATRON_NOMBRE_DEPARTAMENTO.matcher(strDepartamento).matches();
    }
    
    public static boolean validarFacultad(String strFacultad) {
        if (strFacultad == null || strFacultad.trim().isEmpty()) {
            return false;
        }
        return PATRON_FACULTAD.matcher(strFacultad).matches();
    }
    
    public static boolean validarCarrera(String strCarrera) {
        if (strCarrera == null || strCarrera.trim().isEmpty()) {
            return false;
        }
        return PATRON_CARRERA.matcher(strCarrera).matches();
    }
    
    public static boolean validarCelular(String strCelular) {
        if (strCelular == null || strCelular.trim().isEmpty()) {
            return false;
        }
        return PATRON_CELULAR.matcher(strCelular).matches();
    }

    public static boolean validarCodigoCertificado(String strCodigo) {
        if (strCodigo == null || strCodigo.trim().isEmpty()) {
            return true; 
        }
        return PATRON_CERTIFICADO_PROMO.matcher(strCodigo).matches();
    }
}