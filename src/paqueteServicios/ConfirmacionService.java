package paqueteServicios;

/**
 * Clase para manejar confirmaciones del sistema
 * Proporciona funcionalidad para mostrar mensajes de confirmación con delays
 * @author Marca
 */
public class ConfirmacionService {
    
    /**
     * Método que simula el proceso de confirmación después del registro
     * Muestra un mensaje de confirmación con un delay
     */
    public void mostrarConfirmacionRegistro() {
        System.out.println("Procesando registro...");
        
        // procesamiento (3 segundos)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.err.println("Error en el proceso: " + e.getMessage());
        }
        
        // mensaje de confirmación
        System.out.println("Registro confirmado exitosamente");
        System.out.println("Se ha enviado una confirmacion a su correo electronico");
    }
    
    /**
     * Método mensaje de error
     * @param mensaje Mensaje personalizado de confirmación
     */
    public void mostrarConfirmacionRegistro(String mensaje) {
        System.out.println("Procesando solicitud...");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Error en el proceso: " + e.getMessage());
        }
        
        System.out.println(mensaje + "");
    }
    
    /**
     * Método main para pruebas
     * @param args
     */
    public static void main(String[] args) {
        ConfirmacionService confirmacion = new ConfirmacionService();
        confirmacion.mostrarConfirmacionRegistro();
    }
}