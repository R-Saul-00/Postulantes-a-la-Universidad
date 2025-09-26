package paqueteServicios;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;

public class GeneradorDeQR {
    // Propiedades de la cuenta
    private static final String NUMERO_CUENTA = "1234567890";
    private static final String MONTO_POR_DEFECTO = "601.00"; 
    private static final String CONCEPTO_POR_DEFECTO = "Pago de matricula";
    
    // Dimensiones del QR
    private static final int ANCHO_QR = 250;
    private static final int ALTO_QR = 250;
    
    private static boolean pagoRealizado;

    public GeneradorDeQR() {
        this.pagoRealizado = false;
    }

    /**
     * Genera un codigo QR con valores por defecto y un nombre de postulante
     * @param strNombrePostulante el nombre del postulante a incluir en el QR
     * @return BufferedImage la imagen del QR generada o null si hay un error
     */
    public BufferedImage generarImagenQRPago(String strNombrePostulante) {
        // Se construye la cadena de datos con el nuevo campo
        String strDatosPago = "CUENTA:" + NUMERO_CUENTA + 
                              " | MONTO:" + MONTO_POR_DEFECTO + 
                              " | CONCEPTO:" + CONCEPTO_POR_DEFECTO + 
                              " | NOMBRE:" + strNombrePostulante;

        try {
            QRCodeWriter objEscritor = new QRCodeWriter();
            BitMatrix bitMatrix = objEscritor.encode(strDatosPago, BarcodeFormat.QR_CODE, ANCHO_QR, ALTO_QR);
            
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            System.err.println("Error al generar la imagen del QR: " + e.getMessage());
            return null;
        }
    }

    public void setPagoRealizado(boolean bEstadoPago) {
        pagoRealizado = bEstadoPago;
    }

    public boolean getEstadoPago() {
        return pagoRealizado;
    }
}