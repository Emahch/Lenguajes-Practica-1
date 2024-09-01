package josecarlos.analizadorlexico;

import josecarlos.analizadorlexico.paneles.PanelContenido;
import josecarlos.analizadorlexico.utilidades.ExportImportFiles;
import josecarlos.analizadorlexico.utilidades.DialogSolicitarCuadricula;
import josecarlos.analizadorlexico.utilidades.SolicitudCanceladaException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import josecarlos.analizadorlexico.tokens.Token;

/**
 *
 * @author emahch
 */
public class FramePrincipal extends javax.swing.JFrame {

    private boolean botonesActivos = true;
    private PanelContenido panelContenido;
    private Dimension dimensionImagen;

    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        togleBotones();
        solicitarCuadricula();
        
        panelContenido = new PanelContenido(dimensionImagen);
        getContentPane().add(panelContenido, BorderLayout.CENTER);
        pack();
    }
    
    public static void main(String[] args) {
        FramePrincipal frame = new FramePrincipal();
        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAcciones = new javax.swing.JPanel();
        botonIngresarArchivo = new javax.swing.JButton();
        botonExportarPng = new javax.swing.JButton();
        botonReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analizador Léxico");
        setMinimumSize(new java.awt.Dimension(800, 500));

        botonIngresarArchivo.setBackground(new java.awt.Color(0, 102, 153));
        botonIngresarArchivo.setForeground(new java.awt.Color(255, 255, 255));
        botonIngresarArchivo.setText("Ingresar Archivo");
        botonIngresarArchivo.setFocusPainted(false);
        botonIngresarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIngresarArchivoActionPerformed(evt);
            }
        });
        panelAcciones.add(botonIngresarArchivo);

        botonExportarPng.setBackground(new java.awt.Color(0, 102, 153));
        botonExportarPng.setForeground(new java.awt.Color(255, 255, 255));
        botonExportarPng.setText("Exportar Imágen");
        botonExportarPng.setFocusPainted(false);
        botonExportarPng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExportarPngActionPerformed(evt);
            }
        });
        panelAcciones.add(botonExportarPng);

        getContentPane().add(panelAcciones, java.awt.BorderLayout.NORTH);

        botonReportes.setBackground(new java.awt.Color(0, 102, 153));
        botonReportes.setText("Reportes");
        botonReportes.setFocusPainted(false);
        botonReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReportesActionPerformed(evt);
            }
        });
        getContentPane().add(botonReportes, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void togleBotones() {
        botonesActivos = !botonesActivos;
        botonIngresarArchivo.setEnabled(botonesActivos);
        botonExportarPng.setEnabled(botonesActivos);
    }
    
    private void solicitarCuadricula() {
        DialogSolicitarCuadricula dialogCuadricula = new DialogSolicitarCuadricula(this);
        dialogCuadricula.setVisible(true);
        if (dialogCuadricula.getConfirmacion()) {
            dimensionImagen = dialogCuadricula.getDimension();
            togleBotones();
        } else {
            System.exit(0);
        }
    }

    private void botonIngresarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIngresarArchivoActionPerformed
        try {
            JTextArea txtCodigo = panelContenido.getPanelCodigo().getTxtCodigo();
            
            ExportImportFiles importArchivo = new ExportImportFiles(false);
            String texto = importArchivo.recibirArchivoEntrada();
            txtCodigo.setText(texto);
            panelContenido.getPanelCodigo().analizarTexto();
        } catch (SolicitudCanceladaException ex) {
        }
    }//GEN-LAST:event_botonIngresarArchivoActionPerformed

    private void botonExportarPngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExportarPngActionPerformed
        try {
            ExportImportFiles exportarImagen = new ExportImportFiles(true);
            exportarImagen.createImage(panelContenido.getPanelImagen());
        } catch (SolicitudCanceladaException ex) {
            
        }
        
    }//GEN-LAST:event_botonExportarPngActionPerformed

    private void botonReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReportesActionPerformed
        List<Token> tokens = panelContenido.getPanelCodigo().getTokens();
        if (tokens == null) {
            return;
        }
        
        List<String[]> reporte = new ArrayList<>();
        
        for (Token token : tokens) {
            reporte.add(token.getReporte());
        }
        
        FrameReportes frameReportes = new FrameReportes(reporte);
        frameReportes.setVisible(true);
        frameReportes.setLocationRelativeTo(null);
    }//GEN-LAST:event_botonReportesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonExportarPng;
    private javax.swing.JButton botonIngresarArchivo;
    private javax.swing.JButton botonReportes;
    private javax.swing.JPanel panelAcciones;
    // End of variables declaration//GEN-END:variables
}
