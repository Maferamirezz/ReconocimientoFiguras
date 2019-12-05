package src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Luis Benitez
 */
public class Interfaz extends javax.swing.JFrame {

    Graphics paint;
    Graphics2D paint2d;

    //Constructor
    public Interfaz() {
        initComponents();

        setLocationRelativeTo(null);
    }

    public Interfaz(DefaultListModel jlist) {
        initComponents();

        setLocationRelativeTo(null);

        ListaFormas.setModel(jlist);
    }

    //Metodos
    private void MostrarPreview() {

        Graphics grafico = PanelPreview.getGraphics();
        Graphics2D g2d = (Graphics2D) grafico;
        FigurasGeometricas figura = new FigurasGeometricas();

        Rectangle2D rectangulo;
        Ellipse2D elipse;
        Polygon poligono;

        switch (ListaFormas.getSelectedValue()) {
            case "Cuadradro":
                rectangulo = figura.Cuadrado();
                g2d.draw(rectangulo);
                PanelPreview.paintComponents(g2d);
                break;
            case "Rectangulo":
                rectangulo = figura.Rectangulo();
                g2d.draw(rectangulo);
                PanelPreview.paintComponents(g2d);
                break;
            case "Elipse":
                elipse = figura.Elipse();
                g2d.draw(elipse);
                PanelPreview.paintComponents(g2d);
                break;
            case "Circulo":
                elipse = figura.Circulo();
                g2d.draw(elipse);
                PanelPreview.paintComponents(g2d);
                break;
            case "Triangulo":
                poligono = figura.Tringulo();
                g2d.drawPolygon(poligono);
                PanelPreview.paintComponents(g2d);
                break;
            case "Trapecio":
                poligono = figura.Trapecio();
                g2d.drawPolygon(poligono);
                PanelDibujo.paintComponents(g2d);
                break;
            case "Hexagono":
                poligono = figura.Hexagono();
                g2d.drawPolygon(poligono);
                PanelDibujo.paintComponents(g2d);
                break;
            default:

        }

    }

    private void GenerarFormas() {
        paint = this.PanelDibujo.getGraphics();
        paint2d = (Graphics2D) paint;
        int random;

        for (int i = 0; i < 10; i++) {
            random = (int) (Math.random() * 7 + 1);
            if (random == 1) {
                paint2d.draw(new Rectangle2D.Double(
                        Math.random() * (PanelDibujo.getWidth() - 200),
                        Math.random() * (PanelDibujo.getHeight() - 200),
                        200, 150));
                PanelDibujo.paintComponents(paint2d);
            }
            if (random == 2) {
                paint2d.draw(new Rectangle2D.Double(
                        Math.random() * (PanelDibujo.getWidth() - 200),
                        Math.random() * (PanelDibujo.getHeight() - 200),
                        200, 200));
                PanelDibujo.paintComponents(paint2d);
            }
            if (random == 3) {
                paint2d.draw(new Ellipse2D.Double(
                        Math.random() * (PanelDibujo.getWidth() - 200),
                        Math.random() * (PanelDibujo.getHeight() - 200),
                        200, 150));
                PanelDibujo.paintComponents(paint2d);
            }
            if (random == 4) {
                paint2d.draw(new Ellipse2D.Double(
                        Math.random() * (PanelDibujo.getWidth() - 200),
                        Math.random() * (PanelDibujo.getHeight() - 200),
                        200, 200));
                PanelDibujo.paintComponents(paint2d);
            }
            if (random == 5) {
                int X = (int) (Math.random() * (PanelDibujo.getWidth() - 200));
                int Y = (int) (Math.random() * (PanelDibujo.getHeight() - 200));

                int x[] = {X, X + 90, X + 180};
                int y[] = {Y + 190, Y + 10, Y + 190};
                paint2d.draw(new Polygon(x, y, 3));
                PanelDibujo.paintComponents(paint2d);
            }
            if (random == 6) {
                int X = (int) (Math.random() * (PanelDibujo.getWidth() - 200));
                int Y = (int) (Math.random() * (PanelDibujo.getHeight() - 200));

                int x[] = {X, X + 50, X + 150, X + 190};
                int y[] = {Y + 190, Y + 10, Y + 10, Y + 190};
                paint2d.draw(new Polygon(x, y, 4));
                PanelDibujo.paintComponents(paint2d);
            }
            if (random == 7) {
                int X = (int) (Math.random() * (PanelDibujo.getWidth() - 200));
                int Y = (int) (Math.random() * (PanelDibujo.getHeight() - 200));

                int x[] = {X, X+50, X+150, X+190, X+150, X+50};
                int y[] = {Y+100, Y+10, Y+10, Y+100, Y+190, Y+190};
                paint2d.draw(new Polygon(x, y, 6));
                PanelDibujo.paintComponents(paint2d);
            }
        }

    }

