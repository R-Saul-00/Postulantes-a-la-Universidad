package paqueteInterfacesPostulantes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import paqueteServicios.ServicioRegistrarPostulante;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO; 

public class registroUsuario extends javax.swing.JFrame {

    private static final Logger objLogger = Logger.getLogger(registroUsuario.class.getName());

    private final Color colorPlaceholder = new Color(153, 153, 153);
    private final Color colorTexto = Color.BLACK;
    private final String strPlaceholderNombre = "Ingrese su nombre completo";
    private final String strPlaceholderApellidos = "Ingrese Apellido Paterno y Materno";
    private final String strPlaceholderFecha = "Ingrese su fecha de nacimiento dd/mm/aa";
    private final String strPlaceholderCarnet = "Ingrese su numero de carnet";
    private final String strPlaceholderCorreo = "Ingrese correo electronico";
    private final String strPlaceholderContrasenia = "**************";

    public registroUsuario() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setupPlaceholders();
    }
    
    private void setupPlaceholders() {
        setupPlaceholder(txtNombre, strPlaceholderNombre);
        setupPlaceholder(txtApellidos, strPlaceholderApellidos);
        setupPlaceholder(txtFechaNacimiento, strPlaceholderFecha);
        setupPlaceholder(txtNumeroCarnet, strPlaceholderCarnet);
        setupPlaceholder(txtCorreoElectronico, strPlaceholderCorreo);
        setupPlaceholder(pwdContrasenia, strPlaceholderContrasenia);
    }
    
    private void setupPlaceholder(JTextField txtCampo, String strTextoPlaceholder) {
        txtCampo.setText(strTextoPlaceholder);
        txtCampo.setForeground(colorPlaceholder);

        txtCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtCampo.getText().equals(strTextoPlaceholder)) {
                    txtCampo.setText("");
                    txtCampo.setForeground(colorTexto);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtCampo.getText().isEmpty()) {
                    txtCampo.setText(strTextoPlaceholder);
                    txtCampo.setForeground(colorPlaceholder);
                }
            }
        });
    }
    
    private void setupPlaceholder(JPasswordField pwdCampo, String strTextoPlaceholder) {
        pwdCampo.setEchoChar((char) 0);
        pwdCampo.setText(strTextoPlaceholder);
        pwdCampo.setForeground(colorPlaceholder);

        pwdCampo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String strPass = new String(pwdCampo.getPassword());
                if (strPass.equals(strTextoPlaceholder)) {
                    pwdCampo.setText("");
                    pwdCampo.setForeground(colorTexto);
                    pwdCampo.setEchoChar('*');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                String strPass = new String(pwdCampo.getPassword());
                if (strPass.isEmpty()) {
                    pwdCampo.setEchoChar((char) 0);
                    pwdCampo.setText(strTextoPlaceholder);
                    pwdCampo.setForeground(colorPlaceholder);
                }
            }
        });
    }

    private Postulante registrarDatos() {
        ServicioRegistrarPostulante objServicio = new ServicioRegistrarPostulante();
        
        String strNombre = txtNombre.getText().equals(strPlaceholderNombre) ? "" : txtNombre.getText();
        String strApellidos = txtApellidos.getText().equals(strPlaceholderApellidos) ? "" : txtApellidos.getText();
        String strCorreo = txtCorreoElectronico.getText().equals(strPlaceholderCorreo) ? "" : txtCorreoElectronico.getText();
        String strCI = txtNumeroCarnet.getText().equals(strPlaceholderCarnet) ? "" : txtNumeroCarnet.getText();
        String strFechaNacimiento = txtFechaNacimiento.getText().equals(strPlaceholderFecha) ? "" : txtFechaNacimiento.getText();
        
        String strContrasenia = new String(pwdContrasenia.getPassword());
        if (strContrasenia.equals(strPlaceholderContrasenia)) strContrasenia = "";

        Postulante objPostulante = objServicio.registrarPostulante(strNombre, strApellidos, strCorreo, strContrasenia, strCI, strFechaNacimiento);
        
        return objPostulante;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlFondo = new JPanel();
        lblTitulo = new JLabel("REGISTRO DE POSTULANTE");
        lblSubtituloInfoPersonal = new JLabel("Informacion Personal");
        lblNombre = new JLabel("NOMBRE:");
        txtNombre = new JTextField();
        lblApellidos = new JLabel("APELLIDOS:");
        txtApellidos = new JTextField();
        lblFechaNacimiento = new JLabel("FECHA DE NACIMIENTO:");
        txtFechaNacimiento = new JTextField();
        lblCarnet = new JLabel("NUMERO DE CARNET:");
        txtNumeroCarnet = new JTextField();
        lblCorreo = new JLabel("CORREO:");
        txtCorreoElectronico = new JTextField();
        lblContrasenia = new JLabel("CONTRASEÑA:");
        pwdContrasenia = new JPasswordField();
        botonGuardar = new JButton("Guardar");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pnlFondo.setLayout(new GridBagLayout());
        pnlFondo.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        pnlFondo.add(new JPanel(), gbc);
        gbc.weighty = 0;

        lblTitulo.setFont(new Font("Eras Bold ITC", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        pnlFondo.add(lblTitulo, gbc);

        lblSubtituloInfoPersonal.setFont(new Font("Eras Bold ITC", Font.BOLD, 18));
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        pnlFondo.add(lblSubtituloInfoPersonal, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        
        lblNombre.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pnlFondo.add(lblNombre, gbc);
        
        txtNombre.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlFondo.add(txtNombre, gbc);

        lblApellidos.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        pnlFondo.add(lblApellidos, gbc);
        
        txtApellidos.setPreferredSize(new Dimension(370, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlFondo.add(txtApellidos, gbc);

        lblFechaNacimiento.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        pnlFondo.add(lblFechaNacimiento, gbc);
        
        txtFechaNacimiento.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlFondo.add(txtFechaNacimiento, gbc);

        lblCarnet.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        pnlFondo.add(lblCarnet, gbc);
        
        txtNumeroCarnet.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlFondo.add(txtNumeroCarnet, gbc);

        lblCorreo.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        pnlFondo.add(lblCorreo, gbc);
        
        txtCorreoElectronico.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlFondo.add(txtCorreoElectronico, gbc);

        lblContrasenia.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        pnlFondo.add(lblContrasenia, gbc);
        
        pwdContrasenia.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlFondo.add(pwdContrasenia, gbc);

        botonGuardar.setText("Guardar");
        botonGuardar.setPreferredSize(new Dimension(150, 30));
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        pnlFondo.add(botonGuardar, gbc);
        
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.weighty = 1.0;
        pnlFondo.add(new JPanel(), gbc);

        getContentPane().add(pnlFondo);
        pack();
    }
    
    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        Postulante objPostulante = registrarDatos();
        
        if (objPostulante != null) {
            
            DAO objDEAPrueba = new DAO();
            
            RegistroDocumentacion objDocumentacion = objDEAPrueba.obtenerRegistroDocumentacionPorIdUsuario(objPostulante.getIntIdUsuario());
            
            if (objDocumentacion != null) {
                 JOptionPane.showMessageDialog(this, 
                    "Registro exitoso. Bienvenido: " + objPostulante.getStrNombre() + "\nDocumentación inicial obtenida del DAO.", 
                    "Registro Completo", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                Menu objMenu = new Menu(objPostulante, objDocumentacion); 
                objMenu.setVisible(true);
                this.dispose();
            } else {
                 JOptionPane.showMessageDialog(this, 
                    "Advertencia: El usuario fue creado, pero falló al recuperar el registro de documentación inicial directamente del DAO. Contacte a soporte.", 
                    "Error Parcial", 
                    JOptionPane.WARNING_MESSAGE);
                
                 Menu objMenu = new Menu(objPostulante, null); 
                 objMenu.setVisible(true);
                 this.dispose();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el postulante. Verifique los datos ingresados.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void txtCorreoElectronicoActionPerformed(java.awt.event.ActionEvent evt) {
        pwdContrasenia.requestFocus();
    }

    private void txtFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {
        txtNumeroCarnet.requestFocus();
    }

    private void txtNumeroCarnetActionPerformed(java.awt.event.ActionEvent evt) {
        txtCorreoElectronico.requestFocus();
    }

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {
        txtFechaNacimiento.requestFocus();
    }

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {
        txtApellidos.requestFocus();
    }

    private void pwdContraseniaActionPerformed(java.awt.event.ActionEvent evt) {
        botonGuardar.requestFocus();
    }

    public static void main(String args[]) {
        // Código main eliminado por ser de prueba.
    }

    private javax.swing.JPanel pnlFondo;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblSubtituloInfoPersonal;
    private javax.swing.JPasswordField pwdContrasenia;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblCarnet;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroCarnet;
}