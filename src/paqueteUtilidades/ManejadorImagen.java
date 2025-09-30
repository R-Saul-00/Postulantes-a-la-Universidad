package paqueteUtilidades;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ManejadorImagen {

    private static final Logger objLogger = Logger.getLogger(ManejadorImagen.class.getName());
    
    public static void cargarImagenConManejoError(JLabel lblEtiqueta, String strRutaRecurso, int intAncho, int intAlto) {
        try {
            // Usa UtilidadImagen.class para obtener la URL del recurso (dentro del JAR/Classpath)
            URL urlRecurso = ManejadorImagen.class.getResource(strRutaRecurso);

            if (urlRecurso != null) {
                // Éxito: Carga, escala y asigna el ícono.
                ImageIcon objIconoOriginal = new ImageIcon(urlRecurso);
                
                Image objImagenEscalada = objIconoOriginal.getImage().getScaledInstance(intAncho, intAlto, Image.SCALE_SMOOTH);
                lblEtiqueta.setIcon(new ImageIcon(objImagenEscalada));
                
                // Limpiar estilos de error
                lblEtiqueta.setText(null);
                lblEtiqueta.setOpaque(false);

            } else {
                // Advertencia: Recurso no encontrado (se rellena con gris claro).
                String strMensajeError = "Recurso no encontrado: " + strRutaRecurso;
                objLogger.log(Level.WARNING, strMensajeError);
                
                configurarLabelParaError(lblEtiqueta, strMensajeError, Color.LIGHT_GRAY);
            }
        } catch (Exception ex) {
            // Error: Fallo grave durante la carga (I/O, formato, etc.) (se rellena con rojo claro).
            String strMensajeErrorGrave = "Error al cargar la imagen desde: " + strRutaRecurso;
            objLogger.log(Level.SEVERE, strMensajeErrorGrave, ex);
            
            configurarLabelParaError(lblEtiqueta, strMensajeErrorGrave, new Color(255, 200, 200)); 
        }
    }
    
    private static void configurarLabelParaError(JLabel lblEtiqueta, String strMensaje, Color colorFondo) {
        lblEtiqueta.setIcon(null);
        lblEtiqueta.setText(strMensaje);
        lblEtiqueta.setHorizontalAlignment(JLabel.CENTER);
        lblEtiqueta.setBackground(colorFondo);
        lblEtiqueta.setForeground(Color.RED);
        lblEtiqueta.setOpaque(true);
    }

    public static void cargarImagenAButton(JButton jButton3, String strRutaQR, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}