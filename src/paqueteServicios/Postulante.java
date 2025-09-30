package paqueteServicios;

public class Postulante {
    
    private int intIdUsuario;
    private String strCorreoElectronico;
    private String strNombre;
    private String strApellido;
    private String strNumeroCI;
    private String strFechaNacimiento;
    
    private RegistroDocumentacion objRegistroDocumentacion; 
    
    public Postulante(int intIdUsuario, String strCorreoElectronico, String strNombre, String strApellido, String strNumeroCI, String strFechaNacimiento) {
        this.intIdUsuario = intIdUsuario;
        this.strCorreoElectronico = strCorreoElectronico;
        this.strNombre = strNombre;
        this.strApellido = strApellido;
        this.strNumeroCI = strNumeroCI;
        this.strFechaNacimiento = strFechaNacimiento;
        this.objRegistroDocumentacion = null;
    }

    public int getIntIdUsuario() {
        return intIdUsuario;
    }

    public String getStrCorreoElectronico() {
        return strCorreoElectronico;
    }
    
    public String getStrNombre() {
        return strNombre;
    }

    public String getStrApellido() {
        return strApellido;
    }

    public String getStrNumeroCI() {
        return strNumeroCI;
    }

    public String getStrFechaNacimiento() {
        return strFechaNacimiento;
    }
    
    public String getNombreCompleto() {
        return this.strNombre + " " + this.strApellido;
    }
    
    public RegistroDocumentacion getObjRegistroDocumentacion() {
        return objRegistroDocumentacion;
    }

    public void setObjRegistroDocumentacion(RegistroDocumentacion objRegistroDocumentacion) {
        this.objRegistroDocumentacion = objRegistroDocumentacion;
    }
}