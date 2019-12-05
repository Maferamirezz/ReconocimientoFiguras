
package src;

/**
 *
 * @author Luis Benitez
 */
public class MainClass {

  
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContruccionRNA().setVisible(true);
            }
        });
        
    }
    
}
