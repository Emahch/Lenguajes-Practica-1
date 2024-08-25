package josecarlos.analizadorlexico;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author emahch
 */
public class DialogSolicitarCuadricula extends javax.swing.JDialog {

    private int filas;
    private int columnas;
    private boolean confirmado;

    /**
     * Muestra un JDialog que solicita el tamaño de la cuadricula
     *
     * @param componente
     */
    public DialogSolicitarCuadricula(JFrame componente) {
        setModal(true);
        initComponents();
        pack();
        setLocationRelativeTo(componente);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonConfirmar = new javax.swing.JButton();
        labelTituloFilas = new javax.swing.JLabel();
        labelTituloColumnas = new javax.swing.JLabel();
        spinnerColumnas = new javax.swing.JSpinner();
        spinnerFilas = new javax.swing.JSpinner();
        labelTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Especificar tiempo");
        setMinimumSize(new java.awt.Dimension(300, 180));
        setPreferredSize(new java.awt.Dimension(300, 180));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        botonConfirmar.setBackground(new java.awt.Color(0, 102, 153));
        botonConfirmar.setFont(new java.awt.Font("Padauk", 0, 18)); // NOI18N
        botonConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        botonConfirmar.setText("Confirmar");
        botonConfirmar.setFocusPainted(false);
        botonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarActionPerformed(evt);
            }
        });

        labelTituloFilas.setForeground(new java.awt.Color(0, 0, 0));
        labelTituloFilas.setText("Filas :");

        labelTituloColumnas.setForeground(new java.awt.Color(0, 0, 0));
        labelTituloColumnas.setText("Columnas :");

        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Ingrese las dimensiones de la cuadricula");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(botonConfirmar)
                .addContainerGap(94, Short.MAX_VALUE))
            .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTituloColumnas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTituloFilas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerFilas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTituloFilas)
                    .addComponent(spinnerFilas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTituloColumnas)
                    .addComponent(spinnerColumnas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarActionPerformed
        columnas = (int) spinnerColumnas.getValue();
        filas = (int) spinnerFilas.getValue();
        boolean datosValidos = true;
        if (columnas <= 0) {
            labelTituloColumnas.setForeground(new Color(204, 0, 0));
            datosValidos = false;
        } else {
            labelTituloColumnas.setForeground(Color.BLACK);
        }
        if (filas <= 0) {
            labelTituloFilas.setForeground(new Color(204, 0, 0));
            datosValidos = false;
        } else {
            labelTituloFilas.setForeground(Color.BLACK);
        }
        if (!datosValidos) {
            return;
        }
        confirmado = true;
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_botonConfirmarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.confirmado = false;
    }//GEN-LAST:event_formWindowClosing

    public Dimension getDimension() {
        return new Dimension(columnas, filas);
    }

    public boolean getConfirmacion() {
        return confirmado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelTituloColumnas;
    private javax.swing.JLabel labelTituloFilas;
    private javax.swing.JSpinner spinnerColumnas;
    private javax.swing.JSpinner spinnerFilas;
    // End of variables declaration//GEN-END:variables
}
