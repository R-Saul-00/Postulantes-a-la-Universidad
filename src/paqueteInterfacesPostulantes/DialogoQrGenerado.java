package paqueteInterfacesPostulantes;

import paqueteUtilidades.GeneradorDeQR;
import paqueteServicios.Postulante;
import paqueteServicios.RegistroDocumentacion;
import paquetePersistencia.DAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DialogoQrGenerado extends JDialog {

    private static final Logger objLogger = Logger.getLogger(DialogoQrGenerado.class.getName());

    private final GeneradorDeQR objGeneradorQR;
    private final JLabel lblQR;
    private final JLabel lblEstadoPago;
    private final JLabel lblCodigoPostulante;
    private final JButton botonComprobar;
    private final JButton botonAtras;
    
    private Postulante objPostulante;
    private RegistroDocumentacion objRegistroDocumentacion;
    private DAO objDAOPersistencia;
    private final MetododoDePago objVentanaPadre;

    public DialogoQrGenerado(MetododoDePago objVentanaPadre, Postulante objPostulanteRecibido, RegistroDocumentacion objRegistroDocumentacionRecibido) {
        super(objVentanaPadre, "Escaneo de Codigo QR", true);
        this.objVentanaPadre = objVentanaPadre; 
        this.objPostulante = objPostulanteRecibido;
        this.objRegistroDocumentacion = objRegistroDocumentacionRecibido;
        this.objDAOPersistencia = new DAO();
        
        objGeneradorQR = new GeneradorDeQR();
        lblQR = new JLabel();
        
        lblEstadoPago = new JLabel("", SwingConstants.CENTER);
        lblCodigoPostulante = new JLabel("", SwingConstants.CENTER);
        
        botonComprobar = new JButton("Comprobar Pago");
        botonAtras = new JButton("Cerrar");
        
        configurarDialogo();
        crearComponentes();
        agregarEventos();
        mostrarQR();
        
        actualizarEstado(this.objRegistroDocumentacion);
    }

    private void configurarDialogo() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500, 600)); 
        setPreferredSize(new Dimension(500, 600));
        pack();
        setLocationRelativeTo(objVentanaPadre);
    }
    
    private void actualizarEstado(RegistroDocumentacion objRegistro) {
        if (objRegistro == null) {
            lblEstadoPago.setText("Estado del Pago: Pendiente (Error de datos)");
            lblEstadoPago.setForeground(Color.RED);
            lblCodigoPostulante.setText("");
            botonComprobar.setEnabled(true);
            return;
        }
        
        boolean bolPagoAprobado = objRegistro.getBolEstadoDePago();
        
        if (bolPagoAprobado) {
            lblEstadoPago.setText("Estado del Pago: Aprobado");
            lblEstadoPago.setForeground(Color.GREEN);
            botonComprobar.setEnabled(false);
            
            String strCI = this.objPostulante.getStrNumeroCI();
            int intIdRegistroDoc = objRegistro.getIntIdRegistroDoc();
            String strCodigo = strCI + "-" + intIdRegistroDoc;
            
            lblCodigoPostulante.setText("Codigo de Postulante: " + strCodigo);
            lblCodigoPostulante.setForeground(new Color(0, 57, 117));
            
        } else {
            lblEstadoPago.setText("Estado del Pago: Pendiente");
            lblEstadoPago.setForeground(Color.RED);
            lblCodigoPostulante.setText("");
            botonComprobar.setEnabled(true);
        }
    }

    private void crearComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.WHITE);
        
        JPanel panelSuperior = new JPanel(new GridLayout(3, 1)); 
        panelSuperior.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel("Escanea el Codigo QR para Pagar", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panelSuperior.add(lblTitulo);
        
        lblEstadoPago.setFont(new Font("Arial", Font.PLAIN, 18));
        panelSuperior.add(lblEstadoPago);
        
        lblCodigoPostulante.setFont(new Font("Arial", Font.BOLD, 16));
        lblCodigoPostulante.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelSuperior.add(lblCodigoPostulante); 
        
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        
        JPanel panelQR = new JPanel();
        panelQR.setBackground(Color.WHITE);
        panelQR.add(lblQR);
        
        panelPrincipal.add(panelQR, BorderLayout.CENTER);
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 20));
        panelBoton.setBackground(Color.WHITE);
        
        botonAtras.setFont(new Font("Arial", Font.BOLD, 14));
        botonComprobar.setFont(new Font("Arial", Font.BOLD, 14));
        
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        
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
                    JOptionPane.showMessageDialog(DialogoQrGenerado.this,
                                "Error de sistema: No se encontraron los datos de documentacion para el pago.",
                                "Error Critico",
                                JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                new Thread(() -> {
                    
                    botonComprobar.setEnabled(false);
                    lblEstadoPago.setText("Comprobando pago...");
                    lblEstadoPago.setForeground(Color.BLUE);
                    lblCodigoPostulante.setText("");
                    
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        objLogger.log(Level.SEVERE, "Interrupcion en la comprobacion de pago", ex);
                        Thread.currentThread().interrupt();
                    }
                    
                    int intIdUsuario = objPostulante.getIntIdUsuario();
                    
                    
                    RegistroDocumentacion objNuevoRegistro = objDAOPersistencia.obtenerRegistroDocumentacionPorIdUsuario(intIdUsuario);
                    
                    boolean bolEstadoPagoActualizado = false;
                    if (objNuevoRegistro != null) {
                        objRegistroDocumentacion = objNuevoRegistro;
                        objPostulante.setObjRegistroDocumentacion(objNuevoRegistro);
                        bolEstadoPagoActualizado = objNuevoRegistro.getBolEstadoDePago();
                    }
                    
                    final boolean bolPagoExitoso = bolEstadoPagoActualizado;
                    final RegistroDocumentacion objRegistroFinal = objRegistroDocumentacion;
                    
                    SwingUtilities.invokeLater(() -> {
                        
                        actualizarEstado(objRegistroFinal); 
                        
                        if (bolPagoExitoso){
                            JOptionPane.showMessageDialog(DialogoQrGenerado.this,
                                "Pago Comprobado y Aprobado! Estado de pago actualizado.",
                                "Verificacion de Pago Exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(DialogoQrGenerado.this,
                                "Pago aun pendiente! Asegurese de haber realizado la transaccion.",
                                "Verificacion de Pago Fallida",
                                JOptionPane.ERROR_MESSAGE);
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
        objLogger.log(Level.INFO, "Cerrando dialogo QR. Volviendo a Metodos de Pago.");
        this.dispose();
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