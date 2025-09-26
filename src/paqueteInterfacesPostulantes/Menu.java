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

public class Menu extends javax.swing.JFrame {

    private static final Logger objLogger = Logger.getLogger(Menu.class.getName());

    private final boolean boolDOCUMENTACION_COMPLETA = true;

    private Postulante objPostulante;

    public Menu(Postulante objPostulante) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.objPostulante = objPostulante; 
        
        agregarActionListeners();
    }

    private void agregarActionListeners() {
        btnMetodosDePago.addActionListener(this::btnMetodosDePagoActionPerformed);
        // ... (otros action listeners)
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // ... (resto del método initComponents sin cambios)
        pnlFondo = new JPanel();
        pnlMenuLateral = new JPanel();
        lblTituloMenu = new JLabel("REGISTRO INICIAL");
        btnMetodosDePago = new JButton("Métodos de Pago");
        btnEstadoDeInscripcion = new JButton("Estado de inscripción");
        btnRegistroDeDocumentacion = new JButton("Registro de documentación");
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

        btnRegistroDeDocumentacion.setFont(new Font("Eras Demi ITC", Font.PLAIN, 18));
        btnRegistroDeDocumentacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 3;
        pnlMenuLateral.add(btnRegistroDeDocumentacion, gbc);
        
        btnSalir.setFont(new Font("Eras Demi ITC", Font.PLAIN, 18));
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 4;
        pnlMenuLateral.add(btnSalir, gbc);
        
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        pnlBarraRoja1.setBackground(new Color(204, 0, 0));
        pnlBarraRoja1.setPreferredSize(new Dimension(200, 24));
        gbc.gridy = 5;
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

        try {
            ImageIcon objIcono = new ImageIcon(getClass().getResource("/imagenes de interfaces/inicio de sesion/plogo.png"));
            lblLogoUMSS.setIcon(objIcono);
        } catch (Exception e) {
            objLogger.log(java.util.logging.Level.SEVERE, "Error al cargar la imagen: plogo.png", e);
        }
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
        objLogger.log(Level.INFO, "Botón Métodos de Pago presionado.");

        if (boolDOCUMENTACION_COMPLETA) {
            this.dispose(); 
            
            SwingUtilities.invokeLater(() -> {
                // Se pasa el objeto objPostulante al constructor de la ventana de pago.
                interfazMetododoDePago objVentanaPago = new interfazMetododoDePago(this.objPostulante);
                objVentanaPago.setVisible(true);
            });
            
        } else {
            JOptionPane.showMessageDialog(this, 
                "No puede proceder al pago.\nRazón: No entregó la documentación necesaria. (Postulante: " + objPostulante.getStrNombre() + " " + objPostulante.getStrApellido() + ")", 
                "Documentación Pendiente", 
                JOptionPane.WARNING_MESSAGE);
            objLogger.log(Level.WARNING, "Acceso a pagos denegado. Documentación incompleta (constante en FALSE).");
        }
    }

    private void btnRegistroDeDocumentacionActionPerformed(java.awt.event.ActionEvent evt) {
        // Lógica de Registro de Documentación
    }
    
    private void btnEstadoDeInscripcionActionPerformed(java.awt.event.ActionEvent evt) {
        // Lógica de Estado de Inscripción
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
        // **CÓDIGO DE PRUEBA ELIMINADO.**
        // Si se ejecuta este main, dará un error ya que el constructor de Menu requiere un Postulante. 
        // Es correcto, ya que InicioDeSesion es la clase principal.
        java.awt.EventQueue.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, "Error: Menu no se puede iniciar directamente, debe iniciar desde InicioDeSesion.", "Error de Ejecución", JOptionPane.ERROR_MESSAGE);
            // new Menu(null).setVisible(true); // Evitamos esto para forzar la ejecución desde InicioDeSesion
        });
    }

    private javax.swing.JButton btnEstadoDeInscripcion;
    private javax.swing.JButton btnMetodosDePago;
    private javax.swing.JButton btnRegistroDeDocumentacion;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel lblLogoUMSS;
    private javax.swing.JLabel lblTituloMenu;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlBarraRoja1;
    private javax.swing.JPanel pnlBarraRoja2;
}