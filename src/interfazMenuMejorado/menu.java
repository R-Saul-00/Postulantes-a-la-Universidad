package interfazMenuMejorado;

import java.awt.*;
import javax.swing.Box;
import javax.swing.JFrame;

public class menu extends javax.swing.JFrame {
    
    // Paleta de colores uniforme
    private static final java.awt.Color PRIMARY_BLUE = new java.awt.Color(0, 51, 102);
    private static final java.awt.Color ACCENT_RED = new java.awt.Color(204, 0, 0);
    private static final java.awt.Color LIGHT_BLUE = new java.awt.Color(153, 204, 255);
    private static final java.awt.Color WHITE = new java.awt.Color(255, 255, 255);
    private static final java.awt.Color LIGHT_GRAY = new java.awt.Color(245, 250, 255);
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(menu.class.getName());

    public menu() {
        initComponents();
        setupDesign(); // APLICAR DISEÑO DESPUÉS DE CREAR COMPONENTES
        setupFullScreen();
    }
    
    private void setupFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    // NUEVO MÉTODO PARA APLICAR LA PALETA DE COLORES
    private void setupDesign() {
        // Fondo principal
        bg.setBackground(LIGHT_GRAY);
        centerPanel.setBackground(LIGHT_GRAY);
        
        // Panel azul principal (mantener azul pero usar constante)
        jPanel1.setBackground(PRIMARY_BLUE);
        
        // Título sobre fondo azul (mantener blanco)
        jLabel1.setForeground(WHITE);
        
        // Logo (usar azul principal)
        jLabel2.setForeground(PRIMARY_BLUE);
        
        // Configurar botones con la nueva paleta
        setupButton(jButton1, WHITE, PRIMARY_BLUE);       // Métodos de Pago - botón normal
        setupButton(jButton2, LIGHT_BLUE, PRIMARY_BLUE);  // Estado inscripción - secundario
        setupButton(jButton3, ACCENT_RED, WHITE);         // Registro documentación - principal
        
        // Panel rojo inferior (usar constante)
        jPanel3.setBackground(ACCENT_RED);
    }
    
    private void setupButton(javax.swing.JButton button, java.awt.Color bgColor, java.awt.Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (bgColor.equals(WHITE)) {
                    button.setBackground(LIGHT_BLUE);
                } else {
                    button.setBackground(bgColor.brighter());
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // ... (mantener el código existente de initComponents igual) ...
        bg = new javax.swing.JPanel();
        centerPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO INICIAL - Sistema Universitario");

        // ... resto del código initComponents igual al original ...
        
        // El resto del código de initComponents permanece igual
        bg.setBackground(new java.awt.Color(240, 240, 240));
        bg.setLayout(new java.awt.BorderLayout());

        centerPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerPanel.setLayout(new java.awt.GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 500));

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 0, 36));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO INICIAL");
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setFont(new java.awt.Font("Eras Demi ITC", 0, 18));
        jButton1.setText("Métodos de Pago");
        jButton1.setPreferredSize(new java.awt.Dimension(250, 40));
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setFont(new java.awt.Font("Eras Demi ITC", 0, 18));
        jButton2.setText("Estado de inscripción");
        jButton2.setPreferredSize(new java.awt.Dimension(250, 40));

        jButton3.setFont(new java.awt.Font("Eras Demi ITC", 0, 18));
        jButton3.setText("Registro de documentación");
        jButton3.setPreferredSize(new java.awt.Dimension(250, 40));
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));
        jPanel3.setPreferredSize(new java.awt.Dimension(0, 5));

        javax.swing.JPanel centralComponents = new javax.swing.JPanel();
        centralComponents.setOpaque(false);
        centralComponents.setLayout(new javax.swing.BoxLayout(centralComponents, javax.swing.BoxLayout.Y_AXIS));

        centralComponents.add(Box.createVerticalGlue());
        jLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralComponents.add(jLabel1);
        centralComponents.add(Box.createVerticalStrut(60));

        jButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralComponents.add(jButton1);
        centralComponents.add(Box.createVerticalStrut(30));

        jButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralComponents.add(jButton2);
        centralComponents.add(Box.createVerticalStrut(30));

        jButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralComponents.add(jButton3);

        centralComponents.add(Box.createVerticalGlue());

        jPanel1.add(centralComponents, BorderLayout.CENTER);
        jPanel1.add(jPanel3, BorderLayout.SOUTH);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LOGO UNIVERSITARIO");
        jLabel2.setFont(new java.awt.Font("Arial", Font.BOLD, 24));
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));

        gbc.gridy = 0;
        centerPanel.add(jPanel1, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(30, 0, 0, 0);
        centerPanel.add(jLabel2, gbc);

        bg.add(centerPanel, BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println("Métodos de Pago seleccionado");
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            System.out.println("Registro de documentación seleccionado");
            
            // Abrir MenuOli con la nueva paleta de colores
            SeleccionTipoPost menuOli = new SeleccionTipoPost();
            menuOli.setVisible(true);
            menuOli.setLocationRelativeTo(null);
            this.dispose();
            
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al abrir MenuOli", e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de registro de documentación: " + e.getMessage(),
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

        java.awt.EventQueue.invokeLater(() -> {
            new menu().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JPanel bg;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration
}