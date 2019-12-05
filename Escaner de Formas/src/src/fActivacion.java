

package src;



/**
 * 
 * @author Luis Benitez
 */
public abstract class fActivacion {
protected float umbral;

public fActivacion(float umbral){
    this.umbral = umbral;
}

public abstract float evaluar(float yin);
public abstract float calcularYin(neurona n);

public float getUmbral() {
        return umbral;
    }

public void setUmbral(float umbral) {
        this.umbral = umbral;
    }
}
