package paqueteServicios;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestGeneradorQR {

    public static void main(String[] args) {
        GeneradorDeQR objGeneradorQR = new GeneradorDeQR();
        // Se define la ruta completa donde se guardará el QR
        String strRutaCarpeta = "src" + File.separator + "QR generados";
        String strNombreArchivo = "QR_Matricula.png";
        
        try {
            // Se genera un QR con el nombre
            BufferedImage imgQR = objGeneradorQR.generarImagenQRPago("Erick Arnez");

            if (imgQR != null) {
                // Se crea un objeto File para la carpeta
                File objCarpeta = new File(strRutaCarpeta);
                
                // Se verifica si la carpeta existe y, si no, se crea
                if (!objCarpeta.exists()) {
                    // mkdirs() crea los directorios padres si no existen
                    objCarpeta.mkdirs(); 
                }
                // Se crea un objeto File con la ruta completa del archivo
                File archivoSalida = new File(objCarpeta, strNombreArchivo);
                // Se guarda la imagen en el archivo
                ImageIO.write(imgQR, "png", archivoSalida);
                System.out.println("Código QR generado y guardado en: " + archivoSalida.getAbsolutePath());
            } else {
                System.err.println("No se pudo generar el código QR.");
            }
        } catch (IOException e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
        }
    }
}