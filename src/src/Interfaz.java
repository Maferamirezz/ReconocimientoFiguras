
package src;

import RNA.RNAPerceptron;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Luis Benitez
 */
public class Interfaz extends javax.swing.JFrame {
    Graphics paint;
    Graphics2D paint2d;
    Graphics2D paint2dt;
    RNAPerceptron rna;
    BufferedImage bufferPanelDibujo;
    ArrayList<figuraAgregada> registro;
    int AnchoFigura = 200;int AltoFigura = 200;
    int[] contador;
    
   
    
    //Constructor
    public Interfaz(ListModel jList) {
        initComponents();
        
        ReiniciarBuffer();  //Reinicia el bufferPanelDibujo para guardar lo que se dibuja en el paneldibujo para luego binarizar
        ListaFormas.setModel(jList);    //Nos lista las formas que recibimos de la pantalla anterior
        CrearRNA();
        EntrenamientoInicial();
               
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //Metodos
    private void ReiniciarBuffer(){
        bufferPanelDibujo = new BufferedImage(800, 500, BufferedImage.TYPE_INT_RGB);
        paint2dt = (Graphics2D) bufferPanelDibujo.getGraphics();
        registro = new ArrayList<>();
    }
    
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
    
    private void GenerarFormas(){
        int x,y;
        
        for (int i = 0; i < ListaFormas.getModel().getSize(); i++) {
            x= ((int)(Math.random()*PanelDibujo.getWidth())); 
            y= ((int)(Math.random()*PanelDibujo.getHeight())); 
            if(x>600)x=590;
            if(y>300)y=290;   
            InsertarForma(ListaFormas.getModel().getElementAt(i),x,y);
        }               
    }
    
    private void InsertarForma(String forma,int x, int y){
        paint = this.PanelDibujo.getGraphics();
        paint2d = (Graphics2D) paint;
        paint2dt = (Graphics2D) bufferPanelDibujo.getGraphics();        

        x-=(x%5);
        y-=(y%5);
        if(x<0)x=5;if(x>600)x=595;
        if(y<0)y=5;if(y>300)y=295;
        if (forma.equals("Elipse") && y==5)y=0;        //Puramente estetico con cordenadas >=0 y <=300 no hay errores
        if (forma.equals("Rectangulo") && y==5)y=0;    //Puramente estetico    
        if (forma.equals("Elipse") && y==295)y=300;     //Puramente estetico
        if (forma.equals("Rectangulo") && y==295)y=300; //Puramente estetico
        
        
        //registramos las figuras agregadas, el nombre, coordenadas 
        registro.add(new figuraAgregada(forma,x,y));
        
        switch (forma) {
            case "Cuadradro":
                paint2d.draw(new Rectangle2D.Double(x,y,200,200));
                paint2dt.draw(new Rectangle2D.Double(x,y,200,200));
                PanelDibujo.paintComponents(paint2d);
            break;
            case "Rectangulo":
                paint2d.draw(new Rectangle2D.Double(x,y+25,200,150));
                paint2dt.draw(new Rectangle2D.Double(x,y+25,200,150));
                PanelDibujo.paintComponents(paint2d);
            break;
            case "Elipse":
                paint2d.draw(new Ellipse2D.Double(x,y+25,200,150));
                paint2dt.draw(new Ellipse2D.Double(x,y+25,200,150));
                PanelDibujo.paintComponents(paint2d);
            break;
            case "Circulo":
                paint2d.draw(new Ellipse2D.Double(x,y,200,200));
                paint2dt.draw(new Ellipse2D.Double(x,y,200,200));
                PanelDibujo.paintComponents(paint2d);
            break;
            case "Triangulo":
                int xp[] = {x, x + 100, x + 200};
                int yp[] = {y + 200, y, y + 200};
                paint2d.draw(new Polygon(xp, yp, 3));
                paint2dt.draw(new Polygon(xp, yp, 3));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Trapecio":
                int xa[] = {x, x + 50, x + 150, x + 200};
                int yb[] = {y + 200, y, y, y + 200};
                paint2d.draw(new Polygon(xa, yb, 4));
                paint2dt.draw(new Polygon(xa, yb, 4));
                PanelDibujo.paintComponents(paint2d);
                break;
            case "Hexagono":
                int xc[] = {x, x + 50, x + 150, x + 200, x + 150, x + 50};
                int yd[] = {y + 100, y, y, y + 100, y + 200, y + 200};
                paint2d.draw(new Polygon(xc, yd, 6));
                paint2dt.draw(new Polygon(xc, yd, 6));
                PanelDibujo.paintComponents(paint2d);
                break;
            default:
                
        }
        
    }
    
    private void RecolectarEntradas(){
        
        for (int i = 0; i < PanelDibujo.getHeight()-200 ; i+=5) {
            for (int j = 0; j < PanelDibujo.getWidth()-200; j+=5) {                                
                rna.CalcularSalida(bufferPanelDibujo.getSubimage(j, i, AnchoFigura, AltoFigura));
                contar();
            }
        }

        for (int i = 0; i < contador.length; i++) {
            System.out.println(contador[i]);
        }
    }
    
    private void contar(){
        for (int i = 0; i < rna.getCapas().get(0).getNeuronas().size(); i++) {
            if (rna.getCapas().get(0).getNeuronas().get(i).getY() == 1) {
                contador[i]++;
            }
            
        }
        
    }
    
    private void CargarImagen(){
        JFileChooser file = new JFileChooser();
        file.showOpenDialog(this);
        
        
        if (file.getSelectedFile()!=null) {
            paint = this.PanelDibujo.getGraphics();
            paint2d = (Graphics2D) paint;
            try {
                BufferedImage imagen = ImageIO.read(file.getSelectedFile());
                bufferPanelDibujo= imagen;
                paint2d.drawImage(imagen, 0, 0, PanelDibujo.getWidth(), PanelDibujo.getHeight(), null);
                PanelDibujo.paintComponents(paint2d);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Problemas al abrir el archivo");
            }
     
            
        }
    }
    
    private void GuardarImagenPNG(BufferedImage buffer,String nombre){
        try {
            ImageIO.write(buffer, "png", new File(nombre+".png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void CrearRNA(){
        rna = new RNAPerceptron(0,1);   //Instanciamos la clase de la RNA con umbral 0 y alfa 1
        rna.CrearCapa(ListaFormas.getModel().getSize());    //Ocupamos una capa por lo que un llamado a la funcion con el tamaño de la lista
                                                            //como parametro para crear las neuronas necesarias 
    }
            
    private void EntrenarRNA(){
        ArrayList<BufferedImage> buffer = new ArrayList<>();
        for (int i = 0; i < registro.size(); i++) {
            buffer.add(bufferPanelDibujo.getSubimage(
                    registro.get(i).getX(),
                    registro.get(i).getY(),
                    200,
                    200));
        }
//        rna.IniciarEntrenamiento(buffer);
    }
    
    private void EntrenamientoInicial(){
        //Unicamente para este proyecto, despues de entrenarlo y guardar los pesos puede desaparecer
        ArrayList<BufferedImage> arrayFigurasTotales = new ArrayList<>();
        ArrayList<BufferedImage> arrayFigurasNoTotales = new ArrayList<>();
        BufferedImage bf;
        for (int m = 0; m < ListaFormas.getModel().getSize(); m++) {
            bf = new BufferedImage(600,600,BufferedImage.TYPE_INT_RGB);
            guardarEnBuffer(ListaFormas.getModel().getElementAt(m),bf.getGraphics(),false);
            arrayFigurasTotales.add(bf.getSubimage(200, 200, 200, 200));
            
//            for (int i = 0; i < 200; i++) {
//                for (int j = 0; j < 400; j++) {
//                    arrayFigurasNoTotales.add(bf.getSubimage(j, i, 200, 200));
//                }
//            }
//            
//            for (int i = 201; i < 400; i++) {
//                for (int j = 0; j < 400; j++) {
//                    arrayFigurasNoTotales.add(bf.getSubimage(j, i, 200, 200));
//                }
//            }
            
        }        
        rna.EntrenamientoInicial(arrayFigurasTotales,1);
        rna.EntrenamientoInicial(arrayFigurasTotales,1);
//        rna.EntrenamientoInicial(arrayFigurasNoTotales,0);
        
    }
    
    private void CheckLastAdded(){
        if (!registro.isEmpty()) {
            rna.CalcularSalida(bufferPanelDibujo.getSubimage(
                registro.get(registro.size()-1).getX(),
                registro.get(registro.size()-1).getY(),
                200,
                200));
        JOptionPane.showMessageDialog(this, rna.ObtenerSalida());
        }
    }
    
    private void CheckAllForms(){
         //Unicamente para este proyecto, despues de entrenarlo y guardar los pesos puede desaparecer
        ArrayList<BufferedImage> arrayFigurasTotales = new ArrayList<>();
        BufferedImage bf;
        for (int i = 0; i < ListaFormas.getModel().getSize(); i++) {
            bf = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
            guardarEnBuffer(ListaFormas.getModel().getElementAt(i),bf.getGraphics(),true);
            arrayFigurasTotales.add(bf);
        }  
        for (int i = 0; i < arrayFigurasTotales.size(); i++) {
             rna.CalcularSalida(arrayFigurasTotales.get(i));
            JOptionPane.showMessageDialog(this, ListaFormas.getModel().getElementAt(i)+"\n"+rna.ObtenerSalida());
        }
        
    }
    
    private void guardarEnBuffer(String forma, Graphics graphicsbuffer,boolean init){
        Graphics g = graphicsbuffer;
        Graphics2D g2d = (Graphics2D) g;  
        int x=200;
        int y=200;
        if(init){
             x=0;
             y=0;
        }else{
            
        }
        
        switch (forma) {
            case "Cuadradro":
                g2d.draw(new Rectangle2D.Double(x,y,200,200));                
            break;
            case "Rectangulo":
                g2d.draw(new Rectangle2D.Double(x,y+25,200,150));
            break;
            case "Elipse":
                g2d.draw(new Ellipse2D.Double(x,y+25,200,150));
            break;
            case "Circulo":
                g2d.draw(new Ellipse2D.Double(x,y,200,200));
            break;
            case "Triangulo":
                int xp[] = {x, x + 100, x + 200};
                int yp[] = {y + 200, y, y + 200};
                g2d.draw(new Polygon(xp, yp, 3));
                break;
            case "Trapecio":
                int xa[] = {x, x + 50, x + 150, x + 200};
                int yb[] = {y + 200, y, y, y + 200};
                g2d.draw(new Polygon(xa, yb, 4));
                break;
            case "Hexagono":
                int xc[] = {x, x + 50, x + 150, x + 200, x + 150, x + 50};
                int yd[] = {y + 100, y, y, y + 100, y + 200, y + 200};
                g2d.draw(new Polygon(xc, yd, 6));
                break;
            default:
                
        }
        
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
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmGuardar = new javax.swing.JMenuItem();
        jmCargar = new javax.swing.JMenuItem();
        jmSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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

        jButton1.setText("Cargar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
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
                        .addGroup(SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        SubPanelMenuLayout.setVerticalGroup(
            SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SubPanelMenuLayout.createSequentialGroup()
                .addComponent(PanelPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SubPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1)))
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

        jMenu1.setText("Archivo");

        jmGuardar.setText("Guardar Pesos w");
        jMenu1.add(jmGuardar);

        jmCargar.setText("Cargar Pesos w");
        jMenu1.add(jmCargar);

        jmSalir.setText("Salir");
        jMenu1.add(jmSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Entrenamiento");

        jMenuItem1.setText("Entrenar Ultima Agregada");
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Entrenar Imagen Completa");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("jMenuItem3");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Calculo de Salidas");

        jMenuItem4.setText("Verificar Ultima Agregada");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Verificar Figuras");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Impresion de Valores");

        jMenuItem6.setText("Ver Pesos");
        jMenu4.add(jMenuItem6);

        jMenuItem7.setText("Ver Salidas Yn");
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

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
        if(evt.getValueIsAdjusting()){
            PanelPreview.repaint();
        }else{
            MostrarPreview();
        }
    }//GEN-LAST:event_ListaFormasValueChanged

    private void jButton4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseReleased
        GenerarFormas();
    }//GEN-LAST:event_jButton4MouseReleased

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        PanelDibujo.repaint();
        ReiniciarBuffer();
    }//GEN-LAST:event_jButton4MousePressed

    private void PanelDibujoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelDibujoMousePressed
        if(!ListaFormas.isSelectionEmpty() && evt.getButton()==1){
            InsertarForma(ListaFormas.getSelectedValue(),evt.getX()-100,evt.getY()-100);
        }
        if(evt.getButton()==3 && evt.getClickCount()>1){
            PanelDibujo.repaint();
            ReiniciarBuffer();
            registro=new ArrayList<>();
        }
    }//GEN-LAST:event_PanelDibujoMousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        contador=new int[ListaFormas.getModel().getSize()];
        RecolectarEntradas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        PanelDibujo.repaint();
        ReiniciarBuffer();
        registro=new ArrayList<>();
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        CargarImagen();
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        this.RecolectarEntradas();
    this.InsertarForma("Circulo", 0, 0);
        System.out.println("Prueba Figura 1");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(0, 0, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 2");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(1, 0, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 3");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(2, 0, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 4");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(3, 0, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 5");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(4, 0, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 6");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(5, 0, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 7");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(6, 0, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 7");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(1, 2, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 7");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(2, 1, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 7");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(3, 1, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
        System.out.println("Prueba Figura 7");
        rna.CalcularSalida(bufferPanelDibujo.getSubimage(4, 1, 200, 200));
        System.out.println(rna.getCapas().get(0).getNeuronas().get(0).getY());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.CheckLastAdded();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        CheckAllForms();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Interfaz().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaFormas;
    private javax.swing.JPanel PanelDibujo;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JPanel PanelPreview;
    private javax.swing.JPanel SubPanelMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmCargar;
    private javax.swing.JMenuItem jmGuardar;
    private javax.swing.JMenuItem jmSalir;
    // End of variables declaration//GEN-END:variables
}

