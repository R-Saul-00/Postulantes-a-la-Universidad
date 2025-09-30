package paqueteInterfacesPostulantes;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO;


public class MetododoDePago extends javax.swing.JFrame {
    
    private static final Logger objLogger = Logger.getLogger(MetododoDePago.class.getName());

    private int iOpcionSeleccionada = 2;
    private Postulante objPostulante;
    private RegistroDocumentacion objRegistroDocumentacion;
    private DAO objDAOPersistencia;
    
    
    public MetododoDePago(Postulante objPostulanteRecibido, RegistroDocumentacion objRegistroDocumentacionRecibido) {
        this.objDAOPersistencia = new DAO();
        
        this.objPostulante = objPostulanteRecibido;
        this.objRegistroDocumentacion = objRegistroDocumentacionRecibido;
        
        actualizarRegistroDocumentacion();
        
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        actualizarBordesOpciones();
        objLogger.log(Level.INFO, "Ventana de Pago iniciada para: " + objPostulante.getNombreCompleto());
    }
    
    public MetododoDePago() {
        this.objDAOPersistencia = new DAO();
        this.objPostulante = new Postulante(0, "debug@test.com", "Test", "User", "0000000", "01/01/2000");
        this.objRegistroDocumentacion = new RegistroDocumentacion(0, 0, "", true, "", true, "", "", "", "", "");
        
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        actualizarBordesOpciones();
        objLogger.log(Level.WARNING, "Ventana de Pago iniciada con Postulante de DEBUG.");
    }

    private void actualizarRegistroDocumentacion() {
        if (this.objPostulante != null) {
            int intIdUsuario = this.objPostulante.getIntIdUsuario();
            RegistroDocumentacion objNuevoRegistro = objDAOPersistencia.obtenerRegistroDocumentacionPorIdUsuario(intIdUsuario);

            if (objNuevoRegistro != null) {
                this.objRegistroDocumentacion = objNuevoRegistro;
                this.objPostulante.setObjRegistroDocumentacion(objNuevoRegistro);
                objLogger.log(Level.INFO, "Registro de documentacion actualizado desde la BD al entrar a Metodos de Pago.");
            }
        }
    }
    
