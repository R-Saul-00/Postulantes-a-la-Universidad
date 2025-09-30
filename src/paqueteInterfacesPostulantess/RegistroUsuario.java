package paqueteInterfacesPostulantess;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import paqueteServicios.ServicioRegistrarPostulante;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO;

public class RegistroUsuario extends javax.swing.JFrame {
    
    private final Color colorPlaceholder = new Color(204, 204, 204);
    private final Color colorTexto = Color.BLACK;
    private final String strPlaceholderNombre = "Ingrese su nombre completo";
    private final String strPlaceholderApellidos = "Ingrese apellido Paterno y Materno";
    private final String strPlaceholderFecha = "DD/MM/AA";
    private final String strPlaceholderCarnet = "Ingrese su número de carnet";
    private final String strPlaceholderCorreo = "Ingrese su correo electrónico";
    private final String strPlaceholderContrasena = "Ingrese una contraseña";
    
    private char chrEchoCharOriginal;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroUsuario.class.getName());

    public RegistroUsuario() {
        initComponents();
        this.chrEchoCharOriginal = ingresarContrasena.getEchoChar();
        
        agregarPlaceholder(ingresarNombre, strPlaceholderNombre);
        agregarPlaceholder(ingresarApellidos, strPlaceholderApellidos);
        agregarPlaceholder(ingresarFechaDeNacimiento, strPlaceholderFecha);
        agregarPlaceholder(ingresarCI, strPlaceholderCarnet);
        agregarPlaceholder(ingresarCorreo, strPlaceholderCorreo);
        agregarPlaceholderContrasena(ingresarContrasena, strPlaceholderContrasena);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            jPanel1.requestFocusInWindow();
        });
    }
    
    private Postulante obtenerYRegistrarDatos() {
        ServicioRegistrarPostulante objServicio = new ServicioRegistrarPostulante();
        
        String strNombre = ingresarNombre.getText().equals(strPlaceholderNombre) ? "" : ingresarNombre.getText();
        String strApellidos = ingresarApellidos.getText().equals(strPlaceholderApellidos) ? "" : ingresarApellidos.getText();
        String strFechaNacimiento = ingresarFechaDeNacimiento.getText().equals(strPlaceholderFecha) ? "" : ingresarFechaDeNacimiento.getText();
        String strCorreo = ingresarCorreo.getText().equals(strPlaceholderCorreo) ? "" : ingresarCorreo.getText();
        String strCI = ingresarCI.getText().equals(strPlaceholderCarnet) ? "" : ingresarCI.getText();
        
        String strContrasena = new String(ingresarContrasena.getPassword());
        if (strContrasena.equals(strPlaceholderContrasena)) strContrasena = "";
        
        Postulante objPostulante = objServicio.registrarPostulante(strNombre, strApellidos, strCorreo, strContrasena, strCI, strFechaNacimiento);
        
        return objPostulante;
    }
    
    private void pnlRegistrarMouseClicked(java.awt.event.MouseEvent evt) {                                         
        Postulante objPostulante = obtenerYRegistrarDatos();
        
        if (objPostulante != null) {
            
            DAO objDEAPrueba = new DAO();
            
            RegistroDocumentacion objDocumentacion = objDEAPrueba.obtenerRegistroDocumentacionPorIdUsuario(objPostulante.getIntIdUsuario());
            
            JOptionPane.showMessageDialog(this, 
                    "Registro exitoso. Bienvenido: " + objPostulante.getStrNombre(), 
                    "Registro Completo", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            Menu objMenu = new Menu(objPostulante, objDocumentacion); 
            objMenu.setVisible(true);
            this.dispose(); 
            
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el postulante. Verifique los datos ingresados.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void botonIniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {                                                    
        this.dispose(); 
        InicioDeSesion objInicioSesion = new InicioDeSesion();
        objInicioSesion.setVisible(true);
    } 

    private void agregarPlaceholder(JTextField campo, String placeholder) {
        campo.setText(placeholder);
        campo.setForeground(colorPlaceholder);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(colorTexto);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(colorPlaceholder);
                }
            }
        });
    }
    
    private void agregarPlaceholderContrasena(JPasswordField campo, String placeholder) {
        
        campo.setText(placeholder);
        campo.setEchoChar((char) 0); 
        campo.setForeground(colorPlaceholder);

        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                String strTexto = new String(campo.getPassword());
                if (strTexto.equals(placeholder)) {
                    campo.setText("");
                    campo.setEchoChar(chrEchoCharOriginal); 
                    campo.setForeground(colorTexto);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                String strTexto = new String(campo.getPassword());
                if (strTexto.isEmpty()) {
                    campo.setText(placeholder);
                    campo.setEchoChar((char) 0); 
                    campo.setForeground(colorPlaceholder);
                }
            }
        });
    }
    
    private void pnlRegistrarMouseEntered(java.awt.event.MouseEvent evt) {                                          
       pnlRegistrar.setBackground(new Color(204, 14, 33)); 
    }                                         

    private void pnlRegistrarMouseExited(java.awt.event.MouseEvent evt) {                                         
        pnlRegistrar.setBackground(new Color(183, 13, 30)); 
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ingresarContraseña = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        ingresarNombre = new javax.swing.JTextField();
        ingresarFechaDeNacimiento = new javax.swing.JTextField();
        ingresarCI = new javax.swing.JTextField();
        ingresarCorreo = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        ingresarApellidos = new javax.swing.JTextField();
        ingresarContrasena = new javax.swing.JPasswordField();
        pnlRegistrar = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        botonIniciarSesion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1600, 780));
        jPanel1.setName(""); 
        jPanel1.setPreferredSize(new java.awt.Dimension(1600, 780));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 57, 117));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes de interfaces/Registro/LogoUmssAzul.png"))); 
        jLabel2.setText("jLabel2");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 350, 370));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 1060));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 52)); 
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Registro De Postulante");
        jLabel4.setToolTipText("");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 720, 110));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 1200, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); 
        jLabel5.setText("Informacion Personal :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 530, 80));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 520, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); 
        jLabel6.setText("NOMBRE");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, 130, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); 
        jLabel7.setText("APELLIDOS");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); 
        jLabel8.setText("NÚMERO DE CARNET");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 280, -1, -1));

        ingresarContraseña.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); 
        ingresarContraseña.setText("CONTRASEÑA");
        jPanel1.add(ingresarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 500, 260, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); 
        jLabel10.setText("CORREO ELECTRONICO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 380, 430, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); 
        jLabel11.setText("FECHA DE NACIMIENTO");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 570, 320, 10));

        ingresarNombre.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); 
        ingresarNombre.setForeground(new java.awt.Color(204, 204, 204));
        ingresarNombre.setText("Ingrese su nombre completo");
        ingresarNombre.setBorder(null);
        ingresarNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingresarNombreMouseClicked(evt);
            }
        });
        jPanel1.add(ingresarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 460, 40));

        ingresarFechaDeNacimiento.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); 
        ingresarFechaDeNacimiento.setForeground(new java.awt.Color(204, 204, 204));
        ingresarFechaDeNacimiento.setText("Ingrese su nombre completo");
        ingresarFechaDeNacimiento.setBorder(null);
        jPanel1.add(ingresarFechaDeNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 460, 40));

        ingresarCI.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); 
        ingresarCI.setForeground(new java.awt.Color(204, 204, 204));
        ingresarCI.setText("Ingrese su nombre completo");
        ingresarCI.setBorder(null);
        jPanel1.add(ingresarCI, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 310, 460, 40));

        ingresarCorreo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); 
        ingresarCorreo.setForeground(new java.awt.Color(204, 204, 204));
        ingresarCorreo.setText("Ingrese su nombre completo");
        ingresarCorreo.setBorder(null);
        jPanel1.add(ingresarCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 410, 460, 40));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 350, 330, 10));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, 500, 10));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 570, 500, 10));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 450, 320, 10));

        ingresarApellidos.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); 
        ingresarApellidos.setForeground(new java.awt.Color(204, 204, 204));
        ingresarApellidos.setText("Ingrese su nombre completo");
        ingresarApellidos.setBorder(null);
        jPanel1.add(ingresarApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 420, 460, 40));

        ingresarContrasena.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); 
        ingresarContrasena.setForeground(new java.awt.Color(204, 204, 204));
        ingresarContrasena.setText("jPasswordField1");
        ingresarContrasena.setBorder(null);
        ingresarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarContrasenaActionPerformed(evt);
            }
        });
        jPanel1.add(ingresarContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 530, -1, -1));

        pnlRegistrar.setBackground(new java.awt.Color(183, 13, 30));
        pnlRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlRegistrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlRegistrarMouseExited(evt);
            }
        });
        pnlRegistrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); 
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Registrar");
        pnlRegistrar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 40));

        jPanel1.add(pnlRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 630, 130, 40));

        jPanel3.setBackground(new java.awt.Color(183, 13, 30));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 710, 960, 25));

        jPanel4.setBackground(new java.awt.Color(183, 13, 30));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 750, 1200, 25));

        jSeparator9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, 500, 10));

        botonIniciarSesion.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); 
        botonIniciarSesion.setForeground(new java.awt.Color(0, 76, 156));
        botonIniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonIniciarSesion.setText("Iniciar Sesión");
        botonIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIniciarSesionMouseClicked(evt);
            }
        });
        jPanel1.add(botonIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 670, 120, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); 
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("¿Ya te registraste?");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 670, 170, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }                        

    private void ingresarNombreMouseClicked(java.awt.event.MouseEvent evt) {                                            
    }                                           

    private void ingresarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        pnlRegistrarMouseClicked(null);
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
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new RegistroUsuario().setVisible(true));
    }

    private javax.swing.JLabel botonIniciarSesion;
    private javax.swing.JTextField ingresarApellidos;
    private javax.swing.JTextField ingresarCI;
    private javax.swing.JPasswordField ingresarContrasena;
    private javax.swing.JLabel ingresarContraseña;
    private javax.swing.JTextField ingresarCorreo;
    private javax.swing.JTextField ingresarFechaDeNacimiento;
    private javax.swing.JTextField ingresarNombre;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel pnlRegistrar;
}