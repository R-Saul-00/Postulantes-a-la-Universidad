package paqueteInterfacesPostulantes;

import paqueteUtilidades.GeneradorDeQR;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
    private final JButton botonAtras;    
    
    private Postulante objPostulante;
    private RegistroDocumentacion objRegistroDocumentacion;
    private DAO objDEAPersistencia; 

    public VentanaQrGenerado(Postulante objPostulanteRecibido, RegistroDocumentacion objRegistroDocumentacionRecibido) {
        super("Simulador de Pago de Matrícula - Postulante: " + objPostulanteRecibido.getNombreCompleto()); 
        
        this.objPostulante = objPostulanteRecibido;
        this.objRegistroDocumentacion = objRegistroDocumentacionRecibido;
        this.objDEAPersistencia = new DAO();
        
        objGeneradorQR = new GeneradorDeQR();
        lblQR = new JLabel();
        
        String strEstadoInicial = (this.objRegistroDocumentacion != null && this.objRegistroDocumentacion.getBolEstadoDePago()) ? "Estado del Pago: Aprobado" : "Estado del Pago: Pendiente";
        lblEstadoPago = new JLabel(strEstadoInicial, SwingConstants.CENTER);
        
        botonComprobar = new JButton("Comprobar Pago");
        botonAtras = new JButton("Atrás"); 
        
        configurarVentana();
        crearComponentes();
        agregarEventos();
        mostrarQR(); 
        
        if (this.objRegistroDocumentacion != null && this.objRegistroDocumentacion.getBolEstadoDePago()) {
            lblEstadoPago.setForeground(Color.GREEN);
            botonComprobar.setEnabled(false);
        } else {
            lblEstadoPago.setForeground(Color.RED);
        }
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
        panelSuperior.add(lblEstadoPago);
        
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        
        JPanel panelQR = new JPanel();
        panelQR.setBackground(Color.WHITE);
        panelQR.add(lblQR);
        
        panelPrincipal.add(panelQR, BorderLayout.CENTER);
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0)); 
        panelBoton.setBackground(Color.WHITE);
        
        botonAtras.setFont(new Font("Arial", Font.BOLD, 18));
        botonComprobar.setFont(new Font("Arial", Font.BOLD, 18));
        
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        
        panelBoton.add(botonAtras); 
        panelBoton.add(botonComprobar);
        
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);
        add(panelPrincipal);
    }
    
    private void agregarEventos() {
        botonComprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (objRegistroDocumentacion == null) {
                    JOptionPane.showMessageDialog(VentanaQrGenerado.this,
                                "Error de sistema: No se encontraron los datos de documentación para el pago.",
                                "Error Crítico",
                                JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                
                new Thread(() -> {
                    botonComprobar.setEnabled(false);
                    lblEstadoPago.setText("Comprobando pago...");
                    lblEstadoPago.setForeground(Color.BLUE);
                    
                    try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    
                    int intIdUsuario = objPostulante.getIntIdUsuario();
                    
                    RegistroDocumentacion objNuevoRegistro = objDEAPersistencia.obtenerRegistroDocumentacionPorIdUsuario(intIdUsuario);
                    
                    boolean bolEstadoPagoActualizado = false;
                    if (objNuevoRegistro != null) {
                        objRegistroDocumentacion = objNuevoRegistro;
                        
                        objPostulante.setObjRegistroDocumentacion(objNuevoRegistro); 
                        
                        bolEstadoPagoActualizado = objNuevoRegistro.getBolEstadoDePago();
                    }
                    
                    boolean bolPagoExitoso = bolEstadoPagoActualizado;
                    SwingUtilities.invokeLater(() -> {
                        if (bolPagoExitoso){
                            lblEstadoPago.setText("Estado del Pago: Aprobado");
                            lblEstadoPago.setForeground(Color.GREEN);
                            botonComprobar.setEnabled(false);
                            
                            JOptionPane.showMessageDialog(VentanaQrGenerado.this,
                                            "¡Pago Comprobado y Aprobado! Estado de pago actualizado en el objeto Postulante.",
                                            "Verificación de Pago Exitosa",
                                            JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            lblEstadoPago.setText("Estado del Pago: No Realizado");
                            lblEstadoPago.setForeground(Color.RED);
                            JOptionPane.showMessageDialog(VentanaQrGenerado.this,
                                            "¡Pago aún pendiente! Asegúrese de haber realizado la transacción.",
                                            "Verificación de Pago Fallida",
                                            JOptionPane.ERROR_MESSAGE);
                            botonComprobar.setEnabled(true);
                        }
                    });
                }).start();
            }
        });
        
        botonAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonAtrasActionPerformed(e);
            }
        });
    }
    
    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        
        SwingUtilities.invokeLater(() -> {
            interfazMetododoDePago objVentanaPago = new interfazMetododoDePago(this.objPostulante, this.objRegistroDocumentacion);
            objVentanaPago.setVisible(true);
        });
    }
    
    public void mostrarQR() {
        String strNombre = this.objPostulante.getNombreCompleto();
        
        boolean bolEstadoPago = (objRegistroDocumentacion != null) ? objRegistroDocumentacion.getBolEstadoDePago() : false;
        
        objGeneradorQR.setPagoRealizado(bolEstadoPago); 
        
        BufferedImage imgQR = objGeneradorQR.generarImagenQRPago(strNombre);
        if (imgQR != null) {
            lblQR.setIcon(new ImageIcon(imgQR));
        } else {
            lblQR.setText("Error al generar QR");
        }
    }
    
    public static void main(String[] args) {
        
    }
}