    private void InsertarForma(int x, int y) {
        paint = this.PanelDibujo.getGraphics();
        paint2d = (Graphics2D) paint;

        FigurasGeometricas figura = new FigurasGeometricas();
        Rectangle2D rectangulo;
        Ellipse2D elipse;
        Polygon poligono;
        x -= 100;
        y -= 100;
        if (x > 600) {
            x = 599;
        }
        if (y > 300) {
            y = 299;
        }
        if (x < 1) {
            x = 1;
        }
        if (y < 1) {
            y = 1;
        }

        switch (ListaFormas.getSelectedValue()) {
            case "Cuadradro":
                paint2d.draw(new Rectangle2D.Double(x, y, 200, 200));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Rectangulo":
                paint2d.draw(new Rectangle2D.Double(x, y + 50, 200, 150));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Elipse":
                paint2d.draw(new Ellipse2D.Double(x, y + 50, 200, 150));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Circulo":
                paint2d.draw(new Ellipse2D.Double(x, y, 200, 200));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Triangulo":
                int xp[] = {x, x + 100, x + 200};
                int yp[] = {y + 200, y, y + 200};
                paint2d.draw(new Polygon(xp, yp, 3));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Trapecio":
                int xa[] = {x, x + 50, x + 150, x + 190};
                int yb[] = {y + 190, y + 10, y + 10, y + 190};
                paint2d.draw(new Polygon(xa, yb, 4));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Hexagono":
                int xc[] = {x, x + 50, x + 150, x + 190, x + 150, x + 50};
                int yd[] = {y + 100, y + 10, y + 10, y + 100, y + 190, y + 190};
                paint2d.draw(new Polygon(xc, yd, 6));
                PanelDibujo.paintComponents(paint2d);
                break;
            default:

        }

    }

    private void RecolectarEntradas() {
        System.out.println(PanelDibujo.getGraphicsConfiguration().getColorModel());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelDibujo = new javax.swing.JPanel();
        PanelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaFormas = new javax.swing.JList<>();
        SubPanelMenu = new javax.swing.JPanel();
        PanelPreview = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RNA");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(204, 204, 255));
        setResizable(false);

        PanelDibujo.setBackground(new java.awt.Color(255, 255, 255));
        PanelDibujo.setMaximumSize(new java.awt.Dimension(800, 500));
        PanelDibujo.setMinimumSize(new java.awt.Dimension(800, 500));
        PanelDibujo.setPreferredSize(new java.awt.Dimension(800, 500));
        PanelDibujo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelDibujoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout PanelDibujoLayout = new javax.swing.GroupLayout(PanelDibujo);
        PanelDibujo.setLayout(PanelDibujoLayout);
        PanelDibujoLayout.setHorizontalGroup(
            PanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        PanelDibujoLayout.setVerticalGroup(
            PanelDibujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        ListaFormas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Circulo", "Elipse", "Cuadradro", "Rectangulo", "Triangulo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaFormas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ListaFormasValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(ListaFormas);

        PanelPreview.setBackground(new java.awt.Color(255, 255, 255));
        PanelPreview.setMaximumSize(new java.awt.Dimension(200, 200));
        PanelPreview.setMinimumSize(new java.awt.Dimension(200, 200));
        PanelPreview.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout PanelPreviewLayout = new javax.swing.GroupLayout(PanelPreview);
        PanelPreview.setLayout(PanelPreviewLayout);
        PanelPreviewLayout.setHorizontalGroup(
            PanelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        PanelPreviewLayout.setVerticalGroup(
            PanelPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jButton2.setText("Entrenar");

        jButton3.setText("Contar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Generar");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout SubPanelMenuLayout = new javax.swing.GroupLayout(SubPanelMenu);
        SubPanelMenu.setLayout(SubPanelMenuLayout);
        SubPanelMenuLayout.setHorizontalGroup(
            SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubPanelMenuLayout.createSequentialGroup()
                .addGroup(SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PanelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SubPanelMenuLayout.createSequentialGroup()
                        .addGroup(SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        SubPanelMenuLayout.setVerticalGroup(
            SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SubPanelMenuLayout.createSequentialGroup()
                .addComponent(PanelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)))
        );

        javax.swing.GroupLayout PanelMenuLayout = new javax.swing.GroupLayout(PanelMenu);
        PanelMenu.setLayout(PanelMenuLayout);
        PanelMenuLayout.setHorizontalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SubPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelMenuLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelMenuLayout.setVerticalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SubPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelDibujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ListaFormasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ListaFormasValueChanged
        if (evt.getValueIsAdjusting()) {
            PanelPreview.repaint();
        } else {
            MostrarPreview();
        }
    }//GEN-LAST:event_ListaFormasValueChanged

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        GenerarFormas();
    }//GEN-LAST:event_jButton4MouseReleased

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        PanelDibujo.repaint();
    }//GEN-LAST:event_jButton4MousePressed

    private void PanelDibujoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelDibujoMousePressed
        if (!ListaFormas.isSelectionEmpty() && evt.getButton() == 1) {
            InsertarForma(evt.getX(), evt.getY());
        }
        if (evt.getButton() == 3 && evt.getClickCount() > 1) {
            PanelDibujo.repaint();
        }
    }//GEN-LAST:event_PanelDibujoMousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        RecolectarEntradas();
    }//GEN-LAST:event_jButton3ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaFormas;
    private javax.swing.JPanel PanelDibujo;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JPanel PanelPreview;
    private javax.swing.JPanel SubPanelMenu;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
