package paqueteInterfacesPostulantes;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO;
import paqueteUtilidades.ManejadorImagen; // <--- Única importación añadida

public class Menu extends javax.swing.JFrame {

    private static final Logger objLogger = Logger.getLogger(Menu.class.getName());

    private Postulante objPostulante;
    private RegistroDocumentacion objRegistroDocumentacion;
    private DAO objDEAPersistencia;

    public Menu(Postulante objPostulante, RegistroDocumentacion objRegistroDocumentacion) {
        this.objDEAPersistencia = new DAO();
        
        this.objPostulante = objPostulante;
        this.objRegistroDocumentacion = objRegistroDocumentacion;
        
        actualizarRegistroDocumentacion();
        
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        agregarActionListeners();
    }
    
    private void actualizarRegistroDocumentacion() {
        if (this.objPostulante != null) {
            int intIdUsuario = this.objPostulante.getIntIdUsuario();
            RegistroDocumentacion objNuevoRegistro = objDEAPersistencia.obtenerRegistroDocumentacionPorIdUsuario(intIdUsuario);

            if (objNuevoRegistro != null) {
                this.objRegistroDocumentacion = objNuevoRegistro;
                this.objPostulante.setObjRegistroDocumentacion(objNuevoRegistro);
                objLogger.log(Level.INFO, "Registro de documentación actualizado desde la BD.");
            } else {
                 objLogger.log(Level.WARNING, "No se encontró registro de documentación en la BD para el ID: " + intIdUsuario);
            }
        }
    }

    private void agregarActionListeners() {
        btnMetodosDePago.addActionListener(this::btnMetodosDePagoActionPerformed);
        btnEstadoDeInscripcion.addActionListener(this::btnEstadoDeInscripcionActionPerformed);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        pnlFondo = new JPanel();
        pnlMenuLateral = new JPanel();
        lblTituloMenu = new JLabel("REGISTRO INICIAL");
        btnMetodosDePago = new JButton("Métodos de Pago");
        btnEstadoDeInscripcion = new JButton("Estado de inscripción");
        btnSalir = new JButton("Salir");
        pnlBarraRoja1 = new JPanel();
        pnlBarraRoja2 = new JPanel();
        lblLogoUMSS = new JLabel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlFondo.setBackground(new Color(255, 255, 255));
        pnlFondo.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        pnlMenuLateral.setBackground(new Color(0, 51, 102));
        pnlMenuLateral.setLayout(new GridBagLayout());
        
        lblTituloMenu.setFont(new Font("Eras Bold ITC", Font.PLAIN, 36));
        lblTituloMenu.setForeground(Color.WHITE);
        lblTituloMenu.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(100, 20, 50, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        pnlMenuLateral.add(lblTituloMenu, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 50, 20, 50);

        btnMetodosDePago.setFont(new Font("Eras Demi ITC", Font.PLAIN, 18));
        btnMetodosDePago.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 1;
        pnlMenuLateral.add(btnMetodosDePago, gbc);

        btnEstadoDeInscripcion.setFont(new Font("Eras Demi ITC", Font.PLAIN, 18));
        btnEstadoDeInscripcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 2;
        pnlMenuLateral.add(btnEstadoDeInscripcion, gbc);
        
        btnSalir.setFont(new Font("Eras Demi ITC", Font.PLAIN, 18));
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 3; 
        pnlMenuLateral.add(btnSalir, gbc);
        
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        pnlBarraRoja1.setBackground(new Color(204, 0, 0));
        pnlBarraRoja1.setPreferredSize(new Dimension(200, 24));
        gbc.gridy = 4; 
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        pnlMenuLateral.add(pnlBarraRoja1, gbc);

        GridBagConstraints gbcFondo = new GridBagConstraints();
        gbcFondo.gridx = 0;
        gbcFondo.gridy = 0;
        gbcFondo.fill = GridBagConstraints.BOTH;
        gbcFondo.weightx = 0.25;
        gbcFondo.weighty = 1.0;
        pnlFondo.add(pnlMenuLateral, gbcFondo);

        pnlBarraRoja2.setBackground(new Color(204, 0, 0));
        gbcFondo.gridx = 1;
        gbcFondo.weightx = 0.01;
        pnlFondo.add(pnlBarraRoja2, gbcFondo);
        
        // logica para cargar imagen
        String strRutaLogo = "/imagenes de interfaces/inicio de sesion/plogo.png";
        ManejadorImagen.cargarImagenConManejoError(lblLogoUMSS, strRutaLogo, -1, -1);
        
        gbcFondo.gridx = 2;
        gbcFondo.weightx = 0.74;
        gbcFondo.anchor = GridBagConstraints.CENTER;
        pnlFondo.add(lblLogoUMSS, gbcFondo);

        getContentPane().add(pnlFondo);
        pack();
    }
    
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void btnMetodosDePagoActionPerformed(java.awt.event.ActionEvent evt) {
        objLogger.log(Level.INFO, "Botón Métodos de Pago presionado. Consultando estado de documentación...");

        actualizarRegistroDocumentacion();
        
        boolean blnDocumentacionCompleta = this.objRegistroDocumentacion != null && this.objRegistroDocumentacion.getBolEstadoDocumentacion();

        if (blnDocumentacionCompleta) {
            this.dispose();
            
            SwingUtilities.invokeLater(() -> {
                interfazMetododoDePago objVentanaPago = new interfazMetododoDePago(this.objPostulante, this.objRegistroDocumentacion);
                objVentanaPago.setVisible(true);
            });
            
        } else {
            String strNombreCompleto = (objPostulante != null) ? objPostulante.getNombreCompleto() : "Desconocido";
            
            JOptionPane.showMessageDialog(this,
                "No puede proceder al pago.\nRazón: Su documentación está incompleta o pendiente de registro. (Postulante: " + strNombreCompleto + ")",
                "Documentación Pendiente",
                JOptionPane.WARNING_MESSAGE);
            objLogger.log(Level.WARNING, "Acceso a pagos denegado. Documentación incompleta (Postulante ID: " + objPostulante.getIntIdUsuario() + ")");
        }
    }
    
    private void btnEstadoDeInscripcionActionPerformed(java.awt.event.ActionEvent evt) {
    if (objPostulante != null) {
        this.dispose(); // cerrar el menú actual

        // Abrir la ventana de EstadoInscripcion pasando el objeto Postulante
        SwingUtilities.invokeLater(() -> {
            EstadoInscripcion ventanaEstado = new EstadoInscripcion(objPostulante);
            ventanaEstado.setVisible(true);
        });

    } else {
        JOptionPane.showMessageDialog(this,
            "No se pudo abrir la ventana. Postulante no definido.",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            objLogger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Error: Menu no se puede iniciar directamente, debe iniciar desde InicioDeSesion.", "Error de Ejecución", JOptionPane.ERROR_MESSAGE);
        });
    }

    private javax.swing.JButton btnEstadoDeInscripcion;
    private javax.swing.JButton btnMetodosDePago;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblLogoUMSS;
    private javax.swing.JLabel lblTituloMenu;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlBarraRoja1;
    private javax.swing.JPanel pnlBarraRoja2;
}