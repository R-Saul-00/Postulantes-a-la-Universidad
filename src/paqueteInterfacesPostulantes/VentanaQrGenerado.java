package paqueteInterfacesPostulantes;

import paqueteServicios.GeneradorDeQR;
import paqueteServicios.Postulante;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class VentanaQrGenerado extends JFrame {

    private final GeneradorDeQR objGeneradorQR;
    private final JLabel lblQR;
    private final JLabel lblEstadoPago;
    private final JButton botonComprobar;
    
    private Postulante objPostulante;

    /**
     * CONSTRUCTOR PRINCIPAL: Recibe el objeto Postulante.
     */
    public VentanaQrGenerado(Postulante objPostulanteRecibido) {
        // Usamos el nuevo método obtenerNombreCompleto para el título
        super("Simulador de Pago de Matrícula - Postulante: " + objPostulanteRecibido.getNombreCompleto()); 
        
        this.objPostulante = objPostulanteRecibido;
        
        objGeneradorQR = new GeneradorDeQR();
        lblQR = new JLabel();
        lblEstadoPago = new JLabel("Estado del Pago: Pendiente", SwingConstants.CENTER);
        botonComprobar = new JButton("Comprobar Pago");
        
        configurarVentana();
        crearComponentes();
        agregarEventos();
        mostrarQR(); 
    }
    
    public VentanaQrGenerado() {
        super("Simulador de Pago de Matrícula - MODO PRUEBA");
        this.objPostulante = new Postulante(0, "debug@test.com", "Test", "User", "0000000", "01/01/2000"); 
        
        objGeneradorQR = new GeneradorDeQR();
        lblQR = new JLabel();
        lblEstadoPago = new JLabel("Estado del Pago: Pendiente", SwingConstants.CENTER);
        botonComprobar = new JButton("Comprobar Pago");
        
        configurarVentana();
        crearComponentes();
        agregarEventos();
        mostrarQR();
    }

    private void configurarVentana() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void crearComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel("Escanea el Código QR para Pagar", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30)); 
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        panelSuperior.add(lblTitulo);
        
        lblEstadoPago.setFont(new Font("Arial", Font.PLAIN, 24)); 
        lblEstadoPago.setForeground(Color.RED);
        panelSuperior.add(lblEstadoPago);
        
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        
        //QR
        JPanel panelQR = new JPanel();
        panelQR.setBackground(Color.WHITE);
        panelQR.add(lblQR);
        
        panelPrincipal.add(panelQR, BorderLayout.CENTER);
        
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(Color.WHITE);
        botonComprobar.setFont(new Font("Arial", Font.BOLD, 18));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        panelBoton.add(botonComprobar);
        
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);
        add(panelPrincipal);
    }
    
    private void agregarEventos() {
        botonComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    botonComprobar.setEnabled(false);
                    lblEstadoPago.setText("Comprobando pago...");
                    lblEstadoPago.setForeground(Color.BLUE);
                    
                    try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    
                    objGeneradorQR.setPagoRealizado(true);
                    boolean bEstadoPago = objGeneradorQR.getEstadoPago();
                    
                    SwingUtilities.invokeLater(() -> {
                        if (bEstadoPago) {
                            lblEstadoPago.setText("Estado del Pago: Aprobado");
                            lblEstadoPago.setForeground(Color.GREEN);
                            
                            // Usamos el método obtenerNombreCompleto
                            JOptionPane.showMessageDialog(VentanaQrGenerado.this,
                                    "¡Pago Comprobado y Aprobado!\nPostulante: " + objPostulante.getNombreCompleto(),
                                    "Verificación de Pago",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            lblEstadoPago.setText("Estado del Pago: No Realizado");
                            lblEstadoPago.setForeground(Color.RED);
                            JOptionPane.showMessageDialog(VentanaQrGenerado.this,
                                    "¡Pago no realizado! Por favor, intente de nuevo.",
                                    "Verificación de Pago",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        botonComprobar.setEnabled(true);
                    });
                }).start();
            }
        });
    }
    
    /**
     * **MÉTODO MODIFICADO:** Ahora obtiene el nombre del Postulante localmente.
     * Genera el QR pasando SOLO el nombre al GeneradorDeQR.
     */
    public void mostrarQR() {
        // Se obtiene SOLO el nombre (getStrNombre()) y se pasa al generador.
        String strNombre = this.objPostulante.getNombreCompleto();
        
        BufferedImage imgQR = objGeneradorQR.generarImagenQRPago(strNombre);
        if (imgQR != null) {
            lblQR.setIcon(new ImageIcon(imgQR));
        } else {
            lblQR.setText("Error al generar QR");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaQrGenerado ventana = new VentanaQrGenerado();
            ventana.setVisible(true);
        });
    }
}