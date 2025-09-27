/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfazMenu;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class registroUsuarios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(registroUsuarios.class.getName());
    
    private final Color PLACEHOLDER_COLOR = new Color(153, 153, 153);
    private final Color TEXT_COLOR = Color.BLACK;
    
    public registroUsuarios() {
        initComponents();
        setupPlaceholders();
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
                field.setForeground(TEXT_COLOR);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (field.getText().isEmpty()) {
                field.setText(placeholderText);
                field.setForeground(PLACEHOLDER_COLOR);
            }
        }
    });
}
        private void setupPlaceholder(javax.swing.JPasswordField field, String placeholderText) {
        field.setEchoChar((char) 0); // Mostrar texto normal
        field.setText(placeholderText);
        field.setForeground(PLACEHOLDER_COLOR);

        field.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            String pass = new String(field.getPassword());
            if (pass.equals(placeholderText)) {
                field.setText("");
                field.setForeground(TEXT_COLOR);
                field.setEchoChar('*'); // Activar modo contraseña
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            String pass = new String(field.getPassword());
            if (pass.isEmpty()) {
                field.setEchoChar((char) 0); // Mostrar texto normal
                field.setText(placeholderText);
                field.setForeground(PLACEHOLDER_COLOR);
            }
        }
    });
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        userlabel1 = new javax.swing.JTextField();
        subtitulo4 = new javax.swing.JLabel();
        subtitulo5 = new javax.swing.JLabel();
        userlabel4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        subtitulo6 = new javax.swing.JLabel();
        userlabel5 = new javax.swing.JTextField();
        subtitulo1 = new javax.swing.JLabel();
        userlabel3 = new javax.swing.JTextField();
        subtitulo7 = new javax.swing.JLabel();
        userlabel6 = new javax.swing.JTextField();
        subtitulo8 = new javax.swing.JLabel();
        userlabel7 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel1.setText("REGISTRO DE POSTULANTE");
        background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, 80));

        userlabel1.setForeground(new java.awt.Color(204, 204, 204));
        userlabel1.setText("Ingrese su nombre completo");
        userlabel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlabel1ActionPerformed(evt);
            }
        });
        background.add(userlabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 240, 30));

        subtitulo4.setFont(new java.awt.Font("Eras Demi ITC", 0, 14)); // NOI18N
        subtitulo4.setText("APELLIDOS:");
        background.add(subtitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 90, 30));

        subtitulo5.setFont(new java.awt.Font("Eras Demi ITC", 0, 14)); // NOI18N
        subtitulo5.setText("NOMBRE:");
        background.add(subtitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 70, 30));

        userlabel4.setForeground(new java.awt.Color(204, 204, 204));
        userlabel4.setText("Apellidos");
        userlabel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlabel4ActionPerformed(evt);
            }
        });
        background.add(userlabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 180, 30));

        jLabel4.setFont(new java.awt.Font("Eras Bold ITC", 0, 18)); // NOI18N
        jLabel4.setText("Informacion Personal\n");
        background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 260, 30));

        subtitulo6.setFont(new java.awt.Font("Eras Demi ITC", 0, 14)); // NOI18N
        subtitulo6.setText("NUMERO DE CARNET:");
        background.add(subtitulo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 160, 30));

        userlabel5.setForeground(new java.awt.Color(204, 204, 204));
        userlabel5.setText("Ingrese su numero de carnet");
        userlabel5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlabel5ActionPerformed(evt);
            }
        });
        background.add(userlabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 240, 30));

        subtitulo1.setFont(new java.awt.Font("Eras Demi ITC", 0, 14)); // NOI18N
        subtitulo1.setText("CELULAR:");
        background.add(subtitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, 40));

        userlabel3.setForeground(new java.awt.Color(204, 204, 204));
        userlabel3.setText("Ingrese su fecha de nacimiento dd/mm/aa");
        userlabel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlabel3ActionPerformed(evt);
            }
        });
        background.add(userlabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 240, 30));

        subtitulo7.setFont(new java.awt.Font("Eras Demi ITC", 0, 14)); // NOI18N
        subtitulo7.setText("CORREO:");
        background.add(subtitulo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, 30));

        userlabel6.setForeground(new java.awt.Color(204, 204, 204));
        userlabel6.setText("Ingrese su # celular");
        userlabel6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlabel6ActionPerformed(evt);
            }
        });
        background.add(userlabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 150, 30));

        subtitulo8.setFont(new java.awt.Font("Eras Demi ITC", 0, 14)); // NOI18N
        subtitulo8.setText("FECHA DE NACIMIENTO:");
        background.add(subtitulo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 30));

        userlabel7.setForeground(new java.awt.Color(204, 204, 204));
        userlabel7.setText("Ingrese correo electronico");
        userlabel7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlabel7ActionPerformed(evt);
            }
        });
        background.add(userlabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 240, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Siguiente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        background.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 140, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userlabel7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlabel7ActionPerformed

        userlabel6.requestFocus();
    }//GEN-LAST:event_userlabel7ActionPerformed

    private void userlabel6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlabel6ActionPerformed

    }//GEN-LAST:event_userlabel6ActionPerformed

    private void userlabel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlabel3ActionPerformed
        userlabel5.requestFocus();
    }//GEN-LAST:event_userlabel3ActionPerformed

    private void userlabel5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlabel5ActionPerformed
        userlabel7.requestFocus();
    }//GEN-LAST:event_userlabel5ActionPerformed

    private void userlabel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlabel4ActionPerformed

    }//GEN-LAST:event_userlabel4ActionPerformed

    private void userlabel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlabel1ActionPerformed
        userlabel4.requestFocus();  // Mover al siguiente campo
    }//GEN-LAST:event_userlabel1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this, "Registro exitoso");
        try {
            RegistroPostulanteForm registr = new RegistroPostulanteForm();
            registr.setVisible(true);
            registr.setLocationRelativeTo(null); // Centrar la ventana
            this.dispose(); // Cerrar la ventana actual
        } catch (Exception e) {
            logger.log(java.util.logging.Level.SEVERE, "Error al abrir MenuOli", e);
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error al abrir la ventana de registro de documentación: " + e.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new registroUsuarios().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton jButton1;
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
    // End of variables declaration//GEN-END:variables

  
}
