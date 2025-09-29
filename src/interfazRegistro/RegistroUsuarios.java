package interfazRegistro;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
// IMPORTS NECESARIOS PARA LA BASE DE DATOS
import paquetePersistencia.DAO;
import paqueteServicios.Postulante;

public class RegistroUsuarios extends javax.swing.JFrame {
    
    // Paleta de colores uniforme
    private static final java.awt.Color PRIMARY_BLUE = new java.awt.Color(0, 51, 102);
    private static final java.awt.Color ACCENT_RED = new java.awt.Color(204, 0, 0);
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(153, 204, 255);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color LIGHT_GRAY = new java.awt.Color(245, 250, 255);
    private static final java.awt.Color PLACEHOLDER_COLOR = new java.awt.Color(153, 153, 153);
    private static final java.awt.Color BORDER_GRAY = new java.awt.Color(200, 200, 200);
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroUsuarios.class.getName());
    
    // Instancia del DAO para acceso a la base de datos
    private DAO objDAO;
    
    public RegistroUsuarios() {
        objDAO = new DAO(); // Inicializar el DAO
        initComponents();
        setupPlaceholders();
        improveDesign();
        pack();
        setupFullScreen();
    }
    
    private void setupFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    private void improveDesign() {
        improveTextFields();
        improveLabels();
        improveButtons();
        applyColorPalette();
    }
    
    private void applyColorPalette() {
        // Fondo principal con la nueva paleta
        background.setBackground(LIGHT_GRAY);
        
        // Encontrar y actualizar el panel principal
        Component[] components = background.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel centerWrapper = (JPanel) comp;
                centerWrapper.setBackground(LIGHT_GRAY);
                
                // Buscar el panel interno
                Component[] innerComponents = centerWrapper.getComponents();
                for (Component innerComp : innerComponents) {
                    if (innerComp instanceof JPanel) {
                        JPanel mainPanel = (JPanel) innerComp;
                        mainPanel.setBackground(WHITE);
                        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(PRIMARY_BLUE, 3),
                            new EmptyBorder(50, 80, 50, 80)
                        ));
                    }
                }
            }
        }
    }
    
    private void improveTextFields() {
        JTextField[] fields = {userlabel1, userlabel3, userlabel4, userlabel5, userlabel6, userlabel7};
        
        for (JTextField field : fields) {
            field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_GRAY, 2),
                new EmptyBorder(12, 16, 12, 16)
            ));
            field.setPreferredSize(new Dimension(400, 45));
            field.setMaximumSize(new Dimension(400, 45));
            field.setBackground(WHITE);
            
            // Efecto hover
            field.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (!field.hasFocus()) {
                        field.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(PRIMARY_BLUE, 2),
                            new EmptyBorder(12, 16, 12, 16)
                        ));
                    }
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (!field.hasFocus()) {
                        field.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(BORDER_GRAY, 2),
                            new EmptyBorder(12, 16, 12, 16)
                        ));
                    }
                }
            });
        }
    }
    
    private void improveLabels() {
        JLabel[] labels = {subtitulo1, subtitulo4, subtitulo5, subtitulo6, subtitulo7, subtitulo8};
        
        for (JLabel label : labels) {
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
            label.setForeground(PRIMARY_BLUE);
        }
        
        // Títulos principales
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(PRIMARY_BLUE);
        
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jLabel4.setForeground(ACCENT_RED);
    }
    
    private void improveButtons() {
        // Botón Siguiente (principal)
        setupButton(jButton1, PRIMARY_BLUE, WHITE);
        
        // Botón Atrás (secundario)
        setupButton(jButtonAtras, ACCENT_RED, WHITE);
    }
    
    private void setupButton(JButton button, Color bgColor, Color textColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setPreferredSize(new Dimension(140, 55));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
    
    private void setupPlaceholders() {
        setupPlaceholder(userlabel1, "Ingrese su nombre completo");
        setupPlaceholder(userlabel4, "Apellidos");
        setupPlaceholder(userlabel3, "Ingrese su fecha de nacimiento dd/mm/aa"); 
        setupPlaceholder(userlabel5, "Ingrese su numero de carnet");
        setupPlaceholder(userlabel7, "Ingrese correo electronico"); 
        setupPlaceholder(userlabel6, "Ingrese su telefono");
    }
    
    private void setupPlaceholder(JTextField field, String placeholderText) {
        field.setText(placeholderText);
        field.setForeground(PLACEHOLDER_COLOR);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholderText)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_BLUE, 2),
                    new EmptyBorder(12, 16, 12, 16)
                ));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholderText);
                    field.setForeground(PLACEHOLDER_COLOR);
                }
                field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_GRAY, 2),
                    new EmptyBorder(12, 16, 12, 16)
                ));
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Crear todos los componentes
        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        subtitulo5 = new javax.swing.JLabel();
        userlabel1 = new javax.swing.JTextField();
        subtitulo4 = new javax.swing.JLabel();
        userlabel4 = new javax.swing.JTextField();
        subtitulo8 = new javax.swing.JLabel();
        userlabel3 = new javax.swing.JTextField();
        subtitulo6 = new javax.swing.JLabel();
        userlabel5 = new javax.swing.JTextField();
        subtitulo7 = new javax.swing.JLabel();
        userlabel7 = new javax.swing.JTextField();
        subtitulo1 = new javax.swing.JLabel();
        userlabel6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButtonAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO DE POSTULANTE - Sistema Universitario");

        // Layout simplificado
        background.setLayout(new BorderLayout());
        background.setBackground(LIGHT_GRAY);

        // Panel principal del formulario
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_BLUE, 3),
            new EmptyBorder(50, 80, 50, 80)
        ));

        // Título principal
        jLabel1.setText("REGISTRO DE POSTULANTE");
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(jLabel1);
        
        mainPanel.add(Box.createVerticalStrut(25));

        // Subtítulo
        jLabel4.setText("Información Personal");
        jLabel4.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(jLabel4);
        
        mainPanel.add(Box.createVerticalStrut(40));

        // Campos del formulario
        addFormField(mainPanel, subtitulo5, userlabel1, "NOMBRE:");
        addFormField(mainPanel, subtitulo4, userlabel4, "APELLIDOS:");
        addFormField(mainPanel, subtitulo8, userlabel3, "FECHA DE NACIMIENTO:");
        addFormField(mainPanel, subtitulo6, userlabel5, "NÚMERO DE CARNET:");
        addFormField(mainPanel, subtitulo7, userlabel7, "CORREO:");
        addFormField(mainPanel, subtitulo1, userlabel6, "CELULAR:");

        mainPanel.add(Box.createVerticalStrut(50));

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        
        jButtonAtras.setText("Atrás");
        jButtonAtras.addActionListener(evt -> jButtonAtrasActionPerformed(evt));
        buttonPanel.add(jButtonAtras);
        
        jButton1.setText("Siguiente");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));
        buttonPanel.add(jButton1);
        
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(buttonPanel);

        // Panel wrapper para centrar
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(mainPanel, new GridBagConstraints());

        background.add(centerWrapper, BorderLayout.CENTER);

        // Configurar navegación
        setupNavigationListeners();

        // Layout del JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(background, BorderLayout.CENTER);
    }

    private void addFormField(JPanel parent, JLabel label, JTextField field, String labelText) {
        label.setText(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(label);
        
        parent.add(Box.createVerticalStrut(8));
        
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(field);
        
        parent.add(Box.createVerticalStrut(20));
    }
    
    private void setupNavigationListeners() {
        userlabel1.addActionListener(evt -> userlabel4.requestFocus());
        userlabel4.addActionListener(evt -> userlabel3.requestFocus());
        userlabel3.addActionListener(evt -> userlabel5.requestFocus());
        userlabel5.addActionListener(evt -> userlabel7.requestFocus());
        userlabel7.addActionListener(evt -> userlabel6.requestFocus());
        userlabel6.addActionListener(evt -> jButton1.requestFocus());
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (validateFields()) {
            // Obtener los datos del formulario
            String strNombre = userlabel1.getText().trim();
            String strApellidos = userlabel4.getText().trim();
            String strFechaNacimiento = userlabel3.getText().trim();
            String strNumeroCI = userlabel5.getText().trim();
            String strCorreo = userlabel7.getText().trim();
            String strCelular = userlabel6.getText().trim();
            
            // Verificar si el correo ya existe
            if (objDAO.verificarExistenciaCorreo(strCorreo)) {
                JOptionPane.showMessageDialog(this, 
                    "El correo electrónico ya está registrado en el sistema.",
                    "Correo duplicado", 
                    JOptionPane.WARNING_MESSAGE);
                userlabel7.requestFocus();
                return;
            }
            
            // Verificar si el CI ya existe
            if (objDAO.verificarExistenciaCI(strNumeroCI)) {
                JOptionPane.showMessageDialog(this, 
                    "El número de carnet ya está registrado en el sistema.",
                    "Carnet duplicado", 
                    JOptionPane.WARNING_MESSAGE);
                userlabel5.requestFocus();
                return;
            }
            
            // Insertar el postulante en la base de datos (sin contraseña, usando cadena vacía)
            Postulante objPostulanteCreado = objDAO.insertarPostulante(
                strCorreo, 
                "", // Contraseña vacía o puedes usar null si lo permites en la BD
                strNombre, 
                strApellidos, 
                strNumeroCI, 
                strFechaNacimiento
            );
            
            if (objPostulanteCreado != null) {
                // NO mostrar mensaje aquí, ir directo al siguiente formulario
                try {
                    // Navegar al siguiente formulario y pasar el postulante
                    RegistroPostulanteForm regis = new RegistroPostulanteForm(objPostulanteCreado);
                    regis.setVisible(true);
                    regis.setLocationRelativeTo(null);
                    this.dispose();
                    
                } catch (Exception e) {
                    logger.log(java.util.logging.Level.SEVERE, "Error al abrir el siguiente formulario", e);
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Error al abrir la siguiente ventana: " + e.getMessage(),
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al registrar el postulante en la base de datos.\n" +
                    "Por favor, intente nuevamente.",
                    "Error de registro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SeleccionTipoPost menuOli = new SeleccionTipoPost();
            menuOli.setVisible(true);
            menuOli.setLocationRelativeTo(null);
            this.dispose();
            
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al regresar", e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al regresar: " + e.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validateFields() {
        // Validar campos de texto
        JTextField[] fields = {userlabel1, userlabel4, userlabel3, userlabel5, userlabel7, userlabel6};
        String[] placeholders = {
            "Ingrese su nombre completo",
            "Apellidos", 
            "Ingrese su fecha de nacimiento dd/mm/aa",
            "Ingrese su numero de carnet",
            "Ingrese correo electronico",
            "Ingrese su telefono"
        };
        String[] fieldNames = {
            "Nombre",
            "Apellidos",
            "Fecha de nacimiento",
            "Número de carnet",
            "Correo electrónico",
            "Teléfono"
        };
        
        for (int i = 0; i < fields.length; i++) {
            String text = fields[i].getText().trim();
            if (text.isEmpty() || text.equals(placeholders[i])) {
                JOptionPane.showMessageDialog(this,
                    "Por favor complete el campo: " + fieldNames[i],
                    "Campo requerido",
                    JOptionPane.WARNING_MESSAGE);
                fields[i].requestFocus();
                return false;
            }
        }
        
        // Validación de email
        String email = userlabel7.getText().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingrese un correo electrónico válido\n" +
                "Ejemplo: usuario@dominio.com",
                "Email inválido",
                JOptionPane.WARNING_MESSAGE);
            userlabel7.requestFocus();
            return false;
        }
        
        // Validar fecha de nacimiento (formato dd/mm/aaaa)
        String fecha = userlabel3.getText().trim();
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingrese la fecha en formato dd/mm/aaaa\n" +
                "Ejemplo: 15/08/2000",
                "Formato de fecha inválido",
                JOptionPane.WARNING_MESSAGE);
            userlabel3.requestFocus();
            return false;
        }
        
        // Validar número de carnet (solo números)
        String carnet = userlabel5.getText().trim();
        if (!carnet.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                "El número de carnet debe contener solo dígitos",
                "Carnet inválido",
                JOptionPane.WARNING_MESSAGE);
            userlabel5.requestFocus();
            return false;
        }
        
        // Validar teléfono (solo números, mínimo 7 dígitos)
        String telefono = userlabel6.getText().trim();
        if (!telefono.matches("\\d{7,}")) {
            JOptionPane.showMessageDialog(this,
                "El teléfono debe contener al menos 7 dígitos",
                "Teléfono inválido",
                JOptionPane.WARNING_MESSAGE);
            userlabel6.requestFocus();
            return false;
        }
        
        return true;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new RegistroUsuarios().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JPanel background;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAtras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel subtitulo1;
    private javax.swing.JLabel subtitulo4;
    private javax.swing.JLabel subtitulo5;
    private javax.swing.JLabel subtitulo6;
    private javax.swing.JLabel subtitulo7;
    private javax.swing.JLabel subtitulo8;
    private javax.swing.JTextField userlabel1;
    private javax.swing.JTextField userlabel3;
    private javax.swing.JTextField userlabel4;
    private javax.swing.JTextField userlabel5;
    private javax.swing.JTextField userlabel6;
    private javax.swing.JTextField userlabel7;
    // End of variables declaration
}