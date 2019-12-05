

package src;

import RNA.fActivacion;
import RNA.neurona;
import java.util.ArrayList;


/**
 * 
 * @author Luis Benitez
 */
public class capa {
    private ArrayList<neurona> capa;
     
    
    public capa(int neuronas, fActivacion funcion,capa sigcapa){
        capa = new ArrayList<>();
    
        for (int i = 0; i < neuronas; i++) {
            capa.add(new neurona("Y"+i,funcion,sigcapa));
        }
    }
    
    public void VerSalida(){
        capa.forEach(next->{System.out.println(next.getY());});
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
    
    
    
    
    
    public ArrayList<neurona> getCapa() {
        return capa;
    }
    public void setCapa(ArrayList<neurona> capa) {
        this.capa = capa;
    }
}
