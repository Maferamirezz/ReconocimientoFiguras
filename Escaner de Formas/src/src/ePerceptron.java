

package src;

import java.util.ArrayList;


/**
 * 
 * @author Luis Benitez
 */
public class ePerceptron{
    private float alfa;
    private boolean cambio;
    
    public ePerceptron(float alfa) {
     this.alfa = alfa;   
    }

    //Metodos
    public void actualizarBias(neurona neuro,float target){
        neuro.setBias(neuro.getBias() + target);
    } 
    public void actualizarPesos(neurona neuro, float target){
        
        if (neuro.getY()!=target) {
            
            actualizarBias(neuro,target);
            
            ArrayList nPesos = neuro.getPesos();
            ArrayList entradas = neuro.getEntradas();
            
            for (int i = 0; i < nPesos.size(); i++) {
                nPesos.set(i, ((float)nPesos.get(i)+(target*(float)entradas.get(i)*alfa)));
            }
            cambio=true;
        }else{
            cambio=false;
        }
    } 
    public void entrenar(neurona n,boolean target,boolean typeData){
        float t;
        if (target) {
            t=1;
        }else{
            t=-1;
        }
        
        n.calcularYin();
        n.evaluarYin();

        this.actualizarPesos(n, t);
        
        while (this.isCambio()) {
            n.calcularYin();
            n.evaluarYin();      
            this.actualizarPesos(n, t);
        }
        
    }
     public void entrenar(neurona n,float target,boolean typeData){
        
        n.calcularYin();
        n.evaluarYin();

        this.actualizarPesos(n, target);
        
        while (this.isCambio()) {
            n.calcularYin();
            n.evaluarYin();      
            this.actualizarPesos(n, target);
        }
        
    }

    
    //Sets y Gets
    public float getAlfa() {
        return alfa;
    }
    public boolean isCambio() {
        return cambio;
    }
    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }
    public void setCambio(boolean cambio) {
        this.cambio = cambio;
    }
}
