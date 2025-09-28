package interfazMenuMejorado;

public class interfazCarnet extends javax.swing.JFrame {
    
    // Paleta de colores uniforme para todas las ventanas
    private static final java.awt.Color PRIMARY_BLUE = new java.awt.Color(0, 51, 102);
    private static final java.awt.Color ACCENT_RED = new java.awt.Color(204, 0, 0);
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(153, 204, 255);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color LIGHT_GRAY = new java.awt.Color(245, 250, 255);
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(interfazCarnet.class.getName());
    
    public interfazCarnet() {
        initComponents();
        setupDesign();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    private void setupDesign() {
        // Configurar fondo de la ventana
        getContentPane().setBackground(LIGHT_GRAY);
        
        // Configurar título principal
        jLabel1.setForeground(PRIMARY_BLUE);
        
        // Configurar etiqueta de campo
        jLabel2.setForeground(PRIMARY_BLUE);
        
        // Configurar campo de texto
        jTextField1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(PRIMARY_BLUE, 2),
            javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        jTextField1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 16));
        
        // Configurar botón NEXT
        jButton1.setBackground(PRIMARY_BLUE);
        jButton1.setForeground(WHITE);
        jButton1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jButton1.setFocusPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Configurar botón ATRÁS
        jButtonAtras.setBackground(ACCENT_RED);
        jButtonAtras.setForeground(WHITE);
        jButtonAtras.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        jButtonAtras.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jButtonAtras.setFocusPainted(false);
        jButtonAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Efectos hover para botones
        setupHoverEffect(jButton1, PRIMARY_BLUE);
        setupHoverEffect(jButtonAtras, ACCENT_RED);
    }
    
    private void setupHoverEffect(javax.swing.JButton button, java.awt.Color originalColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButtonAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INGRESO DE CARNET - Sistema Universitario");

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 0, 28)); 
        jLabel1.setText("INGRESE CARNET DE POSTULANTE");

        jLabel2.setFont(new java.awt.Font("Eras Demi ITC", 0, 18)); 
        jLabel2.setText("Ingrese Carnet:");

        jTextField1.setColumns(15);
        jTextField1.setPreferredSize(new java.awt.Dimension(200, 40));

        jButton1.setText("SIGUIENTE");
        jButton1.setPreferredSize(new java.awt.Dimension(140, 45));
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButtonAtras.setText("ATRÁS");
        jButtonAtras.setPreferredSize(new java.awt.Dimension(140, 45));
        jButtonAtras.addActionListener(evt -> jButtonAtrasActionPerformed(evt));

        // Layout centrado
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

        // Panel con etiqueta + campo de texto
        panelGbc.gridy++;
        javax.swing.JPanel panelInput = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));
        panelInput.setOpaque(false);
        panelInput.add(jLabel2);
        panelInput.add(jTextField1);
        mainPanel.add(panelInput, panelGbc);

        // Panel de botones
        panelGbc.gridy++;
        javax.swing.JPanel panelBotones = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);
        panelBotones.add(jButtonAtras);
        panelBotones.add(jButton1);
        mainPanel.add(panelBotones, panelGbc);

        // Agregar panel principal centrado
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        getContentPane().add(mainPanel, gbc);

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Validar que el carnet no esté vacío
        String carnet = jTextField1.getText().trim();
        if (carnet.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Por favor ingrese el número de carnet",
                "Campo requerido",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            jTextField1.requestFocus();
            return;
        }
        
        try {
            RegistroPostulanteForm registro = new RegistroPostulanteForm();
            registro.setVisible(true);
            registro.setLocationRelativeTo(null);
            this.dispose();
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al abrir RegistroPostulanteForm", e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de registro: " + e.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // AQUÍ PUEDES CAMBIAR LA VENTANA QUE SE ABRE AL PRESIONAR ATRÁS
            // Ejemplo: abrir MenuOli
            SeleccionTipoPost menu = new SeleccionTipoPost();
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            this.dispose();
            
            // Alternativas que puedes usar:
            // menu ventana = new menu(); // para ir al menú principal
            // RegistroUsuarios registro = new RegistroUsuarios(); // para ir al registro de usuarios
            
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
        java.awt.EventQueue.invokeLater(() -> new interfazCarnet().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAtras; // NUEVO BOTÓN
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}