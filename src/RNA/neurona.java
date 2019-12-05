

package RNA;

import java.util.ArrayList;
import src.capa;


/**
 * 
 * @author Luis Benitez
 */
public class neurona {

    
    private String id;
    private float yin;
    private float y;
    private float bias;
    private fActivacion funcion;
    private ePerceptron entrenador;
    private ArrayList<Float> pesos;
    private ArrayList<Float> entradas;
    private capa sigCapa;
    private float target;
    
    ///Constructor
    public neurona(String id, fActivacion funcion,ePerceptron e, capa sigcapa){
        this.id=id;
        pesos = new ArrayList<>();
        entradas = new ArrayList<>();
        this.sigCapa = sigcapa; 
        this.funcion= funcion;
        this.entrenador = e;
    }

    //Metodos
    public void agregarEntrada(float valor){
        entradas.add(valor);
    }
    public void resetEntradas(){
        entradas = new ArrayList<>();
    }
    public void inicializarPesos(boolean typeData){
        if (typeData) {
            entradas.forEach((_item) -> {
                pesos.add((float)Math.random());
            });
            this.bias = (float)Math.random();
        }else{
            entradas.forEach((_item) -> {
            pesos.add((float)0);
        });
            this.bias=0;
        }
    }
    public void calcularYin(){
        yin=funcion.calcularYin(this);
    }
    public void evaluarYin(){
        y=funcion.evaluar(yin);
        
    }
    public void enviarY(){
        if(sigCapa!=null){
            sigCapa.distribuirEntrada(y);
        }
    }
    public void CalcularSalida(){
        this.calcularYin();
        this.evaluarYin();
        this.enviarY();
    }
    public void entrenar(){
        entrenador.entrenar(this, target);
    }
    
    public void imprimirPesos(){
        System.out.println("Neurona: "+this.id);
        System.out.println("Bias: "+this.getBias());
        for (int i = 0; i < pesos.size(); i++) {
            if(pesos.get(i)!=0)System.out.println("W"+i+": "+pesos.get(i));
        }
    }
    public void imprimirEntradas(){
        System.out.println("Neurona: "+this.id);
        for (int i = 0; i < pesos.size(); i++) {
            System.out.println(entradas.get(i));
        }
    }
     
    ///////Sets y Gets
    public String getId() {
        return id;
    }
    public float getYin() {
        return yin;
    }
    public float getBias() {
        return bias;
    }
    public ArrayList getPesos() {
        return pesos;
    }
    public fActivacion getFuncion() {
        return funcion;
    }
    public capa getSigCapa() {
        return sigCapa;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public void setYin(float yin) {
        this.yin = yin;
    }
    public void setBias(float bias) {
        this.bias = bias;
    }
    public void setPesos(ArrayList pesos) {
        this.pesos = pesos;
    }
    public void setFuncion(fActivacion funcion) {
        this.funcion = funcion;
    }
    public ArrayList getEntradas() {
        return entradas;
    }
    public void setEntradas(ArrayList entradas) {
        this.entradas = entradas;
    }
    public void setSigCapa(capa sigCapa) {
        this.sigCapa = sigCapa;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public ePerceptron getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(ePerceptron entrenador) {
        this.entrenador = entrenador;
    }
}
