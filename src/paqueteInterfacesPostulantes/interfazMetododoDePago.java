package paqueteInterfacesPostulantes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import paqueteServicios.Postulante; // Importación necesaria

public class interfazMetododoDePago extends javax.swing.JFrame {
    
    private static final Logger objLogger = Logger.getLogger(interfazMetododoDePago.class.getName());
    
    private int iOpcionSeleccionada = 2;
    
    private Postulante objPostulante; 
    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    
    public interfazMetododoDePago(Postulante objPostulanteRecibido) {
        initComponents();
        
        // Asignar el objeto recibido al atributo local
        this.objPostulante = objPostulanteRecibido;
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        actualizarBordesOpciones();
        objLogger.log(Level.INFO, "Ventana de Pago iniciada para: " + objPostulante.getStrNombre() + " " + objPostulante.getStrApellido());
    }
    
    /**
     * CONSTRUCTOR DE PRUEBA: Para iniciar la ventana en modo independiente.
     */
    public interfazMetododoDePago() {
        // Constructor de prueba o de emergencia (no usar en producción real)
        this.objPostulante = new Postulante(0, "debug@test.com", "Test", "User", "0000000", "01/01/2000"); 
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        actualizarBordesOpciones();
        objLogger.log(Level.WARNING, "Ventana de Pago iniciada con Postulante de DEBUG.");
    }

    private void actualizarBordesOpciones() {
        Border bordeNoSeleccionado = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border bordeSeleccionado = BorderFactory.createLineBorder(new Color(100, 149, 237), 3);
        
        Border bordeTemp2, bordeTemp3;
        
        if (this.iOpcionSeleccionada == 1) {
            bordeTemp2 = bordeSeleccionado;
            bordeTemp3 = bordeNoSeleccionado;
        } else if (this.iOpcionSeleccionada == 2) {
            bordeTemp2 = bordeNoSeleccionado;
            bordeTemp3 = bordeSeleccionado;
        } else {
            bordeTemp2 = bordeNoSeleccionado;
            bordeTemp3 = bordeNoSeleccionado;
        }
        
        jButton2.setBorder(bordeTemp2);
        jButton3.setBorder(bordeTemp3);
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel("SELECCIONE METODO DE PAGO", SwingConstants.CENTER);
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel("MONTO TOTAL: 350 BS", SwingConstants.CENTER);
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel("FORMA DE PAGO", SwingConstants.LEFT);
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel("DEPOSITO BANCARIO", SwingConstants.CENTER);
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel("PAGO CON QR", SwingConstants.CENTER);
        jButton1 = new javax.swing.JButton("CONTINUAR CON EL PAGO");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new BorderLayout());

        jLabel1.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
        jLabel1.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        jPanel1.add(jLabel1, BorderLayout.NORTH);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSur.setBackground(Color.WHITE);
        
        jButton1.setBackground(new Color(204, 204, 204));
        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jButton1.setForeground(Color.BLACK);
        jButton1.setPreferredSize(new Dimension(300, 45));
        jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
        
        panelSur.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        panelSur.add(jButton1);
        
        jPanel1.add(panelSur, BorderLayout.SOUTH);
        