    private void actualizarBordesOpciones() {
        javax.swing.border.Border bordeNoSeleccionado = BorderFactory.createLineBorder(new Color(0, 57, 117), 5);
        javax.swing.border.Border bordeSeleccionado = BorderFactory.createLineBorder(Color.WHITE, 5);
        
        Color colorFondoSeleccionado = Color.WHITE;
        Color colorFondoNoSeleccionado = new Color(0, 57, 117);

        
        if (this.iOpcionSeleccionada == 1) {
            botonBanco.setBorder(bordeSeleccionado);
            botonBanco.setBackground(colorFondoSeleccionado);
            
            botonQR.setBorder(bordeNoSeleccionado);
            botonQR.setBackground(colorFondoNoSeleccionado);
        } else if (this.iOpcionSeleccionada == 2) {
            botonQR.setBorder(bordeSeleccionado);
            botonQR.setBackground(colorFondoSeleccionado);
            
            botonBanco.setBorder(bordeNoSeleccionado);
            botonBanco.setBackground(colorFondoNoSeleccionado);
        } else {
            botonBanco.setBorder(bordeNoSeleccionado);
            botonBanco.setBackground(colorFondoNoSeleccionado);
            botonQR.setBorder(bordeNoSeleccionado);
            botonQR.setBackground(colorFondoNoSeleccionado);
        }
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonBanco = new javax.swing.JLabel();
        botonQR = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botonConfirmar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        Retroceder = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 815));

        jPanel1.setBackground(new java.awt.Color(0, 57, 117));
        jPanel1.setMaximumSize(new java.awt.Dimension(1600, 815));
        jPanel1.setMinimumSize(new java.awt.Dimension(1600, 815));
        jPanel1.setName("");
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 815));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonBanco.setBackground(new java.awt.Color(0, 57, 117));
        botonBanco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/metodos de pago/pagoBanco.png")));
        botonBanco.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonBanco.setOpaque(true);
        botonBanco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonBanco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonBancoMouseClicked(evt);
            }
        });
        jPanel1.add(botonBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 180, 180));

        botonQR.setBackground(new java.awt.Color(0, 57, 117));
        botonQR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/metodos de pago/pagoQR.png")));
        botonQR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonQR.setOpaque(true);
        botonQR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonQR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonQRMouseClicked(evt);
            }
        });
        jPanel1.add(botonQR, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 180, 180));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 60));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Metodos de Pago");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 580, 90));

        jPanel2.setBackground(new java.awt.Color(183, 13, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(1600, 815));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/metodos de pago/LogoUmssAzul-removebg-preview.png")));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 250));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/metodos de pago/hombre.png")));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, 120, 120));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("\nun metodo de pago");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, 190, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Por favor seleccione\n");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 200, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/metodos de pago/burrbuja (1).png")));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 240, 240));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 0, 380, 815));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 24));
        jLabel7.setForeground(new java.awt.Color(239, 184, 16));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Pago Online ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 510, 230, -1));

        botonConfirmar.setBackground(new java.awt.Color(183, 13, 30));
        botonConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonConfirmarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonConfirmarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonConfirmarMouseExited(evt);
            }
        });
        botonConfirmar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Confirmar");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonConfirmar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 40));

        jPanel1.add(botonConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 660, 230, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 0, 24));
        jLabel13.setForeground(new java.awt.Color(239, 184, 16));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Deposito Bancario");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 330, -1));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 550, 260, 20));

        jPanel4.setBackground(new java.awt.Color(239, 184, 16));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, -10, 20, 830));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 24));
        jLabel3.setForeground(new java.awt.Color(239, 184, 16));
        jLabel3.setText("Monto Total : 350 Bs");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 260, 60));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 1350, 30));

        jPanel3.setBackground(new java.awt.Color(239, 184, 16));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 820));

        Retroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/metodos de pago/Retroceder.png")));
        Retroceder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Retroceder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RetrocederMouseClicked(evt);
            }
        });
        jPanel1.add(Retroceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 100, 90));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, 350, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void botonBancoMouseClicked(java.awt.event.MouseEvent evt) {
        this.iOpcionSeleccionada = 1;
        actualizarBordesOpciones();
        objLogger.log(Level.INFO, "Opcion seleccionada: Deposito Bancario.");
    }

    private void botonQRMouseClicked(java.awt.event.MouseEvent evt) {
        this.iOpcionSeleccionada = 2;
        actualizarBordesOpciones();
        objLogger.log(Level.INFO, "Opcion seleccionada: Pago con QR.");
    }

    private void botonConfirmarMouseEntered(java.awt.event.MouseEvent evt) {
        botonConfirmar.setBackground(new Color(204, 14, 33));
    }

    private void botonConfirmarMouseExited(java.awt.event.MouseEvent evt) {
        botonConfirmar.setBackground(new Color(183,13,30));
    }

    private void botonConfirmarMouseClicked(java.awt.event.MouseEvent evt) {
        if (this.iOpcionSeleccionada == 2) {
            objLogger.log(Level.INFO, "Boton CONFIRMAR presionado. Abriendo Dialogo QR.");
            
            
            SwingUtilities.invokeLater(() -> {
                
                DialogoQrGenerado objDialogoQR = new DialogoQrGenerado(this, this.objPostulante, this.objRegistroDocumentacion);
                objDialogoQR.setVisible(true); 
            });
            
        } else if (this.iOpcionSeleccionada == 1) {
            objLogger.log(Level.INFO, "Boton CONFIRMAR presionado. Opcion Deposito Bancario. Mostrar detalles de deposito.");
            JOptionPane.showMessageDialog(this,
                "Se mostraran los detalles para realizar el deposito bancario.",
                "Deposito Bancario",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void RetrocederMouseClicked(java.awt.event.MouseEvent evt) {                                        
        objLogger.log(Level.INFO, "Retrocediendo al Menu Principal.");
        
        this.dispose();

        SwingUtilities.invokeLater(() -> {
            paqueteInterfacesPostulantes.Menu objVentanaMenu = 
                    new paqueteInterfacesPostulantes.Menu(this.objPostulante, this.objRegistroDocumentacion);
            
            objVentanaMenu.setVisible(true);
        });
    }
    
    private javax.swing.JLabel Retroceder;
    private javax.swing.JLabel botonBanco;
    private javax.swing.JPanel botonConfirmar;
    private javax.swing.JLabel botonQR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
}