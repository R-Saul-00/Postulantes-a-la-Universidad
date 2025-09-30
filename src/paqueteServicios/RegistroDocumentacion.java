package paqueteServicios;

public class RegistroDocumentacion {
    private int intIdRegistroDoc;
    private int intIdUsuario;
    private String strCodigoCertificadoPromo;
    private boolean bolEstadoDocumentacion;
    private String strFechaRegistroAdmin;
    private boolean bolEstadoDePago;
    private String strMunicipio;
    private String strDepartamento;
    private String strFacultad;
    private String strCarrera;
    private String strCelular;
    
    public RegistroDocumentacion(int intIdRegistroDoc, int intIdUsuario, String strCodigoCertificadoPromo, 
                                 boolean bolEstadoDocumentacion, String strFechaRegistroAdmin, 
                                 boolean bolEstadoDePago, String strMunicipio, String strDepartamento, 
                                 String strFacultad, String strCarrera, String strCelular) {
        
        this.intIdRegistroDoc = intIdRegistroDoc;
        this.intIdUsuario = intIdUsuario;
        this.strCodigoCertificadoPromo = strCodigoCertificadoPromo;
        this.bolEstadoDocumentacion = bolEstadoDocumentacion;
        this.strFechaRegistroAdmin = strFechaRegistroAdmin;
        this.bolEstadoDePago = bolEstadoDePago;
        this.strMunicipio = strMunicipio;
        this.strDepartamento = strDepartamento;
        this.strFacultad = strFacultad;
        this.strCarrera = strCarrera;
        this.strCelular = strCelular;
    }
    
    public int getIntIdRegistroDoc() {
        return intIdRegistroDoc;
    }

    public int getIntIdUsuario() {
        return intIdUsuario;
    }

    public String getStrCodigoCertificadoPromo() {
        return strCodigoCertificadoPromo;
    }

    public boolean getBolEstadoDocumentacion() {
        return bolEstadoDocumentacion;
    }

    public String getStrFechaRegistroAdmin() {
        return strFechaRegistroAdmin;
    }

    public boolean getBolEstadoDePago() {
        return bolEstadoDePago;
    }

    public String getStrMunicipio() {
        return strMunicipio;
    }

    public String getStrDepartamento() {
        return strDepartamento;
    }

    public String getStrFacultad() {
        return strFacultad;
    }

    public String getStrCarrera() {
        return strCarrera;
    }

    public String getStrCelular() {
        return strCelular;
    }
    
    public boolean tieneCertificadoBachiller(){
        if (strCodigoCertificadoPromo == null || strCodigoCertificadoPromo.isEmpty()){
            return false;
        }
        return true;
    }
    
    public int asientoDesignado(){
        if (tieneCertificadoBachiller() == bolEstadoDePago){
            return this.intIdRegistroDoc;
        }
        return 0;
    }
}