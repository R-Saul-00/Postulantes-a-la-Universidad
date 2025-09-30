package paqueteInterfacesPostulantess;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

public class EstadoInscripcion extends javax.swing.JFrame {
    private static final Logger objLogger = Logger.getLogger(EstadoInscripcion.class.getName());
    private Postulante objPostulante;
    private RegistroDocumentacion objRegistroDocumentacion;
    private DAO objDEAPersistencia;


    public EstadoInscripcion(Postulante objPostulante, RegistroDocumentacion objRegistroDocumentacion) {
        initComponents();
        
        this.objDEAPersistencia = new DAO();
        this.objPostulante = objPostulante;
        this.objRegistroDocumentacion = objRegistroDocumentacion;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

        cargarDatosPostulante();
    }
    
    private void cargarDatosPostulante() {
        if (objPostulante != null) {
            lblNombrePostulante.setText(objPostulante.getStrNombre());
            lblApellidoPostulante.setText(objPostulante.getStrApellido());
            lblCIPostulante.setText(objPostulante.getStrNumeroCI());
            lblFechaPostulante.setText(objPostulante.getStrFechaNacimiento());
            lblCorreoPostulante.setText(objPostulante.getStrCorreoElectronico());

            if (objRegistroDocumentacion != null) {
                lblCarrera.setText(objRegistroDocumentacion.getStrCarrera() != null ? objRegistroDocumentacion.getStrCarrera() : "No seleccionada");
                lblFacultad.setText(objRegistroDocumentacion.getStrFacultad() != null ? objRegistroDocumentacion.getStrFacultad() : "No seleccionada");
                
                // Se verifica el estado de la documentación
                actualizarCheckCI(objPostulante.getStrNumeroCI() != null && !objPostulante.getStrNumeroCI().isEmpty());
                actualizarCheckBachiller(objRegistroDocumentacion.getStrCodigoCertificadoPromo() != null && !objRegistroDocumentacion.getStrCodigoCertificadoPromo().isEmpty());
                actualizarCheckPago(objRegistroDocumentacion.getBolEstadoDePago());
            } else {
                lblCarrera.setText("No seleccionada");
                lblFacultad.setText("No seleccionada");
                actualizarCheckCI(false);
                actualizarCheckBachiller(false);
                actualizarCheckPago(false);
            }
            actualizarMensajeExito();
            asignarAsiento();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "No se pudo cargar el postulante.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void actualizarEstado() {
        if (objPostulante != null) {
            objLogger.log(Level.INFO, "Actualizando estado de inscripción para postulante ID: {0}", objPostulante.getIntIdUsuario());
            
            RegistroDocumentacion objNuevoRegistroDoc = objDEAPersistencia.obtenerRegistroDocumentacionPorIdUsuario(objPostulante.getIntIdUsuario());

            if (objNuevoRegistroDoc != null) {
                this.objRegistroDocumentacion = objNuevoRegistroDoc;
                this.objPostulante.setObjRegistroDocumentacion(objNuevoRegistroDoc);
                //JOptionPane.showMessageDialog(this, "Estado de inscripción actualizado.", "Actualización Completa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.objRegistroDocumentacion = null;
                this.objPostulante.setObjRegistroDocumentacion(null);
                JOptionPane.showMessageDialog(this, "No se encontró registro de documentación. Verifique que haya llenado el formulario de documentación.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            cargarDatosPostulante();
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se encontró la información del postulante para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarMensajeBachiller(boolean bolEstado) {
        lblMensajeBachiller.setText(bolEstado ? "Presentado" : "Pendiente de presentar!");
    }

    private void actualizarMensajeCI(boolean bolEstado) {
        lblMensajeCI.setText(bolEstado ? "Presentado" : "Pendiente de presentar!");
    }

    private void actualizarMensajePago(boolean bolEstado) {
        lblMensajePago.setText(bolEstado ? "Pagado" : "Pago no realizado!");
    }
    
    private void actualizarCheckBachiller(boolean bolEstado) {
        String strRutaImagen = bolEstado
            ? "/imagenes de interfaces/EstadoInscripcion/Check.png"
            : "/imagenes de interfaces/EstadoInscripcion/UnCheck.png";
        lblCheckBachiller.setIcon(new javax.swing.ImageIcon(getClass().getResource(strRutaImagen)));
        actualizarMensajeBachiller(bolEstado);
    }

    private void actualizarCheckCI(boolean bolEstado) {
        String strRutaImagen = bolEstado
            ? "/imagenes de interfaces/EstadoInscripcion/Check.png"
            : "/imagenes de interfaces/EstadoInscripcion/UnCheck.png";
        lblCheckCI.setIcon(new javax.swing.ImageIcon(getClass().getResource(strRutaImagen)));
        actualizarMensajeCI(bolEstado);
    }

    private void actualizarCheckPago(boolean bolEstado) {
        String strRutaImagen = bolEstado
            ? "/imagenes de interfaces/EstadoInscripcion/Check.png"
            : "/imagenes de interfaces/EstadoInscripcion/UnCheck.png";
        lblCheckPago.setIcon(new javax.swing.ImageIcon(getClass().getResource(strRutaImagen)));
        actualizarMensajePago(bolEstado);
    }

    private void actualizarMensajeExito() {
        if (lblMensajeCI.getText().equals("Presentado") &&
            lblMensajeBachiller.getText().equals("Presentado") && 
            lblMensajePago.getText().equals("Pagado")) 
        {
            lblMensajeExito.setText("Usted está habilitado! :)");
        } else {
            lblMensajeExito.setText("");
        }
    }

    private void asignarAsiento() {
        if (objRegistroDocumentacion != null && lblMensajeExito.getText().contains("habilitado")) {
            lblAsientoDesignado.setText(String.valueOf(objRegistroDocumentacion.getIntIdRegistroDoc()));
        } else {
            lblAsientoDesignado.setText("Ninguno");
        }
    }
    
    private void botonRetrocederMouseClicked(java.awt.event.MouseEvent evt) {
        Menu menuVentana = new Menu(this.objPostulante, this.objRegistroDocumentacion);
        menuVentana.setVisible(true);
        this.dispose();
    }

    private void botonActualizarMouseClicked(java.awt.event.MouseEvent evt) {
        actualizarEstado();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        lblCheckCI = new javax.swing.JLabel();
        lblMensajeCI = new javax.swing.JLabel();
        lblMensajeExito = new javax.swing.JLabel();
        pnlFondo = new javax.swing.JPanel();
        lblPieLogo = new javax.swing.JLabel();
        pnlLateral = new javax.swing.JPanel();
        lblTituloPostulante = new javax.swing.JLabel();
        separadorLateral = new javax.swing.JSeparator();
        lblCorreoPostulante = new javax.swing.JLabel();
        lblTituloNombre = new javax.swing.JLabel();
        lblNombrePostulante = new javax.swing.JLabel();
        lblTituloApellido = new javax.swing.JLabel();
        lblApellidoPostulante = new javax.swing.JLabel();
        lblTituloCI = new javax.swing.JLabel();
        lblCIPostulante = new javax.swing.JLabel();
        lblTituloFecha = new javax.swing.JLabel();
        lblFechaPostulante = new javax.swing.JLabel();
        lblCarrera = new javax.swing.JLabel();
        lblTituloCorreo = new javax.swing.JLabel();
        lblTituloFacultad = new javax.swing.JLabel();
        lblFacultad = new javax.swing.JLabel();
        lblTituloCarrera = new javax.swing.JLabel();
        lblTituloPrincipal = new javax.swing.JLabel();
        lblTituloPago = new javax.swing.JLabel();
        lblTituloBachiller = new javax.swing.JLabel();
        separadorBachiller = new javax.swing.JSeparator();
        separadorPago = new javax.swing.JSeparator();
        lblCheckPago = new javax.swing.JLabel();
        lblCheckBachiller = new javax.swing.JLabel();
        lblMensajePago = new javax.swing.JLabel();
        lblMensajeBachiller = new javax.swing.JLabel();
        lblBotonRetroceder = new javax.swing.JLabel();
        lblBotonActualizar = new javax.swing.JLabel();
        lblTituloAsiento = new javax.swing.JLabel();
        lblAsientoDesignado = new javax.swing.JLabel();

        lblCheckCI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/EstadoInscripcion/Check.png")));

        lblMensajeCI.setBackground(new java.awt.Color(51, 51, 51));
        lblMensajeCI.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        lblMensajeCI.setForeground(new java.awt.Color(102, 102, 102));
        lblMensajeCI.setText("lblMensajeCI");

        lblMensajeExito.setFont(new java.awt.Font("Segoe UI Black", 0, 36));
        lblMensajeExito.setForeground(new java.awt.Color(0, 153, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlFondo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/imagenes de interfaces/EstadoInscripcion/PielogoUMSS.jpg"));
        Image imagenRedimensionada = iconoOriginal.getImage().getScaledInstance(
             1600,
             100,
             Image.SCALE_SMOOTH
        );
        lblPieLogo.setIcon(new ImageIcon(imagenRedimensionada));

        lblPieLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlFondo.add(lblPieLogo, new AbsoluteConstraints(-1, 680, 1600, 100));

        pnlLateral.setBackground(new java.awt.Color(196, 95, 95));
        pnlLateral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTituloPostulante.setFont(new java.awt.Font("Segoe UI Black", 0, 32));
        lblTituloPostulante.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPostulante.setText("Postulante :");
        pnlLateral.add(lblTituloPostulante, new AbsoluteConstraints(5, 30, 360, 40));

        pnlLateral.add(separadorLateral, new AbsoluteConstraints(0, 80, 380, 10));

        lblCorreoPostulante.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        lblCorreoPostulante.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreoPostulante.setText("");
        pnlLateral.add(lblCorreoPostulante, new AbsoluteConstraints(20, 455, -1, -1));

        lblTituloNombre.setFont(new java.awt.Font("Segoe UI Black", 0, 19));
        lblTituloNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloNombre.setText("Nombre :");
        pnlLateral.add(lblTituloNombre, new AbsoluteConstraints(20, 110, -1, -1));

        lblNombrePostulante.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        lblNombrePostulante.setForeground(new java.awt.Color(255, 255, 255));
        lblNombrePostulante.setText("");
        pnlLateral.add(lblNombrePostulante, new AbsoluteConstraints(20, 135, -1, -1));

        lblTituloApellido.setFont(new java.awt.Font("Segoe UI Black", 0, 19));
        lblTituloApellido.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloApellido.setText("Apellido :");
        pnlLateral.add(lblTituloApellido, new AbsoluteConstraints(20, 190, -1, -1));

        lblApellidoPostulante.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        lblApellidoPostulante.setForeground(new java.awt.Color(255, 255, 255));
        lblApellidoPostulante.setText("");
        pnlLateral.add(lblApellidoPostulante, new AbsoluteConstraints(20, 215, -1, -1));

        lblTituloCI.setFont(new java.awt.Font("Segoe UI Black", 0, 19));
        lblTituloCI.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloCI.setText("CI :");
        pnlLateral.add(lblTituloCI, new AbsoluteConstraints(20, 270, -1, -1));

        lblCIPostulante.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        lblCIPostulante.setForeground(new java.awt.Color(255, 255, 255));
        lblCIPostulante.setText("");
        pnlLateral.add(lblCIPostulante, new AbsoluteConstraints(20, 295, -1, -1));

        lblTituloFecha.setFont(new java.awt.Font("Segoe UI Black", 0, 19));
        lblTituloFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloFecha.setText("Fecha de Nacimiento :");
        pnlLateral.add(lblTituloFecha, new AbsoluteConstraints(20, 350, -1, -1));

        lblFechaPostulante.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        lblFechaPostulante.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaPostulante.setText("");
        pnlLateral.add(lblFechaPostulante, new AbsoluteConstraints(20, 375, -1, -1));

        lblCarrera.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        lblCarrera.setForeground(new java.awt.Color(255, 255, 255));
        lblCarrera.setText("");
        pnlLateral.add(lblCarrera, new AbsoluteConstraints(20, 615, -1, -1));

        lblTituloCorreo.setFont(new java.awt.Font("Segoe UI Black", 0, 19));
        lblTituloCorreo.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloCorreo.setText("Correo Electronico :");
        pnlLateral.add(lblTituloCorreo, new AbsoluteConstraints(20, 430, -1, -1));

        lblTituloFacultad.setFont(new java.awt.Font("Segoe UI Black", 0, 19));
        lblTituloFacultad.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloFacultad.setText("Facultad :");
        pnlLateral.add(lblTituloFacultad, new AbsoluteConstraints(20, 510, -1, -1));

        lblFacultad.setFont(new java.awt.Font("Segoe UI Black", 0, 16));
        lblFacultad.setForeground(new java.awt.Color(255, 255, 255));
        lblFacultad.setText("");
        pnlLateral.add(lblFacultad, new AbsoluteConstraints(20, 535, -1, -1));

        lblTituloCarrera.setFont(new java.awt.Font("Segoe UI Black", 0, 19));
        lblTituloCarrera.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloCarrera.setText("Carrera :");
        pnlLateral.add(lblTituloCarrera, new AbsoluteConstraints(20, 590, -1, -1));

        pnlFondo.add(pnlLateral, new AbsoluteConstraints(1220, 0, 380, 730));

        lblTituloPrincipal.setFont(new java.awt.Font("Segoe UI Black", 0, 42));
        lblTituloPrincipal.setForeground(new java.awt.Color(0, 55, 130));
        lblTituloPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrincipal.setText("Estado de Inscripcion");
        pnlFondo.add(lblTituloPrincipal, new AbsoluteConstraints(300, 10, 680, -1));

        lblTituloPago.setFont(new java.awt.Font("Segoe UI Black", 0, 22));
        lblTituloPago.setText("Pago de Valorado :");
        pnlFondo.add(lblTituloPago, new AbsoluteConstraints(110, 440, 450, -1));

        lblTituloBachiller.setFont(new java.awt.Font("Segoe UI Black", 0, 22));
        lblTituloBachiller.setText("Titulo de Bachiller :");
        pnlFondo.add(lblTituloBachiller, new AbsoluteConstraints(120, 290, 450, -1));

        pnlFondo.add(separadorBachiller, new AbsoluteConstraints(110, 333, 940, 10));

        pnlFondo.add(separadorPago, new AbsoluteConstraints(110, 480, 940, 20));

        lblCheckPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/EstadoInscripcion/Uncheck.png")));
        pnlFondo.add(lblCheckPago, new AbsoluteConstraints(1000, 435, 40, 40));

        lblCheckBachiller.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/EstadoInscripcion/Uncheck.png")));
        pnlFondo.add(lblCheckBachiller, new AbsoluteConstraints(1000, 290, 40, 40));

        lblMensajePago.setBackground(new java.awt.Color(51, 51, 51));
        lblMensajePago.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        lblMensajePago.setForeground(new java.awt.Color(102, 102, 102));
        lblMensajePago.setText("lblMensajePago");
        pnlFondo.add(lblMensajePago, new AbsoluteConstraints(780, 440, -1, -1));

        lblMensajeBachiller.setBackground(new java.awt.Color(51, 51, 51));
        lblMensajeBachiller.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        lblMensajeBachiller.setForeground(new java.awt.Color(102, 102, 102));
        lblMensajeBachiller.setText("lblMensajeBachiller");
        pnlFondo.add(lblMensajeBachiller, new AbsoluteConstraints(780, 300, -1, -1));

        lblBotonRetroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/EstadoInscripcion/Retroceder.png")));
        lblBotonRetroceder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRetrocederMouseClicked(evt);
            }
        });
        pnlFondo.add(lblBotonRetroceder, new AbsoluteConstraints(0, 0, 110, 120));

        lblBotonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/EstadoInscripcion/Actualizar.png")));
        lblBotonActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonActualizarMouseClicked(evt);
            }
        });
        pnlFondo.add(lblBotonActualizar, new AbsoluteConstraints(160, 0, 90, 110));

        lblTituloAsiento.setFont(new java.awt.Font("Segoe UI Black", 0, 22));
        lblTituloAsiento.setText("Su asiento designado para el examen es :");
        lblTituloAsiento.setToolTipText("");
        pnlFondo.add(lblTituloAsiento, new AbsoluteConstraints(110, 570, 840, 110));

        lblAsientoDesignado.setFont(new java.awt.Font("Segoe UI Black", 0, 22));
        lblAsientoDesignado.setText("jLabel");
        pnlFondo.add(lblAsientoDesignado, new AbsoluteConstraints(820, 590, 640, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );

        pack();
    }
    
    private javax.swing.JLabel lblBotonActualizar;
    private javax.swing.JLabel lblApellidoPostulante;
    private javax.swing.JLabel lblAsientoDesignado;
    private javax.swing.JLabel lblCIPostulante;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblCheckBachiller;
    private javax.swing.JLabel lblCheckCI;
    private javax.swing.JLabel lblCheckPago;
    private javax.swing.JLabel lblCorreoPostulante;
    private javax.swing.JLabel lblFacultad;
    private javax.swing.JLabel lblFechaPostulante;
    private javax.swing.JLabel lblMensajeBachiller;
    private javax.swing.JLabel lblMensajeCI;
    private javax.swing.JLabel lblMensajeExito;
    private javax.swing.JLabel lblMensajePago;
    private javax.swing.JLabel lblNombrePostulante;
    private javax.swing.JLabel lblBotonRetroceder;
    private javax.swing.JLabel lblPieLogo;
    private javax.swing.JLabel lblTituloFecha;
    private javax.swing.JLabel lblTituloCorreo;
    private javax.swing.JLabel lblTituloFacultad;
    private javax.swing.JLabel lblTituloCarrera;
    private javax.swing.JLabel lblTituloPrincipal;
    private javax.swing.JLabel lblTituloPago;
    private javax.swing.JLabel lblTituloBachiller;
    private javax.swing.JLabel lblTituloPostulante;
    private javax.swing.JLabel lblTituloNombre;
    private javax.swing.JLabel lblTituloApellido;
    private javax.swing.JLabel lblTituloCI;
    private javax.swing.JLabel lblTituloAsiento;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlLateral;
    private javax.swing.JSeparator separadorLateral;
    private javax.swing.JSeparator separadorBachiller;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator separadorPago;
}