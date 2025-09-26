package paqueteServicios;

import paquetePersistencia.DEAPrueba;;

public class ServicioAutenticarPostulante {
    
    private DEAPrueba objDEAPrueba;

    public ServicioAutenticarPostulante() {
        this.objDEAPrueba = new DEAPrueba();
    }
    
    public Postulante autenticarPostulante(String strCorreo, String strContrasenia) {
        
        if (strCorreo == null || strCorreo.trim().isEmpty() || strContrasenia == null || strContrasenia.trim().isEmpty()) {
            System.err.println("Error: Correo o contraseña vacíos.");
            return null;
        }

        // 1. Llamar al DEA para verificar la autenticación usando la contraseña en texto plano
        // Nota: Pasamos strContrasenia, NO un hash.
        boolean blnAutenticacionExitosa = objDEAPrueba.autenticarUsuario(strCorreo, strContrasenia);
        
        if (blnAutenticacionExitosa) {
            // 2. Si la autenticación es exitosa, obtenemos todos los datos
            Postulante objPostulante = objDEAPrueba.obtenerPostulantePorCorreo(strCorreo);
            
            if (objPostulante != null) {
                System.out.println("Autenticación exitosa para: " + strCorreo);
                return objPostulante;
            } else {
                System.err.println("Error: Postulante autenticado, pero datos no encontrados.");
                return null;
            }
        } else {
            System.err.println("Autenticación fallida: Credenciales incorrectas para " + strCorreo);
            return null;
        }
    }
}