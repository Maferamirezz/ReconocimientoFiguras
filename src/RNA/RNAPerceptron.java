package RNA;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import src.capa;

/**
 *
 * @author Luis Benitez
 */
public class RNAPerceptron {

    private ArrayList<capa> capas;
    private fActivacion funcion;
    private ePerceptron algoritmo;
    private boolean typeData;   //indica si se inicializa con numeros ramdon 0-1 o 0s
    private boolean initPesos;

    public RNAPerceptron(float umbral, float alfa) {
        capas = new ArrayList<>();
        this.funcion = new fPerceptron(umbral);
        this.algoritmo = new ePerceptron(alfa);
        typeData = false;
        initPesos = false;
    }

    public boolean CrearCapa(int nNeuronas) {
        return capas.add(new capa(nNeuronas, funcion, algoritmo, null));
    }

    public void EntrenamientoInicial(ArrayList<BufferedImage> registro, int TargetFigura) {
        boolean[] parar = new boolean[registro.size()];
        boolean alto;
        do {
            System.out.println("++++++++++++++++++++++++++++++++++epoca+++++++++++++++++++++++++++++++++++");
            for (int i = 0; i < registro.size(); i++) { //Por cada figura a entrenar
                capas.get(0).resetEntradas();

                //Recoleccion de entradas
                for (int j = 0; j < registro.get(i).getWidth(); j++) {  //recorrido recogiendo los datos X de la imagen
                    for (int k = 0; k < registro.get(i).getHeight(); k++) { // recorrido recogiendo los datos Y de la imagen
                        if (registro.get(i).getRGB(j, k) == -1) {
                            capas.get(0).distribuirEntrada(1);
                        } else {
                            capas.get(0).distribuirEntrada(0);
                        }
                    }
                }
//                System.out.println(i);
//                this.InicializarPesos();//esta instruccion va aqui porque los pesos se inicializan segun el numero de entradas
                capas.get(0).resetTargets();
                capas.get(0).setTargetX(i * TargetFigura);
                parar[i] = capas.get(0).entrenarCapa();
            }

            //todos deben ser verdaderos para que pare el entrenamiento
            for (int i = 0; i < parar.length; i++) {
                if (!parar[i]) {
                    alto = false;
                }
            }
            alto = true;
        } while (!alto);//Mientras no halla cambios al final de la epoca
    }

    //vista por consola
    public void ImprimirPesosCapaX(int c) {
        capas.get(c).imprimirPesos();
    }

    public void ImprimirPesosCapaXNeuronaY(int x, int y) {
        capas.get(x).getNeuronas().get(y).imprimirPesos();
    }

    public void ImprimirEntradasCapaX(int c) {
        capas.get(c).imprimirEntradas();
    }

    //manda a inicializar los pesos de todas las capas y en la capa se manda a inicializar los pesos de las neuronas
    public void InicializarPesos() {
        if (!initPesos) {
            capas.forEach(next -> {
                next.initPesos(typeData);
            });
            initPesos = true;
        }
    }

    public void CalcularSalida(BufferedImage buffer) {
        capas.get(0).resetEntradas();

        //Recoleccion de entradas
        for (int j = 0; j < buffer.getWidth(); j++) {  //recorrido recogiendo los datos X de la imagen
            for (int k = 0; k < buffer.getHeight(); k++) { // recorrido recogiendo los datos Y de la imagen
                if (buffer.getRGB(j, k) == -1) {
                    capas.get(0).distribuirEntrada(1);
                } else {
                    capas.get(0).distribuirEntrada(0);
                }
            }
        }

        capas.get(0).CalcularSalida();

    }

    public String ObtenerSalida() {
        String salida = "";
        for (int i = 0; i < capas.get(0).getNeuronas().size(); i++) {
            salida += capas.get(0).getNeuronas().get(i).getId() + ": " + capas.get(0).getNeuronas().get(i).getY() + "\n";
        }
        return salida;

    }

    //Sets y Gets
    public ArrayList<capa> getCapas() {
        return capas;
    }

    public fActivacion getFuncion() {
        return funcion;
    }

    public ePerceptron getAlgoritmo() {
        return algoritmo;
    }

    public boolean isInitRandom() {
        return typeData;
    }

    public void setCapas(ArrayList<capa> capas) {
        this.capas = capas;
    }

    public void setFuncion(fActivacion funcion) {
        this.funcion = funcion;
    }

    public void setAlgoritmo(ePerceptron algoritmo) {
        this.algoritmo = algoritmo;
    }

    public void setInitRandom(boolean typeData) {
        this.typeData = typeData;
    }
}
