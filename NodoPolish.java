
package compiladorruby;

/**
 *
 * @author Jonathan
 */
public class NodoPolish {
     String lexema;
      String tipo;
      
    NodoPolish sig = null;

    public NodoPolish(String lexema, String tipo) {
        this.lexema = lexema;
        this.tipo = tipo;
    }
}
