package interfazMenuMejorado;

public class SeleccionTipoPost extends javax.swing.JFrame {
    
    // Paleta de colores uniforme
    private static final java.awt.Color PRIMARY_BLUE = new java.awt.Color(0, 51, 102);
    private static final java.awt.Color ACCENT_RED = new java.awt.Color(204, 0, 0);
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(153, 204, 255);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color LIGHT_GRAY = new java.awt.Color(245, 250, 255);
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SeleccionTipoPost.class.getName());

    public SeleccionTipoPost() {
        initComponents();
        setupDesign(); // APLICAR DISEÑO DESPUÉS DE CREAR COMPONENTES
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    // NUEVO MÉTODO PARA APLICAR LA PALETA DE COLORES
    private void setupDesign() {
        // Fondo principal
        getContentPane().setBackground(LIGHT_GRAY);
        
        // Título principal
        jLabel1.setForeground(PRIMARY_BLUE);
        
        // Etiquetas de descripción
        jLabel2.setForeground(PRIMARY_BLUE);
        jLabel3.setForeground(PRIMARY_BLUE);
        
        // Configurar botones con la nueva paleta
        setupButton(jButton1, PRIMARY_BLUE, WHITE);    // Postulante Nuevo - principal
        setupButton(jButton2, LIGHT_BLUE, PRIMARY_BLUE); // Postulante Existente - secundario
        setupButton(jButtonAtras, ACCENT_RED, WHITE);  // Botón Atrás - rojo
    }
    
    private void setupButton(javax.swing.JButton button, java.awt.Color bgColor, java.awt.Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        button.setPreferredSize(new java.awt.Dimension(200, 45));
        
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

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonAtras = new javax.swing.JButton(); // NUEVO BOTÓN

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO ESTUDIANTE - Sistema Universitario");
        
        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 0, 28)); 
        jLabel1.setText("REGISTRO ESTUDIANTE");

        jLabel2.setFont(new java.awt.Font("Eras Demi ITC", 0, 16)); 
        jLabel2.setText("Registro para Nuevos:");

        jButton1.setText("Postulante Nuevo");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("Postulante Existente");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jLabel3.setFont(new java.awt.Font("Eras Demi ITC", 0, 16)); 
        jLabel3.setText("Registro para Existentes:");

        jButtonAtras.setText("ATRÁS");
        jButtonAtras.addActionListener(evt -> jButtonAtrasActionPerformed(evt));

        // Layout centrado con GridBagLayout
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        getContentPane().setLayout(layout);
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        
        // Panel principal con fondo blanco
        javax.swing.JPanel mainPanel = new javax.swing.JPanel();
        mainPanel.setBackground(WHITE);
        mainPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(PRIMARY_BLUE, 3),
            javax.swing.BorderFactory.createEmptyBorder(40, 60, 40, 60)
        ));
        mainPanel.setLayout(new java.awt.GridBagLayout());
        
        java.awt.GridBagConstraints panelGbc = new java.awt.GridBagConstraints();
        panelGbc.gridx = 0;
        panelGbc.insets = new java.awt.Insets(15, 15, 15, 15);
        panelGbc.anchor = java.awt.GridBagConstraints.CENTER;

        // Título
        panelGbc.gridy = 0;
        mainPanel.add(jLabel1, panelGbc);

        // Texto y botón "Nuevo"
        panelGbc.gridy++;
        panelGbc.insets = new java.awt.Insets(30, 15, 10, 15);
        mainPanel.add(jLabel2, panelGbc);
        
        panelGbc.gridy++;
        panelGbc.insets = new java.awt.Insets(5, 15, 20, 15);
        mainPanel.add(jButton1, panelGbc);

        // Texto y botón "Existente"
        panelGbc.gridy++;
        panelGbc.insets = new java.awt.Insets(20, 15, 10, 15);
        mainPanel.add(jLabel3, panelGbc);
        
        panelGbc.gridy++;
        panelGbc.insets = new java.awt.Insets(5, 15, 30, 15);
        mainPanel.add(jButton2, panelGbc);

        // Botón Atrás
        panelGbc.gridy++;
        panelGbc.insets = new java.awt.Insets(20, 15, 15, 15);
        mainPanel.add(jButtonAtras, panelGbc);

        // Agregar panel principal centrado
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        getContentPane().add(mainPanel, gbc);

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            RegistroUsuarios regist = new RegistroUsuarios();
            regist.setVisible(true);
            regist.setLocationRelativeTo(null);
            this.dispose();
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al abrir RegistroUsuarios", e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de registro de usuarios: " + e.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Navegar a interfazCarnet para postulantes existentes
            interfazCarnet carnet = new interfazCarnet();
            carnet.setVisible(true);
            carnet.setLocationRelativeTo(null);
            this.dispose();
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al abrir interfazCarnet", e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de carnet: " + e.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // AQUÍ PUEDES CAMBIAR A DONDE REGRESA EL BOTÓN ATRÁS
            // Opción 1: Menú principal
            menu menuPrincipal = new menu();
            menuPrincipal.setVisible(true);
            
            // Opción 2: Otra ventana específica
            // OtraVentana ventana = new OtraVentana();
            // ventana.setVisible(true);
            
            menuPrincipal.setLocationRelativeTo(null);
            this.dispose();
            
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al regresar", e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al regresar: " + e.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
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
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new SeleccionTipoPost().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAtras; // NUEVO BOTÓN
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration
}