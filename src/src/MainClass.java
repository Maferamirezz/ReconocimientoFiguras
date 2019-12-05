
package src;

/**
 *
 * @author Luis Benitez
 */
public class MainClass {
static ConstruccionRNA Constructor;
static Interfaz Pantalla;
  
    public static void main(String[] args) {
        Constructor = new ConstruccionRNA(Constructor, Pantalla);
    }
    
}
