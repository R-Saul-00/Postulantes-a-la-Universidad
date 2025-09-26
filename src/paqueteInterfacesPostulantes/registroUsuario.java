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
import paqueteServicios.ServicioRegistrarPostulante;
import paqueteServicios.Postulante;

public class registroUsuario extends javax.swing.JFrame {

    private static final Logger objLogger = Logger.getLogger(registroUsuario.class.getName());

    private final Color COLOR_PLACEHOLDER = new Color(153, 153, 153);
    private final Color COLOR_TEXTO = Color.BLACK;
    private final String STR_PLACEHOLDER_NOMBRE = "Ingrese su nombre completo";
    private final String STR_PLACEHOLDER_APELLIDOS = "Ingrese Apellido Paterno y Materno";
    private final String STR_PLACEHOLDER_FECHA = "Ingrese su fecha de nacimiento dd/mm/aa";
    private final String STR_PLACEHOLDER_CARNET = "Ingrese su numero de carnet";
    private final String STR_PLACEHOLDER_CORREO = "Ingrese correo electronico";
    private final String STR_PLACEHOLDER_CONTRASENIA = "**************";

    public registroUsuario() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setupPlaceholders();
    }
    
    private void setupPlaceholders() {
        setupPlaceholder(txtNombre, STR_PLACEHOLDER_NOMBRE);
        setupPlaceholder(txtApellidos, STR_PLACEHOLDER_APELLIDOS);
        setupPlaceholder(txtFechaNacimiento, STR_PLACEHOLDER_FECHA);
        setupPlaceholder(txtNumeroCarnet, STR_PLACEHOLDER_CARNET);
        setupPlaceholder(txtCorreoElectronico, STR_PLACEHOLDER_CORREO);
        setupPlaceholder(pwdContrasenia, STR_PLACEHOLDER_CONTRASENIA);
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

    private Postulante registrarDatos() {
        ServicioRegistrarPostulante objServicio = new ServicioRegistrarPostulante();
        
        String strNombre = txtNombre.getText().equals(STR_PLACEHOLDER_NOMBRE) ? "" : txtNombre.getText();
        String strApellidos = txtApellidos.getText().equals(STR_PLACEHOLDER_APELLIDOS) ? "" : txtApellidos.getText();
        String strCorreo = txtCorreoElectronico.getText().equals(STR_PLACEHOLDER_CORREO) ? "" : txtCorreoElectronico.getText();
        String strCI = txtNumeroCarnet.getText().equals(STR_PLACEHOLDER_CARNET) ? "" : txtNumeroCarnet.getText();
        String strFechaNacimiento = txtFechaNacimiento.getText().equals(STR_PLACEHOLDER_FECHA) ? "" : txtFechaNacimiento.getText();
        
        String strContrasenia = new String(pwdContrasenia.getPassword());
        if (strContrasenia.equals(STR_PLACEHOLDER_CONTRASENIA)) strContrasenia = "";

        Postulante objPostulante = objServicio.registrarPostulante(strNombre, strApellidos, strCorreo, strContrasenia, strCI, strFechaNacimiento);
        
        return objPostulante;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBackground = new JPanel();
        lblTitulo = new JLabel("REGISTRO DE POSTULANTE");
        lblSubtituloInfoPersonal = new JLabel("Informacion Personal");
        lblSubtituloNombre = new JLabel("NOMBRE:");
        txtNombre = new JTextField();
        lblSubtituloApellidos = new JLabel("APELLIDOS:");
        txtApellidos = new JTextField();
        lblSubtituloFechaNacimiento = new JLabel("FECHA DE NACIMIENTO:");
        txtFechaNacimiento = new JTextField();
        lblSubtituloCarnet = new JLabel("NUMERO DE CARNET:");
        txtNumeroCarnet = new JTextField();
        lblSubtituloCorreo = new JLabel("CORREO:");
        txtCorreoElectronico = new JTextField();
        lblSubtituloContrasenia = new JLabel("CONTRASEÑA:");
        pwdContrasenia = new JPasswordField();
        btnGuardar = new JButton("Guardar");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pnlBackground.setLayout(new GridBagLayout());
        pnlBackground.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        pnlBackground.add(new JPanel(), gbc);
        gbc.weighty = 0;

        lblTitulo.setFont(new Font("Eras Bold ITC", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        pnlBackground.add(lblTitulo, gbc);

        lblSubtituloInfoPersonal.setFont(new Font("Eras Bold ITC", Font.BOLD, 18));
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        pnlBackground.add(lblSubtituloInfoPersonal, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        
        lblSubtituloNombre.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pnlBackground.add(lblSubtituloNombre, gbc);
        
        txtNombre.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlBackground.add(txtNombre, gbc);

        lblSubtituloApellidos.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        pnlBackground.add(lblSubtituloApellidos, gbc);
        
        txtApellidos.setPreferredSize(new Dimension(370, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlBackground.add(txtApellidos, gbc);

        lblSubtituloFechaNacimiento.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        pnlBackground.add(lblSubtituloFechaNacimiento, gbc);
        
        txtFechaNacimiento.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlBackground.add(txtFechaNacimiento, gbc);

        lblSubtituloCarnet.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        pnlBackground.add(lblSubtituloCarnet, gbc);
        
        txtNumeroCarnet.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlBackground.add(txtNumeroCarnet, gbc);

        lblSubtituloCorreo.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        pnlBackground.add(lblSubtituloCorreo, gbc);
        
        txtCorreoElectronico.setPreferredSize(new Dimension(240, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlBackground.add(txtCorreoElectronico, gbc);

        lblSubtituloContrasenia.setFont(new Font("Eras Demi ITC", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        pnlBackground.add(lblSubtituloContrasenia, gbc);
        
        pwdContrasenia.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pnlBackground.add(pwdContrasenia, gbc);

        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new Dimension(150, 30));
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        pnlBackground.add(btnGuardar, gbc);
        
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.weighty = 1.0;
        pnlBackground.add(new JPanel(), gbc);

        getContentPane().add(pnlBackground);
        pack();
    }
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        Postulante objPostulante = registrarDatos();
        
        if (objPostulante != null) {
            Menu objMenu = new Menu(objPostulante);
            objMenu.setVisible(true);
            this.dispose();
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
        btnGuardar.requestFocus();
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
        java.awt.EventQueue.invokeLater(() -> new registroUsuario().setVisible(true));
    }

    private javax.swing.JPanel pnlBackground;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblSubtituloInfoPersonal;
    private javax.swing.JPasswordField pwdContrasenia;
    private javax.swing.JLabel lblSubtituloContrasenia;
    private javax.swing.JLabel lblSubtituloApellidos;
    private javax.swing.JLabel lblSubtituloNombre;
    private javax.swing.JLabel lblSubtituloCarnet;
    private javax.swing.JLabel lblSubtituloCorreo;
    private javax.swing.JLabel lblSubtituloFechaNacimiento;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroCarnet;
}