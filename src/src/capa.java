

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
    private ArrayList<neurona> arrayNeuronas;    //arraylist de neuronas de la arrayNeuronas
     
    //Constructor
    public capa(int neuronas, fActivacion funcion,ePerceptron entrenador,capa sigcapa){
        arrayNeuronas = new ArrayList<>();
    
        for (int i = 0; i < neuronas; i++) {
            arrayNeuronas.add(new neurona("Y"+i,funcion,entrenador,sigcapa));
        }
    }
    
    //Metodos aplicados a todas las neuronas de la arrayNeuronas
    public void VerSalida(){
        arrayNeuronas.forEach(next->{System.out.println(next.getId()+" Y: "+next.getY());});
    }
    public void CalcularSalida(){
        arrayNeuronas.forEach(next->{next.CalcularSalida();});
    }
    public void initPesos(boolean typeData){
        arrayNeuronas.forEach(next->{next.inicializarPesos(typeData);});
    } 
    public void distribuirEntrada(float entrada){
        arrayNeuronas.forEach((next) -> {
            next.agregarEntrada(entrada);
        });
    }
    public boolean entrenarCapa(){
        boolean [] cambios = new boolean[arrayNeuronas.size()];
        for (int i = 0; i < arrayNeuronas.size(); i++) {
            cambios[i]= arrayNeuronas.get(i).entrenar();
        }
        for (int i = 0; i < cambios.length; i++) {
            if (cambios[i]) {
                return false;
            }
        }
        return true;
    }
    public void resetTargets(){
        arrayNeuronas.forEach((next) -> {
            next.setTarget(-1);
        });
    }
    public void setTargetX(int i){
        arrayNeuronas.get(i).setTarget(1);
    }
    public void resetEntradas(){
        arrayNeuronas.forEach((next) -> {
            next.resetEntradas();
        });
    }
    public void imprimirPesos(){
        arrayNeuronas.forEach((next) -> {
            next.imprimirPesos();
        });
    }
    public void imprimirEntradas(){
        arrayNeuronas.forEach((next) -> {
            next.imprimirEntradas();
        });
    }
    
    
    
    
    //Sets y Gets
    public ArrayList<neurona> getNeuronas() {
        return arrayNeuronas;
    }
    public void setCapa(ArrayList<neurona> capa) {
        this.arrayNeuronas = capa;
    }
}
