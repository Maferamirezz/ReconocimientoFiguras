

package src;

import RNA.ePerceptron;
import RNA.fActivacion;
import RNA.neurona;
import java.util.ArrayList;


/**
 * 
 * @author Luis Benitez
 */
public class capa {
    private ArrayList<neurona> capa;    //arraylist de neuronas de la capa
     
    //Constructor
    public capa(int neuronas, fActivacion funcion,ePerceptron entrenador,capa sigcapa){
        capa = new ArrayList<>();
    
        for (int i = 0; i < neuronas; i++) {
            capa.add(new neurona("Y"+i,funcion,entrenador,sigcapa));
        }
    }
    
    //Metodos aplicados a todas las neuronas de la capa
    public void VerSalida(){
        capa.forEach(next->{System.out.println(next.getId()+" Y: "+next.getY());});
    }
    public void CalcularSalida(){
        capa.forEach(next->{next.CalcularSalida();});
    }
    public void initPesos(boolean typeData){
        capa.forEach(next->{next.inicializarPesos(typeData);});
    } 
    public void distribuirEntrada(float entrada){
        capa.forEach((next) -> {
            next.agregarEntrada(entrada);
        });
    }
    public void entrenarCapa(){
        capa.forEach((next) -> {
            next.entrenar();
        });
    }
    public void resetTargets(){
        capa.forEach((next) -> {
            next.setTarget(-1);
        });
    }
    public void setTargetX(int i){
        capa.get(i).setTarget(1);
    }
    public void resetEntradas(){
        capa.forEach((next) -> {
            next.resetEntradas();
        });
    }
    public void imprimirPesos(){
        capa.forEach((next) -> {
            next.imprimirPesos();
        });
    }
    public void imprimirEntradas(){
        capa.forEach((next) -> {
            next.imprimirEntradas();
        });
    }
    
    
    
    
    //Sets y Gets
    public ArrayList<neurona> getCapa() {
        return capa;
    }
    public void setCapa(ArrayList<neurona> capa) {
        this.capa = capa;
    }
}
