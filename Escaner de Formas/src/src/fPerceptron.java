

package src;


/**
 * 
 * @author Luis Benitez
 */
public class fPerceptron extends fActivacion  {
    
    
    public fPerceptron(float umbral){
        super(umbral);
    }

    @Override
    public float evaluar(float yin){
    if (yin > this.umbral) {
        return 1;
    }else if(yin < -umbral){
        return -1;
    }
    return 0;
}

    @Override
    public float calcularYin(neurona n){
        float yin=0;
        if (n.getPesos().size() == n.getEntradas().size()) {
            for (int i = 0; i < n.getEntradas().size(); i++) {
            yin+= (float)n.getEntradas().get(i) * (float)n.getPesos().get(i);
        }
            yin+=n.getBias();
        }
        return yin;
    }



}
