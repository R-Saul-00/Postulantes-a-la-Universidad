package paqueteServicios;

import paquetePersistencia.DAO;

public class consultaPostulante {
    private DAO dao;

    public consultaPostulante() {
        this.dao = new DAO();
    }

    public Postulante obtenerPostulanteActualizado(Postulante postulante) {
        if (postulante == null) return null;
        return dao.obtenerPostulantePorId(postulante.getIntIdUsuario());
    }

    /**
     * Método que devuelve si el postulante tiene CI registrado
     */
    public boolean estadoCI(Postulante postulante) {
        return postulante.getStrNumeroCI() != null && !postulante.getStrNumeroCI().isEmpty();
    }

    /**
     * Método que devuelve si el postulante tiene el certificado de bachiller
     */
    public boolean estadoBachiller(Postulante postulante) {
        if (postulante.getObjRegistroDocumentacion() == null) return false;
        return postulante.getObjRegistroDocumentacion().getStrCodigoCertificadoPromo() != null &&
               !postulante.getObjRegistroDocumentacion().getStrCodigoCertificadoPromo().isEmpty();
    }

    /**
     * Método que devuelve si el postulante ya realizó el pago
     */
    public boolean estadoPago(Postulante postulante) {
        if (postulante.getObjRegistroDocumentacion() == null) return false;
        return postulante.getObjRegistroDocumentacion().getBolEstadoDePago();
    }

    /**
     * Método que devuelve si la inscripción está completa
     */
    public boolean inscripcionCompleta(Postulante postulante) {
        return estadoCI(postulante) && estadoBachiller(postulante) && estadoPago(postulante);
    }

    /**
     * Método que devuelve un resumen del estado de inscripción
     */
    public String obtenerEstadoInscripcion(Postulante postulante) {
        if (postulante == null) return "Postulante no encontrado";

        StringBuilder estado = new StringBuilder();
        estado.append(postulante.getNombreCompleto()).append("\n");
        estado.append(postulante.getStrCorreoElectronico()).append("\n");

        if (postulante.getObjRegistroDocumentacion() != null) {
            estado.append(postulante.getObjRegistroDocumentacion().getStrCelular() != null
                ? postulante.getObjRegistroDocumentacion().getStrCelular() : "No registrado").append("\n");
            estado.append(postulante.getObjRegistroDocumentacion().getStrCarrera() != null
                ? postulante.getObjRegistroDocumentacion().getStrCarrera() : "No seleccionada").append("\n");
            estado.append(postulante.getObjRegistroDocumentacion().getStrFacultad() != null
                ? postulante.getObjRegistroDocumentacion().getStrFacultad() : "No seleccionada").append("\n");
        } else {
            estado.append("No registrado\nNo seleccionada\nNo seleccionada\n");
        }

        estado.append("\nESTADO DE INSCRIPCION:\n");
        estado.append(estadoCI(postulante) ? "CI: Registrado\n" : "CI: Faltante\n");
        estado.append(estadoBachiller(postulante) ? "Documento de Bachiller: Registrado\n" : "Documento de Bachiller: Faltante\n");
        estado.append(estadoPago(postulante) ? "Pago: Completado\n" : "Pago: Pendiente\n");

        estado.append(inscripcionCompleta(postulante) ? "Inscripcion: Completa\n" : "Inscripcion: Incompleta\n");

        return estado.toString();
    }

    /**
     * Devuelve un arreglo de booleanos con los estados obligatorios [CI, Bachiller, Pago]
     */
    public boolean[] verificarCamposObligatorios(Postulante postulante) {
        return new boolean[] {
            estadoCI(postulante),
            estadoBachiller(postulante),
            estadoPago(postulante)
        };
    }
}
