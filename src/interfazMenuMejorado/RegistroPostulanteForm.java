package interfazMenuMejorado;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;
import java.util.Map;

public class RegistroPostulanteForm extends javax.swing.JFrame {
    
    // Paleta de colores uniforme
    private static final java.awt.Color PRIMARY_BLUE = new java.awt.Color(0, 51, 102);
    private static final java.awt.Color ACCENT_RED = new java.awt.Color(204, 0, 0);
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(153, 204, 255);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color LIGHT_GRAY = new java.awt.Color(245, 250, 255);
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroPostulanteForm.class.getName());
    private String rutaFoto;
    
    // MAPA DE FACULTADES Y SUS CARRERAS
    private Map<String, String[]> facultadCarreras;
    
    public RegistroPostulanteForm() {
        initializeFacultadCarreras(); // INICIALIZAR DATOS ANTES DE CREAR COMPONENTES
        initComponents();
        setupDesign();
        setupComboBoxListeners(); // CONFIGURAR EVENTOS DE COMBOBOX
        lblFoto.setText("Sin foto");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    // INICIALIZAR EL MAPA DE FACULTADES Y CARRERAS
    private void initializeFacultadCarreras() {
        facultadCarreras = new HashMap<>();
        
        facultadCarreras.put("Humanidades y Ciencias de la Educación", new String[]{
            "Seleccionar carrera...", "Psicología", "Ciencias de la Educación", 
            "Lingüística Aplicada a la Enseñanza de Lenguas", "Comunicación Social", 
            "Trabajo Social", "Ciencias de la Actividad Física y Deportes", 
            "Música", "Educación Intercultural Bilingüe"
        });
        
        facultadCarreras.put("Ciencias Sociales", new String[]{
            "Seleccionar carrera...", "Sociología", "Antropología"
        });
        
        facultadCarreras.put("Ciencias Económicas", new String[]{
            "Seleccionar carrera...", "Contaduría Pública", "Administración de Empresas", 
            "Economía", "Ingeniería Financiera", "Ingeniería Comercial"
        });
        
        facultadCarreras.put("Arquitectura y Ciencias del Hábitat", new String[]{
            "Seleccionar carrera...", "Arquitectura", "Turismo", "Diseño de Interiores", 
            "Diseño Gráfico", "Planificación del Territorio y Medio Ambiente", 
            "Técnico Superior en Construcciones"
        });
        
        facultadCarreras.put("Medicina", new String[]{
            "Seleccionar carrera...", "Medicina", "Fisioterapia y Kinesiología", 
            "Nutrición y Dietética"
        });
        
        facultadCarreras.put("Ciencias y Tecnología", new String[]{
            "Seleccionar carrera...", "Ingeniería Petroquímica", "Ingeniería de Sistemas", 
            "Ingeniería Civil", "Ingeniería Industrial", "Ingeniería Electrónica", 
            "Ingeniería Mecánica"
        });
        
        facultadCarreras.put("Ciencias Jurídicas y Políticas", new String[]{
            "Seleccionar carrera...", "Ciencias Jurídicas", "Ciencias Políticas"
        });
        
        facultadCarreras.put("Ciencias Agrícolas, Pecuarias y Forestales", new String[]{
            "Seleccionar carrera...", "Ingeniería Agronómica", "Ingeniería Forestal", 
            "Ingeniería en Producción Animal", "Ingeniería Agroindustrial"
        });
        
        facultadCarreras.put("Ciencias Veterinarias", new String[]{
            "Seleccionar carrera...", "Medicina Veterinaria y Zootecnia"
        });
        
        facultadCarreras.put("Bioquímica y Farmacia", new String[]{
            "Seleccionar carrera...", "Bioquímica y Farmacia"
        });
        
        facultadCarreras.put("Enfermería", new String[]{
            "Seleccionar carrera...", "Enfermería"
        });
        
        facultadCarreras.put("Desarrollo Rural y Territorial", new String[]{
            "Seleccionar carrera...", "Desarrollo Rural y Territorial"
        });
        
        facultadCarreras.put("Politécnica del Valle Alto", new String[]{
            "Seleccionar carrera...", "Construcción Civil", "Salud Comunitaria", 
            "Tecnología Agroindustrial"
        });
        
        facultadCarreras.put("Odontología", new String[]{
            "Seleccionar carrera...", "Odontología"
        });
    }
    
    // CONFIGURAR LISTENERS PARA LOS COMBOBOX
    private void setupComboBoxListeners() {
        jComboBox2.addActionListener(evt -> {
            String facultadSeleccionada = (String) jComboBox2.getSelectedItem();
            updateCarrerasComboBox(facultadSeleccionada);
        });
    }
    
    // ACTUALIZAR COMBOBOX DE CARRERAS SEGÚN FACULTAD SELECCIONADA
    private void updateCarrerasComboBox(String facultad) {
        jComboBox3.removeAllItems();
        
        if (facultad != null && !facultad.equals("Seleccionar...")) {
            String[] carreras = facultadCarreras.get(facultad);
            if (carreras != null) {
                for (String carrera : carreras) {
                    jComboBox3.addItem(carrera);
                }
            } else {
                jComboBox3.addItem("No hay carreras disponibles");
            }
        } else {
            jComboBox3.addItem("Seleccione primero una facultad");
        }
    }
    
    private void setupDesign() {
        getContentPane().setBackground(LIGHT_GRAY);
        jLabel1.setForeground(PRIMARY_BLUE);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        
        setupPanel2();
        setupPanel3();
        setupButtons();
        setupLabels();
    }
    
    private void setupPanel2() {
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS ACADÉMICOS",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Segoe UI", 1, 12), PRIMARY_BLUE));
        jPanel2.setBackground(WHITE);
    }
    
    private void setupPanel3() {
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS EXTRAS",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Segoe UI", 1, 12), ACCENT_RED));
        jPanel3.setBackground(WHITE);
    }
    
    private void setupLabels() {
        javax.swing.JLabel[] labelsPanel2 = {jLabel8, jLabel9, jLabel10, jLabel11};
        for (javax.swing.JLabel label : labelsPanel2) {
            label.setForeground(PRIMARY_BLUE);
            label.setFont(new java.awt.Font("Segoe UI", 1, 14));
        }
        
        javax.swing.JLabel[] labelsPanel3 = {jLabel2, jLabel13, jLabel14, jLabel15};
        for (javax.swing.JLabel label : labelsPanel3) {
            label.setForeground(ACCENT_RED);
            label.setFont(new java.awt.Font("Segoe UI", 1, 14));
        }
    }
    
    private void setupButtons() {
        setupButton(jButton1, LIGHT_BLUE, PRIMARY_BLUE);
        setupButton(jButton2, PRIMARY_BLUE, WHITE);
        setupButton(jButton3, LIGHT_BLUE, PRIMARY_BLUE);
        setupButton(jButton4, ACCENT_RED, WHITE);
        setupButton(jButtonAtras, ACCENT_RED, WHITE);
    }
    
    private void setupButton(javax.swing.JButton button, java.awt.Color bgColor, java.awt.Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButtonAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO DE POSTULANTE - Sistema Universitario");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRAR DATOS DEL POSTULANTE");

        // ================= PANEL 2 (Datos Académicos) ==================
        jLabel8.setText("Nombre del Colegio:");
        jLabel9.setText("Departamento:");
        jLabel10.setText("Municipio:");
        jLabel11.setText("Nº Certificado de Bachiller:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { 
            "Seleccionar...", "La Paz", "Cochabamba", "Santa Cruz", 
            "Oruro", "Potosí", "Tarija", "Sucre", "Beni", "Pando" 
        }));

        // Layout del panel 2 (igual que antes)
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        // ================= PANEL 3 (Datos Extras) ==================
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setText("Sin foto");
        lblFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Fotografía:");
        jButton1.setText("Seleccionar foto");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jLabel14.setText("Seleccione Facultad:");
        
        // CARGAR FACULTADES EN EL COMBOBOX
        jComboBox2.addItem("Seleccionar...");
        for (String facultad : facultadCarreras.keySet()) {
            jComboBox2.addItem(facultad);
        }

        jLabel15.setText("Seleccione carrera:");
        // INICIALIZAR COMBOBOX DE CARRERAS VACÍO
        jComboBox3.addItem("Seleccione primero una facultad");

        jLabel2.setText("Celular:");

        // Layout del panel 3 (igual que antes)
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(30, 30, 30)
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(20)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(20)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(20)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10)
                .addComponent(jButton1)
                .addGap(15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        // ================= BOTONES ==================
        jButton2.setText("GUARDAR");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));
        
        jButton3.setText("LIMPIAR");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));
        
        jButton4.setText("CANCELAR");
        jButton4.addActionListener(evt -> jButton4ActionPerformed(evt));
        
        jButtonAtras.setText("ATRÁS");
        jButtonAtras.addActionListener(evt -> jButtonAtrasActionPerformed(evt));

        // ================= LAYOUT CENTRADO ==================
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        getContentPane().setLayout(layout);
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        gbc.insets = new java.awt.Insets(20, 20, 20, 20);

        getContentPane().add(jLabel1, gbc);

        gbc.gridy++;
        javax.swing.JPanel panelCentral = new javax.swing.JPanel();
        panelCentral.setLayout(new java.awt.GridLayout(1, 2, 30, 0));
        panelCentral.setOpaque(false);
        panelCentral.add(jPanel2);
        panelCentral.add(jPanel3);
        getContentPane().add(panelCentral, gbc);

        gbc.gridy++;
        javax.swing.JPanel panelBotones = new javax.swing.JPanel();
        panelBotones.setOpaque(false);
        panelBotones.add(jButtonAtras);
        panelBotones.add(jButton3);
        panelBotones.add(jButton2);
        panelBotones.add(jButton4);
        getContentPane().add(panelBotones, gbc);

        pack();
    }

    // RESTO DE MÉTODOS (iguales que antes)
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Imágenes (*.jpg, *.jpeg, *.png)", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        
        int opcion = fileChooser.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                ImageIcon icon = new ImageIcon(archivo.getAbsolutePath());
                int w = lblFoto.getWidth();
                int h = lblFoto.getHeight();
                if (w <= 0 || h <= 0) {
                    w = 120; h = 120;
                }
                Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(img));
                lblFoto.setText("");
                rutaFoto = archivo.getAbsolutePath();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Error al cargar la imagen: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (validateFields()) {
            String mensaje = String.format(
                "Datos guardados exitosamente:\n\n" +
                "Colegio: %s\n" +
                "Departamento: %s\n" +
                "Municipio: %s\n" +
                "Facultad: %s\n" +
                "Carrera: %s\n" +
                "Celular: %s",
                jTextField2.getText(),
                jComboBox1.getSelectedItem(),
                jTextField7.getText(),
                jComboBox2.getSelectedItem(),
                jComboBox3.getSelectedItem(),
                jTextField1.getText()
            );
            
            JOptionPane.showMessageDialog(this, mensaje, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        clearFields();
        JOptionPane.showMessageDialog(this, "Formulario limpiado", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de cancelar? Se perderán los datos ingresados.",
            "Confirmar cancelación", JOptionPane.YES_NO_OPTION);
            
        if (result == JOptionPane.YES_OPTION) {
            jButtonAtrasActionPerformed(evt);
        }
    }
    
    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            RegistroUsuarios registro = new RegistroUsuarios();
            registro.setVisible(true);
            registro.setLocationRelativeTo(null);
            this.dispose();
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al regresar a RegistroUsuarios", e);
            JOptionPane.showMessageDialog(this, "Error al regresar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validateFields() {
        if (jTextField2.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del colegio", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            jTextField2.requestFocus();
            return false;
        }
        
        if (jComboBox1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un departamento", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (jComboBox2.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una facultad", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (jComboBox3.getSelectedIndex() == 0 || jComboBox3.getSelectedItem().toString().contains("Seleccione primero")) {
            JOptionPane.showMessageDialog(this, "Seleccione una carrera", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void clearFields() {
        jTextField2.setText("");
        jTextField7.setText("");
        jTextField10.setText("");
        jTextField1.setText("");
        
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.removeAllItems();
        jComboBox3.addItem("Seleccione primero una facultad");
        
        lblFoto.setIcon(null);
        lblFoto.setText("Sin foto");
        rutaFoto = null;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new RegistroPostulanteForm().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAtras;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField4;
}