package RNA;

import java.util.ArrayList;

/**
 *
 * @author Luis Benitez
 */
public class ePerceptron {

    private float alfa;
    private boolean parar;

    public ePerceptron(float alfa) {
        this.alfa = alfa;
    }

    //Metodos
    public void actualizarBias(neurona neuro, float target) {
        neuro.setBias(neuro.getBias() + target);
    }

    public boolean actualizarPesos(neurona neuro, float target) {

        if (neuro.getY() != neuro.getTarget()) {

            actualizarBias(neuro, target);

            ArrayList nPesos = neuro.getPesos();
            ArrayList entradas = neuro.getEntradas();

            System.out.println(nPesos.size());
            System.out.println(entradas.size());

            for (int i = 0; i < nPesos.size(); i++) {

                nPesos.set(i, ((float) nPesos.get(i) + (target * (float) entradas.get(i) * alfa)));
            }
            return true;
        }

        return false;
    }

    public boolean entrenar(neurona n, float target) {
        n.calcularYin();
        n.evaluarYin();
        return actualizarPesos(n, target);
    }

    //Sets y Gets
    public float getAlfa() {
        return alfa;
    }

    public boolean isParar() {
        return parar;
    }

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }

    public void setParar(boolean parar) {
        this.parar = parar;
    }
}
