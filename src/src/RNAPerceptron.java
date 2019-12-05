

package src;

import RNA.fActivacion;
import RNA.ePerceptron;
import RNA.fPerceptron;
import java.util.ArrayList;

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
    
    public RNAPerceptron(float umbral,float alfa){
        capas = new ArrayList<>();
        this.funcion=new fPerceptron(umbral);
        this.algoritmo=new ePerceptron(alfa);
        typeData=false;
        initPesos = true;
    }
    
    public boolean CrearCapa(int nNeuronas){
        return capas.add(new capa(nNeuronas,funcion,null));
    }
    public void IniciarEntrenamiento(String cadena,String target){
 
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.substring(i, i+1).equals("#")) {
                capas.get(0).distribuirEntrada(1);
            }else if(cadena.substring(i, i+1).equals(".")){
                capas.get(0).distribuirEntrada(0);
            }           
        }
        if (initPesos) {
            this.InicializarPesos();
            initPesos=false;
        }
        float temp;
        for (int i = 0; i < capas.get(0).getCapa().size(); i++) {
           temp = Float.parseFloat(target.substring(i, i+1));
           if(temp==0)temp=(float)-1;
           
           this.algoritmo.entrenar(capas.get(0).getCapa().get(i),temp,typeData);
        }
        capas.get(0).resetEntradas();
    }
    public void IniciarEntrenamiento(ArrayList cadena,ArrayList target){
        cadena.forEach(next->{
            if((boolean)next){
                capas.get(0).distribuirEntrada(1);
            }else{
                capas.get(0).distribuirEntrada(0);
            }});
        if (initPesos) {
            this.InicializarPesos();
            initPesos=false;
        }
        float temp;
        for (int i = 0; i < capas.get(0).getCapa().size(); i++) {         
           this.algoritmo.entrenar(capas.get(0).getCapa().get(i),(boolean)target.get(i),typeData);
        }
        capas.get(0).resetEntradas();
    }
    public void ImprimirPesosCapaX(int c){
        capas.get(c).imprimirPesos();
    }
    public void ImprimirEntradasCapaX(int c){
        capas.get(c).imprimirEntradas();
    }
    public void InicializarPesos(){
        capas.forEach(next->{next.initPesos(typeData);});
    }
    public void CalcularSalida(String cadena){
        capas.forEach(next->{next.resetEntradas();});
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.substring(i, i+1).equals("#")) {
                capas.get(0).distribuirEntrada(1);
            }else if(cadena.substring(i, i+1).equals(".")){
                capas.get(0).distribuirEntrada(0);
            }
         }
        capas.forEach(next->{next.CalcularSalida();});
        capas.get(0).resetEntradas();
    }
    public void CalcularSalida(ArrayList cadena){
       cadena.forEach(next->{
            if((boolean)next){
                capas.get(0).distribuirEntrada(1);
            }else{
                capas.get(0).distribuirEntrada(0);
            }});
        capas.forEach(next->{next.CalcularSalida();});
        capas.get(0).resetEntradas();
    }
    public void VerSalida(){
        capas.get(capas.size()-1).VerSalida();
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
