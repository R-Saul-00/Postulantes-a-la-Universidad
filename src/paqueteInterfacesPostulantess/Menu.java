package paqueteInterfacesPostulantess;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO;

public class Menu extends javax.swing.JFrame {

    private static final Logger objLogger = Logger.getLogger(Menu.class.getName());

    private Postulante objPostulante;
    private RegistroDocumentacion objRegistroDocumentacion;
    private DAO objDEAPersistencia;

    public Menu(Postulante objPostulante, RegistroDocumentacion objRegistroDocumentacion) {
        this.objDEAPersistencia = new DAO();
        this.objPostulante = objPostulante;
        this.objRegistroDocumentacion = objRegistroDocumentacion;

        initComponents();
        
        this.setExtendedState(JFrame.MAXIMIZED_VERT | JFrame.MAXIMIZED_HORIZ); 
    }
    
    public Menu() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_VERT | JFrame.MAXIMIZED_HORIZ); 
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
                this.objRegistroDocumentacion = null;
                this.objPostulante.setObjRegistroDocumentacion(null);
                objLogger.log(Level.WARNING, "No se encontró registro de documentación para el usuario ID: " + intIdUsuario);
            }
        }
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        botonSalir = new javax.swing.JLabel();
        botonMetodosDePago = new javax.swing.JLabel();
        botonEstadoDeInscripcion = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1060));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 57, 117));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/Menu/logoUmssDorado.png")));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 130));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 60));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Menú Principal");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 530, 90));

        botonSalir.setFont(new java.awt.Font("Segoe UI Black", 0, 36));
        botonSalir.setForeground(new java.awt.Color(239, 184, 16));
        botonSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonSalir.setText("Salir");
        botonSalir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonSalirMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonSalirMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonSalirMouseExited(evt);
            }
        });
        jPanel2.add(botonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 430, 80));

        botonMetodosDePago.setFont(new java.awt.Font("Segoe UI Black", 0, 36));
        botonMetodosDePago.setForeground(new java.awt.Color(239, 184, 16));
        botonMetodosDePago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonMetodosDePago.setText("Métodos de Pago");
        botonMetodosDePago.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonMetodosDePago.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonMetodosDePago.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonMetodosDePagoMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonMetodosDePagoMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonMetodosDePagoMouseExited(evt);
            }
        });
        jPanel2.add(botonMetodosDePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 430, 80));

        botonEstadoDeInscripcion.setFont(new java.awt.Font("Segoe UI Black", 0, 36));
        botonEstadoDeInscripcion.setForeground(new java.awt.Color(239, 184, 16));
        botonEstadoDeInscripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonEstadoDeInscripcion.setText("Estado De Inscripción");
        botonEstadoDeInscripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonEstadoDeInscripcion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonEstadoDeInscripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEstadoDeInscripcionMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonEstadoDeInscripcionMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonEstadoDeInscripcionMouseExited(evt);
            }
        });
        jPanel2.add(botonEstadoDeInscripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 430, 80));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/Menu/salir.png")));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 590, 100, 100));

        jPanel3.setBackground(new java.awt.Color(239, 184, 16));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 30, 820));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/Menu/metodoPago.png")));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 100, 100));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/Menu/estado.png")));
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 420, 100, 100));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/Menu/paseoUmssPrueba.jpg")));
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 620, 820));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 815));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );

        pack();
    }

    private void botonMetodosDePagoMouseEntered(java.awt.event.MouseEvent evt) {                                             
        botonMetodosDePago.setForeground(new Color(255, 224,23));
    }                                            

    private void botonMetodosDePagoMouseExited(java.awt.event.MouseEvent evt) {                                            
        botonMetodosDePago.setForeground(new Color(239,184,16));
    }                                           

    private void botonEstadoDeInscripcionMouseEntered(java.awt.event.MouseEvent evt) {                                                    
        botonEstadoDeInscripcion.setForeground(new Color(255, 224,23));
    }                                                   

    private void botonEstadoDeInscripcionMouseExited(java.awt.event.MouseEvent evt) {                                                   
        botonEstadoDeInscripcion.setForeground(new Color(239,184,16));
    }                                                  

    private void botonSalirMouseEntered(java.awt.event.MouseEvent evt) {                                      
        botonSalir.setForeground(new Color(255, 224,23));
    }                                     

    private void botonSalirMouseExited(java.awt.event.MouseEvent evt) {                                     
        botonSalir.setForeground(new Color(239,184,16));
    }                                    

    private void botonMetodosDePagoMouseClicked(java.awt.event.MouseEvent evt) {                                              
        objLogger.log(Level.INFO, "Botón Métodos de Pago presionado. Consultando estado de documentación...");

        actualizarRegistroDocumentacion();

        boolean blnDocumentacionCompleta = this.objRegistroDocumentacion != null && this.objRegistroDocumentacion.getBolEstadoDocumentacion();

        if (blnDocumentacionCompleta) {
            this.dispose();

            SwingUtilities.invokeLater(() -> {
                MetododoDePago objVentanaPago = new MetododoDePago(this.objPostulante, this.objRegistroDocumentacion);
                objVentanaPago.setVisible(true);
            });

        } else {
            String strNombreCompleto = (objPostulante != null) ? objPostulante.getNombreCompleto() : "Desconocido";

            JOptionPane.showMessageDialog(this,
                "No puede proceder al pago.\nRazón: Su documentación está incompleta o pendiente de registro. (Postulante: " + strNombreCompleto + ")",
                "Documentación Pendiente",
                JOptionPane.WARNING_MESSAGE);
            
            int intIdUsuario = (objPostulante != null) ? objPostulante.getIntIdUsuario() : -1;
            objLogger.log(Level.WARNING, "Acceso a pagos denegado. Documentación incompleta (Postulante ID: " + intIdUsuario + ")");
        }
    }                                             

    private void botonEstadoDeInscripcionMouseClicked(java.awt.event.MouseEvent evt) {                                                    
        objLogger.log(Level.INFO, "Botón Estado de Inscripción presionado.");

        actualizarRegistroDocumentacion();

        this.dispose();

        SwingUtilities.invokeLater(() -> {
            EstadoInscripcion objVentanaEstado = new EstadoInscripcion(this.objPostulante, this.objRegistroDocumentacion);
            objVentanaEstado.setVisible(true);
        });
    }                                                   

    private void botonSalirMouseClicked(java.awt.event.MouseEvent evt) {                                      
        System.exit(0);
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
            objLogger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
    }

    private javax.swing.JLabel botonEstadoDeInscripcion;
    private javax.swing.JLabel botonMetodosDePago;
    private javax.swing.JLabel botonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
}