        JPanel jPanelContenedorCentral = new JPanel(new GridBagLayout());
        jPanelContenedorCentral.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        jPanel3.setBackground(new Color(220, 220, 220));
        jPanel3.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 50));
        jPanel3.setPreferredSize(new Dimension(500, 70));
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel4.setForeground(new Color(0, 0, 153));
        jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel3.add(jLabel4);
        
        gbc.gridy = 0;
        gbc.ipadx = 100;
        jPanelContenedorCentral.add(jPanel3, gbc);

        jPanel2.setLayout(new BorderLayout());
        jPanel2.setBackground(new Color(245, 245, 245));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        jPanel2.setPreferredSize(new Dimension(500, 300));
        
        jLabel5.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
        jLabel5.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 0));
        jPanel2.add(jLabel5, BorderLayout.NORTH);

        JPanel panelContenedorBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        panelContenedorBotones.setBackground(new Color(245, 245, 245));

        jPanel4.setBackground(new Color(245, 245, 245));
        jPanel4.setPreferredSize(new Dimension(150, 180));
        jPanel4.setLayout(new BorderLayout(5, 5));
        
        jButton2.setBackground(Color.WHITE);
        
        try {
            String strRutaBanco = "/imagenes de interfaces/metodos de pago/banco.jpg";
            ImageIcon imgBanco = new ImageIcon(getClass().getResource(strRutaBanco));
            
            Image imgEscaladaBanco = imgBanco.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            jButton2.setIcon(new ImageIcon(imgEscaladaBanco));
            jButton2.setText(null);
        } catch (Exception e) {
            objLogger.log(Level.WARNING, "Error al cargar la imagen 'banco.jpg'.", e);
            jButton2.setText("No hay imagen");
        }
        
        jButton2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jLabel2.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        jPanel4.add(jButton2, BorderLayout.CENTER);
        jPanel4.add(jLabel2, BorderLayout.SOUTH);
        panelContenedorBotones.add(jPanel4);

        jPanel5.setBackground(new Color(245, 245, 245));
        jPanel5.setPreferredSize(new Dimension(150, 180));
        jPanel5.setLayout(new BorderLayout(5, 5));
        
        jButton3.setBackground(Color.WHITE);
        
        try {
            String strRutaQR = "/imagenes de interfaces/metodos de pago/imagenqr.jpg";
            ImageIcon imgQR = new ImageIcon(getClass().getResource(strRutaQR));
            
            Image imgEscaladaQR = imgQR.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            jButton3.setIcon(new ImageIcon(imgEscaladaQR));
            jButton3.setText(null);
        } catch (Exception e) {
            objLogger.log(Level.WARNING, "Error al cargar la imagen 'imagenqr.jpg'.", e);
            jButton3.setText("No hay imagen");
        }
        
        jButton3.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jLabel3.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        jPanel5.add(jButton3, BorderLayout.CENTER);
        jPanel5.add(jLabel3, BorderLayout.SOUTH);
        panelContenedorBotones.add(jPanel5);
        
        jPanel2.add(panelContenedorBotones, BorderLayout.CENTER);
        
        gbc.gridy = 1;
        gbc.ipadx = 0;
        jPanelContenedorCentral.add(jPanel2, gbc);
        
        jPanel1.add(jPanelContenedorCentral, BorderLayout.CENTER);

        getContentPane().add(jPanel1);
        pack();
        setLocationRelativeTo(null);
        
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton3.addActionListener(this::jButton3ActionPerformed);
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.iOpcionSeleccionada == 2) {
            objLogger.log(Level.INFO, "Botón CONTINUAR (jButton1) presionado. Redirigiendo a QR.");
            
            this.dispose();
            
            SwingUtilities.invokeLater(() -> {
                String strNombrePostulante = this.objPostulante.getStrNombre() + " " + this.objPostulante.getStrApellido(); 
                
                VentanaQrGenerado objVentanaQR = new VentanaQrGenerado(this.objPostulante);
                objVentanaQR.setVisible(true);
            });
            
        } else if (this.iOpcionSeleccionada == 1) {
            // ... (Resto del código sin cambios)
            objLogger.log(Level.INFO, "Botón CONTINUAR (jButton1) presionado. Opción Depósito seleccionada.");
            JOptionPane.showMessageDialog(this, "Redirigiendo a Ventana de Depósito (Aún no implementada)");
        } else {
            objLogger.log(Level.WARNING, "Botón CONTINUAR (jButton1) presionado. Ninguna opción seleccionada.");
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un método de pago.");
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        objLogger.log(Level.INFO, "Botón DEPÓSITO (jButton2) presionado.");
        this.iOpcionSeleccionada = 1;
        actualizarBordesOpciones();
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        objLogger.log(Level.INFO, "Botón PAGO CON QR (jButton3) presionado.");
        this.iOpcionSeleccionada = 2;
        actualizarBordesOpciones();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            objLogger.log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> new interfazMetododoDePago().setVisible(true));
    }
}