package josecarlos.analizadorlexico;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 *
 * @author emahch
 */
public class PanelCodigo extends javax.swing.JPanel {

    private Analizador analizador;
    /**
     * Creates new form PanelCodigo
     */
    public PanelCodigo() {
        initComponents();
        analizador = new Analizador();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPosicionTexto = new javax.swing.JLabel();
        scrollPanel = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();
        labelPosicionImagen = new javax.swing.JLabel();
        labelTituloPosicionTexto = new javax.swing.JLabel();
        labelTituloPosicionCuadricula = new javax.swing.JLabel();

        labelPosicionTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPosicionTexto.setText("Linea: X, Columna: X");

        scrollPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setColumns(20);
        txtCodigo.setFont(new java.awt.Font("Nimbus Mono L", 0, 15)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(0, 0, 0));
        txtCodigo.setLineWrap(true);
        txtCodigo.setRows(5);
        txtCodigo.setText("PRUEBA");
        txtCodigo.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        txtCodigo.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCodigoCaretUpdate(evt);
            }
        });
        scrollPanel.setViewportView(txtCodigo);

        labelPosicionImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPosicionImagen.setText("Linea: X, Columna: X");

        labelTituloPosicionTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloPosicionTexto.setText("Texto");

        labelTituloPosicionCuadricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTituloPosicionCuadricula.setText("Cuadricula");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPanel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTituloPosicionTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPosicionTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPosicionImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                            .addComponent(labelTituloPosicionCuadricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTituloPosicionTexto)
                    .addComponent(labelTituloPosicionCuadricula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPosicionTexto)
                    .addComponent(labelPosicionImagen)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodigoCaretUpdate
        int linea = 1;
        int columna = 1;
        try {
            int caretpos = txtCodigo.getCaretPosition();

            linea = txtCodigo.getLineOfOffset(caretpos);
            columna = caretpos - txtCodigo.getLineStartOffset(linea);
            linea += 1;
        } catch (BadLocationException ex) {
        }
        actualizarEstado(linea, columna);
    }//GEN-LAST:event_txtCodigoCaretUpdate

    private void analizarTexto(String texto){
        analizador.analizarTexto(texto);
    }
    
    private void actualizarEstado(int linea, int columna) {
        labelPosicionTexto.setText("Linea: " + linea + ", Columna: " + columna);
        analizarTexto(txtCodigo.getText());
//        try {
//            txtCodigo.getDocument().getText(0, 1);
//            labelPosicionImagen.setText(txtCodigo.getDocument().getText(0, 2));
//        } catch (BadLocationException ex) {
//            
//        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelPosicionImagen;
    private javax.swing.JLabel labelPosicionTexto;
    private javax.swing.JLabel labelTituloPosicionCuadricula;
    private javax.swing.JLabel labelTituloPosicionTexto;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JTextArea txtCodigo;
    // End of variables declaration//GEN-END:variables

    public JLabel getLabelPosicionImagen() {
        return labelPosicionImagen;
    }

    public JLabel getLabelPosicionTexto() {
        return labelPosicionTexto;
    }

    public JTextArea getTxtCodigo() {
        return txtCodigo;
    }
}
