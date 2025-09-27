package paqueteInterfacesPostulantes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Image;
import java.awt.Dimension;

import paqueteServicios.ServicioAutenticarPostulante;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paqueteUtilidades.ManejadorImagen; // Clase ManejadorImagen importada

public class InicioDeSesion extends javax.swing.JFrame {

    private static final Logger objLogger = Logger.getLogger(InicioDeSesion.class.getName());

    private static final String STR_PLACEHOLDER_CORREO = "Ingrese su correo electronico";
    private static final String STR_PLACEHOLDER_CONTRASENIA = "************";
    private static final Color COLOR_PLACEHOLDER = Color.GRAY;
    private static final Color COLOR_TEXTO = Color.BLACK;

    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JLabel lblTituloIniciarSesion;
    private javax.swing.JLabel lblLogoUmss;
    private javax.swing.JLabel lblImagenPaseo;
    private javax.swing.JLabel lblRegistrarse;
    private javax.swing.JLabel lblTextoNuevo;
    private javax.swing.JLabel lblTituloContrasenia;
    private javax.swing.JLabel lblTituloCorreo;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JLabel lblTextoIngresar;
    private javax.swing.JPanel pnlBotonIngresar;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    private javax.swing.JPanel pnlIzquierdo;
    private javax.swing.JPanel pnlDerecho;
    private javax.swing.JPanel pnlEnlaces;

    public InicioDeSesion() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setupPlaceholder(txtCorreo, STR_PLACEHOLDER_CORREO);
        setupPlaceholder(txtContrasenia, STR_PLACEHOLDER_CONTRASENIA);
        
        pnlBotonIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlBotonIngresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlBotonIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlBotonIngresarMouseExited(evt);
            }
        });
    }

    private void setupPlaceholder(JTextField txtCampo, String strTextoPlaceholder) {
        txtCampo.setText(strTextoPlaceholder);
        txtCampo.setForeground(COLOR_PLACEHOLDER);

        txtCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtCampo.getText().equals(strTextoPlaceholder)) {
                    txtCampo.setText("");
                    txtCampo.setForeground(COLOR_TEXTO);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtCampo.getText().isEmpty()) {
                    txtCampo.setText(strTextoPlaceholder);
                    txtCampo.setForeground(COLOR_PLACEHOLDER);
                }
            }
        });
    }

    private void setupPlaceholder(JPasswordField pwdCampo, String strTextoPlaceholder) {
        pwdCampo.setEchoChar((char) 0);
        pwdCampo.setText(strTextoPlaceholder);
        pwdCampo.setForeground(COLOR_PLACEHOLDER);

        pwdCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String strPass = new String(pwdCampo.getPassword());
                if (strPass.equals(strTextoPlaceholder)) {
                    pwdCampo.setText("");
                    pwdCampo.setForeground(COLOR_TEXTO);
                    pwdCampo.setEchoChar('*');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                String strPass = new String(pwdCampo.getPassword());
                if (strPass.isEmpty()) {
                    pwdCampo.setEchoChar((char) 0);
                    pwdCampo.setText(strTextoPlaceholder);
                    pwdCampo.setForeground(COLOR_PLACEHOLDER);
                }
            }
        });
    }
    
    private Postulante intentarIniciarSesion() {
        String strCorreo = txtCorreo.getText();
        String strContrasenia = new String(txtContrasenia.getPassword());
        
        if (strCorreo.equals(STR_PLACEHOLDER_CORREO) || strContrasenia.equals(STR_PLACEHOLDER_CONTRASENIA) || strCorreo.isEmpty() || strContrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese su correo y contraseña.", "Datos Incompletos", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        ServicioAutenticarPostulante objServicio = new ServicioAutenticarPostulante();
        Postulante objPostulante = objServicio.autenticarYObtenerDatos(strCorreo, strContrasenia);
        
        return objPostulante;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBg = new JPanel();
        pnlIzquierdo = new JPanel();
        lblImagenPaseo = new JLabel();
        pnlDerecho = new JPanel();
        lblLogoUmss = new JLabel();
        lblTituloIniciarSesion = new JLabel();
        lblTituloCorreo = new JLabel();
        txtCorreo = new JTextField();
        separador2 = new JSeparator();
        lblTituloContrasenia = new JLabel();
        txtContrasenia = new JPasswordField();
        separador1 = new JSeparator();
        pnlBotonIngresar = new JPanel();
        lblTextoIngresar = new JLabel();
        pnlEnlaces = new JPanel();
        lblTextoNuevo = new JLabel();
        lblRegistrarse = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        pnlBg.setBackground(new Color(240, 240, 240));
        pnlBg.setLayout(new GridBagLayout());

        pnlIzquierdo.setLayout(new BorderLayout());

        //logica para cargar imagen (lblImagenPaseo)
        String strRutaImagenPaseo = "/imagenes de interfaces/inicio de sesion/paseoUniversitario.jpg";
        int intAnchoDeseado = 1220;
        int intAltoDeseado = -1;
        
        ManejadorImagen.cargarImagenConManejoError(
            lblImagenPaseo, 
            strRutaImagenPaseo, 
            intAnchoDeseado, 
            intAltoDeseado
        );
        
        pnlIzquierdo.add(lblImagenPaseo, BorderLayout.CENTER);
        
        GridBagConstraints gbcImagen = new GridBagConstraints();
        gbcImagen.gridx = 0;
        gbcImagen.gridy = 0;
        gbcImagen.weightx = 0.5;
        gbcImagen.weighty = 1.0;
        gbcImagen.fill = GridBagConstraints.BOTH;
        pnlBg.add(pnlIzquierdo, gbcImagen);

        pnlDerecho.setLayout(new GridBagLayout());
        pnlDerecho.setBackground(Color.WHITE);
        GridBagConstraints gbcFormulario = new GridBagConstraints();
        gbcFormulario.insets = new Insets(10, 20, 10, 20);
        gbcFormulario.fill = GridBagConstraints.HORIZONTAL;
        gbcFormulario.anchor = GridBagConstraints.CENTER;

        //logica para cargar imagen (lblLogoUmss)
        String strRutaLogo = "/imagenes de interfaces/inicio de sesion/LogoUmss.png";
        
        ManejadorImagen.cargarImagenConManejoError(lblLogoUmss, strRutaLogo, -1, -1);
        
        gbcFormulario.gridx = 0;
        gbcFormulario.gridy = 0;
        gbcFormulario.gridwidth = 2;
        pnlDerecho.add(lblLogoUmss, gbcFormulario);

        lblTituloIniciarSesion.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
        lblTituloIniciarSesion.setHorizontalAlignment(JLabel.CENTER);
        lblTituloIniciarSesion.setText("INICIAR SESIÓN");
        gbcFormulario.gridy = 1;
        pnlDerecho.add(lblTituloIniciarSesion, gbcFormulario);

        lblTituloCorreo.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        lblTituloCorreo.setText("CORREO ELECTRONICO");
        gbcFormulario.gridy = 2;
        pnlDerecho.add(lblTituloCorreo, gbcFormulario);

        txtCorreo.setBorder(null);
        gbcFormulario.gridy = 3;
        pnlDerecho.add(txtCorreo, gbcFormulario);

        separador2.setForeground(new Color(0, 0, 0));
        gbcFormulario.gridy = 4;
        pnlDerecho.add(separador2, gbcFormulario);

        lblTituloContrasenia.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        lblTituloContrasenia.setText("CONTRASEÑA");
        gbcFormulario.gridy = 5;
        pnlDerecho.add(lblTituloContrasenia, gbcFormulario);

        txtContrasenia.setBorder(null);
        gbcFormulario.gridy = 6;
        pnlDerecho.add(txtContrasenia, gbcFormulario);

        separador1.setForeground(new Color(0, 0, 0));
        gbcFormulario.gridy = 7;
        pnlDerecho.add(separador1, gbcFormulario);

        pnlBotonIngresar.setBackground(new Color(196, 95, 95));
        pnlBotonIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblTextoIngresar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        lblTextoIngresar.setForeground(Color.WHITE);
        lblTextoIngresar.setText("Ingresar");
        pnlBotonIngresar.add(lblTextoIngresar);
        gbcFormulario.gridy = 8;
        pnlDerecho.add(pnlBotonIngresar, gbcFormulario);

        pnlEnlaces.setBackground(Color.WHITE);
        lblTextoNuevo.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        lblTextoNuevo.setText("¿Postulante nuevo?");
        lblRegistrarse.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
        lblRegistrarse.setForeground(new Color(196, 95, 95));
        lblRegistrarse.setText("Registrarse");
        
        lblRegistrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarseMouseClicked(evt);
            }
        });
        
        pnlEnlaces.add(lblTextoNuevo);
        pnlEnlaces.add(lblRegistrarse);
        gbcFormulario.gridy = 9;
        pnlDerecho.add(pnlEnlaces, gbcFormulario);

        GridBagConstraints gbcFormularioPanel = new GridBagConstraints();
        gbcFormularioPanel.gridx = 1;
        gbcFormularioPanel.gridy = 0;
        gbcFormularioPanel.weightx = 0.5;
        gbcFormularioPanel.weighty = 1.0;
        gbcFormularioPanel.fill = GridBagConstraints.BOTH;
        pnlBg.add(pnlDerecho, gbcFormularioPanel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pnlBg, BorderLayout.CENTER);
        
        pack();
    }

    private void pnlBotonIngresarMouseEntered(java.awt.event.MouseEvent evt) {
        pnlBotonIngresar.setBackground(new Color(214, 103, 103));
    }

    private void pnlBotonIngresarMouseExited(java.awt.event.MouseEvent evt) {
        pnlBotonIngresar.setBackground(new Color(196, 95, 95));
    }

    private void lblRegistrarseMouseClicked(java.awt.event.MouseEvent evt) {
        registroUsuario objRegistroUsuario = new registroUsuario();
        objRegistroUsuario.setVisible(true);
        this.dispose();
    }
    
    private void pnlBotonIngresarMouseClicked(java.awt.event.MouseEvent evt) {
        
        Postulante objPostulante = intentarIniciarSesion();
        
        if (objPostulante != null) {
            
            RegistroDocumentacion objRegistroDoc = objPostulante.getObjRegistroDocumentacion(); 

            if (objRegistroDoc == null) {
                JOptionPane.showMessageDialog(this, "Autenticación exitosa. Sin embargo, no se encontró documentación registrada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            
            Menu objMenu = new Menu(objPostulante, objRegistroDoc);
            
            objMenu.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas o error en el sistema.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
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
        java.awt.EventQueue.invokeLater(() -> new InicioDeSesion().setVisible(true));
    }
}